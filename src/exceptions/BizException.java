package exceptions;

/**
 * @author skubi.
 *
 */
public class BizException extends RuntimeException {


  /**
   * Id auto généré.
   */
  private static final long serialVersionUID = -924279220499454941L;


  public BizException() {
    super();
  }


  public BizException(String message) {
    super(message);
  }

}
