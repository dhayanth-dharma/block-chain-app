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
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class User_DAO {
       private Dao<User_M, Integer> TransactionDAO;
    private ConnectionSource connectionSource;
    public User_DAO() {
        try {
              DAO_Connection_String daoConStr=new DAO_Connection_String();
            connectionSource =
                    new JdbcConnectionSource(daoConStr.getServer_location(), daoConStr.getUser_name(), daoConStr.getPassword());
            
            TransactionDAO=DaoManager.createDao(connectionSource, User_M.class);
        } catch (SQLException ex) {
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int create(User_M obj)
    {
        try {
          int result=  TransactionDAO.create(obj);
          return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public User_M getOne(int id)
    {
        try {
            User_M obj=TransactionDAO.queryForId(id);    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<User_M> getAll()
    {
        try {
            List<User_M> obj=TransactionDAO.queryForAll();    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
