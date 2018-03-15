/**
 *
 */
package election.business;

import election.business.interfaces.Tally;
import lib.PostalCode;

/**
 * @author Simon Guevara-Ponce
 */
public class DawsonVoterTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        testTheFourParameterConstructor();
        testSetPostalCode();
        testisElligible();


    }

    private static void testTheFourParameterConstructor() {
        System.out.println("\nTesting the four parameter constructor.");
        testTheFourParameterConstructor("Case 1 - Valid data", "John", "Wayne", "johnwayne@hotmail.com",
                "H8Y 2c1", true);
        testTheFourParameterConstructor("Case 2 - Invalid  missing data", "", "Wayne",
                "johnwayne@hotmail.com", "H8Y 2c1", false);
        testTheFourParameterConstructor("Case 3 - Invalid  null name", "John", null,
                "johnwayne@hotmail.com", "H8Y 2c1", false);
        testTheFourParameterConstructor("Case 4 - Invalid  null email", "John", "Wayne", null,
                "H8Y 2c1", false);
        testTheFourParameterConstructor("Case 4 - Invalid  null postalcode", "John", "Wayne",
                "johnwayne@hotmail.com", null, false);

    }

    private static void testTheFourParameterConstructor(String testCase, String fname, String lname,
                                                        String email, String postal, boolean expectValid) {
        System.out.println(" " + testCase);
        try {

            DawsonVoter tester = new DawsonVoter(fname, lname, email, postal);
            System.out.print("\tThe DawsonVoter instance was created: " + tester);

            if (!expectValid) {
                System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
            }


        } catch (IllegalArgumentException iae) {
            System.out.print("\t" + iae.getMessage());
            if (expectValid) {
                System.out.print(
                        " Error! Expected Valid. ==== FAILED TEST ====");
            }

        } catch (NullPointerException npe) {
            System.out.print("\t" + npe.getMessage());
            if (expectValid) {
                System.out.print(
                        " Error! Expected Valid. ==== FAILED TEST ====");
            }

        } catch (Exception e) {
            System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage()
                    + " ==== FAILED TEST ====");
            if (expectValid) {
                System.out.print(" Expected Valid.");
            }
        }
        System.out.println("\n");
    }

//______________________________________________________________________________________________________________________________________________


    private static void testSetPostalCode() {
        System.out.println("\nTesting the copy constructor.");

        testSetPostalCode("Case 1 - Valid data", "H8Y 2C1", true);
        testSetPostalCode("Case 2 - Invalid data - null object", null, false);
        testSetPostalCode("Case 2 - Invalid data - null object", null, false);


    }


    private static void testSetPostalCode(String testCase, String postal, boolean expectValid) {

        System.out.println(" " + testCase);

        try {
            DawsonVoter tester = new DawsonVoter("John", "Wayne", "johnwayne@hotmail.com", "H3Y 2C1");

            PostalCode postaltest = new PostalCode(postal);
            tester.setPostalCode(postaltest);
            System.out
                    .print("\tThe Postal code has been replaced by: " + (tester.getPostalCode().toString()));

            if (!expectValid) {
                System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
            }

        } catch (IllegalArgumentException iae) {
            System.out.print("\t" + iae.getMessage());
            if (expectValid) {
                System.out.print(
                        " Error! Expected Valid. ==== FAILED TEST ====");
            }


        } catch (NullPointerException npe) {
            System.out.print("\t" + npe.getMessage());
            if (expectValid) {
                System.out.print(
                        " Error! Expected Valid. ==== FAILED TEST ====");
            }

        } catch (Exception e) {
            System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage()
                    + " ==== FAILED TEST ====");
            if (expectValid) {
                System.out.print(" Expected Valid.");
            }
        }
        System.out.println("\n");
    }

//______________________________________________________________________________________________________________________________________________


    private static void testisElligible() {
        System.out.println("\nTesting isElligible.");
        DawsonTally ty1 = new DawsonTally(0, null);
        testisElligible("Case 1 - Valid data", true, true, "Dawson", "SINGLE", 2015, 9, 12, 2018, 9, 13,
                "H", "H", ty1, "one", "two");
        testisElligible("Case 2 - Valid data - out of date", true, false, "Dawson", "SINGLE", 2015, 9,
                12, 2016, 9, 13, "H", "H", ty1, "one", "two");
        testisElligible("Case 2 - Invalid data - null name", false, false, null, "SINGLE", 2015, 9, 12,
                2016, 9, 13, "H", "H", ty1, "one", "two");


    }


    private static void testisElligible(String testCase, boolean expectValid, boolean results,
                                        String name, String type, int startYear, int startMonth, int startDay, int endYear,
                                        int endMonth, int endDay, String startRange, String endRange, Tally tally, String... items) {

        System.out.println("Testing " + testCase);

        try {

            DawsonElection test = new DawsonElection(name, type, startYear, startMonth, startDay, endYear,
                    endMonth, endDay, startRange, endRange, tally, "one", "two");

            DawsonVoter tester = new DawsonVoter("John", "Wayne", "johnwayne@hotmail.com", "H3Y 2C1");

            if (results == tester.isEligible(test)) {
                System.out.print(" Valid result. Expected and Got " + results);
            }

            if (results != tester.isEligible(test)) {
                System.out.print(
                        " Invalid result. Expected " + results + "   and Got " + tester.isEligible(test));
            }

            if (!expectValid) {
                System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
            }

        } catch (IllegalArgumentException iae) {
            System.out.print("\t" + iae.getMessage());
            if (expectValid) {
                System.out.print(
                        " Error! Expected Valid. ==== FAILED TEST ====");
            }


        } catch (NullPointerException npe) {
            System.out.print("\t" + npe.getMessage());
            if (expectValid) {
                System.out.print(
                        " Error! Expected Valid. ==== FAILED TEST ====");
            }

        } catch (Exception e) {
            System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage()
                    + " ==== FAILED TEST ====");
            if (expectValid) {
                System.out.print(" Expected Valid.");
            }
        }
        System.out.println("\n");
    }
}
