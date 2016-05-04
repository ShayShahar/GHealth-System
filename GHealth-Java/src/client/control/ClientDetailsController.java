package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import client.boundry.DispatcherUI;
import client.boundry.LoginUI;
import client.boundry.SpecialistUI;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import common.enums.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class ClientDetailsController{
	
	@FXML private Button dispLogoutBtn;
	
	
	public void onLogoutButtonClick(ActionEvent event){
		
		ArrayList<String> username = new ArrayList<String>();
		username.add(UserController.getUser());
	
		Request request = new Request(Command.LOGOUT, username, User.ClientDetailsController);

		try {
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void handleReply(Reply reply){
		
		Object result =  reply.getResult();
		
		if (result instanceof Result){
					
			result = (Result)result;
					
			if ((Result)result == Result.ERROR){
					displayErrorMessage ("Error", "Error occured in system. Exit program.");
					System.exit(1);
			}
			else if ((Result)result == Result.LOGGEDOUT){
				if (UserController.getUser().equals("Dispatcher")){
					System.out.println("sdfsdfdsf");
					DispatcherUI.closeWindow();
				}
				
				else if (UserController.getUser().equals("Specialist")){
					SpecialistUI.closeWindow();
				}
				
				displayMessage ("Logged out", "Your user is logged out from Ghealth system.");
		    	LoginUI login = new LoginUI();
		    	login.displayLoginWindow();
		    	return;
			}
		}
							
	}
	
	public static void displayMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url = LoginUI.class.getResource("/img/info.png");
				Dialog<Pair<String, String>> dialog = new Dialog<>();
				dialog.setTitle("INFORMATION");
				dialog.setHeaderText(title);
				dialog.setContentText(information);
				dialog.setGraphic(new ImageView(url.toString()));
				dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
				dialog.showAndWait();
				}
		});
	}
	
	public static void displayErrorMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				URL url = LoginUI.class.getResource("/img/error.png");
				Dialog<Pair<String, String>> dialog = new Dialog<>();
				dialog.setTitle("ERROR");
				dialog.setHeaderText(title);
				dialog.setContentText(information);
				dialog.setGraphic(new ImageView(url.toString()));
				dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
				dialog.showAndWait();
			}
		});
	}
	
	
}