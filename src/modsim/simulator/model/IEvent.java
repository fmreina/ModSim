package modsim.simulator.model;

import modsim.simulator.control.Handler;
import modsim.simulator.entities.TipoServidor;

public interface IEvent {

	public int getTempoExecucao();

	public Handler getHandler();
	
	public int getId();
	
	public TipoServidor getType();

}
