package Homework_Qn_4;

public class Player {

    static char turn = 'o';

    public static void changePlayer() {
        if (turn == 'o') {
            turn = 'x';
        } else if (turn == 'x') {
            turn = 'o';
        }
    }

    public static void makeMove(String userInput) {
        String[] moves = userInput.split(",");
        int j = Integer.parseInt(moves[0]);
        int i = Integer.parseInt(moves[1]);

        if (GameChecker.checkLegal(i, j)) {
            Board.updateBoard(turn, i, j);
        } else {
            System.out.println("Illegal move");
        }
        Board.displayBoard();
    }
}
