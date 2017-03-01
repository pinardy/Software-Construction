package Homework_Qn_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class TicTacToeClient {
    static BufferedReader in;
    static BufferedReader playerInput;
    static PrintWriter out;
    static Socket echoSocket;

    public static void main(String[] args) throws Exception{

        String hostName = "localhost";
        int portNumber = 4321;

        echoSocket = new Socket(hostName, portNumber);
        in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
//        playerInput = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(echoSocket.getOutputStream(), true);

        System.out.println("Please wait for other player...");
        while (!echoSocket.isClosed()){
            String stringFromServer;
            boolean legal = false;

            // Getting the gameboard
            while ((stringFromServer = in.readLine()) != null && !stringFromServer.equals("end")){
                if (stringFromServer.equals("endgame")){
                    endGame();
                    legal = true;
                    break;
                }
                System.out.println(stringFromServer);
            }
            // Get user input and flushes it
            while (!legal){
                String regex = "\\s*[0,1,2]\\s*,\\s*[0,1,2]\\s*";
                try{
                    playerInput = new BufferedReader(new InputStreamReader(System.in));
                    String position = playerInput.readLine();

                    // checks if input is of correct form
                    if (position.matches(regex)){
                        String[] moves = position.split(",");
                        int j = Integer.parseInt(moves[0]);
                        int i = Integer.parseInt(moves[1]);

                        // checks if the move is a legal one
                        if (GameChecker.checkLegal(i,j)) {
                            //TODO: Should be illegal move!!!!
                            System.out.println("sup");
                            Player.makeMove(position);
                            legal = true;
                            out.println(position);
                            out.flush();
                        }
                    }
                } catch (Exception e){
                    System.out.println("Please key the correct format: [1-3],[1-3]");
                }
            }
        }
    }

    public static void endGame() throws IOException {
        in.close();
        out.close();
        playerInput.close();
        echoSocket.close();
    }

}
