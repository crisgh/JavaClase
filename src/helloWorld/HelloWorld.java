package helloWorld;
// cabecera de clase
public class HelloWorld {
	/*
	 * Cuerpo de la clase 
	 */
	public static void main(String[] args){
		// imprime el hola mundo!
		System.out.println("Hola Mundo!");
		// funcionamiento de los ++: 
		System.out.println("numeros con post y pre incremento");
		int i = 3;
		i++;//print 4
		System.out.println(i);
		++i;//print 5
		System.out.println(++i);
		//print 6
		System.out.println(i++);
		System.out.println(i);
	}
}
