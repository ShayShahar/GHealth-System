package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import client.boundry.*;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * DispatcherDetailsController handles the logics of the Dispatcher screen.
 * The class implements IController and Initializable interfaces.
 * @author shays
 *
 */

public class DispatcherDetailsController implements IController, Initializable{
	
	
	@FXML private Button dispLogoutBtn;
	@FXML private Button createAppointmentBtn;
	@FXML private Button cancelAppointmentBtn;
	@FXML private Button dispCreateClientBtn;
	@FXML private Button SpViewHistoryBtn;
	@FXML private Button SpEndTreatmentBtn;
	@FXML private Button SpViewExaminationsBtn;
	@FXML private Button SpCreateRefernceBtn;
	@FXML private Button SpRecordAppointmentBtn;
	@FXML private Button SpReportMissingBtn;
	@FXML private Button removeBtn;
	@FXML private Button retreiveBtn;
	@FXML private TextField dispClientIDTxt;
	@FXML private TextField fieldClientID;
	@FXML private TextField fieldClientClinic;
	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientJoin;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	@FXML private TextField SpClientIDTxt;

	public static String clientID;
	public static int id;
	private IUi thisUi;
		
	
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
	
	/**
	 * validateID function checks if the entered ID of the client is valid.
	 * An Israeli ID contains 9-digits.
	 * @param id Get the text from the ID TexField
	 * @return true if the validate success, false if the validation fails.
	 */
	
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
	
	/**
	 * onFindClientIDButtonClick function handles the logics of the search client button.
	 * @param event
	 */
	
