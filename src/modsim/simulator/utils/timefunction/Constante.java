package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventControl;

public class Constante implements TimeFunction {

	@Override
	public int getTime() {
		return EventControl.ef1;
	}

}
