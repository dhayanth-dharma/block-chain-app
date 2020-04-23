/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Account_M;
import Model.Transaction_M;
import dataAccess.Account_DAO;
import dataAccess.Transaction_DAO;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Test {
    public static void main(String[] args)
    {
        addAccountTest();
    }
    public static void addAccountTest()
    {
    
        Account_DAO acntDao=new Account_DAO();
        Account_M lastID=acntDao.getLastAcount();
           

                Account_M account=new Account_M();
                account.setUser_id(2);
                account.setPublic_key("asdkjaskd");
                account.setPrivate_key("aklsjdlakdj");
                account.setAccount_number(544);
                account.setBalance(0);
                acntDao.create(account);
                
    }
    
}
