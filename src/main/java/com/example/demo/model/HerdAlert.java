package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Check;

@Entity
@Table
@Check(constraints = "bcs_threshold_min < bcs_threshold_max")
public class HerdAlert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
	@ManyToOne
	@JoinColumn(name = "herd_Id", referencedColumnName = "id" )
	private Herd herd;

	@Min(value=1)
	@Max(value=9)
	@Digits(integer=1,fraction=2)
	@Column(name = "bcs_threshold_max", nullable = false)
	private float bcsThresholdMax;
	
	@Min(value=1)
	@Max(value=9)
	@Digits(integer=1,fraction=2)
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
