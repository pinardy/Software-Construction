package Cohort_Exercise_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;

/** Cohort Exercise 5:
 * Assume that you are given four computers, one acting as a server
 * and the three clients do the actual work of factoring and
 * display the result as soon as it is ready.
 * The server knows the semi-prime and assigns the job.  */


public class FactorPrimeClient {
    public static void main(String[] args) throws Exception {

        String hostName = "localhost";
        int portNumber = 4321;

        Socket echoSocket = new Socket(hostName, portNumber);
        BufferedReader in = new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));

        // for method inputs
        BigInteger number = new BigInteger(in.readLine());
        BigInteger init = new BigInteger(in.readLine());

        // closure
        in.close();
        echoSocket.close();

        // computation here
        System.out.println("Factoring begins...");
        ArrayList<BigInteger> result = outPrimeFactors(number, init);
        System.out.println("Factoring is complete!");

        // sending info to server
        try {
            echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            out.println(result.toString());
            out.flush();
            echoSocket.close();
            out.close();
        }
        catch (Exception e) {
        }
    }


    public static ArrayList<BigInteger> outPrimeFactors(BigInteger n, BigInteger init) {
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
