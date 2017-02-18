import java.util.ArrayList;
import java.util.HashMap;

//Uses the Subject interface to update all Observers

public class StockGrabber implements iSubject {

    private HashMap<Observer, ArrayList<String>> observers;
	private HashMap<String, Double> stocks;
	
	public StockGrabber(){

	    // Create a new HashMap to hold all observers
        observers = new HashMap<>();

		// Creates an HashMap to hold all stocks
		stocks = new HashMap<>();
		stocks.put("ibm", 0.4);
		stocks.put("aapl", 0.01);
		stocks.put("goog", 10000.0);
	}
	
	public void register(Observer newObserver) {
		// Adds a new observer to the ArrayList
        observers.put(newObserver, new ArrayList<>());
	}

	public void unregister(Observer deleteObserver) {
		// Print out message (Have to increment index to match)
        System.out.println("Observer " + ((StockObserver) deleteObserver).getObserverID() + " deleted");
		
		// Removes observer from the ArrayList
		observers.remove(deleteObserver);
	}

	// Get the price of a particular stock
	public double getStock(String stockName) {
		try {
			double price = stocks.get(stockName);
			return price;
		} catch (Exception e) {
			System.out.println("No such stock exists.");
			return -1;
		}
	}

	public void addStock(String stockName, double value) {
		stocks.put(stockName, value);
	}

	@Override
	public void notifyObserver(String stockName) {
		// Cycle through all observers and notifies them of price changes
		for (Observer observer : observers.keySet()) {
			if (observers.get(observer).contains(stockName)) {
				observer.update(stockName);
			}
		}
	}
	
	// Change prices for all stocks and notifies observers of changes
	public void setPrice(String stockName, double newPrice) {
		if (stocks.containsKey(stockName)) {
			stocks.replace(stockName, newPrice);
			notifyObserver(stockName);
		} else {
			System.out.println("That stock does not exist.");
		}
	}

	public HashMap<String,Double> getStocks() {
		return stocks;
	}


	public void updateWatchingAdd(Observer observer, String stockName) {
		if (observers.containsKey(observer)) {
			if (!observers.get(observer).contains(stockName)) {
				observers.get(observer).add(stockName);
			} else {
				System.out.println("You are already watching this stock!");
			}
		} else {
			System.out.println("You are not currently subscribed to me!");
		}
	}

	public void updateWatchingDelete(Observer observer, String stockName) {
		if (observers.containsKey(observer)) {
			if (observers.get(observer).contains(stockName)) {
				observers.get(observer).remove(stockName);
			} else {
				System.out.println("You are not watching this stock.");
			}
		} else {
			System.out.println("You are not currently subscribed to me!");
		}
	}


}
