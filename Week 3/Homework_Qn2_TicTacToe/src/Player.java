import java.util.Scanner;

/**
 * Created by Pin on 09-Feb-17.
 */
public class Player {

    static char turn = 'o';

    public static void changePlayer() {
        if (turn == 'o') {
            turn = 'x';
        }
        else  if (turn == 'x') {
            turn = 'o';
        }
    }

    public static void makeMove(){
        System.out.println("Next move by player: " + turn);
        System.out.println("Enter the coordinate of your move: ");

        Scanner sc = new Scanner(System.in);

        // if the user input is of the correct form "[1-3],[1-3]"
        String regex = "\\s*[0,1,2]\\s*,\\s*[0,1,2]\\s*";

        String input =  sc.next();
        if (input.matches(regex)) {
            String[] moves = input.split(",");
            int j = Integer.parseInt(moves[0]);
            int i = Integer.parseInt(moves[1]);

             if (GameChecker.checkLegal(i,j)) {
                 Board.updateBoard(turn, i, j);
             } else{
                 System.out.println("Illegal move");
                 makeMove();
             }

        } else {
            System.out.println("Invalid move");
            makeMove();
        }

        Board.displayBoard();
    }
}
