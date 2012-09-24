/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jbehave.application;

import javax.ejb.Local;
import nl.boomenvanden.tryouts.jbehave.application.domain.Customer;

/**
 *
 * @author mark
 */
@Local
public interface CustomerRepository {
    void createCustomer(Customer customer);
}
