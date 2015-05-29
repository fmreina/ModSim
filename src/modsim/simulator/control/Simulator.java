package modsim.simulator.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;
import modsim.simulator.model.Event;
import modsim.simulator.model.Simulation;
import modsim.simulator.utils.Statistics;
import modsim.simulator.utils.StatisticsCalculator;
import modsim.simulator.utils.timefunction.TimeFunc;
import modsim.simulator.utils.timefunction.TimeFunction;
import modsim.simulator.vision.MainView;

public class Simulator<V> implements Runnable {

	private static int tNow;
	private static int tempoSimulacao;
	private static ArrayList<Event> events;
	private static Simulation simulation;
	public static boolean paused;
	public static boolean running;
	public static boolean fastForward;
	private static HashMap<TipoServidor, Server> servers;
	public static Statistics stats;

	public static void init(Simulation sim) {
		simulation = sim;
		stats = sim.getStats();
		// Variaveis de Controle
		events = new ArrayList<Event>();
		tNow = 0;
		paused = false;
		running = true;
		fastForward = MainView.getCheckboxFastFoward().getState();
		tempoSimulacao = Integer.parseInt(MainView.getTextFieldSimulationTime()
				.getText()) * 60;
		servers = new HashMap<TipoServidor, Server>();
		TimeFunction serviceFunc1 = TimeFunc.getType(MainView
				.getComboBoxTimeServer_1().getSelectedItem().toString());
		TimeFunction serviceFunc2 = TimeFunc.getType(MainView
				.getComboBoxTimeServer_2().getSelectedItem().toString());
		servers.put(TipoServidor.TIPO_1, new Server(TipoServidor.TIPO_1,
				serviceFunc1));
		servers.put(TipoServidor.TIPO_2, new Server(TipoServidor.TIPO_2,
				serviceFunc2));
		EventFactory.arriveFunc = TimeFunc.getType(MainView
				.getComboBoxTimeEntity().getSelectedItem().toString());
		createEventArrival();
		createEventFailureStart();
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
						simulation.getLog().add("Erro ao executar a simulação");
						running = false;
						continue;
					}
				}
				fastForward = MainView.getCheckboxFastFoward().getState();

				MainView.print("Tempo de execução: " + tNow + " segundos");
				System.out.println("Tempo de execução: " + this.tNow
						+ " segundos");
				this.simulation.getLog().add(
						"Tempo de execução: " + this.tNow + " segundos");

				ArrayList<Event> eventList = getEventOnTime(tNow);

				for (Event event : eventList) {
					System.out.println(event.toString());
					MainView.print(event.toString());
					simulation.getLog().add(event.toString());
					Event newEvent = null;
					try {
						newEvent = event.getHandler().handleEvent(event, tNow);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (newEvent != null) {
						events.add(newEvent);
					}
					events.remove(event);
				}

				if (tNow > tempoSimulacao) {
					running = false;
					System.out.println(stats.toString());
				}

				tNow++;

				// Statistics
				int[] countIds = getNumIdsOnSyst();
				l1.add(countIds[0]);
				l2.add(countIds[1]);
				simulation.getStats().addListNbOfEntities1(countIds[0]);
				simulation.getStats().addListNbOfEntities2(countIds[1]);
			}
		}
		MainView.getButtonIniciar().setEnabled(true);
		MainView.getButtonPausar().setEnabled(false);
		StatisticsCalculator.calculateStatistics(stats);
		System.out.println(stats.toString());
	}

	private ArrayList<Integer> l1 = new ArrayList<Integer>();
	private ArrayList<Integer> l2 = new ArrayList<Integer>();

	private int[] getNumIdsOnSyst() {
		HashMap<TipoServidor, Set<Integer>> ids = new HashMap<TipoServidor, Set<Integer>>();
		ids.put(TipoServidor.TIPO_1, new HashSet<Integer>());
		ids.put(TipoServidor.TIPO_2, new HashSet<Integer>());

		for (Event e : events) {
			Integer id = e.getId();
			ids.get(e.getType()).add(id);
		}
		// TODO: contar número de trocas
		/*
		 * else if (e instanceof EventChange) { if (((EventChange)
		 * e).getEntidade().getType() == TipoServidor.TIPO_1) { Integer id =
		 * ((EventChange) e).getEntidade().getId(); if (!ids1.contains(id))
		 * ids1.add(id); } if (((EventChange) e).getEntidade().getType() ==
		 * TipoServidor.TIPO_2) { Integer id = ((EventChange)
		 * e).getEntidade().getId(); if (!ids2.contains(id)) ids2.add(id); } }
		 */

		int[] countIds = new int[2];
		countIds[0] = ids.get(TipoServidor.TIPO_1).size();
		countIds[1] = ids.get(TipoServidor.TIPO_2).size();
		return countIds;
	}

	public static void createEventArrival() {
		List<Event<Entity>> newEvents = EventFactory.newArrivalEvent(tNow);
		events.addAll(newEvents);
		Collections.sort(events);
	}

	public static void createEventFailureStart() {
		List<Event<Server>> newEvents = new ArrayList<Event<Server>>();
		newEvents.add(EventFactory.newFailureStart(
				servers.get(TipoServidor.TIPO_1), tNow));
		newEvents.add(EventFactory.newFailureStart(
				servers.get(TipoServidor.TIPO_2), tNow));
		events.addAll(newEvents);
		Collections.sort(events);
	}

	private ArrayList<Event> getEventOnTime(int tNow2) {
		ArrayList<Event> eventsOnTime = new ArrayList<Event>();
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

	public static Statistics getStats() {
		return stats;
	}

}
