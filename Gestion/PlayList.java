package urjc.ist.playlist;

import java.util.ArrayList;
import java.util.Objects;
import urjc.ist.playlist.Cancion.Codecs;

public class PlayList {
	private String nombre;
	private int numCanciones;
	private int numAlbumes;
	private int duracionTotalPlaylist;
	private ArrayList<Album> playList;
	
	// ------------- CONSTRUCTORES -----------//
	
	public PlayList() {
		// TODO Auto-generated constructor stub
		nombre = "";
		numCanciones = 0;
		numAlbumes = 0;
		duracionTotalPlaylist = getDuracionTotalPlayList();
		playList = new ArrayList<Album>();
	}
	
	public PlayList(String nombre) { // constructor especializado
		this.nombre = nombre;
		numCanciones = 0;
		numAlbumes = 0;
		duracionTotalPlaylist = 0;
		playList = new ArrayList<Album>();
	}
	
	// TODO Añadir métodos de obtención y asignación de valores
	
	// ----------- MÉTODOS -----------//
	//--- NOMBRE ---//
	public String getNombrePlayList(){
		return nombre;
	}
	
	public void setNombrePlayList(String nombre){
		
		this.nombre = nombre;
	}
	
	//--- ALBUM ---//

	
	public int getNumAlbumes(ArrayList<Album> playList){
		numAlbumes = playList.size();
		return numAlbumes;
	}
	
	//--- CANCIONES ---//


	public int getNumCanciones(Album unAlbum){
		int numCancionesAlbum = 0;
		if (playList.size() > 1){
			numCancionesAlbum = unAlbum.getCanciones();			
			numCanciones = numCancionesAlbum + numCanciones;
		}else{
			 numCanciones = unAlbum.getCanciones();
		}
		
		return numCanciones;
	}
	
	//--- DURACION TOTAL ---//

	public int getDuracionTotalPlayList() {
		for(Album elem : playList){
			duracionTotalPlaylist = duracionTotalPlaylist + elem.getDuracionTotal();
			
		}
		
		return duracionTotalPlaylist;
	}
	
	public ArrayList<Album> getPlayList() {
		/**
		 * Método que devuelve la lista de Albumes actualmente
		 * incluídas en el álbum
		 */
			
				return playList;
			
	}
	
	//--- AÑADIR ALBUM SIN POSICION---//

	public void addAlbum(Album unAlbum) {
		/**
		 * Método que añade un album a la lista. 
		 * Además, el método calcula y actualiza
		 * automáticamente la nueva duración total 
		 */
		playList.add(unAlbum);
		numAlbumes = getNumAlbumes(playList);
		duracionTotalPlaylist = duracionTotalPlaylist + unAlbum.getDuracionTotal();
		numCanciones = getNumCanciones(unAlbum);
		// Añade el album al final
	}
	
	//--- AÑADIR ALBUM CON POSICION---//

	public void addAlbum(int posicion, Album unAlbum) {
		/**
		 * Método que añade un álbum(que pasamos
		 * como segundo argumento) en la posición que indique
		 * el int que recibe como primer argumento
		 */
				
		if(posicion < playList.size()){
			playList.add(posicion, unAlbum);
			duracionTotalPlaylist = duracionTotalPlaylist + unAlbum.getDuracionTotal();
			numCanciones = getNumCanciones(unAlbum);
			numAlbumes = getNumAlbumes(playList);
			
	    }else{
	    	addAlbum(unAlbum); // si damos un valor erroneo la añade al final

	    }
		
	}
	
	//--- BORRAR ALBUM SIN POSICION---//

	public void removeAlbum() {
		/**
		 * Método que borra el ultimo album de la lista
		 */
		 
		
		if(playList.size() == 1){ // solo un album en la playList
			clear();
			
		}else{
			Album AlbumRemv = (playList.get(playList.size()-1));
			duracionTotalPlaylist = duracionTotalPlaylist - AlbumRemv.getDuracionTotal();
			numCanciones = numCanciones - AlbumRemv.getCanciones();
			playList.remove(AlbumRemv); // (playList.size()-1)
			numAlbumes = playList.size();

		}
	}
	
	//--- BORRAR ALBUM CON POSICION---//

	public void removeAlbum(int posicion) {
		/**
		 * Método que borra la canción en la posición de la lista
		 * que indica el argumento que recibe
		 */
		
		if(playList.size() == 1){
			clear();
		}else{
			if (posicion < playList.size()){
				
				Album AlbumRemv = playList.get(posicion);
				duracionTotalPlaylist = duracionTotalPlaylist - AlbumRemv.getDuracionTotal();
				numCanciones = numCanciones - AlbumRemv.getCanciones();
				playList.remove(posicion);	
				numAlbumes = playList.size();
				
			}
		
		}
	}
	
	//--- BORRAR ALBUM COMPLETO ---//

	public void clear() {
		/**
		 * Método que borra todas las canciones en la lista de
		 * un álbum
		 */
		System.out.println("Has borrado la lista de reproduccion : " + getNombrePlayList());
		duracionTotalPlaylist = 0;
		numCanciones = 0;
		numAlbumes = 0;
		playList.clear();
	}
	
	
	//--- METODO TOSTRING ---//
	
