package dal.dao.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import business.dto.JourneeDto;
import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import dal.dao.interfaces.JourneeDao;
import dal.services.DalBackendServices;
import exceptions.UncheckedSqlException;
import ihm.AppConfig;

/**
 * @author rachid.
 *
 */
public class JourneeDaoImpl implements JourneeDao {
  private DalBackendServices dalBackendService;
  private BizFactory bizFactory;
  private final Logger logger;
  private AppConfig queriesConfig;

  /**
   * Consructeur JourneeDaoImpl.
   * 
   * @param dalBacService dalBackendService.
   * @param bizFact bizFactory.
   */
  public JourneeDaoImpl(DalBackendServices dalBacService, BizFactory bizFact,
      AppConfig queriesConfig) {
    dalBackendService = dalBacService;
    bizFactory = bizFact;
    this.queriesConfig = queriesConfig;
    this.logger = Logger.getLogger("reportsLogger");
  }

  private void throwUncheckedSqlException(String methode, SQLException sqlException) {
    logger.info("Exception lors de l'appel de la methode: " + methode);
    logger.info(sqlException.getMessage() + "----" + sqlException.getErrorCode() + "-----");
    throw new UncheckedSqlException(sqlException.getMessage());
  }

  /**
   * Cree une journee en base de donnees.
   * 
   * @param journeeDto contient l'ensemble des informations a inserer en DB pour la creation d'une
   *        JE.
   * @return une JourneeDto contenant l'ensemble des champs donnes via le parametre plus
   *         l'id_journee.
   */
  @Override
  public JourneeDto creerJournee(JourneeDto journeeDto) {
    int id = 0;
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("creationJournee"));
      pst.setInt(1, journeeDto.getIdCreateur().getId());
      pst.setString(2, journeeDto.getDateJe().toString());
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          id = rs.getInt(1);
          logger.info("Journee ajoutee");
          journeeDto.setIdJournee(id);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("creerJournee", exc);
    }
    return journeeDto;
  }

  /**
   * Cherche une journee avec la date donnee en parametre.
   * 
   * @param date LocalDate, date recherchee en base de donnees.
   * @return une JourneeDto avec son id si elle a ete trouvee en base de donnees.
   */
  @Override
  public JourneeDto findJourneeByDate(LocalDate date) {
    int id = 0;
    JourneeDto journeeDto = bizFactory.getJourneeDto();
    journeeDto.setDateJe(date);
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findJourneeByDate"));
      pst.setString(1, date.toString());
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          id = rs.getInt(1);
          logger.info("Journee deja  existante");
          journeeDto.setIdJournee(id);
          journeeDto.setCloturee(rs.getBoolean(4));
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findJourneeByDate", exc);
    }
    journeeDto.setIdJournee(id);
    return journeeDto;
  }

  @Override
  public JourneeDto findJourneeActive() {
    int id = 0;
    JourneeDto journeeDto = bizFactory.getJourneeDto();
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findJourneeActive"));
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          id = rs.getInt(1);
          logger.info("Journee trouvee");
          journeeDto.setIdJournee(id);
          LocalDate dateJe = rs.getDate(3).toLocalDate();
          journeeDto.setDateJe(dateJe);
          journeeDto.setCloturee(rs.getBoolean(4));
        } else {
          journeeDto.setIdJournee(-1);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findJourneeActive", exc);
    }
    // journeeDto.setIdJournee(id);
    return journeeDto;
  }

  /**
   * Cloture les journees encore actives.
   */
  @Override
  public void cloturerJournees() {
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("cloturerJournees"));
      pst.execute();
    } catch (SQLException exc) {
      throwUncheckedSqlException("cloturerJournees", exc);
    }
  }

  /**
   * Renvoie les journees encore actives.
   * 
   * @return ArrayList de journees actives.
   */
  @Override
  public ArrayList<JourneeDto> findJournees() {
    ArrayList<JourneeDto> res = new ArrayList<>();
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findJournees"));
      try (ResultSet rs = pst.executeQuery()) {
        if (!rs.next()) {
          logger.info("pas de journées");
        } else {
          do {
            JourneeDto temp = bizFactory.getJourneeDto();
            temp.setIdJournee(rs.getInt(1));
            UtilisateurDto tempUtilisateur = bizFactory.getUtilisateurDto();
            tempUtilisateur.setId(rs.getInt(2));
            temp.setIdCreateur(tempUtilisateur);
            temp.setDateJe(rs.getDate(3).toLocalDate());
            temp.setCloturee(rs.getBoolean(4));
            temp.setNumVersion(rs.getInt(5));
            res.add(temp);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findJournees", exc);
    }
    return res;
  }

  @Override
  public JourneeDto findJourneeById(int idJournee) {
    JourneeDto journeeTrouve = bizFactory.getJourneeDto();
    try {
      PreparedStatement ps =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findJourneeById"));
      ps.setInt(1, idJournee);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          journeeTrouve.setIdJournee(rs.getInt(1));
          journeeTrouve.setDateJe(rs.getDate(3).toLocalDate());
          journeeTrouve.setCloturee(rs.getBoolean(4));
          journeeTrouve.setNumVersion(rs.getInt(5));
          logger.info("journee trouvee");
        } else {
          logger.info("aucun res");
          journeeTrouve.setIdJournee(-1);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findJourneeById", exc);
    }
    return journeeTrouve;
  }

  @Override
  public int cloturerJournee(int idJournee) {
    try {
      PreparedStatement ps =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("cloturerJournee"));
      ps.setInt(1, idJournee);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          logger.info("journée cloturée");
          return idJournee;
        } else {
          logger.info("journée déjà cloturée");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("cloturerJournee", exc);
    }
    return -1;
  }

  @Override
  public JourneeDto findDerniereJournee() {
    JourneeDto journeeTrouve = bizFactory.getJourneeDto();
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findDerniereJournee"));
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          logger.info("Journee trouvee");
          journeeTrouve.setIdJournee(rs.getInt(1));
          LocalDate dateJe = rs.getDate(3).toLocalDate();
          journeeTrouve.setDateJe(dateJe);
          journeeTrouve.setCloturee(rs.getBoolean(4));
        } else {
          journeeTrouve.setIdJournee(-1);
          logger.info("pas encore de JE");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findJourneeActive", exc);
    }
    return journeeTrouve;
  }
}
