package Cohort_Exercise_4;

//this class must be thread-safe. why?
public class MyCyclicBarrier {
    private int count = 0;
    private Runnable torun;

    public MyCyclicBarrier(int count, Runnable torun) {
        this.count = count;
        this.torun = torun;
    }

    public MyCyclicBarrier(int count) {
        this.count = count;
    }

    //complete the implementation below.
    //hint: use wait(), notifyAll()
    public synchronized void await() throws InterruptedException {
        count--;

        // If the current thread is not the last to arrive, thread will wait
        if (count > 0) {
            wait();
        }

		// If the current thread is last to arrive, notify all waiting threads
        else {
            notifyAll(); //notify all waiting threads
        }

    }
}
