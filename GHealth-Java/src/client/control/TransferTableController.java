package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.SpecialistUI;
import client.boundry.TransferDetailsUI;
import client.boundry.TransferTableUI;
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
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * TransferTableController connects between the TransferTableUI to the components logics.
 * @author asaf
 *
 */
public class TransferTableController implements IController,Initializable{

	public String specialist;
	public String clientId;
	private IUi thisUi;

	
	@FXML private TableView<MedicalFile> medicalTable;
	@FXML private TableColumn<MedicalFile, String> specialistClmn;
	@FXML private TableColumn<MedicalFile, String> TypeClmn;
	@FXML private TableColumn<MedicalFile, String> infoClmn;
	
	/**
	 * Set client's ID to the current class with one parameter
	 * @param clientId Gets client's ID
	 */
	public void setDetail(String clientId) {
		askInfoForWholeFile(clientId);
	}
	/**
	 * Set client's ID to the current class with two parameter
	 * @param clientId Gets client's ID
	 * @param specialist Gets specialist id
	 */
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
	/**
	 * MedicalFile table observable
	 * @param list Gets an ArrayList of MedicalFile objects
	 * @return ObservableList<MedicalFile> 
	 */
	public ObservableList<MedicalFile> getMedicalFile(ArrayList<MedicalFile> list){
		ObservableList<MedicalFile> medicalFiles = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			medicalFiles.add(list.get(i));
		}
		
		return medicalFiles;
	}
	/*
	 * onCloseButtonClick function is back button handler. 
	 * The function searches the last IUi instance in the UI stack and show the window.
	 * The function removes the current from the stack.
	 * @param event
	 */
	public void onCloseButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof TransferDetailsUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}
	/**
	 * This function updates the view of the medical file table elements.
	 * @param list Gets an ArrayList of MedicalFile objects
	 */
	public void onUpdateTableView(ArrayList<MedicalFile> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				medicalTable.setItems(getMedicalFile(list));
			}});
	}
	/*
	 * 	The initialize function initializes TransferTableUI screen and class members.
	 *  The function initializes tables'es columns.
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		TypeClmn.setStyle( "-fx-alignment: CENTER;");
		TypeClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
		specialistClmn.setStyle( "-fx-alignment: CENTER;");
		specialistClmn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
		infoClmn.setStyle( "-fx-alignment: CENTER;");
		infoClmn.setCellValueFactory(new PropertyValueFactory<>("info"));
		
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof TransferTableUI){
				thisUi = ui;
			}
		}	
		
		
	}
	/*
	 * The handle reply process the results of FIND_MEDFILE  requests.
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */


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
					
				/*	for (IUi ui : ClientConnectionController.clientConnect.userInterface){
						if (ui instanceof SpecialistUI){
							ui.showWindow();
						}
					}
					ClientConnectionController.clientConnect.userInterface.remove(thisUi);*/
			 }
			 
			 
		 }
	}

}

