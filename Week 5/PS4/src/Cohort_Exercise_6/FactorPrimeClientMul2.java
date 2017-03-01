package Cohort_Exercise_6;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
 
public class FactorPrimeClientMul2 {
    public static void main(String[] args) throws Exception {
        String hostName = "192.168.49.31";
        int portNumber = 4321;
 
        Socket echoSocket = new Socket(hostName, portNumber);
        BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        BigInteger n = new BigInteger(in.readLine());
        BigInteger init = new BigInteger(in.readLine());
        BigInteger stepSize = new BigInteger(in.readLine());
        in.close();
        echoSocket.close();
        
		System.out.println("start factoring.");
        BigInteger one = factor(n, init, stepSize);
		System.out.println("done factoring.");

        try {
            echoSocket = new Socket(hostName, portNumber);        	
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        	out.println(one.toString());
        	out.flush();
            echoSocket.close();
            out.close();
        }
        catch (Exception e) {
        	System.out.println("This is probably because the server is done.");
        }
    }
    
    public static BigInteger factor(BigInteger n, BigInteger init, BigInteger stepSize) {
		BigInteger zero = new BigInteger("0");
		
		while (init.compareTo(n) < 0) {			
			//System.out.println("trying" + i);
			if (n.remainder(init).compareTo(zero) == 0) {
				return init;
			}
			
			init = init.add(stepSize);
		}
		
		assert(false);
		return null;
	}
}
