package client;

import java.io.IOException;

import ocsf.client.*;

public class GHealthClient extends AbstractClient{

	public GHealthClient(String host, int port) throws IOException {
		super(host, port);
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		displayMessage(msg.toString());
	}
	
	public void handleMessageFromClient(Object msg){
		try{
			sendToServer(msg);
		}
		catch(IOException e){
			displayMessage("Could not send message to server. Terminating client");
			terminateClient();
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

	private void displayMessage (Object message){
		System.out.println("-->" + message);
	}
}
