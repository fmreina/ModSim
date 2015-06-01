package modsim.simulator.entities;

import modsim.simulator.utils.timefunction.TimeFunction;
import sun.misc.Queue;

public class Server {
	
	private TimeFunction serviceFunc;
	private TipoServidor type;
	private int tempoServico;
	private Entity entity;
	private boolean broken;
	private Queue<Entity> fila;
	private int inicioFalha;
	private int tempoFalha;
	
	public Server(TipoServidor type, TimeFunction func){
		this.type = type;
		this.broken = false;
		this.fila = new Queue<Entity>();
		this.serviceFunc = func;
	}
	
	public TimeFunction getServiceFunc() {
		return serviceFunc;
	}

	public void setServiceFunc(TimeFunction serviceFunc) {
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
		entity.updateTempoEmFila();
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
	
	public Queue<Entity> getFila() {
		return fila;
	}
	
	public Entity getOcupante(){
		return entity;
	}
	
	public int getInicioFalha() {
		return inicioFalha;
	}

	public void setInicioFalha(int inicioFalha) {
		this.inicioFalha = inicioFalha;
	}

	public int getTempoFalha() {
		return tempoFalha;
	}

	public void updateTempoFalha(int fimFalha) {
		this.tempoFalha = tempoFalha + (fimFalha - inicioFalha);
	}
}
