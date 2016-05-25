package client.control;

import java.net.URL;
import java.util.ResourceBundle;

import client.boundry.SpecialistUI;
import client.boundry.TransferDetailsUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.enums.Command;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferDetailsController implements IController,Initializable{
	
	
	@FXML private TextField SpClientIDTxt;
	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	
	@FXML private ComboBox<String> specificationsCom;
	
	public String clientId;
	private IUi thisUi;
	
	ObservableList<String> specificationsList = FXCollections.observableArrayList("Allergology","Anaesthetics",
			"Biological hematology","Cardiology","Child psychiatry","Clinical biology",
			"Clinical chemistry","Clinical neurophysiology","Craniofacial surgery",
			"Craniofacial surgery","Dermato-venerology","Dermatology","Endocrinology",
			"Gastro-enterologic surgery","Gastroenterology","General hematology","General Practice",
			"Geriatrics","Immunology","Infectious diseases","Internal medicine","Microbiology",
			"Nephrology", "Neuro-psychiatry","Neurology","Neurosurgery","Orthopaedics","Pathology",
			"Psychiatry","Radiology","Stomatology","Urology","Venereology");

	public void setUser(String pName, String fName, String personId, String add, String phoneNumber, String email,
			String clientId, String userName) {
		fieldClientName.setText(pName);
		SpClientIDTxt.setText(personId);
		fieldClientPhone.setText(phoneNumber);
		fieldClientFamily.setText(fName);
		fieldClientAddress.setText(add);
		fieldClientEmail.setText(email);
		fieldClientName.setEditable(false);
		SpClientIDTxt.setEditable(false);
		fieldClientPhone.setEditable(false);
		fieldClientFamily.setEditable(false);
		fieldClientAddress.setEditable(false);
		fieldClientEmail.setEditable(false);
		this.clientId = clientId;
		
	}
public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof SpecialistUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}

public void onSpecificFileClick(ActionEvent event){
	
	specificationsCom.setDisable(false);
	specificationsCom.setItems(specificationsList);
}
	
	

	
	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply){
		 
		Object result =  reply.getResult();
				

		
		
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof TransferDetailsUI){
				thisUi = ui;
			}
		}		
	}

}	





