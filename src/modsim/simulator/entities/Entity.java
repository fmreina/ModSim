package modsim.simulator.entities;

public class Entity {

	private int type;
	private long tec;
	private long tsd;
	
	public Entity(){
		
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getTec() {
		return tec;
	}

	public void setTec(long tec) {
		this.tec = tec;
	}

	public long getTsd() {
		return tsd;
	}

	public void setTsd(long tsd) {
		this.tsd = tsd;
	}

}
