/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.mvnliquibase.model;

import javax.persistence.PersistenceException;
import nl.boomenvanden.tryouts.mvnliquibase.testing.BasePersistenceTestCase;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

/**
 *
 * @author mark
 */
public class PersonTest extends BasePersistenceTestCase {

    public PersonTest(String testName) {
        super(testName);
    }
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testGetPersonOk() throws Exception {
        final String dataSet = "src/test/database/data/dataset_PersonTest.xml";
        Long key = new Long(101);
        Person expected = new Person();
        expected.setPrsId(key);
        expected.setName("Mark");

        loadDataSet(DatabaseOperation.REFRESH, dataSet);

        Person person = em.find(Person.class, key);

        loadDataSet(DatabaseOperation.DELETE, dataSet);

        assertEquals(expected, person);
    }
    
    @Test
    public void testPersonTableContents() throws Exception {
        final String dataSetFile = "src/test/database/data/dataset_base.xml";
        IDataSet dataSet = getDataSet(dataSetFile);
        
        ITable expected = dataSet.getTable("persons");
        ITable actual = getDatabaseConnection().createTable("persons");
        
        Assertion.assertEquals(new SortedTable(expected), new SortedTable(actual, expected.getTableMetaData()));
    }

    @Test
    public void testPersistOk() {
        Person person = new Person();

        person.setName("Should work");

        try {
            em.getTransaction().begin();
            em.persist(person);
            
            assertNotNull(person.getPrsId());
            
            em.getTransaction().rollback();
        } catch (Exception e) {
            fail("Shouldn't go wrong: " + e.getMessage());
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Test
    public void testPersistNokPkFilled() {
        Person person = new Person();

        person.setPrsId(new Long(999));
        person.setName("Should not work");

        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            fail("Should go wrong");
        } catch (Exception e) {
            // should go wrong so all ok!!
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    @Test
    public void testPersistNokNullName() {
        Person person = new Person();

        person.setName(null);

        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            fail("Should go wrong; name is null!");
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }
}
