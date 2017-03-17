package urjc.ist.net;

public class Tweeter implements Runnable {

	public Tweeter() {
		// TODO Auto-generated constructor stub
	}

	private Counter counter; // mismo objeto para todos los hilos
	
	public Tweeter(Counter c){
		counter = c;
	}

	
	// *** Forma 3 ***//
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			System.out.println("Thread "
					+ Thread.currentThread().getId()
					+ " says tweeeeet #"
					+ counter.incr() + " !!!");
		}
	}


	
	// **** Segunda forma ****//
	//	@Override
	//	public void run() {
	//		try {
	//			Thread.sleep(1000);
	//			for(int i = 0; i < 10; i++)
	//
	//				System.out.println("Thread "
	//						+ Thread.currentThread().getId()
	//						+ " State "
	//						+ Thread.currentThread().getState());
	//			/* Si el thread ha sido interrumpido pero no ha saltado la excepcion, dejamos que 
	//			 * lo maneje el catch levantando nosotros mismos la excepcion! interrupted() limpia
	//			 * el estado, pero este throw lo pondra de nuevo.
	//			 */
	//			if (Thread.interrupted())
	//				throw new InterruptedException();
	//
	//		} catch (InterruptedException e) {
	//			// El thread ha sido interrumpido mientras dormia
	//			// y no ha dormido el tiempo deseado. Retornamos
	//			// de run()
	//			System.err.println("Thread "
	//					+ Thread.currentThread().getId()
	//					+" interrupted, exiting.");
	//			return;
	//		}
	//
	//
	//		// realiza 10 iteraciones y podemos ver que se realiza sin orden,depende de cuando lo ejecutes 
	//		// se puede ver que se realiza de una forma u otra
	//	}



	public static void main(String[] args) {
		Counter myCounter = new Counter();
		for(int i = 0 ; i < 10; i++){
			(new Thread(new Tweeter(myCounter))).start();
		}
	}	
		//***Forma 2 ***//		
		//		for(int i = 0 ; i < 10; i++){
		//			Thread hilo = new Thread(new Tweeter());
		//			(hilo).start();
		//			// Crea 10 hilos (0-9)- cada uno es un camino de ejecucion.
		//			// Los hilos no son deterministas , no puedo garantizar a priori el orden de ejecucion de los
		//			// hilos/procesos.
		//			System.out.println("\nThreads activos : " + Thread.activeCount() + "\n");
		//
		//			System.out.println("tenemos un start");
		//			
		//			try {
		//				hilo.join(); // espera a que todos los los hilos terminen
		//				System.out.println("\nJOIN");
		//			} catch (InterruptedException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//		}
		//	}

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


