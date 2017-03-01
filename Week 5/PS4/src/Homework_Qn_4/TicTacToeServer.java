package Homework_Qn_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static Homework_Qn_4.GameChecker.checkWinner;

public class TicTacToeServer {

    static ArrayList<Socket> listOfSockets = new ArrayList<>();
    static ArrayList<PrintWriter> outList = new ArrayList<>();
    static ArrayList<BufferedReader> inList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        TicTacToeServer game = new TicTacToeServer();
        ServerSocket serverSocket = new ServerSocket(4321);
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Waiting for players...");


        for (int i = 0; i < 2; i++) {
            Socket player = serverSocket.accept();
            listOfSockets.add(player);
            System.out.println("A player connected");

            PrintWriter out = new PrintWriter(player.getOutputStream(), true);
            outList.add(out);
            BufferedReader in = new BufferedReader(new InputStreamReader(player.getInputStream()));
            inList.add(in);
        }

        System.out.println("(... connection of the two players established ...)");

        // instantiate a new Board at start of game
        for (int i = 0; i < 2; i++) {
            PrintWriter currentOut = outList.get(i);
            currentOut.print(Board.newBoard());
            currentOut.flush();
        }
        System.out.println("Starting new game...");

        while (checkWinner() == false) {
            for (int i = 0; i < 2; i++) {
                PrintWriter currentOut = outList.get(i);
                BufferedReader currentIn = inList.get(i);
                currentOut.print(Board.displayBoard());
                currentOut.println(String.format("Player %d make your move: [1-3],[1-3]", i + 1));
                currentOut.println("end");
                currentOut.flush();

                String position = "";
                boolean legal = false;

                while (!legal) {
                    try {
                        position = currentIn.readLine();    // reading for position
                        legal = true;
                        Player.makeMove(position);
                    } catch (Exception e) {
                        legal = false;
                    }
                }

                System.out.println(Board.displayBoard());
                Boolean winnerFound = GameChecker.checkWinner();
                if (winnerFound) {
                    game.endGame(i);
                    break;
                }
                Player.changePlayer();
            }
        }
    }

    public static void endGame(int playerNo) throws Exception {
        for (int i = 0; i < 2; i++) {
            outList.get(i).println("win");
            outList.get(i).println("endgame");
            outList.get(i).close();
            inList.get(i).close();
            listOfSockets.get(i).close();
        }
        System.out.println("Player " + String.valueOf(playerNo + 1) + " wins");
    }
}
