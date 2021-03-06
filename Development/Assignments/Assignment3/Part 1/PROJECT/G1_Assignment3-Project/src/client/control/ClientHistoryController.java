package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import client.boundry.ClientHistoryUI;
import client.boundry.SpecialistUI;
import client.boundry.ViewAppointmentRecordUI;
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
// TODO: Auto-generated Javadoc
/**
 * ClientHistoryController connects between the ClientHistoryUI to the components logics.
 * @author asaf
 *
 */
public class ClientHistoryController implements IController, Initializable{

	/** The field client id. */
	@FXML private TextField fieldClientID;
	
	/** The open button. */
	@FXML private Button openBtn;	
	
	/** The medical table. */
	@FXML private TableView<MedicalFile> medicalTable;
	
	/** The column date. */
	@FXML private TableColumn<MedicalFile, String> clmnDate;
	
	/** The column specialization. */
	@FXML private TableColumn<MedicalFile, String> clmnSpecialization;
	
	/** The column name. */
	@FXML private TableColumn<MedicalFile, String> clmnName;
	
	/** The column type. */
	@FXML private TableColumn<MedicalFile, String> clmnType;
	
	/** The column id. */
	@FXML private TableColumn<MedicalFile, String> clmnId;
	
	/** The client id. */
	private String clientID;
	
	/** The this UI. */
	private IUi thisUi;
	
	
	/**
	 * On back button click.
	 *
	 * @param event the event
	 */
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
	 * MedicalFile table observable.
	 *
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
	 *
	 * @param event the event
	 */
	public void onOpenButtonClick(ActionEvent event){
		if (medicalTable.getSelectionModel().getSelectedItem().getType().equals("Appointment")){
			
			ViewAppointmentRecordUI record = new ViewAppointmentRecordUI(clientID,
					medicalTable.getSelectionModel().getSelectedItem().getDate(),
					medicalTable.getSelectionModel().getSelectedItem().getName(),
					medicalTable.getSelectionModel().getSelectedItem().getId());
			
			ClientConnectionController.clientConnect.userInterface.add(record);
			thisUi.hideWindow();
			record.displayUserWindow();
			
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
	 *
	 * @param event the event
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
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	/*
	 * 	The initialize function initializes the ClientHistoryUI screen and class members.
	 *  The function initializes tables'es columns.
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
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
	
	/* (non-Javadoc)
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	/*
	 * The handle reply process the results of GET_MEDICAL_FILE requests.
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
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

	/**
	 * setID send to server request to get medical file according to the client id.  
	 *
	 * @param id the new id
	 */
	
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