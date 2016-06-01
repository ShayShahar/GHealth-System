package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import client.boundry.ClientHistoryUI;
import client.boundry.ViewAppointmentRecordUI;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import common.enums.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

// TODO: Auto-generated Javadoc
/**
 * ViewAppointmentController handles the logics of ViewAppointmentRecordUI.
 *
 * @author shays
 */
public class ViewAppointmentController implements IController, Initializable {

	/** The field client id. */
	@FXML private TextField fieldClientID;
	
	/** The field specielist id. */
	@FXML private TextField fieldSpecielistID;
	
	/** The field date. */
	@FXML private TextField fieldDate;
	
	/** The field name. */
	@FXML private TextField fieldName;
	
	/** The field price. */
	@FXML private TextField fieldPrice;
	
	/** The appointment id field. */
	@FXML private TextField appointmentIdField;
	
	/** The field review. */
	@FXML private TextArea fieldReview;

	
	/** The this ui. */
	private IUi thisUi;

		/**
		 * Initialize class parameters.
		 *
		 * @param clientID Gets client's ID
		 * @param date Gets the appointment's date
		 * @param name Gets the Specialist name
		 * @param appointmentID Gets the appointment's ID
		 */
	public void setDetails(String clientID, String date, String name, String appointmentID){
		fieldClientID.setText(clientID);
		fieldDate.setText(date);
		fieldName.setText(name);
		appointmentIdField.setText(appointmentID);

		getAppointmentReview(appointmentID);
		
	}
	
	/**
	 * getAppointmentReview function creates a GET_APPOINTMENT_REVIEW request and send it to the server.
	 *
	 * @param appointmentID the appointment id
	 * @return the appointment review
	 */
	public void getAppointmentReview(String appointmentID)
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add(appointmentID);
		
		Request requst = new Request(Command.GET_APPOINTMENT_REVIEW,list);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(requst);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * On back button click.
	 *
	 * @param event the event
	 */
	/*
	 * onBackButtonClick function is back button handler. 
	 * The function searches the last IUi instance in the UI stack and show the window.
	 * The function removes the current from the stack.
	 * @param event
	 */
	public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof ClientHistoryUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}
	
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof ViewAppointmentRecordUI){
				thisUi = ui;
			}
		}		
	}

	/* (non-Javadoc)
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply) {

		Object result =  reply.getResult();

		if (reply.getCommand() == Command.GET_APPOINTMENT_REVIEW)
		{
			
			if (result instanceof ArrayList<?>){
				
				ArrayList<String> list = (ArrayList<String>)result;
				fieldReview.setText(list.get(0));
				fieldPrice.setText(list.get(1));
				fieldSpecielistID.setText(list.get(2));
			}
			
			else if(result instanceof Result){
				
			if ((Result)result == Result.ERROR){
				
				thisUi.displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
				System.exit(1);
			}
			
		}
			
	}
		
	}

}
