/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.ejbwithcdi.infrastructure;

import nl.boomenvanden.tryouts.ejbwithcdi.domain.model.person.PersonRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.boomenvanden.tryouts.ejbwithcdi.domain.model.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mark
 */
@Named
//@RepositoryType(Type.PERSISTENT) // qualifier for this specific implementation
@Alternative // makes this PersonRepository an alternative implementation (used instead of qualifier above)
@Stateless
public class JpaPersonRepository implements PersonRepository {
    private static final Logger LOG = LoggerFactory.getLogger(JpaPersonRepository.class);
    
    @Inject
    @DefaultDatabase
    private EntityManager em;
    
    
    @PostConstruct
    public void postConstruct() {
        LOG.info(this.toString() + " Created");
    }
    
    @PreDestroy
    public void preDestroy() {
        LOG.info(this.toString() + " Destroyed");
    }
    
    public void persistPerson(Person person) {
        LOG.info(this.toString() + " PersistPerson()");
        em.persist(person);
    }

    public Person getPersonByName(String name) {
        LOG.info(this.toString() + " getPersonByName()");
        Person result = (Person) em.createQuery("select p from Person p where p.name = :name")
                .setParameter("name", name)
                .getSingleResult();
        return result;
    }

    public List<Person> findAllPersons() {
        LOG.info(this.toString() + " findAllPersons()");
        List<Person> persons = (List<Person>) em.createQuery("select p from Person p order by p.name")
                .getResultList();
        return persons;
    }
    
    
}
