package modsim.simulator.entities;

import java.util.ArrayList;
import java.util.List;

public class Server {

	private int type;
	private int tempoServico;
	private boolean free;
	private boolean broken;
	private List<Entity> fila;
	
	public Server(int type){
		this.type = type;
		this.free = true;
		this.broken = false;
		this.fila = new ArrayList<Entity>();
	}
	public Server(){
		this.free = true;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTempoServico() {
		return tempoServico;
	}

	public void setTempoServico(int tempoServico) {
		this.tempoServico = tempoServico;
	}

	public boolean isFree() {
		return free;
	}
	
	public void setFree(boolean free) {
		this.free = free;
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
