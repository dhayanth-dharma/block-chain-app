/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestMaker;

import JsonParser.JsonParserCustom;
import MessageSender.MessageQueueSingleton;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class BroadCastAllMember {
RequestModel req;
    public BroadCastAllMember(RequestModel requ) {
        this.req=requ;
//        Broadcast("localhost", 8222);
        getMemberList();
    }
    
    public void getMemberList()
    {
        List<Member_M> mList=new ArrayList<Member_M>();
        Member_DAO memDao=new Member_DAO();
        mList=memDao.getAllActive();
        for(Member_M item : mList)
        {
            Broadcast(item.getMember_ip(),item.getMember_port());
        }
    }
    
     public void Broadcast(String ip, int port)
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
