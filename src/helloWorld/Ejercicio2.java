package helloWorld;

import java.util.Random;

public class Ejercicio2 {
	/**
	 * En este ejemplo vamos a trabajar con llamadas a métodos
	 */
	
	public static String longest(String a, String b) {
		
		return (a.length()>b.length()? a : b);
		/*
		 * if (a.length() > b.length()){
		 * 	return a;
		 * }else{
		 * 	retunt b;
		 * } /
		 */
		// a.metodo();
	}
	// Pregunta 1: Utilizando el método longest, imprime por pantalla
	// la cadena más larga de las tres definidas arriba
	/*
	 *  Si,  comparamos la longitud de cadena(a.length);
	 */

	public static void main(String[] args) {
		
		String cadena1 = "Daenerys Targaryen";
		String cadena2 = "Tyrion Lannister";
		String cadena3 = "John Snow";
		
		String[] array; // array de objetos tipo String. Declaramos el array y con "split" reservariamos la memoria.
		
		
		System.out.println(longest(longest(cadena1, cadena2), cadena3));
		
		/* 
		 * Pregunta 2: Usando la interfaz de eclipse, explora qué otros
		 * métodos hay disponibles para una variable de tipo String.
	     * Intenta descubrir un método que sirva para separar el nombre de pila
		 * del apellido en las cadenas anteriores. Imprime por pantalla solo 
		 * el nombre de los tres personajes 
		 
			 regex : patron de busqueda, " ", nos devuelve un arrya de strings []
			 array de strings : Lista de elementos/ variables (posiciones).
			 tenemos un nº de posiciones finitas y todos los elementos tienen que ser del mismo tipo.
		*/
		
		//System.out.println(cadena1.split(" ")); // nos imprime la direccion de memoria de las posiciones del array
		
		array = cadena1.split(" "); // inicializamos el array
		
		System.out.println(array[0]); // imprimimos desde la posicion 0.
		
		//System.out.println(array[10]); // nos dara un excepcion : el programa sale. 
		// en java podemos tener errores en tiempo de compilacion (tipo de datos, variables)
		//o en tiempo de ejecucion; 
		//los primeros son sencillos, 
		//los segundos no son faciles de detectar(la clase esperada no es la ue tengo, "fallo de memoria")
		
		
		/*
		 * Pregunta 3: El siguiente código muestra cómo usar un objeto de la
		 * clase java.util.Random para generar secuencias de números pseudo-
		 * aleatorios. Partiendo de este código, escribe un bloque de sentencias
		 * que imprima cadena1 si el número obtenido es menor que 0.3, cadena2
		 * si el número obtenido es menor que 0.6 y cadena3 en los restantes casos.
		 */
		
		
		Random prng = new Random(); // Generador de números pseudo-aleatorios
		
		System.out.println(prng.nextDouble());
		// nextDouble()-> nos da otro numero de la secuencia pseudoaleatoria acotado en [0,1). valores de probabilidad
		
	}
}
