/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockingQueeue;

import Queue.QueueHandler;
import controller.RequestHandler;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageHandler implements Runnable{
   BlockingQueue<HashMap<String,Object>> queue;
    public MessageHandler(BlockingQueue<HashMap<String,Object>> q)
    {
        this.queue=q;
    }
    @Override
    public void run() {
          System.out.println("Starting qwe");
        while(true)
        {  System.out.println("Starting ewr");
          try {
              HashMap<String,Object> client=  queue.take();
               System.out.println("Starting 3");
              RequestHandler reqHandler=new RequestHandler((Socket)client.get("new_client"));
              reqHandler.run();
          } 
          catch (Exception ex) {
              System.out.println(ex.getMessage());
              Logger.getLogger(QueueHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
    }   
}
