package client.entity;

public class Client extends Person{
	
	//class variables
	private String joinDate;
	private String leftDate;
	private Branch clinic;
	private boolean status;
	private MedicalFile medicalFile;
	
	public Client() {
		medicalFile = new MedicalFile();
	}
	
	//class properties
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getLeftDate() {
		return leftDate;
	}
	public void setLeftDate(String leftDate) {
		this.leftDate = leftDate;
	}
	public Branch getClinic() {
		return clinic;
	}
	public void setClinic(Branch clinic) {
		this.clinic = clinic;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public MedicalFile getMedicalFile() {
		return medicalFile;
	}

	public void setMedicalFile(MedicalFile medicalFile) {
		this.medicalFile = medicalFile;
	}

	
}