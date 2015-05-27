package modsim.simulator.control;

import java.util.ArrayList;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.IEvent;
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

	private static Entity entity;
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

	public static ArrayList<Event> handleArrivalEvent(Event event, int timeNow) {
		Event e;
		if(event.getEntityServerType().equals(TipoServidor.TIPO_1)){
			Server server1 = Simulator.getServers().get(TipoServidor.TIPO_1);
			if(server1.isFree()){
				if(server1.getFila().isEmpty()){
					server1.ocuppyServer(entity); // tomada do servidor
					e = newExit(event.getEntidade(), timeNow, server1.getServiceFunc()); // gera saida
				}else{
					server1.getFila().add(event.getEntidade());
//					e = newExit(server1.getFila().get(0));					
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
			Server server2 = Simulator.getServers().get(TipoServidor.TIPO_2);
			if(server2.isFree()){
				server2.ocuppyServer(entity);
				newExit(event.getEntidade(), timeNow, server2.getServiceFunc());
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

	private static Event newExit(Entity event, int timeNow, TimeFunc func) {
		int arrivalTime = getArrivalTime(func) + timeNow;
		
		int percent1 = Integer.parseInt(MainView.getTextFieldPercEntType_1()
				.getText());

		Entity entity = EntitiyFactory.newEntity(percent1, arrivalTime);
		EventArrival arrival = new EventArrival(arrivalTime, entity);

		return arrival;
	}
	
	public static ArrayList<Event> handleEvent(EventExit event) {
		return null;
	}
	
	public static ArrayList<Event> handleEvent(EventChange event) {
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

	public static void handleEvent(Event event) {
		if(event instanceof EventArrival){
			handleEvent((EventArrival)event);
		}
		if(event instanceof EventExit){
			handleEvent((EventExit)event);
		}
		if(event instanceof EventChange){
			handleEvent((EventChange)event);
		}
	}
}
