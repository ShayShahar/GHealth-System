package client.control;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import client.boundry.*;
import common.entity.*;
import common.enums.Command;
import common.enums.Result;
import common.enums.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Pair;



public class UserController implements IController, Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FXML variables
	@FXML private TextField userNameTxt;
	@FXML private PasswordField passwordFld;
	private static String username;
	private static String privilege;
	
	public void onLoginButtonClick(ActionEvent event){
				
		userNameTxt.setStyle("-fx-prompt-text-fill: gray");
		passwordFld.setStyle("-fx-prompt-text-fill: gray");

		if (userNameTxt.getText() == null || userNameTxt.getText().trim().isEmpty() || passwordFld.getText() == null || passwordFld.getText().trim().isEmpty() ){
				displayErrorMessage("Login Failed", "Some required fields are missing.");
			
			if (userNameTxt.getText() == null || userNameTxt.getText().trim().isEmpty()){
				userNameTxt.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			if (passwordFld.getText() == null || passwordFld.getText().trim().isEmpty()){
				passwordFld.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			return;
		}

		username = userNameTxt.getText();
		validateUser(userNameTxt.getText(),passwordFld.getText());
		
	}
	
	private void validateUser(String username, String password) {
		
		ArrayList<String> userDetails = new ArrayList<String>();
		userDetails.add(username);
		userDetails.add(password);
		Request request = new Request(Command.LOGIN, userDetails,User.LoginController);

		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void handleReply(Reply reply){
		
		Object result =  reply.getResult();
		
		if (result instanceof String){
			
			result = (String) result;
						
				if (((String) result).equalsIgnoreCase("Dispatcher")){
					
						privilege = "Dispatcher";
						LoginUI.hideWindow();
						DispatcherUI dispatcher = new DispatcherUI();
						dispatcher.displayDispatcherWindow();
						
						displayMessage("Login success", "Successfuly logged to G-Health System.");

				}
				
				else if (((String) result).equalsIgnoreCase("Specialist")){
					
					privilege = "Specialist";
					LoginUI.hideWindow();
					SpecialistUI specialist = new SpecialistUI();
					specialist.displaySpecialistWindow();
					
					displayMessage("Login success", "Successfuly logged to G-Health System.");
				}
				
				//add other users

		}
				
		else if (result instanceof Result){
					
			result = (Result)result;
					
			if ((Result)result == Result.WRONG_USER){
					displayErrorMessage ("Invalid username", "Check your input and try again.");
					return;
			}
			else if ((Result)result == Result.WRONG_PASSWORD){
					displayErrorMessage ("Wrong password", "You have entered a wrong password, try again.");
					return;
			}
			else if ((Result)result == Result.ALREADY_LOGIN){
					displayErrorMessage ("Login Error", "User already logged in.");
					return;
			}
			else if ((Result)result  == Result.ERROR){
					displayErrorMessage ("Login Error", "Error occured while tried to log-in.");
					return;
			}
		}
							
	}
	
	public static void displayMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url = LoginUI.class.getResource("/img/info.png");
				Dialog<Pair<String, String>> dialog = new Dialog<>();
				dialog.setTitle("INFORMATION");
				dialog.setHeaderText(title);
				dialog.setContentText(information);
				dialog.setGraphic(new ImageView(url.toString()));
				dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
				dialog.showAndWait();
				}
		});
	}
	
	public static void displayErrorMessage (String title, String information){
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url = LoginUI.class.getResource("/img/error.png");
				Dialog<Pair<String, String>> dialog = new Dialog<>();
				dialog.setTitle("ERROR");
				dialog.setHeaderText(title);
				dialog.setContentText(information);
				dialog.setGraphic(new ImageView(url.toString()));
				dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
				dialog.showAndWait();
			}
		});
	}
	
	public static String getUser(){
		return username;
	}
	
	public static String getPrivilege(){
		return privilege;
	}

	
}