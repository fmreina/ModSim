package modsim.simulator.entities;

public enum TipoServidor {
	
	TIPO_1, TIPO_2;
	
	public static TipoServidor getAnotherType(TipoServidor type){
		if(type.equals(TIPO_1))
			return TIPO_2;
		return TIPO_1;		
	}
}
