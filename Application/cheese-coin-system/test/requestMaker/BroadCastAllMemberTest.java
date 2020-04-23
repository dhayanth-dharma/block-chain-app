/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestMaker;

import Model.Member_M;
import Model.RequestModel;
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
public class BroadCastAllMemberTest {
    
    public BroadCastAllMemberTest() {
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
     * Test of getMemberList method, of class BroadCastAllMember.
     */
    @Test
    public void testGetMemberList() {
        System.out.println("getMemberList");
        RequestModel req=new RequestModel();
         List<Member_M> memList=null;       
        BroadCastAllMember instance = new BroadCastAllMember(req);
        instance.getMemberList();
        assertNotNull(instance);
        fail("The test case is a prototype.");
    }

    /**
     * Test of Broadcast method, of class BroadCastAllMember.
     */
    @Test
    public void testBroadcast() {
        System.out.println("Broadcast");
        String ip = "";
        int port = 0;
        BroadCastAllMember instance = null;
        instance.Broadcast(ip, port);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
