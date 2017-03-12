package Cohort_Exercise_5;

/** Cohort Exercise 5:
 * Assuming that the correctness requirement is that “saving + cash = 5000” is an invariant,
 * fix the following class: LockStaticVariables.java.
 */

public class LockStaticVariablesFixed {
    // NOTE: saving + cash = 5000
	public static int saving = 5000;
	public static int cash = 0;

    private static Lock lock = new Lock();

	public static void main(String args[]){   	
		int numberofThreads = 10000;
		WD[] threads = new WD[numberofThreads];
	
		for (int i = 0; i < numberofThreads; i++) {
			threads[i] = new WD(lock);
			threads[i].start();
		}
		
		try {
			for (int i = 0; i < numberofThreads; i++) {
				threads[i].join();

				// checks if the condition is not met in any thread
                if (saving + cash != 5000){
                    System.out.println("Condition not met");
                }

			}
		} catch (InterruptedException e) {
			System.out.println("some thread is not finished");
		}
		
		System.out.print("The result is: " + LockStaticVariablesFixed.cash);
	}
}

class Lock{
    public Lock() {
        int saving = LockStaticVariablesFixed.saving ;
        int cash = LockStaticVariablesFixed.cash;
    }
}

class WD extends Thread {


    private final Lock lock;

    public WD(Lock lock){
        this.lock = lock;
    }

	public void run () {
		if (LockStaticVariablesFixed.saving >= 1000) {
			System.out.println("I am doing something.");
			// figure out what is the lock
//			synchronized (LockStaticVariablesFixed.class) {
			synchronized (lock) {
                LockStaticVariablesFixed.saving = LockStaticVariablesFixed.saving - 1000;
                LockStaticVariablesFixed.cash = LockStaticVariablesFixed.cash + 1000;
            }
		}		
	}	
}

