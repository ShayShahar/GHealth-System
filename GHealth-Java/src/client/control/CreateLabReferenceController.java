package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.CreateLabReferenceUI;
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
import javafx.fxml.Initializable;

public class CreateLabReferenceController implements IController,Initializable{

	public static String user_id;
	public static int id;
	public String clientID;
		
	//Components Handlers
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientID = SpecialistDeailsController.clientID; 
		
		ArrayList<String> msg = new ArrayList<String>();
		msg.add(clientID);
		Request request = new Request(Command.GET_CLIENT_BY_CLIENT_ID,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
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
								ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
								ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
							}
						}
					}
					
					else if (ClientConnectionController.clientConnect.userPrivilege.equals("Specialist")){
						
						for(IUi ui : ClientConnectionController.clientConnect.userInterface)
						{
							if (ui instanceof SpecialistUI){
								ui.hideWindow();
								ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
								ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
							}
						}
					}
				}
			}
		}
	}
}	





