/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.ejbwithcdi.application;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import nl.boomenvanden.tryouts.ejbwithcdi.application.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mark
 */
@Named
@Stateless
public class PersonService {
    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);
    
    @Inject
    private PersonRepository personRepository;
    
    @PostConstruct
    public void postConstruct() {
        LOG.info(this.toString() + " Created");
    }
    
    @PreDestroy
    public void preDestroy() {
        LOG.info(this.toString() + " Destroyed");
    }
    
    public Person createPersonWithName(String name) {
        LOG.info(this.toString() + " createPersonWithName()");
        
        Person person = new Person(name);
        
        personRepository.persistPerson(person);
        
        return person;
    }
    
    public Person getPersonByname(String name) {
        LOG.info(this.toString() + " getPersonByname()");
        
        Person person = personRepository.getPersonByName(name);
        
        return person;
    }
    
    public List<Person> findAllPersons() {
        LOG.info(this.toString() + " findAllPersons()");
        
        List<Person> persons = personRepository.findAllPersons();
        return persons;
    }
}
