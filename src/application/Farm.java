package application;

import java.util.Date;

public class Farm implements FarmADT {
  /**
   * Receives the data structures unique ID
   * 
   * @return the farm's unique string ID
   */
  public String getID() {
    return null;
  }

  /**
   * Inserts a date and weight pair into the data structure. If there is already a
   * weight for the date passed, replace it (don't throw any exceptions)
   * 
   * @param date   for the weight
   * @param weight for the date
   */
  public void insert(Date date, int weight) {
  }

  /**
   * Removed a specific date and weight pair from the data structure
   * 
   * @param Date date remove the date/weight pair from the data structure
   * @return true if the pair was removed, false if it didn't exist (not removed)
   */
  public boolean remove(Date date) {
    return false;
  }

  /**
   * Returns the size of the data structure
   * 
   * @return the quantity of date/weight pairs in the data structure
   */
  public int size() {
    return 0;
  }

  /**
   * Returns true or false depending if the date exists
   * 
   * @param Date date being checked
   * @return if the date exists in the data structure
   */
  public boolean contains(Date date) {
    return false;
  }

  /**
   * Returns the weight for a specific date
   * 
   * @param date the date
   * @return the weight for a specific date, if the date doesn't exist, return 0
   * @throws InvalidDateException if the date is null
   */
  public int get(Date date) throws InvalidDateException {
    return 0;
  }

  /**
   * Returns the total weight for a range of dates
   * 
   * @param startDate the start of the date range
   * @param endDate   the end of the date range
   * @return the total weight for the date range, skipping dates that don't exist
   * @throws InvalidDateException if the start and/or end date is null
   */
  public int get(Date startDate, Date endDate) throws InvalidDateException {
    return 0;
  }

  /**
   * Find the total weight for a specific month and year
   * 
   * @param month the month
   * @param year  the year
   * @return the total weight for the month and year pair
   * @throws InvalidDateException if the month is outside the range of 1-12 or
   *                              null and if the year is null or negative
   */
  public int get(int month, int year) throws InvalidDateException {
    return 0;
  }

  /**
   * Find the total weight for a specific year
   * 
   * @param year the year
   * @return the total weight for a year
   * @throws InvalidDateException if the year is null or negative
   */
  public int get(int year) throws InvalidDateException {
    return 0;
  }
}
