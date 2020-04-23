/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import AsymmetricEn.VerifyMessage;
import JsonParser.JsonParserCustom;
import Model.Account_M;
import Model.Cheese_M;
import Model.RequestModel;
import Model.ResponseModel;
import Model.Sender_M;
import Model.Transaction_M;
import dataAccess.Account_DAO;
import dataAccess.Cheese_DAO;
import dataAccess.Sender_DAO;
import dataAccess.Transaction_DAO;
import enums.ReqCommand;
import enums.RequestType;
import enums.StatusCode;
import java.io.BufferedReader;
import java.io.DataInputStream;
//import static controller.RequestController.getCheeseChain;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import proof_of_work.SingletonProofOfWorkInstance;
import proof_of_work.Validation;

/**
 *
 * @author user
 */
public class RequestHandler   {
 
    
    private Socket clientSocket;
    public RequestHandler(Socket client)
    {
        System.out.println("Server Request handler started");
        this.clientSocket=client;
    }
    
    public void run() {
        try {
            String requestMessage="";
            InetAddress addr = clientSocket.getInetAddress();
            String clientAddressIP = clientSocket.getInetAddress().toString().substring(1);
            int clientPort = clientSocket.getPort();
            BufferedReader  bufferReaer=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            requestMessage=bufferReaer.readLine();
            JsonParserCustom jsonParser=new JsonParserCustom();
            RequestModel req = jsonParser.stringToReqObj(requestMessage);
            
            //Response for Cheese stack request
              if(req.reqest_type.equals(RequestType.GET.toString()) &&req.command.equals(ReqCommand.REQ_CHEESE_CHAIN.toString()))
              {
                  req_cheese_chain(clientSocket, clientAddressIP, clientPort);
              }
              if(req.reqest_type.equals(RequestType.GET.toString()) &&req.command.equals(ReqCommand.REQ_TRAN_CHAIN.toString()))
              {
                  req_tran_chain(clientSocket, clientAddressIP, clientPort);
              }
              else if (req.reqest_type.equals(RequestType.POST.toString()) &&req.command.equals(ReqCommand.REQ_NEW_TRANS.toString()))
              {
                  req_new_trans(clientSocket, clientAddressIP, clientPort, req);
              }
              else if (req.reqest_type.equals(RequestType.POST.toString()) &&req.command.equals(ReqCommand.REQ_VALIDATION.toString()))
              {
                  req_validation(clientSocket, clientAddressIP, clientPort, req);
              }
              else if (req.reqest_type.equals(RequestType.POST.toString()) &&req.command.equals(ReqCommand.REQ_NEW_CHEESE.toString()))
              {
                  req_new_cheese(clientSocket, clientAddressIP, clientPort, req);
              }
              else if (req.reqest_type.equals(RequestType.GET.toString()) &&req.command.equals(ReqCommand.REQ_TEST.toString()))
              {
                  req_test(clientSocket, clientAddressIP, clientPort);
              }
              
          bufferReaer.close();
          
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public RequestHandler()
    {         
    }
    public void req_cheese_chain(Socket clientSocket, String clientAddressIP,int clientPort)
    {
        try {

            String requestMessage="";
            ResponseModel response;
            JsonParserCustom jsonParser=new JsonParserCustom();
            List<Cheese_M> cheeseStack = getCheeseChainD();
            
            String cheeseStackString=jsonParser.objectToJsonString(cheeseStack);
            response=new ResponseModel(StatusCode.OK.getValue(), cheeseStackString);
            String responseString=jsonParser.objectToJsonString(response);
            System.out.println(responseString);
            
            //returning data to the client
            OutputStream outputStream;
            outputStream = clientSocket.getOutputStream();
            PrintWriter pwrite = new PrintWriter(outputStream, true);
            pwrite.println(responseString);
            pwrite.flush();
//            clientSocket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void req_tran_chain(Socket clientSocket, String clientAddressIP,int clientPort) throws IOException
    {
        try {

            String requestMessage="";
            ResponseModel response;
            JsonParserCustom jsonParser=new JsonParserCustom();
            List<Transaction_M> tranStack = getTranChain();
            
            String tranStackString=jsonParser.objectToJsonString(tranStack);
            response=new ResponseModel(StatusCode.OK.getValue(), tranStackString);
            String responseString=jsonParser.objectToJsonString(response);
            System.out.println(responseString);
            
            //returning data to the client
            OutputStream outputStream;
            outputStream = clientSocket.getOutputStream();
            PrintWriter pwrite = new PrintWriter(outputStream, true);
            pwrite.println(responseString);
            pwrite.flush();
//            clientSocket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            clientSocket.close();
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //broadcasting new transation
    public void req_new_trans(Socket clientSocket, String clientAddressIP,int clientPort, RequestModel req)
    {
    
    try {  
            
            String requestMessage="";
            ResponseModel response;
            JsonParserCustom<HashMap> jsonParser=new JsonParserCustom(HashMap.class);
            
//          SEND MESAGE TO THE BLOCKING QUEUE TO SEND RESPONSE
            response=new ResponseModel(StatusCode.ACKNOWLEDGE.getValue(), "ok");
            String responseString=jsonParser.objectToJsonString(response);
            System.out.println(responseString);
            
//          returning data to the client
            OutputStream outputStream;
            outputStream = clientSocket.getOutputStream();
            PrintWriter pwrite = new PrintWriter(outputStream, true);
            pwrite.println(responseString);
            pwrite.flush();
            clientSocket.close();
            
//          adding to the transaction table
            Transaction_DAO tranDao=new Transaction_DAO();
            Transaction_M transaction= null;
            
            //digital sign
            String encTransaction= req.argsString;
            HashMap<String,Object> mapData=jsonParser.stringToObject(encTransaction);
            HashMap<String,String> certificateMap  =(HashMap) mapData.get("certificate");
            String transactionDataString  =(String) mapData.get("transaction");
            
            JsonParserCustom<Transaction_M> jsonParserTran=new JsonParserCustom(Transaction_M.class);
            transaction=  jsonParserTran.stringToObject(transactionDataString);
            
            int count=0;
            int verified=0;
            VerifyMessage verify=new VerifyMessage();

            //VERIFYING DIGITAL CERTIFICATE***************************
            for(Sender_M send: transaction.getSenderList())
            {
                byte[] certificateByes = org.apache.commons.codec.binary.Base64.decodeBase64(certificateMap.get(String.valueOf(send.getSender_account_id())));
                Account_DAO acntDao=new Account_DAO();
                
                Account_M account=acntDao.getOne(send.getSender_account_id());
                byte[] pubKeyBytes = org.apache.commons.codec.binary.Base64.decodeBase64(account.getPublic_key());
                PublicKey publicKey = 
                KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubKeyBytes));  
                if(verify.verifySignatureCustom(transactionDataString.getBytes(),certificateByes ,publicKey))
                {
                    verified++;
                }
                count++;
            }
            if(verified!=count)
            {
                //stop execution when digital certificates are not verified
                return;
            }
            
            //ADD TRANSACTION TO THE TABLE ON SUCCESSFULL VERIFICATION OF DIGITAL CERTIFICATE
            Transaction_M myTransactionWithoutId=new Transaction_M();
            myTransactionWithoutId.setAmount(transaction.getAmount());
            myTransactionWithoutId.setDate_time(transaction.getDate_time());
            myTransactionWithoutId.setPublic_transaction_id(transaction.getPublic_transaction_id());
            myTransactionWithoutId.setReceiver_account_id(transaction.getReceiver_account_id());
            myTransactionWithoutId.setAmount(transaction.getAmount());
            myTransactionWithoutId.setStatus(transaction.getStatus());

            //adding transaction to the dao
            transaction=tranDao.create(myTransactionWithoutId);

            //adding sender account details
            Sender_DAO senderDao=new Sender_DAO();
            for(Sender_M sender: transaction.getSenderList())
            {
                sender.setPublic_transaction_id(transaction.getPublic_transaction_id());
                senderDao.create(sender);
            }
//************ADDING TRANSATION TO THE ProofOfWork
        //SET OBJECT TO THE MINNING QUEUE

        SingletonProofOfWorkInstance pow=SingletonProofOfWorkInstance.getInstance();
        HashMap<String, Object> queue=new HashMap<String, Object>();
        queue.put("transaction", transaction);
        pow.putQueue(queue);    
    }
    catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void req_validation(Socket clientSocket, String clientAddressIP,int clientPort, RequestModel req)
    {
    
    try {  
            String requestMessage="";
            ResponseModel response;
            JsonParserCustom jsonParser=new JsonParserCustom();
            response=new ResponseModel(StatusCode.ACKNOWLEDGE.getValue(), "ok");
            String responseString=jsonParser.objectToJsonString(response);
            System.out.println(responseString);

            //returning data to the client
            OutputStream outputStream;
            outputStream = clientSocket.getOutputStream();
            PrintWriter pwrite = new PrintWriter(outputStream, true);
            pwrite.println(responseString);
            pwrite.flush();
            clientSocket.close();
            
            
            //removing cheese
            JsonParserCustom<Cheese_M> jsonParsercheese=new JsonParserCustom(Cheese_M.class);
            Cheese_DAO cheeseDao=new Cheese_DAO();
            Cheese_M cheese= null;
            cheese= (Cheese_M)jsonParsercheese.stringToObject(req.argsString);
            int deletedResult=cheeseDao.deleteErroCheese(cheese);
            if(deletedResult>0)
            {
//                updating transaction table
                Transaction_DAO tranDao=new Transaction_DAO();
                Transaction_M transaction=tranDao.getOneByPublicTranID(cheese.getPublic_transaction_id());
                tranDao.updateStatus(transaction, "waiting");
//              Adding to minning queue
                SingletonProofOfWorkInstance pow=SingletonProofOfWorkInstance.getInstance();
                HashMap<String, Object> queue=new HashMap<String, Object>();
                queue.put("transaction", transaction);
                pow.putQueue(queue);
            }
            
    }
    catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void req_new_cheese(Socket clientSocket, String clientAddressIP,int clientPort, RequestModel req)
    {
//    
        try {  
                String requestMessage="";
                ResponseModel response;
                JsonParserCustom<Cheese_M> jsonParser=new JsonParserCustom(Cheese_M.class);
                
                response=new ResponseModel(StatusCode.ACKNOWLEDGE.getValue(), "ok");
                String responseString=jsonParser.objectToJsonString(response);
                System.out.println(responseString);

                //returning data to the client
                OutputStream outputStream;
                outputStream = clientSocket.getOutputStream();
                PrintWriter pwrite = new PrintWriter(outputStream, true);
                pwrite.println(responseString);
                pwrite.flush();
                clientSocket.close();
                
//              start the validation
                Cheese_M cheese= jsonParser.stringToObject(req.argsString);
                Validation validation=new Validation(cheese);
                //starting the validation
                validation.run();
        }
        catch (Exception ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public List<Cheese_M> getCheeseChainD()
    {
       Cheese_DAO cheeseDao=new  Cheese_DAO();
       List<Cheese_M> cheeseList=cheeseDao.getAll();
       return cheeseList;
    }
    public List<Transaction_M> getTranChain()
    {
       Transaction_DAO tranDao=new  Transaction_DAO();
       List<Transaction_M> tranList=tranDao.getAllwithSender();
       
       return tranList;
    }

  public void req_test(Socket clientSocket, String clientAddressIP,int clientPort)
    {
        try {
            
            String requestMessage="";
            ResponseModel response;
            JsonParserCustom jsonParser=new JsonParserCustom();
           
            response=new ResponseModel(StatusCode.OK.getValue(), "Test Success");
            String responseString=jsonParser.objectToJsonString(response);
            System.out.println(responseString);
            
            //returning data to the client
            OutputStream outputStream=clientSocket.getOutputStream();
            PrintWriter pwrite = new PrintWriter(outputStream, true);
            pwrite.println(responseString);
            pwrite.flush();
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
