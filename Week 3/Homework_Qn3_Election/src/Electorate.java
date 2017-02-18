import java.util.Scanner;

public class Electorate {
    static int voteTotal = 0;
    static int voterNumber = 1;

    // set the number of electorates
    public void setNumber(int num) throws InvalidVoteException {
        voteTotal = num;
    }
    public int getVoteTotal(){
        return voteTotal;
    }

    public void voteCandidate() throws InvalidVoteException {
        boolean revote = true;
        System.out.println("Electorate number: " + voterNumber);
        System.out.println("Vote for candidate. Key in ( A ) or ( B ) : ");
        voterNumber += 1;

        while (revote) {
            Scanner sc = new Scanner(System.in);
            String vote = sc.next();

            try {
                if (vote.equals("A")) {
                    PersonA.voteCount += 1;
                    revote = false;
                } else if (vote.equals("B")) {
                    PersonB.voteCount += 1;
                    revote = false;
                } else {
                    throw new InvalidVoteException();
                }
            } catch (InvalidVoteException e) {
                System.out.println("Invalid vote. Please vote again");
            }
        }
    }

}



