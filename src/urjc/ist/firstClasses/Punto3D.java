package urjc.ist.firstClasses;

import java.util.ArrayList;

public class Punto3D extends Punto {
	
	private int z ;
	
	public Punto3D() {
		// TODO Auto-generated constructor stub
		z = 0;
	}

	public Punto3D(int x, int y,int z) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.z = z;
	}
	
	static void sumarPuntos(ArrayList<? extends Punto> arr){ // recibe cualquier cosa que herede de punto
		// Sumar x componentes
		System.out.println("sumar puntos");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Punto punto2D = new Punto(2,4);
		Punto3D punto1 = new Punto3D();
		Punto3D punto2 = new Punto3D(3,2,5);
		ArrayList<Punto> arr1 = new ArrayList<Punto>();

		arr1.add(punto1);
		arr1.add(punto2);
		arr1.add(punto2D);
		
		sumarPuntos(arr1);
		
	}

}
