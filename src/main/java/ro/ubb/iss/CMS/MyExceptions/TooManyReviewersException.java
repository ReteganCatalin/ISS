package ro.ubb.iss.CMS.MyExceptions;

public class TooManyReviewersException extends RuntimeException {

  public TooManyReviewersException(String message) {
    super(message);
  }

  public TooManyReviewersException(String message, Throwable cause) {
    super(message, cause);
  }

  public TooManyReviewersException(Throwable cause) {
    super(cause);
  }
}
