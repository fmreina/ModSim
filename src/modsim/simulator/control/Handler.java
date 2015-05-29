package modsim.simulator.control;

import modsim.simulator.model.Event;

public interface Handler<ITEM> {
	
	public Event<ITEM> handleEvent(Event<ITEM> event, int time) throws InterruptedException;

}
