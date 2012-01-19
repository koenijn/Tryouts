/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.vandenboomen.tryouts.mojarracrashcannotcreatesessionafter;

import java.io.Serializable;
import java.util.Arrays;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mark
 */
@ManagedBean
@ViewScoped // or @SessionScoped
public class Bean implements Serializable {

    public String getExceedBuffer() {
        int size = FacesContext.getCurrentInstance().getExternalContext().getResponseBufferSize();
        char[] chars = new char[size];
        Arrays.fill(chars, 'x');
        return new String(chars);
    }

}