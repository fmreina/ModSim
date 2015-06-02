package modsim.simulator.control;

import java.util.Queue;

import modsim.simulator.model.Event;
import modsim.simulator.model.entity.Entity;
import modsim.simulator.model.server.Server;
import modsim.simulator.model.server.TipoServidor;

public class HandleExit implements Handler<Entity> {

	@Override
	public Event<Entity> handleEvent(Event<Entity> event, int time)
			throws InterruptedException {
		Entity entidade = event.getItem();
		Server server = Simulator.getServers().get(entidade.getType());
		if (!server.isBroken()) {
			server.releaseServer(time);
			Simulator.getStats().getListTimeOnSystem().add(entidade.getTempoNoSistema());
			Queue<Entity> fila = server.getFila();
			if (!fila.isEmpty()) { // se a fila n�o estiver vazia, 2� retirado o primeiro da fila para ocupar o server e gerado um novo evento de saida
				Simulator.print("Entidade ID " + entidade.getId() + " entrou na fila.");
				if (event.getItem().getType() == TipoServidor.TIPO_1) {
					Simulator.getStats().updateAvgNbEntities1InLine(server.getFila().size(), time);
				}
				if (event.getItem().getType() == TipoServidor.TIPO_2) {
					Simulator.getStats().updateAvgNbEntities2InLine(server.getFila().size(), time);
				}
				Entity first = fila.remove();
				Simulator.print("Entidade ID " + entidade.getId() + " saiu da fila e ocupou o servidor.");
				server.ocuppyServer(first, time); // tomada do servidor
				first.updateTempoEmFila();
				
				if (event.getItem().getType() == TipoServidor.TIPO_1) {
					Simulator.getStats().incrNbEntities1Completed();
					Simulator.getStats().getListTimeInLine1().add(first.getTempoFila());
					Simulator.getStats().addListServer1Ocupation(event.getItem().getTempoSaida() -  event.getItem().getTempoInicioAtendimento());
				}
				if (event.getItem().getType() == TipoServidor.TIPO_2) {
					Simulator.getStats().incrNbEntities2Completed();
					Simulator.getStats().getListTimeInLine2().add(first.getTempoFila());
					Simulator.getStats().addListServer2Ocupation(event.getItem().getTempoSaida() -  event.getItem().getTempoInicioAtendimento());
				}
				return EventFactory.newExit(first, time,
						server.getServiceFunc()); // gera saida
			}
		}
		return null;
	}

}
