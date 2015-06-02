package modsim.simulator.control;

import modsim.simulator.model.Event;
import modsim.simulator.model.entity.Entity;
import modsim.simulator.model.server.Server;
import modsim.simulator.model.server.TipoServidor;
import modsim.simulator.utils.Statistics;

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
		if (server.isBroken() && !firstIsBroken) { // se o servidor está quebrado, e não é a primeira vez 
													// que a entidade encontra um servidor quebrado
			entidade.setType(TipoServidor.getAnotherType(entidade.getType())); //troca o tipo da entidade para outro tipo
			if (event.getItem().getType() == TipoServidor.TIPO_1) {
				Simulator.getStats().incrNbOfChanges1();
			}
			if (event.getItem().getType() == TipoServidor.TIPO_2) {
				Simulator.getStats().incrNbOfChanges2();
			}
			return handleEvent(event, timeNow, true); //gera um novo evento de entrada
		}
		if (server.isFree()) {
			if (server.getFila().isEmpty()) { //se o servidor está livre, a entida ocupa-o e gera nova saída
				Simulator.print("Entidade ID " + entidade.getId()	+ " ocupou o servidor.");
				server.ocuppyServer(entidade, timeNow); // tomada do servidor
				return EventFactory.newExit(entidade, timeNow,
						server.getServiceFunc()); // gera saida
			}
		}//senão adiciona a entidade a fila
		Simulator.print("Entidade ID " + entidade.getId() + " entrou na fila.");
		if (event.getItem().getType() == TipoServidor.TIPO_1) {
			Simulator.getStats().updateAvgNbEntities1InLine(server.getFila().size(), timeNow);
		}
		if (event.getItem().getType() == TipoServidor.TIPO_2) {
			Simulator.getStats().updateAvgNbEntities2InLine(server.getFila().size(), timeNow);
		}
		server.getFila().add(entidade);
		return null;
	}
}
