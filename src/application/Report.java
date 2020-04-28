package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.util.Pair;

public class Report {

  // USE DATA FROM THE Main.java ArrayList<Farm> farms (READ ONLY)

  /*
   * Helps store the import information for the report. (Key: (value) == (String
   * key, String value))
   */
  private ArrayList<Pair<String, String>> report;
  
  // Stores the name of the report generated
  private String type;

  /*
   * An array list of each pair for the pie graph. The pair contains a string and
   * integer: String: the label for the value Integer: the value for the label
   */
  private ArrayList<Pair<String, Integer>> pieGraph;

  // FARM REPORT
  /**
   * Assigns values to the "report" and "pieGraph" variables for the
   * specifications of a FARM REPORT
   * 
   * EXAMPLE REPORT VARIABLE: Farm ID: (farm id) Year: (year)
   * 
   * Year Total: (year total) Year Percentage: (year total / all farm year total *
   * 100)
   * 
   * Month 1 Total: (month 1 total) Month 1 Percentage: (month 1 total / all farm
   * month 1 total * 100) Month 2 Total: "" "" Month 2 Percentage: "" "" ... Month
   * 12 Total: "" "" Month 12 Percentage: "" ""
   * 
   * EXAMPLE PIE GRAPH VARIABLE: pieGraph { ("Month 1", month 1 total) ("Month 2",
   * month 2 total) ... ("Month 12", month 12 total) }
   * 
   * @param farmId the farm ID
   * @param year   the year for the farm
   * @throws InvalidReportException if the farm ID is null, if the farm ID doesn't
   *                                exist or if the year is null
   * @throws InvalidDateException
   */
  public Report(String farmId, int year) throws InvalidReportException, InvalidDateException {
    type = "Farm";
    report = new ArrayList<Pair<String, String>>();
    pieGraph = new ArrayList<Pair<String, Integer>>();

    if (farmId == null) {
      throw new InvalidReportException("ERROR: Invalid Farm ID");
    }
    if (!farmExists(farmId)) {
      throw new InvalidReportException("ERROR: Farm ID not found");
    }
    if (year <= 0) {
      throw new InvalidReportException("ERROR: Invalid Year");
    }

    // Adds farmId and Year to report
    report.add(new Pair<>("Farm ID", farmId));
    report.add(new Pair<>("Year", String.valueOf(year)));

    // total milkweight produced by all farms for a given year
    int totalYearWeight = getTotalWeightOfYear(year);
    
    // total milkweight produced by given farm for given year
    int farmYearWeight = findFarm(farmId).get(year);
    // adds the total weight produced by the farm to the report
    report.add(new Pair<>("Year Total", String.valueOf(farmYearWeight)));

    // calculates percentage of milkweight produced by one farm
    // compared to all farms for one year
    // note: Casts year weights into doubles to do percentage calculation
    double totalYearWeight2 = (double) totalYearWeight;
    double farmYearWeight2 = (double) farmYearWeight;
    
    double yearRatio = (farmYearWeight2 / totalYearWeight2) * 100;
    double yearPercentage = Math.round(yearRatio * 100.0) / 100.0;

    // adds percentage of of milkweight produced by one farm
    // compared to all farms for one year to report
    report.add(new Pair<>("Year Percentage", String.valueOf(yearPercentage) + "%"));

    // for each month, calculates the monthly percentage of milk
    // weight produced by a farm compared to the total milk weight
    // produced by all farms
    for (int i = 1; i <= 12; i++) {

      int totalMonthWeight = 0;

      // calculates total milkweight produced by all farms
      // for one month
      for (int j = 0; j < Main.farms.size(); j++) {
        int currMonthWeight = Main.farms.get(j).get(i, year);
        totalMonthWeight = totalMonthWeight + currMonthWeight;
      }

      // gets farm specified by user input
      Farm farm = findFarm(farmId);
      // gets farm weight produced by farm for the month
      int farmMonthWeight = farm.get(i, year);
      
      // to prevent divide by zero, set the total weight to 1 if it is zero
      if (totalMonthWeight == 0)
        totalMonthWeight = 1;

      // calculates percentage of milk weight produced by one farm
      // compared to all farms for a given month
      // Note: casts weights into double to do percentage calculation
      double farmMonthWeight2 = (double) farmMonthWeight;
      double totalMonthWeight2 = (double) totalMonthWeight;
      
      double ratio = (farmMonthWeight2 / totalMonthWeight2) * 100;
      double percentage = Math.round(ratio * 100.0) / 100.0;

      // adds monthly total milk weight produced by farm to report
      report.add(new Pair<>("Month " + String.valueOf(i) + " Total", String.valueOf(farmMonthWeight)));
      // adds monthly percentage of total milkweight produced by one farm to report
      report.add(new Pair<>("Month " + String.valueOf(i) + " Percentage", String.valueOf(percentage) + "%"));

      // adds the month and weight produced by the farm for each
      // month to the pieGraph
      Integer farmMonthWeight3 = findFarm(farmId).get(i, year);
      String month = "Month " + String.valueOf(i);
      Pair<String, Integer> pair = new Pair<String, Integer>(month, farmMonthWeight3);
      pieGraph.add(pair);

    }

  }

