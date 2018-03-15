package util;

import java.io.IOException;

import static util.ListUtilities.merge;
import static util.ListUtilities.sort;

/**
 * @author Saad
 */
public class ListUtilitiesTest {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {

        Comparable[] c1 = new Comparable[3];
        c1[0] = Integer.valueOf(1);
        c1[1] = Integer.valueOf(11);
        c1[2] = Integer.valueOf(2);

        Comparable[] c2 = new Comparable[3];
        c2[0] = Integer.valueOf(7);
        c2[1] = Integer.valueOf(12);
        c2[2] = Integer.valueOf(11);

        Comparable[] c3 = new Comparable[4];
        c3[0] = Integer.valueOf(7);
        c3[1] = Integer.valueOf(12);
        c3[2] = Integer.valueOf(6);
        c3[3] = Integer.valueOf(5);

        sort(c1);
        sort(c2);
        sort(c3);
        Comparable[] merged1 = merge(c1, c2, "duplicateFile1.txt");
        Comparable[] merged2 = merge(c1, c3, "duplicateFile.txt");
        testingSort(c1, true);
        testingMergeSameParameters(merged1, true);
        testingMergeDifferentParameters(merged2, true);
    }

    /**
     * @param cSorted
     * @param expectValid
     */
    private static void testingSort(Comparable[] cSorted, boolean expectValid) {
        try {
            boolean result = false;
            System.out.print("\t\nSorting numbers 1,11,2 -> 1,2,11");
            if (cSorted[0].equals(1) && cSorted[1].equals(2) && cSorted[2].equals(11))
                result = true;
            if (result != expectValid) {
                System.out.print("==== FAILED TEST ====");
            } else
                System.out.print("\t ==== PASSED TEST ====");
        } catch (IllegalArgumentException iae) {
            System.out.print("\t" + iae.getMessage());
        }
    }

    /**
     * @param cMerged
     * @param expectValid
     */
    private static void testingMergeDifferentParameters(Comparable[] cMerged, boolean expectValid) {
        try {
            boolean result = false;
            System.out.print("\t\nMerging 1,11,2 + 7,12,6,5 -> 1,2,5,6,7,11");
            if (cMerged[0].equals(1) && cMerged[1].equals(2) && cMerged[2].equals(5) && cMerged[3].equals(6) &&
                    cMerged[4].equals(7) && cMerged[5].equals(11))
                result = true;
            if (result != expectValid) {
                System.out.print("==== FAILED TEST ====");
            } else
                System.out.print("\t ==== PASSED TEST ====");
        } catch (IllegalArgumentException iae) {
            System.out.print("\t" + iae.getMessage());
        }
    }

    /**
     * @param cMerged
     * @param expectValid
     */
    private static void testingMergeSameParameters(Comparable[] cMerged, boolean expectValid) {
        try {
            boolean result = false;
            System.out.print("\t\nMerging 1,11,2 + 7,11,12 -> 1,2,7,11,12");
            if (cMerged[0].equals(1) && cMerged[1].equals(2) && cMerged[2].equals(7) && cMerged[3].equals(11)
                    && cMerged[4].equals(12))
                result = true;
            if (result != expectValid) {
                System.out.print("==== FAILED TEST ====");
            } else
                System.out.print("\t ==== PASSED TEST ====");
        } catch (IllegalArgumentException iae) {
            System.out.print("\t" + iae.getMessage());
        }
    }
}