package client.control;

import java.io.IOException;
import common.entity.Reply;
import common.enums.User;
import ocsf.client.AbstractClient;

public class ClientController extends AbstractClient{

	public IController controller;
	
	public ClientController(String host, int port) throws IOException {
		super(host, port);
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		Reply reply = (Reply) msg;
		controller.handleReply(reply);
	}

	
}