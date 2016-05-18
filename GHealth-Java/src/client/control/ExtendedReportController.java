package client.control;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import client.boundry.GeneralManagerUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.*;
import common.enums.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ExtendedReportController implements IController, Initializable{

	 /**
	  * FXML Components links
	  */
	
	@FXML private ComboBox<String> selectBranchList;
	@FXML private ComboBox<String> selectBranchList1;
	@FXML private TextField numberTxt;
	@FXML private BarChart<String, Integer> resChart;
	@FXML private TextField clientsAvg1;
	@FXML private TextField waitingAvg1;
	@FXML private TextField clientsSd1;
	@FXML private TextField waitingSd1;
	@FXML private TextField clientsTotal1;
	@FXML private TextField waitingTotal1;
	@FXML private ComboBox<Integer> monthList;
	@FXML private ComboBox<Integer> yearList1;
	@FXML private ComboBox<Integer> yearList2;
	@FXML private ComboBox<Integer> weekList;
	
	
	private IUi thisUi;
	private HashMap<Integer,String> days = new HashMap<Integer,String>();
	private double c_avg;
	private int c_total;
	private double c_sd;
	private double w_avg;
	private int w_total;
	private double w_sd;
	
	/**
	 * Monthly report button handler
	 * @param event
	 */
	 
	public void onMonthlyReportButtonClick(ActionEvent event){

		try{
			
				if (selectBranchList.getSelectionModel().getSelectedItem() == null 
						|| yearList1.getSelectionModel().getSelectedItem() == null 
						|| monthList.getSelectionModel().getSelectedItem() == null ){
					thisUi.displayErrorMessage("Invalid Input", "Required fields are missing. try again");
					return;
				}
				
				
				Calendar now = Calendar.getInstance();
				if (Integer.toString(now.get(Calendar.YEAR)).equals(yearList1.getSelectionModel().getSelectedItem().toString())
						&& now.get(Calendar.MONTH) + 1 <= monthList.getSelectionModel().getSelectedItem()){
					thisUi.displayErrorMessage("Not available", "No available information for the selected month.");
					return;
				}
					
				ArrayList<String> msg = new ArrayList<String>();
				msg.add(selectBranchList.getSelectionModel().getSelectedItem().toString());
				msg.add(yearList1.getSelectionModel().getSelectedItem().toString());
				msg.add(monthList.getSelectionModel().getSelectedItem().toString());

				Request request = new Request(Command.MONTHLY_REPORT,msg);
				
				try {
					ClientConnectionController.clientConnect.controller = this;
					ClientConnectionController.clientConnect.sendToServer(request);
				} catch (IOException e) {
					e.printStackTrace();
				}		
		}catch(Exception e){}
		
	}
 
	
	 /**
	  * Weekly report button handler
	  * @param event
	  */
	
	public void onWeeklyReportButtonClick(ActionEvent event){

		try{
		
				if (selectBranchList1.getSelectionModel().getSelectedItem() == null 
						|| yearList2.getSelectionModel().getSelectedItem() == null 
						|| weekList.getSelectionModel().getSelectedItem() == null ){
					thisUi.displayErrorMessage("Invalid Input", "Required fields are missing. try again");
					return;
				}
				
				Calendar now = Calendar.getInstance();
				
				if (Integer.toString(now.get(Calendar.YEAR)).equals(yearList2.getSelectionModel().getSelectedItem().toString())
						&& now.get(Calendar.WEEK_OF_YEAR) <= weekList.getSelectionModel().getSelectedItem()){
					thisUi.displayErrorMessage("Not available", "No available information for the selected week.");
					return;
				}
				
				ArrayList<String> msg = new ArrayList<String>();
				msg.add(selectBranchList1.getSelectionModel().getSelectedItem().toString());
				msg.add(yearList2.getSelectionModel().getSelectedItem().toString());
				msg.add(weekList.getSelectionModel().getSelectedItem().toString());
				
				Request request = new Request(Command.WEEKLY_REPORT,msg);
				
				try {
					ClientConnectionController.clientConnect.controller = this;
					ClientConnectionController.clientConnect.sendToServer(request);
				} catch (IOException e) {
					e.printStackTrace();
				}		
		}catch(Exception e){}
		
	}
	
		/**
		 * Logout button handler
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
	 * Process reply from server function to Extended report controller
	 *  @param reply
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handleReply(Reply reply) {

		Object result =  reply.getResult();

		if (reply.getCommand() == Command.GET_BRANCHES){
			ArrayList<String> branches = (ArrayList<String>)result;
			ObservableList obList = FXCollections.observableList(branches);
			selectBranchList.setItems(obList);
			selectBranchList1.setItems(obList);
		}
		
		else if (reply.getCommand() == Command.MONTHLY_REPORT){
			if (result instanceof Result){
				if ((Result)result == Result.FAILED){
					thisUi.displayErrorMessage("Operation failed","No information found for the selected month.");
				}
				else {
					thisUi.displayErrorMessage("Server Error","An error occured while trying to create the report. try again.");
				}
			}
			
			else{
				ArrayList<Object> list = (ArrayList<Object>)result;
				ArrayList<Integer> clients = (ArrayList<Integer>)list.get(0);
				ArrayList<Integer> waiting = (ArrayList<Integer>)list.get(1);
				
				Series<String, Integer> s1 = new XYChart.Series<>();
				Series<String, Integer> s2 = new XYChart.Series<>();
				

				s1.setName("Treated Clients");
				s2.setName("Waiting Time");
				
				for (int i = 0 ; i < clients.size(); i++){
					s1.getData().add(new XYChart.Data("Week "+ (i + 1), clients.get(i)));
					s2.getData().add(new XYChart.Data("Week "+ (i + 1), waiting.get(i)));
				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
												
						resChart.getData().clear();
						resChart.getData().addAll(s1,s2);
						}
				});
				
				
			}
		}
		
		else if (reply.getCommand() == Command.WEEKLY_REPORT){
			
			Series<String, Integer> s1 = new XYChart.Series<>();
			Series<String, Integer> s2 = new XYChart.Series<>();

			s1.setName("Treated Clients");
			s2.setName("Waiting Time");
			
			 c_avg = 0;
			 c_total = 0;
			 c_sd = 0;
			 w_avg = 0;
			 w_total = 0;
			 w_sd = 0;
			
			
			 ArrayList<Object> list = (ArrayList<Object>)result;
			
			for (int i = 0 ; i < 5; i++){
				ArrayList<Integer> res = (ArrayList<Integer>) list.get(i);
				s1.getData().add(new XYChart.Data(days.get(i+1), res.get(0)));
				s2.getData().add(new XYChart.Data(days.get(i+1), res.get(1)));
				c_total+=res.get(0);
				w_total+=res.get(1);
			}
			
			c_avg = (float)c_total/5;
			w_avg = (float)w_total/5;
			
			int calc_cSd = 0;
			int calc_wSd = 0;
			
			for (int i = 0 ; i < 5; i++){
				ArrayList<Integer> res = (ArrayList<Integer>) list.get(i);
				calc_cSd += Math.pow(res.get(0) - c_avg, 2);
				calc_wSd += Math.pow(res.get(1) - w_avg, 2);
			}
			
			c_sd = Math.sqrt(0.2*calc_cSd);
			w_sd = Math.sqrt(0.2*calc_wSd);
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					
					DecimalFormat df = new DecimalFormat("#.##");
					clientsAvg1.setText(df.format(c_avg));
					waitingAvg1.setText(df.format(w_avg));
					clientsSd1.setText(df.format(c_sd));
					waitingSd1.setText(df.format(w_sd));
					clientsTotal1.setText(Integer.toString(c_total));
					waitingTotal1.setText(Integer.toString(w_total));
					
					resChart.getData().clear();
					resChart.getData().addAll(s1,s2);
					}
			});

		}
		
		else if ((Result)result == Result.LOGGEDOUT){
			
						thisUi.hideWindow();
						ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
						ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
			}
	
	}

	
	/**
	 * Initialize controller and General Manager UI function
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		days.put(1,"Sunday");
		days.put(2,"Monday");
		days.put(3,"Tuesday");
		days.put(4,"Wednesday");
		days.put(5,"Thursday");
		
		ArrayList<Integer> months = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
		ObservableList obList = FXCollections.observableList(months);
		monthList.setItems(obList);
		
		ArrayList<Integer> years = new ArrayList<Integer>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 0 ; i<7; i++){
			years.add(year--);
		}
		
		ObservableList obYear = FXCollections.observableList(years);
		yearList1.setItems(obYear);
		yearList2.setItems(obYear);

		ArrayList<Integer> weeks = new ArrayList<Integer>();
		for (int i = 1; i<57; i++){
			weeks.add(i);
		}
		ObservableList obWeeks = FXCollections.observableList(weeks);
		weekList.setItems(obWeeks);
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof GeneralManagerUI){
				thisUi = ui;
			}
		}
		
		Request request = new Request(Command.GET_BRANCHES,null);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}