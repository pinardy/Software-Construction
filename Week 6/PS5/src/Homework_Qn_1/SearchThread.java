package Homework_Qn_1;

import java.math.BigInteger;

/**
 * Created by Pin on 05-Mar-17.
 */
public class SearchThread extends Thread {
    BigInteger n;
    BigInteger init;
    BigInteger stepSize;
    int id;

    public SearchThread (BigInteger n, int id, int init, int stepSize) {
        this.id = id;
        this.n = n;
        this.init = BigInteger.valueOf(init);
        this.stepSize = BigInteger.valueOf(stepSize);
    }
    public BigInteger getResult() {
        return FactorThread.factor;
    }

    public void run() {
        System.out.println("Thread " + id + " started");
        BigInteger zero = new BigInteger("0");

        while (init.compareTo(n) < 0) {
            if (isInterrupted()) {
//                System.out.println("Thread " + id + " interrupted");
                break;
            }

            if (n.remainder(init).compareTo(zero) == 0) {
                FactorThread.factor = init;
                System.out.println("Thread " + id + " found result");
                break;
            }

            init = init.add(stepSize);
        }
    }


}
