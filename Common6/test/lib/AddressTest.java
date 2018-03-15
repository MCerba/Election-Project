/**
 *
 */
package lib;

public class AddressTest {

  public static void main(String[] args) {
    testTheThreeParameterConstructor();
    testGetCivicNumber();
    testSetCivicNumber();
    TheNoParameterConstructor();
  }


  private static void TheNoParameterConstructor() {
    System.out.println("\nTesting the no parameter constructor.");
    TheNoParameterConstructor("case 1 : no parameter constructor", true);

  }


  private static void TheNoParameterConstructor(String testCase, boolean expectValid) {

    try {
      Address theAddress = new Address();
      System.out.print("\tThe Address instance was created: "
          + theAddress);
      if (!expectValid) {
        System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
      }
      int expectZero =
          theAddress.getCivicNumber().length() + theAddress.getStreetName().length() + theAddress
              .getCity().length();
      if (expectZero == 0) {
        System.out.print("NoParameterConstructor has works properly");
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
        System.out.print(" Error! Expected Valid. ==== FAILED TEST ====");
      }
    } catch (Exception e) {
      System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +
          " " + e.getMessage() + " ==== FAILED TEST ====");
      if (expectValid) {
        System.out.print(" Expected Valid.");
      }
    }
    System.out.println("\\n");
  }


  private static void testTheThreeParameterConstructor() {
    System.out.println("\nTesting the three parameter constructor.");
    testTheThreeParameterConstructor(
        "Case 1 - Valid data (3040 Sherbrooke Westmount)", "3040",
        "Sherbrooke", "Westmount", true);
    testTheThreeParameterConstructor(
        "Case 2 - Invalid data � empty civicNumber ( Sherbrooke Westmount)", "",
        "Sherbrooke", "Westmount", false);
    testTheThreeParameterConstructor(
        "Case 3 - Invalid data � twospaces  civicNumber ( Sherbrooke Westmount)", "  ",
        "Sherbrooke", "Westmount", false);
    testTheThreeParameterConstructor(
        "Case 4 - Invalid data � empty streetName ( Sherbrooke Westmount)", "3040",
        " ", "Westmount", false);
    testTheThreeParameterConstructor(
        "Case 5 - Invalid data � twospaces streetName ( Sherbrooke Westmount)", "3040",
        "  ", "Westmount", false);
    testTheThreeParameterConstructor(
        "Case 6 - Invalid data � empty city ( Sherbrooke Westmount)", "3040",
        "Sherbrooke", "", false);
    testTheThreeParameterConstructor(
        "Case 7 - Invalid data � twospaces city ( Sherbrooke Westmount)", "3040",
        "Sherbrooke", "  ", false);
    testTheThreeParameterConstructor(
        "Case 8 - Invalid data � null civicNumber (null Sherbrooke Westmount)",
        null, "Sherbrooke", "Westmount", false);
    testTheThreeParameterConstructor(
        "Case 9 - Invalid data � null streetName (null Sherbrooke Westmount)",
        "3040", null, "Westmount", false);
    testTheThreeParameterConstructor(
        "Case 10 - Invalid data � null city (null Sherbrooke Westmount)",
        "3040", "Sherbrooke", null, false);
  }

  private static void testTheThreeParameterConstructor(String testCase,
      String civicNumber, String streetName, String city,
      boolean expectValid) {
    System.out.println(" " + testCase);
    try {
      Address theAddress = new Address(civicNumber, streetName, city);
      System.out.print("\tThe Address instance was created: "
          + theAddress);

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
      System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +
          " " + e.getMessage() + " ==== FAILED TEST ====");
      if (expectValid) {
        System.out.print(" Expected Valid.");
      }
    }
    System.out.println("\\n");
  }

  private static void testGetCivicNumber() {
    System.out.println("\n\nTesting the getCivicNumber method.");
    testGetCivicNumber("Case 1: 3040 without leading/trailing spaces",
        "3040", "3040");
    testGetCivicNumber("Case 2: 3040 with leading/trailing spaces",
        "    3040    ", "3040");
  }

  private static void testGetCivicNumber(String testCase,
      String civicNumber, String expectedCivicNumber) {
    System.out.println("   " + testCase);
    Address theAddress =
        new Address(civicNumber, "Sherbrooke", "Westmount");
    System.out.print("\tThe Address instance was created: " + theAddress);

    if (!theAddress.getCivicNumber().equals(expectedCivicNumber)) {
      System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
    }

    System.out.println("\\n");
  }

  private static void testSetCivicNumber() {
    System.out.println("\n\nTesting the setCivicNumber method.");
    testSetCivicNumber("Case 1: Valid - 2086 without leading/trailing spaces",
        "2086", "2086", true);
    testSetCivicNumber("Case 2: Invalid null civic number",
        null, "", false);
    testSetCivicNumber("Case 3: Valid - 2086 with trailing spaces",
        "2086 ", "2086", true);
    testSetCivicNumber("Case 4: Valid - 2086 with leading spaces",
        " 2086", "2086", true);
    testSetCivicNumber("Case 5: Invalid - twospaces civic number",
        "  ", "", false);


  }

  private static void testSetCivicNumber(String testCase,
      String civicNumber, String expectedCivicNumber, boolean expectValid) {
    System.out.println("   " + testCase);
    Address theAddress =
        new Address("3040", "Sherbrooke", "Westmount");
    try {
      theAddress.setCivicNumber(civicNumber);
      System.out.print("\tThe Address instance was created: " + theAddress);

      if (!theAddress.getCivicNumber().equals(expectedCivicNumber)) {
        System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
      }
    } catch (IllegalArgumentException iae) {
      System.out.print("\t" + iae.getMessage());
      if (expectValid) {
        System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
      }
    } catch (Exception e) {
      System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
          e.getMessage() + " ==== FAILED TEST ====");
      if (expectValid) {
        System.out.print(" Expected Valid.");
      }
    }

    System.out.println("\\n");
  }


}
