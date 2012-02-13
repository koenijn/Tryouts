/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.jmstestproject.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import nl.vandenboomen.tryouts.jmstestproject.ejb.TheGlobalBufferBean;

/**
 *
 * @author mark
 */
@MessageDriven(mappedName="jms/MyTopic", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                                  propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                                  propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionName",
                                  propertyValue = "MyTopicProcessor2")
    })
public class MyTopicProcessor1 implements MessageListener {

    @EJB
    private TheGlobalBufferBean gbb;
    
    @Override
    public void onMessage(Message message) {
        try {
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            String contents = msg.getText();
            
            gbb.logProcessed(MyTopicProcessor1.class, contents);
            
        }
        else {
            throw new IllegalArgumentException("Message type not supported!");
        }
        } catch (JMSException e) {
            
        }
    }
    
}
