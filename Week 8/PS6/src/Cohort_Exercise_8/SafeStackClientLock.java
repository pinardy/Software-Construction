package Cohort_Exercise_8;

import java.util.Stack;

// This is done using Client-Side Locking
public class SafeStackClientLock<E> extends Stack {
    private Stack<E> stack = new Stack<E>();

    // private method since pushing is done by pushIfNotFull(), which makes use of push()
    private boolean pushIfNotFull(E e) {
        // if stack size has not exceed capacity, we cna push
        synchronized (stack) {
            if (stack.size() != stack.capacity()) {
                stack.push(e);
                return true;
            }
            return false;
        }
    }

    private E popIfNotEmpty() {
        synchronized (stack) {
            // we pop if stack is not empty
            if (!checkEmpty()) {
                return stack.pop();
            }
            return null;
        }
    }

    private boolean checkEmpty() {
        return stack.isEmpty();
    }
}
