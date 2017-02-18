/**
 * Created by Pin on 06-Feb-17.
 */
class Professor extends Employee implements Visitable{
    private String name;
    private int no_of_publications;

    public Professor (String name, int no_of_publications) {
        this.name = name;
        this.no_of_publications = no_of_publications;
    }


    public int getNo_of_publications() {
        return no_of_publications;
    }

    public String getName(){
        return name;
    }

    @Override
    public void accept(Visitor v) {
       v.visit(this);
    }
}
