/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestMaker;

import AsymmetricEn.Message;
import org.apache.commons.codec.binary.Base64;
import JsonParser.JsonParserCustom;
import MessageSender.MessageQueueSingleton;
import Model.*;
import dataAccess.Account_DAO;
import dataAccess.Member_DAO;
import dataAccess.Member_List_DAO;
import dataAccess.Sender_DAO;
import dataAccess.Transaction_DAO;
import enums.ReqCommand;
import enums.RequestType;
import enums.StatusCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
//import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proof_of_work.SingletonProofOfWorkInstance;

// add new transaction details to the database and broadcast to all member
public class NewTransaction_RM {

    Transaction_M transaction;
    
    
    public NewTransaction_RM(Transaction_M tran) {
        this.transaction=tran;
    }
    
    
    public void initate(Transaction_M tran) 
    {
        try {
            addTransaction(tran);
            JsonParserCustom jsonParser=new JsonParserCustom();
            String tranString=jsonParser.objectToJsonString(tran);

            //getting private keys of sender
            Account_DAO anctDao=new Account_DAO();
            int count=0;
            HashMap<String,String> certificateMap=new  HashMap<String,String>();
            for(Sender_M sender: tran.getSenderList())
            {
                Account_M acount =anctDao.getOne(sender.getSender_account_id());
                String privatek=acount.getPrivate_key();
                KeyFactory kf = KeyFactory.getInstance("RSA");
                PKCS8EncodedKeySpec specPriv = new PKCS8EncodedKeySpec(Base64.decodeBase64(privatek));
                PrivateKey privKey = kf.generatePrivate(specPriv);
                Message mesg=new Message();
                byte[] certificate= mesg.signCustom(tranString, privKey);
                String certifitecaString = org.apache.commons.codec.binary.Base64.encodeBase64String(certificate);
                certificateMap.put(String.valueOf(sender.getSender_account_id()), certifitecaString);
                count++;
            }
            
            HashMap<String,Object> streamData=new HashMap<String,Object>();
            streamData.put("certificate", certificateMap);
            streamData.put("transaction", tranString);
            String encriptedObject=jsonParser.objectToJsonString(streamData);
                        
            RequestModel<Member_M> req= new RequestModel
            (RequestType.POST.toString(),ReqCommand.REQ_NEW_TRANS.toString(),encriptedObject);
              //Broadcast newcheese to all member
            BroadCastAllMember broadcast=new BroadCastAllMember(req);
            

            //********UNCOMMENT FOR AUTOMATIC ROOF OF WORK
//Since im attaching minning with GUI, i comment this auto mine part
            //put the object for mining to the queue
//            SingletonProofOfWorkInstance pow=SingletonProofOfWorkInstance.getInstance();
//            HashMap<String, Object> obj=new HashMap<String, Object>();
//            obj.put("transaction", tran);
//            pow.putQueue(obj);

        } catch (Exception e) {
            System.out.println("Class Name: "+this.getClass().getName()+">>Error:" +e.getMessage());
            
        }
    }
 
    
    public void addTransaction(Transaction_M tran)
    {
        try
        {
            
            Transaction_DAO tranDao=new Transaction_DAO();
            //check whether already exist
//            int result =tranDao.checkExistByPublicID(this.transaction.getPublic_transaction_id());
            
            this.transaction= tranDao.create(tran);
            Sender_DAO senderDao=new Sender_DAO();
            for(Sender_M sender: tran.getSenderList())
            {
                sender.setPublic_transaction_id(tran.getPublic_transaction_id());
                senderDao.create(sender);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void Broadcast(String ip, int port)
    {
         try {
             int requestingCount=0;
             Socket socket=new Socket(ip,port);
             JsonParserCustom jsonParser = new JsonParserCustom();
              RequestModel<String> req= new RequestModel
                (RequestType.POST.toString(),ReqCommand.REQ_NEW_TRANS.toString(),transaction);
             OutputStream ostream = socket.getOutputStream();
             String reqJsonObj = jsonParser.objectToJsonString(req);
             PrintWriter pwrite = new PrintWriter(ostream, true);
             pwrite.println(reqJsonObj); // sending to server
	     pwrite.flush(); 
             socket.setSoTimeout(3000);
             //******* GETTING DATA FROM SERVER
             InputStream is = socket.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is));
             String receivedData="";
                   while((receivedData=br.readLine()) !=null) 
                   {  
                    try 
                    {
                            System.out.println("Server response Received 1 "+receivedData);
                            ResponseModel res=jsonParser.stringToResObj(receivedData);
                            System.out.print(res);
                            
//      ******************** UNCOMMENT THIS TO WRITE RESPONSE ACK IF NEEDED**********************
                            if(StatusCode.OK.getValue()==res.status_code)
                            {
                                socket.close();
                                //closing the socket
                            }
                    }
                    catch (SocketTimeoutException e) {
                        // timeout exception.
                        System.out.println("Timeout reached!!! " + e);
                        System.out.println("Resending Data!!! " + requestingCount);
//                        s.close();
                        if(requestingCount==0)
                        {
                            requestingCount=1;
                            pwrite.println(reqJsonObj); // sending to server
                            pwrite.flush();
//                            socket.setSoTimeout(3000);//may be not neeed
                        }
                        else if(requestingCount==1)
                        {
                            socket.close();
                        }
                    } //end of catch
                } //end of while
            
         } 
         catch (Exception  ex) {
               if (ex instanceof IOException) {
                System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.getMessage());
                }
         }
         
     }
    
}
