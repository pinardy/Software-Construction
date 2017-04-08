package Cohort_Exercise_7;

import java.util.concurrent.*;


public class TimedPutTakeTestLBQ extends PutTakeTestLBQ {
    private BarrierTimer timer = new BarrierTimer();

    public TimedPutTakeTestLBQ(int cap, int pairs, int trials) {
        super(cap, pairs, trials);
        // task is timer, which will be executed
        barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
    }

    public void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new PutTakeTestLBQ.Producer());
                pool.execute(new PutTakeTestLBQ.Consumer());
            }
            barrier.await();
            barrier.await();
            // ultimately, we want the time for the oroginal program
            // compute time for barrier
            long nsPerItem = timer.getTime() / (nPairs * (long) nTrials);
            System.out.print("Throughput: " + nsPerItem + " ns/item");
            assert(putSum.get() == takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        int tpt = 100000; // trials per thread
        // cap is the capacity of the buffer
        for (int cap = 1; cap <= 1000; cap *= 10) {
            System.out.println("Capacity: " + cap);
            // pairs of producer-consumer
            for (int pairs = 1; pairs <= 128; pairs *= 2) {
                TimedPutTakeTestLBQ t = new TimedPutTakeTestLBQ(cap, pairs, tpt);
                System.out.print("Pairs: " + pairs + "\t");
                t.test();
                System.out.print("\t");
                Thread.sleep(1000);
                t.test();
                System.out.println();
                Thread.sleep(1000);
            }
        }
        PutTakeTestLBQ.pool.shutdown();
    }
}
   