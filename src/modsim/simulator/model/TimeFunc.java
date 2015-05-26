package modsim.simulator.model;

public enum TimeFunc {

	CONSTANTE,
	NORMAL,
	TRIANGULAR,
	UNIFORME,
	EXPONENCIAL;
	
	public static TimeFunc getType(String type){
		TimeFunc timeType = null;
		
		for(TimeFunc localType : values()){
			if(localType.toString().equals(type.toUpperCase())){
				timeType = localType;
				break;
			}
		}
		return timeType;
	}
}
