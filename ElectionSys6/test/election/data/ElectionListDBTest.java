package election.data;

import election.business.DawsonElection;
import election.business.DawsonTally;
import election.business.interfaces.Election;
import util.ListUtilities;
import util.Utilities;

import java.io.IOException;
import java.nio.file.*;

/**
 * @Saad Khan
 */
public class ElectionListDBTest {
    public static void main(String[] args) {
        teardown();
        testAddElection();
        testGetElection();
        testDisconnectElection();
        setup();
    }

    /**
     * tests the disconnect method currently broken due to unkown error in ElectionFileLoader but
     * tested and works otherwise
     */
    private static void testDisconnectElection() {
        setup();
        ObjectSerializedList file = new ObjectSerializedList("../ElectionSys6/datafiles/testfiles/testVoters.ser",
                "../ElectionSys6/datafiles/testfiles/testElections.ser");
        ElectionListDB db = new ElectionListDB(file);

        System.out.println("\n******************** test Disconnect ******************** ");
        System.out.println(
                "\nTest case 1: Add election to database, Disconnect and check if it was write to DB:\n");
        System.out.println(db);

        try {
            String[] choices = new String[]{"One", "Two"};
            DawsonElection election = new DawsonElection("Dawson", "SINGLE", 2015, 9, 12, 2015, 9, 13,
                    "D", "H", new DawsonTally(choices.length, "Dawson"), choices);
            System.out.println("Adding\n");
            db.add(election);
            System.out.println(db);
            System.out.println("disconecting");
            db.disconnect();
            ElectionListDB db2 = new ElectionListDB(file);
            System.out.println(db2);
            System.out.println("IF There are 3 elections in database =>  SUCCESS: Disconect works GOOD");
        } catch (DuplicateElectionException e) {
            System.out.println("FAILING TEST CASE: Add Election doesn't work: " + e);
        } catch (IOException e) {
            System.out.println("FAILING TEST CASE: Disconnect doesn't work: " + e);
        }

        teardown();
    }

    /**
     * tests ElectionListDB's add method by adding an election to database currently works
     */

    private static void testAddElection() {
        setup();
        ObjectSerializedList file = new ObjectSerializedList("../ElectionSys6/datafiles/testfiles/testVoters.ser",
                "../ElectionSys6/datafiles/testfiles/testElections.ser");
        ElectionListDB db = new ElectionListDB(file);
        System.out.println("\n******************** test add Election ******************* ");
        System.out.println("\nTest case 1: Election not in database:\n");
        System.out.println(db);
        try {

            DawsonElection test1 = new DawsonElection("Something", "SINGLE", 2015, 9, 12, 2015, 9, 13,
                    "D", "H", new DawsonTally(2, "Somethings"), "one", "two");
            System.out.println("Adding Election\n");
            db.add(test1);
            System.out
                    .println("Dawson election was added to data base\nSUCCESS: Add Election works GOOD");
        } catch (DuplicateElectionException e) {
            System.out.println("FAILING TEST CASE: getElection doesn't work: " + e);
        }

        System.out.println("\nTest case 2: Election is in database:\n");
        System.out.println(db);
        try {
            DawsonElection test1 = new DawsonElection("Presidential race", "SINGLE", 2015, 9, 12, 2015, 9,
                    13, "D", "H", new DawsonTally(2, "Somethings"), "one", "two");
            System.out.println("Adding Election\n");
            db.add(test1);
            System.out
                    .println("Dawson election was added to data base\nFailure: Add Election works GOOD");
        } catch (DuplicateElectionException e) {
            System.out.println("Success TEST CASE: getElection doesn't work: ");
        }
        teardown();
    }

