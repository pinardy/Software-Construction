package Cohort_Exercise_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/** Cohort Exercise 5:
 * Assume that you are given four computers, one acting as a server
 * and the three clients do the actual work of factoring and
 * display the result as soon as it is ready.
 * The server knows the semi-prime and assigns the job.  */

public class FactorPrimeServer {

    public static void main(String[] args) throws IOException {

        BigInteger number = new BigInteger("1127451830576035879");
        int numOfClients = 3;

        ServerSocket serverSocket = new ServerSocket(4321);

        System.out.println("(... expecting connection ...)");

        for (int i = 0; i < numOfClients; i++){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // arguments
            out.println(number.toString());
            out.println(i+2); // each client will handle a different number division

            // flush and close
            out.flush();
            clientSocket.close();
            out.close();
        }

        System.out.println("(... connection of all clients established ...)");
        System.out.println("Waiting for results...");

        // calculating values
        Socket client = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String result = new String(in.readLine());
        serverSocket.close();

        System.out.println("Factors: " + result);

    }

}
