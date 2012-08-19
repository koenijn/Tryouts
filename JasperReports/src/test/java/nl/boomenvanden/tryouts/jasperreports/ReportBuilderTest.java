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
        g1.setGroupNumber(1L);

        TheGroupObject g2 = new TheGroupObject();
        g2.setGroupText("Group2");
        g2.setGroupNumber(2L);

        TheGroupObject g3 = new TheGroupObject();
        g3.setGroupNumber(3L);

        TheRowObject r1 = new TheRowObject();
        r1.setMyText("r1");
        r1.setParent(g1);
        r1.setRowNumber(1L);

        TheRowObject r2 = new TheRowObject();
        r2.setMyText("r2");
        r2.setParent(g1);
        r2.setRowNumber(2L);

        TheRowObject r3 = new TheRowObject();
        r3.setMyText("r3");
        r3.setParent(g2);
        r3.setRowNumber(1L);

        TheRowObject r4 = new TheRowObject();
        r4.setMyText("r4");
        r4.setParent(g2);
        r4.setRowNumber(2L);

        TheRowObject r5 = new TheRowObject();
        r5.setMyText("r5");
        r5.setParent(g3);
        r5.setRowNumber(1L);

        TheRowObject r6 = new TheRowObject();
        r6.setMyText("r6");
        r6.setParent(g3);
        r6.setRowNumber(2L);

        List<TheRowObject> rows = new ArrayList<TheRowObject>();
        rows.add(r1);
        rows.add(r2);
        rows.add(r3);
        rows.add(r4);
        rows.add(r5);
        rows.add(r6);

        OutputStream output = new FileOutputStream("target/output.pdf");

        builder.generate(rows, output);

    }
}
