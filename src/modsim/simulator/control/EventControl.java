package modsim.simulator.control;

import java.util.ArrayList;

import modsim.simulator.entities.Entity;
import modsim.simulator.entities.TipoServidor;
import modsim.simulator.model.Event;
import modsim.simulator.model.EventArrival;
import modsim.simulator.model.EventExit;
import modsim.simulator.utils.timefunction.TimeFunction;
import modsim.simulator.vision.MainView;

public class EventControl {

	public static int ef1; // entity field time 1
	public static int ef2; // entity field time 2
	public static int ef3; // entity field time 3
	public static TimeFunction arriveFunc;
		if(entity.getType() == TipoServidor.TIPO_1){
			Simulator.getStats().incrNbEntities1();
		}
		if(entity.getType() == TipoServidor.TIPO_2){
			Simulator.getStats().incrNbEntities2();
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
	
	public static ArrayList<Event> newArrivalEvent(int timeNow) {
		ArrayList<Event> events = new ArrayList<Event>();
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
	
	static Event newExit(Entity entity, int timeNow, TimeFunction func,
			int id) {
		setParamsToExitTime(entity.getType());
		int exitTime = getTime(func) + timeNow;

		EventExit exit = new EventExit(exitTime, entity, id);
		
		if(entity.getType() == TipoServidor.TIPO_1){
			Simulator.getStats().getListServer1Ocupation().add(exitTime);
		}
		if(entity.getType() == TipoServidor.TIPO_2){
			Simulator.getStats().getListServer2Ocupation().add(exitTime);
		}

		return exit;
	}
	
	public static int getTime(TimeFunction func) {
		int time = func.getTime();
		return time > 0 ? time : 1;
	}

//			if(event.getEntidade().getType() == TipoServidor.TIPO_1){
//				Simulator.getStats().incrNbEntities1();
//			}
//			if(event.getEntidade().getType() == TipoServidor.TIPO_2){
//				Simulator.getStats().incrNbEntities2();
//			}
			if(event.getEntidade().getType() == TipoServidor.TIPO_1){
				Simulator.getStats().incrNbEntities1Completed();
			}
			if(event.getEntidade().getType() == TipoServidor.TIPO_2){
				Simulator.getStats().incrNbEntities2Completed();
			}
}