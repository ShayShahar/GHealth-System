package client.entity;
// TODO: Auto-generated Javadoc

/**
 * User class defines the workers information including 
 * their user names , passwords and privilege
 * the class extends Person.
 *
 * @author YAKIR
 */
public class User extends Person{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The Enum Privilege.
	 */
	protected enum Privilege {
/** The dispatcher. */
DISPATCHER, 
 /** The lab worker. */
 LAB_WORKER, 
 /** The general manager. */
 GENERAL_MANAGER, 
 /** The branch manager. */
 BRANCH_MANAGER, 
 /** The specialist. */
 SPECIALIST};
	
	/** The username. */
	//Class Members
	protected String username;
	
	/** The password. */
	protected String password;
	
	/** The worker id. */
	protected long workerID;
	
	/** The status. */
	protected boolean status;
	
	/** The user privilege. */
	protected Privilege userPrivilege;
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	//Class Properties
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the worker id.
	 *
	 * @return the worker id
	 */
	public long getWorkerID() {
		return workerID;
	}
	
	/**
	 * Sets the worker id.
	 *
	 * @param workerID the new worker id
	 */
	public void setWorkerID(long workerID) {
		this.workerID = workerID;
	}
	
	/**
	 * Checks if is status.
	 *
	 * @return true, if is status
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Gets the user privilege.
	 *
	 * @return the user privilege
	 */
	public Privilege getUserPrivilege() {
		return userPrivilege;
	}
	
	/**
	 * Sets the user privilege.
	 *
	 * @param userPrivilege the new user privilege
	 */
	public void setUserPrivilege(Privilege userPrivilege) {
		this.userPrivilege = userPrivilege;
	}
	
}