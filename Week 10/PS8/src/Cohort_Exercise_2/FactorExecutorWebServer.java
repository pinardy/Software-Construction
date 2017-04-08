package Cohort_Exercise_2;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
 
public class FactorExecutorWebServer {
	private static final int NTHREADS = 100;

	// creates an active framework where the most number of active threads is NTHREADS
    // Number of active threads in this executor is at most 100
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
	
    public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321, 1000);

		// How the task is executed is not specified here
        // we don't know if this execution is sequential or parallelized
		while (true) {
			final Socket connection = socket.accept();
			// Here is the task being created
			Runnable task = new Runnable () {
				public void run() {
                    try {
                        handleRequest(connection);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
			};
			// submit the task to the Executor framework
            // if we have more than 100 tasks, the task will wait to be executed
            // when a task finishes, a thread gets assigned to the waiting task
			exec.execute(task);
		}
    }

    // this is a task
	protected static void handleRequest(Socket connection) throws IOException {
        PrintWriter out = new PrintWriter(connection.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        BigInteger number = new BigInteger(in.readLine());
        BigInteger result = factor(number);
        out.println(result.toString());
        out.flush();
        out.close();
        in.close();
        connection.close();
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