/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheeseCoinCore;

/**
 *
 * @author user
 */
public class TrackerServerAddress {

    private String ip;
    private int port;
    public TrackerServerAddress() {
        this.ip="localhost";
        this.port=9999;
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
