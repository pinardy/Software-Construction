/**
 * Created by Pin on 07-Feb-17.
 */
public class Robot {
	protected IBehaviour behavior;
	String name;

	public Robot (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int behave() {
		//the robots behave differently
		return behavior.moveCommand();
	}

	public void setBehavior(final IBehaviour behavior) {
		//todo
		this.behavior = behavior;
	}
}
