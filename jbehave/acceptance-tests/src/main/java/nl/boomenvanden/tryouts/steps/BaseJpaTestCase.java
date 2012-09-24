/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.steps;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mark
 */
public abstract class BaseJpaTestCase {
    
    // Used article: http://eskatos.wordpress.com/2007/10/15/unit-test-jpa-entities-with-in-memory-database/
    
    EntityManagerFactory emFactory;
    EntityManager em;
    
    public void beforeScenario() throws Exception {
        emFactory = Persistence.createEntityManagerFactory("jpaPU");
        em = emFactory.createEntityManager();
    }
    
    public void afterScenario() throws Exception {
        if (em != null) {
            em.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
    }
    
}
