/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.ejbwithcdi.infrastructure;

import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.persistence.*;

/**
 *
 * @author mark
 */
class DatabaseProducer implements Serializable {
    
    @Produces
    @PersistenceContext(unitName="thePU")
    @DefaultDatabase
    public EntityManager defaultDatabase;
}
