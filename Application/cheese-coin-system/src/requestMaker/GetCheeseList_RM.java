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
import dataAccess.Member_DAO;
import enums.ReqCommand;
import enums.RequestType;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class GetCheeseList_RM {
   
    public void initate()
    {
        Member_DAO memDao=new Member_DAO();
        List<Member_M> memList=memDao.getAllActive();
        JsonParserCustom jsonParser=new JsonParserCustom();
        RequestModel<Member_M> req= new RequestModel
        (RequestType.GET.toString(),ReqCommand.REQ_CHEESE_CHAIN.toString());
        for(Member_M mem: memList)
        {
            try {
                Socket socket=new Socket(mem.getMember_ip(),mem.getMember_port());
                Broadcast(socket, req);
                break;
              } catch (IOException  e) {
                continue;
            }
        
        }
    }
    
    public void Broadcast(Socket socket, RequestModel req)
    {
         try {
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
