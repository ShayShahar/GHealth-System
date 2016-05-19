package client.interfaces;

import common.entity.Reply;
/**
 * An interface that used to control and forward the reply from the server to the sender (controller)
 * @author shays
 *
 */
public interface IController {

	/**
	 * Handle and process the reply instance that created by the server application. 
	 * @param reply
	 */
	abstract void handleReply(Reply reply);
	
}
