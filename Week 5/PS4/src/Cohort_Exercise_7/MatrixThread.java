package Cohort_Exercise_7;

public class MatrixThread {
    /** ----- Cohort Exercise 7 -----
     * Write a program to multiply two square matrices A and B
     * using two threads (in addition to the main thread).
     */

    public static void main(String[] args) throws Exception {
        int[][] A = { {1,2,3}, {1,2,3}, {1,2,3}, {1,2,3} };
        int[][] B = { {1,1,1}, {1,1,1}, {1,1,1} };

        // Recall that for matrix mult, resultant matrix has:
        // rows: num of rows of A
        // cols: num of cols of B
        int[][] result = new int[A.length][B[0].length];

        MatrixMultiThread thread2 = new MatrixMultiThread(A, B, result, 0, A.length / 2 + 1);
        MatrixMultiThread thread1 = new MatrixMultiThread(A, B, result, A.length / 2 + 1, A.length);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            printMatrix(result);
        } catch (InterruptedException e) {
            System.out.println("A thread didn't finish!");
        }
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






