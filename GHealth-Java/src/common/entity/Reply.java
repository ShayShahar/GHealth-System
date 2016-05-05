package common.entity;

import common.enums.*;
import java.io.Serializable;


public class Reply implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private final Object result;
    private final Command command;

    public Reply(Object result, Command command) {
    	this.result = result;
    	this.command = command;

    }
    
    public Object getResult() {
    	return this.result;
    }

    public Command getCommand() {
    	return this.command;
    }
    
    
}
