package Cohort_Exercise_4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class test extends Thread {
	TrackerFixed trackerFixed;
	
	public test (TrackerFixed tra) {
		this.trackerFixed = tra;
	}
	
	public void run () {
        TrackerFixed.MutablePoint loc = trackerFixed.getLocation("somestring");
		loc.x = -1212000;
	}
}

//is this class thread-safe?
public class TrackerFixed {
	//@guarded by this
	private final Map<String, MutablePoint> locations;
	
	//the reference locations, is it going to be an escape?
	public TrackerFixed(Map<String, MutablePoint> locations) {
        Map<String, TrackerFixed.MutablePoint> toSet = new ConcurrentHashMap<>();
        for (String key: locations.keySet()) {
            TrackerFixed.MutablePoint newPoint = new TrackerFixed.MutablePoint(locations.get(key).x, locations.get(key).y);
            toSet.put(key, newPoint);
        }
        this.locations = toSet;
	}
	
	//is this an escape? (can locations be modified outside the class?)
	public synchronized Map<String, MutablePoint> getLocations () {
		Map<String, TrackerFixed.MutablePoint> toReturn = new ConcurrentHashMap<>();
		for (String key: locations.keySet()) {
			TrackerFixed.MutablePoint newPoint = new TrackerFixed.MutablePoint(locations.get(key).x, locations.get(key).y);
			toReturn.put(key, newPoint);
		}
		return toReturn;
	}
	
	//is this an escape? (can locations be modified outside the class?)
	public synchronized MutablePoint getLocation (String id) {
		MutablePoint loc = locations.get(id);
		return loc;
	}
	
	public synchronized void setLocation (String id, int x, int y) {
		MutablePoint loc = locations.get(id);
		
		if (loc == null) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
		
		loc.x = x;
		loc.y = y;
	}
	
	//this class is not thread-safe (why?) and keep it unmodified.
	class MutablePoint {
		public int x, y;

		public MutablePoint (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public MutablePoint (MutablePoint p) {
			this.x = p.x;
			this.y = p.y;
		}
	}
}
