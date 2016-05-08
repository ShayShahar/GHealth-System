package client.control;

import java.io.IOException;
import java.util.ArrayList;

import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import ocsf.client.AbstractClient;

public class ClientController extends AbstractClient{

	public IController controller;
	public ArrayList<IUi> userInterface = new ArrayList<IUi>();
	public String userPrivilege;
	public String userName;
	
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