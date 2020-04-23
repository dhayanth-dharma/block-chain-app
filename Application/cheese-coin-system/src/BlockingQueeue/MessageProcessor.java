/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockingQueeue;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageProcessor implements Runnable{
BlockingQueue<HashMap<String,Object>> queue;
    
    public MessageProcessor(BlockingQueue<HashMap<String,Object>> q)
    {
        this.queue=q;
    }
    
    @Override
    public void run() {
        System.out.println("server run");
        try {
            int port=9999;
            String ip="localhost";
            
            System.out.println("Server starting");
            //creating server socket runnign on given port
            ServerSocket serverSocket = new ServerSocket(port);
            while(true)
            {
                
                Socket clientSocket= serverSocket.accept();
                System.out.println("New Client Request Received");
                //adding to the queue
                HashMap<String,Object> newQueue=new HashMap<String,Object>();
                newQueue.put("new_client", clientSocket);
                this.queue.put(newQueue);
            }

        } catch (IOException |InterruptedException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
    
}
