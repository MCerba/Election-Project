package election.data;

/**
 * This exception signals that the name of election exists in the database already.
 * 
 * @author Cerba Mihail
 */
public class DuplicateElectionException extends Exception {
  private static final long serialVersionUID = 42031768871L;

  public DuplicateElectionException() {
    super("The provided Name of Election is alredy exist.");
  }

  public DuplicateElectionException(String message) {
    super(message);
  }
}
