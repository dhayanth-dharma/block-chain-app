/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestMaker;

import JsonParser.JsonParserCustom;
import Model.Sender_M;
import Model.Transaction_M;
import dataAccess.Transaction_DAO;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import requestMaker.GemMemberList_RM;
import requestMaker.NewTransaction_RM;

/**
 *
 * @author user
 */
public class TestRequest {
   public static void main(String[] args) 
   {
       try {
           
       
       getIP();
//       getMemberList();
//       newTransaction();
//        Timestamp ts=new Timestamp(System.currentTimeMillis());  
//        Date date=new Date(ts.getTime());  
//        System.out.println(date);  
       } catch (Exception e) {
       System.out.println(e.getMessage());
       }
   }
   
   public static void getIP() throws UnknownHostException
   {
        
            InetAddress IP=InetAddress.getLocalHost();
//            String ip=IP.getHostAddress();
             String ip=IP.toString().substring(IP.toString().lastIndexOf('/') + 1,IP.toString().length() );
             System.out.println(ip);
   }
   
   public static void newTransaction()
   {
        Transaction_M tran=new Transaction_M();
        Transaction_DAO tranDao=new Transaction_DAO();
        Transaction_M lastTran=tranDao.getLastTransaction();
        Timestamp ts=new Timestamp(System.currentTimeMillis());  
        Date date=ts;
        Sender_M sender=new Sender_M( );
        sender.setAmount(12);
        sender.setDate_time(ts);
        sender.setSender_account_id(1);
        sender.setReceiver_account_id(2);
        sender.setPublic_transaction_id(lastTran.getPublic_transaction_id()+1);
 //     sender.setSender_id(3); // autogen
        List<Sender_M> senderList=new ArrayList<Sender_M>();
        senderList.add(sender);
        tran.setAmount(20);         
        tran.setDate_time(ts);
        tran.setPublic_transaction_id(lastTran.getPublic_transaction_id()+1);
        tran.setReceiver_account_id(2);
        tran.setSenderList(senderList);

        JsonParserCustom jsonParser=new JsonParserCustom();

      
        NewTransaction_RM tranRM=new NewTransaction_RM(tran);
        tranRM.initate(tran);
        
   }
   public static void newTransactionFromFile()
   {   Transaction_M tran;
   
    String url="E:/Master UJM  Continous K/Network/Project/Repo/Application/cheese-coin-system/Resources/transaction.json";
//    JsonParserCustom<Transaction_M> jsonParser=new JsonParserCustom(Transaction_M.class);
    JsonParserCustom jsonParser=new JsonParserCustom();
    
    Transaction_M trans=jsonParser.readJsonFileToTransaction(url);
    NewTransaction_RM tranRM=new NewTransaction_RM(trans);
    tranRM.initate(trans);
    String text = jsonParser.objectToJsonString(trans);
    System.out.println(text);
   }
   public void getCheeseList()
   {}
   public static void getMemberList()
   {
       GemMemberList_RM register=new GemMemberList_RM();
       register.initate();
       
   }
   public void newCheese()
   {}
   
   
}
