package modsim.simulator.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class StatisticsCalculator {

	public static double calculateWeightedAverage(
			HashMap<Integer, Double> stats, Double weight) {
		if (stats.size() == 0) {
			return 0;
		}

		double sum = 0;

		for (Entry<Integer, Double> entry : stats.entrySet()) {
			sum += ((double) entry.getKey())
					* (entry.getValue() / weight);
		}
		return sum / 100.0;

	}

	public static double calculateAverageOcupation(ArrayList<Integer> stats) {
		if (stats.size() == 0) {
			return 0;
		}

		int sum = 0;
		int qnt = 0;

		for (int value : stats) {
			qnt++;
			sum += value;
		}

		double average = sum / qnt;

		return average;
	}
	
	public static double calculateSum(ArrayList<Integer> stats) {
		if (stats.size() == 0) {
			return 0;
		}

		int sum = 0;

		for (int value : stats) {
			sum += value;
		}

		return sum;
	}

	private static double[] calculateMinAveMax(ArrayList<Integer> stats) {
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

		double values[] = new double[3];
		values[0] = min;
		values[1] = sum / qnt;
		values[2] = max;

		return values;
	}

	public static void calculateStatistics(Statistics stats) {
		double[] nbOfEntities1 = calculateMinAveMax(stats
				.getListNbOfEntities1());
		if (nbOfEntities1 != null) {
			stats.setNbMinEntities1OnSystem(nbOfEntities1[0]);
			stats.setNbAverageEntities1OnSystem(nbOfEntities1[1]);
			stats.setNbMaxEntities1OnSystem(nbOfEntities1[2]);
		}

		double[] nbOfEntities2 = calculateMinAveMax(stats
				.getListNbOfEntities2());
		if (nbOfEntities2 != null) {
			stats.setNbMinEntities2OnSystem(nbOfEntities2[0]);
			stats.setNbAverageEntities2OnSystem(nbOfEntities2[1]);
			stats.setNbMaxEntities2OnSystem(nbOfEntities2[2]);
		}

		double[] timeService1 = calculateMinAveMax(stats
				.getListServer1Ocupation());
		if (timeService1 != null) {
			stats.setTimeServiceMin1(timeService1[0]);
			stats.setTimeServiceAverage1(timeService1[1]);
			stats.setTimeServiceMax1(timeService1[2]);
		}

		double[] timeService2 = calculateMinAveMax(stats
				.getListServer2Ocupation());
		if (timeService2 != null) {
			stats.setTimeServiceMin2(timeService2[0]);
			stats.setTimeServiceAverage2(timeService2[1]);
			stats.setTimeServiceMax2(timeService2[2]);
		}

		double averageOccupationSvr1 = calculateSum(stats
				.getListServer1Ocupation());
		stats.setOccupationServer1(averageOccupationSvr1);

		double averageOccupationSvr2 = calculateSum(stats
				.getListServer2Ocupation());
		stats.setOccupationServer2(averageOccupationSvr2);

		double averageTimeInLineSrv1 = calculateAverageOcupation(stats
				.getListTimeInLine1());
		stats.setAverageTimeInLineSrv1(averageTimeInLineSrv1);

		double averageTimeInLineSrv2 = calculateAverageOcupation(stats
				.getListTimeInLine2());
		stats.setAverageTimeInLineSrv2(averageTimeInLineSrv2);

		double averageTimeOnSystem = (calculateAverageOcupation(stats
				.getListTimeOnSystem()));
		stats.setAverageTimeOnSystem(averageTimeOnSystem);

		double avgTimeInLine1 = calculateWeightedAverage(
				stats.getAvgNbEntities1InLine(), (double) stats.getTempoSim());
		stats.setAvgTimeInLine1(avgTimeInLine1);

		double avgTimeInLine2 = calculateWeightedAverage(
				stats.getAvgNbEntities2InLine(), (double) stats.getTempoSim());
		stats.setAvgTimeInLine2(avgTimeInLine2);
	}
}
