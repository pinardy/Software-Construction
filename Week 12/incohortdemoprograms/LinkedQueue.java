import java.util.concurrent.atomic.AtomicReference;

//import Annotations.GuardedBy;
//import Annotations.ThreadSafe;

//@ThreadSafe
public class LinkedQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    private final Node<E> dummy = new Node<E>(null, null);
    private final AtomicReference<Node<E>> head
            = new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail
            = new AtomicReference<Node<E>>(dummy);

    // no lock!
    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            // get current element to the last element "tail"
            Node<E> curTail = tail.get();
            // get current value of the next pointer of "tail"
            Node<E> tailNext = curTail.next.get();

            /* Has tail has been changed by other thread?
             YES: repeat operation
             NO: continue */

            // This condition is to check if any thread has modified tail
            if (curTail == tail.get()) { // if this is not true, abort since other some other thread has already modified tail;
                // tail has not been updated by other thread
                // "next" pointer of "tail" is always null if the queue is stable
                if (tailNext != null) {  // if this is true, Queue in intermediate state, advance tail
                    // advance the "tail" pointer by 1 position
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // no other thread has updated "tail" pointer
                    // no other thread has updated content of the queue

                    // In normal state, try inserting new node
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // Insertion succeeded, try advancing tail
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }

    // try to write get function
}