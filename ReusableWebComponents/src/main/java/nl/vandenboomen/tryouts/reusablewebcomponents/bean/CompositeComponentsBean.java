/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.reusablewebcomponents.bean;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
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
        
        // begin programmaticaly add component
        UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent(":myForm:programmaticContainer");
        
        UIComponent inputStuff = FacesContext.getCurrentInstance().getApplication().createComponent(HtmlInputText.COMPONENT_TYPE);
        HtmlInputText realInput = (HtmlInputText) inputStuff;
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        ValueExpression nameExpression = FacesContext.getCurrentInstance().getApplication().getExpressionFactory().createValueExpression(elContext, "#{compositeComponentsBean.yourName}", String.class);
        
        realInput.setValueExpression("value", nameExpression);
        
        component.getChildren().add(inputStuff);
        // end programmaticaly add component
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
