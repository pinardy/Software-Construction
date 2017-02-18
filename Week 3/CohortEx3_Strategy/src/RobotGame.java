public class RobotGame {

    /** Robot movement units:
     *
     * Defensive: 0 units
     * Normal: 1 unit
     * Aggressive: 2 units
     */

	public static void main(String[] args) {
		Robot r1 = new Robot("Big Robot");
		Robot r2 = new Robot("George v.2.1");
		Robot r3 = new Robot("R2");

		r1.setBehavior(new Defensive());
		r2.setBehavior(new Normal());
		r3.setBehavior(new Aggressive());

		System.out.println(r1.behave());
		System.out.println(r2.behave());
		System.out.println(r3.behave());

		//change the behaviors of each robot.
		r1.setBehavior(new Aggressive());
		r2.setBehavior(new Defensive());
		r3.setBehavior(new Normal());

		System.out.println(r1.behave());
		System.out.println(r2.behave());
		System.out.println(r3.behave());
	}
}