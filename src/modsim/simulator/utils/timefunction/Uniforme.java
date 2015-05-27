package modsim.simulator.utils.timefunction;

import modsim.simulator.control.EventControl;
import modsim.simulator.utils.MathsUtils;

public class Uniforme implements TimeFunction {

	@Override
	public int getTime() {
		return MathsUtils.uniforme(EventControl.ef1, EventControl.ef2);
	}

}
