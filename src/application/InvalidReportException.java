/*
 * Assignment Name:   MilkWeight
 * Filename:          InvalidReportException.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

/**
 * Exception occurs when report creation fails
 * 
 * @author ATEAM050
 */
@SuppressWarnings("serial")
public class InvalidReportException extends Exception {
  /**
   * Default constructor for exceptions
   * 
   * @param errorMessage the error message
   */
  public InvalidReportException(String errorMessage) {
    super(errorMessage);
  }
}