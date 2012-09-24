/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jbehave.application;

import javax.ejb.Stateless;
import nl.boomenvanden.tryouts.jbehave.application.domain.Customer;

/**
 *
 * @author mark
 */
@Stateless
public class CustomerService {
    
    private CustomerRepository customerRepository;

    public CustomerService() {
    }

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    public void createNewCustomer(Customer customer) {
        
        customerRepository.createCustomer(customer);
        
    }
    
}
