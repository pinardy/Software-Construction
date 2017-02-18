import java.util.ArrayList;

/**
 * Created by Pin on 06-Feb-17.
 */
class SUTD {
    private ArrayList<Visitable> employee;

    public SUTD () {
        employee = new ArrayList<Visitable>();
        employee.add(new Professor("Sun Jun", 0));
        employee.add(new AdminStaff("Stacey", 5));
        employee.add(new Student("Allan", 3));
    }

    public ArrayList<Visitable> getEmployee () {
        return employee;
    }
}
