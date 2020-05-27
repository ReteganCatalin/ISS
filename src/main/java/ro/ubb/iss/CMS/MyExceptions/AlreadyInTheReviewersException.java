package ro.ubb.iss.CMS.MyExceptions;

public class AlreadyInTheReviewersException extends RuntimeException {

  public AlreadyInTheReviewersException(String message) {
    super(message);
  }

  public AlreadyInTheReviewersException(String message, Throwable cause) {
    super(message, cause);
  }

  public AlreadyInTheReviewersException(Throwable cause) {
    super(cause);
  }
}
