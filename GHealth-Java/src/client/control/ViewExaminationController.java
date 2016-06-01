package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.CreateExaminationUI;
import client.boundry.SpecialistUI;
import client.boundry.ViewExaminationUI;
import client.entity.Examination;
import client.entity.Reference;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
// TODO: Auto-generated Javadoc
/**
 * ViewExaminationController connects between the ViewExaminationUI to the components logics.
 * @author asaf
 *
 */
@SuppressWarnings("unused")
public class ViewExaminationController implements IController,Initializable{

	
	/** The Sp client id txt. */
	@FXML private TextField SpClientIDTxt;
	
	/** The field client name. */
	@FXML private TextField fieldClientName;
	
	/** The field client family. */
	@FXML private TextField fieldClientFamily;
	
	/** The field client address. */
	@FXML private TextField fieldClientAddress;
	
	/** The field client phone. */
	@FXML private TextField fieldClientPhone;
	
	/** The field client email. */
	@FXML private TextField fieldClientEmail;
	
	/** The tabel examination. */
	@FXML private TableView<Examination> tabelExamination;
	
	/** The date clmn. */
	@FXML private TableColumn<Examination, String> dateClmn;
	
	/** The specialist clmn. */
	@FXML private TableColumn<Examination, String> specialistClmn;
	
	/** The specialist name clmn. */
	@FXML private TableColumn<Examination, String> specialistNameClmn;
	
	/** The examination name clmn. */
	@FXML private TableColumn<Examination, String> examinationNameClmn;
	
	/** The serial clmn. */
	@FXML private TableColumn<Examination, String> serialClmn;
	
	/** The open btn. */
	@FXML private Button openBtn;
	
	/** The this ui. */
	private IUi thisUi;
	
	/** The serial no. */
	private int serialNo;
	
	/** The examination code. */
	private String examinationCode;
	
	/** The examination id. */
	private int examinationId;
	
	/** The user id. */
	private int userId;
	
	/** The client id. */
	private String clientId;
	
	/** The comments. */
	private String comments;
	
	/** The choosed urgency. */
	private String choosedUrgency;
	
	/** The choosed examination type. */
	private String choosedExaminationType;
	
	/** The user name. */
	private String userName;
	
	/** The id. */
	static int id;

	
	/**
	 * Set clientId ,Client Name,phone,family name, address,email to the current class scenario and text field.
	 *
	 * @param pName the name
	 * @param fName the f name
	 * @param personId the person id
	 * @param add the add
	 * @param phoneNumber the phone number
	 * @param email the email
	 */
		
	public void setUser(String pName,String fName,String personId,String add,String phoneNumber,String email){
		fieldClientName.setText(pName);
		SpClientIDTxt.setText(personId);
		fieldClientPhone.setText(phoneNumber);
		fieldClientFamily.setText(fName);
		fieldClientAddress.setText(add);
		fieldClientEmail.setText(email);
		fieldClientName.setEditable(false);
		SpClientIDTxt.setEditable(false);
		fieldClientPhone.setEditable(false);
		fieldClientFamily.setEditable(false);
		fieldClientAddress.setEditable(false);
		

		
		ArrayList<String> list = new ArrayList<String>();
		list.add(personId);
		
		Request requst = new Request(Command.FIND_CLIENT_EXAMINATION,list);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(requst);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * MedicalFile table observable.
	 *
	 * @param list Gets an ArrayList of Examination objects
	 * @return ObservableList<Examination>
	 */
	public ObservableList<Examination> getExamination(ArrayList<Examination> list){
		ObservableList<Examination> examinations = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			examinations.add(list.get(i));
		}
		
		return examinations;
	}

	/**
	 * This function updates the view of the medical file table elements.
	 * @param list Gets an ArrayList of MedicalFile objects
	 */
	
