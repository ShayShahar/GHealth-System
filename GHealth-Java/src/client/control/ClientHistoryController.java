package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import client.boundry.ClientHistoryUI;
import client.boundry.SpecialistUI;
import client.entity.MedicalFile;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientHistoryController implements IController, Initializable{

	@FXML private TextField fieldClientID;	
	@FXML private TableView<MedicalFile> medicalTable;
	@FXML private TableColumn<MedicalFile, String> clmnDate;
	@FXML private TableColumn<MedicalFile, String> clmnSpecialization;
	@FXML private TableColumn<MedicalFile, String> clmnName;
	@FXML private TableColumn<MedicalFile, String> clmnType;
	
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
	
	/**
	 * MedicalFile table observable
	 * @param list Gets an ArrayList of MedicalFile objects
	 * @return ObservableList<MedicalFile> 
	 */
	public ObservableList<MedicalFile> getSpecialist(ArrayList<MedicalFile> list){
		ObservableList<MedicalFile> info = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			info.add(list.get(i));
		}
		
		return info;
	}
	
	/**
	 * This function updates the view of the medical file table elements.
	 * @param list Gets an ArrayList of MedicalFile objects
	 */
	public void onUpdateTableView(ArrayList<MedicalFile> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				medicalTable.setItems(getSpecialist(list));
			}});
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof ClientHistoryUI){
				thisUi = ui;
			}
		}		
		
		clmnDate.setStyle( "-fx-alignment: CENTER;");
		clmnSpecialization.setStyle( "-fx-alignment: CENTER;");
		clmnName.setStyle( "-fx-alignment: CENTER;");
		clmnType.setStyle( "-fx-alignment: CENTER;");
		
		clmnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		clmnSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));	
		clmnName.setCellValueFactory(new PropertyValueFactory<>("name"));	
		clmnType.setCellValueFactory(new PropertyValueFactory<>("type"));
		
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void handleReply(Reply reply) {
		Object result =  reply.getResult();
		
		if (reply.getCommand() == Command.GET_MEDICAL_FILE){
			
			ArrayList<MedicalFile> info = new ArrayList<MedicalFile>(); 
			
			if (result instanceof ArrayList<?>){
				ArrayList<Object> list = (ArrayList<Object>)result;
				
				for (int i = 0 ; i < list.size(); i++){
					ArrayList<String> strings = (ArrayList<String>)list.get(i);
					MedicalFile file = new MedicalFile();
					String dateInString = strings.get(0);
					String[] date = dateInString.split("-");
					String setDate = date[2]+"-"+date[1]+"-"+date[0];	
					file.setDate(setDate);
					file.setSpecialization(strings.get(1));
					file.setName(strings.get(2) + " " + strings.get(3));
					file.setType(strings.get(4));
					info.add(file);
				}
			}
			
			onUpdateTableView(info);
			
		}
		
	}

	public void setID(String id) {
		
		clientID = id;
		fieldClientID.setText(id);
		ArrayList<String> msg = new ArrayList<String>();
		msg.add(clientID);
		
		Request request = new Request(Command.GET_MEDICAL_FILE,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
}