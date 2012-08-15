/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.javapersistenceapi.infrastructure;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.boomenvanden.tryouts.javapersistenceapi.application.DomainException;
import nl.boomenvanden.tryouts.javapersistenceapi.application.OrderRepository;
import nl.boomenvanden.tryouts.javapersistenceapi.application.domain.Order;

/**
 *
 * @author mark
 */
@Stateless
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;
    
    public JpaOrderRepository() {}

    public JpaOrderRepository(EntityManager em) {
        this.em = em;
    }
    
    public void save(Order order) throws DomainException {
        em.persist(order);
    }

    public Order findByPk(Long id) {
        Order order = em.find(Order.class, id);
        return order;
    }
    
}
