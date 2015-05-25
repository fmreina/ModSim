package modsim.simulator.model;

import modsim.simulator.entities.Entity;

public class EventArrival extends Event{
	
	private int id;
	
	public EventArrival(int tempoChegada, Entity entidade){
		super(tempoChegada, entidade);
		this.id = super.id ++;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Chegada <ID: "+id+"; Tempo de chegada: "+super.tempoEvento+" >";
	}

}
