package Cohort_Exercise_1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WorkerThread extends Thread {

    private Map<String, Integer> map = null;
    private static ArrayList<WorkerThread> synchroList = new ArrayList<>();
    private static ArrayList<WorkerThread> concurrentList = new ArrayList<>();
    private static int numOfThreads = 10;

    public static void main(String[] args) {
        // HashMap is not thread-safe
        Map<String, Integer> locations = new HashMap<>(100);
        Map<String, Integer> hashMap = Collections.synchronizedMap(new HashMap<String, Integer>());

        // ConcurrentHashMap is thread-safe
        // ConcurrentHashMap does not allow exclusive locking on the map
        ConcurrentHashMap<String,Integer> concHashMap = new ConcurrentHashMap<>(100);

        // create our threads and add them to list
        for (int i = 0; i < numOfThreads; i++){
//            synchroList.add(new WorkerThread(locations)); // thread unsafe
            synchroList.add(new WorkerThread(Collections.synchronizedMap(locations))); // thread safe
            concurrentList.add(new WorkerThread(concHashMap));
        }

        // start recording time
        long timeIn = System.currentTimeMillis();

        for (int i = 0; i < numOfThreads; i ++){
            synchroList.get(i).start();
            try {
                synchroList.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("synchronizedMap time: " + (System.currentTimeMillis() - timeIn));

        // start recording time
        long timeIn1 = System.currentTimeMillis();

        for (int i = 0; i < numOfThreads; i ++){
            concurrentList.get(i).start();
            try {
                concurrentList.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("concurrentHashMap time: " + (System.currentTimeMillis() - timeIn1));
    }

    public WorkerThread(Map<String, Integer> map) {
        this.map = map;
    }

    public void run() {
        for (int i = 0; i < 500000; i++) {
            // Return 2 random integers
            Integer newInteger1 = (int) Math.ceil(Math.random());
            Integer newInteger2 = (int) Math.ceil(Math.random());
            // 1. Attempt to retrieve a random Integer element
            map.get(String.valueOf(newInteger1));
            // 2. Attempt to insert a random Integer element
            map.put(String.valueOf(newInteger2), newInteger2);
        }
    }

}