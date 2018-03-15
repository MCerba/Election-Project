package election.business;

import election.business.interfaces.*;
import util.ListUtilities;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DawsonElection object is used to crate Dawson Election. DawsonElection implements Election.
 *
 * @author CerbaM Date: 02.10.2017 Last modification 05.11.2017
 */
public class DawsonElection implements Election {

    private static final long serialVersionUID = 42031768871L;
    private String name, postalRangeEnd, postalRangeStart;
    private ElectionType electionType;
    private LocalDate startDate;
    private LocalDate endDate;
    private BallotItem[] dawsonBallotItems;
    private Tally tally;
    private List<Voter> gotballot;
    private List<Voter> castballot;
    private int invalidvotes = 0;

    /**
     * @param name       - name to set
     * @param type       - type of election
     * @param startYear  - start Year
     * @param startMonth - start Month
     * @param startDay   - start Day
     * @param endYear    - end Year
     * @param endMonth   - end Month
     * @param endDay     - end Day
     * @param startRange - start postal Range
     * @param endRange   - end Postal Range
     * @param tally      - tally object
     * @param items      - Array of Ballot Items
     * @author CerbaM
     */
    public DawsonElection(String name, String type, int startYear, int startMonth, int startDay,
                          int endYear, int endMonth, int endDay, String startRange, String endRange, Tally tally,
                          String... items) {

        // name of the election cannot be null or an empty string
        if (name == null || name == "") {
            throw new IllegalArgumentException("DawsonElection class: name is null or eampty string");
        }
        this.name = name;

        // Postal Range initialization
        this.postalRangeStart = startRange;
        this.postalRangeEnd = endRange;
        gotballot = new ArrayList<>();
        castballot = new ArrayList<>();

        // the ballot items cannot be null or an empty string
        if (items == null) {
            throw new IllegalArgumentException("DawsonElection class: items is null.");
        } else {
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null || items[i] == "") {
                    throw new IllegalArgumentException(
                            "DawsonElection class: at least one of ballot items is null or empty string.");
                }
            }
        }
        // @throws IllegalArgumentException if startYear, startMonth, startDay are invalid
        try {
            this.startDate = LocalDate.of(startYear, startMonth, startDay);
        } catch (DateTimeException dte) {
            throw new IllegalArgumentException("DawsonElection:Start Date is invalid ");
        }

        try {
            this.endDate = LocalDate.of(endYear, endMonth, endDay);
        } catch (DateTimeException dte) {
            throw new IllegalArgumentException("DawsonElection:End Date is invalid ");
        }

        // @throws IllegalArgumentException if end date is before the start date.
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(
                    "DawsonElection class: The end date is before the start date.");
        }

        // ElectionType enums Recalling
        type = type.toUpperCase().trim();

        // @throw an IllegalArgumentException if String type is not match to one of enums in
        // ElectionType;
        try {
            this.electionType = ElectionType.valueOf(type);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("DawsonElection class: The Election type is invalid");
        }


        // The Tally object cannot be null.
        if (tally == null) {
            throw new IllegalArgumentException("DawsonElection class: The Tally object cannot be null.");
        }

        // ballot items validation
        // @throw IllegalArgumentException if ballot items less then 2
        if (items.length < 2) {
            throw new IllegalArgumentException("DawsonElection class: It be at least two ballot items");
        }

        // Instantiate DawsonBallotItems and save them in an array.
        this.dawsonBallotItems = new DawsonBallotItem[items.length];
        for (int i = 0; i < dawsonBallotItems.length; i++) {
            dawsonBallotItems[i] = DawsonElectionFactory.DAWSON_ELECTION.getBallotItem(items[i],
                    this.electionType, items.length);
        }
        this.tally = tally;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof DawsonElection) {
            DawsonElection de = (DawsonElection) obj;
            return this.name.equalsIgnoreCase(de.name);
        }
        return false;

    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    /**
     * @returns String
     */
    public String toString() {
        String result = this.name;
        result += "*" + startDate.getYear();
        result += "*" + startDate.getMonthValue();
        result += "*" + startDate.getDayOfMonth();
        result += "*" + endDate.getYear();
        result += "*" + endDate.getMonthValue();
        result += "*" + endDate.getDayOfMonth();
        result += "*" + this.postalRangeStart;
        result += "*" + this.postalRangeEnd;
        result += "*" + electionType;
        result += "*" + this.dawsonBallotItems.length;
        for (int i = 0; i < this.dawsonBallotItems.length; i++) {
            result += "\n" + dawsonBallotItems[i].getChoice();
        }
        return result;
    }


    @Override
    /**
     * @returns a negative integer, zero, or a positive integer as the specified Election'n mane is
     *          greater than, equal to, or less than this Election'n mane(parameter), ignoring case
     *          considerations.
     */
    public int compareTo(Election other) {
        if (this == other) {
            return 0;
        }
        return this.name.compareTo(other.getName());
    }

    @Override
    /**
     * @returns ElectionType enum
     */
    public ElectionType getElectionType() {
        return this.electionType;
    }

    /**
     * @return an array of strings with the BallotItem choices
     */
    @Override
    public String[] getElectionChoices() {
        String[] result = new String[this.dawsonBallotItems.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.dawsonBallotItems[i].toString();
        }
        return result;
    }

    @Override
    /**
     * @return LocalDate End Date
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    @Override
    /**
     * @return LocalDate Start Date
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    @Override
    /**
     * @return String postalRangeEnd
     */
    public String getPostalRangeEnd() {
        return this.postalRangeEnd;
    }

    @Override
    /**
     * @return String postalRangeStart
     */
    public String getPostalRangeStart() {
        return this.postalRangeStart;
    }

    @Override
    /**
     * @returns true if the postal range start and end are not null
     */
    public boolean isLimitedToPostalRange() {
        if (this.postalRangeEnd != null && this.postalRangeStart != null) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * returns String name
     */
    public String getName() {
        return this.name;
    }

    @Override

    /**
     * returns tally object, Do not deep copy.
     */
    public Tally getTally() {
        return this.tally;
    }

    @Override
    /**
     * @author CerbaM
     * @param Tally tally
     * @return Ballot - copy of StubBallot @ throws IllegalArgumentException if The tally is null or
     *         The Tally and this election doesn't match!
     */
    public void setTally(Tally tally) {
        if (tally == null) {
            throw new IllegalArgumentException("DawsonElection class --> setTally:  The Tally is null ");
        }

        if (tally.getElectionName() != this.name) {
            throw new IllegalArgumentException(
                    "DawsonElection class --> setTally:  The Tally and this election doesn't match!");
        } else {
            this.tally = tally;
        }
    }

    @Override
    /**
     * Check if the voter is Eligible to vote, then if he has already request a ballot, and then if
     * the voter has already cast a vote If the checks are right, it will return a new ballot
     *
     * @author Simon Guevara
     * @param Voter v
     * @return Ballot - copy of StubBallot
     */
    public Ballot getBallot(Voter v) {

        if (v.isEligible(this)) {

            int indexgot = ListUtilities.binarySearch(gotballot, v);

            if (indexgot < 0) {
                gotballot.add(-(indexgot + 1), v);
                Ballot result = DawsonElectionFactory.DAWSON_ELECTION.getBallot(this.dawsonBallotItems,
                        this.electionType, this);

                return result;


            } else {

                if (ListUtilities.binarySearch(castballot, v) <= 0) {
                    invalidvotes++;
                    throw new InvalidVoterException("The voter" + v.toString() + " has already cast a vote!");
                } else {
                    Ballot result = DawsonElectionFactory.DAWSON_ELECTION.getBallot(this.dawsonBallotItems,
                            this.electionType, this);

                    return result;

                }

            }

        } else {
            invalidvotes++;
            throw new InvalidVoterException(
                    "The voter" + v.toString() + " his not elligible to vote in this election!");

        }

    }

    @Override
    /**
     * This method checks if the user can cast a ballot. If he can, it'll refresh the Ballot. If it
     * can't, it'll throw an exception depending on the type of error.
     *
     * This method checks if the voter is valid. If he isn't throw an
     *
     *
     * @author Simon Guevara
     * @param Ballot b
     * @param Voter v
     * @return Ballot - copy of StubBallot
     */
    public void castBallot(Ballot b, Voter v) {

        if (v.isEligible(this)) {

            if (ListUtilities.binarySearch(gotballot, v) < 0) {
                invalidvotes++;
                throw new InvalidVoterException(
                        "The voter" + v.toString() + " has not requested a ballot yet!");
            } else {
                int indexcast = ListUtilities.binarySearch(castballot, v);
                if (indexcast < 0) {
                    castballot.add(-(indexcast + 1), v);
                    try {
                        if (b.validateSelections()) {
                            this.tally.update(b);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    invalidvotes++;
                    throw new InvalidVoterException("The voter" + v.toString() + " has already cast a vote!");
                }
            }
        } else {
            invalidvotes++;
            throw new InvalidVoterException(
                    "The voter" + v.toString() + " his not elligible to vote in this election!");
        }

    }


    @Override
    /**
     * @author Simon Guevara-Ponce
     * @return CastBallot size
     */
    public int getTotalVotesCast() {
        return this.castballot.size();

    }

    @Override
    /**
     *
     * @author Simon Guevara-Ponce
     * @return Invalid vote attempts
     */
    public int getInvalidVoteAttempts() {
        return this.getInvalidVoteAttempts();

    }

}
