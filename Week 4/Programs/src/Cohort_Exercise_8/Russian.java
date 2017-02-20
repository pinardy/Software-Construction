package Cohort_Exercise_8;

public class Russian {
	// fault-based testing: overflow

	public static int multiply (int m, int n) {
		int toReturn = 0;
		
		while (n > 0) {
			// if n is odd
			if (n%2 == 1) {
				toReturn += m;
			}
			
			m = m*2; 
			n = n/2;
		}
		
		return toReturn;
	}
}
