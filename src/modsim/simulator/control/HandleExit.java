package modsim.simulator.control;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.model.Event;
import sun.misc.Queue;

public class HandleExit implements Handler<Entity> {

	@Override
	public Event<Entity> handleEvent(Event<Entity> event, int time)
			throws InterruptedException {
		Entity entidade = event.getItem();
		Server server = Simulator.getServers().get(entidade.getType());
		if (!server.isBroken()) {
			server.releaseServer(time);
			Queue<Entity> fila = server.getFila();
			if (!fila.isEmpty()) { // se a fila não estiver vazia, 2é retirado o
									// primeiro da fila para ocupar o server e
									// gerado um novo evento de saida
				Entity first = fila.dequeue();
				server.ocuppyServer(first, time); // tomada do servidor
				return EventFactory.newExit(first, time,
						server.getServiceFunc()); // gera saida
			}
		}
		return null;
	}

}
