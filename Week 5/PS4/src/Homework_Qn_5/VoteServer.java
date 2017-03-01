package Homework_Qn_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class VoteServer {
    private static ServerSocket serverSocket;
    private static ArrayList<Socket> socketList = new ArrayList<Socket>();
    private static ArrayList<PrintWriter> pwList = new ArrayList<PrintWriter>();
    private static ArrayList<Thread> threadList = new ArrayList<Thread>();
    private static int numOfElectorates = 5;
    private static boolean winnerFound;

    public static void main(String[] args) throws Exception {
        serverSocket = new ServerSocket(4321);
        System.out.println("Waiting for electorates to connect");

        for (int i = 0; i < numOfElectorates; i++) {
            Socket client = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());
            socketList.add(client);
            pwList.add(out);
            threadList.add(new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        while (true){
                            String vote = in.readLine();
                            if ((winnerFound = Boolean.parseBoolean(vote))){
                                for (int j = 0; j < pwList.size(); j++) {
                                    pwList.get(j).close();
                                    socketList.get(j).close();
                                }
                                System.out.println("Sockets closed");
                                break;
                            }
                            for (int j = 0; j < pwList.size(); j++) {
                                pwList.get(j).println(vote);
                                pwList.get(j).flush();
                            }
                        }
                    } catch (IOException e) {
                        for (int j = 0; j < pwList.size(); j++) {
                            try {
                                socketList.get(j).close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        System.out.println("Sockets closed");
                    }
                }
            }));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        System.out.println("Connection done");
    }
}
