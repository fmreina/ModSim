package modsim.simulator.entities;

public class Server {

	private TipoServidor type;
	private int tempoServico;
	private Entity entity;
	
	public Server(TipoServidor type){
		this.type = type;
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
	
}
