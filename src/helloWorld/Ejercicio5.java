package helloWorld;
import java.util.Collections;
import java.util.Random;

public class Ejercicio5 {
	/**
	 * Enunciado: Crea un programa que genere una serie de números
	 * enteros pseudo-aleatorios, usando java.util.Random. El programa
	 * debe concluir cuando se hayan generado 10 números pares.
	 * 
	 * A continuación, expandir el programa creando un bloque de código
	 * que genere una nueva serie de, exactamente, 20 números enteros pseudo-aleatorios.
	 * Al concluir, el programa debe imprimir por pantalla el menor de todos los
	 * números generados en esta segunda serie.
	 */
	public static void AleatorioPar(){
		Random rnd = new Random();
		int i = 1;
		int array[] = new int[10];
		int  contador = 0;
		while(contador < array.length){
			int x = rnd.nextInt();
			if (x % 2 == 0){
				array[contador] = x;
				System.out.println(i + "_ " + array[contador]);
				contador++;
				i++;
			}
		}
	}
	public static void MenNumAleatorios(){
		Random rnd = new Random();
		int array[] = new int[20];
		int  contador = 0;
		int indice = 1;
		while(contador < array.length){
			int x = rnd.nextInt();
			array[contador] = x;
			System.out.println(indice + "_ " + array[contador]);
			contador++;
			indice++;
			
			}
		int j = 0 ;
		int min = array[j];
		while(j < array.length){
			if(array[j]<= min){
				min = array[j];
				j++;

			}else{
				j++;
			}
		}
		System.out.println("El valor minimo de los 20 es : "+ min);

	}
	
	public static void main (String[] args){
		System.out.println("10 numeros pares aleatorios : ");
		AleatorioPar();
		System.out.println();
		System.out.println("calcular el minimo de 20 numeros aleatorios : ");
		MenNumAleatorios();
	}
		
}
	

