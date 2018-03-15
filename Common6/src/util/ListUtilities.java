package util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// skeleton provides the imports, plus methods saveListToTextFile and the Comparator sort overload

/**
 * @author Saad Khan
 */
public class ListUtilities {
  private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

  private ListUtilities() {}

  /**
   * Takes in a list of objects and writes them to a given file. This method overwrites data in file
   * and uses UTF8 character set.
   *
   * @param objects Array of items to be written to file.
   * @param filename filename and location of the file where list of items will be written.
   * @throws IOException if an I/O error occurs writing to or creating the file
   */

  public static void saveListToTextFile(Object[] objects, String filename) throws IOException {
    saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
  }

  /**
   * Takes in a list of objects and writes them to a given file. This method uses UTF8 character
   * set.
   *
   * @param objects Array of items to be written to file.
   * @param filename filename and location of the file where list of items will be written.
   * @param append boolean indicating if the file is overwritten or if the items are written to the
   *        end of the file.
   * @throws IOException if an I/O error occurs writing to or creating the file
   */

  public static void saveListToTextFile(Object[] objects, String filename, boolean append)
      throws IOException {
    saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
  }

  /**
   * Takes in a list of objects and writes them to a given file.
   *
   * @param objects Array of items to be written to file.
   * @param filename filename and location of the file where list of items will be written.
   * @param append boolean indicating if the file is overwritten or if the items are written to the
   *        end of the file.
   * @param characterEncoding the Charset to be used when encoding
   * @throws IOException if an I/O error occurs writing to or creating the file
   */
  public static void saveListToTextFile(Object[] objects, String filename, boolean append,
      Charset characterEncoding) throws IOException {

    Path path = Paths.get(filename);
    OpenOption option;
    if (append) {
      option = StandardOpenOption.APPEND;
    } else {
      option = StandardOpenOption.CREATE;
    }

    // create list of strings
    List<String> toWrite = new ArrayList<String>();
    for (Object obj : objects) {
      if (obj != null) {
        toWrite.add(obj.toString());
      }
    }
    // write list to file
    Files.write(path, toWrite, characterEncoding, StandardOpenOption.WRITE, option);
  }

  /**
   * Sorts a list of objects in ascending natural order using selection sort.
   * <p>
   * Precondition: Assumes that the list is not null and that the list's capacity is equal to the
   * list's size.
   *
   * @param list A list of objects. Assumes that the list's capacity is equal to the list's size.
   * @throws IllegalArgumentException if the parameter is not full to capacity.
   * @throws NullPointerException if the list is null.
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public static void sort(Comparable[] list) throws IllegalArgumentException, NullPointerException {
    if (list.equals(null))
      throw new NullPointerException();
    else if (list.equals(""))
      throw new IllegalArgumentException();
    int low;
    Comparable other;
    for (int i = 0; i < list.length; i++) {
      low = i;
      for (int j = i + 1; j < list.length; j++) {
        if (list[low].compareTo(list[j]) > 0) {
          low = j;
        }
      }
      other = list[low];
      list[low] = list[i];
      list[i] = other;
    }
  }

  /**
   * Efficiently merges two sorted lists of objects in ascending natural order. If the duplicate
   * objects are in both lists, the object from list1 is merged into the resulting list, and both
   * objects are written to the duplicate file.
   * <p>
   * Precondition: Assumes that the lists are not null and that both lists contain objects that can
   * be compared to each other and are filled to capacity.
   *
   * @param list1 A naturally sorted list of objects. Assumes that the list contains no duplicates
   *        and that its capacity is equal to its size.
   * @param list2 A naturally sorted list of objects. Assumes that the list contains no duplicates
   *        and that its capacity is equal to its size.
   * @param duplicateFileName The name of the file in datafilesduplicates to which duplicate pairs
   *        will be appended.
   * @throws IllegalArgumentException if either parameter is not full to capacity.
   * @throws NullPointerException if the either list is null.ye
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public static Comparable[] merge(Comparable[] list1, Comparable[] list2, String duplicateFileName)
      throws IOException {
    int cList3 = 0;
    Comparable[] list3 = new Comparable[list1.length + list2.length];
    for (int i = 0, j = 0; (i < list1.length) && (j < list2.length);) {
      int comparison = (list1[i].compareTo(list2[j]));
      if (comparison > 0) {
        list3[cList3] = list2[j++];
        cList3++;
      }
      if (comparison == 0) {
        fileWriting(list1, list2, i, j, duplicateFileName);
        list3[cList3] = list1[i++];
        j++;
        cList3++;
      }
      if (comparison < 0) {
        list3[cList3] = list1[i++];
        cList3++;
      }
      if (j == list1.length && i != list2.length) {

        for (int m = cList3; m < list3.length; m++) {
          if (i < list1.length) {
            list3[m] = list1[i++];
            cList3++;
          }
        }
      }
      if (i == list2.length && j != list1.length) {

        for (int m = cList3; m < list3.length; m++) {

          if (j < list2.length) {
            list3[m] = list2[j++];
            cList3++;
          }
        }
      }
    }
    Comparable[] mergedList =
        (Comparable[]) Array.newInstance(list1.getClass().getComponentType(), cList3);
    for (int i = 0; i < cList3; i++) {
      mergedList[i] = list3[i];

    }
    return mergedList;

  }

  /**
   * Takes two lists that have a common field and writes that field to a file
   *
   * @param list1 List one contains the first list of objects
   * @param list2 List two contains the secoud list of objects
   * @param i int i contains position of list1
   * @param j int j contains position of list2
   *
   * @throws IOException if an I/O error occurs writing to or creating the file
   */
  private static void fileWriting(Comparable[] list1, Comparable[] list2, int i, int j,
      String duplicateFileName) throws IOException {
    String[] duplicates = {list1[i].toString() + " (merged)", list2[j].toString()};
    if (!(new File("../ElectionSys6/datafiles/duplicates")).exists()) {
      try {
        Files.createDirectory(Paths.get("../ElectionSys6/datafiles/duplicates/"));
      } catch (IOException ioe) {
        System.out.println(ioe);
      }
    }
    saveListToTextFile(duplicates, "../ElectionSys6/datafiles/duplicates/" + duplicateFileName);
  }

