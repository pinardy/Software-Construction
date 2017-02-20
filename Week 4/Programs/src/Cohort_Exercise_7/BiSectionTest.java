package Cohort_Exercise_7;

import org.junit.Before;
import org.junit.Test;

// For each statement, there must be one test case which executes it, if feasible.

public class BiSectionTest {
	private BiSectionExample bi;
	
	@Before 
	public void runBeforeEachTest() {
		bi = new BiSectionExample();
	}
	
	@Test
	public void test4MethodCoverage () {
		//System.out.print(bi.root(0.5, 100.3, 0.1));
		assert (bi.root(0.5, 100.3, 0.1) >= 100);
		//question: should we assert the returned value is the exact value we expect?
	}
	
	@Test 
	public void test4LoopCoverage1 () { // loop once
		assert(bi.root(0,100,80) > 50);
	}

	/** It is impossible to print out '4' without entering an infinite loop
	 * because to enter the while loop, d has to be smaller than e.
	 * Hence, the value middle will always be smaller than e.
	 */

    @Test // (d >= e) --> throws IllegalArgumentException
    public void print4 (){
        assert (bi.root(4,5,0) > 3);
    }


}
