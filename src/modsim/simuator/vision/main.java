package modsim.simuator.vision;

import modsim.simuator.control.EntityGenerator;
import modsim.simuator.control.Simulator;
import modsim.simulator.model.Simulation;

public class main {

	public static void main(String [] args){
//		Thread entytiGenerator = new Thread();
		
//		entytiGenerator.run();
		
		System.out.println(">>");
		
		Simulator sim = new Simulator();
		EntityGenerator entGen = new EntityGenerator();
		
		sim.init(new Simulation("Test", 1));
		entGen.init();
		
		sim.run();
		entGen.run();
		
		System.out.println("<<");
	}
	
}