  /**
   * Method returns the total milk weight produced by all farms for a given year
   * 
   * @param year
   * @return
   * @throws InvalidDateException
   */
  private int getTotalWeightOfYear(int year) throws InvalidDateException {
    int totalWeight = 0;

    // finds the total milk weight of all farms for a given year
    for (int i = 0; i < Main.farms.size(); i++) {
      int currYearWeight = Main.farms.get(i).get(year);
      totalWeight = totalWeight + currYearWeight;
    }

    return totalWeight;
  }

  /**
   * Helper method to determine if a farm exists based on a given farm Id
   * 
   * @param farmId
   * @return
   */
  private boolean farmExists(String farmId) {
    for (int i = 0; i < Main.farms.size(); i++)
      if (Main.farms.get(i).getID().equals(farmId))
        return true;
    return false;
  }

  /**
   * Helper method that returns a farm based on a specified farm Id
   * 
   * @param farmId of farm
   * @return farm of farmId
   */
  private Farm findFarm(String farmId) {
    for (int i = 0; i < Main.farms.size(); i++)
      if (Main.farms.get(i).getID().equals(farmId))
        return Main.farms.get(i);
    return null;
  }

  // ANNUAL REPORT
  /**
   * Assigns values to the "report" and "pieGraph" variables for the
   * specifications of a ANNUAL REPORT
   * 
   * EXAMPLE REPORT VARIABLE: Year: (year)
   * 
   * Year Total: (all farm year total)
   * 
   * farm1 Total: (farm1 year total) farm1 Percentage: (farm1 year total / all
   * farm year total * 100) farm2 Total: "" "" farm2 Percentage: "" "" ... farm103
   * Total: "" "" farm103 Percentage: "" ""
   * 
   * EXAMPLE PIE GRAPH VARIABLE: pieGraph { ("farm1", farm1 year total) ("farm2",
   * farm2 year total) ... ("farm103", farm103 year total) }
   * 
   * @param year the year for the report
   * @throws InvalidReportException if the year is null
   * @throws InvalidDateException
   */
  public Report(int year) throws InvalidReportException, InvalidDateException {
    type = "Annual";
    report = new ArrayList<Pair<String, String>>();
    pieGraph = new ArrayList<Pair<String, Integer>>();

    if (year <= 0) {
      throw new InvalidReportException("ERROR: That year is invalid");
    }

    // adds year to report
    report.add(new Pair<>("Year", String.valueOf(year)));

    // total milkweight produced by all farms in a given year
    int yearWeight = getTotalWeightOfYear(year);

    // adds total milk weight produced by all farms
    // for a given year to report
    report.add(new Pair<>("Year Total", String.valueOf(yearWeight)));

    // loops through all farms, adding their total milkweight produced
    // of that given year to report and piegraph,
    // and adds percentage of milkweight produced for a farm compared to
    // all farms for a given year to report as well
    for (int i = 0; i < Main.farms.size(); i++) {

      // gets current farm and ID
      Farm currFarm = Main.farms.get(i);
      String ID = currFarm.getID();

      // gets total weight produced by the farm for the
      // given year
      int farmTotal = currFarm.get(year);

      // adds farm's yearly total to report
      report.add(new Pair<>("Farm " + ID + " Total", String.valueOf(farmTotal)));

      // adds the farm and its yearly total to piegraph
      Integer farmTotal2 = farmTotal;
      String farm = "Farm" + ID;
      Pair<String, Integer> pair = new Pair<String, Integer>(farm, farmTotal2);
      pieGraph.add(pair);

      // Calculates percentage of yearly milkweight produced by one farm
      // compared to all farms
      double farmTotal3 = (double) farmTotal;
      double yearWeight3 = (double) yearWeight;
     
      double ratio = (farmTotal3 / yearWeight3) * 100;
      double percentage = Math.round(ratio * 100.0) / 100.0;

      // adds percentage for farm to report
      report.add(new Pair<>("Farm " + ID + " Percentage", String.valueOf(percentage) + "%"));

    }

  }

