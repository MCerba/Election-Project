package election.ui.tui;

import java.util.Observer;
import election.business.interfaces.*;
import java.util.List;
import java.util.Observable;

/**
 * update behavior
 * 
 * @author Cerba Mihail
 */
public class TextView implements Observer {
  public TextView(Observable model) {
    // register self with the model
    model.addObserver(this);
    // optional, if want to start view with an initial state
    // update(model, null);
  }

  /**
   * update behavior
   * 
   * @param Observable o - model which observe this class
   * @paramObject arg - take object for displaying information properly
   */

  @SuppressWarnings("unchecked")
  @Override
  public void update(Observable o, Object arg) {
    if (arg instanceof Voter) {
      displayVoter((Voter) arg);
    }

    if (arg instanceof Election) {
      displayElection((Election) arg);
    }

    if (arg instanceof List<?>) {
      displayWinner((List<String>) arg);

    }


  }

  // Display in case of List<String>
  private void displayWinner(List<String> winners) {
    if (winners.size() == 0 || winners == null) {
      System.out.println("\nNo Winners.\n");
      System.out.println("\nNobody voted yet.\n");
    } else {
      for (int i = 0; i < winners.size(); i++) {
        System.out.println((i + 1) + "-st place: " + winners.get(i));
      }
    }

  }

  // Display in case of voter
  private void displayVoter(Voter voter) {
    System.out.println("\nVoter information\n");
    System.out
        .println("Name: " + voter.getName().getFirstname() + " " + voter.getName().getLastname());
    System.out.println("Email: " + voter.getEmail());
    System.out.println("Postal code: " + voter.getPostalCode() + "\n\n");
  }

  // Display in case of election
  private void displayElection(Election election) {
    System.out.println("\nElection information\n");
    System.out.println("Name: " + (election.getName()));
    System.out.println("Start date: " + (election.getStartDate()));
    System.out.println("End date: " + (election.getEndDate() + "\n\n"));
  }

}
