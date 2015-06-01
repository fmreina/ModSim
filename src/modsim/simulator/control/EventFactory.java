package modsim.simulator.control;

import java.util.ArrayList;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.entities.TipoServidor;
import modsim.simulator.model.Event;
import modsim.simulator.model.EventArrival;
import modsim.simulator.model.EventExit;
import modsim.simulator.model.EventFailureEnd;
import modsim.simulator.model.EventFailureStart;
import modsim.simulator.utils.timefunction.TimeFunc;
import modsim.simulator.utils.timefunction.TimeFunction;
import modsim.simulator.vision.MainView;

public class EventFactory {

	public static int ef1; // entity field time 1
	public static int ef2; // entity field time 2
	public static int ef3; // entity field time 3
	public static TimeFunction arriveFunc;

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

	private static void setParamsToFailureStart() {
		ef1 = Integer.parseInt(MainView.getTextFieldMinTEF().getText());
		ef2 = Integer.parseInt(MainView.getTextFieldMedTEF().getText());
		ef3 = Integer.parseInt(MainView.getTextFieldMaxTEF().getText());
	}

	private static void setParamsToFailureEnd() {
		ef1 = Integer.parseInt(MainView.getTextFieldMinTF().getText());
		ef2 = Integer.parseInt(MainView.getTextFieldMedTF().getText());
		ef3 = Integer.parseInt(MainView.getTextFieldMaxTF().getText());
	}

	public static ArrayList<Event<Entity>> newArrivalEvent(int timeNow) {
		ArrayList<Event<Entity>> events = new ArrayList<Event<Entity>>();
		EventArrival arrival = newArrival(timeNow, arriveFunc);
		events.add(arrival);
		return events;
	}

	public static EventArrival newArrival(int timeNow, TimeFunction func) {
		setParamsToArrivalTime();
		int arrivalTime = getTime(func) + timeNow;

		int percent1 = Integer.parseInt(MainView.getTextFieldPercEntType_1()
				.getText());

		Entity entity = EntityFactory.newEntity(percent1, arrivalTime);
		EventArrival arrival = new EventArrival(arrivalTime, entity);

		return arrival;
	}

	static Event<Entity> newExit(Entity entity, int timeNow, TimeFunction func) {
		setParamsToExitTime(entity.getType());
		int exitTime = getTime(func) + timeNow;

		EventExit exit = new EventExit(exitTime, entity);

		if (entity.getType() == TipoServidor.TIPO_1) {
			Simulator.getStats().getListServer1Ocupation().add(exitTime);
		}
		if (entity.getType() == TipoServidor.TIPO_2) {
			Simulator.getStats().getListServer2Ocupation().add(exitTime);
		}

		return exit;
	}

	static EventFailureStart newFailureStart(Server server, int timeNow) {
		setParamsToFailureStart();

		TimeFunction timeFunction = TimeFunc.getType(MainView.getComboBoxTEF()
				.getSelectedItem().toString());
		int failureTimeStart = (getTime(timeFunction)*60) + timeNow;
		EventFailureStart failure = new EventFailureStart(failureTimeStart,
				server);
		return failure;
	}

	static Event<Server> newFailureEnd(Server server, int timeNow) {
		setParamsToFailureEnd();
		TimeFunction timeFunction = TimeFunc.getType(MainView.getComboBoxTF()
				.getSelectedItem().toString());
		int failureEnd = (getTime(timeFunction)/**60*/) + timeNow;

		if (server.getType() == TipoServidor.TIPO_1) {
			Simulator.getStats().setTimeOnFailureSvr1(failureEnd - timeNow);
		}
		if (server.getType() == TipoServidor.TIPO_2) {
			Simulator.getStats().setTimeOnFailureSvr2(failureEnd - timeNow);
		}
		
		return new EventFailureEnd(failureEnd, server);
	}

	public static int getTime(TimeFunction func) {
		int time = func.getTime();
		return time > 0 ? time : 1;
	}
}