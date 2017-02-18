/**
 * Created by Pin on 06-Feb-17.
 */
public interface Visitor {
    // method overloading to accept other kinds of objects that are passed over
    void visit (Professor p);
    void visit (Student s);
    void visit (AdminStaff a);

}
