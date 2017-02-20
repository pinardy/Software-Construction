package Cohort_Exercise_4;

import org.jmock.*;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

/** Cohort Exercise 4:
 * ---- Mocking ----
 * 1) Mock
 * 2) Expectations
 * 3) Action
 * 4) Verification
 */

public class FindMaxTest {

    @Test
    public void testCalculatingMachine() {
        Mockery context = new JUnit4Mockery();

        // mock
        final Sorter sorter = context.mock(Sorter.class);
        final int[] inputArray = new int[] {1,3,2};

        // expectations
        context.checking(new Expectations() {{
            oneOf(sorter).sort(inputArray); // invoke sort from Sorter ONCE (method not there yet)
            will(returnValue(new int[] {1,2,3})); // will return max
        }});

        // actions
        FindMaxUsingSorting.findmax(inputArray, sorter);

        // verification
        context.assertIsSatisfied();
    }
}
