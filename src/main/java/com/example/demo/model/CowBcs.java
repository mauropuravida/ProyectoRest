package com.example.demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CowBcs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "cow_id", nullable = false)
	private long cowId;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@Column(name = "cc", nullable = false)
	private float cc;

 	public long getId() {
 		return id;
 	}
	
	public long getCowId() {
		return cowId;
	}
	
	public void setCowId(long value) {
		this.cowId = value;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date value) {
		this.fecha = value;
	}
	
	public float getCc() {
		return cc;
	}
	
	public void setCc(float value) {
		this.cc = value;
	}
}