  /**
   * Returns the milkweight produced by all farms for a given month of some year
   * 
   * @param month
   * @param year
   * @return
   * @throws InvalidDateException
   */
  private int allFarmMonthWeight(int month, int year) throws InvalidDateException {
    int monthWeight = 0;

    for (int i = 0; i < Main.farms.size(); i++) {
      int currWeight = Main.farms.get(i).get(month, year);
      monthWeight = monthWeight + currWeight;
    }

    return monthWeight;

  }

  // MONTHLY REPORT
  /**
   * Assigns values to the "report" and "pieGraph" variables for the
   * specifications of a MONTHLY REPORT
   * 
   * EXAMPLE REPORT VARIABLE: Year: (year) Month: (month)
   * 
   * Month Total: (all farm month total)
   * 
   * farm1 Total: (farm1 month total) farm1 Percentage: (farm1 month total / all
   * farm month total * 100) farm2 Total: "" "" farm2 Percentage: "" "" ...
   * farm103 Total: "" "" farm103 Percentage: "" ""
   * 
   * EXAMPLE PIE GRAPH VARIABLE: pieGraph { ("farm1", farm1 month total) ("farm2",
   * farm2 month total) ... ("farm103", farm103 month total) }
   * 
   * @param year  the year for the report
   * @param month the month for the report
   * @throws InvalidReportException if the year is null
   * @throws InvalidDateException
   */
  public Report(int year, int month) throws InvalidReportException, InvalidDateException {
    type = "Monthly";
    report = new ArrayList<Pair<String, String>>();
    pieGraph = new ArrayList<Pair<String, Integer>>();

    if (year <= 0) {
      throw new InvalidReportException("ERROR: Invalid Year");
    }

    // gets total milkweight produced by all farms for a given month and year
    int totalMonthWeight = allFarmMonthWeight(month, year);

    // adds the year to report
    report.add(new Pair<>("Year", String.valueOf(year)));
    // adds the month to the report
    report.add(new Pair<>("Month", String.valueOf(month)));
    // adds the total milkweight produced by all farms for a given month
    // to report
    report.add(new Pair<>("Month Total", String.valueOf(totalMonthWeight)));

    // loops through each farm, adding their monthly total
    // and percentage of all farms monthly total to report,
    // also adds the monthly total for each farm to piegraph
    for (int i = 0; i < Main.farms.size(); i++) {

      // gets current farm in list of farms
      Farm currFarm = Main.farms.get(i);
      // gets ID of current farm
      String ID = currFarm.getID();
      // gets monthly milkweight total for a farm
      int monthTotal = currFarm.get(month, year);

      // adds farms monthly total to report
      report.add(new Pair<>("Farm" + ID + " Total", String.valueOf(monthTotal)));

      // adds each farms monthly total to piegraph
      Integer monthTotal2 = monthTotal;
      String farm = "Farm" + ID;
      Pair<String, Integer> pair = new Pair<String, Integer>(farm, monthTotal2);
      pieGraph.add(pair);

      // Calculates monthly percentage of each farm compared to all farms
      double monthTotal3 = (double) monthTotal;
      double totalMonthWeight3 = (double) totalMonthWeight;
      double ratio = (monthTotal3 / totalMonthWeight3) * 100;
      double percentage = Math.round(ratio * 100.0) * 100.0;

      // adds monthly percentage to report
      report.add(new Pair<>("Farm" + ID + " Percentage", String.valueOf(percentage) + "%"));

    }

  }

  /**
   * Method returns the total milkweight produced by all farms over a specified
   * date range
   * 
   * @param start
   * @param end
   * @return
   * @throws InvalidDateException
   */
  private int weightOverRange(LocalDate start, LocalDate end) throws InvalidDateException {
    int totalWeight = 0;

    // loops through each farm and calculates the total weight
    // produced by all farms over a range of dates
    for (int i = 0; i < Main.farms.size(); i++) {
      Farm currentFarm = Main.farms.get(i);
      LocalDate currentDate = start;

      // loops through each day in date range (inclusively)
      // while adding to the totalweight
      while (currentDate.isBefore(end.plusDays(1))) {
        int dayWeight = currentFarm.get(currentDate);
        totalWeight = totalWeight + dayWeight;
        currentDate = currentDate.plusDays(1);
      }

    }

    return totalWeight;

  }

