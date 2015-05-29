package modsim.simulator.model;

import com.sun.beans.TypeResolver;

import modsim.simulator.control.Handler;

public abstract class Event<ITEM> implements IEvent, Comparable<Event<ITEM>>{
	
	protected ITEM item;
	protected int tempoEvento;
		
	public Event(int tempoEvento){
		this.tempoEvento = tempoEvento;
	}

	public int getTempoExecucao() {
		return tempoEvento;
	}
	
	public abstract ITEM getItem();

}
