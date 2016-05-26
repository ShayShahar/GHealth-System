package client.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.interfaces.IController;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import javafx.fxml.Initializable;

public class TransferTableController implements IController,Initializable{

	public String specialist;
	public String clientId;
	
	public void setDetails(String clientId) {
		this.clientId = clientId;		
	}

	public void setDetails(String clientId, String specialist) {		
		askInfoForSpecificFile(clientId,specialist);
	}

	private void askInfoForSpecificFile(String clientId, String specialist) {
		
		ArrayList<String> list = new ArrayList<>();
		list.add(clientId);
		list.add(specialist);
Request requst = new Request(Command.FIND_MEDFILE,list);
		
		try {
			ClientConnectionController.clientConnect.controller = this;
			ClientConnectionController.clientConnect.sendToServer(requst);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}

}
