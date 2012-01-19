/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.mvnliquibase.testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import sun.tools.tree.SuperExpression;

/**
 *
 * @author mark
 */
public abstract class BasePersistenceTestCase extends TestCase {

    private EntityManagerFactory emf;
    protected EntityManager em;
    
    public BasePersistenceTestCase(String testName) {
        super(testName);
        
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/mvnLiquibase");
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "mvnLiquibase");
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "1234");
    }
    
    
    public IDatabaseConnection getDatabaseConnection() throws Exception {
        IDatabaseConnection connection = null;
        
        Connection conn = getConnection();

        connection = new DatabaseConnection(conn);

        return connection;
    }
    
    
    public Connection getConnection() throws Exception {
        Connection conn = null;
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvnLiquibase", "mvnLiquibase", "1234");
        
        return conn;
    }
    
    public void loadDataSet(IDatabaseConnection connection, DatabaseOperation operation, IDataSet dataSet) throws Exception {
        operation.execute(connection, dataSet);
    }
    
    public void loadDataSet(DatabaseOperation operation, IDataSet dataSet) throws Exception {
        IDatabaseConnection connection = getDatabaseConnection();
        
        try {
            loadDataSet(connection, operation, dataSet);
        }
        finally {
            connection.close();
        }
    }

    public void loadDataSet(DatabaseOperation operation, String fileName) throws Exception {
        IDataSet dataSet = getDataSet(fileName);
        loadDataSet(operation, dataSet);
    }
    
    public void loadDataSet(IDataSet dataSet) throws Exception {
        loadDataSet(DatabaseOperation.CLEAN_INSERT, dataSet);
    }
    
    public void loadDataSet(String fileName) throws Exception {
        IDataSet dataSet = getDataSet(fileName);
        loadDataSet(DatabaseOperation.CLEAN_INSERT, dataSet);
    }

    protected IDataSet getDataSet(String fileName) throws Exception {
        IDataSet dataSet = null;

        if (fileName != null && !"".equals(fileName))
            dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(fileName));
        
        return dataSet;
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        emf = Persistence.createEntityManagerFactory("mvnLiquibasePU");
        em = emf.createEntityManager();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        if (em != null) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
