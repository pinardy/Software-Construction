public class PersonA implements Candidate{
    String name = "A";
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
