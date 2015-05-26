package modsim.simulator.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Simulation implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private ArrayList<String> log;
	
	public Simulation(String name, int id){
		this.id = id;
		this.name = name;
		this.log = new ArrayList<String>();
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
	
	@Override
	public String toString() {
		return "Simula��o: "+name+"; ID: "+this.id+";";
	}
}