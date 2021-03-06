/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.ejbwithcdi.presentation;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import nl.boomenvanden.tryouts.ejbwithcdi.application.PersonMaintenanceService;
import nl.boomenvanden.tryouts.ejbwithcdi.domain.model.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mark
 */
//@Named
@ManagedBean
@ViewScoped
public class PersonsBean {
    private static final Logger LOG = LoggerFactory.getLogger(PersonsBean.class);
    
    @Inject
    private PersonMaintenanceService personService;
    
    private List<Person> persons;
    
    private String personName;
    
    @PostConstruct
    public void postConstruct() {
        LOG.info(this.toString() + " Created");
        loadPersons();
    }
    
    @PreDestroy
    public void preDestroy() {
        LOG.info(this.toString() + " Destroyed");
    }
    
    public String createPerson(String personName) {
        LOG.info(this.toString() + " createPerson");
        Person createdPerson = personService.createPersonWithName(personName);
        
        persons.add(createdPerson);
        
        return "index.xhtml";
    }
    
    public void reload() {
        loadPersons();
    }
    
    private void loadPersons(){
        this.persons = personService.findAllPersons();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
