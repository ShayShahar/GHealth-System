package common.entity;

import common.enums.*;
import java.io.Serializable;

/**
 * General reply class used to handle replies from the server
 * Class implements Serializable
 * @author shays
 *
 */
public class Reply implements Serializable{

	private static final long serialVersionUID = 1L;	
	private final Object result;
  private final Command command;

    /**
     * Reply class constructor
     * @param result
     * @param command
     */
    public Reply(Object result, Command command) {
    	this.result = result;
    	this.command = command;

    }
    
    /**
     * Get result property from Reply instance
     * @return Object
     */
    
    public Object getResult() {
    	return this.result;
    }

    /**
     * Get command property from Reply instance
     * @return Enum Command
     */
    
    public Command getCommand() {
    	return this.command;
    }
    
}
