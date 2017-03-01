package Homework_Qn_4;

public class Board {
    static char[][] table = new char[3][3];

    public static String newBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = ' ';
            }
        }
        return "\n";
    }

    public static String displayBoard() {
        String board = "  2  " + table[2][0] + "|" + table[2][1] + " |" + table[2][2] + "\n" +
                "    --+--+--" + "\n" +
                "  1  " + table[1][0] + "|" + table[1][1] + " |" + table[1][2] + "\n" +
                "    --+--+--" + "\n" +
                "  0  " + table[0][0] + "|" + table[0][1] + " |" + table[0][2] + "\n" +
                "     0  1  2 " + "\n";

        return board;
    }

    public static void updateBoard(char turn, int row, int column) {
        table[row][column] = turn;
    }

}
