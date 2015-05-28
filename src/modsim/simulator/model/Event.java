package modsim.simulator.model;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.IEvent;
import modsim.simulator.entities.Server;

public abstract class Event implements IEvent, Comparable<Event>{
	
	protected int tempoEvento;
	protected Entity entidade;
	
	public Event(int tempoEvento, Entity entidade){
		this.entidade = entidade;
		this.tempoEvento = tempoEvento;
	}

	public int getTempoExecucao() {
		return tempoEvento;
	}

	public Entity getEntidade() {
		return entidade;
	}

}
