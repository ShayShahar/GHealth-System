package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import client.boundry.BranchManagerUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;

import javafx.scene.chart.XYChart;
 
public class BranchReportController implements IController, Initializable{
	
	//FXML variables
	@FXML private Button WeeklyBtn;
	@FXML private Button MonthlyBtn;
	@FXML private BarChart<String, Number> weeklyChart;
	
	private HashMap<Integer,String> days = new HashMap<Integer,String>();
	
public void onLogoutButtonClick (ActionEvent event){
	
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
public void onWeeklyReportButtonClick(ActionEvent event){
	
//from DB:
	
	weeklyChart.getData().clear();

	XYChart.Series s1 = new XYChart.Series<>();
	
	s1.setName("2003");
	s1.getData().add(new XYChart.Data(days.get(1), 1));
	s1.getData().add(new XYChart.Data(days.get(2), 1));
	s1.getData().add(new XYChart.Data(days.get(3), 1));
	s1.getData().add(new XYChart.Data(days.get(4), 1));
	s1.getData().add(new XYChart.Data(days.get(5), 1));
	
	XYChart.Series s2 = new XYChart.Series();
    s2.setName("2004");
    s2.getData().add(new XYChart.Data(days.get(1), 5));
    s2.getData().add(new XYChart.Data(days.get(2), 41));
    s2.getData().add(new XYChart.Data(days.get(3), 45));
    s2.getData().add(new XYChart.Data(days.get(4), 11));
    s2.getData().add(new XYChart.Data(days.get(5), 14));  
    
    XYChart.Series s3 = new XYChart.Series();
    s3.setName("2005");
    s3.getData().add(new XYChart.Data(days.get(1), 45));
    s3.getData().add(new XYChart.Data(days.get(2), 44));
    s3.getData().add(new XYChart.Data(days.get(3), 18));
    s3.getData().add(new XYChart.Data(days.get(4), 17));
    s3.getData().add(new XYChart.Data(days.get(5), 92));
    
    weeklyChart.getData().addAll(s1,s2,s3);
    
}

public void onMonthlyReportButtonClick(ActionEvent event){
		
	
}


@Override
public void handleReply(Reply reply) {

	Object result =  reply.getResult();

	
	if (reply.getCommand() == Command.LOGOUT){
		
		if (result instanceof Result){
					
			result = (Result)result;
					
			if ((Result)result == Result.ERROR){
				ClientConnectionController.clientConnect.userInterface.get(1).displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
					System.exit(1);
			}
			else if ((Result)result == Result.LOGGEDOUT){
				
				if (ClientConnectionController.clientConnect.userPrivilege.equals("Branch")){
					
					for(IUi ui : ClientConnectionController.clientConnect.userInterface)
					{
						if (ui instanceof BranchManagerUI){
							ui.hideWindow();
							ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
							ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
						}
					}
				}
			}
		}
	}
}


@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	days.put(1,"Sunday");
	days.put(2,"Monday");
	days.put(3,"Tuesday");
	days.put(4,"Wednesday");
	days.put(5,"Thursday");
}



}