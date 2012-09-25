/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jbehave.application.infrastructure;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.boomenvanden.tryouts.jbehave.application.CustomerRepository;
import nl.boomenvanden.tryouts.jbehave.application.domain.Customer;
import org.apache.commons.lang3.Validate;

/**
 *
 * @author mark
 */
@Stateless
public class JpaCustomerRepository implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public JpaCustomerRepository() {
    }

    public JpaCustomerRepository(EntityManager em) {
        this.em = Validate.notNull(em);
    }
    
    public void createCustomer(Customer customer) {
        em.persist(customer);
    }
    
    public List<Customer> findAllCustomers() {
        String query = "select c from Customer c order by c.companyName";
        List<Customer> result = em.createQuery(query).getResultList();
        return result;
    }

    public Customer findByCustomerNumber(Long customerNumber) {
        Customer customer = em.find(Customer.class, Validate.notNull(customerNumber));
        return customer;
    }
    
}
