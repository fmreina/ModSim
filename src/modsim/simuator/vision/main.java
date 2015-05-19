package modsim.simuator.vision;

import modsim.simuator.control.Simulator;
import modsim.simulator.model.Simulation;

public class main {

	public static void main(String [] args){
//		Thread entytiGenerator = new Thread();
		
//		entytiGenerator.run();
		
		System.out.println(">>");
		Simulator sim = new Simulator();
		sim.init(new Simulation("Test", 1));
		sim.run();
		
		System.out.println("=P");
	}
	
}
