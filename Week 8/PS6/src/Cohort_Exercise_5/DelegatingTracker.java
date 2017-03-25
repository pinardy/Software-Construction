package Cohort_Exercise_5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap; //this is thread-safe!
import java.util.concurrent.ConcurrentMap; //this is thread-safe!

//is this class thread-safe?

/**
 *  This class is thread-safe. The only way to access locations is through the method calls.
 *  Since the setters and getters are synchronized, only one thread which is calling these methods
 *  can be running at any time
 */

public class DelegatingTracker {
	private final ConcurrentMap<String, Point> locations;

	// No need to synchronize constructor because only the thread that creates
    // an object should have access to it while it is being constructed.
	public DelegatingTracker(Map<String, Point> locations) {
		this.locations = new ConcurrentHashMap<String, Point>(locations);
	}

	// this is not an escape since we are returning an immutable object
	public synchronized Map<String, Point> getLocations () {
		return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
	}
	
	// is this an escape? This is an escape, but it is ok since Point is thread safe.
    // we fix it by having a clone
	public synchronized Point getLocation (String id) {
//        return locations.get(id);

        Point clone = new Point(locations.get(id));
        return clone;
	}

	// this method is thread safe as String objects are immutable
    // other parameters are of primitive types
	public synchronized void setLocation (String id, int x, int y) {
		if (!locations.containsKey(id)) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
		// locations.get(id) returns the respective Point object
		locations.get(id).set(x, y);
	}
	
	// Point class is thread safe
	// is a Point object mutable? No (because not passing the reference)
	class Point {
		private int x, y;

		// using array elements, which are of primitive type
        // there is no object reference. This is fine
		private Point (int[] a) { 
			this(a[0], a[1]);
		}

        public Point (Point p) {
            // this is fine. refer to get() method
            this(p.get());
        }

		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}

		public synchronized int[] get() {
		    // this is creating a completely new object
            // there is no object reference
			return new int[] {x, y};
		}

		// synchronized takes an object of type "this"
        // set is atomic with respect to each object
		public synchronized void set(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
