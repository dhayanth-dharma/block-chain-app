/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestMaker;

import CheeseCoinCore.CurrentMember;
import CheeseCoinCore.TrackerServerAddress;
import JsonParser.JsonParserCustom;
import MessageSender.MessageQueueSingleton;
import Model.Member_M;
import Model.RequestModel;
import enums.ReqCommand;
import enums.RequestType;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import proof_of_work.SingletonProofOfWorkInstance;

/**
 *
 * @author user
 */
public class GemMemberList_RM {

    public GemMemberList_RM() {
    }
    
    public void initate()
    {
        CurrentMember curMem=new CurrentMember();
        Member_M me=new Member_M();
        me.setMember_ip(curMem.getIp());
        me.setMember_port(curMem.getPort());
        me.setMember_name(curMem.getName());
        JsonParserCustom jsonParser=new JsonParserCustom();
         String meString=jsonParser.objectToJsonString(me);
        RequestModel<Member_M> req= new RequestModel
        (RequestType.POST.toString(),ReqCommand.REQ_REGISTER.toString(),meString);
        TrackerServerAddress tracker=new TrackerServerAddress();
     
       Broadcast(tracker.getIp(), tracker.getPort(), req);
    }
    
    public void Broadcast(String ip, int port, RequestModel req)
    {
         try {
              Socket socket=new Socket(ip,port);
              HashMap<String,Object> mesg=new HashMap<String,Object>();
              mesg.put("socket",socket);
              mesg.put("request",req);
              
              MessageQueueSingleton mesgSing=MessageQueueSingleton.getInstance();
              mesgSing.putQueue(mesg);
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
