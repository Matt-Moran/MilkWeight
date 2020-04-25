package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javafx.util.Pair;

public class Report {
  
  // USE DATA FROM THE Main.java ArrayList<Farm> farms (READ ONLY)

  /* 
   * Helps store the import information for the report.
   * (Key: (value) == (String key, String value))
   */
  private HashMap<String, String> report;
  
  /*
   * An array list of each pair for the pie graph.
   * The pair contains a string and integer:
   * String: the label for the value
   * Integer: the value for the label
   */
  private ArrayList<Pair<String, Integer>> pieGraph;

  // FARM REPORT
  /**
   * Assigns values to the "report" and "pieGraph" variables for the
   * specifications of a FARM REPORT
   * 
   * EXAMPLE REPORT VARIABLE:
   * Farm ID: (farm id)
   * Year: (year)
   * 
   * Year Total: (year total)
   * Year Percentage: (year total / all farm year total * 100)
   * 
   * Month 1 Total: (month 1 total)
   * Month 1 Percentage: (month 1 total / all farm month 1 total * 100)
   * Month 2 Total: ""   ""
   * Month 2 Percentage: ""   ""
   * ...
   * Month 12 Total: ""   ""
   * Month 12 Percentage: ""   ""
   * 
   * EXAMPLE PIE GRAPH VARIABLE:
   * pieGraph {
   * ("Month 1", month 1 total)
   * ("Month 2", month 2 total)
   * ...
   * ("Month 12", month 12 total)
   * }
   * 
   * @param farmId the farm ID
   * @param year   the year for the farm
   * @throws InvalidReportException if the farm ID is null, if the farm ID doesn't
   *                                exist or if the year is null
   */
  public Report(String farmId, int year) throws InvalidReportException {
    report = new HashMap<String, String>();
    pieGraph = new ArrayList<Pair<String, Integer>>();

    // TODO implement initialization for FARM REPORT
  }
  
  // ANNUAL REPORT
  /**
   * Assigns values to the "report" and "pieGraph" variables for the
   * specifications of a ANNUAL REPORT
   * 
   * EXAMPLE REPORT VARIABLE:
   * Year: (year)
   * 
   * Year Total: (all farm year total)
   * 
   * farm1 Total: (farm1 year total)
   * farm1 Percentage: (farm1 year total / all farm year total * 100)
   * farm2 Total: ""   ""
   * farm2 Percentage: ""   ""
   * ...
   * farm103 Total: ""   ""
   * farm103 Percentage: ""   ""
   * 
   * EXAMPLE PIE GRAPH VARIABLE:
   * pieGraph {
   * ("farm1", farm1 year total)
   * ("farm2", farm2 year total)
   * ...
   * ("farm103", farm103 year total)
   * }
   * 
   * @param year   the year for the report
   * @throws InvalidReportException if the year is null
   */
  public Report(int year) throws InvalidReportException {
    report = new HashMap<String, String>();
    pieGraph = new ArrayList<Pair<String, Integer>>();

    // TODO implement initialization for ANNUAL REPORT
  }
  
  // MONTHLY REPORT
  /**
   * Assigns values to the "report" and "pieGraph" variables for the
   * specifications of a MONTHLY REPORT
   * 
   * EXAMPLE REPORT VARIABLE:
   * Year: (year)
   * Month: (month)
   * 
   * Month Total: (all farm month total)
   * 
   * farm1 Total: (farm1 month total)
   * farm1 Percentage: (farm1 month total / all farm month total * 100)
   * farm2 Total: ""   ""
   * farm2 Percentage: ""   ""
   * ...
   * farm103 Total: ""   ""
   * farm103 Percentage: ""   ""
   * 
   * EXAMPLE PIE GRAPH VARIABLE:
   * pieGraph {
   * ("farm1", farm1 month total)
   * ("farm2", farm2 month total)
   * ...
   * ("farm103", farm103 month total)
   * }
   * 
   * @param year   the year for the report
   * @param month   the month for the report
   * @throws InvalidReportException if the year is null
   */
  public Report(int year, int month) throws InvalidReportException {
    report = new HashMap<String, String>();
    pieGraph = new ArrayList<Pair<String, Integer>>();

    // TODO implement initialization for MONTHLY REPORT
  }
  
  // DATE RANGE REPORT
  /**
   * Assigns values to the "report" and "pieGraph" variables for the
   * specifications of a DATE RANGE REPORT
   * 
   * EXAMPLE REPORT VARIABLE:
   * Start Date: (startDate)
   * endDate: (endDate)
   * 
   * Range Total: (all farm range total)
   * 
   * farm1 Total: (farm1 range total)
   * farm1 Percentage: (farm1 range total / all farm range total * 100)
   * farm2 Total: ""   ""
   * farm2 Percentage: ""   ""
   * ...
   * farm103 Total: ""   ""
   * farm103 Percentage: ""   ""
   * 
   * EXAMPLE PIE GRAPH VARIABLE:
   * pieGraph {
   * ("farm1", farm1 range total)
   * ("farm2", farm2 range total)
   * ...
   * ("farm103", farm103 range total)
   * }
   * 
   * @param startDate the start of the date range for the report
   * @param endDate the end of the date range for the report
   * @throws InvalidReportException if the year is null
   */
  public Report(Date startDate, Date endDate) throws InvalidReportException {
    report = new HashMap<String, String>();
    pieGraph = new ArrayList<Pair<String, Integer>>();

    // TODO implement initialization for DATE RANGE REPORT
  }
  
  /**
   * Returns the report string array for the GUI
   * @return the report string array for the GUI
   */
  public HashMap<String, String> getReport() {
    return report;
  }
  
  /**
   * Returns the report pie graph for the GUI
   * @return the report pie graph for the GUI
   */
  public ArrayList<Pair<String, Integer>> getPieGraph() {
    return pieGraph;
  }
}
