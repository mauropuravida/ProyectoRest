package com.example.demo.model;

import java.util.Date;

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

@Entity
@Table
public class HerdAlertFired {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "herd_Id", referencedColumnName = "id" )
	private Herd herd;

	@Min(value = 1)
	@Max(value = 9)
	@Digits(integer=1, fraction = 2)
	private float bcs_fired;

	@Column(nullable = false)
	private Date fecha;
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Herd getHerd() {
		return herd;
	}

	public void setHerd(Herd herd) {
		this.herd = herd;
	}

	public float getBcs_fired() {
		return bcs_fired;
	}

	public void setBcs_fired(float bcs_fired) {
		this.bcs_fired = bcs_fired;
	}

	public long getId() {
		return id;
	}

	
}
