package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventControl;
import modsim.simulator.utils.MathsUtils;

public class Normal implements TimeFunction {

	@Override
	public int getTime() {
		return MathsUtils.normal(EventControl.ef1, EventControl.ef2);
	}

}
