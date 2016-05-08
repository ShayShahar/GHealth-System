package client.control;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CancelAppointmentController implements IController, Initializable{

	@FXML private TextField fieldPersonID;
	
	@FXML private TableView<Appointment> tabelAppointments;
	@FXML private TableColumn<Appointment, String> idClmn;
	@FXML private TableColumn<Appointment, Date> dateClmn;
	@FXML private TableColumn<Appointment, String> timeClmn;
	@FXML private TableColumn<Appointment, String> specialistClmn;
	@FXML private TableColumn<Appointment, String> branchClmn;
	
	
	HashMap<Integer,String> getHourByInteger = new HashMap<Integer,String>();

	
	private String clientID;
	private IUi thisUi;
	
	public void setUser(String id){
		clientID = id;
		fieldPersonID.setText(id);
		
		ArrayList<String> msg = new ArrayList<String>();
		msg.add(clientID);
		Request request = new Request(Command.FIND_APPOINTMENTS,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	
	public ObservableList<Appointment> getAppointments(ArrayList<Appointment> list){
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			appointments.add(list.get(i));
		}
		
		return appointments;
	}
	
	public void onUpdateTableView(ArrayList<Appointment> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				tabelAppointments.setItems(getAppointments(list));
			}});
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
	
	public void onMouseClick(MouseEvent event){
		
	}
	
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
		dateClmn.setCellValueFactory(new PropertyValueFactory<>("date"));	
		timeClmn.setCellValueFactory(new PropertyValueFactory<>("TimeString"));	
		specialistClmn.setCellValueFactory(new PropertyValueFactory<>("specialistName"));	
		branchClmn.setCellValueFactory(new PropertyValueFactory<>("branchName"));
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply) {

		Object result =  reply.getResult();

		
		if (reply.getCommand() == Command.FIND_APPOINTMENTS){
			
			ArrayList<Appointment> appointmnetsList = new ArrayList<Appointment>();
			
			System.out.println("1");
			
			if (result instanceof ArrayList<?>){
				
				ArrayList<Object> list = (ArrayList<Object>)result;
	
				System.out.println("2");

				for (int i = 0 ; i < list.size(); i++){
					ArrayList<String> strings = (ArrayList<String>)(list.get(i));
					Appointment appointment = new Appointment();
					appointment.setAppointmentID(Integer.parseInt(strings.get(0)));
					appointment.setBranchName(strings.get(4));
					appointment.setTimeString(getHourByInteger.get(strings.get(2)));
					appointment.setSpecialistName(strings.get(3));
			/*		
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					String dateInString = strings.get(1);
					String[] date = dateInString.split("-");
					String setDate = date[2]+"-"+date[1]+"-"+date[0];*/
					
				//	appointment.setDate(formatter.parse(setDate));
					
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
		
		
	}
	
}