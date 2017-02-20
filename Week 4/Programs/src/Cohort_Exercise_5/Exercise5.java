package Cohort_Exercise_5;

public class Exercise5 {
    public Exercise5() {
    }

    // only 1 part of specs
    public int checkValue1(int input) {
        return 0;
    }

    // not full specs
    public int checkValue2(int input) {
        int output;
        if (input == 0) {
            output = 0;
        } else {
            output = 1;
        }
        return output;
    }

    // full specs
    public int checkValue3(int input) {
        int output;
        if (input == 0) {
            output = 0;
        } else if (input > 0){
            output = 1;
        } else {
            output = -1;
        }
        return output;
    }

}
