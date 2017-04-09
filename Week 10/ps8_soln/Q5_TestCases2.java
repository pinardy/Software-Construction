package Week10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
 
public class TestCases2 {
	private static final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor (100);
	
    public static void main(String[] args) throws Exception {
    	ExecutorService threadPool =
    		    new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
    		        new SynchronousQueue<Runnable>());
    		// submit 2 jobs that take a while to run
    		/// this job takes the only thread
    		threadPool.execute(new SleepRunnable());
    		// this tries to put the job into the queue, throws RejectedExecutionException
    		threadPool.execute(new SleepRunnable());
    }
}


class SleepRunnable implements Runnable {
    public void run() {
        try {
            // this just sleeps for a while which pauses the thread
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}