package Cohort_Exercise_2;

/** ----- Cohort Exercise 2 -----
 *  Search for an element in an integer array using two threads.
 */

public class SearchThread extends Thread {
    private int lower;
    private int upper;
    private int[] array;
    private int target;
    private int ID;

    public SearchThread(int ID, int[] array, int lower, int upper, int target) {
        this.upper = upper;
        this.lower = lower;
        this.array = array;
        this.target = target;
        this.ID = ID;
    }

    @Override
    public void run() {
        for (int i = lower; i < upper; i++) {
            if (isInterrupted()){
                System.out.println("Thread " + ID + " is interrupted");
                break;
            }
            if (target == array[i]){
                MultiThreadSearch.isFound = true;
                System.out.println("Thread " + ID + " found " + target + " in position " + i);
                break;
            }
        }
    }

}
