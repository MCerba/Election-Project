package lib;

import lib.Name;

public class NameTest {





  public static void main (String [] ags){
    
      testTheThreeParameterConstructor();
      testCopyConstructor(); 
      testcompareTo();

    testequals();

  }


  private static void testTheThreeParameterConstructor() {
    System.out.println("\nTesting the three parameter constructor.");
    testTheThreeParameterConstructor("Case 1 - Valid data", "Henry", "Ford", true);
    testTheThreeParameterConstructor("Case 2 - Invalid data empty firstname", "", "Ford", false);
    testTheThreeParameterConstructor("Case 3 - Invalid data empty lastname", "Henry", "", false);
    testTheThreeParameterConstructor("Case 4 - Invalid data both empty", "", "", false);
    testTheThreeParameterConstructor("Case 5 - Invalid data invalid character", "@lex", "Ford",
        false);
    testTheThreeParameterConstructor("Case 6 - Invalid data  invalid characters", "@>:?}{>",
        "{!@~@$%^&*(", false);
    testTheThreeParameterConstructor("Case 7 - Invalid data too short name", "a", "Ford", false);
    testTheThreeParameterConstructor("Case 8 - Valid data short name", "zz", "Ford", true);
    testTheThreeParameterConstructor("Case 9 - Invalid data null name", null, "Ford", false);
  }

  private static void testTheThreeParameterConstructor(String testCase, String fname, String lname,
      boolean expectValid) {
    System.out.println(" " + testCase);
    try {
      Name nametest = new Name(fname, lname);
      System.out.print("\tThe Name instance was created: " + nametest);

      if (!expectValid) {
        System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
      }

    } catch (IllegalArgumentException iae) {
      System.out.print("\t" + iae.getMessage());
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

  //_____________________________________________________________________________________________________-


  private static void testCopyConstructor() {
    System.out.println("\nTesting the copy constructor.");
    Name nametest1 = new Name("Henry", "Ford");
    testCopyConstructor("Case 1 - Valid data", nametest1, true);
    testCopyConstructor("Case 1 - Invalid data - null object", ((Name) null), true);


  }


  private static void testCopyConstructor(String testCase, Name name, boolean expectValid) {

    System.out.println(" " + testCase);
    try {
      Name nametest = name;
      System.out.print("\tThe Name instance was created: " + nametest);

      if (!expectValid) {
        System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
      }

    } catch (IllegalArgumentException iae) {
      System.out.print("\t" + iae.getMessage());
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

  //_____________________________________________________________________________________________


  private static void testcompareTo() {
    Name nametest = new Name("Henry", "Ford");
    System.out.println("\nTesting the compareTo() method.");

    Name nametest1 = new Name("Henry", "Ford");
    testcompareTo("Case 1 - Valid data", nametest, nametest1, "equals", true);

    Name nametest2 = new Name("Alex", "Ford");
    testcompareTo("Case 2 - Valid data", nametest, nametest2, "less", true);

    Name nametest3 = new Name("Zalex", "Ford");
    testcompareTo("Case 3 - Valid data", nametest, nametest3, "more", true);

    testcompareTo("Case 4 - Invalid data", nametest, ((Name) null), "more", false);

  }


  private static void testcompareTo(String testCase, Name name, Name compname, String expected,
      boolean expectValid) {
    System.out.println(" " + testCase);
    try {

      int result = name.compareTo(compname);

      if (!expectValid) {
        System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
      }

      if (expected == "less" && result >= -1) {
        System.out.print(" Error! Invalid result. Expected less. Got " + result);
      }

      if (expected == "equals" && result != 0) {
        System.out.print(" Error! Invalid result. Expected equals. Got " + result);
      }

      if (expected == "more" && result < 1) {
        System.out.print(" Error! Invalid result. Expected more. Got " + result);
      }

    } catch (IllegalArgumentException iae) {
      System.out.print("\t" + iae.getMessage());
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

  //___________________________________________________________________________________________________________________


  private static void testequals() {
    Name nametest = new Name("H enry", "Ford");
    System.out.println("\n Testing equals() method.");

    Name nametest1 = new Name("H enry", "Ford");
    testequals("Case 1 - Valid data", nametest, nametest1, true, true);

    Name nametest2 = new Name("Alex", "Ford");
    testequals("Case 2 - Valid data", nametest, nametest2, false, true);

    testequals("Case 3 - Valid data", nametest, ((Name) null), false, true);
    testequals("Case 4 - Valid data", nametest, nametest, true, true);


  }


  private static void testequals(String testCase, Name name, Name compname, boolean expected,
      boolean expectValid) {
    System.out.println(" " + testCase);
    try {

      boolean result = name.equals(compname);

      if (!expectValid) {
        System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
      }

      if (expected == result) {
        System.out.print(" Valid result. Expected and Got " + result);
      }

      if (expected != result) {
        System.out.print(" Invalid result. Expected " + expected + "  and Got " + result);
      }


    } catch (IllegalArgumentException iae) {
      System.out.print("\t" + iae.getMessage());
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


 
  
  
