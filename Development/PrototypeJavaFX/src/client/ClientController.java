package client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ocsf.client.*;

public class ClientController extends AbstractClient implements Initializable{
	
	@FXML private TextField physcNameTxt;
	@FXML private TextField newSpeTxt;
	@FXML private Button loadBtn;
	@FXML private TableView<Physician> table;
	@FXML private TableColumn<Physician, String> pscName;
	@FXML private TableColumn<Physician, String>  pscSpec;

	public ClientController() throws IOException{
		super("localhost", 5551);
		openConnection();
	}

	public ClientController(String host, int port) throws IOException {
		super(host, port);
		openConnection();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pscName.setCellValueFactory(new PropertyValueFactory<>("name"));
		pscSpec.setCellValueFactory(new PropertyValueFactory<>("specialization"));	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void handleMessageFromServer(Object msg) {
		
	
		String opcode = ((ArrayList<String>) msg).get(0);
		String subMessage = ((ArrayList<String>) msg).get(1);
				
		if (opcode.equals("update")){
			if (subMessage.equals("error")){
				displayErrorMessage("Update Error","An error occured while trying to update your value.");
			}
			
			if (subMessage.equals("succeed")){
				displayMessage("Update Succeed","Your value successfuly updated in data-base");
			}
		}
		
		else if (opcode.equals("show")){
			onUpdateTableView((ArrayList<String>) msg);
		}
	}
	
	private void terminateClient() {
		
		try {
			closeConnection();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	private void displayMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setHeaderText(title);
				alert.setContentText(information);
				alert.showAndWait();					}
		});
	}
	
	private void displayErrorMessage (String title, String information){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText(title);
				alert.setContentText(information);
				alert.showAndWait();					}
		});
	}
	
	public void onUpdateValueClick(ActionEvent event){
		
		physcNameTxt.setStyle("-fx-prompt-text-fill: gray");
		newSpeTxt.setStyle("-fx-prompt-text-fill: gray");
		
		ArrayList<String> msg = new ArrayList<String>();
		msg.add("update");
		msg.add(physcNameTxt.getText());
		msg.add(newSpeTxt.getText());
		
		if (physcNameTxt.getText() == null || physcNameTxt.getText().trim().isEmpty() || newSpeTxt.getText() == null || newSpeTxt.getText().trim().isEmpty()){
			displayErrorMessage("Update Error", "Some required fields are missing.");
			if ((physcNameTxt.getText() == "" || physcNameTxt.getText().trim().isEmpty()) && (newSpeTxt.getText() == "" || newSpeTxt.getText().trim().isEmpty()) ){
				physcNameTxt.setStyle("-fx-prompt-text-fill: red");
				newSpeTxt.setStyle("-fx-prompt-text-fill: red");
			}
			else if ((physcNameTxt.getText() == "" || physcNameTxt.getText().trim().isEmpty()) && !(newSpeTxt.getText() == "" || newSpeTxt.getText().trim().isEmpty())){
				physcNameTxt.setStyle("-fx-prompt-text-fill: red");
			}
			else if (!(physcNameTxt.getText() == "" || physcNameTxt.getText().trim().isEmpty()) && (newSpeTxt.getText() == "" || newSpeTxt.getText().trim().isEmpty())){
				newSpeTxt.setStyle("-fx-prompt-text-fill: red");
			}
		}
		else{
		physcNameTxt.clear();
		newSpeTxt.clear();
		sendMessageToServer(msg);		
		}
		
	}
	
	public void onLoadTableClick(ActionEvent event){
		loadBtn.setText("Re-Load Table");
		ArrayList<String> msg = new ArrayList<String>();
		
		msg.add("show");
		
		sendMessageToServer(msg);		
	}
	
	protected void sendMessageToServer(Object msg){
		
		try{
			sendToServer(msg);
		}
		catch(IOException e){
			displayErrorMessage("Fatal Error","Could not send message to server. Terminating client");
			terminateClient();
		}
	}

	public ObservableList<Physician> getPhysician(ArrayList<String> list){
		ObservableList<Physician> physicians = FXCollections.observableArrayList();
		
		for (int i=1 ; i < list.size(); i++){
			physicians.add(new Physician(list.get(i++),list.get(i)));
		}
		
		return physicians;
	}
	
	public void onUpdateTableView(ArrayList<String> list){

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				table.setItems(getPhysician(list));
			}});
	}

	
}
	
	
