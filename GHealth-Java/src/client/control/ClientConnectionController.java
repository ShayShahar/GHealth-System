package client.control;

import client.boundry.ClientConnectionUI;
import client.boundry.LoginUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class ClientConnectionController{

	@FXML private TextField ipAddress;
	
	
	final public static int DEFAULT_PORT = 5551;
	public static String IP_ADDR;

	public static ClientController clientConnect;
	
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
				ClientConnectionUI.displayMessage("Connection Succeed","Connected to server at " + ipAddress.getText() + " on PORT " + DEFAULT_PORT);

		    try {
		    		LoginUI.displayUserWindow();
		        ((Node)(event.getSource())).getScene().getWindow().hide();
		      
		    }catch (Exception ex) {
		            ex.printStackTrace();
		    }
			
		}
		catch (Exception e){
			ClientConnectionUI.displayErrorMessage("Connection Failed","Error occured while trying to connect to " + ipAddress.getText() + " on PORT " + DEFAULT_PORT);
		}
	  
	}
	

}
