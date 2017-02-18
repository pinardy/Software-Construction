/**
 * Created by Pin on 06-Feb-17.
 */
class Student extends Employee implements Visitable{
    private String name;
    private float GPA;

    public Student (String name, float GPA) {
        this.name = name;
        this.GPA = GPA;
    }

    public float getGPA() {
        return GPA;
    }

    public String getName(){
        return name;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
