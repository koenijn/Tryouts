/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.reusablewebcomponents.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author mark
 */
@ManagedBean
@ViewScoped
public class CompositeComponentsBean {

    private String yourName;
    private int popupTriggerCount = 0;
    
    @PostConstruct
    private void init() {
        yourName = "default name";
    }
    
    public void popupAction(ActionEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The popupbutton has been triggered " + ++popupTriggerCount + " times!"));
    }
    
    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName + "-";
    }
    
    public void doStuff() {
        
    }
    
}
