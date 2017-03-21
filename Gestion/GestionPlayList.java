package urjc.ist.playlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import urjc.ist.playlist.Cancion.Codecs;


public class GestionPlayList {
	private   ArrayList<PlayList> gestion;

	public GestionPlayList() {

		gestion = new ArrayList<PlayList>();
	}

	//  ******************************* CREAR / MODIF / BORRAR PlayList ************************//

	public void mostrarPL(GestionPlayList g ){
		if(gestion.size()!= 0){
			System.out.println("Tienes estas PlayList:");
			int i = 1;
			try{
				for(PlayList pl: gestion){ // recorremos la gestion (arraylist<pl>)
					System.out.println("\n-----------    "
							+ i + " - "+ pl.getNombrePlayList() // solo mostramos el nombre
							+ "    -----------\n");
					i++;

				}	
			}catch(NullPointerException e){
				System.err.println(" Has salido del rango, inténtalo de nuevo ");
				menu(g);
			}
			
		}else{
			System.out.println("No tienes PlayList aun");
		}
	}

	public void crearPL(GestionPlayList g) {
		mostrarPL(g);
		System.out.print("¿Que nombre quieres poner a la PlayList? ");
		Scanner texto = new Scanner(System.in);
		String nombre = texto.nextLine();
		PlayList pl = new PlayList(nombre);
		System.out.println(pl.getNombrePlayList() + ": ha sido creada.");		
		gestion.add(pl);
		menu(g);
		texto.close(); // cerramos el scanner

	}

	public void modificarPL(GestionPlayList g) {
		mostrarPL(g);
		System.out.print("¿Cual quieres modificar?( Escriba el nombre de la playlist)");
		Scanner texto = new Scanner(System.in);
		String nombre = texto.nextLine();
		texto.close(); // cerramos el scanner del nombre de la pl
		Scanner in = new Scanner ( System.in );	
		System.out.println ( "1. Cambiar nombre de la PlayList    2. Borrar PlayList"
				+ "\n3. Modificar albumnes(Añadir/Borrar)");

		System.out.print ( "¿Qué quieres hacer?: " );
		switch ( in.nextInt() ) {
		case 1: //cambiar nombre
			modificarNombrePL(nombre);
			mostrarPL(g);
			menu(g);
		case 2: // borrar playlist
			borrarPL(nombre);
			mostrarPL(g);
			menu(g);
		case 3: // modificar los albumnes
			modificarAL(g);
			menu(g);
		default:
			System.err.println ( "Elige una opcion válida" );
			modificarPL(g);
		}
		in.close(); // cerramos scanner de las opciones
	}



	public void borrarPL(String nombre) {
		PlayList resultante = null;
		try{
			for(PlayList elem : gestion){
				if(elem.getNombrePlayList().equals(nombre)){
					resultante = elem;	
					break;

				}
			}
	
		}catch(NullPointerException e){
			System.err.println(" Has salido del rango, inténtalo de nuevo ");
		}
		resultante.clear();
		gestion.remove(resultante);
	}

	public void modificarNombrePL(String nombre) {

		PlayList resultante = null;
		try{
			for(PlayList elem : gestion){ // recorremos la gesion
				if(elem.getNombrePlayList().equals(nombre)){ // cuando el nombre sea el mismo
					resultante = elem;	
					break;

				}
			}
	
		}catch(NullPointerException e){
			System.err.println(" Has salido del rango, inténtalo de nuevo ");
			e.printStackTrace();
		}
		System.out.print("Escriba el nuevo nombre de la PlayList");
		Scanner texto = new Scanner(System.in);
		String nombre2 = texto.nextLine();
		resultante.setNombrePlayList(nombre2);
		texto.close();//cerramos scanner
	}


//	public void add(PlayList pl) {
//		gestion.add(pl);
//	}
//	
	// ****************** CREAR / MODIF / BORRAR ALBUM ******************** //