    /**
     * tests getElection's method by retriving election from database
     */
    private static void testGetElection() {
        setup();
        ObjectSerializedList file = new ObjectSerializedList("../ElectionSys6/datafiles/testfiles/testVoters.ser",
                "../ElectionSys6/datafiles/testfiles/testElections.ser");
        ElectionListDB db = new ElectionListDB(file);

        System.out.println("\n******************** test getElection ******************** ");
        System.out.println("\nTest case 1: Election in database:");
        System.out.println("Currently in Database \n\n" + db);
        try {
            Election election = db.getElection("Presidential race");
            System.out.println(election);
            System.out.println("SUCCESS: Election found " + election.toString());
        } catch (InexistentElectionException e) {
            System.out.println("FAILING TEST CASE: Election should be found, " + e.toString());
        }

        System.out.println("\nTest case 2: Election not in database:");
        try {
            Election election = db.getElection("Failure");
            System.out.println("FAILING TEST CASE: Election found " + election.toString());
        } catch (InexistentElectionException e) {
            System.out.println("SUCCESS: election not found");
        }
        teardown();
    }

    private static void setup() {
        String[] voters = new String[5];
        voters[0] = "joe.mancini@mail.me*Joe*Mancini*H3C4B7";
        voters[1] = "wong.mancini@mail.me*Wong*Mancini*H5C2B3";
        voters[2] = "raj@test.ru*Raj*Wong*H3E1B4";
        voters[3] = "ted@test.ru*Ted*Wong*H2E1B4";
        voters[4] = "uno@test.ru*Uno*Lu*H3E1B6";

        String[] elecs = new String[2];
        elecs[0] =
                "Favourite program*2018*5*1*2019*5*31*H4G*H4G*single*2" + "\nGame of Thrones" + "\nNarcos";
        elecs[1] =
                "Presidential race*2020*11*1*2020*11*1***single*2" + "\nDonald Trump" + "\nAnyone Else";

        String[] tallies = new String[2];
        tallies[0] = "Favourite program*2" + "\n1000*0" + "\n0*560";
        tallies[1] = "Presidential race*2" + "\n100*0" + "\n0*102";

        // make the testfiles directory
        Path dir;
        try {
            dir = Paths.get("../ElectionSys6/datafiles/testfiles");
            if (!Files.exists(dir))
                Files.createDirectory(dir);
            ListUtilities.saveListToTextFile(voters, "../ElectionSys6/datafiles/testfiles/testVoters.txt");
            ListUtilities.saveListToTextFile(elecs, "../ElectionSys6/datafiles/testfiles/testElections.txt");
            ListUtilities.saveListToTextFile(tallies, "../ElectionSys6/datafiles/testfiles/testTally.txt");
            SequentialTextFileList sql = new SequentialTextFileList("../ElectionSys6/datafiles/testfiles/testVoters.txt",
                    "../ElectionSys6/datafiles/testfiles/testElections.txt", "../ElectionSys6/datafiles/testfiles/testTally.txt");
            Utilities.serializeObject(sql.getVoterDatabase(), "../ElectionSys6/datafiles/testfiles/testVoters.ser");
            Utilities.serializeObject(sql.getElectionDatabase(), "../ElectionSys6/datafiles/testfiles/testElections.ser");
        } catch (InvalidPathException e) {
            System.err.println("could not create testfiles directory " + e.toString());
        } catch (FileAlreadyExistsException e) {
            System.err.println("could not create testfiles directory " + e.toString());
        } catch (IOException e) {
            System.err.println("could not create testfiles directory in setup() " + e.toString());
        }
    }

    private static void teardown() {
        Path file;
        try {
            file = Paths.get("../ElectionSys6/datafiles/testfiles/testVoters.txt");
            Files.deleteIfExists(file);
            file = Paths.get("../ElectionSys6/datafiles/testfiles/testElections.txt");
            Files.deleteIfExists(file);
            file = Paths.get("../ElectionSys6/datafiles/testfiles/testTally.txt");
            Files.deleteIfExists(file);
        } catch (InvalidPathException e) {
            System.err.println("could not delete test files " + e.toString());
        } catch (NoSuchFileException e) {
            System.err.println("could not delete test files " + e.toString());
        } catch (DirectoryNotEmptyException e) {
            System.err.println("could not delete test files " + e.toString());
        } catch (IOException e) {
            System.err.println("could not delete test files " + e.toString());
        }
    }

}
