package modsim.simulator.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;
import modsim.simulator.model.Event;
import modsim.simulator.model.EventArrival;
import modsim.simulator.model.EventChange;
import modsim.simulator.model.EventExit;
import modsim.simulator.model.Simulation;
import modsim.simulator.model.TimeFunc;
import modsim.simulator.utils.Statistics;
import modsim.simulator.vision.MainView;

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
		TimeFunc serviceFunc1 = TimeFunc.getType(MainView
				.getComboBoxTimeServer_1().getSelectedItem().toString());
		TimeFunc serviceFunc2 = TimeFunc.getType(MainView
				.getComboBoxTimeServer_2().getSelectedItem().toString());
		servers.put(TipoServidor.TIPO_1, new Server(TipoServidor.TIPO_1,
				serviceFunc1));
		servers.put(TipoServidor.TIPO_2, new Server(TipoServidor.TIPO_2,
				serviceFunc2));
		EventControl.arriveFunc = TimeFunc.getType(MainView
				.getComboBoxTimeEntity().getSelectedItem().toString());
	}

	public void run() {
		while (this.running) {
			if (this.paused) {
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					this.simulation.getLog().add("Erro ao pausar simulação");
					this.running = false;
					continue;
				}
			} else {

				if (!this.fastForward) {
					try {
						Thread.sleep(MainView.getSlider().getValue());
					} catch (InterruptedException e) {
						this.simulation.getLog().add(
								"Erro ao executar a simulação");
						this.running = false;
						continue;
					}
				}
				fastForward = MainView.getCheckboxFastFoward().getState();
				this.tNow++;

				MainView.print("Tempo de execução: " + this.tNow + " segundos");
				System.out.println("Tempo de execução: " + this.tNow
						+ " segundos");
				this.simulation.getLog().add(
						"Tempo de execução: " + this.tNow + " segundos");

				ArrayList<Event> eventList = getEventOnTime(this.tNow);

				for (Event event : eventList) {
					System.out.println(event.toString());
					MainView.print(event.toString());
					this.simulation.getLog().add(event.toString());
					Event handleArrivalEvent = EventControl.handleEvent(event,
							tNow);
					if (handleArrivalEvent != null) {
						events.add(handleArrivalEvent);
					}
					this.events.remove(event);
				}

				newEvent();

				if (this.tNow > this.tempoSimulacao) {
					this.running = false;
				}

				// Statistics
				int[] countIds = getNumIdsOnSyst();
				simulation.getStats().addListNbOfEntities1(countIds[0]);
				simulation.getStats().addListNbOfEntities2(countIds[1]);
				
//				simulation.getStats().addListServer1Ocupation(EventControl.));
			}
		}
	}

	private int[] getNumIdsOnSyst() {
		ArrayList<Integer> ids1 = new ArrayList<Integer>();
		ArrayList<Integer> ids2 = new ArrayList<Integer>();

		for (Event e : events) {
			if (e instanceof EventArrival) {
				if (((EventArrival) e).getEntidade().getType() == TipoServidor.TIPO_1) {
					Integer id = ((EventArrival) e).getEntidade().getId();
					if (!ids1.contains(id))
						ids1.add(id);
				}
				if (((EventArrival) e).getEntidade().getType() == TipoServidor.TIPO_2) {
					Integer id = ((EventArrival) e).getEntidade().getId();
					if (!ids2.contains(id))
						ids2.add(id);
				}
			}else if (e instanceof EventExit) {
				if (((EventExit) e).getEntidade().getType() == TipoServidor.TIPO_1) {
					Integer id = ((EventExit) e).getEntidade().getId();
					if (!ids1.contains(id))
						ids1.add(id);
				}
				if (((EventExit) e).getEntidade().getType() == TipoServidor.TIPO_2) {
					Integer id = ((EventExit) e).getEntidade().getId();
					if (!ids2.contains(id))
						ids2.add(id);
				}
			}
			// TODO: contar número de trocas
			/*else if (e instanceof EventChange) {
				if (((EventChange) e).getEntidade().getType() == TipoServidor.TIPO_1) {
					Integer id = ((EventChange) e).getEntidade().getId();
					if (!ids1.contains(id))
						ids1.add(id);
				}
				if (((EventChange) e).getEntidade().getType() == TipoServidor.TIPO_2) {
					Integer id = ((EventChange) e).getEntidade().getId();
					if (!ids2.contains(id))
						ids2.add(id);
				}
			}*/
		}

		int[] countIds = new int[2];
		countIds[0] = ids1.size();
		countIds[1] = ids2.size();
		return countIds;
	}

	private void newEvent() {
		criateEvent_Arrival();
	}

	private void criateEvent_Arrival() {
		List<Event> newEvents = EventControl.newArrivalEvent(tNow);
		events.addAll(newEvents);
		Collections.sort(events);
	}

	private ArrayList<Event> getEventOnTime(int tNow2) {
		ArrayList<Event> eventsOnTime = new ArrayList<Event>();
		for (Event event : this.events) {
			if (event.getTempoExecucao() == this.tNow) {
				eventsOnTime.add(event);
			}
		}
		return eventsOnTime;
	}

	private int verifyEntitiesOnSystem() {
		ArrayList<Integer> ids = new ArrayList<Integer>();

		for (Event event : this.events) {
			Integer id = event.getEntidade().getId();
			if (!ids.contains(id)) {
				ids.add(id);
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

	public static HashMap<TipoServidor, Server> getServers() {
		return servers;
	}
}
