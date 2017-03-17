package urjc.ist.net;

public class Tweeter implements Runnable {

	public Tweeter() {
		// TODO Auto-generated constructor stub
	}
	
// **** Segunda forma ****//
	@Override
	public void run() {

		for(int i = 0; i < 10; i++)
			System.out.println("Thread "
					+ Thread.currentThread().getId()
					+ " State "
					+ Thread.currentThread().getState());
		
		// realiza 10 iteraciones y podemos ver que se realiza sin orden,depende de cuando lo ejecutes 
		// se puede ver que se realiza de una forma u otra
	}


	public static void main(String[] args) {

		for(int i = 0 ; i < 10; i++){
			(new Thread(new Tweeter())).start();
			// Crea 10 hilos (0-9)- cada uno es un camino de ejecucion.
			// Los hilos no son deterministas , no puedo garantizar a priori el orden de ejecucion de los
			// hilos/procesos.
			System.out.println("\nThreads activos : " + Thread.activeCount() + "\n");
		}
	}
	
// *********** Primera forma ************//
//	@Override
//	public void run() {
//
//		for(int i = 0; i < 10; i++)
//			System.out.println("Thread says tweeeeet!!!");
//		// realiza 10 iteraciones 
//	}
//
//
//	public static void main(String[] args) {
//
//		for(int i = 0 ; i < 10; i++){
//			(new Thread(new Tweeter())).start();
//			// Crea 10 hilos (0-9)- cada uno es un camino de ejecucion.
//			// Los hilos no son deterministas , no puedo garantizar a priori el orden de ejecucion de los
//			// hilos/procesos.
//		}
//	}

}


