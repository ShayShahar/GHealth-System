package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.boundry.CreateLabReferenceUI;
import client.boundry.SpecialistUI;
import client.entity.Examination;
import client.entity.Hour;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
	
	//@FXML private TableColumn<Examination, String> specialistClmn;
	//@FXML private TableColumn<Examination, String> specialistNameClmn;
	@FXML private TableColumn<Examination, String> examinationCodeClmn;
	
	private IUi thisUi;
	private String personId;
	
		
	public void setUser(String pName,String fName,String phoneNumber,String add,String personId,String email){
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

	}
	
	public void onUpdateTableView(ArrayList<Examination> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				tabelExamination.setItems(getHours(list));
			}});
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dateClmn.setStyle( "-fx-alignment: CENTER;");
		dateClmn.setCellValueFactory(new PropertyValueFactory<>("examination"));
		examinationCodeClmn.setStyle( "-fx-alignment: CENTER;");
		examinationCodeClmn.setCellValueFactory(new PropertyValueFactory<>("examination"));
		
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
	
	

	
	public void onBackButtonClick(ActionEvent event){
		
		thisUi.hideWindow();
		
		for (IUi ui : ClientConnectionController.clientConnect.userInterface){
			if (ui instanceof SpecialistUI){
				ui.showWindow();
			}
		}
		ClientConnectionController.clientConnect.userInterface.remove(thisUi);
	}
	
	

	
	@SuppressWarnings("unchecked")
	@Override
	public void handleReply(Reply reply){
		 
		Object result =  reply.getResult();
		
		 if (reply.getCommand() == Command.FIND_CLIENT_EXAMINATION){
			
			ArrayList<String> examination = (ArrayList<String>)result;
			ArrayList<Examination> list = new ArrayList<Examination>();
			for (int i = 0 ; i<examination.size(); i++){
				Examination ex  = new Examination();
				ex.setDate(examination.get(i));
				ex.setExaminationCode(examination.get(++i));
				list.add(ex);
			}
			onUpdateTableView(list);
	}
				
		
	}

}	





