package modsim.simulator.control;

import modsim.simulator.model.Event;

public interface Handler {
	
	public Event handleEvent(Event event, int time) throws InterruptedException;

}
