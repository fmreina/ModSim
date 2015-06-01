package modsim.simulator.model;

import java.io.Serializable;
import java.util.ArrayList;

import modsim.simulator.utils.Statistics;

public class Simulation implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private ArrayList<String> log;
	private Statistics stats;
	
	public Simulation(String name, int id, int tempoSim){
		this.id = id;
		this.name = name;
		this.log = new ArrayList<String>();
		this.stats = new Statistics(tempoSim);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getLog() {
		return log;
	}

	public void setLog(ArrayList<String> log) {
		this.log = log;
	}
	
	public Statistics getStats() {
		return stats;
	}

	public void setStats(Statistics stats) {
		this.stats = stats;
	}

	@Override
	public String toString() {
		return "Simulação: "+name+"; ID: "+this.id+";";
	}
}
