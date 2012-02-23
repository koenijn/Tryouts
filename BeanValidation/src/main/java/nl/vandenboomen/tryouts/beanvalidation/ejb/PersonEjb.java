/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.beanvalidation.ejb;

import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import nl.vandenboomen.tryouts.beanvalidation.exception.ValidationException;
import nl.vandenboomen.tryouts.beanvalidation.model.Person;

/**
 *
 * @author mark
 */
@Stateless
@LocalBean
public class PersonEjb {
    
    @Resource
    Validator validator;

    public void savePerson(@NotNull(message="Shit happens") Person person) throws ValidationException {
        Set validationErrors = validator.validate(person, Default.class);
        
        if (validationErrors != null && validationErrors.size() != 0) {
            throw new ValidationException(validationErrors);
        }
        
        
        // logic to save person
//        BeanValidator
    }

    public void saveNullPerson(@NotNull(message="Shit happens") Person person) throws ValidationException {
        // blaaa
    }

}
