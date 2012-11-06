/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.ejbwithcdi.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.inject.Scope;

/**
 *
 * @author mark
 */
@Inherited
@Scope // or @javax.enterprise.context.NormalScope
@Retention(RUNTIME)
@Target({METHOD, FIELD, TYPE})
public @interface CustomScope {
}
