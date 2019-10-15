package com.example.demo.model;

import java.util.Date;

public class AnimalInfo {

	private long id;

	private long electronicId;

	private Date fechaNacimiento;

	private Date ultimaFechaParto = null;

	private long cantidadPartos;

	private Float peso;

	private long herdId;	

	private long cowBcsId;

	private Date fechaBcs = null;

	private float cc;

 public long getId() {
     return id;
 }

 public void setId(long value) {
     this.id = value;
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
     this.fechaNacimiento = value;
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
     return herdId;
 }

 public void setHerdId(long value) {
     this.herdId = value;
 }

 public long getCowBcsId() {
     return cowBcsId;
 }

 public void setCowBcsId(long value) {
     this.cowBcsId = value;
 }

 public Date getFechaBcs() {
     return fechaBcs;
 }

 public void setFechaBcs(Date value) {
     this.fechaBcs = value;
 }

 public float getCc() {
     return cc;
 }

 public void setCc(float value) {
     this.cc = value;
 }
}
