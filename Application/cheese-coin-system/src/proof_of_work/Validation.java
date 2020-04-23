/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proof_of_work;

import JsonParser.JsonParserCustom;
import MessageSender.MessageHandler;
import MessageSender.MessageQueueSingleton;
import Model.Cheese_M;
import Model.Member_M;
import Model.RequestModel;
import Model.Transaction_M;
import dataAccess.Cheese_DAO;
import dataAccess.Member_DAO;
import dataAccess.Transaction_DAO;
import enums.ReqCommand;
import enums.RequestType;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Validation  {

    private Cheese_M cheese;
    private Transaction_M transaction;
    public Validation(Cheese_M ches) {
        this.cheese=ches;
    }

    public void run() {
        
        //doing the validation*******************
        
        //getting transaction of this cheese
        Transaction_DAO tranDao=new  Transaction_DAO();
        Transaction_M tran=tranDao.getOneByPublicTranID(cheese.getPublic_transaction_id());
        String hash=computeHash(tran);
        if(hash.equals(cheese.getHash()))
        {
            System.out.println("Validation Success");
            updateTransactionTable(tran, tranDao); //update transaction table to confirm that cheese has been found
            addCheese();//add cheese
        }
        else
        {
            sendWrongValidation();
        }
                 
    }
    public void sendWrongValidationReq()
    {
        try {
            Member_DAO memDao=new Member_DAO();
            Member_M mem=memDao.getOne(cheese.getMember_id());
            RequestModel<String> req=
                    new RequestModel(RequestType.GET.toString(),ReqCommand.REQ_VALIDATION.toString(),cheese);
            Socket socket=new Socket(mem.getMember_ip(),mem.getMember_port());
            MessageQueueSingleton messQueue=MessageQueueSingleton.getInstance();
            HashMap<String,Object> mesg=new HashMap<String,Object>();
            mesg.put("socket",socket);
            mesg.put("request",req);
            messQueue.putQueue(mesg);
        } catch (IOException ex) {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    public void sendWrongValidation()
    {
        try {
            Member_DAO memDao=new Member_DAO();
            Member_M mem=memDao.getOne(cheese.getMember_id());
            JsonParser.JsonParserCustom jsonParser=new JsonParserCustom();
            String cheeseString=jsonParser.objectToJsonString(cheese);
            RequestModel<String> req=
                    new RequestModel(RequestType.GET.toString(),ReqCommand.REQ_VALIDATION.toString(), cheeseString);
//          MessageHandler mesgHan=new MessageHandler();
            Socket socket=new Socket(mem.getMember_ip(),mem.getMember_port());
            HashMap<String, Object> hashMap=new HashMap<String,Object>();
            hashMap.put("socket",socket);
            hashMap.put("request",req);
            MessageQueueSingleton mesgSing=MessageQueueSingleton.getInstance();
            mesgSing.putQueue(hashMap);
            
        } catch (IOException ex) {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    
    public int updateTransactionTable(Transaction_M tran, Transaction_DAO tranDAO)
    {
       int result= tranDAO.updateStatus(tran,"done");
        return result;
    }
    public void addCheese()
    {
        Cheese_DAO cheeseDao=new Cheese_DAO();
        cheeseDao.create(cheese);
    }
    
    public String computeHash(Transaction_M trans) {
		
		String dataToHash = "" 
                        + trans.getAmount() 
                        + trans.getReceiver_account_id()
                        + trans.getPublic_transaction_id()
                        + cheese.getPrevious_hash()
                        + cheese.getNonce();
		String encoded=bytesToSHA1(dataToHash.getBytes());
                return encoded;
    }
    
    public  String bytesToSHA1(byte[] bytes) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            byte[] digestedBytes = messageDigest.digest(bytes);

            // convert the digestedBytes to hexadecimal format to reduce the size of the ouput
            String result = "";
            for (int i = 0; i < digestedBytes.length; i++) {
                result += Integer.toString((digestedBytes[i] & 0xff) + 0x100, 16).substring(1);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }        
    }
    
    
}
