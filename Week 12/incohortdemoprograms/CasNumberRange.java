import java.util.concurrent.atomic.AtomicReference;

public class CasNumberRange {
    private class IntPair {
    	// INVARIANT: lower <= upper
    	final int lower;
    	final int upper;

    	public IntPair(int lower, int upper) {
    		this.lower = lower;
    		this.upper = upper;
    	}
    }

    private final AtomicReference<IntPair> values = new AtomicReference<IntPair>(new IntPair(0, 0));

    public int getLower() {
    	return values.get().lower;
    }

    public int getUpper() {
    	return values.get().upper;
    }

    // we do not take any locks!
    public void setLower(int i) {
    	while (true) {
    	    // read old value of interval
    		IntPair oldv = values.get();

    		// is lower > upper?
    		if (i > oldv.upper) {
    			throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
    		}
    		// at this moment lower bound is <= upper bound
            // try to update lower bound here
    		IntPair newv = new IntPair(i, oldv.upper);

    		// is the interval the same as before?
            // YES: the interval value is still "oldv", read by this thread
            // if NO: some other thread hass updated, repeat operation
    		if (values.compareAndSet(oldv, newv)) { // commitment here
    			return;
    		}
    	}
    }

    public void setUpper(int i) {
    	while (true) {
    		IntPair oldv = values.get();
    		if (i < oldv.lower) {
    			throw new IllegalArgumentException("Can't set upper to " + i + " < lower");
    		}
    		IntPair newv = new IntPair(oldv.lower, i);
    		if (values.compareAndSet(oldv, newv)) {//commitment here
    			return;
    		}
    	}
    }
}