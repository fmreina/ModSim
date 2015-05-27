package modsim.simulator.model;

import modsim.simulator.control.HandleExit;
import modsim.simulator.control.Handler;
import modsim.simulator.entities.Entity;

public class EventExit extends Event{

	public EventExit(int tempoSaida, Entity entidade, int id) {
		super(tempoSaida, entidade);
	}

	public String toString() {
		return "Sa�da <ID: "+entidade.getId()+"; Tempo de sa�da: "+super.tempoEvento+" >";
	}
	
	public int compareTo(Event o) {
		return this.tempoEvento;
	}

	@Override
	public Handler getHandler() {
		return new HandleExit();
	}
}
