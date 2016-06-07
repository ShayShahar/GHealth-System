package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.boundry.*;
import client.entity.*;
import client.interfaces.*;
import common.entity.*;
import common.enums.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

// TODO: Auto-generated Javadoc
/**
 * CreateClientController handles the logics of the CreateClient screen.
 * The class implements IController and Initializable interfaces.
 * @author shays
 *
 */
public class CreateClientController implements IController, Initializable{

	
	/** The field person id. */
	@FXML private TextField fieldPersonID;
	
	/** The field client name. */
	@FXML private TextField fieldClientName;
	
	/** The field client family. */
	@FXML private TextField fieldClientFamily;
	
	/** The field client address. */
	@FXML private TextField fieldClientAddress;
	
	/** The field client phone. */
	@FXML private TextField fieldClientPhone;
	
	/** The field client email. */
	@FXML private TextField fieldClientEmail;
	
	/** The field client clinic. */
	@FXML private TextField fieldClientClinic;
	
	/** The field client email domain. */
	@FXML private TextField fieldClientEmailDomain;
	
	/** The list phone. */
	@FXML private ComboBox<String> listPhone;
	
	
	/** The this ui. */
	private IUi thisUi;
	
	public String returnMsg;



	/** The list. */
	ObservableList<String> list = FXCollections.observableArrayList("--","050","052","054","058","03","04","08");


	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	/*
	 * 	The initialize function initializes the CreateClientUI screen and class members.
	 *  The function initializes the phones number prefixes.
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listPhone.setItems(list);		
		listPhone.getSelectionModel().selectFirst();
				
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateClientUI){
				thisUi = ui;
			}
		}
	}

	/* (non-Javadoc)
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	/*
	 * The handle reply process the results of CREATE_CLIENT request.
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	@Override
	public void handleReply(Reply reply) {

		Result result = (Result) reply.getResult();
		
		if ((Result)result == Result.ERROR){
			thisUi.displayErrorMessage ("Create Client Error", "Error occured while tried to create a new client.");
		}
		
		else if ((Result)result == Result.PERSON_EXISTS){
			returnMsg = "ClientFound";
			thisUi.displayErrorMessage ("Create Client Error", "A worker cannot be a client! check the entered id.");
		}
		
		else if ((Result)result == Result.FAILED){
			returnMsg = "Failed";
		}
		
		else {
			returnMsg = "MedicalFileCreated";
			thisUi.hideWindow();
			for (IUi ui : ClientConnectionController.clientConnect.userInterface){
				if (ui instanceof DispatcherUI){
					ui.showWindow();
				}
			}
			
			thisUi.displayMessage("Client Created", "New client registered successfuly to IHealth.");
		}
	}

	/**
	 * Sets the user.
	 *
	 * @param user_id the new user
	 */
	public void setUser(String user_id){
		fieldPersonID.setText(user_id);
		fieldPersonID.setEditable(false);
	}
	
