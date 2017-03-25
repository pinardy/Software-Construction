package Cohort_Exercise_8;

import java.util.Stack;

// This is done using Composition
public class SafeStackComposition<E> extends Stack {
    private Stack<E> stack = new Stack<E>();

    // private method since pushing is done by pushIfNotFull(), which makes use of push()
    private synchronized boolean pushIfNotFull(E e) {
        // if stack size has not exceed capacity, we cna push
        if (stack.size() != stack.capacity()) {
            stack.push(e);
            return true;
        }
        return false;
    }

    private synchronized E popIfNotEmpty() {
        // we pop if stack is not empty
        if (!checkEmpty()) {
            return stack.pop();
        }
        return null;
    }

    private synchronized boolean checkEmpty() {
        return stack.isEmpty();
    }
}
