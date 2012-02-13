/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.jmstestproject.ui;

import java.util.Stack;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.jms.JMSException;
import nl.vandenboomen.tryouts.jmstestproject.ejb.JmsMyQueueSenderBean;
import nl.vandenboomen.tryouts.jmstestproject.ejb.JmsMyTopicSenderBean;
import nl.vandenboomen.tryouts.jmstestproject.ejb.TheGlobalBufferBean;

/**
 *
 * @author mark
 */
@ManagedBean
@RequestScoped
public class LoggedMessagesBean {

    @EJB
    private TheGlobalBufferBean gbb;
    
    @EJB
    private JmsMyQueueSenderBean queueSender;
    
    @EJB
    private JmsMyTopicSenderBean topicSender;
    
    private Stack<String> messages;
    
    private String message;

    @PostConstruct
    private void init() {
        this.messages = gbb.getMessages();
    }
    
    public Stack<String> getMessages() {
        return messages;
    }
    
    public void clearMessages() {
        this.gbb.clear();
    }
    
    public void sendMessageQueue() throws JMSException {
        queueSender.sendToMyQueue(message);
    }
    
    public void sendMessageTopic() throws JMSException {
        topicSender.sendToMyTopic(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

}
