package election.business;

import election.business.interfaces.*;
import election.data.DuplicateElectionException;
import election.data.DuplicateVoterException;
import election.data.InexistentElectionException;
import election.data.InexistentVoterException;
import election.data.interfaces.ElectionDAO;
import election.data.interfaces.VoterDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * DawsonElectionOffice object prevents tight coupling between the client and the system components.
 *
 * @author CerbaM Date: 27.11.2017 Last modification 27.11.2017
 */
public class DawsonElectionOffice extends Observable implements ElectionOffice {
    private final ElectionFactory factory;
    private final ElectionDAO elections;
    private final VoterDAO voters;
    private static final long serialVersionUID = 42031768871L;


    /**
     * Create a DawsonElectionOffice object.
     *
     * @param factory   to use
     * @param elections to vote in
     * @param voters    who wants to vote
     */
    public DawsonElectionOffice(ElectionFactory factory, ElectionDAO elections, VoterDAO voters) {
        this.factory = factory;
        this.elections = elections;
        this.voters = voters;
    }

    /**
     * Gets a Ballot for a given Election for a given Voter.
     *
     * @param voter    who wants to vote
     * @param election to vote in
     * @throws InvalidVoterException if voter cannot vote in the election
     */
    @Override
    public Ballot getBallot(Voter voter, Election election) throws InvalidVoterException {
        return election.getBallot(voter);
    }

    /**
     * Enables a given voter to cast a given ballot.
     *
     * @param voter  who want to cast a ballot
     * @param ballot to cast
     */
    @Override
    public void castBallot(Voter voter, Ballot b) {
        b.cast(voter);
    }

    /**
     * Saves all data and closes the admin office and voting booth.
     *
     * @throws IOException If there is a problem closing the files.
     */
    @Override
    public void closeOffice() throws IOException {
        this.elections.disconnect();
        this.voters.disconnect();
    }

    /**
     * Create a new Election
     *
     * @param name
     * @param type
     * @param startYear
     * @param startMonth
     * @param startDay
     * @param endYear
     * @param endMonth
     * @param endDay
     * @param startRange
     * @param endRange
     * @param choices
     * @return the new election
     * @throws DuplicateElectionException if an election with same name exists
     */
    @Override
    public Election createElection(String name, String type, int startYear, int startMonth,
                                   int startDay, int endYear, int endMonth, int endDay, String startRange, String endRange,
                                   String... choices) throws DuplicateElectionException {

        return this.factory.getElectionInstance(name, type, startYear, startMonth, startDay, endYear,
                endMonth, endDay, startRange, endRange, choices);

    }

    /**
     * Determine the winner of an election
     *
     * @param election
     * @throws IncompleteElectionException if the election is ongoing
     */
    @Override
    public List<String> getWinner(Election election) {
        setChanged();
        List<String> list = new ArrayList <String>();
        ElectionPolicy dep = this.factory.getElectionPolicy(election);
        list =	dep.getWinner();
        notifyObservers(list);    
        return list;
        
    }

    /**
     * Registers a new Voter
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param postalcode
     * @return The Voter object
     * @throws DuplicateVoterException if a voter with same e-mail address exists
     */
    @Override
    public Voter registerVoter(String firstName, String lastName, String email, String postalcode)
            throws DuplicateVoterException {
        setChanged();
        Voter voter = this.factory.getVoterInstance(firstName, lastName, email, postalcode);
        this.voters.add(voter);
        notifyObservers(voter);
        return voter;
    }

    /**
     * Finds and returns the Election with the given name
     *
     * @param name of the election
     * @return Election object
     * @throws InexistentElectionException if an election with that name cannot be found
     */
    @Override
    public Election findElection(String name) throws InexistentElectionException {
        setChanged();
        notifyObservers(this.elections.getElection(name));
        return this.elections.getElection(name);
    }

    /**
     * Finds and returns the Voter with the given email address.
     *
     * @param email address
     * @return Voter object
     * @throws InexistentVoterException if a voter with that email address cannot be found
     */
    @Override
    public Voter findVoter(String email) throws InexistentVoterException {
        setChanged();
        notifyObservers(this.voters.getVoter(email));
        return this.voters.getVoter(email);
    }

    /**
     * Finds a voter with a given email
     *
     * @param email  of the voter
     * @param notify observers if true
     * @return
     * @throws InexistentVoterException
     * @Author Saad Khan
     */
    @Override
    public Voter findVoter(String email, boolean notify) throws InexistentVoterException {

        this.setChanged();
        if (notify == true)
            this.notifyObservers(this.voters.getVoter(email));
        return this.voters.getVoter(email);
    }
}
