package modsim.simulator.model;

import modsim.simulator.entities.IEvent;

public abstract class Event implements IEvent, Comparable<Event>{
	
	protected int tempoEvento;
		
	public Event(int tempoEvento){
		this.tempoEvento = tempoEvento;
	}

	public int getTempoExecucao() {
		return tempoEvento;
	}
}
