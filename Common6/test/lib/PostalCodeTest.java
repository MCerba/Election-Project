/**
 *
 */
package lib;

/**
 * @author Saad
 */
public class PostalCodeTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    //Instantiate new objects
    PostalCode testCode1 = new PostalCode("H4M1S4");
    PostalCode testCode2 = new PostalCode("H4M1S4");
    PostalCode testCode3 = new PostalCode("G4M1S4");
    PostalCode testCode4 = new PostalCode("H4M 1S4");
    PostalCode testCode5 = new PostalCode("L4M 1S4");

    //testing equals method as well as code if it would work
    System.out.println("testing equals");
    testTest(testCode1.equals(testCode2), true);
    testTest(testCode1.equals(testCode3), false);
    testTest(testCode1.equals(testCode4), true);

    //testing hashCode
    System.out.println("\ntesting hashCode");
    testTest(testCode1.hashCode(), testCode2.hashCode(), true);
    testTest(testCode1.hashCode(), testCode3.hashCode(), false);
    testTest(testCode4.hashCode(), testCode3.hashCode(), false);

    //testing compareTO
    System.out.println("\ntesting compareTO");
    testTest(testCode1.compareTo(testCode2), 0);
    testTest(testCode1.compareTo(testCode3), 1);
    testTest(testCode1.compareTo(testCode4), 0);
    testTest(testCode1.compareTo(testCode5), -4);

    //testing getCode
    System.out.println("\ntesting getCode");
    testTest(testCode1.getCode(), "H4M1S4");
    testTest(testCode2.getCode(), "H4M1S4");
    testTest(testCode3.getCode(), "G4M1S4");
    testTest(testCode4.getCode(), "H4M1S4");
    testTest(testCode5.getCode(), "L4M1S4");

    //testing inRange
    System.out.println("\ntesting inRange");
    testTest(testCode1.inRange("G", "I"), true);
    testTest(testCode2.inRange("G", "G"), false);
    testTest(testCode3.inRange("A", "B"), false);
    testTest(testCode4.inRange("G1", "G5"), false);
    testTest(testCode5.inRange("l4", "l6"), true);
    testTest(testCode5.inRange("l4a 1b5", "l6N 9z9"), true);
    testTest(testCode5.inRange("l4a1b5", "l6N9z9"), true);
    testTest(testCode5.inRange("l4n", "l6n"), false);
    testTest(testCode5.inRange("l4a", "l6l"), true);
    testTest(testCode5.inRange("l4a", "l6n9z9"), true);
    testTest(testCode5.inRange("l4a1b5", "l6n"), true);

    //testing toString
    System.out.println("\ntesting toString");
    testTest(testCode1.toString(), "H4M1S4");
    testTest(testCode2.toString(), "H4M1S4");
    testTest(testCode3.toString(), "G4M1S4");
    testTest(testCode4.toString(), "H4M1S4");
    testTest(testCode5.toString(), "L4M1S4");

  }


  private static void testTest(int a, int b) {
    if (a == b) {
      System.out.println("Working");
    } else {
      System.out.println("Failure");
    }
  }


  private static void testTest(String a, String b) {
    if (a.equals(b)) {
      System.out.println("Working");
    } else {
      System.out.println("Failure");
    }
  }


  public static void testTest(boolean b, boolean c) {
    if (b == c) {
      System.out.println("Working");
    } else {
      System.out.println("Failure");
    }
  }

  public static void testTest(double b, double c, boolean result) {
    if ((b == c) == (result)) {
      System.out.println("Working");
    } else {
      System.out.println("Failure");
    }
  }
}
