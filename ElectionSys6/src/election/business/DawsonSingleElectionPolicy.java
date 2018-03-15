package election.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionPolicy;
import election.business.interfaces.Tally;
import java.time.LocalDate;


public class DawsonSingleElectionPolicy implements ElectionPolicy {

  private Election reference;
  private static final long serialVersionUID = 42031768871L;

  /*
   * @author Austin Antoine
   * 
   * @param Election: The reference of this parameter is saved as an instance variable
   * 
   * @throws IllegalArgumentException: Parameter can't be null
   * 
   * @throws IncompleteElectionException: Current date can't be before end date
   */
  @SuppressWarnings("unused")
  public DawsonSingleElectionPolicy(Election election) {
    if (election.getElectionType() != ElectionType.SINGLE) {
      throw new IllegalArgumentException("The election type must be SINGLE");
    }

    if (election.getEndDate().isAfter(LocalDate.now()))
      throw new IncompleteElectionException();

    if (election == null)
      System.err.println("A null value was passed as a parameter");

    else
      this.reference = election;
  }

  /*
   * @author Austin Antoine
   * 
   * @return List<String>: Returns a List<String> with the winner of the election
   * 
   * This method is used to determine the winner of an election
   */
  @Override
  public List<String> getWinner() {

    int halfOfTotalVotes = this.reference.getTotalVotesCast() / 2;
    int currentMax = 0;
    int[][] breakdown = this.reference.getTally().getVoteBreakdown();
    int rowOfWinner = 0;
    List<String> winner = new ArrayList<String>();
    String[] choices = this.reference.getElectionChoices();

    for (int i = 0; i < breakdown.length && i < breakdown[i].length; i++) {
      if (breakdown[i][i] > currentMax) {
        currentMax = breakdown[i][i];
        rowOfWinner = i;
      }
    }

    if (currentMax > halfOfTotalVotes) {
      winner.add(choices[rowOfWinner]);
    }

    return winner;
  }
}
