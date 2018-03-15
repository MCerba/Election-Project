package runner;


import election.business.DawsonBallotItemTest;
import election.business.DawsonElectionTest;
import election.business.DawsonVoterTest;
import election.business.VoterComparatorTest;
import election.data.ElectionFileLoaderTest;
import election.data.ElectionListDBTest;
import election.data.VoterListDBTest;
import lib.AddressTest;
import lib.EmailTest;
import lib.NameTest;
import lib.PostalCodeTest;
import util.ListUtilitiesTest;

import java.io.IOException;

/**
 * @author Simon Guevara-Ponce
 *
 */
public class ElectionSysTestRunner {

  /**
   * @param args
   */
  public static void main(String[] args) {

    System.out.println(
            "----------------------------------------------------------RUNNING ALL TEST CLASSES------------------------------------------------------------------");
    System.out.println(
            "\n \n -----------------------------------------PACKAGE: LIB ------------------------------------\n \n");
    System.out.println("\n \n ---------------------AdressTest--------------------- \n \n");
    AddressTest.main(null);
    System.out.println("\n \n ---------------------EmailTest--------------------- \n \n");
    EmailTest.main(null);
    System.out.println("\n \n ---------------------NameTest--------------------- \n \n");
    NameTest.main(null);
    System.out.println("\n \n ---------------------PostalCodeTest--------------------- \n \n");
    PostalCodeTest.main(null);


    System.out.println(
            "\n \n -----------------------------------------PACKAGE: UTIL ------------------------------------\n \n");
    System.out.println("\n \n ---------------------ListUtilities--------------------- \n \n");
    try {
      ListUtilitiesTest.main(null);
    }
    catch (IOException e)
    {
      System.out.println(e);
    }


    System.out.println(
            "\n \n -----------------------------------------PACKAGE: ELECTION.BUSINESS ------------------------------------\n  \n\n");
    System.out.println("\n \n ---------------------DawsonBallotItemTest--------------------- \n \n");
    DawsonBallotItemTest.main(null);
    System.out.println("\n \n \n---------------------DawsonElectionTest--------------------- \n \n");
    DawsonElectionTest.main(null);
    System.out.println("\n \n \n---------------------DawsonVoterTest--------------------- \n \n \n");
    DawsonVoterTest.main(null);
    System.out.println("\n \n \n---------------------VoterComparatorTest--------------------- \n \n \n");
    VoterComparatorTest.main(null);


    System.out.println(
            "\n \n \n -------------------------------------------PACKAGE: ELECTION.DATA ---------------------------------------\n \n \n");
    System.out.println("\n \n \n ---------------------ElectionFileLoaderTest--------------------- \n \n \n");
    ElectionFileLoaderTest.main(null);
    System.out.println("\n \n \n ---------------------ElectionListDBTest--------------------- \n \n\n");
    ElectionListDBTest.main(null);
    System.out.println("\n \n \n ---------------------VoterListDBTest--------------------- \n \n\n");
    VoterListDBTest.main(null);
    System.out.println("\n \n \n---------------------DONE--------------------- \n \n");

  }
}
