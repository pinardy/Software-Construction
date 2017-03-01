package Cohort_Exercise_9;

class MatrixMultiThread extends Thread {
    private int[][] A;
    private int[][] B;
    private int[][] result;
    int lower;
    int upper;

    public MatrixMultiThread(int[][] A, int[][] B, int[][] result, int lower, int upper) {
        this.A = A;
        this.B = B;
        this.result = result;
        this.lower = lower;
        this.upper = upper;
    }

    public void run() {
        for (int i = lower; i < upper; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

}
