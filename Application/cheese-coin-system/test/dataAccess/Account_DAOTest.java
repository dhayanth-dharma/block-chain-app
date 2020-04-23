/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import Model.Account_M;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class Account_DAOTest {
    
    public Account_DAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        System.out.println("create");
        Account_M obj = new Account_M(12, "sjdhfkjsdhf", "sfsdf000",
                1, 2, 20.6);
        Account_DAO instance = new Account_DAO();
        int expResult = 1;
        int result = instance.create(obj);
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOne method, of class Account_DAO.
     */
    @Test
    public void testGetOne() {
        System.out.println("getOne");
        int id = 2;
        Account_DAO instance = new Account_DAO();
        Account_M expResult = null;
        Account_M result = instance.getOne(id);
       assertNotNull( result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOneByTranID method, of class Account_DAO.
     */
    @Test
    public void testGetOneByTranID() {
        System.out.println("getOneByTranID");
        int id = 0;
        Account_DAO instance = new Account_DAO();
        Account_M expResult = null;
        Account_M result = instance.getOneByTranID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class Account_DAO.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        Account_DAO instance = new Account_DAO();
        List<Account_M> expResult = null;
        List<Account_M> result = instance.getAll();
        assertNotNull( result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
