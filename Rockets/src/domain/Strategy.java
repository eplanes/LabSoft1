package domain;

public class Strategy {
	
	public static boolean updateAccelerationOnRace(Rocket rocket, Race race) {
		double velocitatSobrant;
		velocitatSobrant = computeBestAcceleration(rocket, race); 
	    return velocitatSobrant == 0.0;
	}
	
	private static double computeBestAcceleration(Rocket rocket, Race race) {
		double maxSpeed = rocket.getFuelTank().getActualVolume() / (0.02 * (race.getDistance()-rocket.getPosition()));
		return calibrateAcceleration(rocket, maxSpeed - rocket.getSpeed());
	}
	
	private static double calibrateAcceleration(Rocket rocket, double speed) {
		double maxAcceleration;
		for(Propeller propeller : rocket.getPropellersList()) {
			maxAcceleration = propeller.getMaxAcceleration();
			if(maxAcceleration <= speed) {
				propeller.updateAcceleration(speed);
				speed -= maxAcceleration;
			} else {
				propeller.updateAcceleration(speed); 
				speed = 0.0;
				break;
			}
		}
		return speed;
	}
}
