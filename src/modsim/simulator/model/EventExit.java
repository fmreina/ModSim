package modsim.simulator.model;

import modsim.simulator.control.HandleExit;
import modsim.simulator.control.Handler;
import modsim.simulator.entities.Entity;
import modsim.simulator.entities.TipoServidor;

public class EventExit extends Event<Entity>{
	
	public EventExit(int tempoSaida, Entity entidade) {
		super(tempoSaida);
		this.item = entidade;
	}

	public String toString() {
		return "Sa�da <ID: "+item.getId()+"; Tipo: "+item.getType().toString()+"; Tempo de sa�da: "+super.tempoEvento+" >";
	}
	
	public int compareTo(Event<Entity> o) {
		return this.tempoEvento;
	}

	@Override
	public Handler<Entity> getHandler() {
		return new HandleExit();
	}
	
	public Entity getItem(){
		return item;
	}

	@Override
	public int getId() {
		return item.getId();
	}

	@Override
	public TipoServidor getType() {
		return item.getType();
	}
}
