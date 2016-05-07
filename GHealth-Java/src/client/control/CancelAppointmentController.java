package client.control;

import java.net.URL;
import java.util.ResourceBundle;

import client.boundry.CancelAppointmentUI;
import client.boundry.CreateAppointmentUI;
import client.boundry.DispatcherUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CancelAppointmentController implements IController, Initializable{

	@FXML private TextField fieldPersonID;
	
	@SuppressWarnings("unused")
	private String clientID;
	private IUi thisUi;
	
	public void setUser(String id){
		clientID = id;
		fieldPersonID.setText(id);
	}
	
	public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof DispatcherUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}
	
	public void onMouseClick(MouseEvent event){
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CancelAppointmentUI){
				thisUi = ui;
			}
		}		
	}

	@Override
	public void handleReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}
	
}