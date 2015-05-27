package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventControl;
import modsim.simulator.utils.MathsUtils;

public class Triangular implements TimeFunction {

	@Override
	public int getTime() {
		return MathsUtils.triangular(EventControl.ef1, EventControl.ef2, EventControl.ef3);
	}

}
