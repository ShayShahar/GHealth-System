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

/**
 * ExtendedReportController connects between the GeneralManagerUI to the components logics.
 * @author shays
 *
 */

public class ExtendedReportController implements IController, Initializable{

	
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
	@FXML private TextField clientsAvg;
	@FXML private TextField waitingAvg;
	@FXML private TextField clientsSd;
	@FXML private TextField waitingSd;
	@FXML private TextField clientsTotal;
	@FXML private TextField waitingTotal;
	@FXML private TextField missedAvg;
	@FXML private TextField missedSd;
	@FXML private TextField missedTotal;
	@FXML private TextField leftAvg;
	@FXML private TextField leftSd;
	@FXML private TextField leftTotal;

	private HashMap<Integer,String> days = new HashMap<Integer,String>();
	private IUi thisUi;
	private double c_avg;
	private int c_total;
	private double c_sd;
	private double w_avg;
	private int w_total;
	private double w_sd;
	private double m_avg;
	private int m_total;
	private double m_sd;
	private double l_avg;
	private int l_total;
	private double l_sd;
	
	/**
	 * onMonthlyReportButtonClick function is view monthly report button handler.
	 * The function creates a request to display a monthly report for a selected month and branch.
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
					
				displayMonthlyReport(selectBranchList.getSelectionModel().getSelectedItem().toString(), yearList1.getSelectionModel().getSelectedItem(), monthList.getSelectionModel().getSelectedItem());
				
				
		}catch(Exception e){}
		
	}
 
	
	/**
	 * displayMonthlyReport sends a request to the server that creates a monthly report
	 * @param branch Gets the name of the branch
	 * @param year Gets the year
	 * @param month Gets the month
	 */
	
	public void displayMonthlyReport(String branch, int year, int month){
		
				ArrayList<String> msg = new ArrayList<String>();
				msg.add(branch);
				msg.add(Integer.toString(year));
				msg.add(Integer.toString(month));
		
				Request request = new Request(Command.MONTHLY_REPORT,msg);
				
				try {
					ClientConnectionController.clientConnect.controller = this;
					ClientConnectionController.clientConnect.sendToServer(request);
				} catch (IOException e) {
					e.printStackTrace();
				}		
	}

	 /**
	  * onWeeklyReportButtonClick function is view weekly report button handler.
	  * The function creates a request to display a weekly report for a selected month and branch.
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
								
				displayWeeklyReport(selectBranchList1.getSelectionModel().getSelectedItem().toString(),yearList2.getSelectionModel().getSelectedItem(), weekList.getSelectionModel().getSelectedItem());
		}catch(Exception e){}
		
	}
	
	/**
	 * displayWeeklyReport creates a weekly report by sending a request to the server
	 * @param branch Gets the branch name
	 * @param year Gets the year
	 * @param week Gets the week of the year number
	 */
	public void displayWeeklyReport(String branch, int year, int week){
		
		ArrayList<String> msg = new ArrayList<String>();
		msg.add(branch);
		msg.add(Integer.toString(year));
		msg.add(Integer.toString(week));
		
		Request request = new Request(Command.WEEKLY_REPORT,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
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
	
	/*
	 * The handle reply process the results of LOGOUT, GET_BRANCHES, MONTHLY_REPORT & WEEKLY_REPORT requests.
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
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
				ArrayList<Integer> missed = (ArrayList<Integer>)list.get(2);
				ArrayList<Integer> left = (ArrayList<Integer>)list.get(3);

				Series<String, Integer> s1 = new XYChart.Series<>();
				Series<String, Integer> s2 = new XYChart.Series<>();
				Series<String, Integer> s3 = new XYChart.Series<>();
				Series<String, Integer> s4 = new XYChart.Series<>();


				s1.setName("Treated Clients");
				s2.setName("Waiting Time");
				s3.setName("Missed Appointments");
				s4.setName("Left Clients");
				
				
				 c_avg = 0;
				 c_total = 0;
				 c_sd = 0;
				 w_avg = 0;
				 w_total = 0;
				 w_sd = 0;
				 m_avg = 0;
				 m_total = 0;
				 m_sd = 0;
				 l_avg = 0;
				 l_total = 0;
				 l_sd = 0;

				for (int i = 0 ; i < clients.size(); i++){
					s1.getData().add(new XYChart.Data("Week "+ (i + 1), clients.get(i)));
					s2.getData().add(new XYChart.Data("Week "+ (i + 1), waiting.get(i)));
					s3.getData().add(new XYChart.Data("Week "+ (i + 1), missed.get(i)));
					s4.getData().add(new XYChart.Data("Week "+ (i + 1), left.get(i)));
					c_total+=clients.get(i);
					w_total+=waiting.get(i);
					m_total+=missed.get(i);
					l_total+=left.get(i);
				}

				c_avg = (float)c_total/5;
				w_avg = (float)w_total/5;
				m_avg = (float)m_total/5;
				l_avg = (float)l_total/5;
				
				int calc_cSd = 0;
				int calc_wSd = 0;
				int calc_mSd = 0;
				int calc_lSd = 0;
				
				for (int i = 0 ; i < 5; i++){
					calc_cSd += Math.pow(clients.get(i) - c_avg, 2);
					calc_wSd += Math.pow(waiting.get(i) - w_avg, 2);
					calc_mSd += Math.pow(missed.get(i) - m_avg, 2);
					calc_lSd += Math.pow(left.get(i) - l_avg, 2);
				}
				
				c_sd = Math.sqrt(0.2*calc_cSd);
				w_sd = Math.sqrt(0.2*calc_wSd);
				m_sd = Math.sqrt(0.2*calc_mSd);
				l_sd = Math.sqrt(0.2*calc_lSd);

				
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
												
						DecimalFormat df = new DecimalFormat("#.##");
						clientsAvg.setText(df.format(c_avg));
						waitingAvg.setText(df.format(w_avg));
						clientsSd.setText(df.format(c_sd));
						waitingSd.setText(df.format(w_sd));
						clientsTotal.setText(Integer.toString(c_total));
						waitingTotal.setText(Integer.toString(w_total));
						missedAvg.setText(df.format(m_avg));
						leftAvg.setText(df.format(l_avg));
						missedSd.setText(df.format(m_sd));
						leftSd.setText(df.format(l_sd));
						missedTotal.setText(Integer.toString(m_total));
						leftTotal.setText(Integer.toString(l_total));
						
						resChart.getData().clear();
						resChart.getData().addAll(s1,s2,s3,s4);
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
		
		else 	if (reply.getCommand() == Command.LOGOUT){
		
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
	}

	/*
	 * 	The initialize function initializes the GeneralManagerUI screen and class members.
	 *  The function sends a GET_BRANCHES request to the server to allocate the branches list on start.
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
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
		for (int i = 1; i<53; i++){
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