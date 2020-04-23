/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackerserver;

import JsonParser.JsonParserCustom;
import Model.CheeseModel;
import Model.Member_M;
import Model.RequestModel;
import Model.ResponseModel;
import Model.Sender_M;
import Model.Transaction_M;
import dataAccess.Member_DAO;
import dataAccess.Member_List_DAO;

import enums.ReqCommand;
import enums.RequestType;
import enums.StatusCode;
import java.io.BufferedReader;
//import static controller.RequestController.getCheeseChain;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
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
public class TrackerRequestHandler   {
     
    
    public TrackerRequestHandler()
    {         
    }

    
    public void run(Socket clientSocket ) {
        try {
            
            System.out.print("tracker handler running"+clientSocket.toString());
            String requestMessage="";
            InetAddress addr = clientSocket.getInetAddress();
            String clientAddressIP = clientSocket.getInetAddress().toString().substring(1);
            int clientPort = clientSocket.getPort();
            BufferedReader   bufferReaer=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //reading message from the bufferReader
//            while((requestMessage=bufferReaer.readLine())!=null)
//            {
//                System.out.println(requestMessage);
//            }
            requestMessage=bufferReaer.readLine();
            JsonParserCustom jsonParser=new JsonParserCustom();
            RequestModel req = jsonParser.stringToReqObj(requestMessage);
            
            //Response for Cheese stack request
               if (req.reqest_type.equals(RequestType.GET.toString()) &&req.command.equals(ReqCommand.REQ_TEST.toString()))
              {
                  req_test(clientSocket, clientAddressIP, clientPort);
              }
               if (req.reqest_type.equals(RequestType.POST.toString()) &&req.command.equals(ReqCommand.REQ_REGISTER.toString()))
              {
                  req_member_list(clientSocket, clientAddressIP, clientPort, req);
              }
               if (req.reqest_type.equals(RequestType.POST.toString()) &&req.command.equals(ReqCommand.REQ_DEATH_MEMBER.toString()))
              {
                  req_liveliness(clientSocket, clientAddressIP, clientPort, req);
              }
          
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(TrackerRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 public void req_member_list(Socket clientSocket, String clientAddressIP,int clientPort, RequestModel req)
 {
        try {
            
            System.out.print("tracker: req memberlist reached");
            ResponseModel response;
            JsonParserCustom<Member_M> jsonParser=new JsonParserCustom(Member_M.class);
            
            //get member data and return
            Member_DAO memDao=new Member_DAO();
            List<Member_M> memberList;
            memberList=memDao.getAll_Ten();
            if(memberList==null)
                memberList=new ArrayList<Member_M>();
//            memberList=memDao.getAll();
            
            Member_M newMem=jsonParser.stringToObject(req.argsString);
            newMem.setStatus("active");
            newMem=memDao.createAndGet(newMem);
            
            //adding current member to the list
            memberList.add(newMem);
            
            //this is response.args
            String memberListString=jsonParser.objectToJsonString(memberList);
            response=new ResponseModel(StatusCode.OK.getValue(), memberListString);
            String responseString=jsonParser.objectToJsonString(response);
            System.out.println(responseString);
            
            //returning data to the client
            OutputStream outputStream;
            outputStream = clientSocket.getOutputStream();
            PrintWriter pwrite = new PrintWriter(outputStream, true);
            pwrite.println(responseString);
            pwrite.flush();
            clientSocket.close();
           
//********************************************************* i move this to up            
//            Member_M newMem=jsonParser.stringToObject(req.argsString);
//            System.out.println(newMem.getMember_name()+":"+ newMem.getMember_ip());
//            memDao.create(newMem);
            
        } catch (IOException ex) {
            Logger.getLogger(TrackerRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(TrackerRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  public void req_liveliness(Socket clientSocket, String clientAddressIP,int clientPort, RequestModel req)
    {
        try {
            
            System.out.print("tracker: req memberlist reached");
            ResponseModel response;
            JsonParserCustom<Member_M> jsonParser=new JsonParserCustom(Member_M.class);

            response=new ResponseModel(StatusCode.ACKNOWLEDGE.getValue(), "ack");
            String responseString=jsonParser.objectToJsonString(response);
            
            //returning data to the client
            OutputStream outputStream;
            outputStream = clientSocket.getOutputStream();
            PrintWriter pwrite = new PrintWriter(outputStream, true);
            pwrite.println(responseString);
            pwrite.flush();
            clientSocket.close();
            
            Member_M newMem=jsonParser.stringToObject(req.argsString);
            liveliness(newMem);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(TrackerRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void liveliness(Member_M member)
    {

        int requestingCount=0;
        RequestModel<String> req=new RequestModel(RequestType.GET.toString(),ReqCommand.REQ_TEST.toString());
         
         try {
             try {
             
            Socket socket=new Socket(member.getMember_ip(),member.getMember_port());
            JsonParserCustom jsonParser = new JsonParserCustom();
             
            OutputStream ostream = socket.getOutputStream();
            String reqJsonObj = jsonParser.objectToJsonString(req);
            PrintWriter pwrite = new PrintWriter(ostream, true);
            pwrite.println(reqJsonObj); // sending to server
            pwrite.flush(); 
            //             requestingCount=1;
            socket.setSoTimeout(2000);
            //******* GETTING DATA FROM SERVER
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
             while(true){ 
                 try{
                           String receivedData = br.readLine();
                           System.out.println("Server response Received 1 "+receivedData);
                           ResponseModel res=jsonParser.stringToResObj(receivedData);
                           System.out.print(res);
                           socket.close();
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
                           if(memberDead(member))
                           {
                               System.out.println("member status updataded");
                           }
                       }
                   }
               }
              } catch (java.net.ConnectException  e) {
                  memberDead(member);
                  System.out.println("Tracker Server: Member dead updated");
             }
           }
           catch(Exception ex)
           {
               System.out.println(ex.getMessage());
           }
    }
    
    
    public boolean memberDead(Member_M member)
    {
        Member_DAO memdao=new Member_DAO();
       int result= memdao.updateStatus(member, "dead");
       if(result>0)
           return true;
       else 
           return false;
           
    }
}
