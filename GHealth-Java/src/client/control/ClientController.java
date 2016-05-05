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
		ControllerSelector(msg);
	}
	
	private void ControllerSelector(Object msg){
		
		Reply reply = (Reply) msg;
		
		if (reply.getUser() == User.LoginController){
			UserController.handleReply(reply);
		}
		else if (reply.getUser() == User.ClientDetailsController){
			new ClientDetailsController().handleReply(reply);
		}

	}
}