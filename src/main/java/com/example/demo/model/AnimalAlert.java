package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class AnimalAlert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "cow_Id", referencedColumnName = "id" )
	private Cow cow;	
	
	@Column(name = "bcs_threshold_max", nullable = false)
	private float bcsThresholdMax;
	
	@Column(name = "bcs_threshold_min", nullable = false)
	private float bcsThresholdMin;
	
	public long getId() {
		return id;
	}

	public long getAnimalId() {
		return cow.getId();
	}

	public void setAnimal(Cow c) {
	this.cow = c;
	}

	public float getBcsThresholdMax() {
		return bcsThresholdMax;
	}

	public void setBcsThresholdMax(float value) {
		this.bcsThresholdMax = value;
	}

	public float getBcsThresholdMin() {
		return bcsThresholdMin;
	}

	public void setBcsThresholdMin(float value) {
		this.bcsThresholdMin = value;
	}
}
