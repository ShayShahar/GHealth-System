package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.ClientHistoryUI;
import client.boundry.SpecialistUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ClientHistoryController implements IController, Initializable{

	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	@FXML private TextField fieldPersonID;
	
	@SuppressWarnings("unused")
	private String clientID;
	private IUi thisUi;
	
	
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof ClientHistoryUI){
				thisUi = ui;
			}
		}		
		
		ArrayList<String> msg = new ArrayList<String>();
		
		
		Request request = new Request(Command.FIND_AVAILABLE_HOURS,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}

	@Override
	public void handleReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}

	public void setID(String id) {
		clientID = id;
	}
	
}