/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackerserver;

import Model.Member_M;
import dataAccess.Member_DAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Test {
    public static void main(String[] args)
    {
        testGetMember10();
    }
    
    public static void testGetMember10()
    {
      Member_DAO memDao=new Member_DAO();
      List<Member_M> memberList=new ArrayList<Member_M>();
     memberList=memDao.getAll_Ten();
        memberList=memDao.getAll();
    }
}
