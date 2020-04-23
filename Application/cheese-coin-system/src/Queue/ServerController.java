/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

import CheeseCoinCore.CurrentMember;
import JsonParser.JsonParserCustom;
import Model.RequestModel;
import Model.ResponseModel;
import com.google.gson.Gson;
import enums.ReqCommand;
import enums.RequestType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServerController implements Runnable{
    BlockingQueue<HashMap<String,Object>> queue;
    public ServerController(BlockingQueue<HashMap<String,Object>> q)
    {
        this.queue=q;
    }
    
   
    @Override
    public void run() {
        System.out.println("server run");
        try {
            CurrentMember me=new CurrentMember();
            int port=me.getPort();
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
                queue.put(newQueue);            
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
//            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (InterruptedException ex) {
             System.out.println(ex.getMessage());
        Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
