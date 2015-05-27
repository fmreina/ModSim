package modsim.simulator.utils.timefunction;


public enum TimeFunc {

	CONSTANTE(new Constante()), NORMAL(new Normal()), TRIANGULAR(
			new Triangular()), UNIFORME(new Uniforme()), EXPONENCIAL(
			new Exponencial());

	private TimeFunction function;

	private TimeFunc(TimeFunction function) {
		this.function = function;
	}
	
	public TimeFunction getFunction(){
		return this.function;
	}

	public static TimeFunction getType(String type) {
		for (TimeFunc localType : values()) {
			if (localType.toString().equals(type.toUpperCase())) {
				return localType.getFunction();
			}
		}
		return null;
	}
}
