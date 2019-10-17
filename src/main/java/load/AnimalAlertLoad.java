package load;

import com.sun.istack.NotNull;

public class AnimalAlertLoad {

	@NotNull
	private long cowId;	
	
	@NotNull
	private float bcsThresholdMax;
	
	@NotNull
	private float bcsThresholdMin;

	public long getCowId() {
		return cowId;
	}

	public void setCowId(long id) {
	this.cowId = id;
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
