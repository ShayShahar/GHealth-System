package client.control;

import java.io.IOException;
import java.util.ArrayList;
import client.boundry.DispatcherUI;
import client.boundry.SpecialistUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientDetailsController implements IController{
	

	@FXML private Button dispLogoutBtn;
	@FXML private TextField dispClientIDTxt;
	@FXML private TextArea clientDetailsField;
	@FXML private Button dispCreateClientBtn;
	
	public static String clientID;
	
	public void onLogoutButtonClick(ActionEvent event){
		
		ArrayList<String> username = new ArrayList<String>();
		username.add(UserController.getUser());
	
		Request request = new Request(Command.LOGOUT, username);

		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void onFindClientIDButtonClick(ActionEvent event){
		
		dispCreateClientBtn.setDisable(true);

		
		dispClientIDTxt.setStyle("-fx-prompt-text-fill: gray");

		if (dispClientIDTxt.getText() == null || dispClientIDTxt.getText().trim().isEmpty()){
			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
			
			if (dispClientIDTxt.getText() == null || dispClientIDTxt.getText().trim().isEmpty()){
				dispClientIDTxt.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			return;
		}

		clientID = dispClientIDTxt.getText();
		
		ArrayList<String> client = new ArrayList<String>();

		client.add(clientID);
		
		Request request = new Request(Command.FIND_CLIENT, client);

		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void onCreateAppointmentButtonClick(ActionEvent event){
		
	}
	
	public void onCancelAppointmentButtonClick(ActionEvent event){
		
	}
	
	
	public void onCreateClientButtonClick(ActionEvent event){
		
	}
	
	
	public void handleReply(Reply reply){
		 
		Object result =  reply.getResult();
		
		if (reply.getCommand() == Command.LOGOUT){
		
			if (result instanceof Result){
						
				result = (Result)result;
						
				if ((Result)result == Result.ERROR){
					ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
						System.exit(1);
				}
				else if ((Result)result == Result.LOGGEDOUT){
					
					if (ClientConnectionController.clientConnect.userPrivilege.equals("Dispatcher")){
						
						for(IUi ui : ClientConnectionController.clientConnect.userInterface)
						{
							if (ui instanceof DispatcherUI){
								ui.hideWindow();
								ClientConnectionController.clientConnect.userInterface.remove(ui);
							}
						}
					}
					
					else if (ClientConnectionController.clientConnect.userPrivilege.equals("Specialist")){
						
						for(IUi ui : ClientConnectionController.clientConnect.userInterface)
						{
							if (ui instanceof SpecialistUI){
								ui.hideWindow();
								ClientConnectionController.clientConnect.userInterface.remove(ui);
							}
						}
					}
					
					else if (ClientConnectionController.clientConnect.userPrivilege.equals("LabWorker")){
						
						for(IUi ui : ClientConnectionController.clientConnect.userInterface)
						{
							if (ui instanceof SpecialistUI){
								ui.hideWindow();
								ClientConnectionController.clientConnect.userInterface.remove(ui);
							}
						}
					}
					
					ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
					ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
				}
			}
		}
		
		else if (reply.getCommand() == Command.FIND_CLIENT){
			
			
			if (result instanceof ArrayList<?>){
				
				result = (ArrayList<?>) result;
			  @SuppressWarnings("unchecked")
			ArrayList<String> res = (ArrayList<String>) result;
			  
			  System.out.println(res.get(0));
			  System.out.println(res.get(1));
			  System.out.println(res.get(2));
			  System.out.println(res.get(3));
			  System.out.println(res.get(4));
			  System.out.println(res.get(5));
			  System.out.println(res.get(6));
				
			  
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						clientDetailsField.clear();

						clientDetailsField.appendText("Client ID: " + res.get(0) + "\n");
						}
				});
						  
			}
			
			else {
				ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Client not found", "You can add new client from the menu below.");
				
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						dispCreateClientBtn.setDisable(false);
						}
				});
				
			}
			
		}
							
	}	
	
}