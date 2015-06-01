package modsim.simulator.model.server;

public enum TipoServidor {

	TIPO_1("Tipo 1"), TIPO_2("Tipo 2");

	private String nome;

	private TipoServidor(String nome) {
		this.nome = nome;
	}

	public static TipoServidor getAnotherType(TipoServidor type) {
		if (type.equals(TIPO_1))
			return TIPO_2;
		return TIPO_1;
	}

	public String getNome() {
		return nome;
	}
}
