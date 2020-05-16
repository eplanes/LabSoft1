package domain;

import java.util.ArrayList;
import java.util.List;

public class Rocket {
	private final String name;
	private boolean perfectSpeed = false;
	private double actualSpeed, actualAcceleration; 
	private double position;
	private FuelTank fuelTank;
	private List<Propeller> propellers;
	
	public Rocket(String name) {
		validateName(name);
		this.name = name;
		propellers = new ArrayList<Propeller>();
		position = 0.0;
	}
	
	public String toString() {
		return 	" \tAcceleration: "+toRoundDouble(this.actualAcceleration)+
				" \tSpeed: "+toRoundDouble(this.actualSpeed) +
				" \tDistance: "+toRoundDouble(this.position);
	}
	
	private void stopRocket() {
		actualSpeed = 0.0;
		this.getFuelTank().setFuelTankTo0();
	}
	
	private void computeFinalPosition(int timeInterval) {
		double timePercentage = this.getFuelTank().getActualVolume() / (0.02 * this.actualSpeed * this.actualSpeed);
		this.position += this.actualSpeed * (timeInterval * timePercentage);
	}
	
	private void validateName(String name) {
		if(name.equals("") || name == null) throw new IllegalArgumentException("Nom del cohet nul.");
	}
	
	private double toRoundDouble(double number) {
		number *=100;
		number = Math.round(number);
		number/=100;
		return number;
	}
	
	public boolean raceFinished(Race race) {
		return this.position >= race.getDistance();
	}
	
	public void updateOnTime(Race race, int timeInterval) {
		updateAcceleration(race);
		updateSpeed(timeInterval);
		updatePosition(race, timeInterval);
		setPropellersTo0();
	}
	
	private void updateAcceleration(Race race) {
		if(!perfectSpeed) this.perfectSpeed = Strategy.updateAccelerationOnRace(this, race);
		this.actualAcceleration = getTotalAcceleration();
	}
	
	private void updateSpeed(int timeInterval) {
		actualSpeed += actualAcceleration * timeInterval;
	}
	
	private void updatePosition(Race race, int timeInterval) {
		try {
			updateFuelTank();
			this.position += (actualSpeed*timeInterval)+(actualAcceleration*timeInterval*timeInterval)/2;
		} catch (RuntimeException e) {
			computeFinalPosition(timeInterval);
			stopRocket();
		}
	}
	
	private void updateFuelTank() {
		updateFuel();
	}
	
	private void updateFuel() {
		try {
			fuelTank.updateTank(actualSpeed);
		} catch (RuntimeException e) {
			throw new RuntimeException("Vehicle aturat. "+e.getMessage());
		}
	}
	
	public boolean addFuelTank(FuelTank deposit) {
		this.fuelTank = deposit;
		return true;
	}
	
	public boolean addNewPropeller(double accelerationMax) {
		return propellers.add(new Propeller(accelerationMax));
	}
	
	public boolean addExistentPropeller(Propeller propeller) {
		return propellers.add(propeller);
	}
	
	public FuelTank getFuelTank() {return this.fuelTank;}
	
	public List<Propeller> getPropellersList() {return this.propellers;}
	
	public String getName() {return this.name;}
	
	public double getPosition() {return position;}
	
	public double getSpeed() {return actualSpeed;}
	
	public double getTotalAcceleration() {
		double totalAccelation = 0.0;
		for(Propeller propeller : propellers) {
			totalAccelation += propeller.getAcceleration();
		}
		return totalAccelation;
	} 
	
	public void setInitialStatus() {
		this.position = 0;
		this.actualSpeed = 0.0;
		this.actualAcceleration = 0.0;
		setInitialFuelTank();
	}	
	
	private void setPropellersTo0() {
		for(Propeller propeller : propellers) {
			propeller.resetAcceleration();
		}
	}
	
	public void setInitialFuelTank() {fuelTank.loadFuelTank();}
}
