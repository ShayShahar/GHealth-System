package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.boundry.CreateLabReferenceUI;
import client.boundry.RecordAppointmentUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Request;
import common.enums.Command;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class RecordAppointmentController implements IController,Initializable{
	
	@FXML private TextField SpClientIDTxt;
	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	
	
	
	private IUi thisUi;

	public void setUser(String pName, String fName, String personId, String add, String phoneNumber, String email,
			String clientId, int userId) {
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
		
		// this.clientId = clientId;
		// this.userId = userId;
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	

		Request request = new Request(Command.GET_EXAMINATION_TYPE,null);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		for (IUi ui : RecordAppointmentController.clientConnect.userInterface){
			if (ui instanceof RecordAppointmentUI){
				thisUi = ui;
			}
		}	
		
	}
	
	

}
