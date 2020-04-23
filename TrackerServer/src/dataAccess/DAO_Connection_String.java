/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class DAO_Connection_String {

    private String server_location;
    private String user_name;
    private String password;
     private File configFile = new File("config.properties");
	private Properties configProps;
    public DAO_Connection_String() {

          this.server_location="jdbc:mysql://"+
                  configProps.getProperty("tracker--host")+":"+configProps.getProperty("tracker--port")+"/"+
                  configProps.getProperty("tracker--dbname");
          this.user_name=configProps.getProperty("tracker--user");
          this.password=configProps.getProperty("tracker--pass");
              
    }

    public String getPassword() {
        return password;
    }

    public String getServer_location() {
        return server_location;
    }

    public String getUser_name() {
        return user_name;
    }
    private void loadProperties()  {
        try {
            
        
		Properties defaultProps = new Properties();
		// sets default properties
		defaultProps.setProperty("host", "localhost");
		defaultProps.setProperty("port", "3306");
		defaultProps.setProperty("user", "root");
		defaultProps.setProperty("pass", "");
		defaultProps.setProperty("dbname", "cheese-coin-member");
		defaultProps.setProperty("tracker--host", "localhost");
		defaultProps.setProperty("tracker--port", "3306");
		defaultProps.setProperty("tracker--user", "root");
		defaultProps.setProperty("tracker-pass", "");
		defaultProps.setProperty("tracker-dbname", "cheese-coin-tracker");
		
		configProps = new Properties(defaultProps);
		
		// loads properties from file
		InputStream inputStream = new FileInputStream(configFile);
		configProps.load(inputStream);
                
		inputStream.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
        }
	}
    
    
}
