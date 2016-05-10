package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import client.boundry.*;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ClientDetailsController implements IController, Initializable{
	
	//FXML Components
	/*
	@FXML private Button dispLogoutBtn;
	@FXML private Button createAppointmentBtn;
	@FXML private Button cancelAppointmentBtn;
	@FXML private Button dispCreateClientBtn;
	@FXML private Button SpViewHistoryBtn;
	@FXML private Button SpEndTreatmentBtn;
	@FXML private Button SpViewExaminationsBtn;
	@FXML private Button SpCreateRefernceBtn;
	@FXML private Button SpRecordAppointmentBtn;
	@FXML private Button SpReportMissingBtn;
	*/
	
	@FXML private JFXButton dispLogoutBtn;
	@FXML private JFXButton createAppointmentBtn;
	@FXML private JFXButton cancelAppointmentBtn;
	@FXML private JFXButton dispCreateClientBtn;
	@FXML private JFXButton SpViewHistoryBtn;
	@FXML private JFXButton SpEndTreatmentBtn;
	@FXML private JFXButton SpViewExaminationsBtn;
	@FXML private JFXButton SpCreateRefernceBtn;
	@FXML private JFXButton SpRecordAppointmentBtn;
	@FXML private JFXButton SpReportMissingBtn;
	@FXML private JFXButton removeBtn;
	
	@FXML private JFXTextField dispClientIDTxt;
	@FXML private JFXTextField fieldClientID;
	@FXML private JFXTextField fieldClientClinic;
	@FXML private JFXTextField fieldClientName;
	@FXML private JFXTextField fieldClientFamily;
	@FXML private JFXTextField fieldClientJoin;
	@FXML private JFXTextField fieldClientAddress;
	@FXML private JFXTextField fieldClientPhone;
	@FXML private JFXTextField fieldClientEmail;
	@FXML private JFXTextField SpClientIDTxt;
	
	/*
	
	@FXML private TextField dispClientIDTxt;
	@FXML private TextField fieldClientID;
	@FXML private TextField fieldClientClinic;
	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientJoin;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	@FXML private TextField SpClientIDTxt;
	//@FXML private Button removeBtn;*/

	
	//Members
	public static String clientID;
	public static int id;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createAppointmentBtn.setDisable(true);		
		cancelAppointmentBtn.setDisable(true);
		dispCreateClientBtn.setDisable(true);
		removeBtn.setDisable(true);
		
		dispClientIDTxt.setStyle("-fx-prompt-text-fill: white");
	}	
	
	
	
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
	
	private boolean validateID(String id){
		
		if (id.length() != 9){
			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Invalid Input", "ID must contain 9 digits.");
			return false;
		}
		
		for (int i = 0 ; i < id.length(); i++){
			if(id.charAt(i) < '0' || id.charAt(i) > '9'){
				ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Invalid Input", "ID must contain digits only.");
				return false;
			}
		}
		
		return true;
	}
	
	public void onFindClientIDButtonClick(ActionEvent event){
		
		
		fieldClientID.clear();
		fieldClientName.clear();
		fieldClientFamily.clear();
		fieldClientJoin.clear();
		fieldClientAddress.clear();
		fieldClientPhone.clear();
		fieldClientEmail.clear();
		fieldClientClinic.clear();
		
		
		dispCreateClientBtn.setDisable(true);

		
		dispClientIDTxt.setStyle("-fx-prompt-text-fill: gray");

		if (dispClientIDTxt.getText() == null || dispClientIDTxt.getText().trim().isEmpty()){
			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
			
			if (dispClientIDTxt.getText() == null || dispClientIDTxt.getText().trim().isEmpty()){
				dispClientIDTxt.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			return;
		}
		
		boolean check = validateID(dispClientIDTxt.getText());
		
		if (check == false){
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
	
	public void onFindClientIDButtonClickSpecialist(ActionEvent event){
		
		
		fieldClientID.clear();
		fieldClientName.clear();
		fieldClientFamily.clear();
		fieldClientJoin.clear();
		fieldClientAddress.clear();
		fieldClientPhone.clear();
		fieldClientEmail.clear();
		fieldClientClinic.clear();
		
		
		

		
		SpClientIDTxt.setStyle("-fx-prompt-text-fill: gray");

		if (SpClientIDTxt.getText() == null || SpClientIDTxt.getText().trim().isEmpty()){
			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
			
			if (SpClientIDTxt.getText() == null || SpClientIDTxt.getText().trim().isEmpty()){
				SpClientIDTxt.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			return;
		}
		
		boolean check = validateID(SpClientIDTxt.getText());
		
		if (check == false){
			return;
		}

		clientID = SpClientIDTxt.getText();
		
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
		CreateAppointmentUI create = new CreateAppointmentUI(clientID,id);
		ClientConnectionController.clientConnect.userInterface.add(create);
		
		for(IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof DispatcherUI){
				ui.hideWindow();
			}
		}
		
		create.displayUserWindow();
	}
	
	public void onCancelAppointmentButtonClick(ActionEvent event){
		CancelAppointmentUI cancel = new CancelAppointmentUI(dispClientIDTxt.getText());
		ClientConnectionController.clientConnect.userInterface.get(1).hideWindow();
		ClientConnectionController.clientConnect.userInterface.add(cancel);
		cancel.displayUserWindow();	
	}
	
	
	public void onCreateClientButtonClick(ActionEvent event){
		
		CreateClientUI create = new CreateClientUI(dispClientIDTxt.getText());
		ClientConnectionController.clientConnect.userInterface.get(1).hideWindow();
		ClientConnectionController.clientConnect.userInterface.add(create);
		create.displayUserWindow();		
	}
	
	public void onRemoveClientButtonClick(ActionEvent event){
		
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
		
		else if (reply.getCommand() == Command.FIND_CLIENT){
			
			
			if (result instanceof ArrayList<?>){
				
				result = (ArrayList<?>) result;
			  @SuppressWarnings("unchecked")
			ArrayList<String> res = (ArrayList<String>) result;
			  
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						fieldClientID.setText(res.get(0));
						id = Integer.parseInt(fieldClientID.getText());
						fieldClientClinic.setText(res.get(1));
						String[] date = res.get(2).split("-");
						fieldClientJoin.setText(date[2]+"-"+date[1]+"-"+date[0]);
						fieldClientName.setText(res.get(3));
						fieldClientFamily.setText(res.get(4));
						fieldClientAddress.setText(res.get(7));
						fieldClientPhone.setText(res.get(6));
						fieldClientEmail.setText(res.get(5));
						
						createAppointmentBtn.setDisable(false);
						cancelAppointmentBtn.setDisable(false);
						removeBtn.setDisable(false);

					}
						
				});
						  
			}
			
			else {
				ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Client not found", "You can add new client from the menu below.");
				
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						dispCreateClientBtn.setDisable(false);
						createAppointmentBtn.setDisable(true);
						cancelAppointmentBtn.setDisable(true);
						removeBtn.setDisable(true);
						}
				});
				
			}
			
		}
							
	}
	
}