  // DATE RANGE REPORT
  /**
   * Assigns values to the "report" and "pieGraph" variables for the
   * specifications of a DATE RANGE REPORT
   * 
   * EXAMPLE REPORT VARIABLE: Start Date: (startDate) endDate: (endDate)
   * 
   * Range Total: (all farm range total)
   * 
   * farm1 Total: (farm1 range total) farm1 Percentage: (farm1 range total / all
   * farm range total * 100) farm2 Total: "" "" farm2 Percentage: "" "" ...
   * farm103 Total: "" "" farm103 Percentage: "" ""
   * 
   * EXAMPLE PIE GRAPH VARIABLE: pieGraph { ("farm1", farm1 range total) ("farm2",
   * farm2 range total) ... ("farm103", farm103 range total) }
   * 
   * @param startDate the start of the date range for the report
   * @param endDate   the end of the date range for the report
   * @throws InvalidReportException if the year is null
   * @throws InvalidDateException
   */
  public Report(LocalDate startDate, LocalDate endDate) throws InvalidReportException, InvalidDateException {
    type = "Date Range";
    report = new ArrayList<Pair<String, String>>();
    pieGraph = new ArrayList<Pair<String, Integer>>();

    // Formats a date into a string in the format "yyyy-mm-dd"
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    String start = dateFormat.format(startDate);
    String end = dateFormat.format(endDate);

    // adds the start and end date to report
    report.add(new Pair<>("Start Date", start));
    report.add(new Pair<>("End Date", end));

    // calculates the total weight produced by all farms over the
    // specified date range
    int totalRangeWeight = weightOverRange(startDate, endDate);
    
    if (totalRangeWeight == 0) {
    	totalRangeWeight = 1;
    }

    // adds the total milkweight produced by all farms over a given
    // date range to report
    report.add(new Pair<>("Range Total", String.valueOf(totalRangeWeight)));

    // loops through each farm and calculates farm milkweight
    // data over the date range for each farm
    for (int i = 0; i < Main.farms.size(); i++) {

      // gets current farm and ID of current farm
      Farm currentFarm = Main.farms.get(i);
      String ID = currentFarm.getID();

      LocalDate currentDate = startDate;
      int farmRangeWeight = 0;

      // loops through each day in date range (inclusively) keeping running total
      // of total milk weight for each farm
      while (currentDate.isBefore(endDate.plusDays(1))) {

        // weight produced on the current date
        int dayWeight = currentFarm.get(currentDate);
        // adds current date weight to total farm weight
        farmRangeWeight = farmRangeWeight + dayWeight;
        // increments the date by one day
        currentDate = currentDate.plusDays(1);
      }

      // at this point, farmRangeWeight is equal to the total weight
      // produced by one over the given date range

      // add farms date range weight to report
      report.add(new Pair<>("Farm " + ID + " Total", String.valueOf(farmRangeWeight)));

      // adds each farms date range weight to pie graph
      Integer farmWeight2 = farmRangeWeight;
      String farm = "Farm " + ID;
      Pair<String, Integer> pair = new Pair<String, Integer>(farm, farmWeight2);
      pieGraph.add(pair);

      // calculates percentage of weight produced by one farm over given
      // date range compared to all farms weight produced in that range
      double farmRangeWeight2 = (double) farmRangeWeight;
      double totalRangeWeight2 = (double) totalRangeWeight;
      double ratio = (farmRangeWeight2 / totalRangeWeight2) * 100;
      double percentage = Math.round(ratio * 100.0) / 100.0;

      // adds farms weight range percentage to report
      report.add(new Pair<>("Farm " + ID + " Percentage", String.valueOf(percentage) + "%"));

    }

  }

  /**
   * Returns the report string array for the GUI
   * 
   * @return the report string array for the GUI
   */
  public ArrayList<Pair<String, String>> getReport() {
    return report;
  }

  /**
   * Returns the report pie graph for the GUI
   * 
   * @return the report pie graph for the GUI
   */
  public ArrayList<Pair<String, Integer>> getPieGraph() {
    return pieGraph;
  }
  
  /**
   * Returns the type of report
   * 
   * @return the type of report
   */
  public String getType() {
    return type;
  }
}
