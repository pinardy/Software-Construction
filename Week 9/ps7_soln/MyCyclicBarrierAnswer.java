package Week9;

//this class must be thread-safe. why?
public class MyCyclicBarrierAnswer {
	private int count = 0; 
	private int initialcount = 0;
	private Runnable torun;
	
	public MyCyclicBarrierAnswer (int count, Runnable torun) {
		this.count = count;
		initialcount = count;
		this.torun = torun;
	}

	public MyCyclicBarrierAnswer (int count) {
		this.count = count;
		initialcount = count;
	}
	
	//complete the implementation below.
	//hint: use wait(), notifyAll()
	public synchronized void await () throws Exception {
		count--;
		if (count > 0) {
				wait();
		}
		else {
			notifyAll();		
		if (torun != null) {
			torun.run();
		}
		
		count = initialcount;
		}
	}
}
