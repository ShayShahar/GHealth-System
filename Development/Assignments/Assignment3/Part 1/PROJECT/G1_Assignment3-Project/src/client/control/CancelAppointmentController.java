package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import client.boundry.CancelAppointmentUI;
import client.boundry.DispatcherUI;
import client.entity.Appointment;
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

// TODO: Auto-generated Javadoc
/**
 * CancelAppointmentController connects between the CancelAppointmentUI to the components logics.
 * @author shays
 *
 */
public class CancelAppointmentController implements IController, Initializable{

	/** The field person id. */
	@FXML private TextField fieldPersonID;
	
	/** The cancel button. */
	@FXML private Button cancelBtn;
	
	/** The table appointments. */
	@FXML private TableView<Appointment> tabelAppointments;
	
	/** The id column. */
	@FXML private TableColumn<Appointment, String> idClmn;
	
	/** The date column. */
	@FXML private TableColumn<Appointment, String> dateClmn;
	
	/** The time column. */
	@FXML private TableColumn<Appointment, String> timeClmn;
	
	/** The specialist column. */
	@FXML private TableColumn<Appointment, String> specialistClmn;
	
	/** The branch column. */
	@FXML private TableColumn<Appointment, String> branchClmn;
	
	/** The choosed id. */
	private int choosedID;
	
	/** The client id. */
	private String clientID;
	
	/** The this UI. */
	private IUi thisUi;
	
	/** The appointments list. */
	private ArrayList<Appointment> appointmnetsList = new ArrayList<Appointment>();		
	
	/** The get hour by integer. */
	private HashMap<Integer,String> getHourByInteger = new HashMap<Integer,String>();

	/**
	 * Set client's ID to the current cancel appointment scenario.
	 *
	 * @param id Gets client's ID
	 */
	public void setUser(String id){
		clientID = id;
		fieldPersonID.setText(id);
		
		findClientAppointments(clientID);
		
	}
	
