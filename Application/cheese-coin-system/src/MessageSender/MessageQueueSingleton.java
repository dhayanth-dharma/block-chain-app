/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageSender;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MessageQueueSingleton {

     static BlockingQueue<HashMap<String,Object>>  queue;
     private static MessageQueueSingleton instance;     
    private MessageQueueSingleton() {
        this.queue=new ArrayBlockingQueue<>(15);
         
    }
    
    public void setInstance(BlockingQueue<HashMap<String,Object>> q)
    {System.out.println("Queue singleton done");
        this.queue=q;
    }
    
    public static void putQueue(HashMap<String,Object> obj)
    {
         try {
             System.out.println("Current Queue: "+queue);
             queue.put(obj);
         } catch (InterruptedException ex) {
             System.out.println(ex.getMessage());
                     
             Logger.getLogger(MessageQueueSingleton.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
    
    public static MessageQueueSingleton getInstance()
    {
        if(instance==null)
            instance=new MessageQueueSingleton();
        return instance;
    }
        
     public  HashMap<String,Object> getObjFromQueue()
    {
         try {
             return this.queue.take();
         } catch (InterruptedException ex) {
             
             Logger.getLogger(MessageQueueSingleton.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
    }
        
    
    
    
    
    
    
    
}
