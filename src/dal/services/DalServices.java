package dal.services;

/**
 * @author rachid.
 *
 */
public interface DalServices {

  /**
   * Démarre une nouvelle transaction.
   */
  void startTransaction();

  /**
   * Applique les changements de transaction dans la db.
   */
  void commitTransaction();

  /**
   * Annule toutes les instructions d'une transaction.
   */
  void rollbackTransaction();

  /**
   * Ouvre une nouvelle connexion.
   */
  void openConnection();

  /**
   * Ferme la connexion.
   */
  void closeConnection();

  boolean hasAlreadyOpenedConnection();

  boolean hasAlreadyClosedConnection();

  boolean hasOpenTransaction();

}
