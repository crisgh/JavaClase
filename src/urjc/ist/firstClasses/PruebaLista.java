package urjc.ist.firstClasses;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PruebaLista{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Iterator<Integer> it;
		Integer total = 0 ;
		
		List Lista1 = new ArrayList<Integer>();
		// List : es una variable generica que implementa la interfaz List
		// Al ser una interafz no podemos crear objetos de el por tanto tenemos que crear una new ArrayList que si puede contener objetos
		
		for (int i = 0; i < 3;i++){
			Lista1.add(i);
			System.out.println("Añado a la lista : " + i);
		}
		it = Lista1.iterator();
		
	// Forma 1 : Iterador
		
		// Si quitamos elementos de la lista el iterador dara un error , ya que no tenemos los mismos contenidos.
		// Hay que garantizar que no se acede a la lista de forma concurrente
		// controlar ell hasNext
		
		while(it.hasNext()){ 
			total += it.next();
		}
		System.out.println("\nForma 1 -> total : " + total);
	
	// Forma 2 : Patron de comportamiento
	
		// la colecion sobre la que estamos realizando este comportamiento tiene que ser iterable, si no no se podria hacer eso
		
		Integer total2 = 0;
		List<Integer> list = new ArrayList<Integer>();
		for(Integer elem: list){
			total2 += elem;
		}
		System.out.println("Forma 2 -> total : " + total);
		
		
	}

}
