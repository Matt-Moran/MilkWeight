package application;

import java.util.Date;
import java.util.Hashtable;

public class Farm implements FarmADT {
	
	//HashTable storing the date/weight pairs
	private Hashtable<Date, Integer> farmTable;
	
	//The id associated with the farm
	private String id;
	
  public Farm(String id) {
	  farmTable = new Hashtable<Date, Integer>();
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
  public void insert(Date date, int weight) {
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
  public boolean remove(Date date) {
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
  public boolean contains(Date date) {
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
  public int get(Date date) throws InvalidDateException {
	if (date == null)
		throw new InvalidDateException("Date is null");
    if (contains(date))
    	return farmTable.get(date);
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
  @SuppressWarnings("deprecation")
  public int get(Date startDate, Date endDate) throws InvalidDateException {
	if (startDate == null)
		throw new InvalidDateException("Start date is null");
	if (endDate == null)
		throw new InvalidDateException("End date is null");
    //If the start date is after the end date, switch the start and end dates to compute
    if (startDate.after(endDate)) {
    	Date temp = startDate;
    	startDate = endDate;
    	endDate = temp;
    }
    int totalWeight = 0;
    Date date = new Date(startDate.getYear(), startDate.getMonth(), startDate.getDay());
    while(date != nextDay(endDate)) {
      totalWeight += farmTable.get(date);
      date = nextDay(date);
    }
    return totalWeight;	
  }
  
  

  /**
   * Find the total weight for a specific month and year
   * 
   * @param month the month
   * @param year  the year
   * @return the total weight for the month and year pair
   * @throws InvalidDateException if the month is outside the range of 1-12 or if the year is negative
   */
  @SuppressWarnings("deprecation")
  public int get(int month, int year) throws InvalidDateException {
    //In date class it says the month is between 1-12
    if (month < 1 || month > 12)
    	throw new InvalidDateException("Month is outside the range of 1 - 12");
    else if (year < 0)
    	throw new InvalidDateException("Year is negative value");
    int totalWeight = 0;
    for (int i = 1; i <= 31; i++)
    	totalWeight += farmTable.get(new Date(year, month, i));
    return totalWeight;
  }

  /**
   * Find the total weight for a specific year
   * 
   * @param year the year
   * @return the total weight for a year
   * @throws InvalidDateException if the year is null or negative
   */
  @SuppressWarnings("deprecation")
  public int get(int year) throws InvalidDateException {
	if (year < 0)
		throw new InvalidDateException("Year is negative");
    int totalWeight = 0;
    for (int i = 1; i <= 12; i++)
    	for (int j = 1; j <= 31; i++)
    		totalWeight += farmTable.get(new Date(year, i, j));
    return totalWeight;
  }
  
  @SuppressWarnings("deprecation")
  private Date nextDay(Date date) {
    Date next = new Date(date.getYear(), date.getMonth(), date.getDay() + 1);
    if(next.getDay() > 31) {
      next.setDate(1);
      next.setMonth(next.getMonth() + 1);
    }
    if (next.getMonth() > 12) {
      next.setMonth(1);
      next.setYear(next.getYear() + 1);
    }
    return next;
  }
}
