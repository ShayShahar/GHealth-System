package client.control;

import client.boundry.ClientConnectionUI;
import client.boundry.LoginUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

/**
 * ClientConnectionController handles the logics of the ClientConnectionUI
 * @author shays
 *
 */
public class ClientConnectionController{

	@FXML private TextField ipAddress;
	
	/**
	 * Set the default connection port
	 */
	final public static int DEFAULT_PORT = 5551;
	
	/**
	 * Set the connection IP Address
	 */
	public static String IP_ADDR;

	/**
	 * Instance of the connection to the server
	 */
	public static ClientController clientConnect;
	
	/**
	 * onConnectButtonClick handles the connect button click.
	 * The function tries to create a connection to the server.
	 * If the connection succeed, the function creates an instance of LoginUI and displays it on the screen.
	 * @param event
	 */
	public void onConnectButtonClick(ActionEvent event)
	{
		try{
			
			ipAddress.setStyle("-fx-prompt-text-fill: gray");

			if (ipAddress.getText() == null || ipAddress.getText().trim().isEmpty() ){
				ClientConnectionUI.displayErrorMessage("Connection Error", "Some required fields are missing.");
				ipAddress.setStyle("-fx-prompt-text-fill: #ffa0a0");
				return;
			}
			
			clientConnect = new ClientController(ipAddress.getText(),DEFAULT_PORT);
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
		catch (Exception e){
			ClientConnectionUI.displayErrorMessage("Connection Failed","Error occured while trying to connect to " + ipAddress.getText() + " on PORT " + DEFAULT_PORT);
		}
	  
	}
	

}
