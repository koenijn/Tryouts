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

/**
 *
 * @author mark
 */
public class ReportBuilder {
    public void generate(List<TheRowObject> rows, OutputStream output) throws ReportException {
        InputStream report = this.getClass().getResourceAsStream("theReport.jasper");
        
        Map parameters = new HashMap();
        
        JRDataSource dataSource = createDataSource(rows);
        try {
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
            
            JasperExportManager.exportReportToPdfStream(print, output);
            
        } catch (JRException ex) {
            throw new ReportException("Exception while generating report", ex);
        }
    }
    
    private JRDataSource createDataSource(List<TheRowObject> rows) {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rows);
        return dataSource;
    }
}
