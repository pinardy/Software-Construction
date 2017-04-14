package Cohort_Exercise_6;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class NonblockingCounter {
    //    private AtomicInteger value = new AtomicInteger();

    // first input is initialRef, second input is initialStamp
    private AtomicStampedReference<AtomicInteger> value = new AtomicStampedReference<AtomicInteger>(new AtomicInteger(), 0);


    public int getValue() {
        return value.getReference().get();
    }

    public int increment() {
        int oldValue; // this is our stamp
        AtomicInteger oldRef;
        AtomicInteger newRef;

        do {
            oldRef = value.getReference();
            oldValue = value.getStamp();
            newRef = new AtomicInteger(oldRef.get());
            newRef.incrementAndGet();

            // does the value still have the old reference? (oldRef)
            // does the value still have the old stamp? (oldValue)
            // if yes for both, update reference and stamp atomically
        } while (!value.compareAndSet(oldRef, newRef, oldValue, oldValue + 1));
        return oldRef.get() + 1;
    }
}
