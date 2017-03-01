package Homework_Qn_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatRoom2 {

    private static ServerSocket serverSocket;
    private static ArrayList<Socket> listOfSockets = new ArrayList<Socket>();
    private static ArrayList<PrintWriter> listOfPw = new ArrayList<PrintWriter>();
    private static ArrayList<BufferedReader> listOfBr = new ArrayList<BufferedReader>();


    public static void main(String[] args) throws Exception {
        System.out.println("Chat Room server initialized");
        System.out.println("Awaiting client response..." + "\n");
        getClient();
        startChat();
    }

    public static void startChat() {
        while(true){
            for (int i = 0; i < listOfBr.size(); i++) {
                PrintWriter currentPw = listOfPw.get(i);
                BufferedReader currentBr = listOfBr.get(i);

                try{
                    currentPw.println("serverEndMessage");
                    currentPw.flush();
                    String input;
                    while ((input = currentBr.readLine()) != null && !input.equals("endMessage")){
                        if (input.equals("")){
                            System.out.println("Client " + i + " has left the chat!");
                            listOfBr.get(i).close();
                            listOfSockets.get(i).close();
                            listOfSockets.remove(i);
                            listOfBr.remove(i);
                            break;
                        }
                        System.out.print("Client " + i + " says: ");
                        System.out.println(input);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void getClient() throws IOException {
        serverSocket = new ServerSocket(4321);
        while(true) {
            try {
                serverSocket.setSoTimeout(10000);
                Socket clientSocket = serverSocket.accept();
                PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                listOfPw.add(pw);
                listOfSockets.add(clientSocket);
                listOfBr.add(br);
                pw.println("You are now connected to the server!");
                pw.println("Please wait...");
                pw.flush();
            } catch (java.net.SocketTimeoutException e){
                break;
            }
        }
    }
}
