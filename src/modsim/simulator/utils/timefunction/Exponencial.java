package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventControl;
import modsim.simulator.utils.MathsUtils;

public class Exponencial implements TimeFunction {

	@Override
	public int getTime() {
		return MathsUtils.expo(EventControl.ef1);
	}

}
