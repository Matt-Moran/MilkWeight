package application;

@SuppressWarnings("serial")
public class DataFormatException extends Exception {
  public DataFormatException(String errorMessage) {
    super(errorMessage);
  }
}