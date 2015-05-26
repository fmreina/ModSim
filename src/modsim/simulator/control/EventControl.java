package modsim.simulator.control;

import java.util.ArrayList;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.Server;
import modsim.simulator.model.Event;
import modsim.simulator.model.EventArrival;
import modsim.simulator.model.TimeFunc;
import modsim.simulator.utils.MathsUtils;
import modsim.simulator.vision.MainView;

public class EventControl {

	private static Entity entity;
	private static int ef1; // entity field time 1
	private static int ef2; // entity field time 2
	private static int ef3; // entity field time 3
	private static Server server1;
	private static Server server2;

	public static ArrayList<Event> newArrivalEvent(int timeNow, TimeFunc func) {
		ArrayList<Event> events = new ArrayList<Event>();

		EventArrival arrival = newArrival(timeNow, func);

		events.add(arrival);

		return events;
	}

	public static EventArrival newArrival(int timeNow, TimeFunc func) {
		int arrivalTime = getArrivalTime(func) + timeNow;
		
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

	public static ArrayList<Event> handleArrivalEvent(Event event, int entityType) {
		Event e;
		if(entityType == 1){
			if(server1.isFree()){
				if(server1.getFila().isEmpty()){
					server1.setFree(false); // tomada do servidor
					e = newExit(event.getEntidade()); // gera saida
				}else{
					server1.getFila().add(event.getEntidade());
					e = newExit(server1.getFila().get(0));					
					server1.getFila().remove(0);
				}
			}else{
				if(server1.isBroken()){
					newChange();//go to server 2
				}else{
					server1.getFila().add(event.getEntidade());
				}
			}
		}else{
			if(server2.isFree()){
				server2.setFree(false);
				newExit(event.getEntidade());
			}else{
				if(server2.isBroken()){
					//go to server 1
					newChange();
				}else{
					server2.getFila().add(event.getEntidade());
				}
			}
		}
		return null;
	}

	private static void newChange() {
		// TODO Auto-generated method stub
		
	}

	private static Event newExit(Entity event) {
		
		return null;
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
