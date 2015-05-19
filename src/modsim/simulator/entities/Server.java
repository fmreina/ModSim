package modsim.simulator.entities;

public class Server {

	private int type;
	private int tempoServico;
	private boolean free;
	
	public Server(int type){
		this.type = type;
		this.free = true;
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

	public boolean getStatus() {
		return free;
	}

	public void setStatus(boolean free) {
		this.free = free;
	}
	
}
