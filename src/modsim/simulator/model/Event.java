package modsim.simulator.model;


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