	@Override
	public String toString() {
		for (Album elem: playList){
			numCanciones = numCanciones + elem.getCanciones();
		}
		int duracionLista = getDuracionTotalPlayList();
		int hours = duracionLista / 3600;
		int minutes = (duracionLista % 3600) / 60;
		int seconds = duracionLista % 60;

		String timeString;
		if (hours > 0) {
			timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds); //%02d : int de dos cifras 
		} else {
			timeString = String.format("%02d:%02d", minutes, seconds);
		}

		return String.join("\n", ".-.-.-.-.-.-.",
				"Nombre Playlist: " + nombre,
				"Canciones: " + numCanciones,
				"Albumes: " + numAlbumes,
				"Duración Lista: " + timeString,
				
				"Albumes:\n"+ this.getPlayList(),
				".-.-.-.-.-.-.");
		
	}
	
	//--- METODO EQUALS ---//

	@Override
	public boolean equals(Object other) { 
		/**
		 * Implementación de un método de comparación del contenido
		 * de dos album
		 */
		
		if (other == null) return false; 
	    if (other == this) return true;  
	    if (!(other instanceof PlayList)) return false; 
	
	    PlayList otherPlayList = (PlayList)other; // para hacer la comparacion de los objetos tengo que hacer CASTING - a la clase que quiero comparar
	    return  Objects.equals(this.nombre,otherPlayList.nombre) &&
	    		Objects.equals(this.numCanciones,otherPlayList.numCanciones) &&
	    		Objects.equals(this.numAlbumes, otherPlayList.numAlbumes) &&
	    		Objects.equals(this.duracionTotalPlaylist,otherPlayList.duracionTotalPlaylist) &&
	    		Objects.equals(this.playList,otherPlayList.playList);
	}
	    	
	//--- METODO HASHCODE ---//

	@Override
	public int hashCode(){
		return Objects.hash(nombre,numCanciones,numAlbumes, duracionTotalPlaylist,playList);
	}
	
	//--- MAIN ---//

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		PlayList pl = new PlayList("pl");
//		Album al = new Album();
//		
//		System.out.println(pl);
		
		PlayList playList1 = new PlayList("myList"); // uso de equals y hashCode
		PlayList playList2 = new PlayList("myList"); // uso de equals y hashCode
		PlayList playList3 = new PlayList("my SecondList"); // para borrar
		
		Album album3 = new Album(" El tercero", " El grupo 3", "cristina");
		album3.addTrack(new Cancion("Segunda Cancion", "Alguien", 273, Codecs.MP3));
		album3.addTrack (0,new Cancion("Primera Cancion", "Una persona", 512, Codecs.FLAC));
		album3.addTrack(new Cancion("la tercera","otra",421,Codecs.FLAC));
		
		Album album1 = new Album("primero", "Ejemplo", "emsi");
		album1.addTrack(new Cancion("One song", "Another", 543, Codecs.OGG));
		album1.addTrack(new Cancion("shaky shaky", "Daddy yankee", 327, Codecs.MP3));

		Album album2 = new Album(new String(" primero"), "Ejemplo", "emsi");
		album2.addTrack(new Cancion(new String("One song"), "Another", 543, Codecs.OGG));
		album2.addTrack(new Cancion("shaky shaky", "Daddy yankee", 327, Codecs.MP3));
		
		// Añadir Albumes a listas de reproduccion --------------------------------------------------------------

		playList1.addAlbum(album2);
		playList1.addAlbum(0, album1);
		
		playList2.addAlbum(1,album2);
		playList2.addAlbum(0,album1);
		
		playList3.addAlbum(album3);
		playList3.addAlbum(album2);
		playList3.addAlbum(0, album1);
		
		// Equals y hashCode list1 y list2 -------------------------------------------------------------------------
		
		System.out.println("¿son iguales " + playList1.nombre + " y " + playList2.nombre + "? (equals) : " + playList1.equals(playList2));	
		System.out.println("y..¿ son iguales? (hashCode) : " + ((playList1.hashCode()) == (playList2.hashCode())));

		// Equals y hashCode list1 y list3 -------------------------------------------------------------------------
		
		System.out.println("¿son iguales " + playList1.nombre + " y " + playList3.nombre + "? (equals) : " + playList1.equals(playList3));	
		System.out.println("y..¿ son iguales? (hashCode) : " + ((playList1.hashCode()) == (playList3.hashCode())));
		
		
		// Lista3 para comprobar programa --------------------------------------------------------------------------
		
		System.out.println("La lista completa\n" + playList3);
		
		// Borramos albumes de las lista3 [diferentes metodos remv(pos) o remv()]------------------------------------
		
		playList3.removeAlbum(0);// album3 y album2
		System.out.println("Borramos un album de la lista\n" + playList3); // album3 y album2
		
		playList3.removeAlbum(); // album1 
		System.out.println("Borramos otro album de la lista\n" + playList3);
		
		playList3.removeAlbum(); // no hay playList3, 0 album 
		System.out.println("Borramos todos los albumes de la lista\n" + playList3);
		
		// Borramos la playList por ejemplo PlayList1 --------------------------------------------------------------
	
		playList1.clear();
		System.out.println("Borramos una lista completa\n" + playList1);
	
	}

}
