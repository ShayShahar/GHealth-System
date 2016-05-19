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

public class CreateLabReferenceController implements IController,Initializable{

	public static String user_id;
	public static int id;
	public String clientId;
	public String comments;
	public String choosedUrgency;
	public String choosedExaminationType;
	
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
	
	ObservableList<String> urgencyList = FXCollections.observableArrayList("Low","Normal","Critical");
		
	
	public void setUser(String pName,String fName,String phoneNumber,String add,String personId,String email,String clientId){
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
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		urgencyCom.setItems(urgencyList);		

		Request request = new Request(Command.GET_EXAMINATION_TYPE,null);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateLabReferenceUI){
				thisUi = ui;
			}
		}	
		
	}
	
	
	 public void onClickCreate(ActionEvent event){
		 
		 comments = commentsField.getText();
		 choosedUrgency = urgencyCom.getSelectionModel().getSelectedItem().toString();
		 choosedExaminationType = examinationTypeCom.getSelectionModel().getSelectedItem().toString();
		 
		 ArrayList<String> list = new ArrayList<String>();
		 list.add(comments);
		 list.add(choosedUrgency);
		 list.add(choosedExaminationType);
		 list.add(clientId);
		 
		 
		 
			Request request = new Request(Command.INSERT_LAB_REFRENCE,list);
			
			try {
				ClientConnectionController.clientConnect.controller = this;
				ClientConnectionController.clientConnect.sendToServer(request);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	 }
	
	public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof SpecialistUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply){
		 
		Object result =  reply.getResult();
				
		if (reply.getCommand() == Command.GET_EXAMINATION_TYPE){
			ArrayList<String> exType = (ArrayList<String>)result;

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





