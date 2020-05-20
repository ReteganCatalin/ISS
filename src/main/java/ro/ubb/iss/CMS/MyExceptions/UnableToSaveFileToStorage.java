package ro.ubb.iss.CMS.MyExceptions;

public class UnableToSaveFileToStorage extends RuntimeException {

  public UnableToSaveFileToStorage(String message) {
    super(message);
  }

  public UnableToSaveFileToStorage(String message, Throwable cause) {
    super(message, cause);
  }

  public UnableToSaveFileToStorage(Throwable cause) {
    super(cause);
  }
}
