package urjc.ist.net;

import java.io.*;
import java.util.Scanner;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.NoSuchElementException;

public class EchoServer {
	
	private static final String CHARSET_NAME = "UTF-8";
	private static final int NCLIENTES = 5;
	
    public static void main(String[] args) throws Exception {

    	
        // Crear el socket del servidor
        int port = 15000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Inciado servidor en el puerto " + port);

        Scanner scanner;
        PrintWriter out;
        
        // Esperar a las conexiones de los clientes y procesar
        // Procesa NCLIENTES conexiones entrantes antes de finalizar
        for (int count = 1; count <= NCLIENTES; count++) {
        	System.err.println("Esperando nuevo cliente...");
            // La llamada a accept() es bloqueante hasta que llegue un cliente
            Socket clientSocket = serverSocket.accept();
            System.err.println("Aceptada nueva conexión de un cliente, procesando...");

            // Configurar streams de E/S
            try {
            
                InputStream is = clientSocket.getInputStream();
                scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                
            }
            catch (IOException ioe) {
                throw new IllegalArgumentException("Imposible crear " + clientSocket, ioe);
            }

            //nextLine() se bloquea hasta recibir una nueva línea del cliente
            String line = null;
            try {
                line = scanner.nextLine();
            }
            catch (NoSuchElementException e) {
                line = null;
            }
            System.out.println("Recibo del cliente... " + line);
            out.println(line);
            out.flush();
            // Cerrar streams de E/S y después el socket de atención al cliente
            System.err.println("Cerrando recursos de comuniación...");
            out.close();
            scanner.close();
            clientSocket.close();
            System.err.println("¡Cliente " + count +" atendido con éxito!");
        }
        System.err.println("Ya he atendido a " + NCLIENTES + ", fin del programa");
        System.err.println("Liberando el socket del servidor...");
        serverSocket.close();
        System.err.println("Finaliza el servidor");
    }
}