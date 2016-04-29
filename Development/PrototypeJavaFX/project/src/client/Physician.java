package client;

public class Physician {
	private final String name;
	private final String specialization;
	
	public Physician(String str1, String str2){
		name = str1;
		specialization = str2;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSpecialization(){
		return specialization;
	}
}
