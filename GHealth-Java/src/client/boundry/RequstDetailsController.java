package client.boundry;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.control.ClientConnectionController;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.enums.Command;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class RequstDetailsController implements IController,Initializable{
	
	
	@FXML private TextField SpClientIDTxt;
	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	
	public String clientId;
	private IUi thisUi;

	public void setUser(String pName, String fName, String personId, String add, String phoneNumber, String email,
			String clientId, String userName) {
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
				

		
		
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}	





