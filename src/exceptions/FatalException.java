package exceptions;

/**
 * @author skubi.
 *
 */
public class FatalException extends RuntimeException {

  /**
   * Id auto généré.
   */
  private static final long serialVersionUID = 634584548335692632L;


  public FatalException() {
    super();
  }


  public FatalException(String message) {
    super(message);
  }

}
