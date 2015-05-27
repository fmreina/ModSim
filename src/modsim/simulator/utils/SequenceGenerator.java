package modsim.simulator.utils;

public class SequenceGenerator {
	
	public static int num = 0;
	
	public static int next() {
		return num++;
	}

}
