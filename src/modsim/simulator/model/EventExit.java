package modsim.simulator.model;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;

public class EventExit extends Event{

	private int id;
	
	public EventExit(int tempoSaida, Entity entidade) {
		super(tempoSaida, entidade);
		this.id = super.id ++;
	}
	
	public int getId() {
		return id;
	}

	public String toString() {
		return "Sa�da <ID: "+id+"; Tempo de sa�da: "+super.tempoEvento+" >";
	}
	
	public int compareTo(Event o) {
		return 0;
	}

	@Override
	public void func(Server server) {
		server.releaseServer();
	}
}
