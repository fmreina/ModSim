package modsim.simulator.model;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;

public class EventArrival extends Event{
	
	public EventArrival(int tempoChegada, Entity entidade){
		super(tempoChegada, entidade);
	}

	public String toString() {
		return "Chegada <ID: "+entidade.getId()+"; Tempo de chegada: "+super.tempoEvento+" >";
	}

	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
