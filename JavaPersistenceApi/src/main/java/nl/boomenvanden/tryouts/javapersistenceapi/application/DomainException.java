/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.javapersistenceapi.application;

/**
 *
 * @author mark
 */
public class DomainException extends Exception {

    public DomainException(Throwable thrwbl) {
        super(thrwbl);
    }

    public DomainException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public DomainException(String string) {
        super(string);
    }
    
}
