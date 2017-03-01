package Homework_Qn_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// TCP: Transmission Control Protocol

/** ------ Homework Question 1 ------
 *  One process acts as server and >= 1 processes as clients
 *  Server is always waiting for client connection and messages
 *  Any client can join and talk any time
 *  Keyboard input “\n” from a client signals that he/she is done talking
 */

public class ChatRoom {
    private static ServerSocket serverSocket;
    private static ArrayList<Socket> listOfSockets = new ArrayList<Socket>();
    private static int ID = 0;
    private static PrintWriter pw;
    private static BufferedReader br;

    public static void main(String[] args) throws Exception {
        System.out.println("Chat Room server initialized");
        System.out.println("Awaiting client response..." + "\n");
        getClient();
    }

    public static void getClient() throws Exception{
        serverSocket = new ServerSocket(4321);
        Thread clientListener = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    try {
                        Socket clientSocket = serverSocket.accept();
                        ID++;
                        pw = new PrintWriter(clientSocket.getOutputStream(), true);
                        br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        pw.println("Connected to the server");
                        pw.flush();
                        System.out.println("Client " + ID + " connected!");
                        Thread clientThread = clientThread();
                        clientThread.start();
                        listOfSockets.add(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        clientListener.start();
    }

    public static Thread clientThread(){
        Thread thread = new Thread(new Runnable(){
            final int threadID = ID;
            @Override
            public void run() {
                String userInput;
                try {
                    while ((userInput = br.readLine()) != null){
                        if (userInput.isEmpty()){
                            System.out.println("Client " + threadID + " has left the chat");
                            break;
                        }
                        System.out.print("Client " + threadID + " says: ");
                        System.out.println(userInput);
                    }
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }
}
