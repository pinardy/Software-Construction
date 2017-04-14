package Cohort_Exercise_1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Apply SPMD (Single Program, Multiple Data) design pattern for concurrent programming to parallelize the program which 
 * approximates $\pi$ by integrating the following formula $4/(1+x^2 )$. Hint: In the SPMD design pattern, all threads 
 * run the same program, operating on different data.
 */
public class Exercise1 {
	public static void main(String[] args) throws Exception {
		int NTHREADS = 5;
		ExecutorService exec = Executors.newFixedThreadPool(NTHREADS - 1);
		double pi = 0;

		// todo: complete the program by writing your code below.
        final double stepSize = 1.0 / NTHREADS;
        for (int i = 0; i < NTHREADS; i++){
            final double a = i * stepSize; // lower limit
            final double b = (i + 1) * stepSize; // upper limit

            Future<Double> result = exec.submit(new Callable<Double> () {
                public Double call() throws Exception {
                    return integrate(a, b);
                }
            });

            pi += result.get();
        }
        System.out.println(pi);
        exec.shutdown();
	}

	public static double f(double x) {
		return 4.0 / (1 + x * x);
	}

	// the following does numerical integration using Trapezoidal rule.
	// a is lower bound, b is upper bound
	public static double integrate(double a, double b) {
		int N = 10000; // preciseness parameter
		double h = (b - a) / (N - 1); // step size
		double sum = 1.0 / 2.0 * (f(a) + f(b)); // 1/2 terms

		for (int i = 1; i < N - 1; i++) {
			double x = a + h * i;
			sum += f(x);
		}

		return sum * h;
	}
}
