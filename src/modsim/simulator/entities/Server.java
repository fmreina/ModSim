package modsim.simulator.entities;

import java.util.ArrayList;
import java.util.List;

public class Server {

	private TipoServidor type;
	private int tempoServico;
	private Entity entity;
	private boolean broken;
	private List<Entity> fila;
	
	public Server(TipoServidor type){
		this.type = type;
		this.broken = false;
		this.fila = new ArrayList<Entity>();
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
		return entity != null;
	}

	public void ocuppyServer(Entity entity) {
		this.entity = entity;
	}
	
	public void releaseServer(){
		this.tempoServico = tempoServico + entity.getTempoNoServidor();
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
