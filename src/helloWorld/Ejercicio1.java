package helloWorld;

public class Ejercicio1 {
	/**
	 * Primer ejemplo de declaración y uso de variables con tipos primitivos
	 */
	
	/*
	 * los metodos presentan un static implica que es un 
	 * metodo estatico de la clase, no necesito tener un objeto
	 *  de esa clase para referenciarlo.
	 *  Existe un solo metodo para la clase- metodo de utilidad, no necesito
	 *  crear los onjetos de la clase para llamarlos
	 */
	
	public static int suma(int num1, int num2) {
		return num1 + num2;
	}

	public static double suma_double(double num1, double num2) {
		
		return num1 + num2;
	}
	
	public static int resta(int num1, int num2) {
		return num1 + num2;
	}
	
	public static double media(int num1, int num2) {

		return (num1 + num2)/2.; 
		
		// para que sea un double, o un float dependiendo de lo que metas ya que el resultado de la
		// expresion evalua al tipo de datos mas grande- entonces evaluaria a double.
	}
	
	public static int max(int num1, int num2){
		//Math.max(num, num2);
		return (num1 > num2)? num1 : num2; // if else -- ternario 
	}
	
	public static int min(int num1, int num2){
		// Implementar el cuerpo del método
		 return (num1 < num2)? num1 : num2; // if else -- ternario 

	}
	
	public static void main(String[] args){
		int a = 1, b = 2, c = 3;
		float d = 3.f, e = 4.f;
		double x = 3.14, y = 2.71, z = 1.1412;
		
		// pasar un argumento a un metodo(que espere un tipo primitivo o un objeto) 
		// puedo pasarle la expresion directamente o
		// puedo llamar al metodo(sus argumentos : valor de una variable(a,b,c) 
		// o el resultado ed cuanquier expresion aritmetica que evalue a un entero)
		
		System.out.println("La suma de los tres enteros es:" + suma(a + b, c));
		// Pregunta 1: ¿Podemos usar el método suma(int, int) con argumentos 
		// de tipo float o double? ¿Cómo?
		/*
		 * Si tenemos un tipo de dato o clase diferente tengo que hacer casting para forzarlo 
		 */

		d = Math.round(d); 
		e = Math.round(e);
		
		System.out.println("La suma de los floats es:" + suma((int)d, (int)e));
		// Tenemos que hacer casting - ya que devuelve un tipo long y necesitamos un tipo int
		
	
		System.out.println("La suma de los 3 double es:" + suma_double(x+ y,z));
		
		
		// Pregunta 2: ¿Qué ocurre si usamos el método suma_double(double, double)
		// con dos números int como argumentos? ¿Y si usamos dos números float?
		
		// Pregunta 3: Completa los tres métodos que faltan por implementar y comprueba
		// su correcto funcionamiento en este método main.
		
		// Implementar un método suma_double para sumar dos números double que se
		// pasen como argumentos
		

	}
}
