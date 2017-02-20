package Cohort_Exercise_5;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Exercise5Test {
    Exercise5 e;

    @Before
    public void runBefore(){
        e = new Exercise5();
    }

    @Test
    public void testCheckValue1pass() {
        assertTrue(e.checkValue1(0) == 0);
    }

    @Test
    public void testCheckValue1fail() {
        assertTrue(e.checkValue1(1) == 1);
    }


    @Test
    public void testCheckValue2pass() {
        assertTrue(e.checkValue2(0) == 0);
    }

    @Test
    public void testCheckValue2fail() {
        assertTrue(e.checkValue2(-1) == -1);
    }

    @Test
    public void testCheckValue3check0() {
        assertTrue(e.checkValue3(0) == 0);
    }

    @Test
    public void testCheckValue3checkneg() {
        assertTrue(e.checkValue3(-1) == -1);
    }

    @Test
    public void testCheckValue3checkpos() {
        assertTrue(e.checkValue3(1) == 1);
    }

}
