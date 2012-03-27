/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.reusablewebcomponents.bean;

import com.sun.faces.scripting.groovy.GroovyHelper;
import java.io.IOException;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIPanel;
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

        FacesContext fctx = FacesContext.getCurrentInstance();
        Application appl = fctx.getApplication();
        ELContext elContext = fctx.getELContext();
        ExpressionFactory elFact = appl.getExpressionFactory();
        
        UIComponent container = fctx.getViewRoot().findComponent(":myForm:programmaticContainer");
        ValueExpression nameExpression = elFact.createValueExpression(elContext, "#{compositeComponentsBean.yourName}", String.class);
        
        // begin programmaticaly add component
        UIComponent inputText = appl.createComponent(HtmlInputText.COMPONENT_TYPE);
        
        inputText.setId("dynamicComponent");
        inputText.setValueExpression("value", nameExpression);
        
        container.getChildren().add(inputText);
        // end programmaticaly add component
        
        
        // begin programmaticaly add composite component
        Resource customResource = appl.getResourceHandler().createResource("testje.xhtml", "ezcomp");
        
        UIComponent customComponent = appl.createComponent(fctx, customResource);
        customComponent.setId("dynamicComposite");
        
        customComponent.setValueExpression("yourName", nameExpression);
        
        // currently it's not possible to automaticaly initialize the child components, so dynamic initializing is currently not (or very hard) possible for composite components.
        {
            // only programmatic initialization is possible :-(
            UIComponent panel = appl.createComponent(UIPanel.COMPONENT_TYPE);

            UIComponent test = appl.createComponent(UIInput.COMPONENT_TYPE);
            test.setValueExpression("value", nameExpression);
            panel.getChildren().add(test);

            Object o = customComponent.getFacets();
            boolean b = customComponent.getRendersChildren();
            customComponent.getFacets().put(UIComponent.COMPOSITE_FACET_NAME, panel);
        }
        
        container.getChildren().add(customComponent);
        // end programmaticaly add composite component
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
    
    public boolean isGroovyAvailable() {
        Map<String, Object> blaa = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
        return GroovyHelper.isGroovyAvailable(FacesContext.getCurrentInstance());
    }

}
