package modsim.simulator.control;

import java.util.Random;

import modsim.simulator.model.entity.Entity;
import modsim.simulator.model.server.TipoServidor;

public class EntityFactory {
	
	public static Entity newEntity(int typeOneProbability, int arrivalTime){
		Random random = new Random();
		int nextInt = random.nextInt(100);
		if(nextInt >= typeOneProbability){
			return new Entity(TipoServidor.TIPO_2, arrivalTime);
		}else{
			return new Entity(TipoServidor.TIPO_1, arrivalTime);
		}
	}

}
