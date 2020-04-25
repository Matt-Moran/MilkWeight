package application;

@SuppressWarnings("serial")
public class InvalidReportException extends Exception {
  public InvalidReportException(String errorMessage) {
    super(errorMessage);
  }
}