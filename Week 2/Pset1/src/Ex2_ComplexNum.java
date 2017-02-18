/**
 * Created by Pin on 23-Jan-17.
 */
public class Ex2_ComplexNum {

    /** (10 points) Cohort Exercise 2:
     * Design and implement a program that supports accepting two complex numbers
     * from the user; adding, subtracting, multiplying, and dividing them;
     * and reporting each result to the user
     */

    public static class ComplexNumber {
        public static void main(String[] args) {

            // ******* TEST CASES FOR CARTESIAN ******* //

            Complex c1 = new Complex(2.0, 4.0);
            c1.type = "cartesian";

            Complex c2 = new Complex(1.0, 2.0);
            c2.type = "cartesian";

            System.out.println("First complex number: " + "\n " + c1);
            System.out.println("Second complex number: " + "\n " + c2);

            System.out.println("Sum of complex numbers: " + "\n " + Complex.add(c1,c2));
            System.out.println("Subtraction of complex numbers: " + "\n " + Complex.sub(c1,c2));
            System.out.println("Multiplication of complex numbers: " + "\n " + Complex.multiply(c1,c2));
            System.out.println("Division of complex numbers: " + "\n " + Complex.divide(c1,c2));


            // ******* TEST CASES FOR POLAR ******* //

//            Complex c3 = new Complex(1, 0.707);
//            c3.type = "polar";
//
//            Complex c4 = new Complex(2, 0.707);
//            c4.type = "polar";

//            System.out.println("Sum of complex numbers: " + "\n " + Complex.add(c3,c4));
//            System.out.println("Subtraction of complex numbers: " + "\n " + Complex.sub(c3,c4));
//            System.out.println("Multiplication of complex numbers: " + "\n " + Complex.multiply(c3,c4));
//            System.out.println("Division of complex numbers: " + "\n " + Complex.divide(c3,c4));

        }

        static class Complex {
            double real;
            double img;
            String type;

            public String toString() {
                double r = Math.sqrt(Math.pow(real, 2) + Math.pow(img, 2));
                double angle = Math.atan(img/real);

                if (type.equals("cartesian")){
                    return String.format("Cartesian form: %.2f +(%.2f)i\n", real,img);
                }else
                    return String.format("Polar form: %.2fcos%.2f + (%.2fsin%.2f)i\n", r, angle, r, angle);
            }

            // cartesian
            public Complex(double num1, double num2) {
                real = num1;
                img = num2;
                type = "cartesian";
            }

            // polar (angle is in rad)
            public Complex(float r, float angle) {
                real = r * Math.cos(angle);
                img = r * Math.sin(angle);
                type = "polar";
            }

            // cartesian
            public static Complex add(Complex a, Complex b) {
                if (!a.type.equals(b.type)) {
                    System.out.println("Not the same type");
                    return null;
                }
                else {
                    Complex sum = new Complex(0, 0);
                    sum.real = a.real + b.real;
                    sum.img = a.img + b.img;

                    return sum;
                }
            }

            public static Complex sub(Complex a, Complex b) {
                if (!a.type.equals(b.type)) {
                    System.out.println("Not the same type");
                    return null;
                }
                else {
                    Complex diff = new Complex(0, 0);
                    diff.real = a.real - b.real;
                    diff.img = a.img - b.img;

                    return diff;
                }
            }

            public static Complex multiply(Complex a, Complex b) {
                // if a and b are not of the same type
                if (!a.type.equals(b.type)) {
                    System.out.println("Not the same type");
                    return null;
                }
                // if a and b are of the same type
                else {
                    double newReal = 0;
                    double newImg = 0;
                    newReal = (a.real * b.real - a.img * b.img); // recall that sqrt of -i = 1
                    newImg = (a.real * b.img + a.img * b.real);
                    Complex multValue = new Complex(newReal, newImg);
                    return multValue;
                }
            }

            public static Complex divide(Complex a, Complex b) {
                // if a and b are not of the same type
                if (!a.type.equals(b.type)) {
                    System.out.println("Not the same type");
                    return null;
                }
                // if a and b are of the same type
                else {
                    double fraction = 1 / (Math.pow(b.real, 2) + Math.pow(b.img, 2));
                    Complex c2 = multiply(a, new Complex(b.real, -b.img));
                    Complex divValue  = new Complex(fraction * c2.real, fraction * c2.img);
                    return divValue;
                }
            }
        }
    }
}
