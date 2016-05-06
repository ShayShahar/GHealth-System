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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateClientController implements IController, Initializable{

	
	//FXML Components

	@FXML private TextField fieldPersonID;
	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	@FXML private TextField fieldClientClinic;
	@FXML private TextField fieldClientEmailDomain;
	@FXML private ComboBox<String> listPhone;


	ObservableList<String> list = FXCollections.observableArrayList("--","050","052","054","058","03","04","08");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listPhone.setItems(list);		
		listPhone.getSelectionModel().selectFirst();
	}

	@Override
	public void handleReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void setUser(String user_id){
		fieldPersonID.setText(user_id);
		fieldPersonID.setEditable(false);
	}
	
	public void onCreateClientButtonClick(ActionEvent event){
		
		IUi thisUi = ClientConnectionController.clientConnect.userInterface.get(0);
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateClientUI){
				thisUi = ui;
			}
		}
		
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
		
		Client client;
		
		if (fieldClientEmailDomain.getText() == null   || fieldClientEmailDomain.getText().trim().isEmpty()){
			client = new Client(fieldClientName.getText(), fieldClientFamily.getText(),
					fieldPersonID.getText(), fieldClientAddress.getText(),
					listPhone.getSelectionModel().getSelectedItem().toString()+"-"+fieldClientPhone.getText(), fieldClientClinic.getText(), "none");
		}
		else {
			client = new Client(fieldClientName.getText(), fieldClientFamily.getText(),
					fieldPersonID.getText(), fieldClientAddress.getText(),
					listPhone.getSelectionModel().getSelectedItem().toString()+"-"+fieldClientPhone.getText(), fieldClientClinic.getText(), fieldClientEmail.getText() +"@"+fieldClientEmailDomain.getText());
		}

		Request request = new Request(Command.CREATE_CLIENT,client);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
	public void onBackButtonClick(ActionEvent event){
		
		for(IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateClientUI){
				ui.hideWindow();
			}
		}
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof DispatcherUI){
				ui.showWindow();
			}
		}

		
	} 
	
}
