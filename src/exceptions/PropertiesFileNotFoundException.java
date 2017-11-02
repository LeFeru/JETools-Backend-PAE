package exceptions;

/**
 * @author rachid.
 *
 */
public class PropertiesFileNotFoundException extends RuntimeException {

  /**
   * Constructeur vide de PropertiesFileNotFoundException.
   */
  public PropertiesFileNotFoundException() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Constructeur avec un message de PropertiesFileNotFoundException.
   * 
   * @param message.
   */
  public PropertiesFileNotFoundException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  /**
   * Constructeur de PropertiesFileNotFoundException avec un Throwable.
   * 
   * @param cause.
   */
  public PropertiesFileNotFoundException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }

  /**
   * Constructeur de PropertiesFileNotFoundException avec un Throwable et un message.
   * 
   * @param message.
   * @param cause.
   */
  public PropertiesFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  /**
   * Constructeur de PropertiesFileNotFoundException avec un Throwable, un message et des booléens
   * de configuration du message.
   * 
   * @param message.
   * @param cause.
   * @param enableSuppression.
   * @param writableStackTrace.
   */
  public PropertiesFileNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    // TODO Auto-generated constructor stub
  }

}
