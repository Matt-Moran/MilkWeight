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

public class Import {

  // USE DATA FROM THE Main.java ArrayList<Farm> farms (WRITE ONLY)

  /**
   * Parses a file and adds its contents to the farms array list
   * 
   * @param file the file selected by the user in the GUI
   * @throws MissingDataException if a CSV value is null or missing
   * @throws DataFormatException  if a CSV value is not the correct format for the
   *                              column it is in
   * @throws IOException          if the give file cannot be read
   */
  public static void Parse(File file) throws MissingDataException, DataFormatException, IOException {
    ArrayList<int[]> dates = new ArrayList<int[]>();
    ArrayList<String> ids = new ArrayList<String>();
    ArrayList<Integer> weights = new ArrayList<Integer>();

    List<String> lines = getFile(file);
    for (String line : lines) {
      String[] arr = line.split(",");
      int[] date = checkDate(arr[0]);
      String id = checkID(arr[1]);
      int weight = checkWeight(arr[2]);
      dates.add(date);
      ids.add(id);
      weights.add(weight);
    }

    // at this point, all data in csv file is valid
    // add each line of the file to the data
    for (int i = 0; i < ids.size(); i++) {
      addFarm(dates.get(i), ids.get(i), weights.get(i));
    }
  }

  private static List<String> getFile(File file) throws IOException {
    List<String> lineList = new ArrayList<String>();
    try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
      // checks for valid date
      lineList = lines.skip(1).collect(Collectors.toList());
    } catch (IOException e) {
      throw new IOException();
    }
    return lineList;
  }

  /**
   * Checks if the date given is valid.
   * 
   * @param date - date that you are checking
   * @throws MissingDataException - if a CSV value is null or missing
   * @throws DataFormatException  - if a CSV value is not the correct format for
   *                              the column it is in
   */
  private static int[] checkDate(String date) throws MissingDataException, DataFormatException {
    if (date == null || date == "" || date == "-") {
      throw new MissingDataException("missing date in csv file");
    }
    String[] dateArr;
    try {
      dateArr = date.split("-");
    } catch (Exception e) {
      throw new DataFormatException("date in csv file does not contain \"-\"");
    }
    if (dateArr.length != 3) {
      throw new DataFormatException("date in csv file doesn't contain correct format");
    }
    int month;
    int year;
    int day;
    try {
      month = Integer.parseInt(dateArr[1]);
      year = Integer.parseInt(dateArr[0]);
      day = Integer.parseInt(dateArr[2]);
    } catch (Exception e) {
      throw new DataFormatException("date in csv file cannot be converted to int");
    }
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
    dateArray[2] = day;
    return dateArray;
  }

  /**
   * Checks if the id is valid.
   * 
   * @param id - id checking
   * @throws MissingDataException - if a CSV value is null or missing
   * @throws DataFormatException  - if a CSV value is not the correct format for
   *                              the column it is in
   */
  private static String checkID(String id) throws MissingDataException, DataFormatException {
    if (id == null || id == "" || id == "-") {
      throw new MissingDataException("Missing id in csv file");
    }
    String[] farms = new String[2];
    try {
      farms = id.split(" ", 2);

    } catch (Exception e) {
      throw new DataFormatException("ID isn't in correct format in csv file");
    }
    if (!farms[0].contains("Farm")) {
      throw new DataFormatException("ID isn't in correct format in csv file");
    }
    if (farms[1] == null)
      throw new DataFormatException("farm id is null");
    return farms[1];
  }

  /**
   * Checks if weight is valid
   * 
   * @param w - is the weight
   * @throws MissingDataException
   * @throws DataFormatException
   */
  private static int checkWeight(String w) throws MissingDataException, DataFormatException {
    if (w == null || w == "" || w == "-") {
      throw new MissingDataException("Missing weight in csv file");
    }
    Integer weight;
    try {
      weight = Integer.parseInt(w);
    } catch (Exception e) {
      throw new DataFormatException("Weight can't be converted to int");
    }
    return weight;
  }

  /**
   * Adds each farm to the main program
   * 
   * @param line
   */
  private static void addFarm(int[] intDate, String id, Integer weight) {
    LocalDate date = LocalDate.of(intDate[0], intDate[1], intDate[2]);
    // Create a new farm variable
    Farm farm = null;
    // Look through the Main.farms array list, see if any farm object has the same
    // ID as the ID passed for the add function
    for (Farm f : Main.farms)
      // If the ID for the farm matches, set the farm variable to the farm in the list
      // (variable f)
      if (f.getID().equals(id))
        farm = f;
    // After the for loop, if the farm variable is still null (meaning no matches)
    // create a new entry in the Main.farms array list
    if (farm == null) {
      farm = new Farm(id);
      Main.farms.add(farm);
    }
    // Insert the value into the correct farm in the Main.farms array list
    farm.insert(date, weight);
  }
}
