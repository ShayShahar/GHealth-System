package client.control;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ExaminationController implements IController{
	
	//FXML Components
	
	@FXML private DatePicker Edate;
	@FXML private TextField Esid;
	@FXML private TextField Ecid;
	@FXML private TextField fieldReferenceNum,fieldClientID,fieldSpecielistID,fieldCode,fieldDate,fieldUrgency,fieldComments,fieldStatus;
	
	
	
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
        	Request request = new Request(Command.FIND_REFERENCE_BY_SID_CID_DATE, (Object)reference);
        	//
 
        	//Send the request to server
        	try {
    			ClientConnectionController.clientConnect.controller = this;
    			ClientConnectionController.clientConnect.sendToServer(request);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
        	
        	
        	
        	//System.out.println("sid: "+Esid.getText()+" cid: "+Ecid.getText()+"date:"+date);
        	
        	
        	
        }


		@Override
		public void handleReply(Reply reply) {
			
			
			Object result =  reply.getResult();
			if (reply.getCommand() == Command.LOGOUT)
				logoutCheck(result);
			
			else if (reply.getCommand() == Command.FIND_REFERENCE_BY_SID_CID_DATE);
			{
				//System.out.println("111");
				/*
				if ((Result)result == Result.ERROR){
					ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
						System.exit(1);
				}
				*/
				
				
				Reference reference = new Reference();
				reference = (Reference)reply.getResult();
				
				
				//fieldReferenceNum,fieldClientID,fieldSpecielistID,fieldCode,fieldDate,fieldUrgency,fieldComments,fieldStatus;
				//SetText to the fields
				fieldComments.setText(reference.getComments());
				fieldReferenceNum.setText(Integer.toString(reference.getRefNum()));
				fieldClientID.setText(Integer.toString(reference.getCId()));
				fieldSpecielistID.setText(Integer.toString(reference.getSId()));
				fieldCode.setText(Integer.toString(reference.getCode()));
				fieldUrgency.setText(reference.getUrgency());
				fieldStatus.setText(Integer.toString(reference.getStatus()));
				
				
		         
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
									ClientConnectionController.clientConnect.userInterface.remove(ui);
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
									ClientConnectionController.clientConnect.userInterface.remove(ui);
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
									ClientConnectionController.clientConnect.userInterface.remove(ui);
									ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
									ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
								}
							}
						}
						
					}
				}
			
		}
}