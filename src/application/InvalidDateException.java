/*
 * Assignment Name:   MilkWeight
 * Filename:          InvalidDateException.Java
 * Authors:           ATEAM050
 * Known Bugs:        None
 */

package application;

/**
 * Exception occurs when date is not in the correct format
 * 
 * @author ATEAM050
 */
@SuppressWarnings("serial")
public class InvalidDateException extends Exception {
  /**
   * Default constructor for exceptions
   * 
   * @param errorMessage the error message
   */
  public InvalidDateException(String errorMessage) {
    super(errorMessage);
  }
}