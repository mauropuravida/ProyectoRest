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

@Entity
@Table
public class Cow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "electronic_id", nullable = false)
	private long electronicId;

	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fechaNacimiento;
	
	@Column(name = "ultima_fecha_parto", nullable = true)
	private Date ultimaFechaParto;
	
	@Column(name = "cantidad_partos", nullable = false)
	private long cantidadPartos;
	
	@Column(name = "peso", nullable = false)
	private float peso;
	
	@ManyToOne
	@JoinColumn(name = "herd_Id", referencedColumnName = "id" )
	private Herd herd;	

 public long getId() {
     return id;
 }

 public long getElectronicId() {
     return electronicId;
 }

 public void setElectronicId(long value) {
     this.electronicId = value;
 }

 public Date getFechaNacimiento() {
     return fechaNacimiento;
 }

 public void setFechaNacimiento(Date value) {
     fechaNacimiento = value;
 }

 public Date getUltimaFechaParto() {
     return ultimaFechaParto;
 }

 public void setUltimaFechaParto(Date value) {
     this.ultimaFechaParto = value;
 }

 public long getCantidadPartos() {
     return cantidadPartos;
 }

 public void setCantidadPartos(long value) {
     this.cantidadPartos = value;
 }

 public float getPeso() {
     return peso;
 }

 public void setPeso(float value) {
     this.peso = value;
 }

 public long getHerdId() {
     return herd.getId();
 }

	public void setHerd(Herd herd) {
		this.herd = herd;
	}
}