	public void mostrarAL(GestionPlayList g ){
		if(gestion.size()!= 0){
			System.out.println("Tienes estas PlayList:");
			int i = 1;
			try{
				for(PlayList pl: gestion){
					System.out.println("\n-----------\n"
							+ i + " -  Nombre PlayList : " + pl.getNombrePlayList());
					if(pl.getPlayList() != null){
						for(Album elem2 : pl.getPlayList()){
							int a = 1;
							System.out.println("	" + i + "."+ a + " -  Album : " + elem2.getTituloAlbum());
							a++;
						}
						System.out.println( "\n-----------\n");
						i++;	
					}
				}	
			}catch(NullPointerException e){
				System.err.println(" No tienes playList aun, crea una primero");
				crearPL(g);
			}

		}else{
			System.err.println(" No tienes playList aun, crea una primero");
			crearPL(g);
		}
	}

	public void crearAL(GestionPlayList g) {

		
		try{
			if(gestion.size() != 0){
				mostrarPL(g);
				System.out.println("Indique el nombre de la lista a la que quieres añadir el album");
				Scanner texto = new Scanner(System.in);
				PlayList resultanteAL = null;
				for(PlayList elem : gestion){
					if(elem.getNombrePlayList().equals(texto.nextLine())){
						resultanteAL = elem;
						try{
							System.out.print("Indique en orden el titulo, el autor y su grupo: ");
							
							Scanner album = new Scanner(System.in);
							String[] elAlbum = album.nextLine().split(" ");
							// creamos la cancion
							Album unAlbum = new Album(elAlbum[0],elAlbum[1],elAlbum[2]);
							System.out.println("El album ha sido añadido:\n" + unAlbum);		
							resultanteAL.addAlbum(unAlbum);
							menu(g);
							texto.close();
							album.close(); // cerramos el scanner del album
			
						}catch(ArrayIndexOutOfBoundsException e){
							System.err.print("Comrueba los datos : (titulo autor y grupo)");
							crearAL(g);
						}
					}
				}
				
				}else{
				System.err.println(" No tienes playlist, primero tienes que crear una playList");
				crearPL(g);
			}
		}catch(NullPointerException e){
			System.err.println(" No ha podido añadirse.");
			menu(g);
		}
	}

	public void modificarNombreAL(String nombre) {
		String answer; // nos saca el valor de la respuesta
		try{
			for(PlayList pl : gestion){
				try{
					for(Album al : pl.getPlayList()){
						if(al.getTituloAlbum().equals(nombre)){
							System.out.print("Escriba el nuevo titulo :");
							Scanner textoAL = new Scanner(System.in);
							String titulo = textoAL.nextLine();
							al.setTituloAlbum(titulo);
							textoAL.close();
							System.out.println("¿Quieres modificar el autor?[y/n]");
							Scanner yesno = new Scanner(System.in);
							while (true) {
								answer = yesno.nextLine().trim().toLowerCase();
								if (answer.equals("y")) { // modificamos el autor
									System.out.print("Escriba el nuevo autor :");
									Scanner autor = new Scanner(System.in);
									al.setAutorAlbum(autor.nextLine());
									autor.close(); // cerramos scanner autor
									break;
								} else if (answer.equals("n")) {
									System.out.println("No modificamos autor");
									break;
								} else {
									System.out.println("Opción inválida");						  
								}
							}
							yesno.close(); // cerramos el 1º de os yesno
						}
						System.out.println("¿Quieres modificar el grupo?[y/n]");
						Scanner yesno2 = new Scanner(System.in);
						while (true) {
							answer = yesno2.nextLine().trim().toLowerCase();
							if (answer.equals("y")) {
								System.out.print("Escriba el nuevo grupo :");
								Scanner grupo = new Scanner(System.in);
								al.setGrupo(grupo.nextLine());
								grupo.close(); // cerramos el scanner del grupo
								break;
							} else if (answer.equals("n")) {
								System.out.println("No modificamos grupo");
								break;
							} else {
								System.out.println("Opción inválida");						  
							}
						}
						yesno2.close(); // cerramos el scanner
						System.out.println(" El album : " + al.getTituloAlbum() + " " + " " + al.getAutorAlbum() + al.getGrupo()); 
					}
				}catch(NullPointerException e){
					e.getMessage();
				}
				
			}
		}catch(NullPointerException e){
			e.getMessage();
		}
		
	}

