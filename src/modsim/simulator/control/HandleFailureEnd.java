package modsim.simulator.control;

import java.util.Queue;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;
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
			Entity first = fila.remove();
			server.ocuppyServer(first, time);
		}
		
//		if (server.getType() == TipoServidor.TIPO_1) {
//			Simulator.getStats().setTimeOnFailureSvr1(server.getTempoFalha());
//		}
//		if (server.getType() == TipoServidor.TIPO_2) {
//			Simulator.getStats().setTimeOnFailureSvr2(server.getTempoFalha());
//		}
		
		return EventFactory.newFailureStart(server, time);
	}

}
