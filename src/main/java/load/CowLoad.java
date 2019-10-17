package load;

import java.util.Date;
import com.sun.istack.NotNull;

public class CowLoad {

	@NotNull
	private long electronicId;

	@NotNull
	private Date fechaNacimiento;
	
	@NotNull
	private Date ultimaFechaParto;
	
	@NotNull
	private long cantidadPartos;
	
	@NotNull
	private float peso;
	
	@NotNull
	private long herdId;	

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
     return herdId;
 }

	public void setHerd(long id) {
		this.herdId = id;
	}
}
