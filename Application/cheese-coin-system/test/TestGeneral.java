/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Model.Sender_M;
import Model.Transaction_M;
import Model.User_M;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dataAccess.Sender_DAO;
import java.io.File;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author user
 */
public class TestGeneral {
    public static void main(String[] args)
    {
        insertTestUser();
      dateTimeFormater();
       getTransaction();
       
    }
    
   public static void  dateTimeFormater()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
//        System.out.println(dateFormat.format(date));
       
        System.out.println(dateFormat.format(date.toString()));
       
        return;
    }
   
   
   
   public static void insertTestUser()
   {
       try{
        // create a connection source to our database
            ConnectionSource connectionSource =
                    new JdbcConnectionSource("jdbc:mysql://localhost:3306/cheese_coin_system_2", "root", "" );
            Dao<User_M, Integer> accountDao =
            DaoManager.createDao(connectionSource, User_M.class);
            
            User_M account = new User_M();
            account.setUser_name("dhaya");
            account.setPassword("123abc");
            account.setUser_type("client");
            int i=accountDao.create(account);
            long id=accountDao.queryRawValue("select MAX(use_id) from _user");
            User_M account2 = accountDao.queryForId((int)id);
            System.out.println("Account: " + account2.getUser_name()+":"+ id);
        
            // close the connection source
            connectionSource.close();
        } catch (IOException | SQLException ex)
        {
            Logger.getLogger(TestGeneral.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println(ex.getMessage());
        }
   }
   
   public static void getTransaction()
   {
   try{
       
   ConnectionSource connectionSource =
                    new JdbcConnectionSource("jdbc:mysql://localhost:3306/cheese_coin_system_2", "root", "" );
            Dao<Transaction_M, Integer> accountDao =
                    DaoManager.createDao(connectionSource, Transaction_M.class);
            
//           // retrieve the account from the database by its id field (name)
            long id=accountDao.queryRawValue("select MAX(transaction_id) from _transaction");
            Transaction_M tran = accountDao.queryForId((int)id);
            System.out.println(tran.getPublic_transaction_id());
            Sender_DAO senDao=new Sender_DAO();
            Sender_M sender= senDao.getOneByTranID((int)id);
            List<Sender_M> list=new ArrayList<Sender_M>();
            list.add(sender);
            System.out.println(sender.getAmount());
            tran.setSenderList(list);
            
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.writeValue(
            new File("E:/Master UJM  Continous K/Network/Project/Repo/Application/cheese-coin-system/Resources/transaction.json"), 
            tran);

        // close the connection source
            connectionSource.close();
        } catch (IOException | SQLException ex)
        {
            Logger.getLogger(TestGeneral.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println(ex.getMessage());
        }
   }
   
   
}
