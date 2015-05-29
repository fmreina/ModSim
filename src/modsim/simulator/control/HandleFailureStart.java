package modsim.simulator.control;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.model.Event;

public class HandleFailureStart implements Handler<Server> {

	@Override
	public Event<Server> handleEvent(Event<Server> event, int time)
			throws InterruptedException {		
		Server server = event.getItem();
		if(!server.isFree()){
			Entity ocupante = server.getOcupante();
			server.getFila().enqueue(ocupante);
			server.releaseServer(time);
		}
		server.setInicioFalha(time);
		server.setBroken(true);
		return EventFactory.newFailureEnd(server, time);
	}

}
