package helloWorld;
import java.util.Scanner;

public class Ejercicio3 {
	/*
	 * En este ejercicio practicamos algunas funciones de E/S
	 */
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in); 
		// Scanner -- es EL OBJETO de tipo entrada con argumento(system.IN) para la "EC"/S
		
		System.out.print("Introduce tu nombre: ");
		String nombre = entrada.nextLine();
		// entrada.___
		
		System.out.print("Introduce tu edad: ");
		int edad = entrada.nextInt();
		
		System.out.println("Me llamo " + nombre + " y tengo " + edad + " años.");
		
		// Pregunta: ¿Qué ocurre si introducimos un tipo de dato incorrecto, como
		// una cadena de caracteres no numérica, en la segunda entrada de datos?
		// que solicita el programa?
		// Te devuelve una EXCEPTION  : sale una traza de error - para saber de donde proviene el origen del fallo,
		// las trazas se leen de abajo a arriba (pila).
	}
}
