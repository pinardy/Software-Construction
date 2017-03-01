package Homework_Qn_1;

import java.io.*;
import java.net.*;

// TCP: Transmission Control Protocol

/** ------ Homework Question 1 ------
 *  One process acts as server and >= 1 processes as clients
 *  Server is always waiting for client connection and messages
 *  Any client can join and talk any time
 *  Keyboard input “\n” from a client signals that he/she is done talking
 */

public class ChatRoomClient {
    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader stdIn = null;
        int portNumber = 4321;

        try {
            echoSocket = new Socket(hostName, portNumber);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            stdIn = new BufferedReader(
                            new InputStreamReader(System.in));
            String userInput;
            System.out.println(in.readLine());

            // do-while loop to ensure client keys in input at least once
            do {
                System.out.println("Type your message: ");
                userInput = stdIn.readLine();
                out.println(userInput);
                out.flush();
            }
            // Loop to stay in chat until keyboard input "\n"
            while (!userInput.isEmpty());
            System.out.println("You have left the chat room");
            echoSocket.close();
            in.close();
            out.close();
            stdIn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            echoSocket.close();
            in.close();
            out.close();
            stdIn.close();
        }

    }
}
