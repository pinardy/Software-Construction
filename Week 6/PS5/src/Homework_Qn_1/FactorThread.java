package Homework_Qn_1;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Homework Question 1:
 * Write a multi-threaded program to factor semi-prime.
 * Stop all threads as soon as the problem is solved.
 */

public class FactorThread {

    static BigInteger factor = new BigInteger("0");

    public static void main(String[] args) {

        BigInteger n = new BigInteger("4294967297");
//        BigInteger n = new BigInteger("1127451830576035879");

        int numberOfThreads = 50;
        long startTime = System.currentTimeMillis();

        ArrayList<SearchThread> threads = new ArrayList<>();

        // each thread will search starting from a different number
        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new SearchThread(n, i, i+2, numberOfThreads));
            threads.get(i).start();
        }

        try {
            while (factor.intValue() == 0) {
                Thread.sleep(500);
            }

            for (int i = 0; i < numberOfThreads; i++) {
                threads.get(i).interrupt();
            }

            long duration = System.currentTimeMillis() - startTime;
            BigInteger otherFactor = n.divide(FactorThread.factor);

            System.out.println("Factors are: " + FactorThread.factor + ", " + otherFactor);
            System.out.print("Time used: " + duration + " milliseconds");

        } catch (InterruptedException e) {
            System.out.println("A thread didn't finish!");
        }
    }

}
