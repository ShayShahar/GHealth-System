package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.CreateExaminationUI;
import client.boundry.CreateLabReferenceUI;
import client.boundry.SpecialistUI;
import client.boundry.ViewExaminationUI;
import client.entity.Examination;
import client.entity.Hour;
import client.entity.Reference;
import client.entity.Specialist;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
	
	private IUi thisUi;
	private String personId;
	
		
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
		
		this.personId = personId;

		
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
	
	public ObservableList<Examination> getExamination(ArrayList<Examination> list){
		ObservableList<Examination> examinations = FXCollections.observableArrayList();
		
		for (int i=0 ; i < list.size(); i++){
			examinations.add(list.get(i));
		}
		
		return examinations;
	}
	


	
	public void onUpdateTableView(ArrayList<Examination> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				tabelExamination.setItems(getExamination(list));
			}});
	}
	
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
		
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof ViewExaminationUI){
				thisUi = ui;
			}
		}
		
	}
	
	

	
	public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof SpecialistUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}
	
	public void onOpenButtonClick(ActionEvent event){
		
		ExaminationController.Curr_Ref = new Reference();
		ExaminationController.Curr_Ref.setRefNum(1);
		ExaminationController.Curr_Ref.setCode(1);
		ExaminationController.Curr_Ref.setType(Integer.toString(2000));
		
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
				if (tabelExamination.getSelectionModel().getSelectedItem().getHour() != null){
				int hour = tabelAppointment.getSelectionModel().getSelectedItem().getHour();
					
			
					ArrayList<String> msg = new ArrayList<String>();
					msg.add(Integer.toString(hour));
					msg.add(userName);   
					Request request = new Request(Command.GET_CLIENT_BY_APPOINTMET,msg);
					
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
				
		
	}

}	





