package Cohort_Exercise_2;

/** ----- Cohort Exercise 2 -----
 *  Search for an element in an integer array using two threads.
 */

public class MultiThreadSearch {

    public static boolean isFound = false;

    public static void main(String[] args) throws InterruptedException {
        int target = 7; // target value to search
        int[] array = {6,1,3,7,2,9};

        // search lower half
        SearchThread thread1 = new SearchThread(1, array, 0, array.length / 2, target);

        // search upper half
        SearchThread thread2 = new SearchThread(2, array, array.length / 2 , array.length, target);

        // start the threads
        thread1.start();
        thread2.start();

        // busy waiting
        while (true) {
            if (MultiThreadSearch.isFound) {
                if (thread1.isAlive()){
                    thread1.interrupt();
                }
                if (thread2.isAlive()){
                    thread2.interrupt();
                }
                System.out.println("The element is in the list.");
                break;
            }
            if (!MultiThreadSearch.isFound && !thread1.isAlive() && !thread2.isAlive()) {
                System.out.println("The element is not in the list.");
                break;
            }

        }
    }
}