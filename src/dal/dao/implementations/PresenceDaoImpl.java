package dal.dao.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import business.dto.PresenceDto;
import business.factories.BizFactory;
import dal.dao.interfaces.PresenceDao;
import dal.services.DalBackendServices;
import exceptions.UncheckedSqlException;
import ihm.AppConfig;

/**
 * @author Skubi.
 *
 */
public class PresenceDaoImpl implements PresenceDao {
  private DalBackendServices dalBackendService;
  private BizFactory bizFactory;
  private final Logger logger;
  private AppConfig queriesConfig;

  /**
   * Consructeur PresenceDaoImpl.
   * 
   * @param dalBacService dalBackendService.
   * @param bizFact bizFactory.
   */
  public PresenceDaoImpl(DalBackendServices dalBacService, BizFactory bizFact,
      AppConfig queriesConfig) {
    this.dalBackendService = dalBacService;
    this.bizFactory = bizFact;
    this.queriesConfig = queriesConfig;
    this.logger = Logger.getLogger("reportsLogger");
  }

  private void throwUncheckedSqlException(String methode, SQLException sqlException) {
    logger.info("Exception lors de l'appel de la methode: " + methode);
    logger.info(sqlException.getMessage() + "----" + sqlException.getErrorCode() + "-----");
    throw new UncheckedSqlException(sqlException.getMessage());
  }

  /*
   * (non-Javadoc)
   * @see dal.dao.interfaces.PresenceDao#findPresenceByIdParticipationIdPersonne(business.dto.
   * PresenceDto)
   */
  @Override
  public PresenceDto findPresenceByIdParticipationIdPersonne(PresenceDto presenceDto) {
    int idParticipation = 0;
    int idPersonne = 0;
    PresenceDto retour = bizFactory.getPresenceDto();
    try {
      PreparedStatement ps3 = dalBackendService
          .prepareStatement(queriesConfig.getValueFor("findPresenceByIdParticipationIdPersonne"));
      ps3.setInt(1, presenceDto.getIdParticipation());
      ps3.setInt(2, presenceDto.getIdPersonne());
      try (ResultSet rs = ps3.executeQuery()) {
        if (rs.next()) {
          idParticipation = rs.getInt(1);
          idPersonne = rs.getInt(2);
        } else {
          logger.error("aucune presence trouvee");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findPresenceByIdParticipationIdPersonne", exc);
    }
    retour.setIdParticipation(idParticipation);
    retour.setIdPersonne(idPersonne);
    return retour;
  }


  /*
   * (non-Javadoc)
   * @see dal.dao.interfaces.PresenceDao#insererPresence(business.dto.PresenceDto)
   */
  @Override
  public PresenceDto insererPresence(PresenceDto presenceDto) {
    try {
      PreparedStatement ps3 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("insererPresence"));
      ps3.setInt(1, presenceDto.getIdParticipation());
      ps3.setInt(2, presenceDto.getIdPersonne());
      try (ResultSet rs = ps3.executeQuery()) {
        if (rs.next()) {
          logger.info("Presence creee");
        } else {
          logger.error("aucune presence creee");
        }
      }
    } catch (SQLException exc) {
      logger.info("Echec insererPresence");
      throwUncheckedSqlException("insererPresence", exc);
    }
    return presenceDto;
  }

}
