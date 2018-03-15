package election.business;

import election.business.interfaces.Voter;

import java.util.Arrays;

/**
 * @author Saad
 */
public class VoterComparatorTest {

    public static void main(String[] args) {
        Voter ted = new DawsonVoter("aabe", "aollin", "ted@drago.ca", "C5K6P6");
        Voter marge = new DawsonVoter("babe", "bollin", "marge@drago.ca", "B5K6P6");
        Voter kord = new DawsonVoter("cabe", "collin", "ted@drago.ca", "A5K6P6");
        Voter kard = new DawsonVoter("cabe", "collin", "ted@drago.ca", "C5K6P6");
        Voter[] voters = {ted, marge, kord, kard};

        testingNameComparator(voters, voters[0], true);
        testingPostalCodeComparator(voters, voters[0], true);
        testingNullComparator(voters, voters[0], false);
    }

    /**
     * @param voters
     * @param voter
     * @param expectValid
     */
    private static void testingPostalCodeComparator(Voter[] voters, Voter voter, boolean expectValid) {
        try {
            boolean result = false;
            System.out.print("\t\nSorting by Postal Code");
            Arrays.sort(voters, new VoterPostalCodeComparator());
            if (voters[0].equals(voter))
                result = true;
            if (result != expectValid) {
                System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
            } else
                System.out.print("\t ==== PASSED TEST ====");
        } catch (IllegalArgumentException iae) {
            System.out.print("\t" + iae.getMessage());
        }
    }

    /**
     * @param voters
     * @param voter
     * @param expectValid
     */
    private static void testingNameComparator(Voter[] voters, Voter voter, boolean expectValid) {
        try {
            boolean result = false;
            System.out.print("\t\nSorting by Name");
            Arrays.sort(voters, new VoterNameComparator());
            if (voters[0].equals(voter))
                result = true;
            if (result != expectValid) {
                System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
            } else
                System.out.print("\t ==== PASSED TEST ====");
        } catch (IllegalArgumentException iae) {
            System.out.print("\t" + iae.getMessage());
        }
    }

    /**
     * @param voters
     * @param voter
     * @param expectValid
     */
    private static void testingNullComparator(Voter[] voters, Voter voter, boolean expectValid) {
        try {
            boolean result = false;
            System.out.print("\t\nSorting by a Null Value");
            Arrays.sort(voters, null);
            if (voters[0].equals(voter))
                result = true;
            if (result != expectValid) {
                System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
            } else
                System.out.print("\t ==== PASSED TEST ====");
        } catch (IllegalArgumentException iae) {
            System.out.print("\t" + iae.getMessage());
        }
    }

}
