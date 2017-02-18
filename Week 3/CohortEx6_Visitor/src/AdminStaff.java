/**
 * Created by Pin on 06-Feb-17.
 */
class AdminStaff extends Employee implements Visitable{
    private String name;
    private float efficiency;

    public AdminStaff(String name, float efficiency) {
        this.name = name;
        this.efficiency = efficiency;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public String getName(){
        return name;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
