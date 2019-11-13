package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

//Formato para tabla en la base de datos

@Entity
@Table
public class Herd {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "location", nullable = false)
	private String location;
	
	public long getId() {
		return id;
	}

	public String getLocation() {
		 return location;
	}

	public void setLocation(String value) {
		this.location = value;
	}

}