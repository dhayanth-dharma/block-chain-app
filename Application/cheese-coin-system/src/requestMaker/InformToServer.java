/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestMaker;

import CheeseCoinCore.TrackerServerAddress;
import JsonParser.JsonParserCustom;
import MessageSender.MessageQueueSingleton;
import MessageSender.MessageSender;
import Model.Member_M;
import Model.RequestModel;
import Model.ResponseModel;
import dataAccess.Member_DAO;
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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class InformToServer {

    public InformToServer() {
        
    }
    
   
    public void inform(Member_M member)
    {
        try {
            TrackerServerAddress tracker=new TrackerServerAddress();
            
            Socket socket=new Socket(tracker.getIp(),tracker.getPort());
            RequestModel<Member_M> req= new RequestModel
            (RequestType.POST.toString(),ReqCommand.REQ_DEATH_MEMBER.toString(),member);
            
            HashMap<String,Object> mesg=new HashMap<String,Object>();
            mesg.put("socket",socket);
            mesg.put("request",req);
            
            MessageQueueSingleton mesgSing=MessageQueueSingleton.getInstance();     
            mesgSing.putQueue(mesg);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(InformToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void inform(String ip, int port)
    {
        try {
            TrackerServerAddress tracker=new TrackerServerAddress();
            Member_M member=new Member_M();
            member.setMember_ip(ip);
            member.setMember_port(port);
            //updating current member table
            memberDead(member);
            JsonParserCustom jsonParser=new JsonParserCustom();
            String meString=jsonParser.objectToJsonString(member);
            Socket socket=new Socket(tracker.getIp(),tracker.getPort());
            RequestModel<Member_M> req= new RequestModel
              (RequestType.POST.toString(),ReqCommand.REQ_DEATH_MEMBER.toString(),meString);
            
            HashMap<String,Object> mesg=new HashMap<String,Object>();
            mesg.put("socket",socket);
            mesg.put("request",req);
            MessageQueueSingleton mesgSing=MessageQueueSingleton.getInstance();     
            mesgSing.putQueue(mesg);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(InformToServer.class.getName()).log(Level.SEVERE, null, ex);
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
