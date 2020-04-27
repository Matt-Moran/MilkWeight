package application;

import java.time.LocalDate;
import java.util.Hashtable;

/**
 * 
 * @author ATEAM050
 */
public class Farm implements FarmADT {

  // HashTable storing the date/weight pairs
  private Hashtable<LocalDate, Integer> farmTable;

  // The id associated with the farm
  private String id;

  public Farm(String id) {
    // Initialize the farm with a string ID and a LocalDate (date) and Integer (weight) hash map
    farmTable = new Hashtable<LocalDate, Integer>();
    this.id = id;
  }

  /**
   * Receives the data structures unique ID
   * 
   * @return the farm's unique string ID
   */
  public String getID() {
    return id;
  }

  /**
   * Inserts a date and weight pair into the data structure. If there is already a
   * weight for the date passed, replace it (don't throw any exceptions)
   * 
   * @param date   for the weight
   * @param weight for the date
   */
  public void insert(LocalDate date, int weight) {
    if (contains(date))
      farmTable.replace(date, weight);
    else
      farmTable.put(date, weight);
  }

  /**
   * Removed a specific date and weight pair from the data structure
   * 
   * @param Date date remove the date/weight pair from the data structure
   * @return true if the pair was removed, false if it didn't exist (not removed)
   */
  public boolean remove(LocalDate date) {
    if (contains(date)) {
      farmTable.remove(date);
      return true;
    }
    return false;
  }

  /**
   * Returns the size of the data structure
   * 
   * @return the quantity of date/weight pairs in the data structure
   */
  public int size() {
    return farmTable.size();
  }

  /**
   * Returns true or false depending if the date exists
   * 
   * @param Date date being checked
   * @return if the date exists in the data structure
   */
  public boolean contains(LocalDate date) {
    if (date == null)
      return false;
    return farmTable.containsKey(date);
  }

  /**
   * Returns the weight for a specific date
   * 
   * @param date the date
   * @return the weight for a specific date, if the date doesn't exist, return 0
   * @throws InvalidDateException if the date is null
   */
  public int get(LocalDate date) throws InvalidDateException {
    if (date == null)
      throw new InvalidDateException("Date is null");
    // If there is a date, get its integer value and return it, if not, return 0
    if (contains(date))
      return farmTable.get(date);
    return 0;
  }

  /**
   * Returns the total weight for a range of dates (both start and end are
   * inclusive)
   * 
   * @param startDate the start of the date range
   * @param endDate   the end of the date range
   * @return the total weight for the date range, skipping dates that don't exist
   * @throws InvalidDateException if the start and/or end date is null or the
   *                              order is incorrect
   */
  public int get(LocalDate startDate, LocalDate endDate) throws InvalidDateException {
    // Check if both dates are valid dates
    if (startDate == null)
      throw new InvalidDateException("Start date is null");
    else if (endDate == null)
      throw new InvalidDateException("End date is null");
    else if (startDate.isAfter(endDate))
      throw new InvalidDateException("The start date is after the end date");
    // Total the weights for each date in the range
    int totalWeight = 0;
    for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1))
      totalWeight += get(date);
    return totalWeight;
  }

  /**
   * Find the total weight for a specific month and year
   * 
   * @param month the month
   * @param year  the year
   * @return the total weight for the month and year pair
   * @throws InvalidDateException if the month is outside the range of 1-12 or if
   *                              the year is negative
   */
  public int get(int month, int year) throws InvalidDateException {
    // Check that the year is above 0 and the month is between 1-12
    if (month < 1 || month > 12)
      throw new InvalidDateException("Month is outside the range of 1 - 12");
    else if (year < 0)
      throw new InvalidDateException("Year is negative value");
    // Return a date range, with the range of the month/year
    LocalDate s = LocalDate.of(year, month, 1);
    LocalDate e = s.withDayOfMonth(s.lengthOfMonth());
    return get(s, e);
  }

  /**
   * Find the total weight for a specific year
   * 
   * @param year the year
   * @return the total weight for a year
   * @throws InvalidDateException if the year is null or negative
   */
  public int get(int year) throws InvalidDateException {
    // Check that the year is above 0
    if (year < 0)
      throw new InvalidDateException("Year is negative");
    // Return a date range, with the range of the year
    return get(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31));
  }
}
