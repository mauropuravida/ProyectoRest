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
public class AnimalAlertFired {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "cow_Id", referencedColumnName = "id")
	private Cow cow;

	@Min(value = 1)
	@Max(value = 9)
	@Digits(integer=1, fraction = 0)
	private int bcs_fired;
	
	@Column(nullable = false)
	private Date fecha;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cow getCow() {
		return cow;
	}

	public void setCow(Cow cow) {
		this.cow = cow;
	}

	public int getBcs_fired() {
		return bcs_fired;
	}

	public void setBcs_fired(int bcs_fired) {
		this.bcs_fired = bcs_fired;
	}

	public long getId() {
		return id;
	}

	
}
