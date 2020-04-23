/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;
import Model.CheeseModel;
import Model.Transaction_B;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class DataAccessCommon implements DataAccess{
    private Connection cn;
    public DataAccessCommon()
    {
       try{
           cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cheese_coin_system","root",""); 
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public int add(Object ob) {
         CheeseModel cheese=(CheeseModel)ob;
         String insert="insert into _cheese(hash,previous_has) values(?,?);";
        int result=0;
        try{
            PreparedStatement ps=cn.prepareStatement(insert);
            ps.setString(1, cheese.getHash());
            ps.setString(2,cheese.getPreviousHash());
//            ps.setString(3, cheese.getTimestamp().toString());
            result=ps.executeUpdate();
            
            //insert now transaction corresponding to cheese
            int c_id=getLastIDCheese();
            
            
            ps.close();
            if(result>0)
                return c_id;
            else
                return 0;
        }catch(SQLException e){
            e.printStackTrace();
            return result;
        }
    }
    
    public int addTransaction(Transaction_B obj)
    {
        Transaction_B tran=(Transaction_B)obj;
         String insert="insert into _transaction( user_id, t_date, public_key, amount, c_id) values(?,?,?,?,?);";
         
        int result=0;
        try{
            PreparedStatement ps=cn.prepareStatement(insert);
            ps.setString(1, tran.getUser_id());
            ps.setString(2,tran.getT_date().toString());
            ps.setString(3, tran.getPublic_key().toString());
            ps.setString(4, String.valueOf( tran.getAmount()));
            ps.setString(5, String.valueOf( tran.c_id));
            result=ps.executeUpdate();
            
            ps.close();
            return result;
        }catch(SQLException e){
            e.printStackTrace();
            return result;
        }
    
    }
    

    @Override
    public int delete(int id) {
        String delete="delete * from Patient where patientID=?";
       int result=0;
       try{
           PreparedStatement ps=cn.prepareStatement(delete);
           ps.setInt(1, id);
           result=ps.executeUpdate();
           ps.close();
           return result;
       }catch(SQLException e){
           e.printStackTrace();
           return result;
       }
    }

    @Override
    public int update(Object ob) {
        return 1;
    }

    public int getLastIDCheese() {
        String select="SELECT * FROM permlog ORDER BY id DESC LIMIT 1";
        
        try{
            PreparedStatement ps=cn.prepareStatement(select);
            int id=0;
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                id=Integer.valueOf(rs.getString("c_id"));
            }
            rs.close();
            ps.close();
            return id;
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public Object get(int id) {
        String select="select * from _cheese where c_id=?";
        CheeseModel p=new CheeseModel();
        try{
            PreparedStatement ps=cn.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                p.c_id=Integer.valueOf(rs.getString("c_id"));
                p.hash=rs.getString("hash");
                p.previousHash=rs.getString("previous_has");
                p.Timestamp=Date.valueOf(rs.getString("timestamp"));
                p.nonce=Integer.valueOf(rs.getString("nonce"));
                p.dificulty=Integer.valueOf(rs.getString("dificulty"));
                
            }
            rs.close();
            ps.close();
            return p;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Object> getAll() {
        String select="select * from Patient";
       ArrayList<Object> cheeseList=new ArrayList<>();
       try{
          PreparedStatement ps=cn.prepareStatement(select); 
          ResultSet rs=ps.executeQuery();
          while(rs.next()){
              CheeseModel p=new CheeseModel();
               p.c_id=Integer.valueOf(rs.getString("c_id"));
                p.hash=rs.getString("hash");
                p.previousHash=rs.getString("previous_has");
                p.Timestamp=Date.valueOf(rs.getString("timestamp"));
                cheeseList.add(p);
          }
          rs.close();
          ps.close();
          return cheeseList;
       }catch(SQLException e){
           e.printStackTrace();
           return null;
       }
    }
    
}
