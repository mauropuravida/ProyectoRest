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

import org.hibernate.annotations.Check;

@Entity
@Table
//@Check(constraints = " fecha > SELECT IFNULL( (SELECT * FROM COW_BCS  WHERE cow_id = 1 order by fecha asc limit 1 ) ,NULL);")
public class CowBcs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "cow_id", referencedColumnName = "id" )
	private Cow cowId;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@Min(value=1)
	@Max(value=9)
	@Digits(integer=1,fraction=2)
	@Column(name = "cc", nullable = false)
	private float cc;

 	public long getId() {
 		return id;
 	}
	
	public long getCowId() {
		return cowId.getId();
	}
	
	public void setCowId(Cow c) {
		this.cowId = c;
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
