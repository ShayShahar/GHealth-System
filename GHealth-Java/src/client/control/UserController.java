package client.control;

import java.io.IOException;
import java.util.ArrayList;

import client.boundry.LoginUI;
import common.entity.*;
import common.enums.Command;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class UserController{
	
	
	//FXML variables
	@FXML private TextField userNameTxt;
	@FXML private TextField passwordTxt;


	
	public void onLoginButtonClick(ActionEvent event){
		
		userNameTxt.setStyle("-fx-prompt-text-fill: gray");
		passwordTxt.setStyle("-fx-prompt-text-fill: gray");

		if (userNameTxt.getText() == null || userNameTxt.getText().trim().isEmpty() || passwordTxt.getText() == null || passwordTxt.getText().trim().isEmpty() ){
		//	LoginUI.displayErrorMessage("Login Failed", "Some required fields are missing.");
			
			if (userNameTxt.getText() == null || userNameTxt.getText().trim().isEmpty()){
				userNameTxt.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			if (passwordTxt.getText() == null || passwordTxt.getText().trim().isEmpty()){
				passwordTxt.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			return;
		}
		
		validateUser(userNameTxt.getText(),passwordTxt.getText());
		
	}
	
	private void validateUser(String username, String password) {
		
		ArrayList<String> userDetails = new ArrayList<String>();
		userDetails.add(username);
		userDetails.add(password);
		Request request = new Request(Command.LOGIN, userDetails);

		try {
			clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
			}
	
	
	public void onConnectButtonClick(ActionEvent event) {
		try{
			
			ipAddress.setStyle("-fx-prompt-text-fill: gray");
			port.setStyle("-fx-prompt-text-fill: gray");

			if (ipAddress.getText() == null || ipAddress.getText().trim().isEmpty() || port.getText() == null || port.getText().trim().isEmpty() ){
		//		LoginUI.displayErrorMessage("Connection Error", "Some required fields are missing.");
				
				if (ipAddress.getText() == null || ipAddress.getText().trim().isEmpty()){
					ipAddress.setStyle("-fx-prompt-text-fill: #ffa0a0");
				}
				
				if (port.getText() == null || port.getText().trim().isEmpty()){
					port.setStyle("-fx-prompt-text-fill: #ffa0a0");
				}
				
				return;
			}

				clientConnect = new ClientController(ipAddress.getText(),Integer.parseInt(port.getText()));
		//		LoginUI.displayMessage("Connection Succeed","Connected to server at " + ipAddress.getText() + " on PORT " + port.getText());

		    try {
		    		LoginUI.displayLoginWindow();
		        ((Node)(event.getSource())).getScene().getWindow().hide();
		      
		    }catch (Exception ex) {
		            ex.printStackTrace();
		    }
			
		}
		catch (Exception e){
			//LoginUI.displayErrorMessage("Connection Failed","Error occured while trying to connect to " + ipAddress.getText() + " on PORT " + port);
		}
	  
	}
	
}