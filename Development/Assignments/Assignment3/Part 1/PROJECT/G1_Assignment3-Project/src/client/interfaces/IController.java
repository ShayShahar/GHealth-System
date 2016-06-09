package client.interfaces;

import common.entity.Reply;
// TODO: Auto-generated Javadoc

/**
 * An interface that used to control and forward the reply from the server to the sender (controller).
 *
 * @author shays
 */
public interface IController {

	/**
	 * Handle and process the reply instance that created by the server application. 
	 *
	 * @param reply the reply
	 */
	abstract void handleReply(Reply reply);
	
}
