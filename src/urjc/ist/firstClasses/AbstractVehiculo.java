package urjc.ist.firstClasses;

public abstract class AbstractVehiculo implements Acondicionado {

	private int numRuedas;
	private int potencia;
	private String color;


// Si tenemos metodos que no van a ser implementados tengo que definirlos como abstract 
	public abstract boolean arrancar();
	
	public abstract boolean parar();
	
	@Override 
	public String toString(){
		return String.join("\n", "vehiculos",
				"Ruedas: " + numRuedas,
				"potencia:" + potencia,
				"Color : " + color);
	}

}