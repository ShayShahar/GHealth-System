package client.control;

import java.io.IOException;

import client.boundry.GeneralUI;
import ocsf.client.AbstractClient;

public class ClientController extends AbstractClient{

	public ClientController(String host, int port) throws IOException {
		super(host, port);
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		
	}
	
	private GeneralUI uiSelector(Object msg){
		
		
		return null;
	}
}