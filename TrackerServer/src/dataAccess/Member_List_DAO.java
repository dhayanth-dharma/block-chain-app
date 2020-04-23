/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import Model.Member_List_M;
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
public class Member_List_DAO {

    private Dao<Member_List_M, Integer> memberListDAO;
    private ConnectionSource connectionSource;
    public Member_List_DAO() {
        try {
              DAO_Connection_String daoConStr=new DAO_Connection_String();
            connectionSource =
                    new JdbcConnectionSource(daoConStr.getServer_location(), daoConStr.getUser_name(), daoConStr.getPassword());
            
            memberListDAO=DaoManager.createDao(connectionSource, Member_List_M.class);
        } catch (SQLException ex) {
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int create(Member_List_M obj)
    {
        try {
          int result=  memberListDAO.create(obj);
          return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public Member_List_M getOne(int id)
    {
        try {
            Member_List_M obj=memberListDAO.queryForId(id);    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Member_List_M> getAll()
    {
        try {
            List<Member_List_M> obj=memberListDAO.queryForAll();    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
    
}
