package common.entity;

import java.util.ArrayList;
import java.io.Serializable;
import common.enums.*;

/**
 * An object that created to transfer data from client application to the server.
 * Server application processes client's requests by using the types in Request class.
 * Class implements Serializable
 * @author shays
 *
 */

public class Request implements Serializable{

	private static final long serialVersionUID = 1L;

	private final Command command; 
    private ArrayList<String> list = null;
    private Object entity = null, entity2 = null; 

    /**
     * Request class constructor
     * @param command Gets the request op-code
     * @param list Gets an array list of string that includes required information for the queries
     * @param entity Gets an entity to add to the mySQL database
     * @param entity2 Gets an entity to add to the mySQL database
     */
    
    public Request(Command command, ArrayList<String> list, Object entity, Object entity2) {
				this.command = command;
				this.list = list;
				this.entity = entity;
				this.entity2 = entity2;
    }
    
    /**
     * Request class constructor
     * @param command Gets the request op-code
     * @param list Gets an array list of string that includes required information for the queries
     */

    public Request(Command command, ArrayList<String> list) {
	    	this.command = command;
	    	this.list = list;

    }

    /**
     * Request class constructor
     * @param command Gets the request op-code
     * @param list Gets an array list of string that includes required information for the queries
     * @param entity Gets an entity to add to the mySQL database
     */
    
    public Request(Command command, ArrayList<String> list, Object entity) {
				this.command = command;
				this.list = list;

				this.entity = entity;
		}
    
    /**
     * Request class constructor
     * @param command Gets the request op-code
     * @param entity Gets an entity to add to the mySQL database   
     */

    public Request(Command command, Object entity) {
    		this.command = command;
    		this.entity = entity;
    }

    /**
     *  Get the object that stored in the entity property
     * @return Object type
     */
    
    public Object getEntity() {
    		return entity;
    }

    /**
     * Load an entity to the property
     * @param entity gets an Object type.
     */
    
    public void setEntity(Object entity) {
    		this.entity = entity;
    }

    
    /**
     * Get the second entity that stored in Request instance
     * @return Object type
     */
    
    public Object getEntity2() {
    		return entity2;
    }

    /**
     * Load a second entity to the Request instance
     * @param entity2 gets an Object type.
     */
    
    public void setEntity2(Object entity2) {
    		this.entity2 = entity2;
    }
    
    /**
     * Get the string arraylist
     * @return the stored arraylist of strings with the required information
     */
    public ArrayList<String> getList(){
    	return list;
    }
   
    /**
     * Get the op-opcode from the request instance
     * @return Enum Command
     */
    public Command getCommand() {
    		return command;
    }

}
