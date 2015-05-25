package modsim.simulator.model;

import modsim.simulator.entities.Entity;

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

	@Override
	public void func() {
		//do event
	}
}
