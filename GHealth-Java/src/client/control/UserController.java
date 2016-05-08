package client.control;

import java.io.IOException;
import java.util.ArrayList;
import client.boundry.*;
import client.interfaces.IController;
import common.entity.*;
import common.enums.Command;
import common.enums.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class UserController implements IController{
		

	//FXML variables
	@FXML private TextField userNameTxt;
	@FXML private PasswordField passwordFld;
	private static String username;
	
	public void onLoginButtonClick(ActionEvent event){
				
		userNameTxt.setStyle("-fx-prompt-text-fill: gray");
		passwordFld.setStyle("-fx-prompt-text-fill: gray");

		if (userNameTxt.getText() == null || userNameTxt.getText().trim().isEmpty() || passwordFld.getText() == null || passwordFld.getText().trim().isEmpty() ){
			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Login Failed", "Some required fields are missing.");
			
			if (userNameTxt.getText() == null || userNameTxt.getText().trim().isEmpty()){
				userNameTxt.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			if (passwordFld.getText() == null || passwordFld.getText().trim().isEmpty()){
				passwordFld.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			return;
		}

		username = userNameTxt.getText();
		ClientConnectionController.clientConnect.userName = username;
		validateUser(userNameTxt.getText(),passwordFld.getText());
		
	}
	
	private void validateUser(String username, String password) {
		
		ArrayList<String> userDetails = new ArrayList<String>();
		userDetails.add(username);
		userDetails.add(password);
		Request request = new Request(Command.LOGIN, userDetails);

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
					
						ClientConnectionController.clientConnect.userPrivilege = "Dispatcher";
						ClientConnectionController.clientConnect.userInterface.get(0).hideWindow();
						DispatcherUI dispatcher = new DispatcherUI();
						dispatcher.displayUserWindow();
					  ClientConnectionController.clientConnect.userInterface.add(dispatcher);

					  ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Login success", "Successfuly logged to G-Health System.");

				}
				
				else if (((String) result).equalsIgnoreCase("Specialist")){
					
					ClientConnectionController.clientConnect.userPrivilege = "Specialist";
					ClientConnectionController.clientConnect.userInterface.get(0).hideWindow();
					SpecialistUI specialist = new SpecialistUI();
					specialist.displayUserWindow();
					ClientConnectionController.clientConnect.userInterface.add(specialist);
					
					ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Login success", "Successfuly logged to G-Health System.");
				}
				
        else if (((String) result).equalsIgnoreCase("LabWorker")){
					
					ClientConnectionController.clientConnect.userPrivilege =  "LabWorker";
					ClientConnectionController.clientConnect.userInterface.get(0).hideWindow();
					LabWorkerUI LabWorker = new LabWorkerUI();
					LabWorker.displayUserWindow();
					ClientConnectionController.clientConnect.userInterface.add(LabWorker);
					
					ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Login success", "Successfuly logged to G-Health System.");
				}
				
				//add other users

		}
				
		else if (result instanceof Result){
					
			result = (Result)result;
					
			if ((Result)result == Result.WRONG_USER){
				ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage ("Invalid username", "Check your input and try again.");
					return;
			}
			else if ((Result)result == Result.WRONG_PASSWORD){
				ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage ("Wrong password", "You have entered a wrong password, try again.");
					return;
			}
			else if ((Result)result == Result.ALREADY_LOGIN){
				ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage ("Login Error", "User already logged in.");
					return;
			}
			else if ((Result)result  == Result.ERROR){
				ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage ("Login Error", "Error occured while tried to log-in.");
					return;
			}
		}
							
	}

	
	public static String getUser(){
		return username;
	}
	
	
}