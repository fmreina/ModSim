package modsim.simulator.model;

import modsim.simulator.control.HandleFailure;
import modsim.simulator.control.HandlerEntity;
import modsim.simulator.entities.IEvent;
import modsim.simulator.entities.Server;

public class ServerEvent extends Event implements IEvent {

	private Server server;
	
	public ServerEvent(int tempoEvento, Server server) {
		super(tempoEvento);
		this.server = server;
	}

	@Override
	public int getTempoExecucao() {
		return tempoEvento;
	}

	@Override
	public HandlerEntity getHandler() {
		return new HandleFailure();
	}

	@Override
	public int compareTo(Event arg0) {
		return tempoEvento;
	}
	
	public Server getServer(){
		return server;
	}

}
