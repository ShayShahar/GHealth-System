package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.CreateAppointmentUI;
import client.boundry.DispatcherUI;
import client.entity.Specialist;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CreateAppointmentController implements IController, Initializable{
	
	@FXML private ComboBox<String> listSpecialization;
	@FXML private TextField fieldPersonID;
	@FXML private TableView<Specialist> tabelSpecialists;
	@FXML private TableColumn<Specialist, String> idClmn;
	@FXML private TableColumn<Specialist, String> nameClmn;
	@FXML private TableColumn<Specialist, String> familyClmn;
	@FXML private TableColumn<Specialist, String> branchClmn;
	@FXML private TableColumn<Specialist, String> addrClmn;
	@FXML private HBox hboxDate;
	
	//Members
	IUi thisUi = null;


	ObservableList<String> list = FXCollections.observableArrayList("--","Allergology","Anaesthetics",
			"Biological hematology","Cardiology","Child psychiatry","Clinical biology",
			"Clinical chemistry","Clinical neurophysiology","Craniofacial surgery",
			"Craniofacial surgery","Dermato-venerology","Dermatology","Endocrinology",
			"Gastro-enterologic surgery","Gastroenterology","General hematology","General Practice",
			"Geriatrics","Immunology","Infectious diseases","Internal medicine","Microbiology",
			"Nephrology", "Neuro-psychiatry","Neurology","Neurosurgery","Orthopaedics","Pathology",
			"Psychiatry","Radiology","Stomatology","Urology","Venereology");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		listSpecialization.setItems(list);		
		listSpecialization.getSelectionModel().selectFirst();
				
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateAppointmentUI){
				thisUi = ui;
			}
		}
		idClmn.setStyle( "-fx-alignment: CENTER;");
		nameClmn.setStyle( "-fx-alignment: CENTER;");
		familyClmn.setStyle( "-fx-alignment: CENTER;");
		branchClmn.setStyle( "-fx-alignment: CENTER;");
		addrClmn.setStyle( "-fx-alignment: CENTER;");

		
		idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameClmn.setCellValueFactory(new PropertyValueFactory<>("name"));	
		familyClmn.setCellValueFactory(new PropertyValueFactory<>("familyName"));	
		branchClmn.setCellValueFactory(new PropertyValueFactory<>("branchName"));
		addrClmn.setCellValueFactory(new PropertyValueFactory<>("branchAddress"));	
		
	}
	
	public ObservableList<Specialist> getSpecialist(ArrayList<Specialist> list){
		ObservableList<Specialist> specialists = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			specialists.add(list.get(i));
		}
		
		return specialists;
	}
	
	public void onUpdateTableView(ArrayList<Specialist> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				tabelSpecialists.setItems(getSpecialist(list));
			}});
	}
	
	public void setUser(String user_id){
		fieldPersonID.setText(user_id);
		fieldPersonID.setEditable(false);
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
	
	
	
	public void onSearchSpecialistButtonClick(ActionEvent event){
		
		hboxDate.setDisable(true);
		
		if (listSpecialization.getSelectionModel().getSelectedItem().toString().equals("--")){
			thisUi.displayErrorMessage("Invalid Input", "Please choose a specialization from the list.");
			return;
		}
		
		ArrayList<String> msg = new ArrayList<String>();
		msg.add(listSpecialization.getSelectionModel().getSelectedItem().toString());
		
		Request request = new Request(Command.FIND_SPECIALIST,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void onMouseClick(MouseEvent event){
		
		try{
				if (tabelSpecialists.getSelectionModel().getSelectedItem().getId() != null){
					hboxDate.setDisable(false);
					System.out.println(tabelSpecialists.getSelectionModel().getSelectedItem().getId());
				}
				
				else{
					hboxDate.setDisable(true);
				}	
		}catch(Exception e){
			hboxDate.setDisable(true);
		}
		
	}
	
	@Override
	public void handleReply(Reply reply) {

		Object result =  reply.getResult();
		
		if (reply.getCommand() == Command.FIND_SPECIALIST){
						
			ArrayList<Specialist> specialistList = new ArrayList<Specialist>();
			if (result instanceof ArrayList<?>){
				
				@SuppressWarnings("unchecked")
				ArrayList<Object> list = (ArrayList<Object>)result;
	
				for (int i = 0 ; i < list.size(); i++){

					@SuppressWarnings("unchecked")
					ArrayList<String> strings = (ArrayList<String>)(list.get(i));
					Specialist specialist = new Specialist(strings.get(0),strings.get(2),strings.get(3),strings.get(1),strings.get(4),strings.get(5));					
					specialistList.add(specialist);
				}
		 }
			
			onUpdateTableView(specialistList);	
	 }
	}
	
	
}