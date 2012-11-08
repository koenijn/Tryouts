/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.ejbwithcdi.domain.model.person;

import java.util.List;
import nl.boomenvanden.tryouts.ejbwithcdi.domain.model.person.Person;

/**
 *
 * @author mark
 */
public interface PersonRepository {
    
    void persistPerson(Person person);
    
    Person getPersonByName(String name);
    
    List<Person> findAllPersons();
}
