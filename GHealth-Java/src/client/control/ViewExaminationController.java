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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/**
 * ViewExaminationController connects between the ViewExaminationUI to the components logics.
 * @author asaf
 *
 */
public class ViewExaminationController implements IController,Initializable{

	public int userId;
	public static int id;
	public String clientId;
	public String comments;
	public String choosedUrgency;
	public String choosedExaminationType;
	public String userName;
	
	@FXML private TextField SpClientIDTxt;
	@FXML private TextField fieldClientName;
	@FXML private TextField fieldClientFamily;
	@FXML private TextField fieldClientAddress;
	@FXML private TextField fieldClientPhone;
	@FXML private TextField fieldClientEmail;
	@FXML private TableView<Examination> tabelExamination;
	@FXML private TableColumn<Examination, String> dateClmn;
	@FXML private TableColumn<Examination, String> specialistClmn;
	@FXML private TableColumn<Examination, String> specialistNameClmn;
	@FXML private TableColumn<Examination, String> examinationNameClmn;
	@FXML private TableColumn<Examination, String> serialClmn;
	
	private IUi thisUi;
	private int serialNo;
	private String examinationCode;
	private int examinationId;
	/**
	 * Set clientId ,Client Name,phone,family name, address,email to the current class scenario and text field
	 * @param id Gets client's ID
	 * @param fieldClientName Gets personal Name
	 * @param SpClientIDTxt Gets person Id
	 * @param fieldClientFamily Gets Family name
	 * @param fieldClientAddress Gets adderss
	 * @param fieldClientEmail Gets email
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
	 * MedicalFile table observable
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
	 * @param event
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
	
	public void onMouseClick(MouseEvent event){
		
		try{
				if (tabelExamination.getSelectionModel().getSelectedItem().getExaminationName() != null  && tabelExamination.getSelectionModel().getSelectedItem().getSerial() != null){
			
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
		}
	
	}
	
	
	
	/*
	 * The handle reply process the results of FIND_CLIENT_EXAMINATION & GET_EXAMINATION_NUMBER & GET_EXAMINATION_NUMBER requests.
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




