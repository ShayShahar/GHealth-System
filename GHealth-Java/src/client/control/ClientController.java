package client.control;

import java.io.IOException;
import common.entity.Reply;
import common.enums.User;
import ocsf.client.AbstractClient;

public class ClientController extends AbstractClient{

	public ClientController(String host, int port) throws IOException {
		super(host, port);
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {

		System.out.println("IN handleMessageFromServer ");

		ControllerSelector(msg);
		
	}
	
	private void ControllerSelector(Object msg){
		
		Reply reply = (Reply) msg;
		
		System.out.println("IN ControllerSelector ");
		
		if (reply.getUser() == User.LoginController){
			System.out.println("IN FIRST IF");
				UserController.handleReply(reply);
		}
		else if (reply.getUser() == User.LogoutController){
			//dosomething...
		}
		
		//UserController.handleReply(reply);


	}
}