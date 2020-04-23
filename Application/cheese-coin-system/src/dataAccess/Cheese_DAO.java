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
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Cheese_DAO {
    private Dao<Cheese_M, Integer> cheeseDAO;
    private ConnectionSource connectionSource;
    public Cheese_DAO() {
        try {
            
            DAO_Connection_String daoConStr=new DAO_Connection_String();
            connectionSource =
                    new JdbcConnectionSource(daoConStr.getServer_location(), daoConStr.getUser_name(), daoConStr.getPassword());
            
            cheeseDAO=DaoManager.createDao(connectionSource, Cheese_M.class);
        } catch (SQLException ex) {
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int create(Cheese_M obj)
    {
        try {
          int result=  cheeseDAO.create(obj);
          return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
      public Cheese_M getOneByPublicTranID(int public_transaction_id)
    {
        try {
            Cheese_M   returnValue = null;
             PreparedQuery<Cheese_M> query
                = cheeseDAO.queryBuilder().orderBy("public_transaction_id", true)
                .limit(1L) //setting up one
                .where()
                .eq("public_transaction_id", public_transaction_id)
                .prepare();
              List<Cheese_M> obj2 = cheeseDAO.query(query);
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
      public Cheese_M getGenesis()
    {
        try {
            Cheese_M   returnValue = null;
             PreparedQuery<Cheese_M> query
                = cheeseDAO.queryBuilder().orderBy("public_transaction_id", true)
                .limit(1L) //setting up one
                .where()
                .eq("public_transaction_id", 0)
                 .prepare();
              List<Cheese_M> obj2 = cheeseDAO.query(query);
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
    public Cheese_M getOne(int id)
    {
        try {
            Cheese_M obj=cheeseDAO.queryForId(id);    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int deleteErroCheese(Cheese_M cheese)
    {
        try {
            Cheese_M errorCheese=getOneByPublicTranID(cheese.getPublic_transaction_id());
            int result =cheeseDAO.delete(errorCheese);
            return result;
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            Logger.getLogger(Cheese_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public Cheese_M getLaste()
    {
        try {
            long id=cheeseDAO.queryRawValue("select MAX(cheese_id) from _cheese");
            Cheese_M obj=getOne((int) id);
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Cheese_M> getAll()
    {
        try {
            List<Cheese_M> obj=cheeseDAO.queryForAll();    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
