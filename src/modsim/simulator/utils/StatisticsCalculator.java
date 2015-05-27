package modsim.simulator.utils;

import java.util.ArrayList;

import jdk.internal.dynalink.beans.StaticClass;

public class StatisticsCalculator {

	public static int calculateAverageOcupation(ArrayList<Integer> stats) {
		if (stats.size() == 0) {
			return 0;
		}

		int sum = 0;
		int qnt = 0;

		for (int value : stats) {
			qnt++;
			sum += value;
		}

		int average = sum / qnt;

		return average;
	}

	private static int[] calculateMinAveMax(ArrayList<Integer> stats) {
		if (stats.size() == 0) {
			return null;
		}

		int min = Integer.MAX_VALUE;
		int max = 0;
		int sum = 0;
		int qnt = 0;

		for (int value : stats) {
			if (value < min) {
				min = value;
			}
			if (value > max) {
				max = value;
			}
			qnt++;
			sum += value;
		}
		
		int values[] = new int[3];
		values[0] = min;
		values[1] = sum/qnt;
		values[3] = max;
		
		return values;
	}
	
	public static void calculateStatistics(Statistics stats){
		int[] nbOfEntities1 = calculateMinAveMax(stats.getListNbOfEntities1());
		stats.setNbMinEntities1OnSystem(nbOfEntities1[0]);
		stats.setNbAverageEntities1OnSystem(nbOfEntities1[1]);
		stats.setNbMaxEntities1OnSystem(nbOfEntities1[2]);
		
		int[] timeService1 = calculateMinAveMax(stats.getListServer1Ocupation());
		stats.setTimeServiceMin1(timeService1[0]);
		stats.setTimeServiceAverage1(timeService1[1]);
		stats.setTimeServiceMax1(timeService1[2]);
		
		int[] nbOfEntities2 = calculateMinAveMax(stats.getListNbOfEntities2());
		stats.setNbMinEntities2OnSystem(nbOfEntities2[0]);
		stats.setNbAverageEntities2OnSystem(nbOfEntities2[1]);
		stats.setNbMaxEntities2OnSystem(nbOfEntities2[2]);
		
		int[] timeService2 = calculateMinAveMax(stats.getListServer2Ocupation());
		stats.setTimeServiceMin2(timeService2[0]);
		stats.setTimeServiceAverage2(timeService2[1]);
		stats.setTimeServiceMax2(timeService2[2]);
		
		int averageOccupationSvr1 = calculateAverageOcupation(stats.getListServer1Ocupation());
		stats.setOccupationServer1(averageOccupationSvr1);

		int averageOccupationSvr2 = calculateAverageOcupation(stats.getListServer2Ocupation());
		stats.setOccupationServer2(averageOccupationSvr2);
	}
}
