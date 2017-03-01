package Cohort_Exercise_1;

import java.math.BigInteger;
import java.util.ArrayList;

/** Cohort Exercise 1:
 * Semiprime is a natural number that is the product of two prime numbers.
 * By assumption, a factor of the given number must be prime
 * Given a semiprime, output its prime factors */

public class FactorPrime {
    public static void main(String[] args) {

        // Test case 1
        System.out.println("outPrimeFactors 1:");
        System.out.println(outPrimeFactors1(new BigInteger("4294967297")));
        System.out.println("outPrimeFactors 2:");
        System.out.println(outPrimeFactors2(new BigInteger("4294967297"), new BigInteger("2")));

        // Test case 2
//        System.out.println("outPrimeFactors 1:");
//        System.out.println(outPrimeFactors1(new BigInteger("1127451830576035879")));
//        System.out.println("outPrimeFactors 2:");
//        System.out.println(outPrimeFactors2(new BigInteger("1127451830576035879"), new BigInteger("2")));

    }

    // a slower algorithm
    public static ArrayList<BigInteger> outPrimeFactors1(BigInteger N){
        ArrayList<BigInteger> factors = new ArrayList<>();
        for (BigInteger bi = BigInteger.valueOf(2);
             bi.compareTo(N) <= 0;
             bi = bi.add(BigInteger.ONE)) {

            if (factors.size() != 2) {
                int compare = N.mod(bi).compareTo(BigInteger.ZERO);
                if (compare == 0) {
                    factors.add(bi);
                    factors.add(N.divide(bi));
                    N = N.divide(bi);
                }
            }
        }
        return factors;
    }

    // this algorithm is faster
    public static ArrayList<BigInteger> outPrimeFactors2(BigInteger n, BigInteger init) {
        ArrayList<BigInteger> factors = new ArrayList<>();

        while (init.compareTo(n) < 0) {
            if (n.remainder(init).compareTo(new BigInteger("0")) == 0) {
                factors.add(init);
                BigInteger other = n.divide(init);
                factors.add(other);
                return factors;
            }
            init = init.add(new BigInteger("3"));
        }
        return null;
    }

}
