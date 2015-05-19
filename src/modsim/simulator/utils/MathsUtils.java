package modsim.simulator.utils;

public class MathsUtils {

	public static double rand() {
		return Math.random();
	}

	public static int expo(int lambda) {
		double r = rand();
		double x = (-lambda) * (Math.log(1 - r));

		int valor = (int) x;
		return normalizar(valor);
	}

	public static int normal(int a, int b) {
		double r1 = rand();
		double r2 = rand();

		double z1 = Math.pow((-2 * Math.log(r1)), 0.5) * Math.cos(r2);
		double x1 = b + (a * z1);

		int valor = (int) x1;
		return normalizar(valor);
	}

	public static int uniforme(int a, int b) {
		double uniforme = a + ((b - a) * rand());

		int valor = (int) uniforme;
		return normalizar(valor);
	}

	public static int triangular(int a, int b, int c) {
		double x = 0;
		double random = rand();
		double dist = (b - a) / (c - a);
		if (random >= 0 && random <= dist) {
			x = a + (Math.sqrt(random * (b - a) * (c - a)));
		}
		if (random > dist && random <= 1) {
			x = c - (Math.sqrt((1 - random) * (c - b) * (c - a)));
		}

		int valor = (int) x;
		return normalizar(valor);
	}

	private static int normalizar(int valor) {
		return valor > 0 ? valor : 1;
	}

}
