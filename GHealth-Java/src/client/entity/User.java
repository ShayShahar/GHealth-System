package client.entity;
/**
 * User class defines the workers information including 
 * their user names , passwords and privilege
 * the class extends Person
 * @author YAKIR
 *
 */
public class User extends Person{
	

	private static final long serialVersionUID = 1L;
	protected enum Privilege {DISPATCHER, LAB_WORKER, GENERAL_MANAGER, BRANCH_MANAGER, SPECIALIST};
	
	//Class Members
	protected String username;
	protected String password;
	protected long workerID;
	protected boolean status;
	protected Privilege userPrivilege;
	
	//Class Properties
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getWorkerID() {
		return workerID;
	}
	public void setWorkerID(long workerID) {
		this.workerID = workerID;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Privilege getUserPrivilege() {
		return userPrivilege;
	}
	public void setUserPrivilege(Privilege userPrivilege) {
		this.userPrivilege = userPrivilege;
	}
	
}