	public void onFindClientIDButtonClick(ActionEvent event){
		
		
		fieldClientID.clear();
		fieldClientName.clear();
		fieldClientFamily.clear();
		fieldClientJoin.clear();
		fieldClientAddress.clear();
		fieldClientPhone.clear();
		fieldClientEmail.clear();
		fieldClientClinic.clear();
		
		
		dispCreateClientBtn.setDisable(true);
		
		dispClientIDTxt.setStyle("-fx-prompt-text-fill: gray");

		if (dispClientIDTxt.getText() == null || dispClientIDTxt.getText().trim().isEmpty()){
			ClientConnectionController.clientConnect.userInterface.get(0).displayErrorMessage("Search Error", "Missing required fields. Check your input and try again.");
			
			if (dispClientIDTxt.getText() == null || dispClientIDTxt.getText().trim().isEmpty()){
				dispClientIDTxt.setStyle("-fx-prompt-text-fill: #ffa0a0");
			}
			
			return;
		}
		
		boolean check = validateID(dispClientIDTxt.getText());
		
		if (check == false){
			return;
		}

		clientID = dispClientIDTxt.getText();
		
		findClient(clientID);
	}
	
	
	/**	
	 * findClient function sends a FIND_CLIENT request to the server.
	 * @param id Gets the person's ID to search
	 */
	public void findClient(String id){
		ArrayList<String> client = new ArrayList<String>();

		client.add(id);
		
		Request request = new Request(Command.FIND_CLIENT, client);

		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * onCreateAppointmentButtonClick function handles the logics of create appointment button click
	 * The function request an approved reference ID.
	 * The function sends a validation request to the server.
	 * @param event
	 */

	public void onCreateAppointmentButtonClick(ActionEvent event){
		
				URL url = ClientConnectionController.class.getResource("/img/question.png");
				Dialog<Pair<String, String>> dialog = new Dialog<>();
				dialog.setTitle("Insert Reference");
				dialog.setGraphic(new ImageView(url.toString()));
				dialog.setHeaderText("Validate reference number");
				//dialog.setContentText(information);
		    // Set the button types.
		    ButtonType validate = new ButtonType("OK", ButtonData.OK_DONE);
		    dialog.getDialogPane().getButtonTypes().addAll(validate, ButtonType.CANCEL);

		    GridPane gridPane = new GridPane();
		    gridPane.setHgap(10);
		    gridPane.setVgap(10);
		    gridPane.setPadding(new Insets(20, 120, 10, 10));

		    TextField refNumber = new TextField();
		    refNumber.setPromptText("Reference Number");

		    gridPane.add(new Label("Reference number:"), 0, 0);
		    gridPane.add(refNumber, 1, 0);

		    dialog.getDialogPane().setContent(gridPane);

		    // Request focus on the username field by default.
		    Platform.runLater(() -> refNumber.requestFocus());

		    dialog.setResultConverter(dialogButton -> {
		        if (dialogButton == validate) {
		        		
		        	if (refNumber.getText() == null || refNumber.getText().isEmpty()){
		        		thisUi.displayErrorMessage("Missing Fields", "Missing reference number. please insert the reference number and try again.");
		        	}
		        	
		        	else{
		        		
			        	ArrayList<String> msg = new ArrayList<String>();
			        	msg.add(fieldClientID.getText());
			        	msg.add(refNumber.getText());
			        	
			    			Request request = new Request(Command.VALIDATE_REFERENCE, msg);

				    		try {
				    			ClientConnectionController.clientConnect.controller = this;
				    			ClientConnectionController.clientConnect.sendToServer(request);
				    		} catch (IOException e) {
				    			e.printStackTrace();
				    		}
		        	}
		        }
		        return null;
		    });

		    Optional<Pair<String, String>> result = dialog.showAndWait();

		    result.ifPresent(pair -> {
		    });
		
	}
	
	/**
	 * onCancelAppointmentButtonClick function handles the logics of cancel appointment button click
	 * The function creates a new CancelAppointmentUI instance and display the window on the screen.
	 * @param event
	 */
	
	public void onCancelAppointmentButtonClick(ActionEvent event){
		CancelAppointmentUI cancel = new CancelAppointmentUI(dispClientIDTxt.getText());
		ClientConnectionController.clientConnect.userInterface.get(1).hideWindow();
		ClientConnectionController.clientConnect.userInterface.add(cancel);
		cancel.displayUserWindow();	
	}
	
	
	/**
	 * onRetrieveClientButtonClick function handles the logics of retrieve client button click
	 * The function calls returnClient function
	 * @param event
	 */
	
	public void onRetrieveClientButtonClick(ActionEvent event){
		
		returnClient(fieldClientID.getText());
	}
	
	/**
	 * The function creates a Return client request and sends the request with the client's ID to the server.
	 * @param id Gets person's ID
	 */
	public void returnClient(String id){
		
		ArrayList<String> client = new ArrayList<String>();
		
		client.add(id);
		
		Request request = new Request(Command.RETURN_CLIENT, client);

		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	/**
	 * onCreateClientButtonClick function handles the logics of create client button click
	 * In case the requested client is not found in the DB the create client button is click-able
	 * The function creates a new CreateClientUI instance and display the window on the screen.
	 * @param event
	 */
	
	public void onCreateClientButtonClick(ActionEvent event){
		
		CreateClientUI create = new CreateClientUI(dispClientIDTxt.getText());
		ClientConnectionController.clientConnect.userInterface.get(1).hideWindow();
		ClientConnectionController.clientConnect.userInterface.add(create);
		create.displayUserWindow();		
	}

	
	/**
	 * onRemoveClientButtonClick function handles the logics of remove client button click
	 * The function calls removeClient function
	 * @param event
	 */
	public void onRemoveClientButtonClick(ActionEvent event){
		removeClient(fieldClientID.getText());
	}
	
	/**
	 * The function sends a request of Remove client to the server with the client's ID.
	 * @param id Gets person's ID
	 */
	public void removeClient(String id){
		
		ArrayList<String> client = new ArrayList<String>();
		
		client.add(id);
		
		Request request = new Request(Command.REMOVE_CLIENT, client);

		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
/*
 * The handle reply process the results of LOGOUT, FIND_CLIENT, REMOVE_CLIENT, RETURN_CLIENT & VALIDATE_REFERENCE requests.
 * @see client.interfaces.IController#handleReply(common.entity.Reply)
 */
@Override
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
								thisUi.hideWindow();	
							 	ClientConnectionController.clientConnect.userInterface.remove(thisUi);
								ClientConnectionController.clientConnect.userInterface.get(0).showWindow();
								ClientConnectionController.clientConnect.userInterface.get(0).displayMessage("Logged out", "Your user is logged out from Ghealth system.");
						}
				}
		}
		
