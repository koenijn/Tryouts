/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.reusablewebcomponents.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author mark
 */
@ManagedBean
@ViewScoped
public class AjaxBean {

    private String value;
    private String selectedValue;

    private List<String> items;
    
    @PostConstruct
    private void init() {
        items = new ArrayList<String>();
        items.add("Option1");
        items.add("Option2");
        items.add("Option3");
        items.add("Option4");
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getItems() {
        return items;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }
    
    
    
}
