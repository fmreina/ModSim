package modsim.simulator.control;

import java.util.Random;

import modsim.simulator.entities.Entity;

public class EntitiyFactory {
	
	public static Entity newEntity(int arrivalTime, int typeOneProbability){
		Random random = new Random();
		int nextInt = random.nextInt(100);
		if(nextInt > typeOneProbability){
			return new Entity(2, arrivalTime);
		}
		return new Entity(1, arrivalTime);
	}

}
