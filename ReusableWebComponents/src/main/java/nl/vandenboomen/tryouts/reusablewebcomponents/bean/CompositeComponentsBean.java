/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.reusablewebcomponents.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author mark
 */
@ManagedBean
@ViewScoped
public class CompositeComponentsBean {

    private String yourName;

    @PostConstruct
    private void init() {
        yourName = "default name";
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
