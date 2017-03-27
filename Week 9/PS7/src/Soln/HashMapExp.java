package Soln;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapExp {
       private static final int NB_THREADS = 3;
       private static final int NB_TEST_ITERATIONS = 50;      
      
       public static void main(String[] args) {            
             for (int i=0; i<NB_TEST_ITERATIONS; i++) {                                       
            	 Map<String, Integer> map = new ConcurrentHashMap<String, Integer>(2); // ConcurrentHashMap
            	 //HashMap threadSafeMap2 = new HashMap<String, Integer>(2);
            	 //Map<String, Integer> map = Collections.synchronizedMap(threadSafeMap2);
            	 long timeBefore = System.currentTimeMillis();
                    
                 Thread[] workers = new Thread[NB_THREADS];
                 for (int j = 0; j < NB_THREADS; j++) {                          
                     workers[j] = new WorkerThread(map);
                     workers[j].start();              
                 }

                 try {
                    for (int j = 0; j < NB_THREADS; j++) {                                                  
						workers[j].join();						              
                    }
                 } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
                 }
                   
                 long timeAfter = System.currentTimeMillis();
                 Float totalProcessingTime = new Float( (float) (timeAfter - timeBefore) / (float) 1000);
                   
                 System.out.println("All threads completed in "+totalProcessingTime+" seconds");
             }
       }
}

class WorkerThread extends Thread {
    private Map<String, Integer> map = null;

    public WorkerThread(Map<String, Integer> map) {
          this.map = map;
    }

    public void run() {
          for (int i=0; i<500000; i++) {
                 // Return 2 integers between 1-1000000 inclusive
                 Integer newInteger1 = (int) Math.ceil(Math.random() * 1000000);
                 Integer newInteger2 = (int) Math.ceil(Math.random() * 1000000);
                 // 1. Attempt to retrieve a random Integer element
                 map.get(String.valueOf(newInteger1));
                 // 2. Attempt to insert a random Integer element
                 map.put(String.valueOf(newInteger2), newInteger2);
          }
    }
}