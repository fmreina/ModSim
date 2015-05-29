package modsim.simulator.model;

import modsim.simulator.control.HandleExit;
import modsim.simulator.control.HandlerEntity;
import modsim.simulator.entities.Entity;

public class EntityEvent extends Event{
	
	private Entity entidade;

	public EntityEvent(int tempoSaida, Entity entidade) {
		super(tempoSaida);
		this.entidade = entidade;
	}

	public String toString() {
		return "Saída <ID: "+entidade.getId()+"; Tipo: "+entidade.getType().toString()+"; Tempo de saída: "+super.tempoEvento+" >";
	}
	
	public int compareTo(Event o) {
		return this.tempoEvento;
	}

	@Override
	public HandlerEntity getHandler() {
		return new HandleExit();
	}
	
	public Entity getEntidade(){
		return entidade;
	}
}
