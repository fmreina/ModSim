package modsim.simulator.model;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;

public class EventChange extends Event{

	private int id;
	
	public EventChange(int tempoTroca, Entity entidade, int id) {
		super(tempoTroca, entidade);
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String toString() {
		return "Troca de Fila <ID: "+id+"; Tempo da troca: "+super.tempoEvento+" >";
	}

	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void func(Server server) {
	}
}
