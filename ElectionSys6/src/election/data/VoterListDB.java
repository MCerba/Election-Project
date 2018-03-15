package election.data;

import election.business.DawsonElectionFactory;
import election.business.DawsonVoter;
import election.business.interfaces.ElectionFactory;
import election.business.interfaces.Voter;
import election.data.interfaces.ListPersistenceObject;
import election.data.interfaces.VoterDAO;
import lib.Email;
import lib.PostalCode;
import util.ListUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * VoterListDB class represent the voter database as an internal list
 * 
 * 
 * @author Cerba Mihail
 */
public class VoterListDB implements VoterDAO {

  private List<Voter> database;
  private final ListPersistenceObject listPersistenceObject;
  private final ElectionFactory factory;

  public VoterListDB(ListPersistenceObject listPersistenceObject) {
    this.listPersistenceObject = listPersistenceObject;
    this.database = this.listPersistenceObject.getVoterDatabase();
    this.factory = DawsonElectionFactory.DAWSON_ELECTION;
  }

  public VoterListDB(ListPersistenceObject listPersistenceObject, ElectionFactory factory) {
    this.listPersistenceObject = listPersistenceObject;
    this.database = this.listPersistenceObject.getVoterDatabase();
    this.factory = factory;

  }

  /**
   * @returns String String representation of the VoterListDB class
   */
  @Override
  public String toString() {
    StringBuilder result =
        new StringBuilder("Number of voters in database: " + this.database.size() + "\n");
    for (Voter v : this.database) {
      result.append(v + "\n");
    }
    return result.toString();
  }

  /**
   * Adds a voter to the database. The voter is added in natural sort order to keep the database
   * sorted. Only one customer can be inserted for an email address.
   * 
   * @param voter The voter to add to the database.
   * @throws DuplicateVoterException; The voter's email already exists.
   */
  @Override
  public void add(Voter voter) throws DuplicateVoterException {
    Voter copy = factory.getVoterInstance(voter);

    int index = ListUtilities.binarySearch(database, copy);
    if (index > 0) {
      throw new DuplicateVoterException("Voter is already exist in database!");
    } else {
      index = -(index + 1);
      database.add(index, voter);
      /*
       * for (int i = index; i < database.size(); i++) { Voter temp = database.get(i);
       * database.set(i, copy); copy = temp; }
       */

    }
  }

  /**
   * Saves the list of voters and disconnects from the database.
   * 
   * @throws IOException Problems saving or disconnecting from database.
   */
  @Override
  public void disconnect() throws IOException {
    // make the testfiles directory
    Path dir;
    try {
      dir = Paths.get("../ElectionSys6/datafiles/testfiles");
      if (!Files.exists(dir))
        Files.createDirectory(dir);
      // From List<Voter> To Voter[]
      Voter[] voterArray = new Voter[this.database.size()];
      for (int i = 0; i < this.database.size(); i++) {
        voterArray[i] = database.get(i);
      }

      ListUtilities.saveListToTextFile(voterArray, "../ElectionSys6/datafiles/testfiles/testVoters.txt");
    } catch (IOException e) {
      System.err.println("could not create testfiles in setup() " + e.getMessage());
    }
    this.database = null;

  }

  /**
   * Returns the voter with the specified email address.
   * 
   * @param email The email of the requested voter.
   *
   * @return The voter with the specified email.
   *
   * @throws InexistentVoterException If there is no voter with the specified email.
   */
  @Override
  public Voter getVoter(String email) throws InexistentVoterException {
    // Make voter For Search
    Voter voterForSearch = new DawsonVoter("Mike", "Duke", email, "H3K 1N1");
    int index = ListUtilities.binarySearch(database, voterForSearch);
    if (index < 0) {
      throw new InexistentVoterException("Voter doesnt exist in database!");
    } else {

      // return copy of Voter at index place
      return factory.getVoterInstance(this.database.get(index));
    }

  }

  /**
   * Modifies a voter's postal code.
   * 
   * @param email The email of the voter to be updated
   * @param postalCode The new postal code
   * @throws InexistentVoterException The voter is not in database.
   */
  @Override
  public void update(Email email, PostalCode postalCode) throws InexistentVoterException {
    // Make voter For Search
    Voter voterForSearch = new DawsonVoter("Mike", "Duke", email.toString(), "H3K 1N1");
    int index = ListUtilities.binarySearch(database, voterForSearch);
    if (index < 0) {
      throw new InexistentVoterException("Voter with this email doesn't exist in database!");
    } else {

      // change Voter in database at index place
      database.get(index).setPostalCode(postalCode);
    }

  }

}
