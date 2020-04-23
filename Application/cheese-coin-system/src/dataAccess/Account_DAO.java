/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import Model.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Account_DAO {
    
    private Dao<Account_M, Integer> accountDAO;
    private ConnectionSource connectionSource;
    public Account_DAO() {
        try {
              DAO_Connection_String daoConStr=new DAO_Connection_String();
            connectionSource =
                    new JdbcConnectionSource(daoConStr.getServer_location(), daoConStr.getUser_name(), daoConStr.getPassword());
            
            accountDAO=DaoManager.createDao(connectionSource, Account_M.class);
        } catch (SQLException ex) {
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int create(Account_M obj)
    {
        try {
          int result=  accountDAO.create(obj);
          return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public Account_M getOne(int id)
    {
        try {
            Account_M obj=accountDAO.queryForId(id);    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public Account_M getOneByTranID(int id)
    {
        try {
         
            Account_M   returnValue = null;
             PreparedQuery<Account_M> query
                = accountDAO.queryBuilder().orderBy("account_id", true)
                .limit(1L) //setting up one
                .where()
                .eq("user_id", id)
                .prepare();
              List<Account_M> obj2 = accountDAO.query(query);
                if (obj2.size() > 0) {
                  returnValue= obj2.get(0);
                }
            return returnValue;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Account_M> getAll()
    {
        try {
            List<Account_M> obj=accountDAO.queryForAll();    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public Account_M getLastAcount()
    {
        try {
        
            long id=accountDAO.queryRawValue("select MAX(account_id) from _account");
            Account_M obj2=getOne((int) id);
            return obj2;
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
