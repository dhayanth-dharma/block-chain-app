/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageSender;

import CheeseCoinCore.CurrentMember;
import CheeseCoinCore.TrackerServerAddress;
import JsonParser.JsonParserCustom;
import Model.Cheese_M;
import Model.Member_M;
import Model.RequestModel;
import Model.ResponseModel;
import Model.Transaction_M;
import dataAccess.Cheese_DAO;
import dataAccess.Member_DAO;
import dataAccess.Transaction_DAO;
import enums.ReqCommand;
import enums.RequestType;
import enums.StatusCode;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MessageHandler {

    public MessageHandler() {
    }
    public void Handler(HashMap<String,Object> obj)
    {
         test(obj);
    }
    public void HandlerMessage(Socket socket, RequestModel req)
    {
       System.out.println("mesg handler started");
       if(req.command.equals(ReqCommand.REQ_VALIDATION.toString()))
       {
           validation_error(socket, req);
       }
       else if(req.command.equals(ReqCommand.REQ_CHEESE_CHAIN.toString()))
       {
           req_cheese_chain(socket, req);
       }
       else if(req.command.equals(ReqCommand.REQ_TRAN_CHAIN.toString()))
       {
           req_tran_chain(socket, req);
       }
       else if(req.command.equals(ReqCommand.REQ_NEW_TRANS.toString()))
       {
           new_transaction(socket, req);
       }
       else if(req.command.equals(ReqCommand.REQ_NEW_CHEESE.toString()))
       {
           new_cheese(socket, req);
       }
       else if(req.command.equals(ReqCommand.REQ_TEST.toString()))
       {
           test(socket, req);
       }
        else if(req.command.equals(ReqCommand.REQ_REGISTER.toString()))
       {
           req_register(socket, req);
       }
        else if(req.command.equals(ReqCommand.REQ_DEATH_MEMBER.toString()))
       {
           connect_death_member_(socket, req);
       }
       
    }
    
    
    public void validation_error(Socket socket, RequestModel req)
    {
         try {
                String reqJsonObj="";
                
                int requestingCount=0;
                OutputStream ostream = socket.getOutputStream();
                
                JsonParserCustom jsonParser = new JsonParserCustom();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                reqJsonObj= jsonParser.objectToJsonString(req);
                pwrite.println(reqJsonObj); // sending to server
                pwrite.flush(); //force send
                
                    socket.setSoTimeout(4000);
                    while(true)
                    {
                        try 
                        {
                            InputStream is = socket.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String receivedData = br.readLine();
                            ResponseModel res=jsonParser.stringToResObj(receivedData);
                            if(StatusCode.OK.getValue()==res.status_code && res.argsString.equals("ack"))
                            {
                               socket.close();
                                break;
                            }

                        } 
                        catch (SocketTimeoutException e) {
                            if(requestingCount==0)
                            {
                                requestingCount=1;
                                pwrite.println(reqJsonObj); // sending to server
                                pwrite.flush(); 
                            }
                            else if(requestingCount==1)
                            {
                                socket.close();
                                break;
                            }
                        }//timeout exception end
                    }
            } 
            catch (IOException ex) {
                   Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public int new_cheese(Socket socket, RequestModel req)
    {
        try {
                String reqJsonObj="";
                int requestingCount=0;
                OutputStream ostream = socket.getOutputStream();
                JsonParserCustom jsonParser = new JsonParserCustom();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                reqJsonObj= jsonParser.objectToJsonString(req);
                pwrite.println(reqJsonObj); // sending to server
                pwrite.flush(); //force send
                    
                    while(true)
                    {
                        try 
                        {
                            InputStream is = socket.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String receivedData = br.readLine();
                            ResponseModel res=jsonParser.stringToResObj(receivedData);
                            if(StatusCode.ACKNOWLEDGE.getValue()==res.status_code )
                            {
                               socket.close();
                               return 1;
                            }
                        } 
                        catch (SocketTimeoutException e) {
                                socket.close();
                                return 2; //serve not responding
                        }//timeout exception end
                    }
            } 
       catch (IOException ex) {
                   Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
                   return 4;//internal server error
       }              
    }
    public int req_cheese_chain(Socket socket, RequestModel req)
    {                               
        try {
                String reqJsonObj="";
                int requestingCount=0;
                OutputStream ostream = socket.getOutputStream();
                JsonParserCustom jsonParser = new JsonParserCustom();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                reqJsonObj= jsonParser.objectToJsonString(req);
                pwrite.println(reqJsonObj); // sending to server
                pwrite.flush(); //force send
                    socket.setSoTimeout(4000);
                    while(true)
                    {
                        try 
                        {
                            InputStream is = socket.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String receivedData = br.readLine();
                            ResponseModel res=jsonParser.stringToResObj(receivedData);
                            if(StatusCode.OK.getValue()==res.status_code )
                            {
                               socket.close();
                               List<Cheese_M> list=jsonParser.stringTocheeseList(res.argsString);
                               return add_cheese_list(list);
                            }
                        } 
                        catch (SocketTimeoutException e) {
                            if(requestingCount==0)
                            {
                                requestingCount=1;
                                pwrite.println(reqJsonObj); // sending to server
                                pwrite.flush(); 
                            }
                            else 
                            {
                                socket.close();
                                return 3; //serve not responding
                            }
                        }//timeout exception end
                    }
            } 
       catch (IOException ex) {
                   Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
                   return 4;//internal server error
       }                            
    }    
    public int add_cheese_list(List<Cheese_M>cheeseList)
    {
       
        Cheese_DAO cheeseDao=new Cheese_DAO();
        int count=0;
        int itemCount=0;
        itemCount=cheeseList.size();
        for(Cheese_M item : cheeseList)
        {
            cheeseDao.create(item);
            itemCount++;
        }
        if(itemCount==count)
        {
            return 0;
        }
        else
            return 1;
    }
    public int req_tran_chain(Socket socket, RequestModel req)
    {                               
        try {
                String reqJsonObj="";
                int requestingCount=0;
                OutputStream ostream = socket.getOutputStream();
                JsonParserCustom jsonParser = new JsonParserCustom();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                reqJsonObj= jsonParser.objectToJsonString(req);
                pwrite.println(reqJsonObj); // sending to server
                pwrite.flush(); //force send
                    socket.setSoTimeout(4000);
                    while(true)
                    {
                        try 
                        {
                            InputStream is = socket.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String receivedData = br.readLine();
                            ResponseModel res=jsonParser.stringToResObj(receivedData);
                            if(StatusCode.OK.getValue()==res.status_code )
                            {
                               socket.close();
                               List<Transaction_M> list=jsonParser.stringTotranList(res.argsString);
                               return add_tran_list(list);
                               
                            }
                        } 
                        catch (SocketTimeoutException e) {
                            if(requestingCount==0)
                            {
                                requestingCount=1;
                                pwrite.println(reqJsonObj); // sending to server
                                pwrite.flush(); 
                            }
                            else 
                            {
                                socket.close();
                                return 3; //serve not responding
                            }
                        }//timeout exception end
                    }
            } 
       catch (IOException ex) {
                   Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
                   return 4;//internal server error
       }                            
    }    
    public int add_tran_list(List<Transaction_M> tranList)
    {
        Transaction_DAO tranDao=new Transaction_DAO();
        int count=0;
        int itemCount=0;
        itemCount=tranList.size();
        for(Transaction_M item : tranList)
        {
            tranDao.create(item);
            itemCount++;
        }
        if(itemCount==count)
        {
            return 0;
        }
        else
            return 1;
    }
    public void new_transaction(Socket socket, RequestModel req)
    {
        try {
                String reqJsonObj="";
                int requestingCount=0;
                OutputStream ostream = socket.getOutputStream();
                JsonParserCustom jsonParser = new JsonParserCustom();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                reqJsonObj= jsonParser.objectToJsonString(req);
                
                byte[] outputByte = reqJsonObj.getBytes();

                pwrite.println(reqJsonObj); // sending to server
                pwrite.flush(); //force send
                
                    socket.setSoTimeout(4000);
                    while(true)
                    {
                        try 
                        {
                            InputStream is = socket.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String receivedData = br.readLine();
                            ResponseModel res=jsonParser.stringToResObj(receivedData);
                            if(StatusCode.ACKNOWLEDGE.getValue()==res.status_code)
                            {
                               socket.close();
                                break;
                            }
                        } 
                        catch (SocketTimeoutException e) {
                            if(requestingCount==0)
                            {
                                requestingCount=1;
                                pwrite.println(reqJsonObj); // sending to server
                                pwrite.flush(); 
                            }
                            else if(requestingCount==1)
                            {
                                requestingCount=1;
                                pwrite.println(reqJsonObj); // sending to server
                                pwrite.flush(); 
                            }
                            else 
                            {
                                socket.close();
                                death_member_(socket);
                                break;
                            }
                        }//timeout exception end
                    }
            } 
       catch (IOException ex) {
                   Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void req_register(Socket socket, RequestModel req)
    {
        try {
                String reqJsonObj="";
                int requestingCount=0;
                OutputStream ostream = socket.getOutputStream();
                JsonParserCustom<Member_M> jsonParser = new JsonParserCustom(Member_M.class);
                PrintWriter pwrite = new PrintWriter(ostream, true);
                reqJsonObj= jsonParser.objectToJsonString(req);
                pwrite.println(reqJsonObj); // sending to server
                pwrite.flush(); //force send
                    socket.setSoTimeout(4000);
                    while(true)
                    {
                        try 
                        {
                            InputStream is = socket.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String receivedData = br.readLine();
                            ResponseModel res=jsonParser.stringToResObj(receivedData);
                         
                            System.out.println("Server Respond Recieved");
//                            System.out.println(res.args);
                            if(StatusCode.OK.getValue()==res.status_code)
                            {
                               socket.close();
                               Member_DAO memDap=new Member_DAO();
//                             List<Member_M> memberList=res.args;
                               List<Member_M> memberList=jsonParser.stringTomemberList(res.argsString);

                               for(Member_M mem : memberList)
                               { 
                                   memDap.create(mem);
                                   System.out.println(mem.getMember_name());
                               }
                                       
                               break;
                            }
                        } 
                        catch (SocketTimeoutException e) {
                            if(requestingCount==0)
                            {
                                requestingCount=1;
                                pwrite.println(reqJsonObj); // sending to server
                                pwrite.flush(); 
                            }
                            else if(requestingCount==1)
                            {
                                requestingCount=2;
                                pwrite.println(reqJsonObj); // sending to server
                                pwrite.flush(); 
                            }
                            else 
                            {
                                socket.close();
                                
                                break;
                            }
                        }//timeout exception end
                    }
            } 
       catch (IOException ex) {
                   Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ping(Socket socket, RequestModel req)
    {}
    public void test(HashMap<String,Object> obj)
    {
    try {
            String ip= (String) obj.get("ip");
            int port= (int) obj.get("port");
            Socket socket= new Socket(ip,port);
            RequestModel req= (RequestModel) obj.get("request");
            System.out.print(obj);
            System.out.println("Test function reached");
            JsonParserCustom jsonParser = new JsonParserCustom();

            OutputStream ostream = socket.getOutputStream();
            String reqJsonObj = jsonParser.objectToJsonString(req);
            PrintWriter pwrite = new PrintWriter(ostream, true);
            pwrite.println(reqJsonObj); // sending to server
            pwrite.flush(); 
            //******* GETTING DATA FROM SERVER
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while(true)
                {
                   String receivedData = br.readLine();
                   System.out.println("Server response Received 1 "+receivedData);
                   ResponseModel res=jsonParser.stringToResObj(receivedData);
                   System.out.print(res);
                   if(StatusCode.ACKNOWLEDGE.getValue()==res.status_code)
                   {
                      socket.close();
                       break;
                   }
                   else
                   {
                     System.out.println("RESPONDED WITH DIFFERENT CODE");
                     socket.close();
                     break;
                   }
                }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public void test(Socket socket, RequestModel req)
    {
         try {
             int requestingCount=0;
             System.out.println("Test function reached");
             JsonParserCustom jsonParser = new JsonParserCustom();
             OutputStream ostream = socket.getOutputStream();
             String reqJsonObj = jsonParser.objectToJsonString(req);
             PrintWriter pwrite = new PrintWriter(ostream, true);
             pwrite.println(reqJsonObj); // sending to server
	     pwrite.flush(); 
                          
             socket.setSoTimeout(1000);
        
            //******* GETTING DATA FROM SERVER
             InputStream is = socket.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is));
              while(true)
              { 
                try {
                        String receivedData = br.readLine();
                        System.out.println("Server response Received 1 "+receivedData);
                        ResponseModel res=jsonParser.stringToResObj(receivedData);
                        System.out.print(res);
                        socket.close();
                    } 
                catch (SocketTimeoutException e) {
                        if(requestingCount==0)
                            {
                                requestingCount=1;
                                pwrite.println(reqJsonObj); // sending to server
                                pwrite.flush(); 
                            }
                            else 
                            {
                                socket.close();
                                death_member_(socket);
                                break;
                            }
                                
                    }//timeout exception end
             }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
   
    public void death_member_(Socket soc)
    {
         try {
             Member_M member=new Member_M();
             String ip=(soc.getInetAddress().toString().substring(1));
                member.setMember_ip(ip);
                member.setMember_port(soc.getPort());
                
                TrackerServerAddress server=new TrackerServerAddress();
                
                Socket socket=new Socket(server.getIp(), server.getPort());
                String reqJsonObj="";
                
                JsonParserCustom jsonParser=new JsonParserCustom();
                 String meString=jsonParser.objectToJsonString(member);
                RequestModel<Member_M> req= new RequestModel
                (RequestType.POST.toString(),ReqCommand.REQ_DEATH_MEMBER.toString(),meString);
                TrackerServerAddress tracker=new TrackerServerAddress();
                OutputStream ostream = socket.getOutputStream();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                reqJsonObj= jsonParser.objectToJsonString(req);
                pwrite.println(reqJsonObj); // sending to server
                pwrite.flush(); //force send
                    socket.setSoTimeout(5000);
                    while(true)
                    {
                        try 
                        {
                            InputStream is = socket.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String receivedData = br.readLine();
                            ResponseModel res=jsonParser.stringToResObj(receivedData);
                            System.out.println("Server Respond Recieved");
                            if(StatusCode.ACKNOWLEDGE.getValue()==res.status_code)
                            {
                               socket.close();
                               break;
                            }
                        } 
                        catch (SocketTimeoutException e) {
                            socket.close();
                            break;
                        }//timeout exception end
                    }
            } 
       catch (IOException ex) {
                   Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void connect_death_member_(Socket socket, RequestModel req)
    {
         try {
                String reqJsonObj="";
                JsonParserCustom jsonParser=new JsonParserCustom();
                OutputStream ostream = socket.getOutputStream();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                reqJsonObj= jsonParser.objectToJsonString(req);
                pwrite.println(reqJsonObj); // sending to server
                pwrite.flush(); //force send
                    socket.setSoTimeout(3000);
                    while(true)
                    {
                        try 
                        {
                            InputStream is = socket.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String receivedData = br.readLine();
                            ResponseModel res=jsonParser.stringToResObj(receivedData);
                         
                            System.out.println(" Tracker Server Respond Recieved");
//                            System.out.println(res.args);
                            if(StatusCode.ACKNOWLEDGE.getValue()==res.status_code)
                            {
                               socket.close();
                               break;
                            }
                        } 
                        catch (SocketTimeoutException e) {
                            socket.close();
                            break;
                        }//timeout exception end
                    }
            } 
       catch (IOException ex) {
                   Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
