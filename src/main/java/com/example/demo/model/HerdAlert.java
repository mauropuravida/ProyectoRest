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
public class HerdAlert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
	@ManyToOne
	@JoinColumn(name = "herd_Id", referencedColumnName = "id" )
	private Herd herd;
    
	@Column(name = "bcs_threshold_max", nullable = false)
	private float bcsThresholdMax;
	
	@Column(name = "bcs_threshold_min", nullable = false)
	private float bcsThresholdMin;
	
	public long getId() {
		return id;
	}

    public long getHerdId() {
        return herd.getId();
    }

    public void setHerd(Herd herd) {
        this.herd = herd;
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