	public void modificarAL(GestionPlayList g) {
		mostrarAL(g);
		System.out.print("¿Cual quieres modificar?( Escriba el nombre del album) : ");
		Scanner textoALMod = new Scanner(System.in);
		String nombreALMod = textoALMod.nextLine();
		Scanner inALMod = new Scanner ( System.in );	
		System.out.println ( "1. Cambiar título    2. Borrar Album"
				+ "\n3. Añadir canciones    4. Modificar canciones");

		System.out.println ( "¿Qué quieres hacer? " );
		switch ( inALMod.nextInt() ) {
		case 1: //cambiar nombre / autor / grupo
			modificarNombreAL(nombreALMod);
			menu(g);
		case 2:
			borrarAL(nombreALMod);
			menu(g);
		case 3:
			crearCN(g);
			break;
		case 4:
			modificarCN(g);

		default:
			System.err.println ( "Elige una opcion válida" );
			modificarAL(g);
		}
		// cerramos los scanner para no tener problemas
		textoALMod.close();
		inALMod.close();
		
	}


	public void borrarAL(String nombreALMod) {
		PlayList resultante = null;
		Album resultanteAL = null;
		try{
			for(PlayList pl : gestion){
				if(pl.getNombrePlayList().equals(nombreALMod)){
					resultante = pl;	
					try{
						for(Album al : resultante.getPlayList()){
							if(al.getTituloAlbum().equals(nombreALMod)){
								resultanteAL = al;	
								break;
							}
						}
					}catch(NullPointerException e){
						System.err.println("No tienes albumnes, crea uno para poder borrarlo");
					}
				}
				resultanteAL.clearAlbum();
			}
	
		}catch(NullPointerException e ){
			System.err.println(" No tienes playList, crea una para poder borrarla");
		}
	}


	// ********** CREAR / MODIFICAR / BORRAR CANCIONES ************ //

	public void mostrarCN(GestionPlayList g ){
		if(gestion.size()!= 0){
			System.out.println("Tienes estas PlayList:");
			int i = 1;
			for(PlayList pl: gestion){
				System.out.println("\n-----------\n"
						+ i + " -  Nombre PlayList : " + pl.getNombrePlayList());
				if(pl.getPlayList()  != null){
					for(Album elem2 : pl.getPlayList()){
						int a = 1;
						System.out.println("	" + i + "."+ a + " -  Album : " + elem2.getTituloAlbum());
						a++;
						for(Cancion elem3 : elem2.getTrackList()){
							int b = 1;
							System.out.println("		" + i + "." + a +"."+ b +" - Canciones" + elem3.toString());
							b++;
						}
					}
					System.out.println( "\n-----------\n");
					i++;

				}
			}

		}else{
			System.err.println("No tienes PlayList aun, primero tienes que crearla");
			crearPL(g);
		}
	}
	public void crearCN(GestionPlayList g) {

		
			if(gestion.size() != 0){
				mostrarAL(g);
				System.out.println("Indique el nombre de la lista: ");
				Scanner texto = new Scanner(System.in);
				PlayList resultante = null;
				Album resultanteAL = null;
				for(PlayList elem : gestion){
					if(elem.getNombrePlayList().equals(texto.nextLine())){
						resultante = elem;
						try{
							if(resultante.getPlayList() != null){
								System.out.print("Nombre del album: ");
								Scanner al = new Scanner(System.in);
								for(Album elem2 : resultante.getPlayList()){
									if(elem2.getTituloAlbum().equals(al.nextLine())){
										resultanteAL = elem2;
										try{
											System.out.print("Indique en orden el titulo, el autor, la duracion y su formato (MP3/FLAC/OGG): ");
											Scanner cancion = new Scanner(System.in);
											String[] laCancion = cancion.nextLine().split(" ");
											// creamos la cancion
											Cancion unaCancion = new Cancion(laCancion[0],laCancion[1],Integer.parseInt(laCancion[2]),Codecs.valueOf(laCancion[3]));
											resultanteAL.addTrack(unaCancion);
											System.out.println("La cancion ha sido añadida.\n");
											mostrarCN(g);
											menu(g);
											cancion.close(); // cerramos scanner
											
										}catch(NumberFormatException | ArrayIndexOutOfBoundsException ex2){
											System.err.println(" Comprueba los datos : titulo Autor Duracion(en segundos) formato(MP3/FLAC/OGG) ");
											crearCN(g);
										}

									}
								}
								
								al.close();
							}
						}catch(NullPointerException e){
							
							System.err.println("No tienes albumnes, primero tienes que crear uno");
							crearAL(g);
	
	
						}
					}
				}
				texto.close();
			}else{
				System.err.println(" No tienes playlist, primero tienes que crear una playList");
				crearPL(g);
			}
	}
	
