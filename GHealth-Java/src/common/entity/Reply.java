package common.entity;

import common.enums.*;
import java.io.Serializable;


public class Reply implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private final Object result;
    private final Command command;
    private final User user;

    public Reply(Object result, Command command, User user) {
    	this.result = result;
    	this.command = command;
    	this.user = user;

    }
    
    public Object getResult() {
    	return this.result;
    }

    public Command getCommand() {
    	return this.command;
    }
    
    public User getUser(){
    	return this.user;
    }
    
}
