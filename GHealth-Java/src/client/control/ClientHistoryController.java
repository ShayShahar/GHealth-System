package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import client.boundry.ClientHistoryUI;
import client.boundry.SpecialistUI;
import client.boundry.ViewReferenceUI;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ClientHistoryController implements IController, Initializable{

	@FXML private TextField fieldClientID;
	@FXML private Button openBtn;	
	@FXML private TableView<MedicalFile> medicalTable;
	@FXML private TableColumn<MedicalFile, String> clmnDate;
	@FXML private TableColumn<MedicalFile, String> clmnSpecialization;
	@FXML private TableColumn<MedicalFile, String> clmnName;
	@FXML private TableColumn<MedicalFile, String> clmnType;
	@FXML private TableColumn<MedicalFile, String> clmnId;
	
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
	 * Handles click on Open button.
	 * Creates a new UI window depends on the type of the selected item from the table.
	 * @param event
	 */
	public void onOpenButtonClick(ActionEvent event){
		if (medicalTable.getSelectionModel().getSelectedItem().getType().equals("Appointment")){
			
					
			
		}
		else if (medicalTable.getSelectionModel().getSelectedItem().getType().equals("Reference")){
			
			ViewReferenceUI reference = new  ViewReferenceUI(medicalTable.getSelectionModel().getSelectedItem().getId(), 
					medicalTable.getSelectionModel().getSelectedItem().getName());
			ClientConnectionController.clientConnect.userInterface.add(reference);
			thisUi.hideWindow();
			reference.displayUserWindow();
		}
	}
	
	/**
	 * Handles mouse click on the client's history table.
	 * @param event
	 */
	public void onMouseClick(MouseEvent event){
		
		try{
			if (medicalTable.getSelectionModel().getSelectedItem() != null){
					openBtn.setDisable(false);
			}
	
			else{
				openBtn.setDisable(true);
			}	
			
			}catch(Exception e){
				openBtn.setDisable(true);
				}
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
		clmnId.setStyle( "-fx-alignment: CENTER;");
		
		clmnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		clmnSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));	
		clmnName.setCellValueFactory(new PropertyValueFactory<>("name"));	
		clmnType.setCellValueFactory(new PropertyValueFactory<>("type"));
		clmnId.setCellValueFactory(new PropertyValueFactory<>("id"));

		
		openBtn.setDisable(true);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void handleReply(Reply reply) {
		Object result =  reply.getResult();
		
		if (reply.getCommand() == Command.GET_MEDICAL_FILE){
			
			ArrayList<MedicalFile> info = new ArrayList<MedicalFile>(); 
			
			if (result instanceof ArrayList<?>){
				ArrayList<MedicalFile> list = (ArrayList<MedicalFile>)result;
				for(MedicalFile mf : list){
					String[] date = mf.getDate().split("-");
					mf.setDate(date[2]+"-"+date[1]+"-"+date[0]);
				}
				
				info = list;
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