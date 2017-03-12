package Cohort_Exercise_4;

import java.util.concurrent.atomic.AtomicInteger;

/** Cohort Exercise 4:
 * Fix FirstBlood.java using AtomicInteger,
 * assuming the post-condition is that the sum is the number of additions.
 */

public class FirstFixWithAtomicInteger {
    // We use AtomicInteger instead of int to ensure atomicity
//	public static int count = 0;
	public static AtomicInteger count = new AtomicInteger();

	public static void main(String args[]){   	
		int numberofThreads = 10000;
		ThreadA[] threads = new ThreadA[numberofThreads];
	
		for (int i = 0; i < numberofThreads; i++) {
			threads[i] = new ThreadA();
			threads[i].start();
		}
		
		try {
			for (int i = 0; i < numberofThreads; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println("some thread is not finished");
		}
		
		System.out.print("The result is ... ");
		System.out.print("wait for it ... ");
		System.out.println(count);		
	}
}

class ThreadA extends Thread {
	public void run () {
		// increment is NOT atomic
//		FirstBlood.count++;

		// increment using AtomicInteger's class
        FirstFixWithAtomicInteger.count.addAndGet(1);
	}	
}

