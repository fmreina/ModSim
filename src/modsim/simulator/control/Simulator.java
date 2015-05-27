package modsim.simulator.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;
import modsim.simulator.model.Event;
import modsim.simulator.model.Simulation;
import modsim.simulator.utils.timefunction.TimeFunc;
import modsim.simulator.utils.timefunction.TimeFunction;
import modsim.simulator.vision.MainView;

public class Simulator implements Runnable {

	private static int tNow;
	private static int tempoSimulacao;
	private static ArrayList<Event> events;
	private static Simulation simulation;
	public static boolean paused;
	public static boolean running;
	public static boolean fastForward;
	private static HashMap<TipoServidor, Server> servers;


	public static void init(Simulation sim) {
		simulation = sim;
		// Variaveis de Controle
		events = new ArrayList<Event>();
		tNow = 0;
		paused = false;
		running = true;
		fastForward = MainView.getCheckboxFastFoward().getState();
		tempoSimulacao = Integer.parseInt(MainView.getTextFieldSimulationTime()
				.getText()) * 60;
		servers = new HashMap<TipoServidor, Server>();
		TimeFunction serviceFunc1 = TimeFunc.getType(MainView.getComboBoxTimeServer_1().getSelectedItem().toString());
		TimeFunction serviceFunc2 = TimeFunc.getType(MainView.getComboBoxTimeServer_2().getSelectedItem().toString());
		servers.put(TipoServidor.TIPO_1, new Server(TipoServidor.TIPO_1, serviceFunc1));
		servers.put(TipoServidor.TIPO_2, new Server(TipoServidor.TIPO_2, serviceFunc2));
		EventControl.arriveFunc = TimeFunc.getType(MainView.getComboBoxTimeEntity().getSelectedItem().toString());	
		newEvent();
	}

	public void run() {
		while (running) {
			if (paused) {
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					simulation.getLog().add("Erro ao pausar simulação");
					running = false;
					continue;
				}
			} else {
				
				if (!fastForward) {
					try {
						Thread.sleep(MainView.getSlider().getValue());
					} catch (InterruptedException e) {
						simulation.getLog().add(
								"Erro ao executar a simulação");
						running = false;
						continue;
					}
				}
				fastForward = MainView.getCheckboxFastFoward().getState();

				MainView.print("Tempo de execução: " + tNow + " segundos");
				System.out.println("Tempo de execução: " + tNow + " segundos");
				simulation.getLog().add("Tempo de execução: " + tNow + " segundos");
				
				ArrayList<Event> eventList = getEventOnTime(tNow);				
				
				for (Event event : eventList) {
					System.out.println(event.toString());
					MainView.print(event.toString());
					simulation.getLog().add(event.toString());
					Event newEvent = event.getHandler().handleEvent(event, tNow);
					if(newEvent != null){
						events.add(newEvent);
					}
					events.remove(event);
				}
				tNow++;
				if (tNow > tempoSimulacao) {
					running = false;
				}
			}
		}
	}

	public static void newEvent() {
		criateEvent_Arrival();
	}

	private static void criateEvent_Arrival() {
		List<Event> newEvents = EventControl.newArrivalEvent(tNow);
		events.addAll(newEvents);
		Collections.sort(events);
	}

	private ArrayList<Event> getEventOnTime(int tNow2) {
		ArrayList<Event> eventsOnTime = new ArrayList<>();
		for (Event event : events) {
			if (event.getTempoExecucao() == tNow) {
				eventsOnTime.add(event);
			}
		}
		return eventsOnTime;
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

	public static HashMap<TipoServidor, Server> getServers() {
		return servers;
	}
}
