package Cohort_Exercise_9;

import java.util.ArrayList;

public class MatrixThreadArray {
    public static void main(String[] args) throws Exception {

        /** ----- Cohort Exercise 9 -----
         * Generally, the higher the number of threads and the lesser the number of rows,
         * the less time it will take for the program to complete execution
         * Comment and uncomment the variables below to try out
         * Refer to data_cohortEx9.pdf for the data
         * */
        int numOfRows = 1000;
        int numOfThreads = 10;

        // ~5240 ms
//        int numOfRows = 1000;
//        int numOfThreads = 1;

        // ~1223 ms
//        int numOfRows = 500;
//        int numOfThreads = 2;

        // ~384 ms
//        int numOfRows = 250;
//        int numOfThreads = 4;

        // ~146 ms
//        int numOfRows = 125;
//        int numOfThreads = 8;



        // start recording time here
        long startTime = System.currentTimeMillis();

        // We create a two square matrix A & B, both filled with 0's
        int[][] A = newSquareMatrix(numOfRows);
        int[][] B = newSquareMatrix(numOfRows);

        // Recall that for matrix multiplication, resultant matrix has:
        // Rows: num of rows of A
        // Cols: num of cols of B
        int[][] result = new int[A.length][B[0].length];
        ArrayList<MatrixMultiThread> listOfThreads = new ArrayList<>();

        for (int i = 0; i < numOfThreads; i++){
            MatrixMultiThread thread = new MatrixMultiThread(A, B, result,
                    i*(A.length / numOfThreads), (i+1)*(A.length / numOfThreads));
            listOfThreads.add(thread);
            thread.start();
        }

        try {
            for (int i = 0; i < numOfThreads; i++){
                listOfThreads.get(i).join();
            }
            printMatrix(result);
        } catch (InterruptedException e) {
            System.out.println("Thread not finished");
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.print("\n" + "Duration: " + duration + " milliseconds");
    }

    // initialize a square matrix of zeros
    private static int[][] newSquareMatrix(int numOfRows) {
        int[][] squareMatrix = new int[numOfRows][numOfRows];
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfRows; j++) {
                squareMatrix[i][j] = 0;
            }
        }
        return squareMatrix;
    }

    public static void printMatrix(int[][] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.println(" ");
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(" " + result[i][j]);
            }
        }
    }

}





