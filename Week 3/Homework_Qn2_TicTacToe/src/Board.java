/**
 * Created by Pin on 07-Feb-17.
 */
public class Board {
    static char [][] table = new char[3][3];

    public static void newBoard() {
        System.out.println("Starting new game...");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = ' ';
            }
        }
        displayBoard();
    }

    public static void displayBoard() {
        System.out.println("  2  " + table[2][0] + "|" + table[2][1] + " |" + table[2][2]);
        System.out.println("    --+--+--");
        System.out.println("  1  " + table[1][0] + "|" + table[1][1] + " |" + table[1][2]);
        System.out.println("    --+--+--");
        System.out.println("  0  " + table[0][0] + "|" + table[0][1] + " |" + table[0][2]);
        System.out.println("     0  1  2 ");
    }

    public static void updateBoard(char turn, int row, int column) {
        table[row][column] = turn;
    }

}
