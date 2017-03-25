package Cohort_Exercise_3;

public class MyStackThreadSafe {
	private final int maxSize; // final only initializes. does not allow updating
	private long[] stackArray;
	private int top; // Invariant: top < stackArray.length && top >= -1

	// @pre: s > 0
	// @post: maxSize == s  && stackArray != null && top == -1
	public MyStackThreadSafe(int s) {
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}

    // @pre: top < maxSize
    // @post: top element is 'j'
    private synchronized void push(long j) {
		stackArray[++top] = j;
		notifyAll();
	}

    // @pre: top > 0
    // @post: top element is 'j' / @ret = stackArray[top--]
    private synchronized long pop() {
	    long result;

        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = stackArray[top--];
        return result;
	}

    // @pre: stackArray.length > 0 && 0 <= top + 1 <= maxSize && stackArray != null
    // @post: stackArray.length > 0 && 0 <= top + 1 <= maxSize && stackArray != null
	private synchronized long peek() {
        long topMost;
        topMost = stackArray[top];
	    return topMost;
	}

    // @pre: true
    // @post: @ret true if stack is empty
    private synchronized boolean isEmpty() {
	    boolean result;
	    result = (top == -1);
		return result;
	}

    // @pre: -1 <= top < maxSize && stackArray != null
    // @post: -1 <= top < maxSize && stackArray != null
    private synchronized boolean isFull() {
        boolean result;
        result = (top == maxSize - 1);
        return result;
	}
}