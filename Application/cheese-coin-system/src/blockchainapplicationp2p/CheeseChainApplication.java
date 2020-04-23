/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainapplicationp2p;
//import jdk.internal.org.objectweb.asm.TypeReference;
import MessageSender.MessageQueueSingleton;
import MessageSender.MessageSender;
import Model.Transaction_B;
import Queue.QueueHandler;
import Queue.ServerController;
import UI.Main_Member;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import proof_of_work.POW_Starter;
import proof_of_work.SingletonProofOfWorkInstance;

/**
 *
 * @author user
 */
public class CheeseChainApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            start();
            
        } catch (Exception ex) {
            Logger.getLogger(CheeseChainApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void  start()
    {
        //starting client pipe
        MessageQueueSingleton messQueue=MessageQueueSingleton.getInstance();
        MessageSender mesgSender=new MessageSender();
        new Thread(mesgSender).start();//starting queue
        
        
        //starting server pipe
        BlockingQueue<HashMap<String,Object>> queue=new ArrayBlockingQueue<>(15);
        QueueHandler queueHandler=new QueueHandler(queue);
        new Thread(queueHandler).start();//starting queue 
         ServerController serverController=new ServerController(queue);
        new Thread(serverController).start();
       
        
        //starting proof of work queue
        SingletonProofOfWorkInstance powSing=SingletonProofOfWorkInstance.getInstance();
        POW_Starter powStarter=new POW_Starter();
        new Thread(powStarter).start();//starting queue

        
        Main_Member mm=new Main_Member();
        mm.show();
    }
}
