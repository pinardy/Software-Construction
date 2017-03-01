package Cohort_Exercise_3;

import java.io.*;
import java.net.*;
// TCP: Transmission Control Protocol

public class ChatClient {

    /** ----- Cohort Exercise 3 -----
     * Write a Java program which allows a fixed N processes to talk in a fixed order
     * (assume that a process is serving as a server)
     */

    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        //String hostIP = "10.11.3.28";
        //String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321; // has to be the same port number as in server
 
//        Socket echoSocket = new Socket(hostName, portNumber);
		Socket echoSocket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress("localhost", portNumber); // contains ip address and portNumber
		echoSocket.connect(sockaddr);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream())); // to get msg from server
        BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in)); // whatever written in terminal
        String userInput;
        do {
            userInput = stdIn.readLine(); // read whatever written into userInput
            out.println(userInput);
            out.flush();
            System.out.println("Husband says: " + in.readLine());
        } while (!userInput.equals("Bye")); // goes on forever until "Bye" is typed in
            
        echoSocket.close();
        in.close();
        out.close();
        stdIn.close();           
    }
}
