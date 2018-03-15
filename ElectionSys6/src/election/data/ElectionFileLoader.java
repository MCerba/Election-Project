package election.data;

import election.business.DawsonElectionFactory;
import election.business.interfaces.Election;
import election.business.interfaces.Voter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElectionFileLoader {
  private ElectionFileLoader() {};

  /*
   * @author Austin Antoine
   *
   * @param String
   *
   * @return Voter[]
   *
   * @throws IOException
   *
   * This method takes a path to a file as a parameter and then returns an array filled to capacity
   * with voters' information from the file
   */
  public static Voter[] getVoterListFromSequentialFile(String filename) throws IOException {
    try {
      Path p = Paths.get(filename);
      List<String> allLines = Files.readAllLines(p);
      ArrayList<Voter> list = new ArrayList<Voter>();
      Voter placeHolder;

      for (int i = 0; i < allLines.size(); i++) {
        String[] currentLine = allLines.get(i).split("\\*");

        if (currentLine.length != 4) {
          System.err.println(
                  "This line does not have 4 elements and therefore a valid DawsonElection cannot be created.");
          continue;
        }

        try {
          placeHolder = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance(currentLine[1],
                  currentLine[2], currentLine[0], currentLine[3]);
          list.add(placeHolder);
        }

        catch (IllegalArgumentException e) {
          System.err.println("There was some invalid data entered. " + allLines.get(i) + " "
                  + e.getMessage() + " " + e.getStackTrace());
          continue;
        }
      }

      Voter[] voterArray = new Voter[list.size()];
      voterArray = list.toArray(voterArray);

      return voterArray;
    }

    catch (IOException e) {
      System.out.println("An IOException was caught: " + e.getMessage() + " " + e.getStackTrace());
      return null;
    }

  }

  /*
   * @author Austin Antoine
   *
   * @param String
   *
   * @return Election[]
   *
   * @throws IOException
   *
   * This method takes a path to a file as a parameter and then returns an array filled to capacity
   * with information on elections from the file
   */
  public static Election[] getElectionListFromSequentialFile(String filename) throws IOException {
    try {
      Path p = Paths.get(filename);
      List<String> allLines = Files.readAllLines(p);
      ArrayList<Election> allElections = new ArrayList<Election>();
      Election placeHolder = null;

      for (int i = 0; i < allLines.size(); i++) {
        String[] currentLine = allLines.get(i).split("\\*");
        
        if (currentLine.length != 11) {
          System.err.println(
              "This line does not have 10 elements and therefore a valid DawsonElection cannot be created. "+Arrays.toString(currentLine));
          break;
        }
        
        String numChoices = currentLine[10];
        
        
        if ((allLines.size() - 1) - (allLines.indexOf(allLines.get(i + Integer.parseInt(numChoices)))) > 0) {
          if (allLines.get(i + Integer.parseInt(numChoices) + 1).split("\\*").length != 11) {
            System.err.println("Number of choices is incorrect. "+currentLine[10]);
            break;
          }
        }
        
        String[] choiceArray = makeChoiceArray(allLines, i); // This method makes an array of
        // all the choices
        try {
          placeHolder = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance(currentLine[0],
                  currentLine[9], Integer.parseInt(currentLine[1]), Integer.parseInt(currentLine[2]),
                  Integer.parseInt(currentLine[3]), Integer.parseInt(currentLine[4]),
                  Integer.parseInt(currentLine[5]), Integer.parseInt(currentLine[6]), currentLine[7],
                  currentLine[8], choiceArray);
        }

        catch (NumberFormatException e) {
          System.err.println("One of the numbers could not be parsed. " + e.getMessage() + " "
                  + e.getStackTrace());
          break;
        }
        allElections.add(placeHolder);
        i += Integer.parseInt(numChoices);

      }

      Election[] finishedArray = new Election[allElections.size()];
      finishedArray = allElections.toArray(finishedArray);

      return finishedArray;
    }

    catch (IOException e) {
      return new Election[0];
    }
  }

  /*
   * @author Austin Antoine
   * 
   * @param String, Election[]
   * 
   * @return N/A
   * 
   * @throws IOException
   * 
   * This method takes a path to a file and an array of elections as parameters. It then finds the
   * name of the election within the file and looks for an election with the same name in the array
   * of elections. Finally, it then builds a 2D array of results using the info in the file, then
   * calls setExistingTally from DawsonElectionFactory with the results and the election
   */
  public static void setExistingTallyFromSequentialFile(String filename, Election[] elections)
          throws IOException {
    try {
      Path p = Paths.get(filename);
      List<String> allLines = Files.readAllLines(p);
      ArrayList<String> namesInElections = namesInFileAndArray(allLines, elections);

      int numCols = 1;
      int numChoices = 1;
      int[][] result = null;
      Election currentElection = null;

      for (int i = 0; i < namesInElections.size(); i++) { // go through every election that is in
        // the file and array
        for (int j = 0; j < allLines.size(); j++) { // go through every line that is in the file
          if (allLines.get(j).substring(0, allLines.get(j).indexOf('*'))
                  .equals(namesInElections.get(i))) {
            // System.out.println(namesInElections.get(i));
            int startIndex = j;

            numChoices =
                    Integer.parseInt(allLines.get(j).substring(allLines.get(j).indexOf('*') + 1));

            numCols = 1 + incrementCols(allLines, startIndex); // Assume each column has at least
            // one element so numCols starts at 1
            result = resultingTally(allLines, j, numChoices, numCols); // initializes values in
            // result

            currentElection = determineCurrentElection(allLines, elections, j); // initializes
            // current election

            if (currentElection == null) {
              System.err.println("There is a null election.");
            }
          }
        }

        //System.out.println(Arrays.deepToString(result).replace("], ", "]\n").replace("[[", "[")
        //      .replace("]]", "]"));
        DawsonElectionFactory.DAWSON_ELECTION.setExistingTally(result, currentElection);
      }
    }

    catch (IOException e) {
      System.err.println("An IOException was caught. " + e.getMessage() + " " + e.getStackTrace());
    }
  }

  // ---------- Helper methods bellow this point ----------//

  /*
   * @author Austin Antoine
   * 
   * @param String, Election[]
   * 
   * @return ArrayList<String>
   * 
   * @throws IOException
   * 
   * This is a helper method that takes a file and an array of election objects as input and
   * determines what election names are within the file and within the array. An ArrayList<String>
   * with all the names in both is returned
   */
  private static ArrayList<String> namesInFileAndArray(List<String> allLines, Election[] elections)
          throws IOException {
    ArrayList<String> namesInElections = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();

    // All names in file
    for (int i = 0; i < allLines.size(); i++) {
      if (allLines.get(i).substring(0, allLines.get(i).indexOf('*')) instanceof String) {
        names.add(allLines.get(i).substring(0, allLines.get(i).indexOf('*')));
      }
    }

    // All names in file that are in elections
    for (int i = 0; i < names.size(); i++) {
      for (int j = 0; j < elections.length; j++) {
        if (names.get(i).equals(elections[j].getName())) {
          namesInElections.add(names.get(i));
        }
      }
    }

    return namesInElections;

  }

  /*
   * @author Austin Antoine
   * 
   * @param int[][], List<String>, int
   * 
   * This is a helper method that initializes the values of the 2D result array
   * 
   */
  private static int[][] resultingTally(List<String> allLines, int currentLine, int rows,
                                        int cols) {

    int[][] result = new int[rows][cols];
    int count = currentLine + 1;

    for (int k = 0; k < result.length; k++) {

      String[] line = allLines.get(count).split("\\*");

      for (int m = 0; m < result[1].length; m++) {
        result[k][m] = Integer.parseInt(line[m]);
      }
      count++;
    }

    return result;
  }

  /*
   * @author Austin Antoine
   * 
   * @param List<String>, int
   * 
   * @return int
   * 
   * This is a helper method that is user to determine how many ranks are within a tally file
   */
  private static int incrementCols(List<String> allLines, int startIndex) {
    int count = 0;
    for (int k = 0; k < allLines.get(startIndex + 1).length(); k++) {
      if (allLines.get(startIndex + 1).charAt(k) == '*') {
        count++;
      }
    }
    return count;
  }

  /*
   * @author Austin Antoine
   * 
   * @param List<String>, Election[], int
   * 
   * @return Election
   * 
   * This is a helper method that determines when the current election is at the currentLine
   */
  private static Election determineCurrentElection(List<String> allLines, Election[] elections,
                                                   int currentLine) {
    Election currentElection = null;
    for (int k = 0; k < elections.length; k++) {
      if (elections[k].getName()
              .equals(allLines.get(currentLine).substring(0, allLines.get(currentLine).indexOf('*')))) {
        currentElection = elections[k];
      }
    }

    return currentElection;
  }

  /*
   * @author Austin Antoine
   * 
   * @param String[], List<String>, int
   * 
   * @return String[]
   * 
   * This is a helper method that takes a list of all lines in a file and based on the current line,
   * creates an array of all the choices within that line and returns it
   */
  private static String[] makeChoiceArray(List<String> allLines, int currentLine) {
    String[] line = allLines.get(currentLine).split("\\*");
    String numChoices = line[10];
    String[] choiceArray = new String[Integer.parseInt(numChoices)];

    for (int j = currentLine + 1, count = 0; count < Integer.parseInt(numChoices); j++, count++) {
      String choices = allLines.get(j);
      choiceArray[count] = choices;

    }

    return choiceArray;
  }
}
