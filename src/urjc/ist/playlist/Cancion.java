package urjc.ist.playlist;
import java.util.Objects;
public class Cancion {
	
	public enum Codecs {
		/*
		 * tiene que ser publico para poder inicializarlo ya que necesita acceder a esa clase 
		 */
		// enum : enumerado
		// Codecs : miniclase 
		MP3, FLAC,OGG
		// literales (en mayuscula) del tipo enmuerado que queremos definir
		// MP3 : con perdidas , FLAC : sin perdidas
		
		// para referirse a un tipo enumerado : NombretipoEnum.Valor --> Codecs.MP3 o Codecs.FLAC
	}

	private String titulo;  // Título de la canción
	private String autor;  // Autor(a) de la canción
	private int duracion;  // Duración en segundos
	private Codecs formato;  // Codificación de la canción
	
	// ----------- CONSTRUCTORES ------------- //
	
	public Cancion() {
		/*
		 * Constructor por defecto -- los inicializa a null o 0
		 */
		// TODO Auto-generated constructor stub
		titulo = "";
		autor = "";
		duracion = 0;
		formato = null;
	}
	
	public Cancion(String titulo, String autor, int duracion, Codecs format) {
	/*
	 * Constructor especializado -- puede tener comprobaciones. das un valor propio a cada argumento
	 * Llamamos a los argumentos del constructor igual que a los valores del objeeto por eso tenemos que usar el this.AtributoObj
	 */
		this.titulo = titulo;
		this.autor = autor;
		this.duracion = duracion;
		this.formato = format;
	}
	
	// ----------- METODOS [GET/SET] ------------- //

	public String getTitulo(){
		return titulo;
	}
	
	public void setTitulo(String titulo){
		// no devuelve un boolean  --> en este caso no devuelve nada(asigna el valor al atributo)
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public Codecs getFormato() {
		return formato;
	}
	
	public void setFormato(Codecs formato) {
		this.formato = formato;
	}
	
	@Override // Indica al compilador que el metodo que va a continuacion va a sobreescribir el metodo de la clase superior
	 	      // Decorator : le da informacion al depurador para comprobar que sobreescribe "algo" que esta en una de las superclases 
	  		  // Si da un fallo : Error : no hay un metodo que pueda ser sobreescrito
	
	public String toString() { //Convertimos a string el obj de la clase cancion, 
		/**
		 * Creación de una representación del contenido de la
		 * Canción en formato String
		 */
			
		int hours = duracion / 3600;
		int minutes = (duracion % 3600) / 60;
		int seconds = duracion % 60;

		String timeString;
		if (hours > 0) {
			timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds); //%02d : int de dos cifras 
		} else {
			timeString = String.format("%02d:%02d", minutes, seconds);
		}
		
		String formatoString;
		switch(formato){
		case MP3 : 
			formatoString = "MP3";
			break;
		case FLAC : 
			formatoString = "FLAC";
			break;
		case OGG : 
			formatoString = "OGG";
			break;
		default : 
			formatoString = "??";
			break;
		}	
		return String.join("  ",
				"  Título: " + titulo,
				"  Autor: " + autor,
				"  Duración: " + timeString,
				"  Formato: " + (formatoString)+ 
				//con tres opciones : tenemos tipos enumerados : ogg,mp3,flac = 0,1,2
				// entonces podemos hacerlo con un swich  asignando a cada variable un caso
				"\n","     ");
	}
	
	@Override
	public boolean equals(Object other) { // si la direccion de memoria obj A es igual a la del obj B -- Si son el mismo obj(equals)
		// queremos saber si son identicos aunque no esten en la misma direccion de memoria -- Iguales por valor
		/**
		 * Implementación de un método de comparación del contenido
		 * de dos canciones
		 */
		// Debemos usarlos (ifs) siempre en las clases : en el metodo equals
		// Equals actua siempre con un Object (cualquiera) 
	   
		if (other == this)return true;  // si el objeto se compara consigo mismo : true (misma dirMem) -- equals.javaLangObj
		if (other == null) return false; // si con lo que comparo es null : false -- prog java -- convencion
	    if (!(other instanceof Cancion)) return false;// Si el otro obj con el comparo no es el otro obj una instancia de Cancion : False
	 // if(getClass() != OtherObject.getClass()) return false; -- asume this.getClass()
	    Cancion otherCancion = (Cancion)other; // para hacer la comparacion de los objetos tengo que hacer CASTING - a la clase que quiero comparar
	    if (this.titulo == otherCancion.titulo &&
		    	this.autor == otherCancion.autor &&
		    	this.duracion == otherCancion.duracion &&
		    	this.formato == otherCancion.formato) {
		    return true;
	    } else {
	    	return false;
	    }
	}
	@Override
	public int hashCode(){
		return Objects.hash(titulo,autor,duracion,formato);
	}
	public static void main(String[] args) { // ejemplo de uso
		Cancion track1 = new Cancion("The Song of the Sun", "Mike Olfield", 273, Codecs.MP3);
		Cancion track2 = new Cancion("The Song of the Sun", "Mike Olfield", 273, Codecs.MP3);
		
		System.out.println(track1);
		System.out.println(track2);
		System.out.println(track1.equals(track2)); 
		System.out.println((track1.hashCode())==(track2.hashCode()));
	}

}

