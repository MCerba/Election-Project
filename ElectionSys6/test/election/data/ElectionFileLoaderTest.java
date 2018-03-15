package election.data;

import election.business.interfaces.Election;

import java.io.IOException;

public class ElectionFileLoaderTest {

  public static void main(String[] args) {
    getVoterListFromSequentialFileTest();
    getElectionListFromSequentialFileTest();
    setExistingTallyFromSequentialFileTest();
  }

  public static void getVoterListFromSequentialFileTest() {
    System.out.println(
            "Testing getVoterListFromSequentialFile method on voters1.txt Testing the getName()");
    System.out
            .println("method on index 1 of the returned Voter[]. Result should be Daisey*Johnson\n");

    try {
      System.out.println(
              ElectionFileLoader.getVoterListFromSequentialFile("../ElectionSys6/datafiles/unsorted/voters1.txt")[0]
              .getName());
      System.out.println("RUNNING SMOOTHLY --> PASS");
    }

    catch (IOException e) {
      System.out.println("FAIL");
    }

    System.out.println("\n");

    System.out.println(
            "Testing getVoterListFromSequentialFile method on voters1.txt Testing the getName()");
    System.out.println(
            "method on index 2 of the returned Voter[]. Result should be Emily*Andras because");
    System.out.println(
            "the postal code of the 2nd voter is invalid so they will not be added to the array\n");

    try {
      System.out.println(
              ElectionFileLoader.getVoterListFromSequentialFile("../ElectionSys6/datafiles/unsorted/voters1.txt")[1]
              .getName());
      System.out.println("RUNNING SMOOTHLY --> PASS");
    }

    catch (IOException e) {
      System.out.println("FAIL");
    }

    System.out.println("\n");

    System.out.println(
            "Testing getVoterListFromSequentialFile method on voters5.txt Testing the getName()");
    System.out
            .println("method on index 0 of the returned Voter[]. Result should be James*Taylor\n");

    try {
      System.out.println(
              ElectionFileLoader.getVoterListFromSequentialFile("../ElectionSys6/datafiles/unsorted/voters5.txt")[0]
              .getName());
      System.out.println("RUNNING SMOOTHLY --> PASS");
    }

    catch (IOException e) {
      System.out.println("FAIL");
    }

    System.out.println("\n");

    System.out.println(
            "Testing getVoterListFromSequentialFile method on voters3.txt Testing the getPostalCode()");
    System.out.println("method on index 3 of the returned Voter[]. Result should be B1M1Z6\n");

    try {
      System.out.println(
              ElectionFileLoader.getVoterListFromSequentialFile("../ElectionSys6/datafiles/unsorted/voters3.txt")[3]
              .getPostalCode());
      System.out.println("RUNNING SMOOTHLY --> PASS");
    }

    catch (IOException e) {
      System.out.println("FAIL");
    }

    System.out.println("\n");
  }

  public static void getElectionListFromSequentialFileTest() {
    System.out.println("Testing getElectionListFromSequentialFile method on file");
    System.out.println("\n");
    try {
      Election[] elections8Array =
              ElectionFileLoader.getElectionListFromSequentialFile("../ElectionSys6/datafiles/unsorted/elections8.txt");
      for (Election e : elections8Array) {
        System.out.println(e.toString());
      }
    }

    catch (IOException e) {
      System.out.println("FAIL");
    } catch (IllegalArgumentException iae) {
      System.out.println("Elections8  is invalid --> PASS, " + iae);
    }

    System.out.println("\n");

    try {
      Election[] elections5Array =
              ElectionFileLoader.getElectionListFromSequentialFile("../ElectionSys6/datafiles/unsorted/elections5.txt");
      if (elections5Array[0].getName().equals("Presidental race")) {
        System.out.println("Elections5 is valid  --> PASS");
      }
    }

    catch (IOException e) {
      System.out.println("FAIL");
    }

    System.out.println("\n");

    try {
      Election[] elections21Array = ElectionFileLoader
              .getElectionListFromSequentialFile("../ElectionSys6/datafiles/unsorted/elections21.txt");
    } catch (IllegalArgumentException iae) {
      System.out.println("Elections21 is invalid --> PASS, " + iae);
    } catch (IOException e) {
      System.out.println("Elections21 exist --> FAIL" + e);
    }

    System.out.println("\n");
  }

  public static void setExistingTallyFromSequentialFileTest() {
    try {
      System.out
              .println("Testing tally.txt with an array of elections3.txt elections. The only name");
      System.out.println(
              "that matches is \"Dawson Color Election 2020\" so the results for that election");
      System.out.println("should be displayed. The result should be a 2D of array that is 6x6\n");
      ElectionFileLoader.setExistingTallyFromSequentialFile("../ElectionSys6/datafiles/unsorted/tally.txt",
          ElectionFileLoader
                  .getElectionListFromSequentialFile("../ElectionSys6/datafiles/unsorted/elections3.txt"));
      System.out.println("\nPASS");
    }

    catch (IOException e) {
      System.out.println("FAIL");
    }

    System.out.println("\n");

    try {
      System.out
              .println("Testing tally.txt with an array of elections4.txt elections. The only name");
      System.out.println(
              "that matches is \"Brittany independence referendum\" so the results for that election");
      System.out.println("should be displayed. The result should be a 2D of array that is 2x2\n");
      ElectionFileLoader.setExistingTallyFromSequentialFile("../ElectionSys6/datafiles/unsorted/tally.txt",
          ElectionFileLoader
                  .getElectionListFromSequentialFile("../ElectionSys6/datafiles/unsorted/elections4.txt"));
      System.out.println("\nPASS");
    }

    catch (IOException e) {
      System.out.println("FAIL");
    }

    System.out.println("\n");

    try {
      System.out
              .println("Testing tally.txt with an array of elections1.txt elections. No names match");
      System.out.println("so nothing should be displayed \n");
      ElectionFileLoader.setExistingTallyFromSequentialFile("../ElectionSys6/datafiles/unsorted/tally.txt",
          ElectionFileLoader
                  .getElectionListFromSequentialFile("../ElectionSys6/datafiles/unsorted/elections1.txt"));
      System.out.println("\nPASS");
    }

    catch (IOException e) {
      System.out.println("FAIL");
    }

    System.out.println("\n");

    try {
      System.out
              .println("Testing tally.txt with an array of electionsTest.txt elections. Both names");
      System.out
              .println("\"Dawson Color Election 2020\" and \"Brittany independence referendum\" match");
      System.out.println("so the result should be a 2D of array that is 6x6 and 2x2 \n");
      ElectionFileLoader.setExistingTallyFromSequentialFile("../ElectionSys6/datafiles/unsorted/tally.txt",
          ElectionFileLoader
                  .getElectionListFromSequentialFile("../ElectionSys6/datafiles/unsorted/electionsTest.txt"));
      System.out.println("\nPASS");
    }

    catch (IOException e) {
      System.out.println("FAIL");
    }

    System.out.println("\n");

  }
}
