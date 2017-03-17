package urjc.ist.firstClasses;

public class Coche extends AbstractVehiculo {
	
	private boolean arrancado;
	@Override
	public boolean arrancar() {
		arrancado  = true;
		return arrancado;
		
	}

	@Override
	public boolean parar() {

		return !arrancado;
	}

	public boolean encenderAire(double grados){
		return true;
	}
	public boolean apagarAire(){
		return false;
	}
	public boolean ponerTemperatura(double grados){
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coche Xsari = new Coche();
		Xsari.arrancar();
		System.out.println(Xsari.arrancar());
		System.out.println(Xsari.parar());

		
	}

}
