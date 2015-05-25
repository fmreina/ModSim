package modsim.simulator.model;

import modsim.simulator.entities.Entity;

public class EventExit extends Event{

	private int id;
	
	public EventExit(int tempoSaida, Entity entidade, int id) {
		super(tempoSaida, entidade);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String toString() {
		return "Sa�da <ID: "+id+"; Tempo de sa�da: "+super.tempoEvento+" >";
	}
}
