/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.beanvalidation.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import nl.vandenboomen.tryouts.beanvalidation.model.Person;

/**
 *
 * @author mark
 */
@Stateless
@LocalBean
public class PersonEjb {

    public void savePerson(Person person) {
        // logic to save person
//        BeanValidator
    }
    
}
