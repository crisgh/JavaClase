package urjc.ist.net;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Cliente {

	private static final String CHARSET_NAME = "UTF-8";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		String host = "localhost";
		int port = 15000;
		Scanner scanner;
		PrintWriter out;
		
		try{
			// Conectar al servidor y abrir sockets y streams
			Socket socket = new Socket(host, port);
			System.err.println("\nConnectado al servidor " + host + " en el puerto " + port+ "\n");

			// Introducir el login del cliente

			System.out.print("Indica tu nombre de usuario: ");
			Scanner log = new Scanner(System.in);
			String login = log.nextLine();
			Scanner stdin = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
			System.out.print("[Nota] Para finalizar el programa cliente escribir 'adios' ");
			
			
			try {
				// Comunicación con el servidor a través del socket
				String s;
				String line = null;
				do{
					
					// Configurar streams de E/S

					stdin = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
					
					InputStream is = socket.getInputStream();
					scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
					out = new PrintWriter(socket.getOutputStream(), true);
					
					System.out.print("\nIntroduce el mensaje: ");
					// Leer línea desde consola
					try {

						s = stdin.nextLine();
					}catch (NoSuchElementException e) {
						s = null;
					}
					// Enviar la línea al servidor

					out.println("[" + login + "]: " + s);
					out.flush();

					try {
						line = scanner.nextLine();
					}
					catch (NoSuchElementException e) {
						line = null;
					}
					if(line == null){
						System.err.println("El servidor no esta disponible.");
						break;
					}
					System.out.println(line);

				}while(!s.equals("adios"));

				System.err.println("Cerrando la conexión a " + host + "\n");
				out.close();
				scanner.close();
				socket.close();
				stdin.close();
				System.err.println("Recursos liberados, finaliza el programa cliente.");
				log.close();
			
			}catch (IOException ioe) {
				socket.close();
				stdin.close();
				throw new IllegalArgumentException("Imposible crear " + socket, ioe);
			}
		}catch(ConnectException e){
			System.err.println("El servidor no se encuentra disponible.");
		}
	}



}
