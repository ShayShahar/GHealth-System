package common.entity;

import java.util.ArrayList;
import java.io.Serializable;


import common.enums.*;

public class Request implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Command command; 
	private final User user;

    private ArrayList<String> list = null;
    private Object entity = null, entity2 = null; 

    public Request(Command command, ArrayList<String> list, User user, Object entity, Object entity2) {
				this.command = command;
				this.user = user;
				this.list = list;
				this.entity = entity;
				this.entity2 = entity2;
    }

    public Request(Command command, ArrayList<String> list, User user) {
	    	this.command = command;
	    	this.list = list;
			  this.user = user;

    }

    public Request(Command command, ArrayList<String> list, User user, Object entity) {
				this.command = command;
				this.list = list;
				 this.user = user;
				this.entity = entity;
		}

    public Request(Command command,User user, Object entity) {
    		this.command = command;
			  this.user = user;
    		this.entity = entity;
    }

    public Object getEntity() {
    		return entity;
    }

    public void setEntity(Object entity) {
    		this.entity = entity;
    }


    public Object getEntity2() {
    		return entity2;
    }

    public void setEntity2(Object entity2) {
    		this.entity2 = entity2;
    }
    
    public ArrayList<String> getList(){
    	return list;
    }
   
    public Command getCommand() {
    		return command;
    }
    
    public User getUser(){
    	return user;
    }
}
