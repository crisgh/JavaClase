package helloWorld;

import java.util.Scanner;

public class Ejercicio4 {
	/*
	 * Enunciado: crear un programa que solicite introducir por consola
	 * un número entero en el intervalo [1, 12]. A continuación, el programa
	 * debe imprimir por pantalla el nombre completo del mes que corresponde
	 * al entero introducido.
	 */
 	public static void main(String[] args) {
 		 
	Scanner entrada = new Scanner(System.in); 
	// Scanner -- es EL OBJETO de tipo entrada con argumento(system.IN) para la "EC"/S
	
	System.out.println("Introduce un numero de 1 a 12: ");
	int mes = entrada.nextInt();
	
	String[] arrayMeses = new String[]{
			"enero","febrero","marzo","abril","mayo",
			"junio","julio","agosto","septiembre","octubre",
			"noviembre","diciembre"
	};
	
	// entrada.___
	int i = 0;
	for (i = 0;  i<= arrayMeses.length; i++){
		if (i == mes -1){ // restamos uno para tener la posicion exacta del mes ya que arrays empieza en 0
			System.out.println("El numero " + mes + " es el mes de " + arrayMeses[i]);
		}
	}
 	}
}