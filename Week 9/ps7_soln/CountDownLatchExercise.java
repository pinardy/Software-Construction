package Week9;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountDownLatchExercise {

	public static void main(String args[]) throws InterruptedException {
		int limit = 7;
		final int noOfSearcher = 4;
	    final CountDownLatch latch = new CountDownLatch(limit);
	    final CountDownLatch finish = new CountDownLatch(limit);
	    String[] array = new String[]{"A","B","F","D","A","B","F","D","A","B","F","D","A","B","F","D","A","B","F","D","A","B","F","D", "F", "F"};
	    
	    final Searcher[] searchers = new Searcher[noOfSearcher]; 
	    	    
	    for (int i = 0; i < noOfSearcher; i++) {
	    	searchers[i] = new Searcher(array, i*array.length/noOfSearcher, (i+1)*array.length/noOfSearcher, latch, finish);
	    	searchers[i].start();
	    }
	    	    
	    Thread awaitThread = new Thread( new Runnable () {
			public void run() {
		        try {
		        	System.out.println ("awaitThread awaiting");
					latch.await();
		        	System.out.println ("awaitThread finishing");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  //main thread is waiting on CountDownLatch to finish
		        		        
			    for (int i = 0; i < noOfSearcher; i++) {
			    	searchers[i].interrupt();
			    }
				while (finish.getCount() > 0) {
					finish.countDown();
				}
			}	    		
	    }
	    );
	    awaitThread.start();	    
	    
    	System.out.println ("main Thread awaiting");
		finish.await();		
    	System.out.println ("main Thread finishing");
		while (latch.getCount() > 0) {
			latch.countDown();
		}
	}	  
}

class Searcher extends Thread {
	private final String[] data;
	private final int start;
	private final int end;
	private final CountDownLatch latch;
	private final CountDownLatch finish;
	
	public Searcher (String[] data, int start, int end, CountDownLatch latch, CountDownLatch finish) {
		this.data = data;
		this.start = start;
		this.end = end;
		this.latch = latch;
		this.finish = finish;
	}
	  
	public void run() {
	    for (int i = start; i < end; i++) {	    	
	    	if (data[i].equals("F")) {
	    		latch.countDown();
	    	}
	    	
	    	if (this.isInterrupted()) {
	        	System.out.println ("interrupted");

	    		break;
	    	}
	    }
	    
	    finish.countDown();
	}  
}
