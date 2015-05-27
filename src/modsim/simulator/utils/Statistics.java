package modsim.simulator.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class Statistics implements Serializable{

	private static final long serialVersionUID = 5585029768484572952L;
	
	//Required
	private int nbEntities1 = 0;
	private int nbEntities1InLine = 0;
	private int nbEntities1Completed = 0;
	private int nbEntities1OnSystem = 0;
	private int nbEntities2 = 0;
	private int nbEntities2InLine = 0;
	private int nbEntities2Completed = 0;
	private int nbEntities2OnSystem = 0;
	
	private int occupationServer1 = 0;
	private int nbOfFailuresSvr1 = 0;
	private int timeOnFailureSvr1 = 0;
	private int percentageOnFailureSvr1 = 0;
	private int occupationServer2 = 0;
	private int nbOfFailuresSvr2 = 0;
	private int timeOnFailureSvr2 = 0;
	private int percentageOnFailureSvr2 = 0;
	
	private int averageTimeInLine = 0;
	private int averageTimeOnSystem = 0;
	
	private int nbOfChanges1 = 0;
	private int nbOfChanges2 = 0;
	
	private ArrayList<Integer> listTimeInLine1 = new ArrayList<Integer>(); // tempo de espera na fila = (tExit[n] - tArrival[n-1])
	private ArrayList<Integer> listTimeInLine2 = new ArrayList<Integer>(); 
	private ArrayList<Integer> listTimeOnSystem = new ArrayList<Integer>(); // tempo no sistema = (tExit - tArrival)
	
	//Extra
	private int nbMinEntities1OnSystem = 0;
	private int nbAverageEntities1OnSystem = 0;
	private int nbMaxEntities1OnSystem = 0;
	private int nbMinEntities2OnSystem = 0;
	private int nbAverageEntities2OnSystem = 0;
	private int nbMaxEntities2OnSystem = 0;
	
	private int timeServiceMin1 = 0;
	private int timeServiceAverage1 = 0;
	private int timeServiceMax1 = 0;
	private int timeServiceMin2 = 0;
	private int timeServiceAverage2 = 0;
	private int timeServiceMax2 = 0;
	
	private ArrayList<Integer> listNbOfEntities1 = new ArrayList<Integer>();
	private ArrayList<Integer> listNbOfEntities2 = new ArrayList<Integer>();
	private ArrayList<Integer> listServer1Ocupation = new ArrayList<Integer>();
	private ArrayList<Integer> listServer2Ocupation = new ArrayList<Integer>();
	private ArrayList<Integer> listService1Duration = new ArrayList<Integer>();
	private ArrayList<Integer> listService2Duration = new ArrayList<Integer>();
	
	public Statistics(){}
	
	@Override
	public String toString() {
		String stats = ">---------------- Estatísticas de Simulação ----------------<\n";

		stats += "\n";
		stats += " Contagem de Entidades ao Fim da Simulação:\n";
		stats += "    - Tipo 1:\n";
		stats += "        Total:  "+nbEntities1+" entidades\n";
		stats += "        Em fila:     "+nbEntities1InLine+" entidades\n";
		stats += "        Finalizadas: "+nbEntities1Completed+" entidades\n";
		stats += "        No Sistema:  "+(nbEntities1 - nbEntities1Completed)+" entidades\n";
		stats += "\n";
		stats += "    - Tipo 2:\n";
		stats += "        Total:  "+nbEntities2+" entidades\n";
		stats += "        Em fila:     "+nbEntities2InLine+" entidades\n";
		stats += "        Finalizadas: "+nbEntities2Completed+" entidades\n";
		stats += "        No Sistema:  "+(nbEntities2 - nbEntities2Completed)+" entidades\n";
		stats += "\n";
		stats += "    Total de Entidades: "+(nbEntities1+nbEntities2)+" entidades\n";
		stats += "\n";
		
		stats += " Número de Entidades no Sistema:\n";
		stats += "    - Tipo 1:\n";
		stats += "        Mínimo: "+nbMinEntities1OnSystem+" entidades\n";
		stats += "        Médio:  "+nbAverageEntities1OnSystem+" entidades\n";
		stats += "        Máximo: "+nbMaxEntities1OnSystem+" entidades\n";
		stats += "\n";
		stats += "    - Tipo 2:\n";
		stats += "        Mínimo: "+nbMinEntities2OnSystem+" entidades\n";
		stats += "        Médio:  "+nbAverageEntities2OnSystem+" entidades\n";
		stats += "        Máximo: "+nbMaxEntities2OnSystem+" entidades\n";
		stats += "\n";
		
		stats += " Taxa de Ocupação:\n";
		stats += "    Servidor 1: "+occupationServer1+" %\n";
		stats += "    Servidor 2: "+occupationServer2+" %\n";
		stats += "\n";
		
		stats += " Tempo de Serviço:\n";
		stats += "    - Servidor 1:\n";
		stats += "        Mínimo: "+timeServiceMin1+" segundos\n";
		stats += "        Médio:  "+timeServiceAverage1+" segundos\n";
		stats += "        Máximo: "+timeServiceMax1+" segundos\n";
		stats += "\n";
		stats += "   - Servidor 2:\n";
		stats += "        Mínimo: "+timeServiceMin2+" segundos\n";
		stats += "        Médio:  "+timeServiceAverage2+" segundos\n";
		stats += "        Máximo: "+timeServiceMax2+" segundos\n";
		stats += "\n";
		
		stats += " Tempo em Falhas:\n";
		stats += "    Servidor 1: "+timeOnFailureSvr1+" segundos\n";
		stats += "    Servidor 2: "+timeOnFailureSvr2+" segundos\n";
		stats += "\n";
		
		stats += " Número de Falhas:\n";
		stats += "    Servidor 1: "+nbOfFailuresSvr1+" falhas\n";
		stats += "    Servidor 2: "+nbOfFailuresSvr2+" falhas\n";
		stats += "\n";
		
		stats += " Taxa de Falhas:\n";
		stats += "    Servidor 1: "+percentageOnFailureSvr1+" %\n";
		stats += "    Servidor 2: "+percentageOnFailureSvr2+" %\n";
		stats += "\n";
		
		stats += " Número de Trocas Entre Servidores:\n";
		stats += "    Entidade 1: "+nbOfChanges1+" trocas\n";
		stats += "    Entidade 2: "+nbOfChanges2+" trocas\n";
		stats += "\n";
		
		stats += " Tempo Médio em Fila: "+averageTimeInLine+" segundos\n";
		stats += " Tempo Médio no Sistemas: "+averageTimeOnSystem+" segundos\n";
		stats += "\n";
		stats += ">-----------------------------------------------------------<\n";
		
		return stats;
	}

	public int getNbEntities1() {
		return nbEntities1;
	}

	public void incrNbEntities1() {
		this.nbEntities1 ++;
	}
	
	public int getNbEntities1InLine() {
		return nbEntities1InLine;
	}

	public void setNbEntities1InLine(int nbEntities1InLine) {
		this.nbEntities1InLine = nbEntities1InLine;
	}

	public void incrNbEntities1InLine() {
		this.nbEntities1InLine++;
	}
	
	public int getNbEntities1Completed() {
		return nbEntities1Completed;
	}
	
	public void setNbEntities1Completed(int nbEntities1Completed) {
		this.nbEntities1Completed = nbEntities1Completed;
	}

	public void incrNbEntities1Completed() {
		this.nbEntities1Completed++;
	}
	
	public int getNbEntities1OnSystem() {
//		return nbEntities1OnSystem;
		return nbEntities1;
	}

	public void setNbEntities1OnSystem(int nbEntities1OnSystem) {
		this.nbEntities1OnSystem = nbEntities1OnSystem;
	}

	public void incrNbEntities1OnSystem() {
		this.nbEntities1OnSystem++;
	}
	
	public int getNbEntities2() {
		return nbEntities2;
	}

	public void incrNbEntities2() {
		this.nbEntities2 ++;
	}
	
	public int getNbEntities2InLine() {
		return nbEntities2InLine;
	}

	public void setNbEntities2InLine(int nbEntities2InLine) {
		this.nbEntities2InLine = nbEntities2InLine;
	}

	public void incrNbEntities2InLine() {
		this.nbEntities2InLine++;
	}
	
	public int getNbEntities2Completed() {
		return nbEntities2Completed;
	}

	public void setNbEntities2Completed(int nbEntities2Completed) {
		this.nbEntities2Completed = nbEntities2Completed;
	}

	public void incrNbEntities2Completed() {
		this.nbEntities2Completed++;
	}
	
	public int getNbEntities2OnSystem() {
//		return nbEntities2OnSystem;
		return nbEntities2;
	}

	public void setNbEntities2OnSystem(int nbEntities2OnSystem) {
		this.nbEntities2OnSystem = nbEntities2OnSystem;
	}

	public void incrNbEntities2OnSystem() {
		this.nbEntities2OnSystem++;
	}
	
	public int getOccupationServer1() {
		return occupationServer1;
	}

	public void setOccupationServer1(int occupationServer1) {
		this.occupationServer1 = occupationServer1;
	}

	public int getNbOfFailuresSvr1() {
		return nbOfFailuresSvr1;
	}

	public void setNbOfFailuresSvr1(int nbOfFailuresSvr1) {
		this.nbOfFailuresSvr1 = nbOfFailuresSvr1;
	}

	public void incrNbOfFailuresSvr1() {
		this.nbOfFailuresSvr1++;
	}
	
	public int getTimeOnFailureSvr1() {
		return timeOnFailureSvr1;
	}

	public void setTimeOnFailureSvr1(int timeOnFailureSvr1) {
		this.timeOnFailureSvr1 = timeOnFailureSvr1;
	}

	public int getPercentageOnFailureSvr1() {
		return percentageOnFailureSvr1;
	}

	public void setPercentageOnFailureSvr1(int percentageOnFailureSvr1) {
		this.percentageOnFailureSvr1 = percentageOnFailureSvr1;
	}

	public int getOccupationServer2() {
		return occupationServer2;
	}

	public void setOccupationServer2(int occupationServer2) {
		this.occupationServer2 = occupationServer2;
	}

	public int getNbOfFailuresSvr2() {
		return nbOfFailuresSvr2;
	}

	public void setNbOfFailuresSvr2(int nbOfFailuresSvr2) {
		this.nbOfFailuresSvr2 = nbOfFailuresSvr2;
	}

	public void incrNbOfFailuresSvr2() {
		this.nbOfFailuresSvr2++;
	}
	
	public int getTimeOnFailureSvr2() {
		return timeOnFailureSvr2;
	}

	public void setTimeOnFailureSvr2(int timeOnFailureSvr2) {
		this.timeOnFailureSvr2 = timeOnFailureSvr2;
	}

	public int getPercentageOnFailureSvr2() {
		return percentageOnFailureSvr2;
	}

	public void setPercentageOnFailureSvr2(int percentageOnFailureSvr2) {
		this.percentageOnFailureSvr2 = percentageOnFailureSvr2;
	}

	public int getAverageTimeInLine() {
		return averageTimeInLine;
	}

	public void setAverageTimeInLine(int averageTimeInLine) {
		this.averageTimeInLine = averageTimeInLine;
	}

	public int getAverageTimeOnSystem() {
		return averageTimeOnSystem;
	}

	public void setAverageTimeOnSystem(int averageTimeOnSystem) {
		this.averageTimeOnSystem = averageTimeOnSystem;
	}

	public int getNbOfChanges1() {
		return nbOfChanges1;
	}

	public void setNbOfChanges1(int nbOfChanges1) {
		this.nbOfChanges1 = nbOfChanges1;
	}

	public void incrNbOfChanges1() {
		this.nbOfChanges1++;
	}
	
	public int getNbOfChanges2() {
		return nbOfChanges2;
	}

	public void setNbOfChanges2(int nbOfChanges2) {
		this.nbOfChanges2 = nbOfChanges2;
	}

	public void incrNbOfChanges2() {
		this.nbOfChanges2++;
	}
	
	public int getNbMinEntities1OnSystem() {
		return nbMinEntities1OnSystem;
	}

	public void setNbMinEntities1OnSystem(int nbMinEntities1OnSystem) {
		this.nbMinEntities1OnSystem = nbMinEntities1OnSystem;
	}

	public int getNbAverageEntities1OnSystem() {
		return nbAverageEntities1OnSystem;
	}

	public void setNbAverageEntities1OnSystem(int nbAverageEntities1OnSystem) {
		this.nbAverageEntities1OnSystem = nbAverageEntities1OnSystem;
	}

	public int getNbMaxEntities1OnSystem() {
		return nbMaxEntities1OnSystem;
	}

	public void setNbMaxEntities1OnSystem(int nbMaxEntities1OnSystem) {
		this.nbMaxEntities1OnSystem = nbMaxEntities1OnSystem;
	}

	public int getNbMinEntities2OnSystem() {
		return nbMinEntities2OnSystem;
	}

	public void setNbMinEntities2OnSystem(int nbMinEntities2OnSystem) {
		this.nbMinEntities2OnSystem = nbMinEntities2OnSystem;
	}

	public int getNbAverageEntities2OnSystem() {
		return nbAverageEntities2OnSystem;
	}

	public void setNbAverageEntities2OnSystem(int nbAverageEntities2OnSystem) {
		this.nbAverageEntities2OnSystem = nbAverageEntities2OnSystem;
	}

	public int getNbMaxEntities2OnSystem() {
		return nbMaxEntities2OnSystem;
	}

	public void setNbMaxEntities2OnSystem(int nbMaxEntities2OnSystem) {
		this.nbMaxEntities2OnSystem = nbMaxEntities2OnSystem;
	}

	public int getTimeServiceMin1() {
		return timeServiceMin1;
	}

	public void setTimeServiceMin1(int timeServiceMin1) {
		this.timeServiceMin1 = timeServiceMin1;
	}

	public int getTimeServiceAverage1() {
		return timeServiceAverage1;
	}

	public void setTimeServiceAverage1(int timeServiceAverage1) {
		this.timeServiceAverage1 = timeServiceAverage1;
	}

	public int getTimeServiceMax1() {
		return timeServiceMax1;
	}

	public void setTimeServiceMax1(int timeServiceMax1) {
		this.timeServiceMax1 = timeServiceMax1;
	}

	public int getTimeServiceMin2() {
		return timeServiceMin2;
	}

	public void setTimeServiceMin2(int timeServiceMin2) {
		this.timeServiceMin2 = timeServiceMin2;
	}

	public int getTimeServiceAverage2() {
		return timeServiceAverage2;
	}

	public void setTimeServiceAverage2(int timeServiceAverage2) {
		this.timeServiceAverage2 = timeServiceAverage2;
	}

	public int getTimeServiceMax2() {
		return timeServiceMax2;
	}

	public void setTimeServiceMax2(int timeServiceMax2) {
		this.timeServiceMax2 = timeServiceMax2;
	}

	public ArrayList<Integer> getListNbOfEntities1() {
		return listNbOfEntities1;
	}

	public void addListNbOfEntities1(Integer number){
		this.listNbOfEntities1.add(number);
	}
	
	public ArrayList<Integer> getListNbOfEntities2() {
		return listNbOfEntities2;
	}

	public void addListNbOfEntities2(Integer number){
		this.listNbOfEntities2.add(number);
	}
	
	public ArrayList<Integer> getListServer1Ocupation() {
		return listServer1Ocupation;
	}

	public void addListServer1Ocupation(Integer number){
		this.listServer1Ocupation.add(number);
	}
	
	public ArrayList<Integer> getListServer2Ocupation() {
		return listServer2Ocupation;
	}

	public void addListServer2Ocupation(Integer number){
		this.listServer2Ocupation.add(number);
	}
	
	public ArrayList<Integer> getListService1Duration() {
		return listService1Duration;
	}
	
	public void addListService1Duration(Integer number){
		this.listService1Duration.add(number);
	}
	

	public ArrayList<Integer> getListService2Duration() {
		return listService2Duration;
	}
	
	public void addListService2Duration(Integer number){
		this.listService2Duration.add(number);
	}
}
