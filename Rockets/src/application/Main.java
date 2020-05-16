package application;

import domain.*;

public class Main {
	public final static int totalTime = 2200;
	public final static double rocketDeposit = 2000;
	public final static double raceDistance = 5000;
	private final static int timeInterval = 1;
	private static int finalTime = timeInterval;
	
	public static void main (String [] args) {
		Race race = askForRace();
		Rocket rocket = askForRocket();
		System.out.println("Starting competition. Circuit length: "+race.getDistance()+" Max time: "+race.getTime()+"\n");
		if(!raceResults(rocket, race)) System.out.println("\nThere is no winner.");
		else System.out.println("\nAnd the winner is: "+rocket.getName()+" with a time of "+finalTime+" seconds.");
	}
	
	private static boolean raceResults(Rocket rocket, Race race) {
		while(finalTime <= race.getTime()) {
			raceController(rocket, race);
			if(rocket.raceFinished(race)) return true;
			else if(finalTime > race.getTime()) return false;
			else finalTime += timeInterval;
		}
		return false;
	}
	
	private static void raceController(Rocket rocket, Race race) {
		rocket.updateOnTime(race, timeInterval);
		System.out.println("Current Time: "+finalTime+" "+rocket+
				"  \tCircuit: "+race.getDistance()+ 
				"  \tFuel: "+toRoundDouble(rocket.getFuelTank().getActualVolume())+
				"/"+rocket.getFuelTank().getMaxVolume());
	}
	
	private static void prepairRocket(Rocket rocket) {
		addPropeller(rocket);
		addDeposit(rocket);
		rocket.setInitialStatus();
	}
	
	private static Race askForRace() {
		return new Race("MadMax", raceDistance, totalTime);
	}
	
	private static Rocket askForRocket() {
		Rocket rocket = new Rocket("FalconIX");
		prepairRocket(rocket);
		return rocket;
	}
	
	private static void addPropeller(Rocket rocket) {
			rocket.addNewPropeller(40);
			rocket.addNewPropeller(29);
			rocket.addNewPropeller(12);
	}
	
	private static boolean addDeposit(Rocket rocket) {
		return rocket.addFuelTank(new FuelTank(rocketDeposit));
	}
	
	private static double toRoundDouble(double number) {
		number *=100;
		number = Math.round(number);
		number/=100;
		return number;
	}
}
