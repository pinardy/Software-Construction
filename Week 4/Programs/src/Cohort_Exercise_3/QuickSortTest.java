package Cohort_Exercise_3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)

public class QuickSortTest {
    public int[] a;
    public int[] b;
    public QuickSort sorter;


    public QuickSortTest(int[] inpArray, int[] expectedArray){
        a = inpArray;
        b = expectedArray;
    }


    // Collection is a list
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList (new Object [][] {
                { new int[]{1,3,2,5,7}, new int[]{1,2,3,5,7} },
                { new int[]{4,1,2,10,9}, new int[]{1,2,4,9,10} }
        });
    }
    @Before
    public void runBefore(){
        sorter = new QuickSort();
    }

    @Test
    public void quickTest() {
        sorter.sort(a);
        assertEquals(Arrays.toString(a), Arrays.toString(b));
    }

}
