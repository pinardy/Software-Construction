import java.util.ArrayList;

/**
 * Created by Pin on 23-Jan-17.
 *
 * Cohort Exercise 1 (15 min)
 * Write a function which takes three numbers and return the second largest one.
 * Write a function which takes four numbers and return the second largest one.
 */
public class Ex1_LargestOne {
    public static void main(String[] args) {
        System.out.println(secondLargest(1,3,5));
        System.out.println(secondLargest(6,3,4));
        System.out.println(secondLargest2(6,3,4, 9));
        System.out.println(divideAndConquer(6,3,4,9));
    }

    public static Integer secondLargest(int a, int b, int c){
        ArrayList<Integer> listOfNumbers = new ArrayList<>();
        listOfNumbers.add(a);
        listOfNumbers.add(b);
        listOfNumbers.add(c);

        int maxNum = 0;
        int secondMaxNum = 0;

        for (int n : listOfNumbers){
            if (maxNum < n){
                secondMaxNum = maxNum;
                maxNum = n;
            } else if (secondMaxNum < n){
                secondMaxNum = n;
            }
        }
        return secondMaxNum;
    }

    public static Integer secondLargest2(int a, int b, int c, int d){
        ArrayList<Integer> listOfNumbers = new ArrayList<>();
        listOfNumbers.add(a);
        listOfNumbers.add(b);
        listOfNumbers.add(c);
        listOfNumbers.add(d);

        int maxNum = 0;
        int secondMaxNum = 0;

        for (int n : listOfNumbers){
            if (maxNum < n){
                secondMaxNum = maxNum;
                maxNum = n;
            } else if (secondMaxNum < n){
                secondMaxNum = n;
            }
        }
        return secondMaxNum;
    }

    public static Integer divideAndConquer(int a, int b, int c, int d){
        int largest_group1 = 0;
        int largest_group2 = 0;
        int second_largest = 0;

        // split into a & b, c & d
        if (a > b){
            System.out.println(a + " is the largest of the first group");
            largest_group1 = a;
        } else if (b > a){
            System.out.println(b + " is the largest of the first group");
            largest_group1 = b;
        }

        if (c > d){
            System.out.println(c + " is the largest of the second group");
            largest_group2 = c;

        } else if (d > c){
            System.out.println(d + " is the largest of the second group");
            largest_group2 = d;
        }

        // compare the largest of two groups with each other
        if (largest_group1 < largest_group2){
            second_largest = largest_group1;
        }
        else if (largest_group2 < largest_group1){
            second_largest = largest_group2;
        }

        return second_largest;

    }
}
