package exceptions;


public class UncheckedSqlException extends RuntimeException {
  /**
   * Id auto généré.
   */
  private static final long serialVersionUID = -7731289444000765107L;

  public UncheckedSqlException() {
    super();
  }

  public UncheckedSqlException(String message) {
    super(message);
  }

}
