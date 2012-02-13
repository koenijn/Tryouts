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
public class JmsMyTopicSenderBean {
    @Resource(mappedName = "jms/MyTopic")
    private Topic myTopic;
    @Resource(mappedName = "jms/MyTopicFactory")
    private ConnectionFactory myTopicFactory;
    
    @Schedule(hour="*", minute = "*", second = "*/10")
    public void myTimer() throws JMSException {
        
        sendToMyTopic("Triggered by the timer");
        
    }
    
    public void sendToMyTopic(String message) throws JMSException {
        
        sendJMSMessageToMyTopic("Message send by " + JmsMyTopicSenderBean.class.getSimpleName() + " on " + new Date() + ": " + message);
        
    }

    private Message createJMSMessageForjmsMyTopic(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToMyTopic(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = myTopicFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(myTopic);
            messageProducer.send(createJMSMessageForjmsMyTopic(session, messageData));
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
