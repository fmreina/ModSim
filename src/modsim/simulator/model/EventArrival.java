package modsim.simulator.model;

import modsim.simulator.control.HandleArrival;
import modsim.simulator.control.Handler;
import modsim.simulator.entities.Entity;

public class EventArrival extends Event{
	
	public EventArrival(int tempoChegada, Entity entidade){
		super(tempoChegada, entidade);
	}

	public String toString() {
		return "Chegada <ID: "+entidade.getId()+"; Tempo de chegada: "+super.tempoEvento+" >";
	}

	public int compareTo(Event o) {
		return tempoEvento;
	}

	@Override
	public Handler getHandler() {
		return new HandleArrival();
	}
}
