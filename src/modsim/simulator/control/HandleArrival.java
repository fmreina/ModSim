package modsim.simulator.control;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;
import modsim.simulator.model.EntityEvent;
import modsim.simulator.model.Event;

public class HandleArrival implements HandlerEntity {

	@Override
	public Event handleEvent(EntityEvent event, int time) {
		Simulator.newEvent();
		if(event.getEntidade().getType() == TipoServidor.TIPO_1){
			Simulator.getStats().incrNbEntities1Completed();
		}
		if(event.getEntidade().getType() == TipoServidor.TIPO_2){
			Simulator.getStats().incrNbEntities2Completed();
		}
		return handleEvent(event, time, false);
	}

	public static Event handleEvent(EntityEvent event, int timeNow,
			boolean firstIsBroken) {
		Entity entidade = event.getEntidade();
		Server server = Simulator.getServers().get(entidade.getType());
		if (server.isFree()) {
			if (!server.getFila().isEmpty()) {
				server.getFila().enqueue(entidade);
				return null;
			} else {
				server.ocuppyServer(entidade, timeNow); // tomada do servidor
				return EventControl.newExit(entidade, timeNow,
						server.getServiceFunc()); // gera
																	// saida
			}
		}
		if (server.isBroken() && !firstIsBroken) {
			entidade.setType(TipoServidor.getAnotherType(entidade.getType()));
			handleEvent(event, timeNow, true);
		} else {
			server.getFila().enqueue(entidade);
		}
		return null;
	}
}
