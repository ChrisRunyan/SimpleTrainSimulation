package edu.ilstu;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The class Cycle handles all data manipulation related to one "cycle" of virtual trains in a virtual city.
 * 
 * @author Christopher Runyan
 */
public class Cycle {
	//instantiation of trains and their starting stations
	private String t1 = "A3";
	private String t2 = "A7";
	private String t3 = "B2";
	private String t4 = "B7";
	private String t5 = "B8";
	private String t6 = "B2";
	private String t7 = "B4";
	private String t8 = "B6";
	
	private Boolean incrementTrainValues[] = {true, true, true, true, false};

	private Boolean errorLoopTrain = false;
	private Boolean errorLinearTrain = false;
	
	/*
	 * Prompts a train to go to the next station. 20% chance of a delay and the train staying at its current station.
	 */
	private Boolean trainChangesStation() {
		Boolean toReturn = false;
		
		int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
		toReturn = incrementTrainValues[randomNum];
		
		return toReturn;
	}
	
	/*
	 * Changes the station number for trains within the circular loop of the city. Checks to ensure that intended station is not occupied before changing stations.
	 */
	private String incrementLoopTrains(String currentStation, String trainNumber) {
		String toReturn = "";
		char loopLetter = currentStation.charAt(0);
		int stationNumber = Integer.parseInt(Character.toString(currentStation.charAt(1)));
		
		stationNumber++;
		
		if(stationNumber > 8) {
			stationNumber = 1;
		}
		
		toReturn = loopLetter + Integer.toString(stationNumber);
		
		//check to see if other trains are at intended station
		if(!checkRule(toReturn, trainNumber)) {
			errorLoopTrain = true;
			stationNumber--;
			if(stationNumber < 0) {
				stationNumber = 8;
			}
			toReturn = loopLetter + Integer.toString(stationNumber);
		}
		
		
		return toReturn;
	}
	
	/*
	 * Changes the station number for trains going between the circular loops of the city. Checks to ensure that intended station is not occupied before changing 
	 * stations.
	 */
	private String incrementLinearTrains(String currentStation, String trainNumber) {
		String toReturn = "";
		char loopLetter = currentStation.charAt(0);
		int stationNumber = Integer.parseInt(Character.toString(currentStation.charAt(1)));
		
		if(loopLetter == 'A') {
			loopLetter = 'B';
		}
		else if(loopLetter == 'B') {
			loopLetter = 'A';
		}
		
		toReturn = loopLetter + Integer.toString(stationNumber);
		
		//check to see if other trains are at intended station
		if(!checkRule(toReturn, trainNumber)) {
			errorLinearTrain = true;
			if(loopLetter == 'A') {
				loopLetter = 'B';
			}
			else if(loopLetter == 'B') {
				loopLetter = 'A';
			}
			toReturn = loopLetter + Integer.toString(stationNumber);
		}
		
		return toReturn;
	}
	
	/*
	 * Checks to make sure that the train changing station is not going to a station currently occupied by a different train. 
	 */
	private Boolean checkRule(String intendedStation, String currentTrain) {
		Boolean toReturn = true;
		
		if(currentTrain.equalsIgnoreCase("t2")) {
			if(intendedStation.equalsIgnoreCase(t1)) {
				toReturn = false;
			}
		}
		else if(currentTrain.equalsIgnoreCase("t3")) {
			if(intendedStation.equalsIgnoreCase(t1) || intendedStation.equalsIgnoreCase("t2")) {
				toReturn = false;
			}
		}
		else if(currentTrain.equalsIgnoreCase("t4")) {
			if(intendedStation.equalsIgnoreCase(t1) || intendedStation.equalsIgnoreCase("t2")
					|| intendedStation.equalsIgnoreCase("t3")) {
				toReturn = false;
			}
		}
		else if(currentTrain.equalsIgnoreCase("t5")) {
			if(intendedStation.equalsIgnoreCase(t1) || intendedStation.equalsIgnoreCase("t2")
					|| intendedStation.equalsIgnoreCase("t3") || intendedStation.equalsIgnoreCase("t4")) {
				toReturn = false;
			}
		}
		else if(currentTrain.equalsIgnoreCase("t6")) {
			if(intendedStation.equalsIgnoreCase(t1) || intendedStation.equalsIgnoreCase("t2")
					|| intendedStation.equalsIgnoreCase("t3") || intendedStation.equalsIgnoreCase("t4")
					|| intendedStation.equalsIgnoreCase("t5")) {
				toReturn = false;
			}
		}
		else if(currentTrain.equalsIgnoreCase("t7")) {
			if(intendedStation.equalsIgnoreCase(t1) || intendedStation.equalsIgnoreCase("t2")
					|| intendedStation.equalsIgnoreCase("t3") || intendedStation.equalsIgnoreCase("t4")
					|| intendedStation.equalsIgnoreCase("t5") || intendedStation.equalsIgnoreCase("t6")) {
				toReturn = false;
			}
		}
		else if(currentTrain.equalsIgnoreCase("t8")) {
			if(intendedStation.equalsIgnoreCase(t1) || intendedStation.equalsIgnoreCase("t2")
					|| intendedStation.equalsIgnoreCase("t3") || intendedStation.equalsIgnoreCase("t4")
					|| intendedStation.equalsIgnoreCase("t5") || intendedStation.equalsIgnoreCase("t6")
					|| intendedStation.equalsIgnoreCase("t7")) {
				toReturn = false;
			}
		}
		
		return toReturn;
	}
	
