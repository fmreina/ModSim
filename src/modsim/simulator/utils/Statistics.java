package modsim.simulator.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Statistics implements Serializable {

	private static final long serialVersionUID = 5585029768484572952L;

	private String simulationName;
	private int simulationId;
	private int tempoSim;

	// Required
	private double nbEntities1 = 0;
	private double nbEntities1InLine = 0;
	private double nbEntities1Completed = 0;
	private double nbEntities1OnSystem = 0;
	private double nbEntities2 = 0;
	private double nbEntities2InLine = 0;
	private double nbEntities2Completed = 0;
	private double nbEntities2OnSystem = 0;
	private double lastTimeLineChanged = 0;

	private HashMap<Integer, Double> mapAvgNbEntities1InLine = new HashMap<Integer, Double>();
	private HashMap<Integer, Double> mapAvgNbEntities2InLine = new HashMap<Integer, Double>();

	private double occupationServer1 = 0;
	private double nbOfFailuresSvr1 = 0;
	private double timeOnFailureSvr1 = 0;
	private double percentageOnFailureSvr1 = 0;
	private double occupationServer2 = 0;
	private double nbOfFailuresSvr2 = 0;
	private double timeOnFailureSvr2 = 0;
	private double percentageOnFailureSvr2 = 0;

	private double averageTimeInLineSrv1 = 0;
	private double averageTimeInLineSrv2 = 0;
	private double averageTimeOnSystem = 0;

	private double nbOfChanges1 = 0;
	private double nbOfChanges2 = 0;

	private ArrayList<Integer> listTimeInLine1 = new ArrayList<Integer>(); // tempo de espera na fila = (tExit[n] - tArrival[n-1])
	private ArrayList<Integer> listTimeInLine2 = new ArrayList<Integer>();
	private ArrayList<Integer> listTimeOnSystem = new ArrayList<Integer>(); // tempo no sistema = (tExit - tArrival)

	// Extra
	private double nbMinEntities1OnSystem = 0;
	private double nbAverageEntities1OnSystem = 0;
	private double nbMaxEntities1OnSystem = 0;
	private double nbMinEntities2OnSystem = 0;
	private double nbAverageEntities2OnSystem = 0;
	private double nbMaxEntities2OnSystem = 0;

	private double timeServiceMin1 = 0;
	private double timeServiceAverage1 = 0;
	private double timeServiceMax1 = 0;
	private double timeServiceMin2 = 0;
	private double timeServiceAverage2 = 0;
	private double timeServiceMax2 = 0;

	private ArrayList<Integer> listNbOfEntities1 = new ArrayList<Integer>();
	private ArrayList<Integer> listNbOfEntities2 = new ArrayList<Integer>();
	private ArrayList<Integer> listServer1Ocupation = new ArrayList<Integer>();
	private ArrayList<Integer> listServer2Ocupation = new ArrayList<Integer>();
	private ArrayList<Integer> listService1Duration = new ArrayList<Integer>();
	private ArrayList<Integer> listService2Duration = new ArrayList<Integer>();

	private double avgTimeInLine1;

	private double avgTimeInLine2;

	public Statistics(int tempoSim) {
		this.tempoSim = tempoSim;
	}

	@Override
	public String toString() {
		String stats = ">---------------------- Estatísticas de Simulação ----------------------<\n";

		stats += "\n";
		stats += " Simulação: "+simulationName+" \tID: "+simulationId+"\n";
		stats += "\n";
		stats += " Contagem de Entidades ao Fim da Simulação:\n";
		stats += "    - Tipo 1:\n";
		stats += "        Em fila:    " + (nbEntities1 - nbEntities1Completed)/*nbEntities1InLine*/ + " entidades\n";
		stats += "        Finalizadas:    " + nbEntities1Completed	+ " entidades\n";
		stats += "        Total:    " + nbEntities1 + " entidades\n";
//		stats += "        No Sistema:    "	+ (nbEntities1 - nbEntities1Completed) + " entidades\n";
		stats += "\n";
		stats += "    - Tipo 2:\n";
		stats += "        Em fila:    " + (nbEntities2 - nbEntities2Completed)/*nbEntities2InLine*/ + " entidades\n";
		stats += "        Finalizadas:    " + nbEntities2Completed	+ " entidades\n";
		stats += "        Total:    " + nbEntities2 + " entidades\n";
//		stats += "        No Sistema:    "	+ (nbEntities2 - nbEntities2Completed) + " entidades\n";
		stats += "\n";
		stats += "    Total de Entidades:    " + (nbEntities1 + nbEntities2)	+ " entidades\n";
		stats += "\n";

		stats += " Número de Entidades no Sistema:\n";
		stats += "    - Tipo 1:\n";
		stats += "        Mínimo:    " + nbMinEntities1OnSystem	+ " entidades\n";
		stats += "        Médio:    " + nbAverageEntities1OnSystem	+ " entidades\n";
		stats += "        Máximo:    " + nbMaxEntities1OnSystem	+ " entidades\n";
		stats += "\n";
		stats += "    - Tipo 2:\n";
		stats += "        Mínimo:    " + nbMinEntities2OnSystem	+ " entidades\n";
		stats += "        Médio:    " + nbAverageEntities2OnSystem	+ " entidades\n";
		stats += "        Máximo:    " + nbMaxEntities2OnSystem	+ " entidades\n";
		stats += "\n";

		stats += " Taxa de Ocupação:\n";
		stats += "    Servidor 1:    " + (occupationServer1 / tempoSim * 100) + " %\n";
		stats += "    Servidor 2:    " + (occupationServer2 / tempoSim * 100) + " %\n";
		stats += "\n";

		stats += " Tempo de Serviço:\n";
		stats += "    - Servidor 1:\n";
		stats += "        Mínimo:    " + timeServiceMin1 + " segundos = "+ (timeServiceMin1 / 60) + " minutos\n";
		stats += "        Médio:    " + timeServiceAverage1 + " segundos = "+ (timeServiceAverage1 / 60) + " minutos\n";
		stats += "        Máximo:    " + timeServiceMax1 + " segundos = "+ (timeServiceMax1 / 60) + " minutos\n";
		stats += "\n";
		stats += "   - Servidor 2:\n";
		stats += "        Mínimo:    " + timeServiceMin2 + " segundos = "+(timeServiceMin2 / 60) + " minutos\n";
		stats += "        Médio:    " + timeServiceAverage2 + " segundos = "+ (timeServiceAverage2 / 60) + " minutos\n";
		stats += "        Máximo:    " + timeServiceMax2 + " segundos = "+ (timeServiceMax2 / 60) + " minutos\n";
		stats += "\n";

		stats += " Tempo em Falhas:\n";
		stats += "    Servidor 1:    " + timeOnFailureSvr1 + " segundos = "	+ (timeOnFailureSvr1 / 60) + " minutos\n";
		stats += "    Servidor 2:    " + timeOnFailureSvr2 + " segundos = " + (timeOnFailureSvr2 / 60) + " minutos\n";
		stats += "\n";

		stats += " Número de Falhas:\n";
		stats += "    Servidor 1:    " + nbOfFailuresSvr1 + " falhas\n";
		stats += "    Servidor 2:    " + nbOfFailuresSvr2 + " falhas\n";
		stats += "\n";

		stats += " Taxa de Falhas:\n";
		stats += "    Servidor 1:    " + ((timeOnFailureSvr1/tempoSim) * 100)+ " %\n";
		stats += "    Servidor 2:    " + ((timeOnFailureSvr2/tempoSim) * 100)+ " %\n";
		stats += "\n";

		stats += " Número de Trocas Entre Servidores:\n";
		stats += "    Entidade 1:    " + nbOfChanges1 + " trocas\n";
		stats += "    Entidade 2:    " + nbOfChanges2 + " trocas\n";
		stats += "\n";

		stats += " Tempo Médio em Fila:\n";
		stats += "    Servidor 1:    " + averageTimeInLineSrv1+ " segundos = " + (averageTimeInLineSrv1 / 60) + " minutos\n";
		stats += "    Servidor 2:    " + averageTimeInLineSrv2+ " segundos = " + (averageTimeInLineSrv2 / 60) + " minutos\n";
		stats += "    Total:         " + (averageTimeInLineSrv1+averageTimeInLineSrv2)+ " segundos = " + ((averageTimeInLineSrv1+averageTimeInLineSrv2) / 60) + " minutos\n";
		stats += "\n";
		stats += " Número Médio em Fila:\n";
		stats += "    Servidor 1:    " + avgTimeInLine1+ " entidades = \n";
		stats += "    Servidor 2:    " + avgTimeInLine2+ " entidades = ";
		stats += "\n";
		stats += " Tempo Médio no Sistema:    " + averageTimeOnSystem+ " segundos = " + (averageTimeOnSystem / 60) + " minutos\n";
		stats += "\n";
		stats += ">--------------------------------------------------------------------------------<\n";

		return stats;
	}

	public HashMap<Integer, Double> getAvgNbEntities1InLine() {
		return mapAvgNbEntities1InLine;
	}

	public void updateAvgNbEntities1InLine(Integer nb, Integer time) {
		updateAvgNbEntitiesInLine(nb, time, mapAvgNbEntities1InLine);
	}
	
	public void updateAvgNbEntities2InLine(Integer nb, Integer time) {
		updateAvgNbEntitiesInLine(nb, time, mapAvgNbEntities2InLine);
	}

	private void updateAvgNbEntitiesInLine(Integer nb, Integer time, HashMap<Integer, Double> avgNbEntitiesInLine) {
		Double timeFila = time - lastTimeLineChanged;
		if(avgNbEntitiesInLine.containsKey(nb)){
			Double atual = avgNbEntitiesInLine.get(nb);
			atual = atual + timeFila;
			avgNbEntitiesInLine.replace(nb, atual);
			return;
		}
		avgNbEntitiesInLine.put(nb, timeFila);
		lastTimeLineChanged = time;
	}

	public HashMap<Integer, Double> getAvgNbEntities2InLine() {
		return mapAvgNbEntities2InLine;
	}

	public void setSimulationName(String simulationName) {
		this.simulationName = simulationName;
	}

	public void setSimulationId(int simulationId) {
		this.simulationId = simulationId;
	}

	public double getNbEntities1() {
		return nbEntities1;
	}

	public void incrNbEntities1() {
		this.nbEntities1++;
	}

	public double getNbEntities1InLine() {
		return nbEntities1InLine;
	}

	public void setNbEntities1InLine(double nbEntities1InLine) {
		this.nbEntities1InLine = nbEntities1InLine;
	}

	public void incrNbEntities1InLine() {
		this.nbEntities1InLine++;
	}

	public double getNbEntities1Completed() {
		return nbEntities1Completed;
	}

	public void setNbEntities1Completed(double nbEntities1Completed) {
		this.nbEntities1Completed = nbEntities1Completed;
	}

	public void incrNbEntities1Completed() {
		this.nbEntities1Completed++;
	}

	public double getNbEntities1OnSystem() {
		// return nbEntities1OnSystem;
		return nbEntities1;
	}

	public void setNbEntities1OnSystem(double nbEntities1OnSystem) {
		this.nbEntities1OnSystem = nbEntities1OnSystem;
	}

	public void incrNbEntities1OnSystem() {
		this.nbEntities1OnSystem++;
	}

	public double getNbEntities2() {
		return nbEntities2;
	}

	public void incrNbEntities2() {
		this.nbEntities2++;
	}

	public double getNbEntities2InLine() {
		return nbEntities2InLine;
	}

	public void setNbEntities2InLine(double nbEntities2InLine) {
		this.nbEntities2InLine = nbEntities2InLine;
	}

	public void incrNbEntities2InLine() {
		this.nbEntities2InLine++;
	}

	public double getNbEntities2Completed() {
		return nbEntities2Completed;
	}

	public void setNbEntities2Completed(double nbEntities2Completed) {
		this.nbEntities2Completed = nbEntities2Completed;
	}

	public void incrNbEntities2Completed() {
		this.nbEntities2Completed++;
	}

	public double getNbEntities2OnSystem() {
		// return nbEntities2OnSystem;
		return nbEntities2;
	}

	public void setNbEntities2OnSystem(double nbEntities2OnSystem) {
		this.nbEntities2OnSystem = nbEntities2OnSystem;
	}

	public void incrNbEntities2OnSystem() {
		this.nbEntities2OnSystem++;
	}

	public double getOccupationServer1() {
		return occupationServer1;
	}

	public void setOccupationServer1(double occupationServer1) {
		this.occupationServer1 = occupationServer1;
	}

	public double getNbOfFailuresSvr1() {
		return nbOfFailuresSvr1;
	}

	public void setNbOfFailuresSvr1(double nbOfFailuresSvr1) {
		this.nbOfFailuresSvr1 = nbOfFailuresSvr1;
	}

	public void incrNbOfFailuresSvr1() {
		this.nbOfFailuresSvr1++;
	}

	public double getTimeOnFailureSvr1() {
		return timeOnFailureSvr1;
	}

	public void setTimeOnFailureSvr1(double timeOnFailureSvr1) {
		this.timeOnFailureSvr1 += timeOnFailureSvr1;
	}

	public double getPercentageOnFailureSvr1() {
		return percentageOnFailureSvr1;
	}

	public void setPercentageOnFailureSvr1(double percentageOnFailureSvr1) {
		this.percentageOnFailureSvr1 = percentageOnFailureSvr1;
	}

	public double getOccupationServer2() {
		return occupationServer2;
	}

	public void setOccupationServer2(double occupationServer2) {
		this.occupationServer2 = occupationServer2;
	}

	public double getNbOfFailuresSvr2() {
		return nbOfFailuresSvr2;
	}

	public void setNbOfFailuresSvr2(double nbOfFailuresSvr2) {
		this.nbOfFailuresSvr2 = nbOfFailuresSvr2;
	}

	public void incrNbOfFailuresSvr2() {
		this.nbOfFailuresSvr2++;
	}

	public double getTimeOnFailureSvr2() {
		return timeOnFailureSvr2;
	}

	public void setTimeOnFailureSvr2(double timeOnFailureSvr2) {
		this.timeOnFailureSvr2 += timeOnFailureSvr2;
	}

	public double getPercentageOnFailureSvr2() {
		return percentageOnFailureSvr2;
	}

	public void setPercentageOnFailureSvr2(double percentageOnFailureSvr2) {
		this.percentageOnFailureSvr2 = percentageOnFailureSvr2;
	}

	public double getAverageTimeInLine() {
		return averageTimeInLineSrv1;
	}

	public void setAverageTimeInLineSrv1(double averageTimeInLine) {
		this.averageTimeInLineSrv1 = averageTimeInLine;
	}
	
	public void setAverageTimeInLineSrv2(double averageTimeInLine) {
		this.averageTimeInLineSrv2 = averageTimeInLine;
	}

	public double getAverageTimeOnSystem() {
		return averageTimeOnSystem;
	}

	public void setAverageTimeOnSystem(double averageTimeOnSystem) {
		this.averageTimeOnSystem = averageTimeOnSystem;
	}

	public double getNbOfChanges1() {
		return nbOfChanges1;
	}

	public void setNbOfChanges1(double nbOfChanges1) {
		this.nbOfChanges1 = nbOfChanges1;
	}

	public void incrNbOfChanges1() {
		this.nbOfChanges1++;
	}

	public double getNbOfChanges2() {
		return nbOfChanges2;
	}

	public void setNbOfChanges2(double nbOfChanges2) {
		this.nbOfChanges2 = nbOfChanges2;
	}

	public void incrNbOfChanges2() {
		this.nbOfChanges2++;
	}

	public double getNbMinEntities1OnSystem() {
		return nbMinEntities1OnSystem;
	}

	public void setNbMinEntities1OnSystem(double nbMinEntities1OnSystem) {
		this.nbMinEntities1OnSystem = nbMinEntities1OnSystem;
	}

	public double getNbAverageEntities1OnSystem() {
		return nbAverageEntities1OnSystem;
	}

	public void setNbAverageEntities1OnSystem(double nbAverageEntities1OnSystem) {
		this.nbAverageEntities1OnSystem = nbAverageEntities1OnSystem;
	}

	public double getNbMaxEntities1OnSystem() {
		return nbMaxEntities1OnSystem;
	}

	public void setNbMaxEntities1OnSystem(double nbMaxEntities1OnSystem) {
		this.nbMaxEntities1OnSystem = nbMaxEntities1OnSystem;
	}

	public double getNbMinEntities2OnSystem() {
		return nbMinEntities2OnSystem;
	}

	public void setNbMinEntities2OnSystem(double nbMinEntities2OnSystem) {
		this.nbMinEntities2OnSystem = nbMinEntities2OnSystem;
	}

	public double getNbAverageEntities2OnSystem() {
		return nbAverageEntities2OnSystem;
	}

	public void setNbAverageEntities2OnSystem(double nbAverageEntities2OnSystem) {
		this.nbAverageEntities2OnSystem = nbAverageEntities2OnSystem;
	}

	public double getNbMaxEntities2OnSystem() {
		return nbMaxEntities2OnSystem;
	}

	public void setNbMaxEntities2OnSystem(double nbMaxEntities2OnSystem) {
		this.nbMaxEntities2OnSystem = nbMaxEntities2OnSystem;
	}

	public double getTimeServiceMin1() {
		return timeServiceMin1;
	}

	public void setTimeServiceMin1(double timeServiceMin1) {
		this.timeServiceMin1 = timeServiceMin1;
	}

	public double getTimeServiceAverage1() {
		return timeServiceAverage1;
	}

	public void setTimeServiceAverage1(double timeServiceAverage1) {
		this.timeServiceAverage1 = timeServiceAverage1;
	}

	public double getTimeServiceMax1() {
		return timeServiceMax1;
	}

	public void setTimeServiceMax1(double timeServiceMax1) {
		this.timeServiceMax1 = timeServiceMax1;
	}

	public double getTimeServiceMin2() {
		return timeServiceMin2;
	}

	public void setTimeServiceMin2(double timeServiceMin2) {
		this.timeServiceMin2 = timeServiceMin2;
	}

	public double getTimeServiceAverage2() {
		return timeServiceAverage2;
	}

	public void setTimeServiceAverage2(double timeServiceAverage2) {
		this.timeServiceAverage2 = timeServiceAverage2;
	}

	public double getTimeServiceMax2() {
		return timeServiceMax2;
	}

	public void setTimeServiceMax2(double timeServiceMax2) {
		this.timeServiceMax2 = timeServiceMax2;
	}

	public ArrayList<Integer> getListNbOfEntities1() {
		return listNbOfEntities1;
	}

	public void addListNbOfEntities1(Integer number) {
		this.listNbOfEntities1.add(number);
	}

	public ArrayList<Integer> getListNbOfEntities2() {
		return listNbOfEntities2;
	}

	public void addListNbOfEntities2(Integer number) {
		this.listNbOfEntities2.add(number);
	}

	public ArrayList<Integer> getListServer1Ocupation() {
		return listServer1Ocupation;
	}

	public void addListServer1Ocupation(Integer number) {
		this.listServer1Ocupation.add(number);
	}

	public ArrayList<Integer> getListServer2Ocupation() {
		return listServer2Ocupation;
	}

	public void addListServer2Ocupation(Integer number) {
		this.listServer2Ocupation.add(number);
	}

	public ArrayList<Integer> getListService1Duration() {
		return listService1Duration;
	}

	public void addListService1Duration(Integer number) {
		this.listService1Duration.add(number);
	}

	public ArrayList<Integer> getListService2Duration() {
		return listService2Duration;
	}

	public void addListService2Duration(Integer number) {
		this.listService2Duration.add(number);
	}

	public ArrayList<Integer> getListTimeInLine1() {
		return listTimeInLine1;
	}

	public ArrayList<Integer> getListTimeInLine2() {
		return listTimeInLine2;
	}

	public ArrayList<Integer> getListTimeOnSystem() {
		return listTimeOnSystem;
	}
	
	public Double getLastTimeLineChanged() {
		return lastTimeLineChanged;
	}

	public void setLastTimeLineChanged(int lastTimeLineChanged) {
		this.lastTimeLineChanged = lastTimeLineChanged;
	}

	public int getTempoSim() {
		return tempoSim;
	}

	public void setAvgTimeInLine1(double avgTimeInLine1) {
		this.avgTimeInLine1 = avgTimeInLine1;
	}
	
	public void setAvgTimeInLine2(double avgTimeInLine2) {
		this.avgTimeInLine2 = avgTimeInLine2;
	}
}
