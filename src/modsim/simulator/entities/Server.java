package modsim.simulator.entities;

import java.util.ArrayList;
import java.util.List;

import modsim.simulator.model.TimeFunc;

public class Server {
	
	private TimeFunc serviceFunc;
	private TipoServidor type;
	private int tempoServico;
	private Entity entity;
	private boolean broken;
	private List<Entity> fila;
	
	public Server(TipoServidor type, TimeFunc func){
		this.type = type;
		this.broken = false;
		this.fila = new ArrayList<Entity>();
		this.serviceFunc = func;
	}
	
	public TimeFunc getServiceFunc() {
		return serviceFunc;
	}

	public void setServiceFunc(TimeFunc serviceFunc) {
		this.serviceFunc = serviceFunc;
	}

	public TipoServidor getType() {
		return type;
	}

	public void setType(TipoServidor type) {
		this.type = type;
	}

	public int getTempoServico() {
		return tempoServico;
	}

	public void setTempoServico(int tempoServico) {
		this.tempoServico = tempoServico;
	}

	public boolean isFree() {
		return entity == null;
	}

	public void ocuppyServer(Entity entity, int tNow) {
		this.entity = entity;
		entity.setTempoInicioAtendimento(tNow);
	}
	
	public void releaseServer(int tNow){
		this.tempoServico = tempoServico + entity.getTempoNoServidor();
		entity.setTempoSaida(tNow);
		this.entity = null;
	}
	
	public boolean isBroken() {
		return broken;
	}
	
	public void setBroken(boolean broken) {
		this.broken = broken;
	}
	
	public List<Entity> getFila() {
		return fila;
	}
}
