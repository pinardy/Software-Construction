package Cohort_Exercise_8;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)


public class RussianTest {
    private Russian russian;
    int a;
    int b;
    int expected;


    public RussianTest(int expected, int a, int b){
        this.expected = expected;
        this.a = a;
        this.b = b;
    }

    @Before
    public void runBeforeEachTest() {
        russian = new Russian();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList (new Object [][] {
                // =-=-=- 100% branch coverage =-=-=-
                { 1,1,1 }, // covers n>0, (n%2==1), !(n%2==1),
                { 6,2,3 }, // covers n>0, (n%2==1), !(n%2==1),
                { 0,1,0 }, // covers n<0
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                // Fault-based testing
                { 0,0,-3 }, // result should be a zero
                { 0,0,0 }, // multiply for zero as arguments and result
                { -3,1,-3 }, // result should be a negative number (However, this fails! We got a zero instead)
                { 4,-2,-2 }, // result should be a positive number (However, this fails! We got a zero instead)
            });
    }

    @Test
    public void test() {
        assertEquals(expected, russian.multiply(a,b));
    }



}
