package election.data;

/**
 * This exception signals that a search for a voter doesn't find a match. already.
 * 
 * @author Cerba Mihail
 */
public class InexistentVoterException extends Exception {
  private static final long serialVersionUID = 42031768871L;

  public InexistentVoterException() {
    super("The provided Voter is not exist in the database.");
  }

  public InexistentVoterException(String message) {
    super(message);
  }
}
