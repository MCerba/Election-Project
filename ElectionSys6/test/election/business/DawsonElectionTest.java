package election.business;


import java.time.LocalDate;

public class DawsonElectionTest {

    public static void main(String[] args) {
        System.out.format("%-50s %s", " ", "========#1=========\n");
        the12ParameterConstructor();
        System.out.format("%-50s %s", " ", "========#2=========\n");
        testEquals();
        System.out.format("%-50s %s", " ", "========#3=========\n");
        testToString();
        System.out.format("%-50s %s", " ", "========#4=========\n");
        testhashCode();
        System.out.format("%-50s %s", " ", "========#5=========\n");
        testcompareTo();
        System.out.format("%-50s %s", " ", "========#6=========\n");
        testgetElectionType();
        System.out.format("%-50s %s", " ", "========#7=========\n");
        testgetElectionChoices();
        System.out.format("%-50s %s", " ", "========#8=========\n");
        testgetEndDate();
        System.out.format("%-50s %s", " ", "========#9=========\n");
        testgetStartDate();
        System.out.format("%-50s %s", " ", "========#10========\n");
        testgetPostalRangeEnd();
        System.out.format("%-50s %s", " ", "========#11========\n");
        testgetPostalRangeStart();
        System.out.format("%-50s %s", " ", "========#12========\n");
        testisLimitedToPostalRange();
        System.out.format("%-50s %s", " ", "========#13========\n");
        testgetName();
        System.out.format("%-50s %s", " ", "========#14========\n");
        testgetTally();
        System.out.format("%-50s %s", " ", "========#15========\n");
        testsetTally();
        System.out.format("%-50s %s", " ", "========#15========\n");


        // Still need to test : getBallot,castBallot -  We cannot instantiate Voter object brcause we have no voter class ,we have  only Voter interface.
        // getTotalVotesCast,getInvalidVoteAttempts

    }


