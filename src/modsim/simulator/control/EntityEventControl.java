package modsim.simulator.control;

import java.util.ArrayList;

import modsim.simulator.entities.Entity;
import modsim.simulator.model.Event;
import modsim.simulator.model.EventArrival;
import modsim.simulator.model.TimeFunc;
import modsim.simulator.utils.MathsUtils;
import modsim.simulator.vision.MainView;

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

	public static EventArrival newArrival(int timeNow, TimeFunc func) {
		int arrivalTime = getArrivalTime(func);
		
		int percent1 = Integer.parseInt(MainView.getTextFieldPercEntType_1()
				.getText());

		Entity entity = EntitiyFactory.newEntity(percent1, arrivalTime);
		EventArrival arrival = new EventArrival(arrivalTime, entity);

		return arrival;
	}

	public static int getArrivalTime(TimeFunc func) {
		ef1 = Integer.parseInt(MainView.getTextFieldMinTEC().getText());
		ef2 = Integer.parseInt(MainView.getTextFieldMedTEC().getText());
		ef3 = Integer.parseInt(MainView.getTextFieldMaxTEC().getText());

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
}