	/**
	 * findClientAppointments function sends a request to the server for all upcoming client's appointments.
	 *
	 * @param id Gets client's ID
	 */
	public void findClientAppointments(String id){
		
		ArrayList<String> msg = new ArrayList<String>();
		msg.add(id);
		Request request = new Request(Command.FIND_APPOINTMENTS,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Appointments table observable.
	 *
	 * @param list Gets an ArrayList of Hour objects
	 * @return ObservableList<Appointment>
	 */
	public ObservableList<Appointment> getAppointments(ArrayList<Appointment> list){
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			appointments.add(list.get(i));
		}
		
		return appointments;
	}
	
	/**
	 * This function updates the view of the appointments table elements.
	 * @param list Gets an ArrayList of Appointment objects
	 */
	public void onUpdateTableView(ArrayList<Appointment> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				tabelAppointments.setItems(getAppointments(list));
			}});
	}
	
	/**
	 * onCancelAppointmentButtonClick function handles a click on cancel appointment button.
	 * The function calls cancelAppoiontment with the selected appointment id from the table.
	 *
	 * @param event the event
	 */
	public void onCancelAppointmentButtonClick(ActionEvent event){
		
			cancelAppointment(choosedID);
	}
	
	/**
	 * cancelAppointment function creates a CANCEL_APPOINTMENT request message and send it to the server.
	 * @param appID Gets the appointment's ID
	 */
	public void cancelAppointment(int appID){
		
		ArrayList<String> msg = new ArrayList<String>();
		msg.add(Integer.toString(appID));
		Request request = new Request(Command.CANCEL_APPOINTMENT,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
	}


	/**
	 * onBackButtonClick function is back button handler. 
	 * The function searches the last IUi instance in the UI stack and show the window.
	 * The function removes the current from the stack.
	 * @param event
	 */
	public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof DispatcherUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}
	
	/**
	 * onMouseClick function handles a mouse click on a appointment row from the appointments table.
	 * The function gets the appointment ID from the table.
	 * @param event the event
	 */
	public void onMouseClick(MouseEvent event){
		try{
			if (tabelAppointments.getSelectionModel().getSelectedItem().getAppointmentID() != 0){
				
				cancelBtn.setDisable(false);
				choosedID = tabelAppointments.getSelectionModel().getSelectedItem().getAppointmentID();
					
			}
			
			else{
				choosedID = 0;
				cancelBtn.setDisable(true);
			}	
	}catch(Exception e){
		choosedID = 0;
		cancelBtn.setDisable(true);
	}
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	/*
	 * 	The initialize function initializes the CacnelAppointmentUI screen and class members.
	 *  The function initializes Hash-Maps for hours.
	 *  The function initializes tables'es columns.
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CancelAppointmentUI){
				thisUi = ui;
			}
		}		
	
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
		
		
		idClmn.setStyle( "-fx-alignment: CENTER;");
		dateClmn.setStyle( "-fx-alignment: CENTER;");
		timeClmn.setStyle( "-fx-alignment: CENTER;");
		branchClmn.setStyle( "-fx-alignment: CENTER;");
		specialistClmn.setStyle( "-fx-alignment: CENTER;");

	
		idClmn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
		dateClmn.setCellValueFactory(new PropertyValueFactory<>("dateString"));	
		timeClmn.setCellValueFactory(new PropertyValueFactory<>("timeString"));	
		specialistClmn.setCellValueFactory(new PropertyValueFactory<>("specialistName"));	
		branchClmn.setCellValueFactory(new PropertyValueFactory<>("branchName"));
		
		
	}

	/* (non-Javadoc)
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	/*
	 * The handle reply process the results of FIND_APPOINTMENTS & CANCEL_APPOINTMENT requests.
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply) {

		Object result =  reply.getResult();

		
		if (reply.getCommand() == Command.FIND_APPOINTMENTS){
			
						
			if (result instanceof ArrayList<?>){
				
				ArrayList<Object> list = (ArrayList<Object>)result;
	
				for (int i = 0 ; i < list.size(); i++){
					ArrayList<String> strings = (ArrayList<String>)(list.get(i));
					Appointment appointment = new Appointment();
					appointment.setAppointmentID(Integer.parseInt(strings.get(0)));
					appointment.setBranchName(strings.get(4));
					appointment.setTimeString(getHourByInteger.get(Integer.parseInt(strings.get(2))));
					appointment.setSpecialistName(strings.get(3));
									
					String dateInString = strings.get(1);
					String[] date = dateInString.split("-");
					String setDate = date[2]+"-"+date[1]+"-"+date[0];					

					appointment.setDateString(setDate);

					appointmnetsList.add(appointment);
				}
				
				onUpdateTableView(appointmnetsList);	

		 }
			
			else {
				thisUi.hideWindow();
			
				ClientConnectionController.clientConnect.userInterface.remove(thisUi);
				
				for (IUi ui : ClientConnectionController.clientConnect.userInterface){
					if (ui instanceof DispatcherUI){
						ui.showWindow();
					}
				}
				
				thisUi.displayErrorMessage("No Appointments To Display", "This client has no appointments yet.");

				
			}
	 }
	
		else if (reply.getCommand() == Command.CANCEL_APPOINTMENT){
			
			if ((Result)result == Result.OK){
				thisUi.displayMessage("Appointment Canceled", "The choosed appointment canceled successfuly.");
				
				for(Appointment app : appointmnetsList){
					if (app.getAppointmentID() == choosedID){
						appointmnetsList.remove(app);
						onUpdateTableView(appointmnetsList);	
						break;
					}
				}
			}
			
			else if ((Result)result == Result.NEXT_24){
				thisUi.displayErrorMessage("Cancel Appointment Error", "Cannot cancel appointments that occurs in the next day.");
			}
			else{
				thisUi.displayErrorMessage("Cancel Appointment Error", "An error occured while tried to cancel the requested appointment.");
			}
		}
		
	}
	
}