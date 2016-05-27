package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.CreateLabReferenceUI;
import client.boundry.SpecialistUI;
import client.interfaces.*;
import common.entity.*;
import common.enums.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * CreateLabReferenceController connects between the CreateLabReferenceUI to the components logics.
 * @author asaf
 *
 */
public class CreateLabReferenceController implements IController,Initializable{

	public int userId;
	public static int id;
	public String clientId;
	public String comments;
	public String choosedUrgency;
	public String choosedExaminationType;
	public String userName;
	
	@FXML private TextField SpClientIDTxt;
	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	@FXML private ComboBox<String> examinationTypeCom;
	@FXML private ComboBox<String> urgencyCom;
	@FXML private TextArea commentsField;
	
	
	private IUi thisUi;
	/**
	 * MedicalFile table observable
	 * @param list Gets an ArrayList of String objects
	 * @return ObservableList<String> 
	 */
	ObservableList<String> urgencyList = FXCollections.observableArrayList("LOW","NORMAL","CRITICAL");
	/**
	 * Set clientId ,Client Name,phone,family name, address,email to the current class scenario and text field
	 * @param id Gets client's ID
	 * @param fieldClientName Gets personal Name
	 * @param SpClientIDTxt Gets person Id
	 * @param fieldClientFamily Gets Family name
	 * @param fieldClientAddress Gets adderss
	 * @param fieldClientEmail Gets email
	 */
	public void setUser(String pName,String fName,String personId,String add,String phoneNumber,String email,String clientId, String userName){
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
		
		this.clientId = clientId;
		this.userName = userName;
	}
	
	/*
	 * 	The initialize function initializes the CreateLabReferenceUI screen and class members.
	 *  The function initializes combobox
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		urgencyCom.setItems(urgencyList);	
		getExaminationType(ClientConnectionController.clientConnect.userName);
		
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateLabReferenceUI){
				thisUi = ui;
			}
		}	
		
	}
	/**
	 * Bring examination type from the DB to comboBox.
	 * @param userName by using specialist user name
	 */
	
	public void getExaminationType(String userName){
		ArrayList<String> list = new ArrayList<String>();
        list.add(userName);
        
		
		Request request = new Request(Command.GET_EXAMINATION_TYPE,list);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Handles click on Create button.
	 * Calling to insertLabRefernceToDb by clicking Create button.
	 * @param event
	 */
	
	 public void onClickCreate(ActionEvent event){
	 
		 insertLabRefernceToDb();
	
	 }
	 
	 /**
		 * Creates a new UI window depends on the type of the selected item from the ComboBoxes.
		 * Insert the lab reference to db by taking them from the GUI.
		 * @param event
		 */
	 
	 public void insertLabRefernceToDb(){
		 comments = commentsField.getText();
		 choosedUrgency = urgencyCom.getSelectionModel().getSelectedItem().toString();
		 choosedExaminationType = examinationTypeCom.getSelectionModel().getSelectedItem().toString();
		 
		 ArrayList<String> list = new ArrayList<String>();
		 list.add(comments);
		 list.add(choosedUrgency);
		 list.add(choosedExaminationType);
		 list.add(clientId);
		 list.add(Integer.toString(userId));
		 
		 
		 
			Request request = new Request(Command.INSERT_LAB_REFRENCE,list);
			
			try {
				ClientConnectionController.clientConnect.controller = this;
				ClientConnectionController.clientConnect.sendToServer(request);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 /**
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
	
	

	/*
	 * The handle reply process the results of GET_EXAMINATION_TYPE & INSERT_LAB_REFRENCE & GET_CLIENT_BY_CLIENT_ID  requests.
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply){
		 
		Object result =  reply.getResult();
				
		if (reply.getCommand() == Command.GET_EXAMINATION_TYPE){
			ArrayList<String> exType = (ArrayList<String>)result;

			userId = Integer.parseInt(exType.get(0));
			exType.remove(0);
			
			@SuppressWarnings("rawtypes")
			
			ObservableList examinationList = FXCollections.observableList(exType);
			examinationTypeCom.setItems(examinationList);
			
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					examinationTypeCom.setItems(examinationList);

				}
				
			});
		}
		else if (reply.getCommand() == Command.INSERT_LAB_REFRENCE){
			
			if (result instanceof ArrayList<?>){
				result = (ArrayList<Integer>) result;
				ArrayList<Integer> res = (ArrayList<Integer>) result;
				
				thisUi.hideWindow();
				
				for (IUi ui : ClientConnectionController.clientConnect.userInterface){
					if (ui instanceof SpecialistUI){
						ui.showWindow();
					}
				}
				ClientConnectionController.clientConnect.userInterface.remove(thisUi);
				
				thisUi.displayMessage("Reference successfully created", "The reference number is: " + res.get(0));
			}
			
		}
		
		else if (reply.getCommand() == Command.GET_CLIENT_BY_CLIENT_ID){
			

			if (result instanceof ArrayList<?>){
				
				result = (ArrayList<?>) result;
				ArrayList<String> res = (ArrayList<String>) result;
			  
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						System.out.println(res.get(0));
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





