package election.business;

import election.business.interfaces.Voter;
import lib.Name;

import java.util.Comparator;

/**
 * This class provides an alternative way to compare Voter objects, besides the natural order.
 * Voters are compared based on their Name. If the Name are the same, they are ordered
 * naturally.
 *
 * @author Saad
 */
public class VoterNameComparator implements Comparator<Voter> {


    @Override
    public int compare(Voter voter1, Voter voter2) {
        // if two objects are equal, comparing them should yield 0
        Name name1 = voter1.getName();
        Name name2 = voter2.getName();
        if (name1.compareTo(name2) < 0) {
            return 1;
        } else if (name1.compareTo(name2) > 0) {
            return -1;
        } else {
            return voter1.compareTo(voter2);
        }
    }
}
