package Cohort_Exercise_5;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.*;

public class LifeCycleWebServerB {
	private static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(3);
	private static final ThreadPoolExecutor exec = new ThreadPoolExecutor(1, 5, 10, TimeUnit.SECONDS, queue);
	
    public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321);
		
		while (true) {
			try {
				final Socket connection = socket.accept();
				Runnable task = new Runnable () {
					public void run() {
							handleRequest(connection);
					}
				};
	
				exec.execute(task);
			} catch (RejectedExecutionException e) {
				if (!exec.isShutdown()) {
					System.out.println("LOG: task submission is rejected.");
				}
			}			
		}
    }
    
    public static void stop() {
    	exec.shutdown();
    }

	protected static void handleRequest(Socket connection) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);                   
			BigInteger number = new BigInteger(in.readLine());
			BigInteger result = factor(number);
			//System.out.println("sending results: " + String.valueOf(result));
			out.println(result);
			out.flush();
			in.close();
			out.close();
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Something went wrong with the connection");
		}
	}
	
	private static BigInteger factor(BigInteger n) {
		BigInteger i = new BigInteger("2");
		BigInteger zero = new BigInteger("0");
		
		while (i.compareTo(n) < 0) {			
			if (n.remainder(i).compareTo(zero) == 0) {
				return i;
			}
			
			i = i.add(new BigInteger("1"));
		}
		
		assert(false);
		return null;
	}
}