	public void modificarCN(GestionPlayList g) {
		mostrarCN(g);
		System.out.println("¿Cual quieres modificar?( Escriba el nombre de la canción)");
		Scanner textoCNMod = new Scanner(System.in);
		String nombreCNMod = textoCNMod.nextLine();
		Scanner inCNMod = new Scanner ( System.in );	
		System.out.println ( "1. Modificar datos    2. Borrar cancion");

		System.out.println ( "¿Qué quieres hacer? " );
		switch ( inCNMod.nextInt() ) {
		case 1: //cambiar nombre / autor / grupo
			modificarNombreCN(nombreCNMod);
			menu(g);
		case 2:
			borrarCN(nombreCNMod);
			menu(g);
		default:
			System.err.println ( "Elige una opcion válida" );
			modificarAL(g);
		}
		// cerramos los scanner de cancion
		textoCNMod.close();
		inCNMod.close();
	}
	public void modificarNombreCN(String nombreCNMod) {
		String answer;
		if (gestion.size() != 0){ // comprobamos que haya pl
			for(PlayList pl : gestion){
				if(pl.getPlayList() != null){ // comprobamos que haya al
					for(Album al : pl.getPlayList()){
						if(al.getTrackList() != null){ // comprobamos que tenga canciones
							for(Cancion cn : al.getTrackList()){
								if(cn.getTitulo().equals(nombreCNMod)){
									System.out.print("Escriba el nuevo titulo :");
									Scanner tituloCN = new Scanner(System.in);
									cn.setTitulo(tituloCN.nextLine());
									tituloCN.close(); // cerramos el scanner del titulo
									System.out.println("¿Quieres modificar el autor?[y/n]");
									Scanner yesno = new Scanner(System.in);
									while (true) {
										answer = yesno.nextLine().trim().toLowerCase();
										if (answer.equals("y")) {
											System.out.print("Escriba el nuevo autor :");
											Scanner autor = new Scanner(System.in);
											al.setAutorAlbum(autor.nextLine());
											autor.close(); // cerramos el scanner
											break;
										} else if (answer.equals("n")) {
											System.out.println("No modificamos autor");
											break;
										} else {
											System.out.println("Opción inválida");						  
										}
									}
									yesno.close(); // cerramos el scanner y/n
									System.out.print("¿Quieres modificar la duracion?[y/n] : ");
									Scanner yesno2 = new Scanner(System.in);
									while (true) {
										answer = yesno2.nextLine().trim().toLowerCase();
										if (answer.equals("y")) {
											System.out.print("Escriba la duracion (en numeros) : ");
											Scanner duracion = new Scanner(System.in);
											cn.setDuracion(Integer.parseInt(duracion.nextLine()));
											duracion.close(); // cerramos el scanner
											break;
										} else if (answer.equals("n")) {
											System.out.println("No modificamos la duracion");
											break;
										} else {
											System.out.println("Opción inválida");						  
										}
									}
									yesno2.close();
									System.out.print("¿Quieres modificar el formato?[y/n]");
									Scanner yesno3 = new Scanner(System.in);
									while (true) {
										answer = yesno3.nextLine().trim().toLowerCase();
										if (answer.equals("y")) {
											System.out.print("Escriba el nuevo formato [ MP3 / FLAC / OGG ]:");
											Scanner formato = new Scanner(System.in);
											cn.setFormato(Codecs.valueOf(formato.nextLine()));
											formato.close(); // cerramos scanenr
											break;
										} else if (answer.equals("n")) {
											System.out.println("No modificamos su formato");
											break;
										} else {
											System.out.println("Opción inválida");						  
										}
									}
									yesno3.close(); // cerramos el scanner
								}
							}
						}else{
							System.err.println(" El album no tiene canciones, primero tienes que crearla.");
						} // que el al tenga canciones
					}
				}else{
					System.err.println("La PlayList no tiene album, primero tienes que crear un album.");
					//no hay album
				}
					
			}
					
		}else{
			System.err.println(" No hay playList, primero tienes que crearla.");
			//no pl 
		}
	}