    private static void testsetTally() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "D", "H", ty1, "one", "two");
        try {
            test1.setTally(null);
        } catch (IllegalArgumentException iae) {
            System.out.format("%-50s %s", "The setTally validation method: ", "=======GOOD=======\n");
        }
    }


    private static void testgetTally() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "D", "H", ty1, "one", "two");
        if (test1.getTally().equals(ty1))
            System.out.println("The getName method    =======GOOD=======");
        else
            System.out.println("The getName method    =======FAIL=======");

    }

    private static void testgetName() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "D", "H", ty1, "one", "two");
        if (test1.getName().equalsIgnoreCase("Dawson"))
            System.out.println("The getName method    =======GOOD=======");
        else
            System.out.println("The getName method    =======FAIL=======");

    }

    private static void testisLimitedToPostalRange() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "D", "H", ty1, "one", "two");
        if (test1.isLimitedToPostalRange() == true)
            System.out.println("The isLimitedToPostalRange method    =======GOOD=======");
        else
            System.out.println("The isLimitedToPostalRange method    =======FAIL=======");

    }

    private static void testgetPostalRangeStart() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "D", "H", ty1, "one", "two");
        if (test1.getPostalRangeStart().equals("D"))
            System.out.println("The getPostalRangeStart method    =======GOOD=======");
        else
            System.out.println("The getPostalRangeStart method    =======FAIL=======");

    }

    private static void testgetPostalRangeEnd() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "D", "H", ty1, "one", "two");
        if (test1.getPostalRangeEnd().equals("H"))
            System.out.println("The getPostalRangeEnd method    =======GOOD=======");
        else
            System.out.println("The getPostalRangeEnd method    =======FAIL=======");


    }

    private static void testgetStartDate() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty1, "one", "two");
        LocalDate ld = LocalDate.of(2015, 9, 12);
        if (test1.getStartDate().equals(ld))
            System.out.println("The getStartDate method    =======GOOD=======");
        else
            System.out.println("The getStartDate method    =======FAIL=======");

    }

    private static void testgetEndDate() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty1, "one", "two");
        LocalDate ld = LocalDate.of(2015, 9, 13);
        if (test1.getEndDate().equals(ld))
            System.out.println("The getEndDate method    =======GOOD=======");
        else
            System.out.println("The getEndDate method    =======FAIL=======");

    }

    private static void testgetElectionChoices() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty1, "one", "two");
        String[] temp = {"one*0", "two*0"};
        boolean work = true;
        for (int i = 0; i < temp.length; i++) {
            if (!temp[i].equals(test1.getElectionChoices()[i])) {
                work = false;
                System.out.println(temp[i] + "              " + test1.getElectionChoices()[i]);
            }
        }
        if (work)
            System.out.println("The getElectionChoices method    =======GOOD=======");
        else
            System.out.println("The getElectionChoices method    =======FAIL=======");

    }

    private static void testgetElectionType() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty1, "one", "two");

        if (test1.getElectionType() == ElectionType.valueOf("SINGLE"))
            System.out.println("The getElectionType method    =======GOOD=======");
        else
            System.out.println("The getElectionType method    =======FAIL=======");

    }

    private static void testcompareTo() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty1, "one", "two");
        DawsonTally ty2 = new DawsonTally(2, "Dawson");
        DawsonElection test2 = new DawsonElection("Dawson2", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty2, "one", "two");
        if (test1.compareTo(test2) < 0)
            System.out.println("The compareTo method    =======GOOD=======");
        else
            System.out.println("The compareTo method    =======FAIL=======");

    }

    private static void testhashCode() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty1, "one", "two");
        DawsonTally ty2 = new DawsonTally(2, "Dawson");
        DawsonElection test2 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty2, "one", "two");
        if (test1.hashCode() == test2.hashCode())
            System.out.println("The hashCode method     =======GOOD=======");
        else
            System.out.println("The hashCode method     =======FAIL=======");

    }

    private static void testEquals() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty1, "1", "2", "34");
        DawsonTally ty2 = new DawsonTally(2, "Dawson");
        DawsonElection test2 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty2, "1", "2", "34");
        DawsonTally ty3 = new DawsonTally(2, "Dawson");
        DawsonElection test3 = new DawsonElection("Dawson1", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty3, "1", "2", "34");
        if (test1.equals(test2) && !test2.equals(test3))
            System.out.println("The equals              =======GOOD=======");
        else
            System.out.println("The equals              =======FAIL=======");


    }

    private static void testToString() {
        DawsonTally ty1 = new DawsonTally(2, "Dawson");
        DawsonElection test1 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty1, "one", "two");
        if (test1.toString().equals("Dawson*2015*9*12*2015*9*13*H*H*single*2\none\ntwo"))
            System.out.println("testToString              =======GOOD=======");
        else
            System.out.println("testToString              =======FAIL=======");

    }

    private static void the12ParameterConstructor() {
        DawsonTally ty = new DawsonTally(2, "Dawson");
        DawsonElection test = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty, "Ivan", "John");
        System.out.println(test);
        System.out.format("%-50s %s", "The12ParameterConstructor validation: ", "=======GOOD=======\n");
        try {
            DawsonElection test2 = new DawsonElection(null, "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty, "Ivan", "John");
            System.out.format("%-50s %s", "The12ParameterConstructor-->name validation: ", "=======FAIL=======\n");
        } catch (IllegalArgumentException iae) {
            System.out.format("%-50s %s", "The12ParameterConstructor-->name validation: ", "=======GOOD=======\n");
        }
        try {
            DawsonElection test2 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", null, "Ivan", "John");
            System.out.format("%-50s %s", "The12ParameterConstructor-->tally validation: ", "=======FAIL=======\n");
        } catch (IllegalArgumentException iae) {
            System.out.format("%-50s %s", "The12ParameterConstructor-->tally validation: ", "=======GOOD=======\n");

        }
        try {
            DawsonElection test2 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty, null, "John");
            System.out.format("%-50s %s", "TheConstructor-->DawsonBallotItems validation1: ", "=======FAIL=======\n");
        } catch (IllegalArgumentException iae) {
            System.out.format("%-50s %s", "TheConstructor-->DawsonBallotItems validation1: ", "=======GOOD=======\n");
        }
        try {
            DawsonElection test2 = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13, "H", "H", ty, "john", null);
            System.out.format("%-50s %s", "TheConstructor-->DawsonBallotItems validation2: ", "=======FAIL=======\n");
        } catch (IllegalArgumentException iae) {
            System.out.format("%-50s %s", "TheConstructor-->DawsonBallotItems validation2: ", "=======GOOD=======\n");
        }


    }
}
