/**
 * Created by Pin on 07-Feb-17.
 */
public class GameChecker {


    public static boolean checkTie() {
        for (int i = 0; i < 3; i++)
            for (int p=0; p < 3; p++)
                if(Board.table [i][p]==' ')
                    return false;
        return true;
    }

    public static boolean checkColumns(){
        if (Board.table [0][0] == Board.table[1][0] && Board.table[1][0] == Board.table[2][0]
                && (Board.table [0][0]=='x' || Board.table [0][0]=='o'))
            return true;
        else if (Board.table [0][1] == Board.table[1][1] && Board.table[1][1] == Board.table[2][1]
                && (Board.table [0][1]=='x' || Board.table [0][1]=='o'))
            return true;
        else if (Board.table [0][2] == Board.table[1][2] && Board.table[1][2] == Board.table[2][2]
                && (Board.table [0][2]=='x' || Board.table [0][2]=='o'))
            return true;
        else {
            return false;
        }
    }

    public static boolean checkRows(){
        if (Board.table [0][0] == Board.table[0][1] && Board.table[0][1] == Board.table[0][2]
                && (Board.table [0][0]=='x' || Board.table [0][0]=='o'))
            return true;
        else if (Board.table [1][0] == Board.table[1][1] && Board.table[1][1] == Board.table[1][2]
                && (Board.table [1][0]=='x' || Board.table [1][0]=='o'))
            return true;
        else if(Board.table [2][0] == Board.table[2][1] && Board.table[2][1] == Board.table[2][2]
                && (Board.table [2][0]=='x' || Board.table [2][0]=='o'))
            return true;
        else {
            return false;
        }
    }

    public static boolean checkDiagonals(){
        if (Board.table [0][0] == Board.table[1][1] && Board.table[1][1] == Board.table[2][2]
                && (Board.table [0][0]=='x' || Board.table [0][0]=='o'))
            return true;
        else if (Board.table [2][0] == Board.table[1][1] && Board.table[1][1] == Board.table[0][2]
                && (Board.table [2][0]=='x' || Board.table [2][0]=='o'))
            return true;
        else {
            return false;
        }
    }

    public static boolean checkWinner() {
        // if there is a winning column, a winning row, or a winning diagonal
        if ( (checkColumns() == true) || (checkRows() == true) || (checkDiagonals() == true) ) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkLegal(int row, int column) {
        if( (row > 2 || column > 2) || (row < 0 || column < 0) )
            return false;

        else if(Board.table[row][column]=='x' || Board.table[row][column]=='o')
            return false;

        return true;
    }
}
