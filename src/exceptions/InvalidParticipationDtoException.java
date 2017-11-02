package exceptions;

/**
 * @author skubi.
 *
 */
public class InvalidParticipationDtoException extends BizException {

  /**
   * Id auto-généré.
   */
  private static final long serialVersionUID = -7191798208949058779L;

  /**
   * Constructeur vide de InvalidParticipationDtoException.
   */
  public InvalidParticipationDtoException() {
    super();
  }

  /**
   * Constructeur avec un message de InvalidParticipationDtoException.
   * 
   * @param message.
   */
  public InvalidParticipationDtoException(String message) {
    super(message);
  }

}
