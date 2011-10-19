/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.mvnliquibase.model;

import java.io.FileInputStream;
import java.sql.Connection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import junit.framework.TestCase;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.PropertyValueException;
import org.hibernate.ejb.HibernateEntityManager;
import org.junit.Test;

/**
 *
 * @author mark
 */
public class PersonTest extends DBTestCase {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    private Connection conn;
    
    public PersonTest(String testName) {
        super(testName);

        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/mvnLiquibase" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "mvnLiquibase" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "1234" );
    }
    
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/database/data/dataset_PersonTest.xml"));
    }

    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            emf = Persistence.createEntityManagerFactory("mvnLiquibasePU");
            em = emf.createEntityManager();
        } catch(Exception e) {
            e.printStackTrace();
            fail("Problem setting up persistence unit");
        }
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        
        if (em != null)
            em.close();
        if (emf != null)
            emf.close();
    }
    
    protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception
    {
        return DatabaseOperation.DELETE;
    }

    public void testGetPersonOk() {
        Long key = new Long(101);
        Person expected = new Person();
        expected.setPrsId(key);
        expected.setName("Mark");
        
        Person person = em.find(Person.class, key);
        
        assertEquals(expected, person);
    }
    
    @Test
    public void testPersistOk() {
        Person person = new Person();
        
        person.setName("Should work");
        
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            
            assertNotNull(person.getPrsId());
        }
        catch (Exception e) {
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
        }
        catch (Exception e) {
            // should go wrong so all ok!!
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
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
        }
        catch (PersistenceException e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
    }
}
