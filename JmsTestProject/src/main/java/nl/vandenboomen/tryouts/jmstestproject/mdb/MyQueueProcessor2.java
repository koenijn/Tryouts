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
@MessageDriven(mappedName="jms/MyQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                                  propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                                  propertyValue = "javax.jms.Queue")
    })
public class MyQueueProcessor2 implements MessageListener {

    @EJB
    private TheGlobalBufferBean gbb;
    
    @Override
    public void onMessage(Message message) {
        try {
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            String contents = msg.getText();
            
            gbb.logProcessed(MyQueueProcessor2.class, contents);
            
        }
        else {
            throw new IllegalArgumentException("Message type not supported!");
        }
        } catch (JMSException e) {
            
        }
    }
    
}