  /**
   * Sorts a list of objects in the given order.
   * <p>
   * Precondition: Assumes that the list is not null and that the list's capacity is equal to the
   * list's size.
   *
   * @param list A list of objects. Assumes that the list's capacity is equal to the list's size.
   * @param sortOrder A Comparator object that defines the sort order
   * @throws IllegalArgumentException if the parameter is \* not full to capacity.
   * @throws NullPointerException if the list or sortOrder \* are null.
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public static void sort(Comparable[] list, Comparator sortOrder)
      throws IllegalArgumentException, NullPointerException {
    if (list == null || sortOrder == null)
      throw new NullPointerException();
    if (list.equals("") || sortOrder.equals(""))
      throw new IllegalArgumentException();
    Arrays.sort(list, sortOrder);
  }

  /**
   * Calls another method that does the searching
   * <p>
   * Precondition: Assumes that the list is not null, list is ordered
   *
   * @param database A list of comparable<T>, T being a generic
   * 
   * @param key A generic object that implements Comparable
   * 
   * @return int A positive int if found. A negative int of where it would be found if not.
   */
  public static <T extends Comparable<? super T>> int binarySearch(List<T> database, T key) {

    return binarySearch(database, key, 0, database.size());

  }

  /**
   * Recursively searches through a list
   * <p>
   * Precondition: Assumes that the list is not null, list is ordered
   *
   * @param database A list of comparable<T>, T being a generic
   * 
   * @param key A generic object that implements Comparable
   * 
   * @param start The beginning of the list
   * 
   * @param end The end of the list
   * 
   * @return A positive int if found. A negative int of where it would be found if not.
   */
  public static <T extends Comparable<? super T>> int binarySearch(List<T> database, T key,
      int start, int end) {

    if (start < end) {

      int middle = start + (end - start) / 2;

      if (database.get(middle).compareTo(key) > 0) {
        return binarySearch(database, key, start, middle);

      } else if (database.get(middle).compareTo(key) < 0) {

        return binarySearch(database, key, middle + 1, end);

      } else if ((database.get(middle).compareTo(key) == 0)) {

        return middle;

      }
    }

    return -(start + 1);
  }

  /**
   * Searches iteratively through an array of comparables to find a key
   * <p>
   * Precondition: Assumes that the list is not null, list is ordered
   *
   * @param database An array of type comparable
   * 
   * @param key A Comparable object we want to find
   * 
   * @return int A positive int if found. A negative int of where it would be found if not.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static int binarySearch(Comparable[] database, Comparable key) {

    int start = 0;
    int end = database.length;

    while (start <= end) {

      int middle = (start + end - start / 2);

      if (key.compareTo(database[middle]) < 0) {
        end = middle - 1;
      } else if (key.compareTo(database[middle]) > 0) {
        start = middle + 1;
      } else {
        return middle;
      }
    }

    return -(start + 1);
  }
}
