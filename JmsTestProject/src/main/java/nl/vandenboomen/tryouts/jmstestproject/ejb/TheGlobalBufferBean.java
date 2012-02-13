/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.jmstestproject.ejb;

import java.util.Stack;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author mark
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@LocalBean
public class TheGlobalBufferBean {
    
    private Stack<String> messages = new Stack<String>();

    public synchronized void logProcessed(Class clazz, String message) {
        messages.push(clazz.getSimpleName() + ": " + message);
    }

    public Stack<String> getMessages() {
        return messages;
    }
    
    public synchronized void clear() {
        messages.clear();
    }
}
