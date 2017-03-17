package urjc.ist.firstClasses;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *  Aquí importamos los paquetes o clases externas que necesitemos usar dentro
 *  de nuestra clase
 */

public class Empleado {

	/*
	 *  Tras la definición de clase declaramos constantes, si son necesarias
	 */
	
	private final double MAX_SALARIO = 90000.;
	
	/* 
	 * Después van los atributos o campos (fields) que tendrán
	 * cada uno de los objetos de la clase Empleado 
	 */
	/*
	 * Introduccion a Gregorian Calendar - para saber las fechas
	 */
	
	private String nombre;
	private String apellidos;
	private String DNI;
	private double salario;
	
	/*
	 * A continuación van los métodos constructores de objetos de esa clase.
	 * El nombre es igual que el de la clase, pero se pueden definir diferentes
	 * versiones que acepten distintos argumentos
	 * 
	 * El primero es el constructor por defecto, que no recibe ningún argumento
	 * e inicializa los atributos del objeto a valores iniciales por defecto.
	 * 
	 * Si no implementamos **ningún constructor** en nuestras clases, entonces
	 * se hereda automáticamente el constructor por defecto de java.lang.Object.
	 * 
	 * Sin embargo, basta con que tan solo implementemos un método constructor de
	 * nuestra clase (el por defecto u otra versión que reciba argumentos) para que
	 * ya no sea visible el constructor por defecto heredado de Object.
	 */
	
	public Empleado() {
		/**
		 * Este es un ejemplo de constructor por defecto de mi clase
		 */
		
		nombre = "";
		apellidos = "";
		DNI = "";
		salario = 0;
	}
	
	public Empleado(String unNombre, String unosApellidos, String unDNI, double unSalario) {
		/**
		 * Este es un ejemplo de constructor que acepta argumentos
		 * Usaremos los get y set para actualizar los valores del contructor
		 */
		
		/*
		 * this.nombre -- hace referencia al objeto sobre el que llamo el metodo. this.nombre sera el atributo y lo sustituyo por = "nombre" 
		 */
		if (this.setName(unNombre) == true){
			nombre = unNombre;
		}
		if (this.setApellidos(unosApellidos)== true){
			apellidos = unosApellidos;
		}
		if (this.setDNI(unDNI)){
			DNI = unDNI;
		}
		if (this.setSalario(unSalario)){
			salario = unSalario;
		}
	}
	
	/*
	 * Después de los constructores se suelen poner los métodos de acceso y modificación de
	 * los atributos, llamados getters y setters (respectivamente). No obstante, hay
	 * desarrolladores que prefieren poner estos métodos al final de la clase.
	 */
	
	// INSERTA AQUÍ EL MÉTODO DE ACCESO AL NOMBRE
	public String getName(){
		System.out.print("Nombre: ");
		return nombre;
	}
	
	// INSERTA AQUÍ EL MÉTODO DE MODIFICACIÓN DEL NOMBRE
	public boolean setName(String newNombre){
		nombre = newNombre;
		return true;
		
	}
	// INSERTA AQUÍ EL MÉTODO DE ACCESO AL APELLIDO
	public String getApellidos(){
		System.out.print(" Apellidos: ");

		return apellidos;
	}
	
	// INSERTA AQUÍ EL MÉTODO DE MODIFICACIÓN DEL APELLIDO
	public boolean setApellidos(String newApellido){
		
		apellidos = newApellido;
		return true;
		
	}
	
	public String getDNI() {
		/**
		 * Método de acceso al DNI
		 */
		System.out.print(" DNI: ");

		return DNI;
	}
	
	public boolean setDNI(String newDNI) {
		/**
		 * El método debe hacer una mínima comprobación sobre la validez
		 * del nuevo ID: verificar que el últio caracter no es numérico y
		 * que la longitud total es de 9 caracteres (con 0 a la izquierda de
		 * relleno si es necesario) 
		 */
		String DNIcheck = newDNI;

		int longitud = DNIcheck.length();
		if (longitud !=9){
			if (longitud < 9){
				while(DNIcheck.length()< 9){
					DNIcheck = "0" + DNIcheck; 
					
				}
				DNI = DNIcheck;
				return true;
			}
			DNI = "Incorrecto";
			return false; 
		}else{
		
			String patron = "([0-9]{8})([A-Z])";//Indicamos que queremos 8 numeros, seguidos de una letra
			Pattern p = Pattern.compile(patron);

			Matcher matcher = p.matcher(DNIcheck);
			 
			while(matcher.find()){
						// System.out.printf("DNI : %s - %s es válido",
						// matcher.group(1),//Con el group, decimos que parámetro queremos mostrar
			            // matcher.group(2));
						 DNI = DNIcheck;
				  return true;
			}
			DNI="Incorrecto";
			return false ;
				
		}
	}
	
	public double getSalario() {
		/**
		 * Método de acceso al Salario
		 */

		return salario;
	}
	public boolean setSalario(double newSalario){
		if (newSalario <= MAX_SALARIO){
			salario = newSalario;
			return true;
		}
		salario = 0.0;
		return false ;
	}
	public boolean subirSalario(double cantidad) {
		/**
		 * No se podrá tener un salario superior a 90.000 euros
		 */
		
		if (salario + cantidad <= MAX_SALARIO) {
			salario += cantidad;
			return true;
		}
		else {
			// Más adelante veremos cómo lanzar errores (llamados excepciones)
			return false;
		}
	}
	
	public String toString() {
		/**
		 * Sobreescribimos el método genérico toString de java.lang.Object
		 * para mostrar información útil sobre el empleado cada vez que
		 * nos soliciten representar el objeto como una cadena de caracteres
		 */
		return String.join(" ", "Nombre y apellidos: ", nombre, apellidos,
			   "DNI: ", DNI,
			   "Salario: ", String.valueOf(salario));		
	}
	
	public static void main(String[] args) {
		
		// Vamos a probar nuestra clase Empleado
		
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado("John D.", "Rockefeller",
										  "00000001A", 90000);
		Empleado empleadoCris = new Empleado("Cristina", "Gallego Herrero",
				  "0000520001A", 100000);
		empleado1.setName("Jason");
		empleado1.setApellidos("Bourne");
		empleado1.setDNI("88777X");
		empleado1.setSalario(85000);
		// nombreObj.metodo("lo que sea");
		//System.out.println(empleado1.getName()+ empleado1.getApellidos() + empleado1.getDNI() + empleado1.getSalario() );
		System.out.println(empleado1);
		System.out.println(empleado2);
	
		System.out.println(empleadoCris);
		
		/*
		 * A continuación, modificar los atributos del objeto empleado1 de la siguiente
		 * forma:
		 * 		- nombre: Jason;
		 * 		- apellido: Bourne;
		 * 		- DNI: 99888777X;
		 * 		- salario: 85.000;
		 */
	}
}
