package modsim.simulator.control;

import sun.misc.Queue;
import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.model.EntityEvent;
import modsim.simulator.model.Event;

public class HandleExit implements HandlerEntity {

	@Override
	public Event handleEvent(EntityEvent event, int time) throws InterruptedException {
		Entity entidade = event.getEntidade();
		Server server = Simulator.getServers().get(entidade.getType());
		server.releaseServer(time);
		Queue<Entity> fila = server.getFila();
		if (!fila.isEmpty()) { // se a fila não estiver vazia, é retirado o
								// primeiro da fila para ocupar o server e gerado um novo evento de saida
			Entity first = fila.dequeue();
			server.ocuppyServer(first, time); // tomada do servidor
			return EventControl.newExit(first, time, server.getServiceFunc()); // gera saida
		}
		return null;
	}

}
