import java.util.ArrayList;

public class VisitorPattern {

    public static void main(String[] args){
        SUTD oneSUTD = new SUTD ();
        SUTDVisitor visitor = new SUTDVisitor();
        ArrayList<Visitable> employee = oneSUTD.getEmployee();

        for (Visitable o : employee) {
            o.accept(visitor);
        }

        System.out.println(visitor.getEmployee());

    }
}

