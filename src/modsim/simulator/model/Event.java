package modsim.simulator.model;

import modsim.simulator.entities.Entity;

public abstract class Event {
	
	protected static int id = 0;
	protected int tempoEvento;
	private Entity entidade;
	
	public Event(int tempoChegada, Entity entidade){
		this.entidade = entidade;
		this.tempoEvento = tempoChegada;
	}

	public int getTempoChegada() {
		return tempoEvento;
	}

	public void setTempoChegada(int tempoChegada) {
		this.tempoEvento = tempoChegada;
	}

	public Entity getEntidade() {
		return entidade;
	}

	public void setEntidade(Entity entidade) {
		this.entidade = entidade;
	}
}
