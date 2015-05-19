package modsim.simulator.entities;

public class Entity {

	private int id;
	private int type;
	private int tempoChegada;
	private int tempoSaida;
	private int tempoInicioAtendimento;
	private int tempoEmFila;
	
	public Entity(int type, int tempoChegada){
		this.type = type;
		this.tempoChegada = tempoChegada;
		/*this.tempoInicioAtendimento = tempoChegada;
		this.tempoEmFila = 0;
		this.tempoSaida = tempoChegada;*/
	}
	
	public Entity(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTempoChegada() {
		return tempoChegada;
	}

	public void setTempoChegada(int tempoChegada) {
		this.tempoChegada = tempoChegada;
	}

	public int getTempoSaida() {
		return tempoSaida;
	}

	public void setTempoSaida(int tempoSaida) {
		this.tempoSaida = tempoSaida;
	}

	public int getTempoInicioAtendimento() {
		return tempoInicioAtendimento;
	}

	public void setTempoInicioAtendimento(int tempoInicioAtendimento) {
		this.tempoInicioAtendimento = tempoInicioAtendimento;
	}

	public int getTempoEmFila() {
		return tempoInicioAtendimento - tempoChegada;
	}
}
