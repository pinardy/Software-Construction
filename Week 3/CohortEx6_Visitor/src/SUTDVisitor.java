import java.util.ArrayList;

/**
 * Created by Pin on 06-Feb-17.
 */
public class SUTDVisitor implements Visitor {
    private String employeeList = "";
    SUTD oneSUTD = new SUTD ();
    ArrayList<Visitable> employee = oneSUTD.getEmployee();

    public void visit(Professor p) {
        for (int i = 0; i < employee.size(); i++) {
            if (employee.get(i) instanceof Professor) {
                employeeList += "Prof: " + ((Professor) employee.get(i)).getName() + ", Number of publications: " + ((Professor) employee.get(i)).getNo_of_publications() + "\n";
            }
        }
    }

    public void visit(Student s){
        for (int i = 0; i < employee.size(); i++) {
            if (employee.get(i) instanceof Student) {
                employeeList += "Student: " + ((Student) employee.get(i)).getName() + ", GPA: " + ((Student) employee.get(i)).getGPA() + "\n";
            }
        }
    }

    public void visit(AdminStaff a) {
        for (int i = 0; i < employee.size(); i++) {
            if (employee.get(i) instanceof AdminStaff) {
                employeeList += "AdminStaff: " + ((AdminStaff) employee.get(i)).getName() + ", Efficiency: " + ((AdminStaff) employee.get(i)).getEfficiency() + "\n";
            }
        }
    }

    public String getEmployee() {
        return employeeList;
    }
}
