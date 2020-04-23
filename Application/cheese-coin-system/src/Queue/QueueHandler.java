/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

import JsonParser.JsonParserCustom;
import Model.RequestModel;
import Model.ResponseModel;
import controller.RequestHandler;
import enums.ReqCommand;
import enums.RequestType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class QueueHandler implements Runnable{
    BlockingQueue<HashMap<String,Object>> queue;

    
    public QueueHandler( BlockingQueue<HashMap<String,Object>> q)
    {
        System.out.println("Queue handler started");
        this.queue=q;
    }
    @Override
    public void run() {
        System.out.println("Queue handler asdasdrun");
      while(true)
      {
          try {
              HashMap<String,Object> client=  queue.take();
              RequestHandler reqHandler=new RequestHandler((Socket)client.get("new_client"));
              reqHandler.run();

          } 
          catch (InterruptedException ex) {
              System.out.println(ex.getMessage());
              Logger.getLogger(QueueHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
       
    }
    
}
