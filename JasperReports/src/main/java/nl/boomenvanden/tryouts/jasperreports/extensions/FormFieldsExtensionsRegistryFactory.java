/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jasperreports.extensions;

import java.util.Collections;
import java.util.List;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.export.GenericElementHandler;
import net.sf.jasperreports.engine.export.GenericElementHandlerBundle;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;

/**
 *
 * @author mark
 */
public class FormFieldsExtensionsRegistryFactory implements ExtensionsRegistryFactory {

    public static final String NAMESPACE = "http://boomenvanden.nl/tryouts/jasperreports";
    private static final GenericElementHandlerBundle HANDLER_BUNDLE = new GenericElementHandlerBundle() {

        public String getNamespace() {
            return NAMESPACE;
        }

        public GenericElementHandler getHandler(String elementName, String exporterKey) {
            if(JRPdfExporter.PDF_EXPORTER_KEY.equals(exporterKey) 
                    && FormFieldPdfHandler.ELEMENT_NAME.equals(elementName)) {
                return FormFieldPdfHandler.getInstance();
            }
            return null;
        }
    };
    
    
    private static final ExtensionsRegistry FORM_FIELDS_REGISTRY = new ExtensionsRegistry() {

        public <T> List<T> getExtensions(Class<T> extensionType) {
            if (GenericElementHandlerBundle.class.equals(extensionType)) {
                return (List<T>) Collections.singletonList((Object) HANDLER_BUNDLE);
            } else {
                return null;
            }
        }
    };

    public ExtensionsRegistry createRegistry(String string, JRPropertiesMap jrpm) {
        return FORM_FIELDS_REGISTRY;
    }
}
