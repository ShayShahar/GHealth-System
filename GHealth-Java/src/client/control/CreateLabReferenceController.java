package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.DispatcherUI;
import client.boundry.SpecialistUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateLabReferenceController implements IController,Initializable{

	public static String user_id;
	public static int id;
	public String clientID;
	
	@FXML private TextField SpClientIDTxt;
	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	@FXML private ComboBox<String> examinationTypeCom;
	@FXML private ComboBox<String> urgencyCom;
	
	

	ObservableList<String> examinationList = FXCollections.observableArrayList("Allergology","Anaesthetics",
			"Biological hematology","Cardiology",
			"Clinical chemistry","Craniofacial surgery","Dermatology","Endocrinology",
			"Gastroenterology","General hematology","General Practice",
			"Geriatrics","Immunology","Infectious diseases","Internal medicine","Microbiology",
			"Nephrology", "Neurology","Neurosurgery","Orthopaedics","Pathology",
			"Psychiatry","Radiology","Stomatology","Urology","Venereology");
	
	ObservableList<String> urgencyList = FXCollections.observableArrayList("Low","Normal","Critical");
		
	//Components Handlers
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
	
	public void setUser(String pName,String fName,String phoneNumber,String add,String personId,String email){
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
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientID = SpecialistDeailsController.clientID; 
		
		examinationTypeCom.setItems(examinationList);		
		//examinationTypeCom.getSelectionModel().selectFirst();
		
		urgencyCom.setItems(urgencyList);		
		//urgencyCom.getSelectionModel().selectFirst();
		
	//	ArrayList<String> msg = new ArrayList<String>();

		Request request = new Request(Command.GET_EXAMINATION_TYPE,null);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	



	
	
	public void handleReply(Reply reply){
		 
		Object result =  reply.getResult();
		
		if (reply.getCommand() == Command.LOGOUT){
		
			if (result instanceof Result){
						
				result = (Result)result;
						
				if ((Result)result == Result.ERROR){
					ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
						System.exit(1);
				}
				else if ((Result)result == Result.LOGGEDOUT){
					
					if (ClientConnectionController.clientConnect.userPrivilege.equals("Dispatcher")){
						
						for(IUi ui : ClientConnectionController.clientConnect.userInterface)
						{
							if (ui instanceof DispatcherUI){
								ui.hideWindow();
								ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
								ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
							}
						}
					}
					
					else if (ClientConnectionController.clientConnect.userPrivilege.equals("Specialist")){
						
						for(IUi ui : ClientConnectionController.clientConnect.userInterface)
						{
							if (ui instanceof SpecialistUI){
								ui.hideWindow();
								ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
								ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
							}
						}
					}
					else if (reply.getCommand() == Command.GET_CLIENT_BY_CLIENT_ID){
						

						if (result instanceof ArrayList<?>){
							
							result = (ArrayList<?>) result;
							@SuppressWarnings("unchecked")
							ArrayList<String> res = (ArrayList<String>) result;
						  
							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									System.out.println(res.get(0));
									SpClientIDTxt.setText(res.get(0));
									fieldClientName.setText(res.get(1));
									fieldClientFamily.setText(res.get(2));
									fieldClientAddress.setText(res.get(5));
									fieldClientPhone.setText(res.get(4));
									fieldClientEmail.setText(res.get(3));

								}
								
							});
									  
						}
									
									
									
								}
				}
			}
		}
	}
}	





