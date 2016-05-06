package client.entity;

import java.io.Serializable;
import java.util.List;

import javafx.scene.image.Image;

public class Examination implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//class variables
	private int id;
	private String details;
	private List<Image> pictures;
	
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
	public List<Image> getPictures() {
		return pictures;
	}
	public void setPictures(List<Image> pictures) {
		this.pictures = pictures;
	}
	
}