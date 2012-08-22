/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jasperreports;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

/**
 *
 * @author mark
 */
public class ReportBuilder {
    public void generate(List<TheRowObject> rows, OutputStream output) throws ReportException {
        InputStream report = this.getClass().getResourceAsStream("theReport.jasper");
        
        Map parameters = new HashMap();
        parameters.put(JRPdfExporterParameter.PROPERTY_PDF_VERSION, JRPdfExporterParameter.PDF_VERSION_1_7);
        
        JRDataSource dataSource = createDataSource(rows);
        try {
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
            
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRPdfExporterParameter.PDF_VERSION, JRPdfExporterParameter.PDF_VERSION_1_7);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
            exporter.exportReport();
//            JasperExportManager.exportReportToPdfStream(print, output);
            
        } catch (JRException ex) {
            throw new ReportException("Exception while generating report", ex);
        }
    }
    
    private JRDataSource createDataSource(List<TheRowObject> rows) {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rows);
        return dataSource;
    }
}
