/*
 * Assignment Name:   MilkWeight
 * Filename:          MissingDataException.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

/**
 * Exception occurs when data is missing from CSV files
 * 
 * @author ATEAM050
 */
@SuppressWarnings("serial")
public class MissingDataException extends Exception {
  /**
   * Default constructor for exceptions
   * 
   * @param errorMessage the error message
   */
  public MissingDataException(String errorMessage) {
    super(errorMessage);
  }
}