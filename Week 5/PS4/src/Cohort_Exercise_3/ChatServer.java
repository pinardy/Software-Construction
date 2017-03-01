package Cohort_Exercise_3;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
// TCP: Transmission Control Protocol

public class ChatServer {

    /** ----- Cohort Exercise 3 -----
     * Write a Java program which allows a fixed N processes to talk in a fixed order
     * (assume that a process is serving as a server)
     */

    public static void main(String[] args) throws Exception {
    	ServerSocket serverSocket = new ServerSocket(4321); // 4321 is the port number
    	System.out.println("(... expecting connection ...)");
        int N = 2; // number of clients
        Socket clientSocket;

        ArrayList<Socket> listOfSockets = new ArrayList<>();
        for (int i = 0; i < N; i++){
            clientSocket = serverSocket.accept();
            listOfSockets.add(clientSocket);
        }

    	System.out.println("(... connection established ...)");
    	// PrintWriter for printing message to stream
        // if true, it will send message immediately to client
        // if false, it will wait first

        int i = 0;
        while (i < N) {
//            PrintWriter out =
//                    new PrintWriter(clientSocket.getOutputStream(), true); // o/p stream of socket
            PrintWriter out =
                new PrintWriter(listOfSockets.get(i).getOutputStream(), true); // o/p stream of socket
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(listOfSockets.get(i).getInputStream()));

            BufferedReader stdIn =
                    new BufferedReader(
                            new InputStreamReader(System.in)); // bufferedreader helps to optimize performance
            String inputLine;
            // reading line from inputstream of socket, check if it is "Bye"
            while (!((inputLine = in.readLine()).equals("Bye"))) {
                System.out.println("Wife says:" + inputLine);
                out.println(stdIn.readLine()); // whatever written in terminal will be sent to server
                out.flush(); // sent immediately
            }
            i += 1;

            out.println(inputLine);
            listOfSockets.get(i).close();
            out.close();
            in.close();
        }
        serverSocket.close();
    }
}