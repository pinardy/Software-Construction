/**
 * Created by Pin on 09-Feb-17.
 */
public class StockMain {
    public static void main(String[] args) {
        // Create the Subject object
        // It will handle updating all observers
        // as well as deleting and adding them
        StockGrabber stockGrabber = new StockGrabber();

        // Create an Observer that will be sent updates from Subject
        StockObserver observer1 = new StockObserver(stockGrabber);

        observer1.watchStock("ibm");

        stockGrabber.setPrice("ibm", 197.00);
        stockGrabber.setPrice("aapl", 677.60);
        stockGrabber.setPrice("goog", 676.40);
        System.out.println();

        StockObserver observer2 = new StockObserver(stockGrabber);
        observer2.watchStock("aapl");

        stockGrabber.setPrice("ibm", 197.00);
        stockGrabber.setPrice("aapl", 677.60);
        stockGrabber.setPrice("goog", 676.40);

        System.out.println();
        stockGrabber.unregister(observer2);

        stockGrabber.setPrice("ibm", 197.00);
        stockGrabber.setPrice("aapl", 677.60);
        stockGrabber.setPrice("goog", 676.40);
    }


}
