package modsim.simulator.model;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.IEvent;
import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;

public abstract class Event implements IEvent, Comparable<Event>{
	
	protected static int id = 0;
	protected int tempoEvento;
	protected Entity entidade;
	protected TipoServidor tipoServidor;
	
	public Event(int tempoChegada, Entity entidade){
		this.entidade = entidade;
		this.tempoEvento = tempoChegada;
	}

	public int getTempoExecucao() {
		return tempoEvento;
	}

	public void setTempoExecucao(int tempoChegada) {
		this.tempoEvento = tempoChegada;
	}

	public Entity getEntidade() {
		return entidade;
	}

	public void setEntidade(Entity entidade) {
		this.entidade = entidade;
	}

	public TipoServidor getServerType() {
		return this.tipoServidor;
	}

	public void func(Server server) {
		// TODO Auto-generated method stub
		
	}
}
