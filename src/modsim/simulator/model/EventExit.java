package modsim.simulator.model;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;

public class EventExit extends Event{

	public EventExit(int tempoSaida, Entity entidade, int id) {
		super(tempoSaida, entidade);
	}

	public String toString() {
		return "Saída <ID: "+entidade.getId()+"; Tipo: "+entidade.getType().toString()+"; Tempo de saída: "+super.tempoEvento+" >";
	}
	
	public int compareTo(Event o) {
		return 0;
	}
}
