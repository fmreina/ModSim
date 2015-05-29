package modsim.simulator.control;

import sun.misc.Queue;
import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.model.Event;

public class HandleFailureEnd implements Handler<Server> {

	@Override
	public Event<Server> handleEvent(Event<Server> event, int time)
			throws InterruptedException {
		Server server = event.getItem();
		server.setBroken(false);
		server.updateTempoFalha(time);
		Queue<Entity> fila = server.getFila();
		if (!fila.isEmpty()) {
			Entity first = fila.dequeue();
			server.ocuppyServer(first, time);
		}
		return EventFactory.newFailureStart(server, time);
	}

}
