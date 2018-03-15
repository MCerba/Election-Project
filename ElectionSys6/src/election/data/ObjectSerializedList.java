package election.data;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;
import election.data.interfaces.ListPersistenceObject;
import util.Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements ListPersistenceObject for text files populated with toString()
 * representation of the objects.
 *
 * @author Saad Khan
 **/
public class ObjectSerializedList implements ListPersistenceObject {

    private final String voterFilename;
    private final String electionFilename;

    /**
     * Constructor requires the filenames of the files containing the sorted string representations of
     * the Voter, Elections.
     *
     * @param voterFilename    Text file with sorted Voters
     * @param electionFilename Text file with sorted Elections
     */
    public ObjectSerializedList(String voterFilename, String electionFilename) {
        this.electionFilename = electionFilename;
        this.voterFilename = voterFilename;
    }

    /**
     * Returns a reference to an arraylist containing the voters. If an IOException occurs an
     * ArrayList of size zero will be returned.
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Voter> getVoterDatabase() {

        List<Voter> voters = null;
        try {
            voters = (List<Voter>) (Utilities.deserializeObject(this.voterFilename));
        } catch (IOException e) {
            return new ArrayList<Voter>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Voter> listAdapter = voters;
        return new ArrayList<Voter>(listAdapter);
    }

    /**
     * Returns a reference to an arraylist containing the elections. If an IOException occurs an
     * ArrayList of size zero will be returned.
     */
    @Override
    public List<Election> getElectionDatabase() {
        List<Election> elections = null;
        try {
            elections = (List<Election>) (Utilities.deserializeObject(this.electionFilename));
        } catch (IOException e) {
            return new ArrayList<Election>();
        } catch (ClassNotFoundException e) {
            return new ArrayList<Election>();
        }
        return elections;
    }

    /**
     * Saves the list of voters to the serializable file
     */
    @Override
    public void saveVoterDatabase(List<Voter> voters) throws IOException {
        Utilities.serializeObject(voters, this.voterFilename);
    }

    /**
     * Saves the list of elections to the serializable file
     */
    @Override
    public void saveElectionDatabase(List<Election> elections) throws IOException {
        Utilities.serializeObject(elections, this.electionFilename);
    }

}
