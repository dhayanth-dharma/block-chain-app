/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proof_of_work;

import MessageSender.MessageQueueSingleton;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class SingletonProofOfWorkInstance {

      static BlockingQueue<HashMap<String,Object>>  queue;
    private static SingletonProofOfWorkInstance instance;     
    
    private SingletonProofOfWorkInstance() {
      this.queue=new ArrayBlockingQueue<HashMap<String,Object>>(15);
    
    }
    
    public void setQueueInstance(BlockingQueue<HashMap<String,Object>> q)
    {
        System.out.println("Queue singleton done");
        this.queue=q;
    }
    
    public void putQueue(HashMap<String,Object> obj)
    {
         try {
             System.out.println("Current Que: "+queue);
             
             queue.put(obj);
         } catch (InterruptedException ex) {
             System.out.println(ex.getMessage());
                     
             Logger.getLogger(MessageQueueSingleton.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
     public  HashMap<String,Object>  takeQueue()
    {
         try {
             
             return queue.take();
         } catch (InterruptedException ex) {
             System.out.println(ex.getMessage());
             Logger.getLogger(MessageQueueSingleton.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
    }
    
    
    public static SingletonProofOfWorkInstance getInstance()
    {
        if(instance==null)
            instance=new SingletonProofOfWorkInstance();
        return instance;
    }
      
    
    
    
    
}
