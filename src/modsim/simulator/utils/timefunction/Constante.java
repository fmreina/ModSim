package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventFactory;

public class Constante implements TimeFunction {

	@Override
	public int getTime() {
		return EventFactory.ef1;
	}

}
