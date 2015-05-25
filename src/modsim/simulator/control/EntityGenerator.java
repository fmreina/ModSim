package modsim.simulator.control;

import java.util.Random;

import modsim.simulator.entities.Entity;

public class EntityGenerator implements Runnable{

	private Entity ent;
	private static boolean running;
	private static boolean paused;
	private static boolean fastFoward;
	private static Random rand;
	
	
	public static void init(){
		running = true;
		paused = false;
		fastFoward = false;
		rand = new Random();
	}
	
	public void run() {
		while(running){
			if(this.paused){
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					this.running = false;
					e.printStackTrace();
					continue;
				}
			}else{
				if(!fastFoward){
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						this.running = false;
						e.printStackTrace();
						continue;
					}
				}
				
				ent = new Entity();
				
				ent.setType(rand.nextInt(2)+1);
				
				System.out.println("Nova entidade: Tipo: "+ent.getType());
				
			}
		}
	}

}
