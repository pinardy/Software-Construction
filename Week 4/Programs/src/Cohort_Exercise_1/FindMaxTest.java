package Cohort_Exercise_1;

import static org.junit.Assert.*;

import Cohort_Exercise_1.FindMax;
import org.junit.Test;

/** Cohort Exercise 1:
 * Given FindMax.java, write three test cases:
 *  1) resulting in Failure,
 *  2) resulting in Error
 *  3) resulting in Pass.
 */

public class FindMaxTest {


    @Test // 1) Failure
    public void calculateMax() {
        int[] testInput = new int[]{5,6,17,8,2,20};
        int result = FindMax.max(testInput);

        assertEquals(20, result);
    }

    @Test // 2) Error
    public void calculateMax2() {
        int[] testInput = new int[]{};
        int result = FindMax.max(testInput);

        assertEquals(null, result);
    }

    @Test // 3) Pass
    public void testSetupMax() {
        FindMax m = new FindMax();
    }

}
