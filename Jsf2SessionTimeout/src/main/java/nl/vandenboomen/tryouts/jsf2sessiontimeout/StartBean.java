/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.jsf2sessiontimeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mark
 */
@ManagedBean
@ViewScoped
public class StartBean {
    private String value;
    private int pressCount;
    
    @PostConstruct
    private void init() {
        value = "This is session scoped";
        pressCount = 0;
    }
    
    public void doStuff() {
        pressCount++;
    }

    public String getValue() {
        return value;
    }

    public int getPressCount() {
        return pressCount;
    }
    
    
}
