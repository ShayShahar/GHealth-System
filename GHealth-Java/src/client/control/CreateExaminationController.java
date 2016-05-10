package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.CreateAppointmentUI;
import client.boundry.CreateExaminationUI;
import client.boundry.DispatcherUI;
import client.boundry.LabWorkerUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class CreateExaminationController implements IController, Initializable{
	
	@FXML private TextField CErefnum;
	
	
	IUi thisUi = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		CErefnum.setText(ExaminationController.Curr_RefNum);
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateExaminationUI){
				thisUi = ui;
			}
		}
		
	}

	@Override
	public void handleReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}
	
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
	 
	 public void onBackButtonClick()
	 {
		 thisUi.hideWindow();
			
			for (IUi ui : ClientConnectionController.clientConnect.userInterface){
				if (ui instanceof LabWorkerUI){
						ui.showWindow();
				}
			}
			
			ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	 }

}
