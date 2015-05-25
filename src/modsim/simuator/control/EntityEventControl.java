package modsim.simuator.control;

import java.util.ArrayList;

import modsim.simuator.vision.MainView;
import modsim.simulator.entities.Entity;
import modsim.simulator.model.Event;
import modsim.simulator.model.EventArrival;
import modsim.simulator.model.TimeFunc;
import modsim.simulator.utils.MathsUtils;

public class EntityEventControl {

	private static Entity entity;
	private static int ef1; // entity field time 1
	private static int ef2; // entity field time 2
	private static int ef3; // entity field time 3

	public static ArrayList<Event> newArrivalEvent(int timeNow, TimeFunc func) {
		ArrayList<Event> events = new ArrayList<Event>();

		EventArrival arrival = newArrival(timeNow, func);

		events.add(arrival);

		return events;
	}

	private static EventArrival newArrival(int timeNow, TimeFunc func) {
		int arrivalTime = getArrivalTime(func);
		int percent1 = Integer.parseInt(MainView.getTextFieldPercEntType_1()
				.getText());
		int percent2 = Integer.parseInt(MainView.getTextFieldPercEntType_2()
				.getText());

		Entity entity = new Entity(percent1 > percent2 ? 1 : 2, arrivalTime);
		EventArrival arrival = new EventArrival(arrivalTime, entity);

		return arrival;
	}

	public static int getArrivalTime(TimeFunc func) {
		ef1 = Integer.parseInt(MainView.getTextFieldMinTEC().getText());
		ef2 = Integer.parseInt(MainView.getTextFieldMedTEC().getText());
		ef3 = Integer.parseInt(MainView.getTextFieldMaxTEC().getText());

		int time = 0;

		switch (func) {
		case CONSTANT:
			time = getConstTime();
			break;
		case NORMAL:
			time = getNormTime();
			break;
		case EXPONENTIAL:
			time = getExpoTime();
			break;
		case UNIFORM:
			time = getUnifTime();
			break;
		case TRIANGULAR:
			time = getTriaTime();
			break;
		}

		return time > 0 ? time : 1;
	}

	public void handleArrivalEvent(int eventId) {
		if(true /*servidorLivre*/){
			
		}else{
			removeEventById(eventId);
			// count / addToLine
		}
	}

	public void removeEventById(int eventId) {
		ArrayList<Event> eventList = new ArrayList<Event>();

		for (Event event : Simulator.getEvents()) {
			if (event instanceof EventArrival) {
				EventArrival arrivalEvent = (EventArrival) event;
				if (arrivalEvent.getId() == eventId) {
					eventList.add(arrivalEvent);
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
}
