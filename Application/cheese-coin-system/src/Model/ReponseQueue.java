/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.net.Socket;

/**
 *
 * @author user
 */
public class ReponseQueue {
    public String ip;
    public int port;
    public ResponseModel res;
    public Socket clientSocket;
    public ReponseQueue() {
    }

    public ReponseQueue(String ip, int port, ResponseModel res) {
        this.ip = ip;
        this.port = port;
        this.res = res;
    }
    
    
    
}
