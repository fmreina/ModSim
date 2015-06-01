package modsim.simulator.control;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;
import modsim.simulator.model.Event;

public class HandleArrival implements Handler<Entity> {

	@Override
	public Event<Entity> handleEvent(Event<Entity> event, int time) {
		Simulator.createEventArrival();
		if (event.getItem().getType() == TipoServidor.TIPO_1) {
			Simulator.getStats().incrNbEntities1();
		}
		if (event.getItem().getType() == TipoServidor.TIPO_2) {
			Simulator.getStats().incrNbEntities2();
		}
		return handleEvent(event, time, false);
	}

	public static Event<Entity> handleEvent(Event<Entity> event, int timeNow,
			boolean firstIsBroken) {
		Entity entidade = event.getItem();
		Server server = Simulator.getServers().get(entidade.getType());
		entidade.setTempoChegada(timeNow);
		if (server.isBroken() && !firstIsBroken) {
			entidade.setType(TipoServidor.getAnotherType(entidade.getType()));
			if (event.getItem().getType() == TipoServidor.TIPO_1) {
				Simulator.getStats().incrNbOfChanges1();
			}
			if (event.getItem().getType() == TipoServidor.TIPO_2) {
				Simulator.getStats().incrNbOfChanges2();
			}
			return handleEvent(event, timeNow, true);
		}
		if (server.isFree()) {
			if (!server.getFila().isEmpty()) {
				server.getFila().add(entidade);
				return null;
			} else {
				Simulator.print("Entidade ID " + entidade.getId() + " ocupou o servidor.");
				server.ocuppyServer(entidade, timeNow); // tomada do servidor
				return EventFactory.newExit(entidade, timeNow,
						server.getServiceFunc()); // gera saida
			}
		}
		Simulator.print("Entidade ID " + entidade.getId() + " entrou na fila.");
		server.getFila().add(entidade);
		return null;
	}
}
