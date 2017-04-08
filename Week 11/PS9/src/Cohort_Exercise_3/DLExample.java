package Cohort_Exercise_3;

import java.util.HashSet;
import java.util.Set;

/** Reason for deadlock:
 *
 There is a possibility of one thread locking on Dispatcher first then Taxi (in setLocation)
 and the other thread locking on Taxi first then Dispatcher (in getImage)

 This could happen when dispatcher.notifyAvailable(this) is called in Taxi
 and getImage() is called in Dispatcher.

 When t.getLocation() is trying to get the lock on the Taxi object that is calling
 dispatcher.notifyAvailable(this), both threads will be stuck in a deadlock
 as they are waiting for each other to release the lock.
  */

public class DLExample {
	
}


class Taxi {
    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

	public synchronized Point getLocation() {
        return location;
    }

    // method locks on taxi
    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination))
            // locks on dispatcher
            dispatcher.notifyAvailable(this);
    }

    public synchronized Point getDestination() {
        return destination;
    }
}

class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<Taxi>();
        availableTaxis = new HashSet<Taxi>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }
    // method locks on dispatcher
    public synchronized Image getImage() {
        Image image = new Image();
        // locks on taxi
        // this is to prevent exception being thrown when iterating through the list
        for (Taxi t : taxis)
            image.drawMarker(t.getLocation());
        return image;
    }
}

class Image {
    public void drawMarker(Point p) {
    }
}

class Point {
	
}

