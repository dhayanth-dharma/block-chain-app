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
public class Sender_DAO {
    private Dao<Sender_M, Integer> senderDAO;
    private ConnectionSource connectionSource;
    public Sender_DAO() {
        try {
              DAO_Connection_String daoConStr=new DAO_Connection_String();
            connectionSource =
                    new JdbcConnectionSource(daoConStr.getServer_location(), daoConStr.getUser_name(), daoConStr.getPassword());
            senderDAO=DaoManager.createDao(connectionSource, Sender_M.class);
        } catch (SQLException ex) {
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int create(Sender_M obj)
    {
        try {
          int result=  senderDAO.create(obj);
          return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public Sender_M getOne(int id)
    {
        try {
            Sender_M obj=senderDAO.queryForId(id);    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public Sender_M getOneByTranID(int id)
    {
        try {
         
            Sender_M   returnValue = null;
             PreparedQuery<Sender_M> query
                = senderDAO.queryBuilder().orderBy("sender_id", true)
                .limit(1L) //setting up one
                .where()
                .eq("public_transaction_id", id)
                .prepare();
              List<Sender_M> obj2 = senderDAO.query(query);
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
     public List<Sender_M> getAllByTranID(int id)
    {
        try {
         
            List<Sender_M>   returnValue = null;
             PreparedQuery<Sender_M> query
                = senderDAO.queryBuilder().orderBy("sender_id", true)
                .where()
                .eq("public_transaction_id", id)
                .prepare();
              List<Sender_M> obj2 = senderDAO.query(query);
                if (obj2.size() > 0) {
                  returnValue= obj2;
                }
            return returnValue;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Sender_M> getAll()
    {
        try {
            List<Sender_M> obj=senderDAO.queryForAll();    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
