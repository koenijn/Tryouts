/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.jmstestproject.ejb;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.jms.*;

/**
 *
 * @author mark
 */
@Singleton
@LocalBean
public class JmsMyQueueSenderBean {
    
    @Resource(mappedName = "jms/MyQueue")
    private Queue myQueue;
    @Resource(mappedName = "jms/MyQueueFactory")
    private ConnectionFactory myQueueFactory;

    private boolean blaa = false;
    
    @Schedule(hour="*", minute = "*", second = "*/10")
    public void myTimer() throws JMSException {
        blaa = !blaa;
        String action = blaa ? "proc1" : "proc2";
        sendToMyQueue("Triggered by the timer", action);
    }
    
    
    public void sendToMyQueue(String message, String action) throws JMSException {
        
        sendJMSMessageToMyQueue("Message send by " + JmsMyQueueSenderBean.class.getSimpleName() + " on " + new Date() + ": " + message, action);
        
    }

    private Message createJMSMessageForjmsMyQueue(Session session, Object messageData, String action) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setStringProperty("ACTION", action);
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToMyQueue(Object messageData, String action) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = myQueueFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(myQueue);
            messageProducer.send(createJMSMessageForjmsMyQueue(session, messageData, action));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
}
