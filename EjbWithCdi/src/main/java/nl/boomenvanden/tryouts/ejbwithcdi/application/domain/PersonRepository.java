/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.ejbwithcdi.application.domain;

import java.util.List;
import nl.boomenvanden.tryouts.ejbwithcdi.application.domain.model.Person;

/**
 *
 * @author mark
 */
public interface PersonRepository {
    
    void persistPerson(Person person);
    
    Person getPersonByName(String name);
    
    List<Person> findAllPersons();
}
