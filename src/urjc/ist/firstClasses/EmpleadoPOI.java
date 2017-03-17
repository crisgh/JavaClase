package urjc.ist.firstClasses;
import java.util.Calendar;


public class EmpleadoPOI extends Empleado {
	// Declaracion de la Clase - que es una subclase de la clase Empleado , hereda todo lo de la superclase y añade mas detalles

	public EmpleadoPOI() {
		// TODO Auto-generated constructor stub
	}

	public EmpleadoPOI(String unNombre, String unosApellidos, String unDNI, double unSalario) {
		super(unNombre, unosApellidos, unDNI, unSalario);
		
		// super : llama al constructor de la superclase (public Empleado(...)).
		// Es como si llamaramos directamente al constructor de la superclase .. Empleado(..).
		// Eso es la encapsulacion. Se refiere al objeto de la clase superior (super.nombre)
		
		// TODO Auto-generated constructor stub
		// Firma de los contructores, tiene la misma clase que los de la clase superior ( Empleado) , si no los tubieramos tenemos
		// que hacer un contrucctor por cada una de las clases que tengamos.
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmpleadoPOI e3 = new EmpleadoPOI();
		System.out.println(e3 instanceof Empleado); // nos devuelve TRUE , ya que e3 es una instancia de la clase Empleado y de la 
													// instanceof coprueba si objecto pertenece a una clase -- si es herencia, es de si mismo y de su superclase 
	}

}
