package urjc.ist.net;

public class Counter{
	private int c;
	
	public synchronized int incr() {
		return ++c;
	}
}
