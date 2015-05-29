package modsim.simulator.control;

import modsim.simulator.model.EntityEvent;
import modsim.simulator.model.Event;

public interface HandlerEntity {
	
	public Event handleEvent(EntityEvent event, int time) throws InterruptedException;

}
