package client.entity;


// TODO: Auto-generated Javadoc
/**
 * Specialist class defines the specialists information including 
 * his branch name and address
 * the class extends Person.
 */

public class Specialist extends Person{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The speciality. */
	//Class Members
	private String speciality;
	
	/** The branch name. */
	private String branchName;
	
	/** The branch address. */
	private String branchAddress;

	/**
	 * Instantiates a new specialist.
	 *
	 * @param id the id
	 * @param name the name
	 * @param family the family
	 * @param speciality the speciality
	 * @param branchName the branch name
	 * @param branchAddress the branch address
	 */
	//Constructors
	public Specialist(String id, String name, String family, String speciality, String branchName, String branchAddress) {
		this.id = id;
		this.name = name;
		this.familyName = family;
		this.speciality = speciality;
		this.branchName = branchName;
		this.branchAddress = branchAddress;

	}
	
	/**
	 * Gets the speciality.
	 *
	 * @return the speciality
	 */
	//Class Properties
	public String getSpeciality() {
		return speciality;
	}
	
	/**
	 * Sets the speciality.
	 *
	 * @param speciality the new speciality
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	/**
	 * Gets the branch name.
	 *
	 * @return the branch name
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * Sets the branch name.
	 *
	 * @param branchName the new branch name
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * Gets the branch address.
	 *
	 * @return the branch address
	 */
	public String getBranchAddress() {
		return branchAddress;
	}

	/**
	 * Sets the branch address.
	 *
	 * @param branchAddress the new branch address
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	
}