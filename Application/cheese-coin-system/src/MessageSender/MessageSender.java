/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageSender;

import JsonParser.JsonParserCustom;
import Model.RequestModel;
import Model.ResponseModel;
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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MessageSender implements Runnable{
 BlockingQueue<HashMap<String,Object>> queue;

  private MessageQueueSingleton singleton;
    public MessageSender() {
         singleton=MessageQueueSingleton.getInstance();
    }

    @Override
    public void run() {
         singleton=MessageQueueSingleton.getInstance();
        while(true)
        {  
            try {
               HashMap<String,Object> obj=  singleton.getObjFromQueue();
               Socket socket= (Socket) obj.get("socket");
               RequestModel req= (RequestModel) obj.get("request");
               MessageHandler msgHand=new MessageHandler();
               msgHand.HandlerMessage(socket, req);
            } //end of complete while
            catch (Exception ex) {
                System.out.println("Error from handler:"+ex.getMessage());
                Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
