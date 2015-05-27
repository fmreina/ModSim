package modsim.simulator.entities;

import modsim.simulator.control.Handler;

public interface IEvent {

	public int getTempoExecucao();

	public Handler getHandler();

}