	public void borrarCN(String nombreCNMod) {
		Album resultanteAL = null;
		int posicion = 0;
		try{
			for(PlayList pl : gestion){	
				try{
					for(Album al : pl.getPlayList()){
						try{
							for (Cancion cn : al.getTrackList()){
								int i = 0;
								if(cn.getTitulo().equals(nombreCNMod))
								{
									resultanteAL = al;
									posicion = i;
								}
								i++;
							}
						}catch(NullPointerException e){
							e.getMessage();
						}
					}	
				}catch(NullPointerException ex){
					ex.getMessage();
				}
				
			}
			resultanteAL.deleteTrack(posicion);
	
		}catch(NullPointerException e){
			e.getMessage();
		}
			}


	public void menu(GestionPlayList g) {
		Scanner in = new Scanner ( System.in );	
		System.out.println ( "1. Crear PlayList	2. Modificar PlayList	3. Borrar PlayList"
				+ "\n4. Crear Album		5. Modificar Album	6. Borrar Album"
				+ "\n7. Crear Cancion	8. Modificar Cancion	9. Borrar Cancion"
				+ "\n10. Terminar " );

		System.out.println ( "¿Qué quieres hacer?: " );
		switch ( in.nextInt() ) {
		// PL -- playList
		case 1:
			crearPL(g);
			break;
		case 2:
			modificarPL(g);
			break;
		case 3:
			mostrarPL(g);
			System.out.print("¿Cual quieres modificar?( Escriba el nombre de la playlist) : ");
			Scanner texto = new Scanner(System.in);
			String nombre = texto.nextLine();
			borrarPL(nombre);
			menu(g);
			break;
			//AL -- album	
		case 4:
			crearAL(g);
			break;
		case 5:
			modificarAL(g);
			break;
		case 6:
			mostrarAL(g);
			System.out.print("¿Cual quieres modificar?( Escriba el nombre del Album) : ");
			Scanner texto2 = new Scanner(System.in);
			String nombre2 = texto2.nextLine();
			borrarAL(nombre2);
			break;
			// CN -- cancion
		case 7:
			crearCN(g);
			break;
		case 8 : 
			modificarCN(g);
			break;
		case 9 : 
			mostrarCN(g);
			System.out.println("¿Cual quieres modificar?( Escriba la posicion de la cancion)");
			Scanner texto3 = new Scanner(System.in);
			String nombre3 = texto3.nextLine();
			borrarCN(nombre3);
		default:
			System.err.println ( "Unrecognized option" );
			break;
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestionPlayList g = new GestionPlayList();
		g.menu(g);
	}

}
