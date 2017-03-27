package Cohort_Exercise_7;

import java.util.ArrayList;
import java.util.concurrent.Phaser;


public class Exam {

    private static int numOfStudents = 5;
    private static ArrayList<Student> classList = new ArrayList<>();
    private static Phaser phaser = new Phaser();


    public static void main(String[] args) {

        // Using Phaser
		phaser.register();
		for (int i = 0; i < numOfStudents; i++) {
			classList.add(new Student(phaser));
		}

		System.out.println("Welcome to Software Construction finals.\n" +
                "We are currently waiting for all students to be ready before we begin the exam.\n");

		for (Student student : classList) {
			student.start();
		}
		try {
			Thread.sleep(100);	// ensures that all threads are blocked at the arriveAndAwaitAdvance
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phaser.arriveAndDeregister(); // to start
		phaser.register();
		phaser.arriveAndAwaitAdvance();
		System.out.println("All students completed test. Examiner leaves with scripts");
    }

}

class Student extends Thread{

    private Phaser phaser;

    public Student(Phaser phaser){
        phaser.register();
        this.phaser = phaser;
    }

    @Override
    public void run() {

        // Using Phaser
		System.out.println("Student waiting");
		phaser.arriveAndAwaitAdvance();
		System.out.println("Starting exam");

        // simulate doing exam
        try {
            sleep(100); // ensures everyone starts at the same time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // completion of exam
        System.out.println("Done with exam. Student leaves exam hall");
		phaser.arrive();

    }
}