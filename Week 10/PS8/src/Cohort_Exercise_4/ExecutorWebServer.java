package Cohort_Exercise_4;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
 
public class ExecutorWebServer {
	private static int NTHREADS;
	private static Executor exec;
    private static final float utilization = 0.75f;
    private static final float ratio = 0.5f;

    public static void main(String[] args) throws Exception {
		System.out.println("Server is running...");
		ServerSocket socket = new ServerSocket(4321, 1000);

		/* The optimal pool size is:
           M = N * U * (1 + W/C) */

        NTHREADS = Math.round(Runtime.getRuntime().availableProcessors()
                * utilization * (1 + ratio));
        exec = Executors.newFixedThreadPool(NTHREADS);


        while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable () {
				public void run() {
                    try {
                        handleRequest(connection);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
			};
			
			exec.execute(task);
		}
    }

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