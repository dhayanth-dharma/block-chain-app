/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackerserver;

import JsonParser.JsonParserCustom;
import Model.Member_M;
import Model.RequestModel;
import Model.ResponseModel;
import com.google.gson.Gson;
import dataAccess.Member_DAO;
import enums.ReqCommand;
import enums.RequestType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class TrackerServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        try {
            
//            getMemberTest();
            int port=9999;
            String ip="localhost";
            
            System.out.println("Tracker starting");
            //creating server socket runnign on given port
            ServerSocket serverSocket = new ServerSocket(port);
            
            System.out.println("Tracker waiting for client");
            Gson gson=new Gson();
            TrackerRequestHandler reqHandler=new TrackerRequestHandler();
            while(true)
            {
                Socket clientSocket= serverSocket.accept();
                TrackerRequestHandler handler=new TrackerRequestHandler();
                handler.run(clientSocket);
       
                        
            }
            
//            closing the server socket
//            serverSocket.close();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    public static void getMemberTest()
    {
         Member_DAO memDao=new Member_DAO();
         List<Member_M> memberList=new ArrayList<Member_M>();
//            memberList=memDao.getAll_Ten();
         memberList=memDao.getAll();
         System.out.println(memberList.toString());
    }
    
}
