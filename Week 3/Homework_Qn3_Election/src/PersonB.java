/**
 * Created by Pin on 08-Feb-17.
 */
public class PersonB implements Candidate{
    String name = "B";
    static int voteCount = 0;

    @Override
    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public String getName() {
        return name;
    }
}
