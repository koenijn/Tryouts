/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.javapersistenceapi.application;

import javax.ejb.Local;
import nl.boomenvanden.tryouts.javapersistenceapi.application.domain.Order;

/**
 *
 * @author mark
 */
@Local
public interface OrderRepository {
    void save(Order order) throws DomainException;
    Order findByPk(Long id);
}
