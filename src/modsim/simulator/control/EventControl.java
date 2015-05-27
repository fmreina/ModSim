package modsim.simulator.control;

import java.util.ArrayList;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;
import modsim.simulator.model.Event;
import modsim.simulator.model.EventArrival;
import modsim.simulator.model.EventChange;
import modsim.simulator.model.EventExit;
import modsim.simulator.model.TimeFunc;
import modsim.simulator.utils.MathsUtils;
import modsim.simulator.vision.MainView;

public class EventControl {

	private static int ef1; // entity field time 1
	private static int ef2; // entity field time 2
	private static int ef3; // entity field time 3
	public static TimeFunc arriveFunc;

	public static ArrayList<Event> newArrivalEvent(int timeNow) {
		ArrayList<Event> events = new ArrayList<Event>();

		EventArrival arrival = newArrival(timeNow, arriveFunc);

		events.add(arrival);

		return events;
	}

	public static EventArrival newArrival(int timeNow, TimeFunc func) {
		setParamsToArrivalTime();
		int arrivalTime = getTime(func) + timeNow;

		int percent1 = Integer.parseInt(MainView.getTextFieldPercEntType_1()
				.getText());

		Entity entity = EntityFactory.newEntity(percent1, arrivalTime);
		EventArrival arrival = new EventArrival(arrivalTime, entity);

		return arrival;
	}

	public static int getTime(TimeFunc func) {
		int time = 0;

		switch (func) {
		case CONSTANTE:
			time = getConstTime();
			break;
		case NORMAL:
			time = getNormTime();
			break;
		case EXPONENCIAL:
			time = getExpoTime();
			break;
		case UNIFORME:
			time = getUnifTime();
			break;
		case TRIANGULAR:
			time = getTriaTime();
			break;
		}

		return time > 0 ? time : 1;
	}

	private static void setParamsToArrivalTime() {
		ef1 = Integer.parseInt(MainView.getTextFieldMinTEC().getText());
		ef2 = Integer.parseInt(MainView.getTextFieldMedTEC().getText());
		ef3 = Integer.parseInt(MainView.getTextFieldMaxTEC().getText());
	}

	private static void setParamsToExitTime(TipoServidor tipo) {
		if (tipo.equals(TipoServidor.TIPO_1)) {
			ef1 = Integer.parseInt(MainView.getTextFieldMinTS_1().getText());
			ef2 = Integer.parseInt(MainView.getTextFieldMedTS_1().getText());
			ef3 = Integer.parseInt(MainView.getTextFieldMaxTS_1().getText());
		} else {
			ef1 = Integer.parseInt(MainView.getTextFieldMinTS_2().getText());
			ef2 = Integer.parseInt(MainView.getTextFieldMedTS_2().getText());
			ef3 = Integer.parseInt(MainView.getTextFieldMaxTS_2().getText());
		}
	}

	public static Event handleEvent(EventArrival event, int timeNow) {
		Event e = null;
		TipoServidor entityServerType = event.getEntityServerType();
		Server server = Simulator.getServers().get(entityServerType);
		if (entityServerType.equals(TipoServidor.TIPO_1)) {
			if (server.isFree()) {
				if (server.getFila().isEmpty()) {
					server.ocuppyServer(event.getEntidade()); // tomada do servidor
					e = newExit(event.getEntidade(), timeNow,
							server.getServiceFunc()); // gera saida
				} else {
					server.getFila().add(event.getEntidade());
					// e = newExit(server1.getFila().get(0));
					server.getFila().remove(0);
				}
			} else {
				if (server.isBroken()) {
					newChange();// go to server 2
				} else {
					server.getFila().add(event.getEntidade());
				}
			}
		} else {
			if (server.isFree()) {
				server.ocuppyServer(event.getEntidade());
				newExit(event.getEntidade(), timeNow, server.getServiceFunc());
			} else {
				if (server.isBroken()) {
					// go to server 1
					newChange();
				} else {
					server.getFila().add(event.getEntidade());
				}
			}
		}
		return e;
	}

	private static Event newExit(Entity entity, int timeNow, TimeFunc func) {
		setParamsToExitTime(entity.getType());
		int exitTime = getTime(func) + timeNow;

		EventExit exit = new EventExit(exitTime, entity);

		return exit;
	}

	public static Event handleEvent(EventExit event, int timeNow) {
		Event e = null;
		TipoServidor entityServerType = event.getEntityServerType();
		Server server = Simulator.getServers().get(entityServerType);
		server.releaseServer();
		if (!server.getFila().isEmpty()) {
			server.ocuppyServer(event.getEntidade()); // tomada do servidor
			e = newExit(event.getEntidade(), timeNow, server.getServiceFunc()); // gera saida
		}
		return e;
	}

	public static Event handleEvent(EventChange event) {
		return null;
	}

	private static void newChange() {

	}

	public void removeEventById(int eventId) {
		ArrayList<Event> eventList = new ArrayList<Event>();

		for (Event event : Simulator.getEvents()) {
			if (event instanceof EventArrival) {
				if (event.getId() == eventId) {
					eventList.add(event);
				}
			}
		}
		Simulator.getEvents().removeAll(eventList);
	}

	private static int getConstTime() {
		return ef1;
	}

	private static int getNormTime() {
		return MathsUtils.normal(ef1, ef2);
	}

	private static int getExpoTime() {
		return MathsUtils.expo(ef1);
	}

	private static int getUnifTime() {
		return MathsUtils.uniforme(ef1, ef2);
	}

	private static int getTriaTime() {
		return MathsUtils.triangular(ef1, ef2, ef3);
	}

	public static Event handleEvent(Event event, int tNow) {
		if (event instanceof EventArrival) {
			return handleEvent((EventArrival) event, tNow);
		}
		if (event instanceof EventExit) {
			return handleEvent((EventExit) event, tNow);
		}
		return handleEvent((EventChange) event);
	}
}
