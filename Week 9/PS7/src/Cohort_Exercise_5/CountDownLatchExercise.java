package Cohort_Exercise_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountDownLatchExercise {

    private static final int numOfThreads = 5;
    private static final CountDownLatch latch = new CountDownLatch(7);
    private static ArrayList<SearchF> listOfThreads = new ArrayList<>();


    public static void main(String args[]) throws InterruptedException {

        ArrayList<String> grades = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
        String[] listOfGrades = new String[100];

        for (int i = 0; i < 100; i++) {
            Collections.shuffle(grades);
            listOfGrades[i] = grades.get(0);
            System.out.print(grades.get(0) + ", ");
        }


        for (int i = 0; i < numOfThreads; i++) {
            SearchF thread = new SearchF(listOfGrades, i * listOfGrades.length / numOfThreads, (i + 1) * listOfGrades.length / numOfThreads, latch);
            listOfThreads.add(thread);
            thread.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counted 7 Fs, stopping all threads");
        for (int i = 0; i < numOfThreads; i++) {
            listOfThreads.get(i).interrupt();
        }

    }
}


class SearchF extends Thread {
    private final String[] data;
    private final int start;
    private final int end;
    private final CountDownLatch latch;

    public SearchF(String[] data, int start, int end, CountDownLatch latch) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.latch = latch;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            if (data[i].equals("F")) {
                System.out.println("Found F");
                latch.countDown(); // reduce count of CountDownLatch by 1
            }

            if (isInterrupted()) {
                System.out.println("Stopping thread");
                break;
            }
        }
    }

}
