package client.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Examination implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//class variables
	private int id , Ref_id;
	private String date;
	private String specialistName;
	private String specialist;
	private String examinationName;
	private String details;
	private ArrayList<byte[]> pictures;
	
	
	//class properties
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public ArrayList<byte[]> getPictures() {
		return pictures;
	}
	public void setPictures(ArrayList<byte[]> pictures) {
		this.pictures = pictures;
	}
	public int getRef_id() {
		return Ref_id;
	}
	public void setRef_id(int ref_id) {
		Ref_id = ref_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSpecialistName() {
		return specialistName;
	}
	public void setSpecialistName(String specialistName) {
		this.specialistName = specialistName;
	}
	public String getSpecialist() {
		return specialist;
	}
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	public String getExaminationName() {
		return examinationName;
	}
	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}
	
}
	
