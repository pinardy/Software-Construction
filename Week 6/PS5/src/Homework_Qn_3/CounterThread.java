package Homework_Qn_3;

/** Homework Question 3
 * Implement a simple counter in Java which increments its value each second.
 * Use sleep(1000) to delay each print.
 * The counter stops whenever the user inserts the value 0 from console.
 * Note that the program cannot make any assumption on when the user will press the key.
 */

public class CounterThread extends Thread {

    private int counter = 0;

    public void run() {
        try {
            while (!this.isInterrupted()) {
                Thread.sleep(1000);
                System.out.println(counter += 1);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

