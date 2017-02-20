public interface iSubject {
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObserver(String stockName);
}