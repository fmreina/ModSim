package modsim.simulator.model;

import modsim.simulator.control.HandleFailureStart;
import modsim.simulator.control.Handler;
import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;

public class EventFailureStart extends Event<Server> implements IEvent {

	public EventFailureStart(int tempoEvento, Server server) {
		super(tempoEvento);
		this.item = server;
	}

	@Override
	public int getTempoExecucao() {
		return tempoEvento;
	}

	@Override
	public Handler<Server> getHandler() {
		return new HandleFailureStart();
	}

	@Override
	public int compareTo(Event<Server> arg0) {
		return tempoEvento;
	}
	
	public Server getItem(){
		return item;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public TipoServidor getType() {
		return item.getType();
	}

	@Override
	public String toString() {
		return "Servidor "+item.getType().getNome()+" falhou no tempo "+super.tempoEvento+" segundos";
	}
}
