package Cohort_Exercise_6;

import java.util.concurrent.*;
import junit.framework.TestCase;

public class TestThreadPoolSample extends TestCase {

    public void testPoolExpansion() throws InterruptedException {
        int max_pool_size = 10;
        ExecutorService exec = Executors.newFixedThreadPool(max_pool_size);

        //todo: insert your code here to complete the test case
        //TODO: create and submit independent tasks to the thread pool
        for (int i = 0; i < max_pool_size; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }
                    System.out.println("Doing task...");
                }
            });

        }

        //hint: you can use the following code to get the number of active threads in a thread pool
        int numThreads = 0;
        if (exec instanceof ThreadPoolExecutor) {
            numThreads = ((ThreadPoolExecutor) exec).getActiveCount();
            System.out.println("Number of threads: " + numThreads);
        }
        assertTrue(numThreads <= max_pool_size);
        exec.shutdownNow();
    }
}
