package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventFactory;
import modsim.simulator.utils.MathsUtils;

public class Normal implements TimeFunction {

	@Override
	public int getTime() {
		return MathsUtils.normal(EventFactory.ef1, EventFactory.ef2);
	}

}
