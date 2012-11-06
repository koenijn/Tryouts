/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.ejbwithcdi.infrastructure;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import nl.boomenvanden.tryouts.ejbwithcdi.application.PersonRepository;
import nl.boomenvanden.tryouts.ejbwithcdi.application.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mark
 */
@Named
//@RepositoryType(RepositoryType.Type.IN_MEMORY) // qualifier for this specific implementation
@Default // makes this PersonRepository the default implementation (used instead of qualifier above)
@Stateless
public class InMemoryPersonRepository implements PersonRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryPersonRepository.class);
    
    @PostConstruct
    public void postConstruct() {
        LOG.info(this.toString() + " Created");
    }
    
    @PreDestroy
    public void preDestroy() {
        LOG.info(this.toString() + " Destroyed");
    }
    
    public void persistPerson(Person person) {
        LOG.info(this.toString() + " persistPerson()");
    }

    public Person getPersonByName(String name) {
        LOG.info(this.toString() + " getPersonByName()");
        return null;
    }

    public List<Person> findAllPersons() {
        LOG.info(this.toString() + " findAllPersons()");
        return null;
    }
    
    
}
