package urjc.ist.net;

public class Counter{
	private int c;
	
	public synchronized int incr() { // obliga que pasen de uno en uno
		return ++c;
	}
}
