package client.control;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import client.boundry.CreateExaminationUI;
import client.boundry.LabWorkerUI;
import client.entity.Reference;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExaminationController implements IController, Initializable{
	
	
		@FXML private DatePicker datePicker;
		@FXML private TextField sidField;
		@FXML private TextField cidField;
		@FXML private TextField fieldReferenceNum;
		@FXML private TextField fieldClientID; 
		@FXML private TextField fieldSpecielistID;
		@FXML private TextField fieldCode; 
		@FXML private TextField fieldDate; 
		@FXML private TextField fieldUrgency; 
		@FXML private TextField fieldStatus; 
		@FXML private TextField reference_number;
		@FXML private TextField fieldType;
		@FXML private TextArea fieldComments;
		@FXML private CheckBox checkbox1;
		@FXML private Button examBtn;	
		
		static Reference currentReference;
		static String currentReferenceNumber;
		private IUi thisUi;

		/**
		 * onLogoutButtonClick function is Logout button handler.
		 * Sends a logout request for the logged in user to the server.
		 * @param event
		 */
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
	
		/**
	 	*Search Reference by sid,cid,date or by reference number
	 	* @param event
  	*/
	  public void OnSearchButtonClick(ActionEvent event){
		  
		  Request request;
		  
	    if(!checkFields()){
	    	examBtn.setDisable(true);
	     return;
	        	
	    }
	        	
	    if(!checkbox1.isSelected()){
			//Get Values cid,sid,date from textfields and date picker
			
			LocalDate ldate;
			//cast to date from the date picker
			ldate = datePicker.getValue();
			Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
	
			
			Reference reference = new Reference();
			reference.setDate(date);
			reference.setCId(Integer.parseInt(cidField.getText()));
			reference.setSId(Integer.parseInt(sidField.getText()));
			request = new Request(Command.FIND_REFERENCE_BY_SID_CID_DATE, (Object)reference);
	
	    }
	    
	    else
	    request = new Request(Command.FIND_REFERENCE_BY_REFNUM,(Object)reference_number.getText());
	    
	    
	    //Send the request to server
	    try {
	    	
	    	ClientConnectionController.clientConnect.controller = this;
	    	ClientConnectionController.clientConnect.sendToServer(request);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	           	
	        	
	}
        
	 /**
	  * Open Create Examination Window
	  */
	 public void OnCreateExaminationClick()
	 {
	 	
	 	CreateExaminationUI create = new CreateExaminationUI();
		ClientConnectionController.clientConnect.userInterface.add(create);
		
		for(IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof LabWorkerUI){
				ui.hideWindow();
			}
		}
		
		create.displayUserWindow();
		 
	   
		
	 }
 

	 /**
	  * choose between cid,sid,date and reference number
	  * disable the textfields that we dont need and enable the needed
	  */
	 public void OnCheckBoxCheck()
	 {
	 	if(checkbox1.isSelected())
	 	{   
	 		//Ref_Num on
	 		reference_number.setDisable(false);
	 		
	 		//cid,sid,date off
	 		datePicker.setDisable(true);
	 		sidField.setDisable(true);
	 		cidField.setDisable(true);
	 	}
	 	else
	 	{
	 		//Ref_Num off
	 		reference_number.setDisable(true);
	 		
	 		//cid,sid,date off on
	 		datePicker.setDisable(false);
	 		sidField.setDisable(false);
	 		cidField.setDisable(false);
	 	}
	 }
 
 
	 /**
	  * handle reply from the server
	  * get the reference from the server
	  */
	@Override
	public void handleReply(Reply reply) {
	
	
		Object result =  reply.getResult();
	
	
		if (reply.getCommand() == Command.LOGOUT){
			
		if (result instanceof Result){
					
			result = (Result)result;
					
			if ((Result)result == Result.ERROR){
				ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
					System.exit(1);
			}
			else if ((Result)result == Result.LOGGEDOUT){
							thisUi.hideWindow();	
						 	ClientConnectionController.clientConnect.userInterface.remove(thisUi);
							ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
							ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
					}
			}
	}
			
			else if (reply.getCommand() == Command.FIND_REFERENCE_BY_SID_CID_DATE || 
					reply.getCommand() == Command.FIND_REFERENCE_BY_REFNUM ){
						
					if(result instanceof Result){
						
						if ((Result)result == Result.ERROR){
							
							thisUi.displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
								System.exit(1);
						}
					
				
						if((Result)result == Result.FAILED || (Result)result == Result.CLIENT_NOT_FOUND){
							
							thisUi.displayErrorMessage ("Error","Reference not found.");
							examBtn.setDisable(true);
							fieldComments.clear();
							fieldReferenceNum.clear();
							fieldClientID.clear();
							fieldSpecielistID.clear();
							fieldCode.clear();
							fieldUrgency.clear();
							fieldStatus.clear();
							fieldDate.clear();
						}
				 
				 
				 
				}
				
				else { 
			
				Reference reference = new Reference();
				reference = (Reference)reply.getResult();
				ExaminationController.currentReference=reference;
				
				//SetText to the fields
				fieldComments.setText(reference.getComments());
				fieldReferenceNum.setText(Integer.toString(reference.getRefNum()));
				fieldClientID.setText(Integer.toString(reference.getCId()));
				fieldSpecielistID.setText(Integer.toString(reference.getSId()));
				fieldCode.setText(Integer.toString(reference.getCode()));
				fieldUrgency.setText(reference.getUrgency());
				
				
				if(reference.getStatus() == 0)
					fieldStatus.setText("Not Checked");
				else fieldStatus.setText("Checked");
				
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");        //set the format of the date
				fieldDate.setText(df.format(reference.getDate().getTime()));
				fieldType.setText(reference.getType());
				examBtn.setDisable(false);
				}
				
			}
				
		}




	/*
	 * check if the input in the field are fine or not
	 * and display massage accordingly
	 */
	
	public boolean checkFields()  //check if the user insert legal values to the fields
	{
		boolean check = true;
		
		//put all Textfiend to be gray style
		cidField.setStyle("-fx-prompt-text-fill: gray");
		sidField.setStyle("-fx-prompt-text-fill: gray");
		datePicker.setStyle("-fx-prompt-text-fill: gray");
		reference_number.setStyle("-fx-prompt-text-fill: gray");
		
		
		if(!checkbox1.isSelected())  //check id cid,sid,date are ok
		{
		
		//check if there is empty Textfield
			
	 	if (cidField.getText() == null || cidField.getText().trim().isEmpty()){
	 		thisUi.displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
			cidField.clear();
			cidField.setStyle("-fx-prompt-text-fill: #ffa0a0");
			check = false;
		}
	 	
	 	if (sidField.getText() == null || sidField.getText().trim().isEmpty()){
	 		if(check)
	 			thisUi.displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
	 		sidField.clear();
			sidField.setStyle("-fx-prompt-text-fill: #ffa0a0");
			check = false;
			
		}
	    
	 	
	 	if (datePicker.getValue() == null){
	 		if(check)
	 			thisUi.displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
			datePicker.setStyle("-fx-prompt-text-fill: #ffa0a0");
			check = false;
		}
	 	
	 	
	 	
	 	//check if the input is 9 digits and numbers only
	 
	 	if ((!cidField.getText().matches("[0-9]+")))
		{
	 		if(check)
	 			thisUi.displayErrorMessage("Search Error", "id must contain only 9 digits number");
	 		cidField.clear();
			cidField.setStyle("-fx-prompt-text-fill: #ffa0a0");
			check = false;
		}
	 	
	 	if ((!sidField.getText().matches("[0-9]+")))
		{
	 		if(check)
	 			thisUi.displayErrorMessage("Search Error", "id must contain only 9 digits number");
	 		sidField.clear();
			sidField.setStyle("-fx-prompt-text-fill: #ffa0a0");
			check = false;
		}
		
		}
		
		
		
		else  //check the reference only input
		{
			
			//check if there is empty Textfield
			
			if (reference_number.getText() == null || reference_number.getText().trim().isEmpty()){
				if(check)
					thisUi.displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
				reference_number.clear();
	 			reference_number.setStyle("-fx-prompt-text-fill: #ffa0a0");
	 			check = false;
	 		}
			
			//check if the input is 9 digits and numbers only
			
			if ((!reference_number.getText().matches("[0-9]+")))      //Ereference_number.getText().length() != 9
			{
				if(check)
				thisUi.displayErrorMessage("Search Error", "id must contain only 9 digits number");
				reference_number.clear();
				reference_number.setStyle("-fx-prompt-text-fill: #ffa0a0");
				check = false;
			}
			
			
		}
		
		return check;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof LabWorkerUI){
				thisUi = ui;
			}
		}			
	
	}
	
}
