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
    private ArrayList<String> list = null;
    private Object entity = null, entity2 = null; 

    public Request(Command command, ArrayList<String> list, Object entity, Object entity2) {
				this.command = command;
				this.list = list;
				this.entity = entity;
				this.entity2 = entity2;
    }

    public Request(Command command, ArrayList<String> list) {
	    	this.command = command;
	    	this.list = list;

    }

    public Request(Command command, ArrayList<String> list, Object entity) {
				this.command = command;
				this.list = list;

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
    
    public ArrayList<String> getList(){
    	return list;
    }
   
    public Command getCommand() {
    		return command;
    }
    

}
