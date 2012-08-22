/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jasperreports.extensions;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.io.IOException;
import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.export.GenericElementPdfHandler;
import net.sf.jasperreports.engine.export.JRPdfExporterContext;

/**
 *
 * @author mark
 */
public final class FormFieldPdfHandler implements GenericElementPdfHandler {

    public static String ELEMENT_NAME = "formField";
    
    private static final FormFieldPdfHandler INSTANCE = new FormFieldPdfHandler();
    
    private FormFieldPdfHandler() {}
    
    
    public static FormFieldPdfHandler getInstance() {
        return INSTANCE;
    }
    
    public void exportElement(JRPdfExporterContext exporterContext, JRGenericPrintElement element) {
        PdfWriter writer = exporterContext.getPdfWriter();
        
        
        Rectangle rect = new Rectangle(50, 50);
        TextField field = new TextField(writer, rect, "myField");
        field.setBackgroundColor(Color.darkGray);
        field.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
        field.setBorderColor(Color.BLUE);
        field.setBorderWidth(2);
        field.setVisibility(TextField.VISIBLE);
        field.setText("This is my text field");
        
        try {
            PdfFormField d = field.getTextField();
            writer.addAnnotation(d);
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (DocumentException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    public boolean toExport(JRGenericPrintElement element) {
        return true;
    }
    
}
