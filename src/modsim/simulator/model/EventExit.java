package modsim.simulator.model;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;

public class EventExit extends Event{

	private int id;
	
	public EventExit(int tempoSaida, Entity entidade, int id) {
		super(tempoSaida, entidade);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String toString() {
		return "Saída <ID: "+id+"; Tempo de saída: "+super.tempoEvento+" >";
	}
	
	public int compareTo(Event o) {
		return 0;
	}

	@Override
	public void func(Server server) {
		server.releaseServer();
	}
}
