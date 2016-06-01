package client.control;

import java.io.IOException;
import java.util.ArrayList;
import client.interfaces.IController;
import client.interfaces.IUi;
import common.entity.Reply;
import ocsf.client.AbstractClient;

// TODO: Auto-generated Javadoc
/**
 * ClientController inherits from AbstractClient and creates the connection to server.
 * @author shays
 *
 */
public class ClientController extends AbstractClient{

	/**
	 * Store an instance of a class that implements IController interface.
	 * Usually used to save last used controller to handle server replies. 
	 */
	public IController controller;
	
	/**
	 * ArrayList of IUi implements a stack of GUI windows.
	 * Helps to manage the displayed windows and switch between screens.
	 */
	public ArrayList<IUi> userInterface = new ArrayList<IUi>();
	
	/**
	 * Stores the connected user's privilege level.
	 */
	public String userPrivilege;
	
	/** Stores the users's username. */
	public String userName;
	
	/**
	 * ClientController constructor.
	 *
	 * @param host Gets the IP Address of the server
	 * @param port Gets the port.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ClientController(String host, int port) throws IOException {
		super(host, port);
		openConnection();
	}

	/* (non-Javadoc)
	 * @see ocsf.client.AbstractClient#handleMessageFromServer(java.lang.Object)
	 */
	@Override
	protected void handleMessageFromServer(Object msg) {
		Reply reply = (Reply) msg;
		controller.handleReply(reply);
	}
	
	
}