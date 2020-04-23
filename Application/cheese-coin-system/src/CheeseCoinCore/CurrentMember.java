/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheeseCoinCore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class CurrentMember {
    public int member_id;
    public String ip;
    public String name;
    public int port;
    public CurrentMember() {
        try {
            this.member_id=1;
            InetAddress IP=InetAddress.getLocalHost();
             ip=IP.toString().substring(IP.toString().lastIndexOf('/') + 1,IP.toString().length() );

             port=8825;
             name="dhaya";
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(CurrentMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDataFromConfig()
    {
    
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    
    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    
}