	public void onUpdateTableView(ArrayList<Examination> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				tabelExamination.setItems(getExamination(list));
			}});
	}
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	/*
	 * 	The initialize function initializes ViewExaminationUI screen and class members.
	 *  The function initializes tables'es columns.
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dateClmn.setStyle( "-fx-alignment: CENTER;");
		dateClmn.setCellValueFactory(new PropertyValueFactory<>("date"));
		examinationNameClmn.setStyle( "-fx-alignment: CENTER;");
		examinationNameClmn.setCellValueFactory(new PropertyValueFactory<>("examinationName"));
		specialistClmn.setStyle( "-fx-alignment: CENTER;");
		specialistClmn.setCellValueFactory(new PropertyValueFactory<>("specialist"));
		specialistNameClmn.setStyle( "-fx-alignment: CENTER;");
		specialistNameClmn.setCellValueFactory(new PropertyValueFactory<>("specialistName"));
		serialClmn.setStyle( "-fx-alignment: CENTER;");
		serialClmn.setCellValueFactory(new PropertyValueFactory<>("serial"));
		
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof ViewExaminationUI){
				thisUi = ui;
			}
		}
		
	}
	
	/*
	 * onBackButtonClick function is back button handler. 
	 * The function searches the last IUi instance in the UI stack and show the window.
	 * The function removes the current from the stack.
	 * @param event
	 */

	/**
	 * On back button click.
	 *
	 * @param event the event
	 */
	public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof SpecialistUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}
	
	/**
	 * onOpenButtonClick function handles a click on open button.
	 * The function calls openViewExamination to watch client examination.
	 *
	 * @param event the event
	 */
	public void onOpenButtonClick(ActionEvent event){
		
		openViewExamination(serialNo,examinationId,examinationCode);
		
	}
	/**
	 * openViewExamination function open client examination.
	 * @param serialNo Gets serial Number of the reference.
	 * @param examinationId Gets serial Number of the examination.
	 * @param examinationCode Gets type Number of the examination.
	 */
	
	public void openViewExamination(int serialNo,int examinationId,String examinationCode){		
		
		ExaminationController.currentReference = new Reference();
		ExaminationController.currentReference.setRefNum(serialNo);
		ExaminationController.currentReference.setCode(examinationId);
		ExaminationController.currentReference.setType(examinationCode);
		
		CreateExaminationUI create = new CreateExaminationUI();
	      ClientConnectionController.clientConnect.userInterface.add(create);
	      
	      for(IUi ui : ClientConnectionController.clientConnect.userInterface){
	       if (ui instanceof ViewExaminationUI){
	        ui.hideWindow();
	       }
	      }
	      
	      create.displayUserWindow();
		
		
	}
	
	/**
	 * On mouse click.
	 *
	 * @param event the event
	 */
	public void onMouseClick(MouseEvent event){
		
		try{
				if (tabelExamination.getSelectionModel().getSelectedItem().getExaminationName() != null  && tabelExamination.getSelectionModel().getSelectedItem().getSerial() != null){
					openBtn.setDisable(false);
					String examinationName =  tabelExamination.getSelectionModel().getSelectedItem().getExaminationName();
					examinationId =  Integer.parseInt(tabelExamination.getSelectionModel().getSelectedItem().getSerial());
				
					ArrayList<String> msg = new ArrayList<String>();
					msg.add(examinationName); 
					msg.add(Integer.toString(examinationId));
					Request request = new Request(Command.GET_EXAMINATION_NUMBER,msg);
					
					try {
						ClientConnectionController.clientConnect.controller = this;
						ClientConnectionController.clientConnect.sendToServer(request);
					} 
					catch (IOException e) {
						e.printStackTrace();
					}					
				}
				
		}catch(Exception e){
			openBtn.setDisable(true);

		}
	
	}
	
	
	
	/*
	 * The handle reply process the results of FIND_CLIENT_EXAMINATION & GET_EXAMINATION_NUMBER & GET_EXAMINATION_NUMBER requests.
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */

	
	/* (non-Javadoc)
	 * @see client.interfaces.IController#handleReply(common.entity.Reply)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply){
		 
		Object result =  reply.getResult();
		
		 if (reply.getCommand() == Command.FIND_CLIENT_EXAMINATION){
			
			 if (result instanceof ArrayList<?>){
					ArrayList<Examination> examination = (ArrayList<Examination>)result;
					onUpdateTableView(examination); 
			 }
			 else{
				 
				 thisUi.displayErrorMessage("Error", "There are no reference to show!");
				
				 thisUi.hideWindow();
					
					for (IUi ui : ClientConnectionController.clientConnect.userInterface){
						if (ui instanceof SpecialistUI){
							ui.showWindow();
						}
					}
					ClientConnectionController.clientConnect.userInterface.remove(thisUi);
			 }
			 
	
		 
		 }
		 else if (reply.getCommand() == Command.GET_EXAMINATION_NUMBER){
			 
			 if (result instanceof ArrayList<?>){
					ArrayList<Integer> list = (ArrayList<Integer>)result;
			 examinationCode = Integer.toString(list.get(0));
			 serialNo = list.get(1);
			 
		 }
				
		
	}

	}	
}




