package modsim.simulator.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class Statistics implements Serializable{

	private static final long serialVersionUID = 5585029768484572952L;
	
	//Required
	private int nbEntities1OnLine = 0;
	private int nbEntities1Completed = 0;
	private int nbEntities1OnSistem = 0;
	private int nbEntities2OnLine = 0;
	private int nbEntities2Completed = 0;
	private int nbEntities2OnSistem = 0;
	
	private int occupationServer1 = 0;
	private int nbOfFailuresSvr1 = 0;
	private int timeOnFailureSvr1 = 0;
	private int percentageOnFailureSvr1 = 0;
	
	private int occupationServer2 = 0;
	private int nbOfFailuresSvr2 = 0;
	private int timeOnFailureSvr2 = 0;
	private int percentageOnFailureSvr2 = 0;
	
	private int averageTimeOnLine = 0;
	private int averageTimeOnSystem = 0;
	
	private int nbOfChanges1 = 0;
	private int nbOfChanges2 = 0;
	
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
	
	private ArrayList<Integer> listNbOfEntities = new ArrayList<Integer>();
	private ArrayList<Integer> listServer1Ocupation = new ArrayList<Integer>();
	private ArrayList<Integer> listServer2Ocupation = new ArrayList<Integer>();
	private ArrayList<Integer> listServiceDuration = new ArrayList<Integer>();
	
	public Statistics(){}
	
	@Override
	public String toString() {
		String stats = ">---------------- Estat�sticas de Simula��o ----------------<\n";
		
		stats += "\n";
		stats += "> Contagem de Entidades ao Fim da Simula��o:\n";
		stats += "\t- Tipo 1:\n";
		stats += "\t Em fila: "+nbEntities1OnLine+" entidades\n";
		stats += "\t Finalizadas: "+nbEntities1Completed+" entidades\n";
		stats += "\t No Sistema: "+nbEntities1OnSistem+" entidades\n";
		stats += "\n";
		stats += "\t- Tipo 2:\n";
		stats += "\t Em fila: "+nbEntities2OnLine+" entidades\n";
		stats += "\t Finalizadas: "+nbEntities2Completed+" entidades\n";
		stats += "\t No Sistema: "+nbEntities2OnSistem+" entidades\n";
		stats += "\n";
		
		stats += "> N�mero de Entidades no Sistema:\n";
		stats += "\t- Tipo 1:\n";
		stats += "\t M�nimo: "+nbMinEntities1OnSystem+" entidades\n";
		stats += "\t M�dio: "+nbAverageEntities1OnSystem+" entidades\n";
		stats += "\t M�ximo: "+nbMaxEntities1OnSystem+" entidades\n";
		stats += "\n";
		stats += "\t- Tipo 2:\n";
		stats += "\t M�nimo: "+nbMinEntities2OnSystem+" entidades\n";
		stats += "\t M�dio: "+nbAverageEntities2OnSystem+" entidades\n";
		stats += "\t M�ximo: "+nbMaxEntities2OnSystem+" entidades\n";
		stats += "\n";
		
		stats += "> Taxa de Ocupa��o:\n";
		stats += "\t Servidor 1: "+occupationServer1+" %\n";
		stats += "\t Servidor 2: "+occupationServer2+" %\n";
		stats += "\n";
		
		stats += "> Tempo de Servi�o:\n";
		stats += "\t- Servidor 1:\n";
		stats += "\t M�nimo: "+timeServiceMin1+" segundos\n";
		stats += "\t M�dio: "+timeServiceAverage1+" segundos\n";
		stats += "\t M�ximo: "+timeServiceMax1+" segundos\n";
		stats += "\n";
		stats += "\t- Servidor 2:\n";
		stats += "\t M�nimo: "+timeServiceMin2+" segundos\n";
		stats += "\t M�dio: "+timeServiceAverage2+" segundos\n";
		stats += "\t M�ximo: "+timeServiceMax2+" segundos\n";
		stats += "\n";
		
		stats += "> Tempo em Falhas:\n";
		stats += "\t Servidor 1: "+timeOnFailureSvr1+" segundos\n";
		stats += "\t Servidor 2: "+timeOnFailureSvr2+" segundos\n";
		stats += "\n";
		
		stats += "> N�mero de Falhas:\n";
		stats += "\t Servidor 1: "+nbOfFailuresSvr1+" falhas\n";
		stats += "\t Servidor 2: "+nbOfFailuresSvr2+" falhas\n";
		stats += "\n";
		
		stats += "> Taxa de Falhas:\n";
		stats += "\t Servidor 1: "+percentageOnFailureSvr1+" %\n";
		stats += "\t Servidor 2: "+percentageOnFailureSvr2+" %\n";
		stats += "\n";
		
		stats += "> N�mero de Trocas Entre Servidores:\n";
		stats += "\t Entidade 1: "+nbOfChanges1+" trocas\n";
		stats += "\t Entidade 2: "+nbOfChanges2+" trocas\n";
		stats += "\n";
		
		stats += "> Tempo M�dio em Fila: "+averageTimeOnLine+" segundos\n";
		stats += "> Tempo M�dio no Sistemas: "+averageTimeOnSystem+" segundos\n";
		stats += "\n";
		stats += ">-----------------------------------------------------------<\n";
		
		
		
		return stats;
	}
}
