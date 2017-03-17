package urjc.ist.firstClasses;

public interface Acondicionado {
	
	// No podemos tener atributos - solo metodos o constantes (tienen que ser publicos o statics)
	
	public boolean encenderAire(double grados); 
	public boolean apagarAire();
	public boolean ponerTemperatura(double grados);
	
	public boolean ok = true; // no da fallo pero no se va a usar.  solo podemos tener o public - static, nunca private

}
