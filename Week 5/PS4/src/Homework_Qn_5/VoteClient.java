package Homework_Qn_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;


public class VoteClient {

    private static int numberOfElec = 5;
    private static int voteA = 0;
    private static int voteB = 0;

    private static boolean voted = false;
    private static boolean winnerFound = false;

    /* ReentrantLock is mutual exclusive lock, which can be used to provide lock to longest waiting thread
     This allows the thread holding the lock to re-enter a critical section */
    private static ReentrantLock lockA = new ReentrantLock();
    private static ReentrantLock lockB = new ReentrantLock();


    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        int portNumber = 4321;

        Socket echoSocket = new Socket(hostName, portNumber);
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        String userInput;

        // new thread for listening to incoming votes
        new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while (counter < numberOfElec){
                    try {
                        String vote = in.readLine();
                        if (vote.equals("A")){
                            voteForA();
                        } else if (vote.equals("B")){
                            voteForB();
                        }
                        counter++;
                    } catch (Exception e) {
                        // carry on
                    }
                }
            }
        }).start();

        // while voters haven't voted yet
        while (!voted){
            System.out.println("Pick a candidate to vote for: [A/B]");
            userInput = stdIn.readLine();
            if (userInput.equals("A") || userInput.equals("B")){
                voted = true;
                out.println(userInput);
                out.flush();
                System.out.println("Vote registered. Please wait for the results");
                break;
            } else {
                System.out.println("Invalid input");
                continue;
            }
        }
        while(!winnerFound){
            winnerFound = checkWinner();
        }
        out.print(true);
        out.flush();

        // closure
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();

        System.out.println("Closed");

    }

    private static boolean checkWinner(){
        int totalVotes = voteA + voteB;

        // if all the voters have voted already
        if (totalVotes == numberOfElec){
            if (voteA > voteB) {
                System.out.println("A is the winner!");
            } else {
                System.out.println("B is the winner!");
            }
            return true;
        }
        return false;
    }

    private static void voteForA() {
        lockA.lock();
        voteA++;
        lockA.unlock();
    }

    private static void voteForB() {
        lockB.lock();
        voteB++;
        lockB.unlock();
    }
}
