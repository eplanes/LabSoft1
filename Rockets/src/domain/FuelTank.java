package domain;

public class FuelTank {
	private double maxVolume, actualVolume;
	
	public FuelTank(double maxVolume) {
		validateVolume(maxVolume);
		this.maxVolume = maxVolume;
		loadFuelTank();
	}
	
	private void validateVolume(double volume) {
		if(volume < 0.0) {
			throw new IllegalArgumentException ("El volum del diposit ha de ser més gran o igual a 0.");
		} 
	}
	
	public void loadFuelTank() {
		this.actualVolume = this.maxVolume;
	}
	
	public void setFuelTankTo0(){actualVolume = 0.0;}
	
	public void updateTank(double speed) {
		double decreasement = 0.02 * (speed * speed);
		if((actualVolume - decreasement) <= 0) throw new RuntimeException("S'ha acabat la gasolina.");
		else actualVolume -= decreasement;
	}
	
	public double getMaxVolume() {return this.maxVolume;}
	
	public double getActualVolume() {return this.actualVolume;}
}
