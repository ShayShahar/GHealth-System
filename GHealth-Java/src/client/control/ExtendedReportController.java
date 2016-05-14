package client.control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.interfaces.IController;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class ExtendedReportController implements IController, Initializable{

	@FXML private ComboBox selectBranchList;
	@FXML private TextField numberTxt;
	@FXML private Spinner monthStartTxt;
	@FXML private Spinner monthEndTxt;
	@FXML private Spinner yearStartTxt;
	@FXML private Spinner yearEndTxt;
	

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
	
	
	@Override
	public void handleReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	//		monthStartTxt.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1950, 2070, LocalDate.now().getYear()));
	//		monthStartTxt.visibleProperty().bind(Bindings.notEqual(cbTimeRange.valueProperty(), TimeRangeType.LAST_12_MONTHS));
		
	}
	
}