/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ControllerThread  extends Thread{
   
    Socket socket;
    public ControllerThread(Socket socket)
    {
        this.socket=socket;
                
    }
  public   void run()
    {
        String message=null;
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((message=br.readLine()) !=null)
            {
                System.out.print(message);
            }
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ControllerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
