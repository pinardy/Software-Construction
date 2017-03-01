package Cohort_Exercise_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/** Cohort Exercise 6:
 * Assume we don’t know how many clients are there to help.
 * Use socket timeout to collect clients and then assign the jobs  */

public class FactorPrimeServerMul {

    public static void main(String[] args) throws IOException {

        BigInteger number = new BigInteger("1127451830576035879");

        ServerSocket serverSocket = new ServerSocket(4321);
        serverSocket.setSoTimeout(20000); // 20s for clients to connect

        ArrayList<Socket> socketList = new ArrayList<>();
        ArrayList<PrintWriter> outList = new ArrayList<>();

        System.out.println("Waiting for 20s for clients to connect...");

        //the following is a pattern of busy-waiting.
        while (true) {
            try {
                Socket newSocket = serverSocket.accept();
                socketList.add(newSocket);
                System.out.println(socketList.size() + " client(s) connected");
                outList.add(new PrintWriter(newSocket.getOutputStream(), true));
            }
            catch (java.net.SocketTimeoutException e) {
                System.out.println("\n" + "30s is over. Timed out" + "\n");
                break;
            }
        }

        System.out.println("Number of clients: " + socketList.size());

        for (int i = 0; i < outList.size(); i++){
            PrintWriter out = new PrintWriter(socketList.get(i).getOutputStream(), true);

            // arguments
            out.println(number.toString());
            out.println(i+2); // each client will handle a different number division
            out.println(socketList.size());

            // flush and close
            out.flush();
            socketList.get(i).close();
            out.close();
        }

        System.out.println("Waiting for results...");

        // calculating values
        Socket client = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String result = new String(in.readLine());
        serverSocket.close();

        System.out.println("Factors: " + result);

    }

}
