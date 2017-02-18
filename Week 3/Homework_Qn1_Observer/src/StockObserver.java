//Represents each Observer that is monitoring changes in the subject

import java.util.HashMap;

public class StockObserver implements Observer {

	private HashMap<String, Double> stockMap = new HashMap<>();


    // Static used as a counter
	private static int observerIDTracker = 0;
	
	// Used to track the observers
	private int observerID;
	
	// Will hold reference to the StockGrabber object
	private iSubject stockGrabber;
	
	public StockObserver(iSubject stockGrabber){

		// Store the reference to the stockGrabber object so
		// I can make calls to its methods
		this.stockGrabber = stockGrabber;
		
		// Assign an observer ID and increment the static counter
		this.observerID = ++observerIDTracker;
		
		// Message notifies user of new observer
		System.out.println("New Observer " + this.observerID);
		
		// Add the observer to the Subjects ArrayList
		stockGrabber.register(this);
	}
	
	// Called to update all observers
	@Override
	public void update(String stockName) {
		stockMap.replace(stockName, ((StockGrabber) stockGrabber).getStock(stockName));

		printThePrices();
	}
	
	public void printThePrices(){
		for (String stockName : stockMap.keySet()) {
            System.out.println(stockName + ": " + stockMap.get(stockName));
		}
	}


    public int getObserverID() {
        return observerID;
    }

	// Add a stock to HashMap which contains the watched stocks
	public void watchStock(String stockName) {
		double price = ((StockGrabber) stockGrabber).getStock(stockName);
		if (price < 0) {
			System.out.println("No such stock exists.");
		} else if (stockMap.containsKey(stockName)) {
			System.out.println("You are already watching this stock.");
		} else {
			stockMap.put(stockName, price);
			((StockGrabber) stockGrabber).updateWatchingAdd(this, stockName);
		}
	}

	// Remove a currently watched stock
	public void removeFromWatched(String stockName) {
		if (stockMap.containsKey(stockName)) {
            stockMap.remove(stockName);
			((StockGrabber)stockGrabber).updateWatchingDelete(this, stockName);
		} else {
			System.out.println("You're not currently watching this stock!");
		}
	}


}
