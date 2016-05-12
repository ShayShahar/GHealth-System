package client.control;

import java.io.IOException;
import java.util.ArrayList;

import client.interfaces.IController;
import common.entity.Reply;
import common.entity.Request;
import common.enums.Command;
import javafx.event.ActionEvent;

public class ExtendedReportController implements IController{

	
	
	
	
	
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
	
}