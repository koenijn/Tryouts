/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.beanvalidation.exception;

import java.util.Set;
import javax.validation.ConstraintViolation;
import nl.vandenboomen.tryouts.beanvalidation.model.Person;

/**
 *
 * @author mark
 */
public class ValidationException extends Exception {

    private final Set<ConstraintViolation> validationErrors;
    
    public ValidationException(Set<ConstraintViolation> validationErrors, Throwable thrwbl) {
        super(thrwbl);
        this.validationErrors = validationErrors;
    }

    public ValidationException(Set<ConstraintViolation> validationErrors, String string, Throwable thrwbl) {
        super(string, thrwbl);
        this.validationErrors = validationErrors;
    }

    public ValidationException(Set<ConstraintViolation> validationErrors, String string) {
        super(string);
        this.validationErrors = validationErrors;
    }

    public ValidationException(Set<ConstraintViolation> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Set<ConstraintViolation> getValidationErrors() {
        return validationErrors;
    }
}
