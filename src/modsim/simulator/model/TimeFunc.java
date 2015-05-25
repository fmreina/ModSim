package modsim.simulator.model;

public enum TimeFunc {

	CONSTANT,
	NORMAL,
	TRIANGULAR,
	UNIFORM,
	EXPONENTIAL;
	
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
