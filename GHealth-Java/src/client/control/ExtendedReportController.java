package client.control;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import client.boundry.GeneralManagerUI;
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
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ExtendedReportController implements IController, Initializable{

	@FXML private ComboBox<String> selectBranchList;
	@FXML private TextField numberTxt;
	@FXML private BarChart<String, Integer> resChart;

	@FXML private TextField clientsAvg;
	@FXML private TextField waitingAvg;
	@FXML private TextField clientsSd;
	@FXML private TextField waitingSd;
	@FXML private TextField clientsTotal;
	@FXML private TextField waitingTotal;
	
	
	private IUi thisUi;
	private HashMap<Integer,String> days = new HashMap<Integer,String>();
	
	 private double c_avg;
	 private int c_total;
	 private double c_sd;
	 private double w_avg;
	 private int w_total;
	 private double w_sd;
	
	public void onWeeklyReportButtonClick(ActionEvent event){
		

		try{
		
			//TODO fix error message
				if (selectBranchList.getSelectionModel().getSelectedItem().toString() == null){
					thisUi.displayErrorMessage("Invalid Input", "Please select a branch from the list.");
					return;
				}
				
				ArrayList<String> msg = new ArrayList<String>();
				msg.add(selectBranchList.getSelectionModel().getSelectedItem().toString());
				
				Request request = new Request(Command.WEEKLY_REPORT,msg);
				
				try {
					ClientConnectionController.clientConnect.controller = this;
					ClientConnectionController.clientConnect.sendToServer(request);
				} catch (IOException e) {
					e.printStackTrace();
				}		
		}catch(Exception e){}
		
	}
	
	
	
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
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handleReply(Reply reply) {

		Object result =  reply.getResult();

		if (reply.getCommand() == Command.GET_BRANCHES){
			ArrayList<String> branches = (ArrayList<String>)result;
			ObservableList obList = FXCollections.observableList(branches);
			selectBranchList.setItems(obList);
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

			System.out.println(c_avg);
			
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


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		days.put(1,"Sunday");
		days.put(2,"Monday");
		days.put(3,"Tuesday");
		days.put(4,"Wednesday");
		days.put(5,"Thursday");
		
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