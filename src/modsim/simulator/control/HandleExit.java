package modsim.simulator.control;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.model.Event;

public class HandleExit implements Handler {

	@Override
	public Event handleEvent(Event event, int time) {
		Entity entidade = event.getEntidade();
		Server server = Simulator.getServers().get(entidade.getType());
		server.releaseServer(time);
		if (!server.getFila().isEmpty()) {
			server.ocuppyServer(entidade, time); // tomada do servidor
			return EventControl.newExit(entidade, time, server.getServiceFunc(),
					entidade.getId()); // gera saida
		}
		return null;
	}

}
