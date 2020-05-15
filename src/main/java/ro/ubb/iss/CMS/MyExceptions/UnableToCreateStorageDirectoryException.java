package ro.ubb.iss.CMS.MyExceptions;

public class UnableToCreateStorageDirectoryException extends RuntimeException {

  public UnableToCreateStorageDirectoryException(String message) {
    super(message);
  }

  public UnableToCreateStorageDirectoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnableToCreateStorageDirectoryException(Throwable cause) {
    super(cause);
  }
}