		else if (reply.getCommand() == Command.FIND_CLIENT){
			
			if (result instanceof ArrayList<?>){
				
				result = (ArrayList<?>) result;
			  @SuppressWarnings("unchecked")
			ArrayList<String> res = (ArrayList<String>) result;
			  
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						fieldClientID.setText(res.get(0));
						id = Integer.parseInt(fieldClientID.getText());
						fieldClientClinic.setText(res.get(1));
						String[] date = res.get(3).split("-");
						fieldClientJoin.setText(date[2]+"-"+date[1]+"-"+date[0]);
						fieldClientName.setText(res.get(4));
						fieldClientFamily.setText(res.get(5));
						fieldClientAddress.setText(res.get(8));
						fieldClientPhone.setText(res.get(7));
						fieldClientEmail.setText(res.get(6));
						
						
						
						if (Integer.parseInt(res.get(2)) == 1){
							createAppointmentBtn.setDisable(false);
							cancelAppointmentBtn.setDisable(false);
							removeBtn.setVisible(true);

						}
						else {
							thisUi.displayErrorMessage ("Client Left", "This client has left IHealth, to retrive client please choose the option from the menu below.");
							createAppointmentBtn.setDisable(true);
							cancelAppointmentBtn.setDisable(true);
							retreiveBtn.setVisible(true);
							removeBtn.setVisible(false);

						}

					}
						
				});
						  
			}
			
			else {
				thisUi.displayErrorMessage ("Client not found", "You can add new client from the menu below.");
				
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						dispCreateClientBtn.setDisable(false);
						createAppointmentBtn.setDisable(true);
						cancelAppointmentBtn.setDisable(true);
						}
				});
				
			}
			
		}
		
		else if (reply.getCommand() == Command.REMOVE_CLIENT){
			ClientConnectionController.clientConnect.userInterface.get(1).displayMessage("Client Removed", "The client removed successfuly from GHealth System.");	
			dispCreateClientBtn.setDisable(true);
			createAppointmentBtn.setDisable(true);
			cancelAppointmentBtn.setDisable(true);
			retreiveBtn.setVisible(true);
			removeBtn.setVisible(false);

			
		}
		
		else if (reply.getCommand() == Command.RETURN_CLIENT){
			
			ClientConnectionController.clientConnect.userInterface.get(1).displayMessage("Client Retrieved", "The client succesfuly returned to IHealth.");	
			dispCreateClientBtn.setDisable(true);
			createAppointmentBtn.setDisable(false);
			cancelAppointmentBtn.setDisable(false);
			retreiveBtn.setVisible(false);
			removeBtn.setVisible(true);
			
		}
			
		else if (reply.getCommand() == Command.VALIDATE_REFERENCE){
			
			if ((Result)result == Result.OK){
				CreateAppointmentUI create = new CreateAppointmentUI(clientID,id);
				ClientConnectionController.clientConnect.userInterface.add(create);
				
				for(IUi ui : ClientConnectionController.clientConnect.userInterface){
					if (ui instanceof DispatcherUI){
						ui.hideWindow();
					}
				}
				
				create.displayUserWindow();
				
			}
			
			else {
				thisUi.displayErrorMessage("Validatation failed", "Please contact client's clinic to approve the reference.");
			}
			
		}
					
		
	}


/*
 * The initialize function initializes the DispatcherUI screen and class members.
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		Request request = new Request(Command.SEND_NOTIFICATION, null);

		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		removeBtn.setVisible(false);
		retreiveBtn.setVisible(false);
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof DispatcherUI){
				thisUi = ui;
			}
		}

	}
	
}