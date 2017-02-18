/** Tic Tac Toe
 * Two users take turns to make a move. The game is over after a winner is decided
 */

public class TicTacToe {

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");

        // New game, first turn
        Board.newBoard();
        System.out.println("Your input is in the form of a coordinate x,y ");
        Player.makeMove();

        // game ends in at most 9 moves
        for (int i = 0; i < 10; i++){
            turnSequence();
        }
    }

    public static void turnSequence(){
        Player.changePlayer();
        Player.makeMove();

        while (GameChecker.checkTie()){
            System.out.println("We got a tie!");
            System.exit(0);
        }
        while (GameChecker.checkWinner()){
            System.out.println("Player " + Player.turn + " wins!");
            System.exit(0);
        }
    }
}
