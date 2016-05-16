package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import client.boundry.*;
import client.entity.Hour;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SpClientDeailsController implements IController, Initializable{

		
		//FXML Components

		@FXML private Button SpViewHistoryBtn;
		@FXML private Button SpEndTreatmentBtn;
		@FXML private Button SpViewExaminationsBtn;
		@FXML private Button SpCreateRefernceBtn;
		@FXML private Button SpRecordAppointmentBtn;
		@FXML private Button SpReportMissingBtn;
		@FXML private TextField fieldClientID;
		@FXML private TextField fieldClientClinic;
		@FXML private TextField fieldClientName;
		@FXML private TextField fieldClientFamily;
		@FXML private TextField fieldClientJoin;
		@FXML private TextField fieldClientAddress;
		@FXML private TextField fieldClientPhone;
		@FXML private TextField fieldClientEmail;
		@FXML private TextField SpClientIDTxt;
		@FXML private TableView<Hour> tabelAppointment;
		@FXML private TableColumn<Hour, String> timeClmn;
		
		//Members
		public static Integer userId;
		public static String clientID;
		public static String userName = ClientConnectionController.clientConnect.userName;
		
		
	  private HashMap<Integer,String> getHourByInteger = new HashMap<Integer,String>();
	  private HashMap<String,Integer> getIntegerByHour = new HashMap<String,Integer>();
	
		
		public ObservableList<Hour> getHours(ArrayList<Hour> list){
			ObservableList<Hour> hours = FXCollections.observableArrayList();
			
			for (int i=0 ; i < list.size(); i++){
				hours.add(list.get(i));
			}
			
			return hours;
		}
		
		public void onUpdateTableView(ArrayList<Hour> list){

			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					tabelAppointment.setItems(getHours(list));
				}});
		}
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
						
			//initialize hash tables
			getHourByInteger.put(1, "8:00");
			getHourByInteger.put(2, "8:30");
			getHourByInteger.put(3, "9:00");
			getHourByInteger.put(4, "9:30");
			getHourByInteger.put(5, "10:00");
			getHourByInteger.put(6, "10:30");
			getHourByInteger.put(7, "11:00");
			getHourByInteger.put(8, "11:30");
			getHourByInteger.put(9, "12:00");
			getHourByInteger.put(10, "12:30");
			getHourByInteger.put(11, "13:00");
			getHourByInteger.put(12, "13:30");
			getHourByInteger.put(13, "14:00");
			getHourByInteger.put(14, "14:30");
			getHourByInteger.put(15, "15:00");
			getHourByInteger.put(16, "15:30");
			getHourByInteger.put(17, "16:00");
			getHourByInteger.put(18, "16:30");
			
			getIntegerByHour.put("8:00",1);
			getIntegerByHour.put("8:30",2);
			getIntegerByHour.put("9:00",3);
			getIntegerByHour.put("9:30",4);
			getIntegerByHour.put("10:00",5);
			getIntegerByHour.put("10:30",6);
			getIntegerByHour.put("11:00",7);
			getIntegerByHour.put("11:30",8);
			getIntegerByHour.put("12:00",9);
			getIntegerByHour.put("12:30",10);
			getIntegerByHour.put("13:00",11);
			getIntegerByHour.put("13:30",12);
			getIntegerByHour.put("14:00",13);
			getIntegerByHour.put("14:30",14);
			getIntegerByHour.put("15:00",15);
			getIntegerByHour.put("15:30",16);
			getIntegerByHour.put("16:00",17);
			getIntegerByHour.put("16:30",18);
				
			ArrayList<String> user = new ArrayList<String>();
			user.add(ClientConnectionController.clientConnect.userName);
			
			/*
			Request requst = new Request(Command.FIND_USERID_BY_USERNAME,user);
			
			try {
				ClientConnectionController.clientConnect.controller = this;
				ClientConnectionController.clientConnect.sendToServer(requst);
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			timeClmn.setStyle( "-fx-alignment: CENTER;");
			timeClmn.setCellValueFactory(new PropertyValueFactory<>("hour"));
			
		//	ArrayList<String> list = new ArrayList<String>();
		//	list.add(Integer.toString(userId));
			Request requst2 = new Request(Command.FIND_TODAY_APPOINTMENT,user);
			
			try {
				ClientConnectionController.clientConnect.controller = this;
				ClientConnectionController.clientConnect.sendToServer(requst2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public void onMouseClick(MouseEvent event){
	
			try{
					if (tabelAppointment.getSelectionModel().getSelectedItem().getHour() != null){
					int hour = getIntegerByHour.get(tabelAppointment.getSelectionModel().getSelectedItem().getHour());
						
				
						ArrayList<String> msg = new ArrayList<String>();
						msg.add(Integer.toString(hour));
						msg.add(userName);   
					System.out.println(userId);
						Request request = new Request(Command.GET_CLIENT_BY_APPOINTMET,msg);
						
						try {
							ClientConnectionController.clientConnect.controller = this;
							ClientConnectionController.clientConnect.sendToServer(request);
						} 
						catch (IOException e) {
							e.printStackTrace();
						}					
					}
					
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}
		
		
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
		
		private boolean validateID(String id){
			
			if (id.length() != 9){
				ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Invalid Input", "ID must contain 9 digits.");
				return false;
			}
			
			for (int i = 0 ; i < id.length(); i++){
				if(id.charAt(i) < '0' || id.charAt(i) > '9'){
					ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Invalid Input", "ID must contain digits only.");
					return false;
				}
			}
			
			return true;
		}
		
	/*	public void onFindClientIDButtonClickSpecialist(ActionEvent event){
			
			
			fieldClientID.clear();
			fieldClientName.clear();
			fieldClientFamily.clear();
			fieldClientJoin.clear();
			fieldClientAddress.clear();
			fieldClientPhone.clear();
			fieldClientEmail.clear();
			fieldClientClinic.clear();
			
			
			
			SpClientIDTxt.setStyle("-fx-prompt-text-fill: gray");

			if (SpClientIDTxt.getText() == null || SpClientIDTxt.getText().trim().isEmpty()){
				ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
				
				if (SpClientIDTxt.getText() == null || SpClientIDTxt.getText().trim().isEmpty()){
					SpClientIDTxt.setStyle("-fx-prompt-text-fill: #ffa0a0");
				}
				
				return;
			}
			
			boolean check = validateID(SpClientIDTxt.getText());
			
			if (check == false){
				return;
			}

			clientID = SpClientIDTxt.getText();
			
			ArrayList<String> client = new ArrayList<String>();

			client.add(clientID);
			
			Request request = new Request(Command.FIND_CLIENT, client);

			try {
				ClientConnectionController.clientConnect.controller = this;
				ClientConnectionController.clientConnect.sendToServer(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		*/
		public void onCreateLabReferenceButtonClick(ActionEvent event){
			CreateLabReferenceUI create = new CreateLabReferenceUI();
			ClientConnectionController.clientConnect.userInterface.add(create);
			
			for(IUi ui : ClientConnectionController.clientConnect.userInterface){
				if (ui instanceof SpecialistUI){
					ui.hideWindow();
				}
			}
			
			create.displayUserWindow();
		}
		

		@SuppressWarnings("unchecked")
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
					}
				}
			}
			
		
			else if (reply.getCommand() == Command.FIND_USERID_BY_USERNAME){
				userId = (Integer)result;
				System.out.println(userId);
				
			}
			
			else if (reply.getCommand() == Command.FIND_CLIENT){
				
				
				if (result instanceof ArrayList<?>){
					
					result = (ArrayList<?>) result;
					ArrayList<String> res = (ArrayList<String>) result;
				  
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							fieldClientID.setText(res.get(0));
						//	id = Integer.parseInt(fieldClientID.getText());
							fieldClientClinic.setText(res.get(1));
							String[] date = res.get(3).split("-");
							fieldClientJoin.setText(date[2]+"-"+date[1]+"-"+date[0]);
							fieldClientName.setText(res.get(4));
							fieldClientFamily.setText(res.get(5));
							fieldClientAddress.setText(res.get(8));
							fieldClientPhone.setText(res.get(7));
							fieldClientEmail.setText(res.get(6));
							SpViewHistoryBtn.setDisable(false);
							SpEndTreatmentBtn.setDisable(false);
							SpCreateRefernceBtn.setDisable(false);
							SpViewExaminationsBtn.setDisable(false);
							SpRecordAppointmentBtn.setDisable(false);
							SpReportMissingBtn.setDisable(false);
							
							

						}
						
					});
							  
				}
				
				
				else {
					ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Client not found", "You can add new client from the menu below.");
					
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							SpViewHistoryBtn.setDisable(false);
							SpEndTreatmentBtn.setDisable(true);
							SpCreateRefernceBtn.setDisable(true);
							}
					});
					
				}
				
			}
			
			else if (reply.getCommand() == Command.FIND_TODAY_APPOINTMENT){
				
				
				if (result instanceof ArrayList<?>){
					ArrayList<Integer> hoursRes = (ArrayList<Integer>)result;
					ArrayList<Hour> hours = new ArrayList<Hour>();
					for (int i = 0 ; i<hoursRes.size(); i++){
						Hour hour  = new Hour();
						hour.setHour(getHourByInteger.get(hoursRes.get(i)));
						hours.add(hour);
					}
					onUpdateTableView(hours);
				}
				
				
				
			}
else if (reply.getCommand() == Command.GET_CLIENT_BY_APPOINTMET){
				

	if (result instanceof ArrayList<?>){
		
		result = (ArrayList<?>) result;
		ArrayList<String> res = (ArrayList<String>) result;
	  
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				fieldClientID.setText(res.get(1));
			//	id = Integer.parseInt(fieldClientID.getText());
				fieldClientClinic.setText(res.get(2));
				//String[] date = res.get(3).split("-");
			//	fieldClientJoin.setText(res.get(9));
				SpClientIDTxt.setText(res.get(0));
				fieldClientName.setText(res.get(4));
				fieldClientFamily.setText(res.get(5));
				fieldClientAddress.setText(res.get(8));
				fieldClientPhone.setText(res.get(7));
				fieldClientEmail.setText(res.get(6));
				
				SpViewHistoryBtn.setDisable(false);
				SpEndTreatmentBtn.setDisable(false);
				SpCreateRefernceBtn.setDisable(false);
				SpViewExaminationsBtn.setDisable(false);
				SpRecordAppointmentBtn.setDisable(false);
				SpReportMissingBtn.setDisable(false);
				
				

			}
			
		});
				  
	}
				
				
				
			}
								
		}

		
		
	}

