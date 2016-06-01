package client.control;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import client.boundry.ClientHistoryUI;
import client.boundry.ViewReferenceUI;
import client.entity.Reference;
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
 * ViewReferenceController class handles the logics of ViewReferenceUI .
 *
 * @author shays
 */
public class ViewReferenceController implements IController, Initializable{

	/** The reference number. */
	@FXML private TextField referenceNumber;
	
	/** The field name. */
	@FXML private TextField fieldName;
	
	/** The field reference num. */
	@FXML private TextField fieldReferenceNum;
	
	/** The field client id. */
	@FXML private TextField fieldClientID;
	
	/** The field specielist id. */
	@FXML private TextField fieldSpecielistID;
	
	/** The field code. */
	@FXML private TextField fieldCode;
	
	/** The field date. */
	@FXML private TextField fieldDate;
	
	/** The field urgency. */
	@FXML private TextField fieldUrgency;
	
	/** The field status. */
	@FXML private TextField fieldStatus;
	
	/** The field type. */
	@FXML private TextField fieldType;
	
	/** The field comments. */
	@FXML private TextArea fieldComments;

	
	/** The this ui. */
	private IUi thisUi;
	
	/**
	 * Initialize class parameters.
	 *
	 * @param id Gets the reference's ID
	 * @param name Gets the specialist's name
	 */
	public void setDetails(String id, String name){
		referenceNumber.setText(id);
		fieldName.setText(name);

		findReferenceByID(id);
		
	}
	
	/**
	 * findReferenceByID creates a FIND_REFERENCE_BY_REFNUM request and send it to the server.
	 *
	 * @param id Gets the reference's ID
	 */
	public void findReferenceByID(String id){
		
		Request request = new Request(Command.FIND_REFERENCE_BY_REFNUM,id);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(request);
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
			if (ui instanceof ViewReferenceUI){
				thisUi = ui;
			}
		}
		
		
	}

	/* (non-Javadoc)
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	@Override
	public void handleReply(Reply reply) {
		
		Object result =  reply.getResult();

		
		if (reply.getCommand() == Command.FIND_REFERENCE_BY_REFNUM )
		{
					
			if(result instanceof Result){
				
			if ((Result)result == Result.ERROR){
				
				thisUi.displayErrorMessage ("Fatal error", "Error occured in system. Exit program.");
				System.exit(1);
			}
		 }
			
			else { 
		
					Reference reference = (Reference)result;
					fieldComments.setText(reference.getComments());
					fieldClientID.setText(Integer.toString(reference.getCId()));
					fieldSpecielistID.setText(Integer.toString(reference.getSId()));
					fieldCode.setText(Integer.toString(reference.getCode()));
					fieldUrgency.setText(reference.getUrgency());
					
					
					if(reference.getStatus() == 0)
						fieldStatus.setText("Not Checked");
					else 
						fieldStatus.setText("Checked");
							
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
					fieldDate.setText(df.format(reference.getDate().getTime()));
					fieldType.setText(reference.getType());
			}
			
		}		
	}

}
