package election.data;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;
import util.ListUtilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SortMergeApp {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        Comparable[] listvoter = new Comparable[9];
        Comparable[] listelection = new Comparable[9];
        new File("src/datafiles/databases").mkdir();
        Object[] stub = new Object[0];
        ListUtilities.saveListToTextFile(stub, "duplicateVoters.txt", false, StandardCharsets.UTF_8);
        ListUtilities.saveListToTextFile(stub, "duplicateElection.txt", false, StandardCharsets.UTF_8);


        Voter[] voterarray21 =
                ElectionFileLoader.getVoterListFromSequentialFile("ElectionSys6/datafiles/unsorted/voters21.txt");

        Comparable[] templistvoter21;
        templistvoter21 = voterarray21;
        ListUtilities.sort(templistvoter21);
        listvoter = templistvoter21;


        Election[] electionarray21 =
                ElectionFileLoader.getElectionListFromSequentialFile("ElectionSys6/datafiles/unsorted/elections1.txt");

        System.out.println(electionarray21[1].toString());
        Comparable<Election>[] templistelection21;
        templistelection21 = electionarray21;
        ListUtilities.sort(templistelection21);
        listelection = templistelection21;


        for (int i = 1; i < 8; i++) {
            Voter[] voterarray =
                    ElectionFileLoader.getVoterListFromSequentialFile("ElectionSys6/datafiles/unsorted/voters" + i + ".txt");

            Comparable<Voter>[] templistvoter;
            templistvoter = voterarray;
            ListUtilities.sort(templistvoter);
            listvoter = ListUtilities.merge(listvoter, templistvoter, "duplicateVoters.txt");


            Election[] electionarray =
                    ElectionFileLoader.getElectionListFromSequentialFile("ElectionSys6/datafiles/unsorted/elections" + i + ".txt");
            
            Comparable<Election>[] templistelection = electionarray;
            ListUtilities.sort(templistelection);
            listelection = ListUtilities.merge(listelection, templistelection, "duplicateElection.txt");


        }
        Files.createDirectory(Paths.get("ElectionSys6/datafiles/database/"));
        ListUtilities.saveListToTextFile(listvoter, "ElectionSys6/datafiles/database/voters.txt");
        ListUtilities.saveListToTextFile(listelection, "ElectionSys6/datafiles/database/elections.txt");


    }


}


