package dal.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import exceptions.UncheckedSqlException;

/**
 * @author rachid.
 *
 */
public class DalServicesStub implements DalServices, DalBackendServices {
  private boolean throwSqlUncheckedException;

  /**
   * Constructeur DalServicesStub.
   */
  public DalServicesStub() {
    this.throwSqlUncheckedException = false;
  }

  private void setThrowSqlUncheckedException(boolean throwSqlUncheckedException) {
    this.throwSqlUncheckedException = throwSqlUncheckedException;;
  }

  private void tryToThrowSqlUncheckedException(String message) {
    if (throwSqlUncheckedException) {
      throw new UncheckedSqlException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.services.DalBackendServices#prepareStatement(java.lang.String)
   */
  @Override
  public PreparedStatement prepareStatement(String string) throws SQLException {
    tryToThrowSqlUncheckedException("Echec prepareStatement");
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.services.DalServices#startTransaction()
   */
  @Override
  public void startTransaction() {
    tryToThrowSqlUncheckedException("Echec startTransaction");

  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.services.DalServices#commitTransaction()
   */
  @Override
  public void commitTransaction() {
    tryToThrowSqlUncheckedException("Echec commitTransaction");

  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.services.DalServices#rollbackTransaction()
   */
  @Override
  public void rollbackTransaction() {
    tryToThrowSqlUncheckedException("Echec rollbackTransaction");

  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.services.DalServices#openConnection()
   */
  @Override
  public void openConnection() {
    tryToThrowSqlUncheckedException("Echec openConnection");

  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.services.DalServices#closeConnection()
   */
  @Override
  public void closeConnection() {
    tryToThrowSqlUncheckedException("Echec closeConnection");

  }

  @Override
  public boolean hasAlreadyOpenedConnection() {
    return true;
  }


  @Override
  public boolean hasAlreadyClosedConnection() {
    return true;
  }

  @Override
  public boolean hasOpenTransaction() {
    return false;
  }

  @Override
  public List<String> backup() {
    // TODO Auto-generated method stub
    return null;
  }


}
