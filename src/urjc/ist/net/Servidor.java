package urjc.ist.net;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.net.Socket;
import java.net.BindException;
import java.net.ServerSocket;

public class Servidor {

	private static final int NCLIENTES = 5;

	public static void main(String[] args) throws Exception {
		
		// Creamos un arraylist para guardar los hilos (Threads).
		ArrayList<Thread> listaHilos = new ArrayList<Thread>();  
		int count = 0;
		try{

			// Crear el socket del servidor
			int port = 15000;
			ServerSocket serverSocket = new ServerSocket(port);
			System.err.println("Inciado servidor en el puerto " + port);
			
			// Esperar a las conexiones de los clientes y procesar
			// Procesa NCLIENTES conexiones entrantes antes de finalizar

			for ( count = 0; count < NCLIENTES; count++) {

				System.err.println("Esperando nuevo cliente...");
				// La llamada a accept() es bloqueante hasta que llegue un cliente

				// Conexion de cliente al servidor

				Socket socketCliente = serverSocket.accept();
				System.err.println("Aceptada nueva conexión de un cliente, procesando...");

				// Se crea un thread por cada conexion aceptada

				Thread hilo = new Thread(new EchoHandler(socketCliente));

				// añadimos primero el hilo al arraylist para que no empiece antes de añadirse.

				listaHilos.add(hilo);

				// El hilo realiza su implementacion run 

				hilo.start();
		
				//System.out.println(listaHilos.size() + " Lista de hilos\n");
				System.out.println("Cliente conectado: " + socketCliente.getInetAddress().toString() + ":"+socketCliente.getPort());
				System.out.println("Se ha creado el hilo para la conección "+ listaHilos.get(count).getName() + " para este cliente");
				
			}
			
			// Si tenemos el socket con todos los clientes ya aceptados no aceptamos mas conexiones
			
			if(count == NCLIENTES){
				serverSocket.close();
			}

			// Espero a que todos los procesos hijos (threads) terminen para poder finalizar y cerrar el servidor
			
			System.out.println("Ya no puedo atender a mas clientes, espero a que terminen todos los hilos...");
			for(Thread th : listaHilos){
				th.join();
			}
			
			System.err.println("Ya he atendido a " + NCLIENTES + " clientes, fin del programa");
			System.err.println("Liberando el socket del servidor...");
			serverSocket.close();
			System.err.println("Finaliza el servidor");

		}catch(BindException e){
			System.err.println("Existe un servidor con ese puerto, intentelo de nuevo con otro.");
		}
	}
} 

// worker para realizar la implemetacion de los hilos

class EchoHandler implements Runnable{
	private static final String CHARSET_NAME = "UTF-8";

	Socket client;
	EchoHandler (Socket client){
		this.client = client;
	}

	@Override
	public void run(){

		InputStream is;
		Scanner scanner;
		PrintWriter out;

		String line;

		try{

			is = client.getInputStream();
			scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
			out = new PrintWriter(client.getOutputStream(), true);

			try{
				do{
					line = scanner.nextLine();
					out.println("[echo] " + line);
					out.flush();	

				}while(line.length()!=0);


			}catch(NoSuchElementException e){
				System.err.println("Desconexion cliente: " + client.getInetAddress().toString() + client.getPort());
				
			}
			
			is.close();
			out.close();
			client.close();

		}catch(Exception e){
			e.printStackTrace();

		}
	}
}
