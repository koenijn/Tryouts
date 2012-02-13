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

    @Schedule(hour="*", minute = "*", second = "*/10")
    public void myTimer() throws JMSException {
        
        sendToMyQueue("Triggered by the timer");
        
    }
    
    
    public void sendToMyQueue(String message) throws JMSException {
        
        sendJMSMessageToMyQueue("Message send by " + JmsMyQueueSenderBean.class.getSimpleName() + " on " + new Date() + ": " + message);
        
    }

    private Message createJMSMessageForjmsMyQueue(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToMyQueue(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = myQueueFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(myQueue);
            messageProducer.send(createJMSMessageForjmsMyQueue(session, messageData));
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
