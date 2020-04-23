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
public class Transaction_DAO {
     private Dao<Transaction_M, Integer> transactionDAO;
    private ConnectionSource connectionSource;
    public Transaction_DAO() {
        try {
              DAO_Connection_String daoConStr=new DAO_Connection_String();
            connectionSource =
                    new JdbcConnectionSource(daoConStr.getServer_location(), daoConStr.getUser_name(), daoConStr.getPassword());
            transactionDAO=DaoManager.createDao(connectionSource, Transaction_M.class);
        } catch (SQLException ex) {
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Transaction_M create(Transaction_M obj)
    {
        try {
          int result=  transactionDAO.create(obj);
          if(result>0)
          {
             long id=transactionDAO.queryRawValue("select MAX(transaction_id) from _transaction");
             Transaction_M obj2=getOne((int) id);
             return obj2;
          }
          else
          {
              return null;
          }
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Transaction_M createWithSender(Transaction_M obj)
    {
        try {
          int result=  transactionDAO.create(obj);
          if(result>0)
          {
            long id=transactionDAO.queryRawValue("select MAX(transaction_id) from _transaction");
            Transaction_M obj2=getOne((int) id);
            Sender_DAO senderDao=new Sender_DAO();
            for(Sender_M sender: obj2.getSenderList())
            {
                sender.setPublic_transaction_id(obj2.getPublic_transaction_id());
                senderDao.create(sender);
            }
             return obj2;
          }
          else
          {
              return null;
          }
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     public int createWithSenderPreSetPubTranID(Transaction_M obj)
    {
        try {
          int result=  transactionDAO.create(obj);
          if(result>0)
          {
           
            Sender_DAO senderDao=new Sender_DAO();
            for(Sender_M sender: obj.getSenderList())
            {
                senderDao.create(sender);
            }
             return result;
          }
          else
          {
              return 0;
          }
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
     
    
     public int updateStatus(Transaction_M tran, String status)
     {
         try {
                int result=0;
             Transaction_M   dbTran = null;
             PreparedQuery<Transaction_M> query
                     = transactionDAO.queryBuilder().orderBy("public_transaction_id", true)
                             .limit(1L) //setting up one
                             .where()
                             .eq("public_transaction_id", tran.getPublic_transaction_id())
                             .prepare();
             List<Transaction_M> obj2 = transactionDAO.query(query);
             if (obj2.size() > 0) {
                 dbTran= obj2.get(0);
                 dbTran.setStatus(status);
             result=   transactionDAO.update(dbTran);
             }
             return result;
         } catch (SQLException ex) {
             System.out.println("Error from TransactionDAO: "+ex.getMessage());
             Logger.getLogger(Transaction_DAO.class.getName()).log(Level.SEVERE, null, ex);
             return 0;
         }
     }
     
     //will be used to cal public transactionID;
    public Transaction_M getLastTransaction()
    {
        try {
        
            long id=transactionDAO.queryRawValue("select MAX(transaction_id) from _transaction");
            Transaction_M obj2=getOne((int) id);
            return obj2;
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
    public Transaction_M getOne(int id)
    {
        try {
            Transaction_M obj=transactionDAO.queryForId(id);    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Transaction_M> getAll()
    {
        try {
            List<Transaction_M> obj=transactionDAO.queryForAll();    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Transaction_M> getAllwithSender()
    {
        try {
            List<Transaction_M> obj=transactionDAO.queryForAll(); 
            int index=0;
            for(Transaction_M tran: obj)
            {
                Sender_DAO sendDao=new Sender_DAO();
                List<Sender_M> senderList=sendDao.getAllByTranID(tran.getPublic_transaction_id());
                obj.get(index).setSenderList(senderList);
                index++;
            }
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public Transaction_M getOneByPublicTranID(int id)
    {
        try {
         
            Transaction_M   returnValue = null;
             PreparedQuery<Transaction_M> query
                = transactionDAO.queryBuilder().orderBy("public_transaction_id", true)
                .limit(1L) //setting up one
                .where()
                .eq("public_transaction_id", id)
                .prepare();
              List<Transaction_M> obj2 = transactionDAO.query(query);
                if (obj2.size() > 0) {
                  returnValue= obj2.get(0);
                  Sender_DAO senderDao=new Sender_DAO();
                  List<Sender_M> senderList=  senderDao.getAllByTranID(returnValue.getTransaction_id());
                  returnValue.setSenderList(senderList);
                }
            return returnValue;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int checkExistByPublicID(int publicTransactionID)
    {
        try {
            Transaction_M returnValue = null;
            int result;
             PreparedQuery<Transaction_M> query
                = transactionDAO.queryBuilder().orderBy("public_transaction_id", true)
                .limit(1L) //setting up one
                .where()
                .eq("public_transaction_id", publicTransactionID)
                .prepare();
              List<Transaction_M> obj2 = transactionDAO.query(query);
                if (obj2.size() > 0) {
                     result=1;
                }
                else
                     result=0;
            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
}
