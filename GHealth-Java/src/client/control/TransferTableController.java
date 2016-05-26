package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.SpecialistUI;
import client.boundry.ViewExaminationUI;
import client.entity.Examination;
import client.entity.MedicalFile;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import fitlibrary.traverse.Evaluator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TransferTableController implements IController,Initializable{

	public String specialist;
	public String clientId;
	private IUi thisUi;

	
	@FXML private TableView<MedicalFile> medicalTable;
	@FXML private TableColumn<MedicalFile, String> specialistClmn;
	@FXML private TableColumn<MedicalFile, String> clmnTypeClmn;
	@FXML private TableColumn<MedicalFile, String> infoClmn;
	
	public void setDetails(String clientId) {
		askInfoForWholeFile(clientId);
	}

	public void setDetails(String clientId, String specialist) {		
		askInfoForSpecificFile(clientId,specialist);
	}
	
private void askInfoForWholeFile(String clientId) {
		
		ArrayList<String> list = new ArrayList<>();
		list.add(clientId);
Request requst = new Request(Command.FIND_MEDFILE,list);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(requst);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void askInfoForSpecificFile(String clientId, String specialist) {
		
		ArrayList<String> list = new ArrayList<>();
		list.add(clientId);
		list.add(specialist);
Request requst = new Request(Command.FIND_MEDFILE,list);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(requst);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<MedicalFile> getMedicalFile(ArrayList<MedicalFile> list){
		ObservableList<MedicalFile> medicalFiles = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			medicalFiles.add(list.get(i));
		}
		
		return medicalFiles;
	}
	
	public void onCloseButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof SpecialistUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}
	
	public void onUpdateTableView(ArrayList<MedicalFile> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				medicalTable.setItems(getMedicalFile(list));
			}});
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof ViewExaminationUI){
				thisUi = ui;
			}
		}	
		
		
	}

	@Override
	public void handleReply(Reply reply) {
		Object result =  reply.getResult();
		
		  if (reply.getCommand() == Command.FIND_MEDFILE){
			 
			 if (result instanceof ArrayList<?>){
					ArrayList<MedicalFile> list = (ArrayList<MedicalFile>)result;
					onUpdateTableView(list); 
			 }
			 else{
				 
				 thisUi.displayErrorMessage("Error", "There are no medical file to show!");
				
				 thisUi.hideWindow();
					
					for (IUi ui : ClientConnectionController.clientConnect.userInterface){
						if (ui instanceof SpecialistUI){
							ui.showWindow();
						}
					}
					ClientConnectionController.clientConnect.userInterface.remove(thisUi);
			 }
			 
			 
		 }
	}

}

