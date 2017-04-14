//import Annotations.GuardedBy;
//import Annotations.ThreadSafe;

//@ThreadSafe
public class SimulatedCAS {
//    @GuardedBy("this")
    private int value;

    public synchronized int get() {
        return value;
    }

    // NOTE: This is a hardware instruction. This code is just a simulation!
    public synchronized int compareAndSwap(int expectedValue,
                                           int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue)
            value = newValue;
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue,
                                              int newValue) {
        return (expectedValue
                == compareAndSwap(expectedValue, newValue));
    }
}
