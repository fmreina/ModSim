package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventFactory;
import modsim.simulator.utils.MathsUtils;

public class Triangular implements TimeFunction {

	@Override
	public int getTime() {
		return MathsUtils.triangular(EventFactory.ef1, EventFactory.ef2, EventFactory.ef3);
	}

}
