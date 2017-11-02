package exceptions;

/**
 * @author rachid.
 *
 */
public class ConnectionNotAvailableException extends BizException {

  /**
   * Id auto généré.
   */
  private static final long serialVersionUID = -4938041490228442524L;


  public ConnectionNotAvailableException() {
    super();
  }


  public ConnectionNotAvailableException(String message) {
    super(message);
  }

}
