package client.control;

import java.net.URL;
import java.util.ResourceBundle;
import client.boundry.SpecialistUI;
import client.boundry.TransferDetailsUI;
import client.boundry.TransferTableUI;
import client.interfaces.IUi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
// TODO: Auto-generated Javadoc
/**
 * TransferDetailsController connects between the TransferDetailsUI to the components logics.
 * @author asaf
 *
 */
public class TransferDetailsController implements Initializable{
	
	
	/** The Sp client id txt. */
	@FXML private TextField SpClientIDTxt;
	
	/** The field client name. */
	@FXML private TextField fieldClientName;
	
	/** The field client family. */
	@FXML private TextField fieldClientFamily;
	
	/** The field client address. */
	@FXML private TextField fieldClientAddress;
	
	/** The field client phone. */
	@FXML private TextField fieldClientPhone;
	
	/** The field client email. */
	@FXML private TextField fieldClientEmail;
	
	/** The specifications label. */
	@FXML private Label specificationsLabel;
	
	/** The transfer btn. */
	@FXML private Button transferBtn;
	
	/** The specifications com. */
	@FXML private ComboBox<String> specificationsCom;
	
	/** The is whole file. */
	public Boolean isWholeFile;
	
	/** The client id. */
	public String clientId;
	
	/** The this ui. */
	private IUi thisUi;
	

	/** The specifications list. */
	ObservableList<String> specificationsList = FXCollections.observableArrayList("Allergology","Anaesthetics",
			"Biological hematology","Cardiology","Child psychiatry","Clinical biology",
			"Clinical chemistry","Clinical neurophysiology","Craniofacial surgery",
			"Craniofacial surgery","Dermato-venerology","Dermatology","Endocrinology",
			"Gastro-enterologic surgery","Gastroenterology","General hematology","General Practice",
			"Geriatrics","Immunology","Infectious diseases","Internal medicine","Microbiology",
			"Nephrology", "Neuro-psychiatry","Neurology","Neurosurgery","Orthopaedics","Pathology",
			"Psychiatry","Radiology","Stomatology","Urology","Venereology");
	
	/**
	 * Set clientId ,Client Name,phone,family name, address,email to the current class scenario and text field.
	 *
	 * @param pName the name
	 * @param fName the f name
	 * @param personId the person id
	 * @param add the add
	 * @param phoneNumber the phone number
	 * @param email the email
	 * @param clientId the client id
	 * @param userName the user name
	 */
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
	
	/*
	 * onBackButtonClick function is back button handler. 
	 * The function searches the last IUi instance in the UI stack and show the window.
	 * The function removes the current from the stack.
	 * @param event
	 */
	
/**
	 * On back button click.
	 *
	 * @param event the event
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
 * onSpecificFileClick function handles a click on specific file button.
 * The function allowed the combo box to choose specialization.
 *
 * @param event the event
 */ 

public void onSpecificFileClick(ActionEvent event){
	isWholeFile = false;
	transferBtn.setDisable(true);
	specificationsCom.setDisable(false);
	specificationsCom.setItems(specificationsList);
}

/**
 * The function allowed the combo box .
 *
 * @param event the event
 */ 

public void onMouseClick(ActionEvent event){

	transferBtn.setDisable(false);
}

/**
 * onTransferButtonClick function is Transfer button handler. 
 * The function searches specialist if there is any and call to transferFile.
 *
 * @param event the event
 */
public void onTransferButtonClick(ActionEvent event){
	String specialist = new String();
	try{
	 specialist = specificationsCom.getSelectionModel().getSelectedItem().toString();
	}
	catch(Exception e){
		specialist = null;
	}
	transferFile(clientId,specialist);
	
}

/**
 * transferFile searching medical file form health maintenance organization on client for Specific file or the whole file.
 *
 * @param clientId the client id
 * @param specialist the specialist
 */

public void transferFile(String clientId, String specialist){
	
	if(isWholeFile){
		TransferTableUI create = new TransferTableUI(clientId,isWholeFile);
		ClientConnectionController.clientConnect.userInterface.add(create);
		
		for(IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof SpecialistUI){
				ui.hideWindow();
			}
		}
		
		create.displayUserWindow();
	}else{

		TransferTableUI create = new TransferTableUI(clientId,specialist);
		ClientConnectionController.clientConnect.userInterface.add(create);
		
		for(IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof SpecialistUI){
				ui.hideWindow();
			}
		}
		
		create.displayUserWindow();
	}
}

/**
 * The function handle the selection of medical file.
 *
 * @param event the event
 */ 

public void onWholeFileClick(ActionEvent event){
	
	isWholeFile = true;
	specificationsCom.setDisable(true);
	transferBtn.setDisable(false);
	specificationsCom.setItems(null);
}
	
/*
 * 	The initialize function initializes TransferDetailsUI screen and class members.
 *  The function initializes tables'es columns.
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */

/* (non-Javadoc)
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */
@Override
public void initialize(URL location, ResourceBundle resources) {
	
	
	
	for (IUi ui : ClientConnectionController.clientConnect.userInterface){
		if (ui instanceof TransferDetailsUI){
			thisUi = ui;
		}
	}		
}






	




}

