package domain;

public class Race {
	private final String name;
	private final double distance;
	private int timer;
	
	public Race(String name, double distance, int timer) {
		validateParameters(name, distance, timer);
		this.distance = distance;
		this.name = name;
		this.timer = timer;
	}
	
	private void validateParameters(String name, double distance, int timer) {
		validateName(name);
		validatePositiveValue(distance);
		validatePositiveValue(timer);
	}
	
	private void validateName(String name) {
		if(name == null || name.equals("")) throw new IllegalArgumentException("Nom del circuit buit."); 
	}
	
	private void validatePositiveValue(double distance) {
		if(distance <= 0.0) throw new IllegalArgumentException("Valor incorrecte, "
				+ "ha de ser un nombre positiu més gran que 0.");
	}
	
	public int getTime() {return this.timer;}
	public double getDistance() {return this.distance;}
	public String getName() {return this.name;}
}
