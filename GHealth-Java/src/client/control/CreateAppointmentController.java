package client.control;

import java.net.URL;
import java.util.ResourceBundle;

import client.boundry.CreateAppointmentUI;
import client.boundry.DispatcherUI;
import client.interfaces.IUi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateAppointmentController implements Initializable{
	
	@FXML private ComboBox<String> listSpecialization;
	@FXML private TextField fieldPersonID;

	
	//Members
	IUi thisUi = null;


	ObservableList<String> list = FXCollections.observableArrayList("Allergology","Anaesthetics",
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
		
		
}