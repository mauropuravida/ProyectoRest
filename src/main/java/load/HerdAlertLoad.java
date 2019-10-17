package load;

import com.sun.istack.NotNull;

public class HerdAlertLoad {
    
	@NotNull
	private long herdId;
    
	@NotNull
	private float bcsThresholdMax;
	
	@NotNull
	private float bcsThresholdMin;
	

    public long getHerdId() {
        return herdId;
    }

    public void setHerdId(long id) {
        this.herdId = id;
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
