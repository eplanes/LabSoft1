package domain;

public class Propeller {
	private double accelerationMax, accelerationActual;
	public Propeller(double accelerationMax) {
		validateAcceleration(accelerationMax);
		this.accelerationMax = accelerationMax;
		accelerationActual = 0.0;
	}
	
	private void validateAcceleration(double accelerationMax) {
		if(accelerationMax < 0) throw new IllegalArgumentException ("L'acceleració ha de ser més gran o igual a 0.");
	}
	
	public void updateAcceleration(double changeAcceleration) {
		if(changeAcceleration >= accelerationMax) this.accelerationActual = this.accelerationMax;
		else if(changeAcceleration <= 0.0) this.accelerationActual = 0.0;
		else this.accelerationActual = changeAcceleration;
	}
	
	public double getAcceleration() {return accelerationActual;}
	
	public double getMaxAcceleration() {return accelerationMax;}
	
	public void resetAcceleration() {accelerationActual = 0.0;}
}
