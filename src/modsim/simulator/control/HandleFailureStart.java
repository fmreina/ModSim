package modsim.simulator.control;

import modsim.simulator.model.Event;
import modsim.simulator.model.entity.Entity;
import modsim.simulator.model.server.Server;
import modsim.simulator.model.server.TipoServidor;

public class HandleFailureStart implements Handler<Server> {

	@Override
	public Event<Server> handleEvent(Event<Server> event, int time)
			throws InterruptedException {		
		Server server = event.getItem();
		if(!server.isFree()){ //se o servidor não estiver livre, retira a entidade e a coloca na fila
			Entity ocupante = server.getOcupante();
			server.getFila().add(ocupante);
			server.releaseServer(time);
		}
		server.setInicioFalha(time);
		server.setBroken(true);		
		if (server.getType() == TipoServidor.TIPO_1) {
			Simulator.getStats().incrNbOfFailuresSvr1();
		}
		if (server.getType() == TipoServidor.TIPO_2) {
			Simulator.getStats().incrNbOfFailuresSvr2();
		}
		return EventFactory.newFailureEnd(server, time); //gera novo evento de término da falha
	}

}
