package urjc.ist.playlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import urjc.ist.playlist.Cancion.Codecs;



public class GestionPlayList implements java.io.Serializable{
	private   ArrayList<PlayList> gestion;

	public GestionPlayList() {
		gestion = new ArrayList<PlayList>();
	}

	//  ******************************* CREAR / MODIF / BORRAR PlayList ************************//

	private void mostrarPL(GestionPlayList g ){
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

	private void crearPL(GestionPlayList g) {
		mostrarPL(g);
		System.out.print("¿Que nombre quieres poner a la PlayList? ");
		Scanner texto = new Scanner(System.in);
		String nombre = texto.nextLine();
		PlayList pl = new PlayList(nombre);
		System.out.println(pl.getNombrePlayList() + ": ha sido creada.");		
		gestion.add(pl);
		menu(g);
	}

	private void modificarPL(GestionPlayList g) {
		mostrarPL(g);
		System.out.print("¿Cual quieres modificar?( Escriba el nombre de la playlist)");
		Scanner texto = new Scanner(System.in);
		String nombre = texto.nextLine();
		//texto.close(); // cerramos el scanner del nombre de la pl
		Scanner inPL = new Scanner ( System.in );	
		System.out.println ( "1. Cambiar nombre de la PlayList    2. Borrar PlayList"
				+ "\n3. Modificar albumnes(Añadir/Borrar)");

		System.out.print ( "¿Qué quieres hacer?: " );
		switch ( inPL.nextInt() ) {
		case 1: //cambiar nombre
			modificarNombrePL(nombre);
			mostrarPL(g);
			g.menu(g);
			break;

		case 2: // borrar playlist
			borrarPL(nombre);
			mostrarPL(g);
			g.menu(g);

			break;

		case 3: // modificar los albumnes
			modificarAL(g);
			g.menu(g);

			break;
		default:
			System.err.println ( "Elige una opcion válida" );
			modificarPL(g);
		}
		//inPL.close(); // cerramos scanner de las opciones
		//texto.close();
	}

	private void borrarPL(String nombre) {

		PlayList resultante = null;
		try{
			for(PlayList elem : gestion){
				if(elem.getNombrePlayList().equals(nombre)){
					resultante = elem;	
					resultante.clear();
					gestion.remove(resultante);
					break;
				}
			}
		}catch(NullPointerException e){
			System.err.println(" Has salido del rango, inténtalo de nuevo ");
		}
	}

	private void modificarNombrePL(String nombre) {

		PlayList resultante;
		try{
			for(PlayList elem : gestion){ // recorremos la gesion
				if(elem.getNombrePlayList().equals(nombre)){ // cuando el nombre sea el mismo
					resultante = elem;	
					System.out.print("Escriba el nuevo nombre de la PlayList");
					Scanner texto = new Scanner(System.in);
					String nombre2 = texto.nextLine();
					resultante.setNombrePlayList(nombre2);
					//texto.close();//cerramos scanner
				}
			}
		}catch(NullPointerException e){
			System.err.println(" Has salido del rango, inténtalo de nuevo ");
		}

	}

	// ****************** CREAR / MODIF / BORRAR ALBUM ******************** //

	private void mostrarAL(GestionPlayList g ){
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

	private void crearAL(GestionPlayList g) {

		try{
			if(gestion.size() != 0){
				mostrarPL(g);
				System.out.println("Indique el nombre de la lista a la que quieres añadir el album");
				Scanner texto = new Scanner(System.in);
				String nombrepl = texto.nextLine();
				for(PlayList elem : gestion){
					PlayList resultanteAL = null;
					if(elem.getNombrePlayList().equals(nombrepl)){
						resultanteAL = elem;
				try{
							//System.out.println(" tengo que añadir a " + resultanteAL);
							System.out.print("Indique en orden el titulo, el autor y su grupo: ");

							Scanner album = new Scanner(System.in);
							String[] elAlbum = album.nextLine().split(" ");
							// creamos la cancion

							Album unAlbum = new Album(elAlbum[0],elAlbum[1],elAlbum[2]);
							System.out.println("El album ha sido añadido:\n" + unAlbum);		
							resultanteAL.addAlbum(unAlbum);
							menu(g);
							album.close();	
						}catch(ArrayIndexOutOfBoundsException e){
							System.err.print("Comrueba los datos : (titulo autor y grupo)");
							crearAL(g);
						}
					}
				}
				//texto.close();
			}else{
				System.err.println(" No tienes playlist, primero tienes que crear una playList");
				crearPL(g);
			}
		}catch(NullPointerException e){
			System.err.println(" No ha podido añadirse.");
			menu(g);
		}
	}

	private void modificarNombreAL(String nombre) {
		String answer; // nos saca el valor de la respuesta
		try{
			for(PlayList pl : gestion){
				if(pl.getNombrePlayList().equals(nombre)){
					System.out.print("Escriba el album : ");
					Scanner alMod = new Scanner(System.in);
					String nombre2 = alMod.nextLine();
					try{
						for(Album al : pl.getPlayList()){
							if(al.getTituloAlbum().equals(nombre2)){
								System.out.print("Escriba el nuevo titulo :");
								Scanner textoAL = new Scanner(System.in);
								String titulo = textoAL.nextLine();
								al.setTituloAlbum(titulo);
								//textoAL.close();
								System.out.println("¿Quieres modificar el autor?[y/n]");
								Scanner yesno = new Scanner(System.in);
								while (true) {
									answer = yesno.nextLine().trim().toLowerCase();
									if (answer.equals("y")) { // modificamos el autor
										System.out.print("Escriba el nuevo autor :");
										Scanner autor = new Scanner(System.in);
										al.setAutorAlbum(autor.nextLine());
										//autor.close(); // cerramos scanner autor
										break;
									} else if (answer.equals("n")) {
										System.out.println("No modificamos autor");
										break;
									} else {
										System.out.println("Opción inválida");						  
									}
								}
								//yesno.close(); // cerramos el 1º de os yesno
							}
							System.out.println("¿Quieres modificar el grupo?[y/n]");
							Scanner yesno2 = new Scanner(System.in);
							while (true) {
								answer = yesno2.nextLine().trim().toLowerCase();
								if (answer.equals("y")) {
									System.out.print("Escriba el nuevo grupo :");
									Scanner grupo = new Scanner(System.in);
									al.setGrupo(grupo.nextLine());
									//grupo.close(); // cerramos el scanner del grupo
									break;
								} else if (answer.equals("n")) {
									System.out.println("No modificamos grupo");
									break;
								} else {
									System.out.println("Opción inválida");						  
								}
							}
							//yesno2.close(); // cerramos el scanner
							System.out.println(" El album modificado: " + al.getTituloAlbum() + " "  + al.getAutorAlbum() + " "+ al.getGrupo()); 
						}
					}catch(NullPointerException e){
						e.getMessage();
					}
				}
			}
		}catch(NullPointerException e){
			e.getMessage();
		}
	}

	private void modificarAL(GestionPlayList g) {
		mostrarAL(g);
		System.out.print("¿Cual quieres modificar?( Escriba el nombre de la playlist del album) : ");
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
		// cerramos los scanner 
		//		textoALMod.close();
		//		inALMod.close();
	}

	private void borrarAL(String nombre) {
		PlayList resultante = null;
		Album resultanteAL = null;
		try{
			for(PlayList pl : gestion){
				if(pl.getNombrePlayList().equals(nombre)){
					resultante = pl;	
					try{
						int pos = 0;
						System.out.print("Escribia el album que quieres borrar: ");
						Scanner album = new Scanner(System.in);
						String nombreALMod = album.nextLine();
						for(Album al : resultante.getPlayList()){

							if(al.getTituloAlbum().equals(nombreALMod)){
								//System.out.println(pos);
								resultanteAL = al;
								resultanteAL.clearAlbum();
								resultante.removeAlbum(pos);
								break;
							}
							pos++;
						}
						//album.close();
					}catch(NullPointerException e){
						System.err.println("No tienes albumnes, crea uno para poder borrarlo");
					}
				}
			}

		}catch(NullPointerException e ){
			System.err.println(" No tienes playList, crea una para poder borrarla");
		}
	}

	// ********** CREAR / MODIFICAR / BORRAR CANCIONES ************ //

	private void mostrarCN(GestionPlayList g ){
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
							System.out.println("		" + i + "." + a +"."+ b +" - Cancion: " + elem3.toString());
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

	private void crearCN(GestionPlayList g) {

		if(gestion.size() != 0){
			mostrarAL(g);
			System.out.println("Indique el nombre de la lista: ");
			Scanner texto = new Scanner(System.in);
			String nombrepl = texto.nextLine();
			PlayList resultante = null;
			Album resultanteAL = null;
			for(PlayList elem : gestion){
				if(elem.getNombrePlayList().equals(nombrepl)){
					resultante = elem;
					try{
						if(resultante.getPlayList().size() != 0){
							System.out.print("Nombre del album: ");
							Scanner al = new Scanner(System.in);
							String nombreal = al.nextLine();
							for(Album elem2 : resultante.getPlayList()){
								if(elem2.getTituloAlbum().equals(nombreal)){
									resultanteAL = elem2;
									try{
										System.out.println("tengo que añadir en" + resultanteAL );
										System.out.print("Indique en orden el titulo, el autor, la duracion y su formato (MP3/FLAC/OGG): ");
										Scanner cancion = new Scanner(System.in);
										String[] laCancion = cancion.nextLine().split(" ");
										// creamos la cancion
										try{
											Cancion unaCancion = new Cancion(laCancion[0],laCancion[1],Integer.parseInt(laCancion[2]),Codecs.valueOf(laCancion[3]));
											resultanteAL.addTrack(unaCancion);

										}catch(IllegalArgumentException ex){
											System.err.println(" Comprueba los datos : titulo Autor Duracion(en segundos) formato(MP3/FLAC/OGG) ");
											crearCN(g);

										}
										System.out.println("La cancion ha sido añadida.\n");
										mostrarCN(g);
										menu(g);
										//cancion.close(); // cerramos scanner

									}catch( NumberFormatException | ArrayIndexOutOfBoundsException ex2){
										System.err.println(" Comprueba los datos : titulo Autor Duracion(en segundos) formato(MP3/FLAC/OGG) ");
										crearCN(g);
									}
								}else{
									System.err.println("No tienes ese album, el nombre debe ir sin espacios");
									crearCN(g);
								}
							}
							//al.close();
						}else{
							System.err.println(" No tienes albumnes, primero tienes que crear uno");
							crearAL(g);
						}
					}catch(NoSuchElementException  e){
						System.err.println("No tienes ese album, el nombre debe ir sin espacios");
						crearCN(g);
					}
				}
			}
			//texto.close();
		}else{
			System.err.println(" No tienes playlist, primero tienes que crear una playList");
			crearPL(g);
		}
	}

	private void modificarCN(GestionPlayList g) {
		mostrarCN(g);
		System.out.println("¿Cual quieres modificar?( Escriba el nombre de playlist de la cancion)");
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
		//textoCNMod.close();
		//inCNMod.close();
	}

	private void modificarNombreCN(String nombre) {
		String answer;
		if (gestion.size() != 0){ // comprobamos que haya pl
			for(PlayList pl : gestion){	
				if(pl.getNombrePlayList().equals(nombre)){
					System.out.print("Escribia el album : ");
					Scanner album = new Scanner(System.in);
					String nombreALMod = album.nextLine();
					try{
						for(Album al : pl.getPlayList()){
							if(al.getTituloAlbum().equals(nombreALMod)){
								System.out.print("Escribia de la cancion : ");
								Scanner can = new Scanner(System.in);
								String nombreCNMod = can.nextLine();

								try{
									for (Cancion cn : al.getTrackList()){
										if(cn.getTitulo().equals(nombreCNMod)){
											System.out.print("Escriba el nuevo titulo :");
											Scanner tituloCN = new Scanner(System.in);
											cn.setTitulo(tituloCN.nextLine());
											//tituloCN.close(); // cerramos el scanner del titulo
											System.out.println("¿Quieres modificar el autor?[y/n]");
											Scanner yesno = new Scanner(System.in);
											while (true) {
												answer = yesno.nextLine().trim().toLowerCase();
												if (answer.equals("y")) {
													System.out.print("Escriba el nuevo autor :");
													Scanner autor = new Scanner(System.in);
													al.setAutorAlbum(autor.nextLine());
													//autor.close(); // cerramos el scanner
													break;
												} else if (answer.equals("n")) {
													System.out.println("No modificamos autor");
													break;
												} else {
													System.out.println("Opción inválida");						  
												}
											}
											//yesno.close(); // cerramos el scanner y/n
											System.out.print("¿Quieres modificar la duracion?[y/n] : ");
											Scanner yesno2 = new Scanner(System.in);
											while (true) {
												answer = yesno2.nextLine().trim().toLowerCase();
												if (answer.equals("y")) {
													System.out.print("Escriba la duracion (en numeros) : ");
													Scanner duracion = new Scanner(System.in);
													cn.setDuracion(Integer.parseInt(duracion.nextLine()));
													//duracion.close(); // cerramos el scanner
													break;
												} else if (answer.equals("n")) {
													System.out.println("No modificamos la duracion");
													break;
												} else {
													System.out.println("Opción inválida");						  
												}
											}
											//yesno2.close();
											System.out.print("¿Quieres modificar el formato?[y/n]");
											Scanner yesno3 = new Scanner(System.in);
											while (true) {
												answer = yesno3.nextLine().trim().toLowerCase();
												if (answer.equals("y")) {
													System.out.print("Escriba el nuevo formato [ MP3 / FLAC / OGG ]:");
													Scanner formato = new Scanner(System.in);
													cn.setFormato(Codecs.valueOf(formato.nextLine()));
													//formato.close(); // cerramos scanenr
													break;
												} else if (answer.equals("n")) {
													System.out.println("No modificamos su formato");
													break;
												} else {
													System.out.println("Opción inválida");						  
												}

											}
										}

									}//yesno3.close(); // cerramos el scanner
								}catch(NullPointerException e){
									System.err.println("No hay canciones, primero tendrias que crearla");
									continue;
								}
							}else{
								System.err.println("No existe ese album");
								continue;
							}
						}
					}catch(NullPointerException ex){
						System.err.println("No hay album, primero tendrias que crearlo");
						continue;
					}
				}else{
					System.err.println("No existe esa playlist");
					continue;
				}
			}
		}
	}

	private void borrarCN(String nombre) {
		try{
			for(PlayList pl : gestion){	
				if(pl.getNombrePlayList().equals(nombre)){
					try{
						System.out.print("Escriba el album : ");
						Scanner album = new Scanner(System.in);
						String nombreALBorr = album.nextLine();
						for(Album al : pl.getPlayList()){
							if(al.getTituloAlbum().equals(nombreALBorr)){
								System.out.print("Escriba la cancion : ");
								Scanner can = new Scanner(System.in);
								String nombreCNBor = can.nextLine();
								try{
									int i = 0;
									for (Cancion cn : al.getTrackList()){
										if(cn.getTitulo().equals(nombreCNBor)){

											System.out.println(i);
											al.deleteTrack(i);
											break;
										}else{
											System.err.println("No existe esa cancion");

										}

										i++;
									}
								}catch(NullPointerException e){
									System.err.println("No hay canciones, primero tendrias que crearla");
									continue;
								}
							}else{
								System.err.println("No existe ese album");
								continue;
							}
						}
					}catch(NullPointerException ex){
						System.err.println("No hay album, primero tendrias que crearlo");
						continue;
					}
				}else{
					System.err.println("No existe esa playlist");
					continue;
				}
			}

		}catch(NullPointerException e){
			System.err.println("No hay playlist, primero tendrias que crearla");
		}
	}


	public void menu(GestionPlayList g) {

		System.out.println ( "1. Crear PlayList	2. Modificar PlayList	3. Borrar PlayList"
				+ "\n4. Crear Album		5. Modificar Album	6. Borrar Album"
				+ "\n7. Crear Cancion	8. Modificar Cancion	9. Borrar Cancion"
				+ "\n10. Mostrar gestion	11. Guardar y terminar	12. Cargar gestion guardada" );

		System.out.println ( "¿Qué quieres hacer?: " );
		Scanner inMenu = new Scanner ( System.in );	
		int menu = inMenu.nextInt();
		
			switch (menu) {
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
				System.out.print("¿Cual quieres modificar?( Escriba el nombre de la playlist) : ");
				Scanner texto2 = new Scanner(System.in);
				String nombre2 = texto2.nextLine();
				borrarAL(nombre2);
				menu(g);
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
				System.out.println("¿Cual quieres modificar?(  Escriba el nombre de la playlist): ");
				Scanner texto3 = new Scanner(System.in);
				String nombre3 = texto3.nextLine();
				borrarCN(nombre3);
				menu(g);
				break;
			case 10:
				mostrarG(g);
				menu(g);
				break;
			case 11:
				try {
					guardar(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Terminado.");
				break;
			case 12:
				cargar();
				break;
	
			default:
				System.err.println ( "Unrecognized option" );
				break;
			}
			//inMenu.close();
		
	}



	private void cargar()  {
	
			File archivo = new File ("Gestion/gestion.txt"); 
			GestionPlayList lista = new GestionPlayList();
	        try{
		        ObjectInputStream ficheroEntrada = new ObjectInputStream(new FileInputStream(archivo));
	            lista = (GestionPlayList)ficheroEntrada.readObject();
	            System.out.println(lista.mostrarG(lista)); 
                lista.menu(lista);
                ficheroEntrada.close();
                
	        }catch(ClassNotFoundException | FileNotFoundException fnfe) {
	           System.err.println("Fallo al cargar.");
	        } catch (IOException ioe){
	        	System.err.println("IO");
	        }	
	}

	private final void guardar(GestionPlayList g) throws IOException {
		File carpetaGestion =new File("Gestion"); // Crea la carpeta donde iran las otras carpetas
		carpetaGestion.mkdir();
		if (!gestion.isEmpty()) {
    			File archivo = new File (carpetaGestion+"/gestion.txt");
                ObjectOutputStream ficheroSalida = new ObjectOutputStream(new FileOutputStream(archivo));
                ficheroSalida.writeObject((GestionPlayList)g);
                ficheroSalida.flush();
                ficheroSalida.close();
                System.out.println("Datos guardados correctamente en " + archivo + ".");
        } else {
             System.out.println("No hay datos que guardar. La lista está vacía. ");
        }
		for(PlayList pl : gestion){
			File dirPL=new File(carpetaGestion + "/" + pl.getNombrePlayList());
			dirPL.mkdir();
			Path archPL = Paths.get(carpetaGestion + "/" + pl.getNombrePlayList()+"/"+ pl.getNombrePlayList()+".txt");
			try {
				Files.createFile(archPL);
				byte[] buf =("PlayList :\n" + pl.toString()).getBytes();
				Files.write(archPL, buf);
			} catch (FileAlreadyExistsException x) {
				System.err.format("Error : el fichero ya existe. Sera borrado y guardado de nuevo\n");
				try {
					Files.delete(archPL);
					guardar(g);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException x) {
				System.err.format("Error de permisos sobre el fichero");
			}
			for(Album al : pl.getPlayList()){
				File dirAL =new File(dirPL+"/"+ al.getTituloAlbum());
				dirAL.mkdir();
				Path archAL = Paths.get(dirPL + "/" + al.getTituloAlbum()+"/"+ al.getTituloAlbum()+".txt");
				try {
					Files.createFile(archAL);
					
					byte[] buf =("PlayList : " + pl.getNombrePlayList() +"\n"   
								+ al.toString()).getBytes();
					
					Files.write(archAL, buf);
				} catch (FileAlreadyExistsException x) {
					System.err.format("Error : el fichero ya existe. Sera borrado y guardado de nuevo\n");
					try {
						Files.delete(archAL);
						guardar(g);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (IOException x) {
					System.err.format("Error de permisos sobre el fichero");
				}
				for (Cancion cn : al.getTrackList()){
					File dirCN =new File( dirAL + "/"+cn.getTitulo());
					dirCN.mkdir();
					Path archCN = Paths.get(dirAL + "/" + cn.getTitulo()+"/"+ cn.getTitulo()+".txt");
					try {
						Files.createFile(archCN);
						byte[] buf =("PlayList : " + pl.getNombrePlayList()  
									+ "\nAlbum : " + al.getTituloAlbum()
									+ cn.toString()).getBytes();
						Files.write(archCN, buf);
					} catch (FileAlreadyExistsException x) {
						System.err.format("Error : el fichero ya existe. Sera borrado y guardado de nuevo\n");
						try {
							Files.delete(archCN);
							guardar(g);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (IOException x) {
						System.err.format("Error de permisos sobre el fichero");
					}
				}
			}
		}
	}
    
	private String mostrarG(GestionPlayList g) {
		String laGestion = "";
		if(gestion.size()!= 0){
			System.out.println("Tienes estas PlayList:");
			int i = 1;
			for(PlayList pl: gestion){
				laGestion = laGestion + ("\n-----------\n"
						+ i + " -  PlayList : \n" + pl.toString()
						+"\n-----------\n");
				i++;
			}
			System.out.println(laGestion);
		}else{
			laGestion =("La gestion esta vacia\n");
		}
		return laGestion;
	}

	public static void main(String[] args) {
		
		GestionPlayList g = new GestionPlayList();
		g.menu(g);
	}
}