/*
 * Assignment Name:   MilkWeight
 * Filename:          DataFormatException.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

/**
 * Exception occurs when data is not formatted correctly in CSV files
 * 
 * @author ATEAM050
 */
@SuppressWarnings("serial")
public class DataFormatException extends Exception {
  /**
   * Default constructor for exceptions
   * 
   * @param errorMessage the error message
   */
  public DataFormatException(String errorMessage) {
    super(errorMessage);
  }
}