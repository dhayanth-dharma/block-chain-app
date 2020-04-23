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
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
//***/ USE THIS CLASS FOR MANUAL DB CODE FIRST MIGRATION APPROUCH
public class Dao_Setup {
     public static void main(String[] args) 
    {
        try {

         DAO_Connection_String daoConStr=new DAO_Connection_String();
         ConnectionSource connectionSource =
                    new JdbcConnectionSource(daoConStr.getServer_location(), daoConStr.getUser_name(), daoConStr.getPassword());
            
            // instantiate the dao
//            Dao<Account_M, Integer> accountDao =
//                    DaoManager.createDao(connectionSource, Account_M.class);
            
            // if you need to create the 'accounts' table make this call
//            TableUtils.createTable(connectionSource, Cheese_M.class);
             TableUtils.createTable(connectionSource, Account_M.class);
//            TableUtils.createTable(connectionSource, Member_List_M.class);
//            TableUtils.createTable(connectionSource, Member_M.class);
//            TableUtils.createTable(connectionSource, Proof_Of_Work_M.class);
//            TableUtils.createTable(connectionSource, Sender_M.class);
//            TableUtils.createTable(connectionSource, Transaction_M.class);
//            TableUtils.createTable(connectionSource, User_M.class);

            ////            TableUtils.createTable(connectionSource, Sender_Block_M.class);
            
           // close the connection source
            connectionSource.close();
        } catch (IOException | SQLException ex)
        {
            Logger.getLogger(Dao_Setup.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println(ex.getMessage());
        }
    }
}