	 /**
 	 * onCreateClientButtonClick function is create client button handler.
 	 * The function checks all the input fields.
 	 * If all the input fields are correct, the function call createClient function with the person's details.
 	 *
 	 * @param event the event
 	 */
	public void onCreateClientButtonClick(ActionEvent event){
				
		fieldClientName.setStyle("-fx-prompt-text-fill: gray");
		fieldClientFamily.setStyle("-fx-prompt-text-fill: gray");
		fieldClientAddress.setStyle("-fx-prompt-text-fill: gray");
		fieldClientPhone.setStyle("-fx-prompt-text-fill: gray");
		fieldClientClinic.setStyle("-fx-prompt-text-fill: gray");
		fieldClientEmail.setStyle("-fx-prompt-text-fill: gray");
		fieldClientEmailDomain.setStyle("-fx-prompt-text-fill: gray");

		
		if (fieldClientName.getText() == null    || fieldClientName.getText().trim().isEmpty()    ||
				fieldClientFamily.getText() == null  || fieldClientFamily.getText().trim().isEmpty()  ||
			  fieldClientAddress.getText() == null || fieldClientAddress.getText().trim().isEmpty() ||
				fieldClientPhone.getText() == null   || fieldClientPhone.getText().trim().isEmpty()   ||
				fieldClientClinic.getText() == null   || fieldClientClinic.getText().trim().isEmpty() ||
				listPhone.getSelectionModel().getSelectedItem().toString().equals("--"))  {
			
		  thisUi.displayErrorMessage("Create Error", "Missing required fields. Check your input and try again.");

			
			if (fieldClientName.getText() == null    || fieldClientName.getText().trim().isEmpty())
					fieldClientName.setStyle("-fx-prompt-text-fill: #ffa0a0");
			
			if (fieldClientFamily.getText() == null    || fieldClientFamily.getText().trim().isEmpty())
				fieldClientFamily.setStyle("-fx-prompt-text-fill: #ffa0a0");
			
			if (fieldClientAddress.getText() == null    || fieldClientAddress.getText().trim().isEmpty())
				fieldClientAddress.setStyle("-fx-prompt-text-fill: #ffa0a0");
			
			if (fieldClientPhone.getText() == null    || fieldClientPhone.getText().trim().isEmpty())
				fieldClientPhone.setStyle("-fx-prompt-text-fill: #ffa0a0");
			
			if (fieldClientClinic.getText() == null    || fieldClientClinic.getText().trim().isEmpty())
				fieldClientClinic.setStyle("-fx-prompt-text-fill: #ffa0a0");
			
			return;
			
		}
		
		if ((!(fieldClientEmailDomain.getText() == null   || fieldClientEmailDomain.getText().trim().isEmpty()) &&
				(fieldClientEmail.getText() == null   || (fieldClientEmail.getText().trim().isEmpty()))) ||
				((fieldClientEmailDomain.getText() == null   || fieldClientEmailDomain.getText().trim().isEmpty()) &&
						!(fieldClientEmail.getText() == null   || (fieldClientEmail.getText().trim().isEmpty())))){
			
			thisUi.displayErrorMessage("Invalid Email", "Check your input and try again.");
			
			fieldClientEmailDomain.setStyle("-fx-prompt-text-fill: #ffa0a0");
			fieldClientEmail.setStyle("-fx-prompt-text-fill: #ffa0a0");

			return;
		}
			
				
		for (int i = 0 ; i < fieldClientPhone.getText().length(); i++){
			if(fieldClientPhone.getText().charAt(i) < '0' || fieldClientPhone.getText().charAt(i) > '9'){
				thisUi.displayErrorMessage("Invalid Input", "ID must contain digits only.");
				return;
			}
		}
				
		if (fieldClientEmailDomain.getText() == null   || fieldClientEmailDomain.getText().trim().isEmpty()){
			
			createClient(fieldClientName.getText(), fieldClientFamily.getText(),
					fieldPersonID.getText(), fieldClientAddress.getText(),
					listPhone.getSelectionModel().getSelectedItem().toString()+"-"+fieldClientPhone.getText(), fieldClientClinic.getText(), "none");
		}
		else {
			createClient(fieldClientName.getText(), fieldClientFamily.getText(),
					fieldPersonID.getText(), fieldClientAddress.getText(),
					listPhone.getSelectionModel().getSelectedItem().toString()+"-"+fieldClientPhone.getText(), fieldClientClinic.getText(), fieldClientEmail.getText() +"@"+fieldClientEmailDomain.getText());
		}

	}
	
	/**
	 * createClient creates a client instance and send  a CREATE_CLIENT request to the server.
	 * @param name Gets person's name
	 * @param familyName Gets person's family name
	 * @param personID Gets person's ID
	 * @param address Gets person's address
	 * @param phone Gets person's phone number
	 * @param clinic Gets person's clinic
	 * @param email Gets person's email
	 */
	public void createClient(String name, String familyName, String personID, String address, String phone, String clinic, String email){
		
		Client client = new Client(name, familyName,
				personID,	address,
				phone, clinic, email);
		
		Request request = new Request(Command.CREATE_CLIENT,client);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * On back button click.
	 *
	 * @param event the event
	 */
	/*
	 * onBackButtonClick function is back button handler. 
	 * The function search the last IUi instance in the UI stack and show the window.
	 * The function remove the current from the stack.
	 * @param event
	 */
	public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof DispatcherUI){
				ui.showWindow();
			}
		}
		
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);

	} 
	
}
