package modsim.simulator.model.entity;

import modsim.simulator.control.HandleExit;
import modsim.simulator.control.Handler;
import modsim.simulator.model.Event;
import modsim.simulator.model.server.TipoServidor;

public class EventExit extends Event<Entity>{
	
	public EventExit(int tempoSaida, Entity entidade) {
		super(tempoSaida);
		this.item = entidade;
	}

	public String toString() {
		return "Saída <ID: "+item.getId()+"; Tipo: "+item.getType().toString()+"; Tempo de saída: "+super.tempoEvento+" >";
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
