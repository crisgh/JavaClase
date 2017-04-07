package urjc.ist.playlist;

import java.util.ArrayList;// Semejante a un array pero en tiempo de ejecucion puede expantirse o comprimirse
import java.util.Objects;


import urjc.ist.playlist.Cancion;
import urjc.ist.playlist.Cancion.Codecs;

public class Album implements java.io.Serializable{
	/*  TODO Se pide completar la definición de la clase Album. Para ello, se puede consultar la URL sobre la clase de
	 *  utilidad ArrayList, que permite crear arrays de cualquier tipo de objetos pero de longitud flexible (se pueden
	 *  extender o acortar en tiempo de ejecución  
	 *  // https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html  -- > metodos de se usan con ARRAYLIST
	 */
	
	private String titulo; // titulo del album
	private String autor;
	private String grupo;
	private int duracionTotal;
	private ArrayList<Cancion> trackList; // Semejante a un array pero en tiempo de ejecucion puede expantirse o comprimirse
										  // Composicion : uso clase para formar otras clases --> Usamos Cancion para completar la clase album	
	public Album() {
		// TODO Completar un constructor por defecto para Album
		titulo = "";
		autor = "";
		grupo = "";
		duracionTotal = 0;
		
		trackList = new ArrayList<Cancion>();// arraylist : no la inicializamos, solo delcaramos
		
	}
	
	public Album(String titulo, String autor, String grupo) { // constructor especializado
		this.titulo = titulo;
		this.autor = autor;
		this.grupo = grupo;
		duracionTotal = 0; 
		trackList = new ArrayList<Cancion>(); // Crea un array vacio de obj de tipo cancion
	}
	
	// TODO Añadir métodos de obtención y asignación de valores
	// para los 4 primeros atributos del objeto Album (SET y GET - titulo autor y grupo) 
	
	public String getTituloAlbum(){
		return titulo;
	}
	
	public void setTituloAlbum(String titulo){
		
		this.titulo = titulo;
	}
	
	public String getAutorAlbum() {
		return autor;
	}
	
	public void setAutorAlbum(String autor) {
		this.autor = autor;
	}
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
		
	public int getDuracionTotal() {
		return duracionTotal;
	}
	
	public int getCanciones(){
		return trackList.size();
	}
	/*
	public void setDuracionTotal(int duracionTotal) {
		this.duracionTotal = duracionTotal;
	}
	no tiene sentido
	*/
	
	
	public ArrayList<Cancion> getTrackList() {
		/**
		 * Método que devuelve la lista de canciones actualmente
		 * incluídas en el álbum
		 */
		
			return trackList;
		
	}
	public Cancion getTrack(int posicion) {
		/**
		 * Método que devuelve la canción que esté en la posición
		 * de la lista del álbum que se indica como argumento
		 */
		try
		{
			return trackList.get(posicion);
		}	
        catch(java.lang.IndexOutOfBoundsException excepcion)
	         {
		         System.out.println("No existe esa cancion en el album, el album tiene : " + trackList.size() + " canciones");
	         }
		return trackList.get(0); // como hacer que no retorne "¡???!"
		
	}
	
	public void addTrack(Cancion unaCancion) {
		/**
		 * Método que añade una canción a la lista de canciones del álbum. Además, el método calcula y actualiza  
		 * la nueva duración total del álbum
		 */
		trackList.add(unaCancion);
		duracionTotal = duracionTotal + unaCancion.getDuracion();
		
	}
	
	public void addTrack(int posicion, Cancion unaCancion) {
		/**
		 * Método que añade una canción al álbum(que pasamos como segundo argumento) en la posición que indique 
		 * el int que recibe como primer argumento
		 */
		//  Si no tenemos esa posicion , EXCEPCION
		try{
				
			trackList.add(posicion, unaCancion);
			duracionTotal = duracionTotal + unaCancion.getDuracion();
				
		    
		}catch (IndexOutOfBoundsException e){
			System.out.println("Capturada la excepcion " + e );
			e.printStackTrace(); // nos muestra la traza la excepcion de abajo a arriba
			System.out.println(e.getMessage()); // mensaje de la excepcion
			System.out.println("Añadimos la cancion al final. " );
		    addTrack(unaCancion); // si damos un valor erroneo la añade al final -- manejo de excepcion SIN exception
		    }
				
	}
	public void deleteLastTrack() {
		/**
		 * Método que borra la última canción de la lista del
		 * álbum
		 */
		trackList.remove(trackList.size() - 1); // CUANDO SOLO HAY UNA EXCEPCION -- BORRAR ALBUM
	}
	
