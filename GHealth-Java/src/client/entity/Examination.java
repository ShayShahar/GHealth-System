package client.entity;

import java.io.Serializable;
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
	private String details;
	private ArrayList<byte[]> pictures = new ArrayList<byte[]>();
	
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
	
}