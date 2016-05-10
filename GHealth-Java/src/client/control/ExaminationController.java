package client.control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import client.boundry.CreateAppointmentUI;
import client.boundry.CreateExaminationUI;
import client.boundry.DispatcherUI;
import client.boundry.LabWorkerUI;
import client.boundry.SpecialistUI;
import client.entity.Reference;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ExaminationController implements IController{
	
	//FXML Components 
	
	//LabWorkerUI components
	
	@FXML private DatePicker Edate;
	@FXML private TextField Esid;
	@FXML private TextField Ecid;
	@FXML private TextField fieldReferenceNum,fieldClientID,fieldSpecielistID,fieldCode,fieldDate,fieldUrgency,fieldComments,fieldStatus,Ereference_number;
	@FXML private CheckBox checkbox1;
	@FXML private Button ExamBtn;
	
	
	//CreateExaminationUI components
	
	
	
	
	//class variables
	
	private Reference Curr_Ref;
	static String Curr_RefNum;

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
        
        
        public void OnSearchButtonClick(ActionEvent event)
        {
        	Request request;
        	
        	
        	if(!checkFields())
        	{
        		ExamBtn.setDisable(true);
        		return;
        	
        	}
        	
        	if(!checkbox1.isSelected())  //check the CheckBox
        	{
        	//Get Values cid,sid,date from textfields and date picker
        	
        	LocalDate ldate;
        	//*cast to date from the date picker
        	ldate = Edate.getValue();
        	Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.systemDefault()));
        	Date date = Date.from(instant);
        	//*
        	
        	Reference reference = new Reference();
        	reference.setDate(date);
        	reference.setCId(Integer.parseInt(Ecid.getText()));
        	reference.setSId(Integer.parseInt(Esid.getText()));
        	request = new Request(Command.FIND_REFERENCE_BY_SID_CID_DATE, (Object)reference);
        	//
        	}
        	
        	else
        	{
        	request = new Request(Command.FIND_REFERENCE_BY_REFNUM,(Object)Ereference_number.getText());
        	}
        	
        	//Send the request to server
        	try {
    			ClientConnectionController.clientConnect.controller = this;
    			ClientConnectionController.clientConnect.sendToServer(request);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		       	
        	
        }
        
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
        
      
        
        public void OnCheckBoxCheck()
        {
        	if(checkbox1.isSelected())
        	{   
        		//Ref_Num on
        		Ereference_number.setDisable(false);
        		
        		//cid,sid,date off
        		Edate.setDisable(true);
        		Esid.setDisable(true);
        		Ecid.setDisable(true);
        	}
        	else
        	{
        		//Ref_Num off
        		Ereference_number.setDisable(true);
        		
        		//cid,sid,date off on
        		Edate.setDisable(false);
        		Esid.setDisable(false);
        		Ecid.setDisable(false);
        	}
        }


		@Override
		public void handleReply(Reply reply) {
			
			
			Object result =  reply.getResult();
			System.out.println("0");
			
			if (reply.getCommand() == Command.LOGOUT)
			{
				System.out.println("1");
				logoutCheck(result);
			}
			
			else if (reply.getCommand() == Command.FIND_REFERENCE_BY_SID_CID_DATE || reply.getCommand() == Command.FIND_REFERENCE_BY_REFNUM )
			{
				System.out.println("2");
				
				if(result instanceof Result)
				{
					
				if ((Result)result == Result.ERROR){
					System.out.println("3");
					ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
						System.exit(1);
				}
				
				
				 if((Result)result == Result.CLIENT_NOT_FOUND)
				{
					System.out.println("4");
					ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("","Reference Not Found!");
					ExamBtn.setDisable(true);
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
			System.out.println("5.1");
				Reference reference = new Reference();
				reference = (Reference)reply.getResult();
				/////////////////////////////////////Curr_Ref.equals(reference);
				ExaminationController.Curr_RefNum =(Integer.toString(reference.getRefNum()));
				System.out.println("5");
				//SetText to the fields
				fieldComments.setText(reference.getComments());
				fieldReferenceNum.setText(Integer.toString(reference.getRefNum()));
				fieldClientID.setText(Integer.toString(reference.getCId()));
				fieldSpecielistID.setText(Integer.toString(reference.getSId()));
				fieldCode.setText(Integer.toString(reference.getCode()));
				fieldUrgency.setText(reference.getUrgency());
				fieldStatus.setText(Integer.toString(reference.getStatus()));
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");        //set the format of the date
				fieldDate.setText(df.format(reference.getDate().getTime()));
				ExamBtn.setDisable(false);
				}
				System.out.println("6");
			}
				
		}
		
		
		
		
		
		
		
		
		
		public void logoutCheck(Object result)
		{
				
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
									//ClientConnectionController.clientConnect.userInterface.remove(ui);
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
									//ClientConnectionController.clientConnect.userInterface.remove(ui);
									ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
									ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
								}
							}
						}
						
						else if (ClientConnectionController.clientConnect.userPrivilege.equals("LabWorker")){
							
							for(IUi ui : ClientConnectionController.clientConnect.userInterface)
							{
								if (ui instanceof LabWorkerUI){
									ui.hideWindow();
									//ClientConnectionController.clientConnect.userInterface.remove(ui);
									ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
									ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
								}
							}
						}
						
					}
				}
			
		}
		
		
		public boolean checkFields()
		{
			boolean check = true;
			
			//put all Textfiend to be gray style
			Ecid.setStyle("-fx-prompt-text-fill: gray");
			Esid.setStyle("-fx-prompt-text-fill: gray");
			Edate.setStyle("-fx-prompt-text-fill: gray");
			Ereference_number.setStyle("-fx-prompt-text-fill: gray");
			
			
			if(!checkbox1.isSelected())  //check id cid,sid,date are ok
			{
			
			//check if there is empty Textfield
				
        	if (Ecid.getText() == null || Ecid.getText().trim().isEmpty()){
    			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
    			Ecid.clear();
    			Ecid.setStyle("-fx-prompt-text-fill: #ffa0a0");
    			check = false;
    		}
        	
        	if (Esid.getText() == null || Esid.getText().trim().isEmpty()){
        		if(check)
    			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
        		Esid.clear();
    			Esid.setStyle("-fx-prompt-text-fill: #ffa0a0");
    			check = false;
    			
    		}
		    
        	
        	if (Edate.getValue() == null){
        		if(check)
    			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
    			Edate.setStyle("-fx-prompt-text-fill: #ffa0a0");
    			check = false;
    		}
        	
        	
        	
        	//check if the input is 9 digits and numbers only
        	
        	if ((!Ecid.getText().matches("[0-9]+")) || Ecid.getText().length() != 9)
			{
        		if(check)
				ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "id must contain only 9 digits number");
        		Ecid.clear();
				Ecid.setStyle("-fx-prompt-text-fill: #ffa0a0");
				check = false;
			}
        	
        	if ((!Esid.getText().matches("[0-9]+")) || Esid.getText().length() != 9)
			{
        		if(check)
				ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "id must contain only 9 digits number");
        		Esid.clear();
				Esid.setStyle("-fx-prompt-text-fill: #ffa0a0");
				check = false;
			}
			
			}
			
			
			
			else  //check the reference only input
			{
				
				//check if there is empty Textfield
				
				if (Ereference_number.getText() == null || Ereference_number.getText().trim().isEmpty()){
					if(check)
	    			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
					Ereference_number.clear();
	    			Ereference_number.setStyle("-fx-prompt-text-fill: #ffa0a0");
	    			check = false;
	    		}
				
				//check if the input is 9 digits and numbers only
				
				if ((!Ereference_number.getText().matches("[0-9]+")) || Ereference_number.getText().length() != 9)
				{
					if(check)
					ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "id must contain only 9 digits number");
					Ereference_number.clear();
					Ereference_number.setStyle("-fx-prompt-text-fill: #ffa0a0");
					check = false;
				}
				
				
			}
			
			return check;
			
			
			
		}
}