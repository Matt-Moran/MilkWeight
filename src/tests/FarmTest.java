package tests;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Farm;
import application.InvalidDateException;

public class FarmTest {

  Farm farm;

  /**
   * Creates a farm object with the following details:
   * ID: "farm01"
   * Keys (Dates): each date of the calendar year 2016 (leap year, so 366 days and 2/29 exists)
   * Values (Weights): 1/1/2016 == 1, 1/2/2016 == 2... ...12/31/2016 == 366 (each date is +1 )
   * @throws Exception
   */
  @BeforeEach
  public void setUp() throws Exception {
    // Create a farm with ID "farm01"
    farm = new Farm("farm01");
    // Add a series of unique weights with unique dates to the farm
    ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
    for (LocalDate date = LocalDate.of(2016, 1, 1); date.isBefore(LocalDate.of(2017, 1, 1)); date = date.plusDays(1))
      dates.add(date);
    for (int i = 1; i <= dates.size(); i++)
      farm.insert(dates.get(i-1), i);
  }

  /**
   * Tests function: farm.getID()
   */
  @Test
  public void test000_getID() {
    assert (farm.getID().equals("farm01"));
  }

  /**
   * Tests function: farm.size()
   */
  @Test
  public void test001_size() {
    // Check the size and see if it matches
    assert (farm.size() == 366);
  }
  
  /**
   * Tests function: farm.get(int year)
   * @throws InvalidDateException 
   */
  @Test
  public void test002_getYear() throws InvalidDateException {
    assert(farm.get(2016) == 67161);
    assert(farm.get(2017) == 0);
    assert(farm.get(2015) == 0);
  }
  
  /**
   * Tests function: farm.get(int month, int year)
   * @throws InvalidDateException 
   */
  @Test
  public void test003_getMonth() throws InvalidDateException {
    assert(farm.get(2, 2016) == 1334);
    assert(farm.get(1, 2016) == 496);
    assert(farm.get(2, 2017) == 0);
    assert(farm.get(2, 2015) == 0);
  }
}
