package modsim.simuator.control;

import java.util.ArrayList;

import modsim.simuator.vision.MainView;
import modsim.simulator.entities.Server;
import modsim.simulator.model.Event;
import modsim.simulator.model.EventArrival;
import modsim.simulator.model.EventChange;
import modsim.simulator.model.EventExit;
import modsim.simulator.model.Simulation;
import modsim.simulator.model.TimeFunc;

public class Simulator implements Runnable {

	private static int tNow;
	private static int tempoSimulacao;
	private static ArrayList<Event> events;
	private static Simulation simulation;
	public static boolean paused;
	public static boolean running;
	public static boolean fastForward;
	private static TimeFunc timeTypeEntity;
	private static TimeFunc timeTypeServer1;
	private static TimeFunc timeTypeServer2;
	
	
	public static void init(Simulation sim) {
		simulation = sim;
		
		// Variaveis de Controle
		events = new ArrayList<Event>();
		tNow = 0;
		paused = false;
		running = true;
		fastForward = false;
		tempoSimulacao = Integer.parseInt(MainView.getTextFieldSimulationTime().getText())*60;
		
		Server server_1 = new Server(1);
		Server server_2 = new Server(2);
		
	}
	
	public void run() {
		while(this.running){
			if(this.paused){
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					this.simulation.getLog().add("Erro ao pausar simula��o");
					this.running = false;
					continue;
				}
			}else {
				if (!this.fastForward){
					try{
						Thread.sleep(800);
					}catch(InterruptedException e){
						this.simulation.getLog().add("Erro ao executar a simula��o");
						this.running = false;
						continue;
					}
				}
				
				this.tNow++;
				
				System.out.println("Tempo de execu��o: "+this.tNow+" segundos");
				this.simulation.getLog().add("Tempo de execu��o: "+this.tNow+" segundos");
				
				ArrayList<Event> eventList = getEventOnTime(this.tNow);
				
				for(Event event : eventList){
					System.out.println(event.toString());
					this.simulation.getLog().add(event.toString());
					
					if(event instanceof EventArrival){
						// tratar chegada
						this.events.remove(event);
					}
					if(event instanceof EventExit){
						// tratar saida
						this.events.remove(event);
					}
					if(event instanceof EventChange){
						// tratar troca
						this.events.remove(event);
					}
				}
				
				newEvent();
				
				if(this.tNow > this.tempoSimulacao){
					this.running = false;
				}
				
			}
		}
	}

	private void newEvent() {
		boolean newEvent_1 = true;
		boolean newEvent_2 = true;
		for( Event event : this.events){
			if(event instanceof EventArrival){
				if(event.getEntidade().getType() == 1){
					newEvent_1 = false;
				}
				if(event.getEntidade().getType() == 2){
					newEvent_2 = false;
				}
			}
			if(newEvent_1){
				criateEvent_1();
			}
			if(newEvent_2){
				criateEvent_2();
			}
		}
	}

	private void criateEvent_1() {
		// criar evento
	}

	private void criateEvent_2() {
		// criar evento
	}

	private ArrayList<Event> getEventOnTime(int tNow2) {
		ArrayList<Event> eventsOnTime = new ArrayList<Event>();
		for(Event event : this.events){
			if(event.getTempoChegada()== this.tNow){
				eventsOnTime.add(event);
			}
		}
		return eventsOnTime;
	}
	
	private int verifyEntitiesOnSystem(){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for(Event event : this.events){
			if(event instanceof EventArrival){
				Integer id = ((EventArrival) event).getId();
				if(!ids.contains(id)){
					ids.add(id);
				}
			}else if(event instanceof EventExit){
				Integer id = ((EventExit) event).getId();
				if(!ids.contains(id)){
					ids.add(id);
				}
			}else if(event instanceof EventChange){
				Integer id = ((EventChange) event).getId();
				if(!ids.contains(id)){
					ids.add(id);
				}
			}
		}
		return ids.size();
	}

	public static int gettNow() {
		return tNow;
	}

	public static int getTempoSimulacao() {
		return tempoSimulacao;
	}

	public static ArrayList<Event> getEvents() {
		return events;
	}

	public static Simulation getSimulation() {
		return simulation;
	}

	public static boolean isPaused() {
		return paused;
	}

	public static boolean isRunning() {
		return running;
	}

	public static boolean isFastForward() {
		return fastForward;
	}

	public static TimeFunc getTimeTypeEntity() {
		return timeTypeEntity;
	}

	public static TimeFunc getTimeTypeServer1() {
		return timeTypeServer1;
	}

	public static TimeFunc getTimeTypeServer2() {
		return timeTypeServer2;
	}
	
}
