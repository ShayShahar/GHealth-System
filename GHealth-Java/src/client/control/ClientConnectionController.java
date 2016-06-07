package client.control;

import java.io.IOException;

import client.boundry.ClientConnectionUI;
import client.boundry.LoginUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

// TODO: Auto-generated Javadoc
/**
 * ClientConnectionController handles the logics of the ClientConnectionUI.
 *
 * @author shays
 */
public class ClientConnectionController{

	/** The ip address. */
	@FXML private TextField ipAddress;
	
	/** Set the default connection port. */
	final public static int DEFAULT_PORT = 5551;
	final public static String DEFAULT_IP = "localhost";

	
	/** Set the connection IP Address. */
	public static String IP_ADDR;

	/** Instance of the connection to the server. */
	public static ClientController clientConnect;
	
	/**
	 * onConnectButtonClick handles the connect button click.
	 * The function tries to create a connection to the server.
	 * If the connection succeed, the function creates an instance of LoginUI and displays it on the screen.
	 *
	 * @param event the event
	 */
	public void onConnectButtonClick(ActionEvent event)
	{
			
			ipAddress.setStyle("-fx-prompt-text-fill: gray");

			if (ipAddress.getText() == null || ipAddress.getText().trim().isEmpty() ){
				ClientConnectionUI.displayErrorMessage("Connection Error", "Some required fields are missing.");
				ipAddress.setStyle("-fx-prompt-text-fill: #ffa0a0");
				return;
			}
			
			if (connectToServer(ipAddress.getText(),DEFAULT_PORT) == false){
				
				ClientConnectionUI.displayErrorMessage("Connection Failed","Error occured while trying to connect to " + ipAddress.getText() + " on PORT " + DEFAULT_PORT);

			}
			
			else{
				
				
				IP_ADDR = ipAddress.getText();
				  try {
				    	LoginUI login = new LoginUI();
				    	login.displayUserWindow();
						  ClientConnectionController.clientConnect.userInterface.add(login);
				    	((Node)(event.getSource())).getScene().getWindow().hide();
				    	ClientConnectionUI.displayMessage("Connection Succeed","Connected to server at " + ipAddress.getText() + " on PORT " + DEFAULT_PORT);
				    	
				    }catch (Exception ex) {
				            ex.printStackTrace();
				    }
				
			}

	}
	
	public static boolean connectToServer(String ip, int port){
		
		try {
			clientConnect = new ClientController(ip,port);
		} catch (IOException e) {
			return false;
		}		
		
		return true;
		
	}
	
	

}