	public void deleteTrack(int posicion) {
		/**
		 * Método que borra la canción en la posición de la lista
		 * que indica el argumento que recibe
		 */
		if (posicion <= trackList.size()){
			trackList.remove(posicion);	
		}else{
			System.out.println("No existe esa cancion en el album");
			
		}
		
		
	}
	
	public void clearAlbum() {
		/**
		 * Método que borra todas las canciones en la lista de
		 * un álbum
		 */
		trackList.clear();
	}
	// metodo equals y toString para el metodo album

	@Override
	public String toString() {
		int duracionDisco = getDuracionTotal();
		int hours = duracionDisco / 3600;
		int minutes = (duracionDisco % 3600) / 60;
		int seconds = duracionDisco % 60;

		String timeString;
		if (hours > 0) {
			timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds); //%02d : int de dos cifras 
		} else {
			timeString = String.format("%02d:%02d", minutes, seconds);
		}

		return String.join("\n","********",
				"  Título: " + titulo,
				"  Autor: " + autor,
				"  Grupo: " + grupo,
				"  Duración disco: " + timeString,
				"  Canciones :\n        "+ this.getTrackList(),
				"\n","********");

		
	}
	@Override
	public boolean equals(Object other) { // si la direccion de memoria obj A es igual a la del obj B -- Si son el mismo obj(equals)
		// queremos saber si son identicos aunque no esten en la misma direccion de memoria -- Iguales por valor
		/**
		 * Implementación de un método de comparación del contenido
		 * de dos album
		 */
		// Debemos usarlos (ifs) siempre en las clases : en el metodo equals
		// Equals actua siempre con un Object (cualquiera) 
		// tiene que mirar todos los atributos de album y todas las canciones
		
		if (other == null) return false; // si con lo que comparo es null : false 
	    if (other == this) return true;  // si el objeto se compara consigo mismo : true
	    if (!(other instanceof Album)) return false; // Si el otro obj con el comparo no es el otro obj una instancia de Cancion : False
	  //  Cancion unaCancion;
	    Album otherAlbum = (Album)other; // para hacer la comparacion de los objetos tengo que hacer CASTING - a la clase que quiero comparar
	    return Objects.equals(this.titulo,otherAlbum.titulo) &&
	    		Objects.equals(this.autor,otherAlbum.autor) &&
	    		Objects.equals(this.grupo, otherAlbum.grupo) &&
	    		Objects.equals(this.duracionTotal,otherAlbum.duracionTotal) &&
	    		Objects.equals(this.trackList,otherAlbum.trackList);
	}
	@Override
	public int hashCode(){
		return Objects.hash(titulo,autor,grupo, duracionTotal,trackList);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Dudas 
		Album album1 = new Album(" Album", "Ejemplo", "emsi");
		album1.addTrack(new Cancion("One song", "Another", 543, Codecs.OGG));
		album1.addTrack(new Cancion("shaky shaky", "Daddy yankee", 327, Codecs.MP3));
		
		// ----------- EXCEPCION --------------- //
		
		Cancion track1 = new Cancion("One song", "Another", 543, Codecs.OGG);
		Cancion track2 = new Cancion("One song 0 ", "Another 0", 532, Codecs.OGG);

		Album miAlbum = new Album();
		miAlbum.addTrack(2, track1);
		miAlbum.addTrack(0, track2);

		
		System.out.println(miAlbum);

		Album album2 = new Album(" Album", "Ejemplo", "emsi");
		album2.addTrack(new Cancion("One song", "Another", 543, Codecs.OGG));
		album2.addTrack(new Cancion("shaky shaky", "Daddy yankee", 327, Codecs.MP3));
		
		Album album3 = new Album(" El tercero", " El grupo 3", "cristina");
		album3.addTrack(new Cancion("Segunda Cancion", "Alguien", 273, Codecs.MP3));
		album3.addTrack (0,new Cancion("Primera Cancion", "Una persona", 512, Codecs.FLAC));
		album3.addTrack(new Cancion("la tercera","otra",421,Codecs.FLAC));
		
		
		System.out.println(album1.equals(album2));
		System.out.println((album1.hashCode()) == (album2.hashCode()));
	
		// Albumes completos

		System.out.println(album1);
		System.out.println(album2);
		System.out.println(album3);
		
		// borrar cancion 
		album1.deleteLastTrack();
		System.out.println("Borramos una cancion\n " + album1);
		
		album3.deleteTrack(0);
		System.out.println("Borramos la primera cancion\n " + album3);
		
		// Borramos todo el album
		
		album2.clearAlbum();
		System.out.println(album2);
		
	}

}

