/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestMaker;

import Model.RequestModel;
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
public class GemMemberList_RMTest {
    
    public GemMemberList_RMTest() {
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

    /**
     * Test of initate method, of class GemMemberList_RM.
     */
    @Test
    public void testInitate() {
        System.out.println("initate");
        GemMemberList_RM instance = new GemMemberList_RM();
        instance.initate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Broadcast method, of class GemMemberList_RM.
     */
    @Test
    public void testBroadcast() {
        System.out.println("Broadcast");
        String ip = "";
        int port = 0;
        RequestModel req = null;
        GemMemberList_RM instance = new GemMemberList_RM();
        instance.Broadcast(ip, port, req);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
