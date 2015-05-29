package modsim.simulator.entities;

import modsim.simulator.control.HandlerEntity;

public interface IEvent {

	public int getTempoExecucao();

	public HandlerEntity getHandler();

}
