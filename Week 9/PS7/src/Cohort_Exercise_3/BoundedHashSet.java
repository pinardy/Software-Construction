package Cohort_Exercise_3;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSet<T> {
	private final Set<T> set;
	private final Semaphore semaphore ;


    public BoundedHashSet (int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
        semaphore = new Semaphore(10, true);
	}
	
	public boolean add(T o) throws InterruptedException {
        boolean bool = set.add(o);

        if (set.add(o) == true){
            semaphore.acquire();
            System.out.println("Integer added, permits left: " + semaphore.availablePermits());
        }
        return bool;
	}
	
	public boolean remove (Object o) {
		boolean bool = set.remove(o);

		if (set.remove(o) == true){
		    semaphore.release();
            System.out.println("Integer removed, permits left: " + semaphore.availablePermits());
        }
        return bool;
	}
}