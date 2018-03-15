package election.business;

/**
 * This exception signals that a voter tries to get or cast a ballot for an election which is either
 * not in progress, or for which the voter is not eligible, or for which the voter has already
 * voted.
 * 
 * 
 * @author Cerba Mihail
 */
public class InvalidVoterException extends RuntimeException {
  private static final long serialVersionUID = 42031768871L;

  public InvalidVoterException() {
    super("This election is not in progress or voter is not eligible/has already voted");
  }

  public InvalidVoterException(String message) {
    super(message);
  }
}
