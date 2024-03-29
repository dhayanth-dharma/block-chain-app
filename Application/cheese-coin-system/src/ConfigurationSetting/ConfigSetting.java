package ConfigurationSetting;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class ConfigSetting extends JFrame {
	private File configFile = new File("config.properties");
	private Properties configProps;
	
	private JLabel labelHost = new JLabel("Host name: ");
	private JLabel labelPort = new JLabel("Port number: ");
	private JLabel labelUser = new JLabel("Username: ");
	private JLabel labelPass = new JLabel("Password: ");
	private JLabel labelDbname = new JLabel("database: ");
        
        private JLabel labeltHost = new JLabel("Trakcer-Host name: ");
	private JLabel labeltPort = new JLabel("Trakcer-Port number: ");
	private JLabel labeltUser = new JLabel("Trakcer-Username: ");
	private JLabel labeltPass = new JLabel("Trakcer-Password: ");
	private JLabel labeltDbname = new JLabel("Trakcer-database: ");
	
	private JTextField textHost = new JTextField(20);
	private JTextField textPort = new JTextField(20);
	private JTextField textUser = new JTextField(20);
	private JTextField textPass = new JTextField(20);
	private JTextField textDb = new JTextField(20);
	
        private JTextField textTHost = new JTextField(20);
	private JTextField textTPort = new JTextField(20);
	private JTextField textTUser = new JTextField(20);
	private JTextField textTPass = new JTextField(20);
	private JTextField textTDb = new JTextField(20);
	private JButton buttonSave = new JButton("Save");
	
	public ConfigSetting() {
		super("Properties Configuration");
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 5, 10);
		constraints.anchor = GridBagConstraints.WEST;
		
		add(labelHost, constraints);
		
		constraints.gridx = 1;
		add(textHost, constraints);
		
		constraints.gridy = 1;
		constraints.gridx = 0;
		add(labelPort, constraints);
		
		constraints.gridx = 1;
		add(textPort, constraints);

		constraints.gridy = 2;
		constraints.gridx = 0;
		add(labelUser, constraints);
		
		constraints.gridx = 1;
		add(textUser, constraints);

		constraints.gridy = 3;
		constraints.gridx = 0;
		add(labelPass, constraints);
		
		constraints.gridx = 1;
		add(textPass, constraints);
		
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		add(buttonSave, constraints);
		
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveProperties();
					JOptionPane.showMessageDialog(ConfigSetting.this, 
							"Properties were saved successfully!");		
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(ConfigSetting.this, 
							"Error saving properties file: " + ex.getMessage());		
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		try {
			loadProperties();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "The config.properties file does not exist, default properties loaded.");
		}
		textHost.setText(configProps.getProperty("host"));
		textPort.setText(configProps.getProperty("port"));
		textUser.setText(configProps.getProperty("user"));
		textPass.setText(configProps.getProperty("pass"));
	}

	private void loadProperties() throws IOException {
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
	}
	
	private void saveProperties() throws IOException {
		configProps.setProperty("host", textHost.getText());
		configProps.setProperty("port", textPort.getText());
		configProps.setProperty("user", textUser.getText());
		configProps.setProperty("pass", textPass.getText());
                configProps.setProperty("dbname", "cheese-coin-member");
		configProps.setProperty("tracker--host", "localhost");
		configProps.setProperty("tracker--port", "3306");
		configProps.setProperty("tracker--user", "root");
		configProps.setProperty("tracker-pass", "");
		configProps.setProperty("tracker-dbname", "cheese-coin-tracker");
		OutputStream outputStream = new FileOutputStream(configFile);
		configProps.store(outputStream, "host setttings");
		outputStream.close();
	}
	
	public static void msain(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ConfigSetting();
			}
		});
	}
}