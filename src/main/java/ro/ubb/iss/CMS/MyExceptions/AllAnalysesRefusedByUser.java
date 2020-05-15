package ro.ubb.iss.CMS.MyExceptions;

public class AllAnalysesRefusedByUser extends RuntimeException {

  public AllAnalysesRefusedByUser(String message) {
    super(message);
  }

  public AllAnalysesRefusedByUser(String message, Throwable cause) {
    super(message, cause);
  }

  public AllAnalysesRefusedByUser(Throwable cause) {
    super(cause);
  }
}
