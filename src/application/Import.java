package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Import method parses the file and adds its contents to the farms array list.
 * 
 * @author ATEAM 050
 *
 */
public class Import {

  private ArrayList<Farm> farms;

  public Import(ArrayList<Farm> farms) {
    this.farms = farms;
  }

  /**
   * Parses a file and adds its contents to the farms array list
   * 
   * @param file the file selected by the user in the GUI
   * @throws MissingDataException if a CSV value is null or missing
   * @throws DataFormatException  if a CSV value is not the correct format for the
   *                              column it is in
   * @throws IOException          if the give file cannot be read
   */
  public void Parse(File file) throws MissingDataException, DataFormatException, IOException {
    // Initialize arraylist to hold dates, ids, and weights
    ArrayList<int[]> dates = new ArrayList<int[]>(); // int[0] = year, int[1] = month, int[2] = day
    ArrayList<String> ids = new ArrayList<String>();
    ArrayList<Integer> weights = new ArrayList<Integer>();

    List<String> lines = getFile(file); // parses file into a list

    for (String line : lines) {
      String[] arr = line.split(","); // splits the date
      if (arr.length != 3) {
        throw new MissingDataException("Missing data");
      }
      String id = checkID(arr[1]);
      int weight = checkWeight(arr[2]);
      int[] date = checkDate(arr[0]);
      // checks if date, id, and weight are valid before adding them to arraylist
      dates.add(date);
      ids.add(id);
      weights.add(weight);
    }

    // at this point, all data in csv file is valid
    // add each line of the file to the main program
    for (int i = 0; i < ids.size(); i++) {
      addFarm(dates.get(i), ids.get(i), weights.get(i));
    }
  }

  /**
   * Parses file's contents into a list using JavaStream.
   * 
   * @param file - file that you want to parse
   * @return list of file's contents
   * @throws IOException - if the given file cannot be read.
   */
  private List<String> getFile(File file) throws IOException {
    List<String> lineList = new ArrayList<String>();
    try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
      // checks for valid date
      lineList = lines.skip(1) // skips first line of the file which contains date, farmid, etc.
          .filter(x -> x != null || x != "") // ignores null or empty string lines
          .collect(Collectors.toList()); // changes stream into list
    } catch (IOException e) {
      throw new IOException(); // throws error if file can't be read
    }
    return lineList; // returns list
  }

  /**
   * Checks if the date given is valid. Returns date
   * 
   * @param date - date that you are checking
   * @return array of date given in year/month/day format
   * @throws MissingDataException - if a CSV value is null or missing
   * @throws DataFormatException  - if a CSV value is not the correct format for
   *                              the column it is in
   */
  private static int[] checkDate(String date) throws MissingDataException, DataFormatException {
    // checks if date is missing
    if (date == null || date == "" || date.equals("-")) {
      throw new MissingDataException("missing date in csv file");
    }
    String[] dateArr; // creates date array
    try {
      dateArr = date.split("-"); // splits into array by "-"
    } catch (Exception e) { // format is incorrect if can't split
      throw new DataFormatException("date in csv file does not contain \"-\"");
    }
    if (dateArr.length != 3) { // checks if split into year/month/day
      throw new DataFormatException("date in csv file doesn't contain correct format");
    }
    int month;
    int year;
    int day;
    try { // try converting each string to a number
      month = Integer.parseInt(dateArr[1]);
      year = Integer.parseInt(dateArr[0]);
      day = Integer.parseInt(dateArr[2]);
    } catch (Exception e) { // if date can't be converted to a string - throw data format exception
      throw new DataFormatException("date in csv file cannot be converted to int");
    }
    // check if valid date
    if (month < 1 || month > 12) {
      throw new DataFormatException("date in csv file contains invalid month");
    } else if (day < 1 || day > 31) {
      throw new DataFormatException("date in csv file contains invalid date");
    } else if (year < 0 || year > 2020) {
      throw new DataFormatException("year in csv file contains invalid year");
    }
    int[] dateArray = new int[3];
    dateArray[0] = year;
    dateArray[1] = month;
    dateArray[2] = day; // return date array with correct year/month/day format
    return dateArray;
  }

  /**
   * Checks if the id is valid. Returns the correct id.
   * 
   * @param id - id that you are checking for.
   * @throws MissingDataException - if a CSV value is null or missing
   * @throws DataFormatException  - if a CSV value is not the correct format for
   *                              the column it is in
   */
  private String checkID(String id) throws MissingDataException, DataFormatException {
    // checks if there is a missing id
    if (id == null || id == "" || id.contentEquals("-")) {
      throw new MissingDataException("Missing id in csv file");
    }
    String[] farms = new String[2];
    try {
      farms = id.split(" ", 2); // split array at space into 2 strings
    } catch (Exception e) {
      throw new DataFormatException("ID isn't in correct format in csv file");
    }
    if (!farms[0].contains("Farm")) { // checks if it contains "farm" format
      throw new DataFormatException("ID isn't in correct format in csv file");
    }
    if (farms[1] == null || farms[1].equals(""))// farm id can't be null, has to have some name
      throw new DataFormatException("farm id is null");
    return farms[1];
  }

  /**
   * Checks if weight is valid. Returns correct weight in number format.
   * 
   * @param w - is the weight
   * @throws MissingDataException
   * @throws DataFormatException
   */
  private int checkWeight(String w) throws MissingDataException, DataFormatException {
    // checks if data is missing
    if (w == null || w == "" || w.contentEquals("-")) {
      throw new MissingDataException("Missing weight in csv file");
    }
    Integer weight;
    try { // converts string to number
      weight = Integer.parseInt(w);
    } catch (Exception e) { // if error converting, there is a problem with the format
      throw new DataFormatException("Weight can't be converted to int");
    }
    return weight;
  }

  /**
   * Adds each farm to the main program
   * 
   * @param intDate - date of farm given by year/month/day
   * @param id      - farm id
   * @param weight  - weight of the farm
   */
  private void addFarm(int[] intDate, String id, Integer weight) {
    LocalDate date = LocalDate.of(intDate[0], intDate[1], intDate[2]);
    // Create a new farm variable
    Farm farm = null;
    // Look through the Main.farms array list, see if any farm object has the same
    // ID as the ID passed for the add function
    for (Farm f : farms)
      // If the ID for the farm matches, set the farm variable to the farm in the list
      // (variable f)
      if (f.getID().equals(id))
        farm = f;
    // After the for loop, if the farm variable is still null (meaning no matches)
    // create a new entry in the Main.farms array list
    if (farm == null) {
      farm = new Farm(id);
      farms.add(farm);
    }
    // Insert the value into the correct farm in the Main.farms array list
    farm.insert(date, weight);
  }
}
