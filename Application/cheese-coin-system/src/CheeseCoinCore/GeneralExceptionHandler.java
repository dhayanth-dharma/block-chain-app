/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheeseCoinCore;

import Model.RequestModel;
import enums.ReqCommand;
import enums.RequestType;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import requestMaker.InformToServer;

public class GeneralExceptionHandler {
    private int port;
    private String ip;
    public GeneralExceptionHandler() {
    }
    public GeneralExceptionHandler(String ip, int port) {
        this.ip=ip;
        this.port=port;
    }
    public void handleException(Exception ex)
    {
         
        if(ex instanceof java.net.ConnectException) 
        {
            
                InformToServer inform=new InformToServer();
                inform.inform(ip, port);
                System.out.println(ex.getMessage());
                System.out.println(ex.getStackTrace());
                showMessage("CONNECTION ERROR>>", "Server notified");
                JOptionPane.showMessageDialog(null, "Server Notified with death member");
        }
        else
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getStackTrace());
        }
    }
    
    public void showMessage(String title, String ex)
    {
        JLabel new_text = new JLabel(ex);
        final JDialog d = new JDialog();
        d.setSize(800, 600);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - d.getWidth()) / 2;
        final int y = (screenSize.height - d.getHeight()) / 2;
        d.setLocation(x, y);
        d.setTitle(title);
        d.add(new_text);
        d.setVisible(true);
        
    }
    public void showMessage(String title, String ex, String args)
    {
        JLabel new_text = new JLabel(args+":>>>:"+ex);
        final JDialog d = new JDialog();
        d.setSize(200, 200);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - d.getWidth()) / 2;
        final int y = (screenSize.height - d.getHeight()) / 2;
        d.setLocation(x, y);
        d.setTitle(title);
        d.add(new_text);
        d.setVisible(true);

    }
}
