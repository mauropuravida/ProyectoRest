package load;

import java.util.Date;
import com.sun.istack.NotNull;

public class CowBcsLoad {

	@NotNull
	private long cowId;
	
	@NotNull
	private Date fecha;
	
	@NotNull
	private float cc;
	
	public long getCowId() {
		return cowId;
	}
	
	public void setCowId(long id) {
		this.cowId = id;
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
