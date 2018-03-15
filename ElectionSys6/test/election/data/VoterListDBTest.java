package election.data;

import election.business.DawsonVoter;
import election.business.interfaces.Voter;
import lib.Email;
import lib.PostalCode;
import util.ListUtilities;
import util.Utilities;

import java.io.IOException;
import java.nio.file.*;

public class VoterListDBTest {

    public static void main(String[] args) {
        testGetVoter();
        testAddVoter();
        testDisconnect();
        testUpdate();
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
            System.err.println("could not delete test files " + e.getMessage());
        } catch (NoSuchFileException e) {
            System.err.println("could not delete test files " + e.getMessage());
        } catch (DirectoryNotEmptyException e) {
            System.err.println("could not delete test files " + e.getMessage());
        } catch (IOException e) {
            System.err.println("could not delete test files " + e.getMessage());
        }

    }

    private static void testGetVoter() {
        setup();
        ObjectSerializedList file = new ObjectSerializedList("../ElectionSys6/datafiles/testfiles/testVoters.ser",
                "../ElectionSys6/datafiles/testfiles/testElections.ser");
        VoterListDB db = new VoterListDB(file);

        System.out.println("\n******************** test getVoter ******************** ");
        System.out.println("\nTest case 1: Voter in database:");
        System.out.println(db);
        try {
            Voter voter = db.getVoter("raj@test.ru");
            System.out.println("SUCCESS: Voter found " + voter.toString());
        } catch (InexistentVoterException e) {
            System.out.println("FAILING TEST CASE: voter should be fould  but: " + e);
        }

        System.out.println("\nTest case 2: Voter not in database:");
        try {
            Voter voter = db.getVoter("jar@test.ru");

            System.out.println("FAILING TEST CASE: Voter found " + voter.toString());
        } catch (InexistentVoterException e) {
            System.out.println("SUCCESS: voter not found");
        }

        teardown();
    }

    private static void testAddVoter() {
        setup();
        ObjectSerializedList file = new ObjectSerializedList("../ElectionSys6/datafiles/testfiles/testVoters.ser",
                "../ElectionSys6/datafiles/testfiles/testElections.ser");
        VoterListDB db = new VoterListDB(file);

        System.out.println("\n******************** test addVoter ******************* ");
        System.out.println("\nTest case 1: Voter in database:");
        System.out.println(db);
        try {
            Voter voter = db.getVoter("raj@test.ru");
            db.add(voter);
            System.out.println("FAILING TEST CASE: voter should be alredy in list");
        } catch (InexistentVoterException e) {
            System.out.println("FAILING TEST CASE: getVoter doesn't work: " + e);
        } catch (DuplicateVoterException e) {
            System.out.println("SUCCESS: Voter is alredy in list found ");
        }

        System.out.println("\nTest case 2: Voter not in database:");
        try {
            Voter voter = new DawsonVoter("Mike", "Duke", "mike@mail.ru", "H3K 1N1");
            db.add(voter);
            System.out.println(db);
            System.out.println(
                    "IF There are 6 voters and Miki is in the 3nd place =>  SUCCESS: AddVoter works GOOD");
        } catch (DuplicateVoterException e) {
            System.out.println("FAILING TEST CASE: getVoter doesn't work: " + e);
        }

        teardown();
    }

    private static void testDisconnect() {
        setup();
        ObjectSerializedList file = new ObjectSerializedList("../ElectionSys6/datafiles/testfiles/testVoters.ser",
                "../ElectionSys6/datafiles/testfiles/testElections.ser");
        VoterListDB db = new VoterListDB(file);

        System.out.println("\n******************** test Disconnect ******************** ");
        System.out.println(
                "\nTest case 1: Add Voter to database, Disconnect and chech if it was write to DB:\n");

        try {
            Voter voter = new DawsonVoter("Mike", "Duke", "mike@mail.ru", "H3K 1N1");
            db.add(voter);
            System.out.println("Database before Disconect():");
            System.out.println(db);
            db.disconnect();
            VoterListDB db2 = new VoterListDB(file);
            System.out.println("Database after Disconect():");
            System.out.println(db2);
            System.out.println(
                    "IF There are 6 voters and Mike is in the 3nd place =>  SUCCESS: Disconect works GOOD");
        } catch (DuplicateVoterException e) {
            System.out.println("FAILING TEST CASE: AddVoter doesn't work: " + e);
        } catch (IOException e) {
            System.out.println("FAILING TEST CASE: Disconnect doesn't work: " + e);
        }

        teardown();
    }

    private static void testUpdate() {
        setup();
        ObjectSerializedList file = new ObjectSerializedList("../ElectionSys6/datafiles/testfiles/testVoters.ser",
                "../ElectionSys6/datafiles/testfiles/testElections.ser");
        VoterListDB db = new VoterListDB(file);

        System.out.println("\n******************** test Update ******************** ");
        System.out.println(
                "\nTest case 1: Add Voter to database, Update it and chech if it was updated in the DB:\n");
        // System.out.println(db);

        try {
            Voter voter = new DawsonVoter("Mike", "Duke", "mike@mail.ru", "H3K 1N1");
            db.add(voter);

            db.update(new Email("mike@mail.ru"), new PostalCode("H2H2B2"));
            System.out.println(db);
            System.out.println(
                    "IF There are 6 voters and Mike is in the 3nd place  and his Postal Code is \"H2H2B2\" =>  SUCCESS: Update works GOOD");
        } catch (DuplicateVoterException e) {
            System.out.println("FAILING TEST CASE: AddVoter doesn't work: " + e);
        } catch (InexistentVoterException e) {
            System.out.println("FAILING TEST CASE: Update doesn't work: " + e);
        }

        System.out.println("\nTest case 2: Voter not in database:\n");

        try {

            db.update(new Email("mihail@mail.ru"), new PostalCode("H2H2B2"));
            System.out.println(db);
            System.out.println(
                    "IF There are 6 voters and Mike is in the 3nd place  and his Postal Code is \"H2H2B2\" =>  FAILING TEST CASE: Update doesn't work");
        } catch (InexistentVoterException e) {
            System.out.println("SUCCESS: Update works GOOD! ");
        }

        teardown();
    }
}
