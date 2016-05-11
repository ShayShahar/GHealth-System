package client.control;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;

import java.util.logging.Logger;

import client.boundry.CreateAppointmentUI;
import client.boundry.CreateExaminationUI;
import client.boundry.DispatcherUI;
import client.boundry.LabWorkerUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class CreateExaminationController implements IController, Initializable{
	
	@FXML private TextField CErefnum;
	@FXML private ComboBox ExamTypes;
	
	
	IUi thisUi = null;
	private boolean ispicture = false;
	
	final FileChooser fileChooser = new FileChooser();
	 private Desktop desktop = Desktop.getDesktop();
	
	ObservableList<String> list = FXCollections.observableArrayList("--","Electrocardiography","physiotherapy",
			"Papilloma","sinusitis","Hearing test","orthodontics","psoriasis","Tova");
			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		ExamTypes.setItems(list);
		CErefnum.setText(ExaminationController.Curr_RefNum);
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateExaminationUI){
				thisUi = ui;
			}
		}
		
		
		
		
	}
	
	
	public void OnSendClickButton()
	{
		
	}
	
	public void OnAddPictureClickButton()
	{
		 File file = fileChooser.showOpenDialog(CreateExaminationUI.mainStage);
         if (file != null) {
             openFile(file);
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
	 
	 
	 private void openFile(File file) {
	        try {
	            desktop.open(file);
	        } catch (IOException ex) {
	            Logger.getLogger(
	            		CreateExaminationUI.class.getName()).log(
	                    Level.SEVERE, null, ex
	                );
	        }

}
}
