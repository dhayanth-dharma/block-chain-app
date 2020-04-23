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
public class Member_DAO {
     private Dao<Member_M, Integer> MemberDAO;
    private ConnectionSource connectionSource;
    public Member_DAO() {
        try {
//            connectionSource =
//                    new JdbcConnectionSource("jdbc:mysql://localhost:3306/ormlite", "root", "" );
              DAO_Connection_String daoConStr=new DAO_Connection_String();
            connectionSource =
                    new JdbcConnectionSource(daoConStr.getServer_location(), daoConStr.getUser_name(), daoConStr.getPassword());
            
            MemberDAO=DaoManager.createDao(connectionSource, Member_M.class);
        } catch (SQLException ex) {
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int create(Member_M obj)
    {
        try {
          int result=  MemberDAO.create(obj);
          return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public Member_M getOne(int id)
    {
        try {
            Member_M obj=MemberDAO.queryForId(id);    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Member_M> getAll()
    {
        try {
            List<Member_M> obj=MemberDAO.queryForAll();    
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Member_M> getAll_Ten()
    {
        try {
             List<Member_M> returnValue = null;
             QueryBuilder<Member_M, Integer> builder = MemberDAO.queryBuilder();
             builder.limit(10L);
             builder.orderBy("member_id", true);  // true for ascending, false for descending
             builder.where().eq("status", "active");
             returnValue = MemberDAO.query(builder.prepare()); 
            return returnValue;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Member_List_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public Member_M createAndGet(Member_M obj)
    {
        try {
          int result=  MemberDAO.create(obj);
          if(result>0)
          {
             long id=MemberDAO.queryRawValue("select MAX(member_id) from _member");
             Member_M obj2=getOne((int) id);
             
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
    
    public Member_M getByIpAndPort(String ip, int port)
    {
        try {
         
            Member_M   returnValue = null;
             PreparedQuery<Member_M> query
                = MemberDAO.queryBuilder().orderBy("member_id", true)
                .limit(1L) //setting up one
                .where()
                .eq("member_ip", ip)
                .and()
                .eq("member_port", port)
                .prepare();
              List<Member_M> obj2 = MemberDAO.query(query);
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
    
    
    public int updateStatus(Member_M mem, String status)
     {
         try {
                int result=0;
             Member_M dbMem = null;
             PreparedQuery<Member_M> query
                     = MemberDAO.queryBuilder().orderBy("member_id", true)
                             .limit(1L) //setting up one
                             .where()
                             .eq("member_ip", mem.getMember_ip())
                             .prepare();
             List<Member_M> obj2 = MemberDAO.query(query);
             if (obj2.size() > 0) {
                 dbMem= obj2.get(0);
                 dbMem.setStatus(status);
             result=   MemberDAO.update(dbMem);
             }
             return result;
         } catch (SQLException ex) {
             System.out.println("Error from TransactionDAO: "+ex.getMessage());
             Logger.getLogger(Member_DAO.class.getName()).log(Level.SEVERE, null, ex);
             return 0;
         }
     }
}
