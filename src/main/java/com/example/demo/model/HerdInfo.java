package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class HerdInfo {
	
	private long id;

	private String location;
	
	private float bcsPromedio;
	
	private List<AnimalInfo> animalsInfo = new ArrayList<AnimalInfo>();

 public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

public String getLocation() {
     return location;
 }

 public void setLocation(String value) {
     this.location = value;
 }

 public float getBcsPromedio() {
     return bcsPromedio;
 }

 public void setBcsPromedio(float value) {
     this.bcsPromedio = value;
 }

 	public List<AnimalInfo> getCows() {
 		return this.animalsInfo;
 	}
 
	public void addAnimalInfo(AnimalInfo c) {
		animalsInfo.add(c);
	}
	
	public void addAnimalsInfo(List<AnimalInfo> lc) {
		animalsInfo.addAll(lc);
	}
}