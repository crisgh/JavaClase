package urjc.ist.firstClasses;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class EjemploNIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// ********************** NIO **************************** //
		
		Path ruta1 = Paths.get("/", "home", "mariacg");
		Path ruta2 = Paths.get("data", "files");
		System.out.format("toString: %s%n", ruta1.toString());
		System.out.format("toString: %s%n", ruta2.toString());
		
		System.out.println("\n__ Ruta 1 __\n");
		System.out.format("toString: %s%n", ruta1.toString());
		System.out.format("getFileName: %s%n", ruta1.getFileName());
		System.out.format("getName(0): %s%n", ruta1.getName(0));
		System.out.format("getNameCount: %d%n", ruta1.getNameCount());
		System.out.format("subpath(0,2): %s%n", ruta1.subpath(0,2));
		System.out.format("getParent: %s%n", ruta1.getParent());
		System.out.format("getRoot: %s%n", ruta1.getRoot());
		
		System.out.println("\n__ Ruta 2 __\n");
		System.out.format("toString: %s%n", ruta2.toString());
		System.out.format("getFileName: %s%n", ruta2.getFileName());
		System.out.format("getName(0): %s%n", ruta2.getName(0));
		System.out.format("getNameCount: %d%n", ruta2.getNameCount());
		System.out.format("subpath(0,2): %s%n", ruta2.subpath(0,2));
		System.out.format("getParent: %s%n", ruta2.getParent());
		System.out.format("getRoot: %s%n", ruta2.getRoot());
		
		
		System.out.println	("Salida....");
		Path p1 = Paths.get("home");
		Path p3 = Paths.get("home/jfelipe/data");
		Path ruta3 = Paths.get("/", "home", "mariacg");
		// Resultado: /home/mariacg/foobar
		System.out.format("%s%n", p1.resolve("foobar"));
		
		// Resultado mariacg/data
		Path p1_to_p3 = p1.relativize(p3);
		System.out.format("%s%n", p1.resolve("maria"));
		// Resultado ../..
		Path p3_to_p1 = p3.relativize(p1);
		System.out.format("%s%n", p1.resolve("pepe"));
//		
//		Files.createDirectory(rutaAlt); // Crea un solo directorio
//		Files.createDirectory(path); // Genera directorios intermedios si es necesario
//		Files.createFile(path); // Lanza excepción si el archivo ya existe
//		Files.exists(path); // Comprueba si existe el archivo o directorio
//		Files.isDirectory(path); // Comprueba si el directorio existe
//		Files.isRegularFile(path); // Comprueba si el archivo existe
//		Files.createTempFile(null, ".txt"); // Crea archivo temporal con nombre aleatorio
	}

}
