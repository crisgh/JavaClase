package urjc.ist.firstClasses;

public class Par<E1, E2> { // defino uno o mas argumentos de cualquier clase que puede ser aceptado por cualquier argumento
						   // Sin especificar el tipo. -- xej.ArrayList<"Cancion"> / tambien lo puede dejar en blanco sin pasar argumento Par<>	
	public E1 elem1;
	public E2 elem2;

	Par(E1 elem1, E2 elem2){ // Puede aceptar dos elementos de cualquier clase (Objects), al llamar a Par(), identifico el tipo especifico de dato (objeto)
							 // cuando lo identifico por primera vez, ya tiempre seran de ese tipo de datos.
		this.elem1 = elem1;
		this.elem2 = elem2;
	}
	
	
	
	
	public static void main(String[] args) {
		
		// Completando con un objeto Long y otro String
		
		Par<Long, String> par = new Par<Long, String>(32L, "hola"); // E1: Long, E2: string, a partir de aqui siempre E1:long y E2: string.(En Par)
		
		// Completando con un objeto Integer y otro Boolean
		
		Par<Integer, Boolean> par2 = new Par<Integer, Boolean>(16, true); //(tenemos otro par2 = new PAR()),con diferentes tipos de datos
		
		System.out.println(par.getClass());
		System.out.println(par2.getClass());
		// Son distintos objetos, con la misma clase.
	}
}
