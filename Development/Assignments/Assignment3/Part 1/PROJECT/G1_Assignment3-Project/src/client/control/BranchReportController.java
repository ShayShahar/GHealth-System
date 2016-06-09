package client.control;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import client.boundry.BranchManagerUI;
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
import javafx.scene.chart.BarChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
 
// TODO: Auto-generated Javadoc
/**
 * BranchReportController connects between the BranchManagerUI to the components logics.
 * @author shays
 *
 */
public class BranchReportController implements IController, Initializable{
	

	/** The number text. */
	@FXML private TextField numberTxt;
	
	/** The res chart. */
	@FXML private BarChart<String, Integer> resChart;
	
	/** The clients avg1. */
	@FXML private TextField clientsAvg1;
	
	/** The waiting avg1. */
	@FXML private TextField waitingAvg1;
	
	/** The clients sd1. */
	@FXML private TextField clientsSd1;
	
	/** The waiting sd1. */
	@FXML private TextField waitingSd1;
	
	/** The clients total1. */
	@FXML private TextField clientsTotal1;
	
	/** The waiting total1. */
	@FXML private TextField waitingTotal1;
	
	/** The month list. */
	@FXML private ComboBox<Integer> monthList;
	
	/** The year list1. */
	@FXML private ComboBox<Integer> yearList1;
	
	/** The year list2. */
	@FXML private ComboBox<Integer> yearList2;
	
	/** The week list. */
	@FXML private ComboBox<Integer> weekList;
	
	/** The clients average. */
	@FXML private TextField clientsAvg;
	
	/** The waiting average. */
	@FXML private TextField waitingAvg;
	
	/** The clients sd. */
	@FXML private TextField clientsSd;
	
	/** The waiting sd. */
	@FXML private TextField waitingSd;
	
	/** The clients total. */
	@FXML private TextField clientsTotal;
	
	/** The waiting total. */
	@FXML private TextField waitingTotal;
	
	/** The missed average. */
	@FXML private TextField missedAvg;
	
	/** The missed sd. */
	@FXML private TextField missedSd;
	
	/** The missed total. */
	@FXML private TextField missedTotal;
	
	/** The left average. */
	@FXML private TextField leftAvg;
	
	/** The left sd. */
	@FXML private TextField leftSd;
	
	/** The left total. */
	@FXML private TextField leftTotal;
	
	/** The branch name. */
	@FXML private Label branchName;

	/** The days. */
	private HashMap<Integer,String> days = new HashMap<Integer,String>();
	
	/** The this UI. */
	private IUi thisUi;
	
	/** The c_average. */
	private double c_avg;
	
	/** The c_total. */
	private int c_total;
	
	/** The c_sd. */
	private double c_sd;
	
	/** The w_average. */
	private double w_avg;
	
	/** The w_total. */
	private int w_total;
	
	/** The w_sd. */
	private double w_sd;
	
	/** The m_average. */
	private double m_avg;
	
	/** The m_total. */
	private int m_total;
	
	/** The m_sd. */
	private double m_sd;
	
	/** The l_average. */
	private double l_avg;
	
	/** The l_total. */
	private int l_total;
	
	/** The l_sd. */
	private double l_sd;
	
	/** The user_branch. */
	private String user_branch;
	
	
	/**
	 * onMonthlyReportButtonClick function is view monthly report button handler.
	 * The function creates a request to display a monthly report for a selected month for the logged-in manager's branch.
	 *
	 * @param event the event
	 */
	public void onMonthlyReportButtonClick(ActionEvent event){

		try{
			
				if (yearList1.getSelectionModel().getSelectedItem() == null 
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
				
				displayMonthlyReport(user_branch,yearList1.getSelectionModel().getSelectedItem(),monthList.getSelectionModel().getSelectedItem());
		
				
			}catch(Exception e){}
	}
		
	
	/**
	 * displayMonthlyReport sends a request to the server that creates a monthly report.
	 *
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
 	 * The function creates a request to display a weekly report for a selected month for the logged-in manager's branch.
 	 *
 	 * @param event the event
 	 */
	public void onWeeklyReportButtonClick(ActionEvent event){

		try{
		
				if (yearList2.getSelectionModel().getSelectedItem() == null 
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
				
			
				displayWeeklyReport(user_branch,yearList2.getSelectionModel().getSelectedItem(),weekList.getSelectionModel().getSelectedItem());
				
		}catch(Exception e){}
		
	}
	
	/**
	 * displayWeeklyReport creates a weekly report by sending a request to the server.
	 *
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
	 *
	 * @param event the event
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
	
	/* (non-Javadoc)
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	/*
	 * The handle reply process the results of LOGOUT, GET_BRANCH_BY_USERNAME, MONTHLY_REPORT & WEEKLY_REPORT requests.
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handleReply(Reply reply) {

		Object result =  reply.getResult();

		if (reply.getCommand() == Command.GET_BRANCH_BY_USERNAME){
			
			if (result instanceof String){
				String branch = (String)result;
				user_branch = branch;
				branchName.setText("Branch: " + user_branch);
			}
			
			else if (result instanceof Result){
				thisUi.displayErrorMessage("Fatal Error", "Error occured while loading the account information. try re-connect.");
			}
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
			
			if (result instanceof Result){
				thisUi.displayErrorMessage("Server Error","An error occured while trying to create the report. try again.");
			}
			
			else{
						
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

	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	/*
	 * 	The initialize function initializes the BranchManagerUI screen and class members.
	 *  The function sends a GET_BRANCH_BY_USERNAME request to the server to allocate the branches list on start.
	 *  The function initializes the days Hash-Map.
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		ArrayList<String> msg = new ArrayList<String>();
		msg.add(ClientConnectionController.clientConnect.userName);
		
		Request request = new Request(Command.GET_BRANCH_BY_USERNAME,msg);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		
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
			if (ui instanceof BranchManagerUI){
				thisUi = ui;
			}
		}
		
	}


}