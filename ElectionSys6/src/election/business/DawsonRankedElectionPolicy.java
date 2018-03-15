package election.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionPolicy;

public class DawsonRankedElectionPolicy implements ElectionPolicy {

  private Election reference;
  private static final long serialVersionUID = 42031768871L;

  /*
   * @author Austin Antoine
   * 
   * @param Election: The reference of this parameter is saved as an instance variable
   * 
   * @throws IncompleteElectionException: Current date can't be before end date
   */
  public DawsonRankedElectionPolicy(Election election) {
    if (election.getElectionType() != ElectionType.RANKED) {
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
    String[] choices = this.reference.getElectionChoices();
    int[] points = new int[choices.length];
    int[][] breakdown = this.reference.getTally().getVoteBreakdown();
    int indexOfWinner = 0;
    int maxPoints = 0;
    List<String> winner = new ArrayList<String>();

    for (int i = 0; i < breakdown.length; i++) {
      for (int j = 0; j < breakdown[i].length; j++) {
        if (j == 0) {
          points[i] += (breakdown[i][j] * 5);
        }

        if (j == 1) {
          points[i] += (breakdown[i][j] * 2);
        }
      }
    }

    for (int i = 0; i < points.length; i++) {
      if (points[i] > maxPoints) {
        maxPoints = points[i];
        indexOfWinner = i;
      }
    }

    winner.add(choices[indexOfWinner]);
    return winner;
  }

}
