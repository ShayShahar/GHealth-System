package common.entity;

import common.enums.*;

public class Request{

    private final Command command; 

    private  String userID; 

    private Object entity = null, entity2 = null; 

    public Request(Command command, String userID, Object entity, Object entity2) {
				this.command = command;
				this.userID = userID;
				this.entity = entity;
				this.entity2 = entity2;
    }

    public Request(Command command, String userID) {
	    	this.command = command;
	    	this.userID = userID;
    }

    public Request(Command command, String userID, Object entity) {
				this.command = command;
				this.userID = userID;
				this.entity = entity;
		}

    public Request(Command command, Object entity) {
    		this.command = command;
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

    public Command getCommand() {
    		return command;
    }

    public String getUserID() {
    		return userID;
    }

}
