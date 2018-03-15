package election.data;

import election.business.DawsonElectionFactory;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionFactory;
import election.data.interfaces.ElectionDAO;
import election.data.interfaces.ListPersistenceObject;
import util.ListUtilities;

import java.io.IOException;
import java.util.List;

/**
 * @author Saad
 */
public class ElectionListDB implements ElectionDAO {

  private List<Election> database;
  private final ListPersistenceObject listPersistenceObject;
  private final ElectionFactory factory;

  /**
   * @param listPersistenceObject
   */
  public ElectionListDB(ListPersistenceObject listPersistenceObject) {
    this.listPersistenceObject = listPersistenceObject;
    this.database = this.listPersistenceObject.getElectionDatabase();
    this.factory = DawsonElectionFactory.DAWSON_ELECTION;
  }

  /**
   * @param listPersistenceObject
   * @param factory
   */
  public ElectionListDB(ListPersistenceObject listPersistenceObject, ElectionFactory factory) {
    this.listPersistenceObject = listPersistenceObject;
    this.database = this.listPersistenceObject.getElectionDatabase();
    this.factory = factory;
  }

  /**
   * @return String representation of the ElectionListDB class
   */
  @Override
  public String toString() {
    StringBuilder result =
            new StringBuilder("Number of elections in database: " + this.database.size() + "\n");
    for (Election e : this.database) {
      result.append(e + "\n");
    }
    return result.toString();
  }

  /**
   * Adds a Election to the database. The Election is added in natural sort order to keep the
   * database sorted. Only one customer can be inserted for an email address.
   *
   * @param e The Election to add to the database.
   * @throws DuplicateElectionException; The Election's email already exists.
   */
  @Override
  public void add(Election e) throws DuplicateElectionException {
    Election copy = factory.getElectionInstance(e);
    int index = ListUtilities.binarySearch(database, copy);
    if (index >= 0) {
      throw new DuplicateElectionException("Election is already exist in database!");
    } else {
      index = -(index + 1);
      this.database.add(index, copy);
    }
  }

  /**
   * Saves the list of Elections and disconnects from the database.
   *
   * @throws IOException Problems saving or disconnecting from database.
   */
  @Override
  public void disconnect() throws IOException {

    System.out.println("saving\n");
    Election[] electionArray = new Election[this.database.size()];
    for (int i = 0; i < this.database.size(); i++) {
      electionArray[i] = database.get(i);
    }

    ListUtilities.saveListToTextFile(electionArray, "../ElectionSys6/datafiles/testfiles/testElections.txt");
    // this.listPersistenceObject.saveElectionDatabase(this.database);
    this.database = null;
  }

  /**
   * Returns the Election with the specified email address.
   *
   * @param name The name of the requested Election.
   * @return The Election with the specified email.
   * @throws InexistentElectionException If there is no Election with the specified email.
   */
  @Override
  public Election getElection(String name) throws InexistentElectionException {
    // Make Election For Search
    Election ElectionForSearch = this.factory.getElectionInstance(name, "ranked", 0, 10, 23, 2017,
            12, 23, null, null, "Austin Antoine", "Mihail Cerba", "Saad Khan", "Simon Guevara");
    int index = (ListUtilities.binarySearch(database, ElectionForSearch));
    if (index < 0) {
      throw new InexistentElectionException("Election doesnt exist in database!");
    } else {
      // return copy of Election at index place
      return factory.getElectionInstance(this.database.get(index));
    }
  }
}
