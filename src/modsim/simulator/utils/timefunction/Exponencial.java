package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventFactory;
import modsim.simulator.utils.MathsUtils;

public class Exponencial implements TimeFunction {

	@Override
	public int getTime() {
		return MathsUtils.expo(EventFactory.ef1);
	}

}
