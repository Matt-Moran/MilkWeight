package application;

@SuppressWarnings("serial")
public class MissingDataException extends Exception {
  public MissingDataException(String errorMessage) {
    super(errorMessage);
  }
}