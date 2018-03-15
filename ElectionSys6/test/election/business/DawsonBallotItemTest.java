package election.business;

/**
 *
 */

/**
 * @author Saad
 */
public class DawsonBallotItemTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //create test objects
        DawsonBallotItem item1 = new DawsonBallotItem("item1", 210);
        DawsonBallotItem item2 = new DawsonBallotItem("item2", 20);
        DawsonBallotItem item3 = new DawsonBallotItem("item2", 20);

        //setValue Test
        System.out.println("Testing setValue");
        item1.setValue(15);
        testTest(item1.getValue(), 15);
        item1.setValue(0);
        testTest(item1.getValue(), 0);

        //toString Test
        System.out.println("\nTesting toString");
        testTest(item1.toString(), "item1*0");

        //hashCode Test

        System.out.println("\nTesting hashCode");
        testTest(item2.hashCode(), item3.hashCode());

        //equals Test
        System.out.println("\nTesting equals");
        testTest(item1.equals(item1), true);
        testTest(item1.equals(item2), false);
        testTest(item1.equals(item3), false);

        //compareTo Test
        System.out.println("\nTesting compareTo");
        testTest(item1.compareTo(item2), -1);
        testTest(item1.compareTo(item3), -1);
        testTest(item2.compareTo(item3), 0);


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
