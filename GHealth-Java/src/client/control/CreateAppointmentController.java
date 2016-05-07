package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.time.LocalDate;
import client.boundry.CreateAppointmentUI;
import client.boundry.DispatcherUI;
import client.entity.DateChecker;
import client.entity.Hour;
import client.entity.Specialist;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class CreateAppointmentController implements IController, Initializable{
	
	/**
	 * JavaFX FXML references
	 */

	@FXML private ComboBox<String> listSpecialization;
	@FXML private TextField fieldPersonID;
	@FXML private TableView<Specialist> tabelSpecialists;
	@FXML private TableColumn<Specialist, String> idClmn;
	@FXML private TableColumn<Specialist, String> nameClmn;
	@FXML private TableColumn<Specialist, String> familyClmn;
	@FXML private TableColumn<Specialist, String> branchClmn;
	@FXML private TableColumn<Specialist, String> addrClmn;
	@FXML private HBox hboxDate;
	@FXML private DatePicker appPicker;
	@FXML private Button selectDateBtn;
	@FXML private TableView<Hour> timeTable;
	@FXML private TableColumn<Hour, String> timeClmn;
	@FXML private Button createAppBtn;


	
	/**
	 * Class members
	 */
	
	IUi thisUi = null;
  private int choosedID = 0;
  private int choosedHour = 0;
  private ArrayList<DateChecker> blockedDates = new ArrayList<DateChecker>();
  
  HashMap<Integer,String> getHourByInteger = new HashMap<Integer,String>();
  HashMap<String,Integer> getIntegerByHour = new HashMap<String,Integer>();


  
  /**
   * list of possible specializations that supported by IHealth
   */
  
	ObservableList<String> list = FXCollections.observableArrayList("--","Allergology","Anaesthetics",
			"Biological hematology","Cardiology","Child psychiatry","Clinical biology",
			"Clinical chemistry","Clinical neurophysiology","Craniofacial surgery",
			"Craniofacial surgery","Dermato-venerology","Dermatology","Endocrinology",
			"Gastro-enterologic surgery","Gastroenterology","General hematology","General Practice",
			"Geriatrics","Immunology","Infectious diseases","Internal medicine","Microbiology",
			"Nephrology", "Neuro-psychiatry","Neurology","Neurosurgery","Orthopaedics","Pathology",
			"Psychiatry","Radiology","Stomatology","Urology","Venereology");

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
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


		timeClmn.setStyle( "-fx-alignment: CENTER;");
		timeClmn.setCellValueFactory(new PropertyValueFactory<>("hour"));

	
		listSpecialization.setItems(list);		
		listSpecialization.getSelectionModel().selectFirst();
				
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof CreateAppointmentUI){
				thisUi = ui;
			}
		}
		idClmn.setStyle( "-fx-alignment: CENTER;");
		nameClmn.setStyle( "-fx-alignment: CENTER;");
		familyClmn.setStyle( "-fx-alignment: CENTER;");
		branchClmn.setStyle( "-fx-alignment: CENTER;");
		addrClmn.setStyle( "-fx-alignment: CENTER;");

		
		idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameClmn.setCellValueFactory(new PropertyValueFactory<>("name"));	
		familyClmn.setCellValueFactory(new PropertyValueFactory<>("familyName"));	
		branchClmn.setCellValueFactory(new PropertyValueFactory<>("branchName"));
		addrClmn.setCellValueFactory(new PropertyValueFactory<>("branchAddress"));	

    appPicker.setValue(LocalDate.now());

		
	    final Callback<DatePicker, DateCell> dayCellFactory = 
	            new Callback<DatePicker, DateCell>() {
	                @Override
	                public DateCell call(final DatePicker datePicker) {
	                    return new DateCell() {
	                        @Override
	                        public void updateItem(LocalDate item, boolean empty) {
	                            super.updateItem(item, empty);
	                            if (item.isBefore(LocalDate.now())) {
	                                    setDisable(true);
	                                    setStyle("-fx-background-color: #ffced7;");
	                            }
	                            
	                            if (item.getDayOfWeek().toString().equals("FRIDAY") || item.getDayOfWeek().toString().equals("SATURDAY")){
	                                setDisable(true);
	                                setStyle("-fx-background-color: #cbcbcb;");
	                            }
	                            
	                            for (DateChecker d : blockedDates){
	                            	
	                            	Date input = d.getDate();
	                            	Calendar cal = Calendar.getInstance();
	                            	cal.setTime(input);
	                            
	                            	if (item.isEqual(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)))){
	                                    setDisable(true);
	                                    setStyle("-fx-background-color: #ffced7;");
	                            	}
	                            }
	                    }
	                };
	            }
	        };
		
    appPicker.setDayCellFactory(dayCellFactory);
		
	}
	
	/**
	 * hours table observable
	 * @param list
	 * @return ObservableList<Hour>
	 */
	
	public ObservableList<Hour> getHours(ArrayList<Hour> list){
		ObservableList<Hour> hours = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			hours.add(list.get(i));
		}
		
		return hours;
	}

	/**
	 * specialists table observable
	 * @param list
	 * @return ObservableList<Specialist> 
	 */
	public ObservableList<Specialist> getSpecialist(ArrayList<Specialist> list){
		ObservableList<Specialist> specialists = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			specialists.add(list.get(i));
		}
		
		return specialists;
	}
	
	public void onUpdateTableView(ArrayList<Specialist> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				tabelSpecialists.setItems(getSpecialist(list));
			}});
	}
	
	public void onUpdateHoursTableView(ArrayList<Hour> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				timeTable.setItems(getHours(list));
			}});
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
	
	
	public void onSearchSpecialistButtonClick(ActionEvent event){
		
		hboxDate.setDisable(true);
	  appPicker.setValue(LocalDate.now());
		if (listSpecialization.getSelectionModel().getSelectedItem().toString().equals("--")){
			thisUi.displayErrorMessage("Invalid Input", "Please choose a specialization from the list.");
			return;
		}
		
		ArrayList<String> msg = new ArrayList<String>();
		msg.add(listSpecialization.getSelectionModel().getSelectedItem().toString());
		
		Request request = new Request(Command.FIND_SPECIALIST,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void onMouseClick(MouseEvent event){
		
		try{
				if (tabelSpecialists.getSelectionModel().getSelectedItem().getId() != null){
					hboxDate.setDisable(false);
					choosedID = Integer.parseInt(tabelSpecialists.getSelectionModel().getSelectedItem().getId());
					
					
					ArrayList<String> msg = new ArrayList<String>();
					msg.add(Integer.toString(choosedID));
				
					Request request = new Request(Command.FIND_FULL_DATES,msg);
					
					try {
						ClientConnectionController.clientConnect.controller = this;
						ClientConnectionController.clientConnect.sendToServer(request);
					} catch (IOException e) {
						e.printStackTrace();
					}					
				}
				
				else{
					choosedID = 0;
					hboxDate.setDisable(true);
				}	
		}catch(Exception e){
			choosedID = 0;
			hboxDate.setDisable(true);
		}
	}
	
	
	public void onMouseClickChooseHour(MouseEvent event){
		
		try{
					if (timeTable.getSelectionModel().getSelectedItem().getHour() != null){
						createAppBtn.setDisable(false);
						choosedHour = getIntegerByHour.get(timeTable.getSelectionModel().getSelectedItem().getHour());
					}
			
					else{
						choosedHour = 0;
						createAppBtn.setDisable(true);
					}	
		}catch(Exception e){
					choosedHour = 0;
					createAppBtn.setDisable(true);
		}
	}
	
	
	public void onCreateAppointmentButtonClick(ActionEvent event){
		
		ArrayList<String> msg = new ArrayList<String>();
		
		msg.add(Integer.toString(choosedID));
		msg.add(Integer.toString(choosedHour));
		msg.add(java.sql.Date.valueOf(appPicker.getValue()).toString());
		
		Request request = new Request(Command.CREATE_APPOINTMENT,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	public void onSelectDate(ActionEvent event){
			
		ArrayList<String> msg = new ArrayList<String>();
		
		msg.add(Integer.toString(choosedID));
		msg.add(java.sql.Date.valueOf(appPicker.getValue()).toString()); //convert LocalDate to java.sql.Date format
		
		Request request = new Request(Command.FIND_AVAILABLE_HOURS,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}		
			
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply) {

		Object result =  reply.getResult();
		
		if (reply.getCommand() == Command.FIND_SPECIALIST){
						
			ArrayList<Specialist> specialistList = new ArrayList<Specialist>();
			if (result instanceof ArrayList<?>){
				
				ArrayList<Object> list = (ArrayList<Object>)result;
	
				for (int i = 0 ; i < list.size(); i++){

					ArrayList<String> strings = (ArrayList<String>)(list.get(i));
					Specialist specialist = new Specialist(strings.get(0),strings.get(2),strings.get(3),strings.get(1),strings.get(4),strings.get(5));					
					specialistList.add(specialist);
				}
		 }
			
			onUpdateTableView(specialistList);	
	 }
		
		
		else if (reply.getCommand() == Command.FIND_FULL_DATES){
			
			if (result instanceof ArrayList<?>){
				
				blockedDates = (ArrayList<DateChecker>)result;
				
				for(int i = 0; i<blockedDates.size(); i++){
					System.out.println(blockedDates.get(i).getDate());
				}
				
			}
			
			else {
				blockedDates = new ArrayList<DateChecker>();
			}
			
		}
		
		else if (reply.getCommand() == Command.FIND_AVAILABLE_HOURS){
			
			ArrayList<Hour> list = new ArrayList<Hour>();
			
			if (result instanceof String){
				
				
				for (int i = 0; i<((String)result).length(); i++){
					if(((String)result).charAt(i) == '0'){
						Hour hour = new Hour();
						hour.setHour(getHourByInteger.get(i+1));
						list.add(hour);
					}
				}
			}
			
			else {
				
				for (int i = 0; i<18; i++){
					Hour hour = new Hour();
					hour.setHour(getHourByInteger.get(i+1));
					list.add(hour);
			}
				
			}
			
			onUpdateHoursTableView(list);
		}
	}
	
	
}