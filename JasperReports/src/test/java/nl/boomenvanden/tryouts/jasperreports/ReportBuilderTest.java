/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jasperreports;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author mark
 */
public class ReportBuilderTest {
    
    ReportBuilder builder;
    
    @Before
    public void setUp() {
        builder = new ReportBuilder();
    }

    /**
     * Test of generate method, of class ReportBuilder.
     */
    @Test
    public void testGenerate() throws Exception {
        System.out.println("generate");
        
        TheGroupObject g1 = new TheGroupObject();
        g1.setGroupText("Group1");
        
        TheGroupObject g2 = new TheGroupObject();
        g2.setGroupText("Group2");
        
        TheRowObject r1 = new TheRowObject();
        r1.setMyText("r1");
        r1.setParent(g1);
        
        TheRowObject r2 = new TheRowObject();
        r2.setMyText("r2");
        r2.setParent(g1);
        
        TheRowObject r3 = new TheRowObject();
        r3.setMyText("r3");
        r3.setParent(g2);
        
        TheRowObject r4 = new TheRowObject();
        r4.setMyText("r4");
        r4.setParent(g2);
        
        List<TheRowObject> rows = new ArrayList<TheRowObject>();
        rows.add(r1);
        rows.add(r2);
        rows.add(r3);
        rows.add(r4);
        
        OutputStream output = new FileOutputStream("target/output.pdf");
        
        builder.generate(rows, output);
        
    }
}
