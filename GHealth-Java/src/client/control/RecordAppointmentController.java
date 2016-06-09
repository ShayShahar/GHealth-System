package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import client.boundry.RecordAppointmentUI;
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
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
// TODO: Auto-generated Javadoc
/**
 * RecordAppointmentController connects between the RecordAppointmentUI to the components logics.
 * @author asaf
 *
 */
public class RecordAppointmentController implements IController,Initializable{
	
	/** The Sp client id txt. */
	@FXML private TextField SpClientIDTxt;
	
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
	
	/** The field record. */
	@FXML private TextArea  fieldRecord;
	
	
	/** The this ui. */
	private IUi thisUi;
	
	/** The app id. */
	private String appId;
	
	/**
	 * Set clientId ,Client Name,phone,family name, address,email to the current class scenario and text field.
	 *
	 * @param pName the name
	 * @param fName the f name
	 * @param personId the person id
	 * @param add the add
	 * @param phoneNumber the phone number
	 * @param email the email
	 * @param clientId the client id
	 * @param userId the user id
	 * @param appId the app id
	 */

	public void setUser(String pName, String fName, String personId, String add, String phoneNumber, String email,
			String clientId, int userId, String appId) {
		fieldClientName.setText(pName);
		SpClientIDTxt.setText(personId);
		fieldClientPhone.setText(phoneNumber);
		fieldClientFamily.setText(fName);
		fieldClientAddress.setText(add);
		fieldClientEmail.setText(email);
		fieldClientName.setEditable(false);
		SpClientIDTxt.setEditable(false);
		fieldClientPhone.setEditable(false);
		fieldClientFamily.setEditable(false);
		fieldClientAddress.setEditable(false);
		fieldClientEmail.setEditable(false);
		
		 this.appId = appId;
		// this.userId = userId;
		
	}
	
	/**
	 * On back button click.
	 *
	 * @param event the event
	 */
	/*
	 * onBackButtonClick function is back button handler. 
	 * The function searches the last IUi instance in the UI stack and show the window.
	 * The function removes the current from the stack.
	 * @param event
	 */
	public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof SpecialistUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
		
		
	}
	
	/**
	 * onSaveButtonClick function handles a click on save record button.
	 * The function calls insertRecord with the appointment's record text and appointment id in a list.
	 *
	 * @param event the event
	 */
	
	public void onSaveButtonClick(ActionEvent event){
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add(fieldRecord.getText());
		list.add(appId);
		insertRecord(list);

	}
	
	/**
	 * insertRecord function sends a request to the server for inserting client's appointments record.
	 * @param list Gets appointment's record text and appointment id.
	 */
	public void insertRecord(ArrayList<String> list){
		Request request = new Request(Command.INSERT_RECORD,list);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 	The initialize function initializes the RecordAppointmentUI screen and class members.
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	

		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof RecordAppointmentUI){
				thisUi = ui;
			}
		}	
		
		
	}
	
	/*
	 * The handle reply process the results of INSERT_RECORD & GET_CLIENT_BY_CLIENT_ID  requests.
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	
	/* (non-Javadoc)
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply){
		 
		Object result =  reply.getResult();
				
if (reply.getCommand() == Command.INSERT_RECORD){
			

			if ((Result)result == Result.OK ){
				
				thisUi.hideWindow();
				
				for (IUi ui : ClientConnectionController.clientConnect.userInterface){
					if (ui instanceof SpecialistUI){
						ui.showWindow();
					}
				}
				ClientConnectionController.clientConnect.userInterface.remove(thisUi);
				
				thisUi.displayMessage("Record successfully created", "Done" );
			}
}
		
		else if (reply.getCommand() == Command.GET_CLIENT_BY_CLIENT_ID){
			

			if (result instanceof ArrayList<?>){
				
				result = (ArrayList<?>) result;
				ArrayList<String> res = (ArrayList<String>) result;
			  
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						SpClientIDTxt.setText(res.get(0));
						fieldClientName.setText(res.get(1));
						fieldClientFamily.setText(res.get(2));
						fieldClientAddress.setText(res.get(5));
						fieldClientPhone.setText(res.get(4));
						fieldClientEmail.setText(res.get(3));

					}
					
				});
						  
			}
		}
		
	}


}	








