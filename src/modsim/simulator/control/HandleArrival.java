package modsim.simulator.control;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;
import modsim.simulator.model.Event;

public class HandleArrival implements Handler {

	@Override
	public Event handleEvent(Event event, int time) {
		Simulator.newEvent();
		return handleEvent(event, time, false);
	}

	public static Event handleEvent(Event event, int timeNow,
			boolean firstIsBroken) {
		Entity entidade = event.getEntidade();
		Server server = Simulator.getServers().get(entidade.getType());
		if (server.isFree()) {
			if (!server.getFila().isEmpty()) {
				server.getFila().add(entidade);
				entidade = server.getFila().remove(0);
			}
			server.ocuppyServer(entidade, timeNow); // tomada do servidor
			return EventControl.newExit(entidade, timeNow, server.getServiceFunc(),
					entidade.getId()); // gera saida
		}
		if (server.isBroken() && !firstIsBroken) {
			entidade.setType(TipoServidor.getAnotherType(entidade.getType()));
			handleEvent(event, timeNow, true);
		} else {
			server.getFila().add(entidade);
		}
		return null;
	}
}
