/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jasperreports;

/**
 *
 * @author mark
 */
public class ReportException extends Exception {

    public ReportException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ReportException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ReportException(String string) {
        super(string);
    }
    
}
