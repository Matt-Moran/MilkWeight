package application;

@SuppressWarnings("serial")
public class InvalidDateException extends Exception {
  public InvalidDateException(String errorMessage) {
    super(errorMessage);
  }
}