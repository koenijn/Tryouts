/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.beanvalidation.bean;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import nl.vandenboomen.tryouts.beanvalidation.ejb.PersonEjb;
import nl.vandenboomen.tryouts.beanvalidation.exception.ValidationException;
import nl.vandenboomen.tryouts.beanvalidation.model.Person;

/**
 *
 * @author mark
 */
@ManagedBean
@ViewScoped
public class PersonFormBean {
    
    private Person newPerson;
    
    @EJB
    private PersonEjb personEjb;
    
    @PostConstruct
    private void init() {
        newPerson = new Person();
    }

    public Person getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
    }
    
    public void save() {
        String message = String.format("Added person with name: %s %s", newPerson.getFirstName(), newPerson.getLastName());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }
    
    public void saveNoUiToEjb() {
        Person thePerson = new Person();
        try {
            personEjb.savePerson(thePerson); // the validation should go of
        } catch (ValidationException ex) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            for (ConstraintViolation violation : ex.getValidationErrors()) {
                fctx.addMessage(null, new FacesMessage("EJB", violation.getMessage()));
            }
        }
    }

    public void saveNoUiNullToEjb() {
        Person thePerson = null;
        try {
            personEjb.saveNullPerson(thePerson); // the validation should go of
        } catch (ValidationException ex) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            for (ConstraintViolation violation : ex.getValidationErrors()) {
                fctx.addMessage(null, new FacesMessage("EJB", violation.getMessage()));
            }
        }
    }
}
