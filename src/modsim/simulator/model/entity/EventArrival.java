package modsim.simulator.model.entity;

import modsim.simulator.control.HandleArrival;
import modsim.simulator.control.Handler;
import modsim.simulator.model.Event;
import modsim.simulator.model.server.TipoServidor;

public class EventArrival extends Event<Entity> {

	public EventArrival(int tempoSaida, Entity entidade) {
		super(tempoSaida);
		this.item = entidade;
	}

	public String toString() {
		return "Entrada <ID: " + item.getId() + "; Tipo: "
				+ item.getType().toString() + "; Tempo de entrada: "
				+ super.tempoEvento + " >";
	}

	public int compareTo(Event<Entity> o) {
		return this.tempoEvento;
	}

	@Override
	public Handler<Entity> getHandler() {
		return new HandleArrival();
	}

	public Entity getItem() {
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
