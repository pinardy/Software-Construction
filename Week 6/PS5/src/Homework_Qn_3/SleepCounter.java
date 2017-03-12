package Homework_Qn_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** Homework Question 3
 * Implement a simple counter in Java which increments its value each second.
 * Use sleep(1000) to delay each print.
 * The counter stops whenever the user inserts the value 0 from console.
 * Note that the program cannot make any assumption on when the user will press the key.
 */

public class SleepCounter {
    public static void main(String[] args) throws Exception{
        CounterThread counter = new CounterThread();
        counter.start();

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        while (!stdIn.readLine().equals("0")) {
            continue;
        }
        counter.interrupt();
    }
}