	/*
	 * Prompts the changing of stations for each train within the simulation and handles printing of result. 
	 */
	public void performCycle() {
		//determine if t1 changes cycle
		System.out.print("t1: " + t1);
		if(trainChangesStation()) {
			t1 = incrementLoopTrains(t1, "t1");
			System.out.print(" -> " + t1);
			if(errorLoopTrain) {
				System.out.print(" INTENDED STATION OCCUPIED");
				errorLoopTrain = false;
			}
		}
		else{
			System.out.print(" -> " + t1 + " DELAYED");
		}
		System.out.println();
		
		//determine if t2 changes cycle
		System.out.print("t2: " + t2);
		if(trainChangesStation()) {
			t2 = incrementLoopTrains(t2, "t2");
			System.out.print(" -> " + t2);
			if(errorLoopTrain) {
				System.out.print(" INTENDED STATION OCCUPIED");
				errorLoopTrain = false;
			}
		}
		else{
			System.out.print(" -> " + t2 + " DELAYED");
		}
		System.out.println();
		
		//determine if t3 changes cycle
		System.out.print("t3: " + t3);
		if(trainChangesStation()) {
			t3 = incrementLoopTrains(t3, "t3");
			System.out.print(" -> " + t3);
			if(errorLoopTrain) {
				System.out.print(" INTENDED STATION OCCUPIED");
				errorLoopTrain = false;
			}
		}
		else{
			System.out.print(" -> " + t3 + " DELAYED");
		}
		System.out.println();
		
		//determine if t4 changes cycle
		System.out.print("t4: " + t4);
		if(trainChangesStation()) {
			t4 = incrementLoopTrains(t4, "t4");
			System.out.print(" -> " + t4);
			if(errorLoopTrain) {
				System.out.print(" INTENDED STATION OCCUPIED");
				errorLoopTrain = false;
			}
		}
		else{
			System.out.print(" -> " + t4 + " DELAYED");
		}
		System.out.println();
		
		//determine if t5 changes cycle
		System.out.print("t5: " + t5);
		if(trainChangesStation()) {
			t5 = incrementLinearTrains(t5, "t5");
			System.out.print(" -> " + t5);
			if(errorLinearTrain) {
				System.out.print(" INTENDED STATION OCCUPIED");
				errorLinearTrain = false;
			}
		}
		else{
			System.out.print(" -> " + t5 + " DELAYED");
		}
		System.out.println();
		
		//determine if t6 changes cycle
		System.out.print("t6: " + t6);
		if(trainChangesStation()) {
			t6 = incrementLinearTrains(t6, "t6");
			System.out.print(" -> " + t6);
			if(errorLinearTrain) {
				System.out.print(" INTENDED STATION OCCUPIED");
				errorLinearTrain = false;
			}
		}
		else{
			System.out.print(" -> " + t6 + " DELAYED");
		}
		System.out.println();
		
		//determine if t7 changes cycle
		System.out.print("t7: " + t7);
		if(trainChangesStation()) {
			t7 = incrementLinearTrains(t7, "t7");
			System.out.print(" -> " + t7);
			if(errorLinearTrain) {
				System.out.print(" INTENDED STATION OCCUPIED");
				errorLinearTrain = false;
			}
		}
		else{
			System.out.print(" -> " + t7 + " DELAYED");
		}
		System.out.println();
		
		//determine if t8 changes cycle
		System.out.print("t8: " + t8);
		if(trainChangesStation()) {
			t8 = incrementLinearTrains(t8, "t8");
			System.out.print(" -> " + t8);
			if(errorLinearTrain) {
				System.out.print(" INTENDED STATION OCCUPIED");
				errorLinearTrain = false;
			}
		}
		else{
			System.out.print(" -> " + t8 + " DELAYED");
		}
		System.out.println();
	}
}
