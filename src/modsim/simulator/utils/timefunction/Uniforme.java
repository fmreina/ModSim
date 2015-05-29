package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventFactory;
import modsim.simulator.utils.MathsUtils;

public class Uniforme implements TimeFunction {

	@Override
	public int getTime() {
		return MathsUtils.uniforme(EventFactory.ef1, EventFactory.ef2);
	}

}
