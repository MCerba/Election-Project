package election.data;

/**
 * This exception signals that a search for a election doesn't find a match. already.
 * 
 * @author Cerba Mihail
 */
public class InexistentElectionException extends Exception {
  private static final long serialVersionUID = 42031768871L;

  public InexistentElectionException() {
    super("The provided Election is not exist in the database.");
  }

  public InexistentElectionException(String message) {
    super(message);
  }
}
