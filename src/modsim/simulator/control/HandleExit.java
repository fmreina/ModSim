package modsim.simulator.control;

import sun.misc.Queue;
import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.model.Event;

public class HandleExit implements Handler {

	@Override
	public Event handleEvent(Event event, int time) throws InterruptedException {
		Entity entidade = event.getEntidade();
		Server server = Simulator.getServers().get(entidade.getType());
		server.releaseServer(time);
		Queue<Entity> fila = server.getFila();
		if (!fila.isEmpty()) {
			Entity first = fila.dequeue();
			server.ocuppyServer(first, time); // tomada do servidor
			return EventControl.newExit(first, time, server.getServiceFunc(),
					first.getId()); // gera saida
		}
		return null;
	}

}
