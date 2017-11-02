package dal.dao.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import dal.dao.interfaces.UtilisateurDao;
import dal.services.DalBackendServices;
import exceptions.UncheckedSqlException;
import ihm.AppConfig;

/**
 * @author rachid.
 *
 */
public class UtilisateurDaoImpl implements UtilisateurDao {
  private DalBackendServices dalBackendService;
  private BizFactory bizFactory;
  private final Logger logger;
  private AppConfig queriesConfig;

  /**
   * Consructeur UtilisateurDaoImpl.
   * 
   * @param dalBservice dalBService.
   * @param bizFact bizzFactory.
   */
  public UtilisateurDaoImpl(DalBackendServices dalBservice, BizFactory bizFact,
      AppConfig queriesConfig) {
    dalBackendService = dalBservice;
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
   * Cree un utilisateur en base de donnees.
   * 
   * @param udto contient l'ensembles des informations à inserer en DB pour l'inscription d'un
   *        utilisateur.
   * @return un utilisateurDto contenant l'ensemble des champs donnees via le parametre plus
   *         l'id_journee.
   */
  @Override
  public UtilisateurDto inscrireUtilisateur(UtilisateurDto udto) {
    int id = 0;
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("inscrireUtilisateur"));
      pst.setString(1, udto.getPseudo());
      pst.setString(2, udto.getNom());
      pst.setString(3, udto.getPrenom());
      pst.setString(4, udto.getEmail());
      pst.setString(5, udto.getMdp());
      pst.setBoolean(6, udto.isResponsable());
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          id = rs.getInt(1);
          logger.info("Utilisateur ajoute");
          udto.setId(id);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("inscrireUtilisateur", exc);
    }
    return udto;
  }

  /**
   * Trouve l'utilisateur dans la base de donnees en fonction de son mail.
   * 
   * @param mail String grâce auquel on trouve l'utilisateur.
   * @return un UtilisateurDto avec toutes les informations de l'utilisateur.
   */
  @Override
  public UtilisateurDto findUtilisateurByEmail(String mail) {
    logger.info("DaoImpl findUtilisateurByPseudo\t\t\tseq");
    UtilisateurDto utilisateurTrouve = bizFactory.getUtilisateurDto();// new
    // UtilisateurImpl();
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findUtilisateurByEmail"));
      pst.setString(1, mail);
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          utilisateurTrouve.setId(rs.getInt(1));
          utilisateurTrouve.setPseudo(rs.getString(2));
          utilisateurTrouve.setNom(rs.getString(3));
          utilisateurTrouve.setPrenom(rs.getString(4));
          utilisateurTrouve.setEmail(rs.getString(5));
          Timestamp tim = rs.getTimestamp(6);
          utilisateurTrouve.setDateInscription(tim.toLocalDateTime());
          utilisateurTrouve.setMdp(rs.getString(7));
          utilisateurTrouve.setResponsable(rs.getBoolean(8));
          utilisateurTrouve.setNumVersion(rs.getInt(9));
          logger.info("Utilisateur trouve");
        } else {
          logger.info("Aucun res");
          utilisateurTrouve.setId(-1);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findUtilisateurByEmail", exc);
    }
    return utilisateurTrouve;
  }

  /**
   * Trouve l'utilisateur dans la base de donnees en fonction de son pseudo.
   * 
   * @param pseudo String grâce auquel on trouve l'utilisateur.
   * @return un UtilisateurDto avec toutes les informations de l'utilisateur.
   */
  @Override
  public UtilisateurDto findUtilisateurByPseudo(String pseudo) {
    logger.info("DaoImpl findUtilisateurByPseudo\t\t\tseq");
    UtilisateurDto utilisateurTrouve = bizFactory.getUtilisateurDto();// new
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findUtilisateurByPseudo"));
      pst.setString(1, pseudo);
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          utilisateurTrouve.setId(rs.getInt(1));
          utilisateurTrouve.setPseudo(rs.getString(2));
          utilisateurTrouve.setNom(rs.getString(3));
          utilisateurTrouve.setPrenom(rs.getString(4));
          utilisateurTrouve.setEmail(rs.getString(5));
          Timestamp tim = rs.getTimestamp(6);
          utilisateurTrouve.setDateInscription(tim.toLocalDateTime());
          utilisateurTrouve.setMdp(rs.getString(7));
          utilisateurTrouve.setResponsable(rs.getBoolean(8));
          utilisateurTrouve.setNumVersion(rs.getInt(9));
          utilisateurTrouve.setNombreTentativesConnexions(rs.getInt(10));
          LocalDateTime date = null;
          Timestamp tmp = rs.getTimestamp(11);
          if (tmp != null) {
            date = tmp.toLocalDateTime();
          }
          utilisateurTrouve.setDateDerniereTentative(date);
          logger.info("Utilisateur trouve");
        } else {
          logger.info("Aucun res");
          utilisateurTrouve.setId(-1);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findUtilisateurByPseudo", exc);
    }
    return utilisateurTrouve;
  }

  /**
   * Trouve l'utilisateur dans la base de donnees en fonction de son id.
   * 
   * @param id Int grace auquel on trouve l'utilisateur.
   * @return un UtilisateurDto avec toutes les informations de l'utilisateur.
   */
  @Override
  public UtilisateurDto findUtilisateurById(int id) {
    UtilisateurDto utilisateurTrouve = bizFactory.getUtilisateurDto();
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findUtilisateurById"));
      pst.setInt(1, id);
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          utilisateurTrouve.setId(rs.getInt(1));
          utilisateurTrouve.setPseudo(rs.getString(2));
          utilisateurTrouve.setNom(rs.getString(3));
          utilisateurTrouve.setPrenom(rs.getString(4));
          utilisateurTrouve.setEmail(rs.getString(5));
          Timestamp tim = rs.getTimestamp(6);
          utilisateurTrouve.setDateInscription(tim.toLocalDateTime());
          utilisateurTrouve.setMdp(rs.getString(7));
          utilisateurTrouve.setResponsable(rs.getBoolean(8));
          utilisateurTrouve.setNumVersion(rs.getInt(9));
          logger.info("Utilisateur trouve");
        } else {
          logger.info("Aucun res");
          utilisateurTrouve.setId(-1);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findUtilisateurById", exc);
    }
    return utilisateurTrouve;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.UtilisateurDao#reinitTentatives(business.dto.UtilisateurDto)
   */
  @Override
  public void resetTentatives(String pseudo) {
    logger.info("DaoImpl reinitTentatives\t\t\tseq");
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("resetTentatives"));
      pst.setString(1, pseudo);
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          logger.info("Donnees tentatives reinitialisees");
        } else {
          logger.info("Aucun res");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("resetTentatives", exc);
    }
  }

  @Override
  public void majTentatives(String pseudo) {
    logger.info("DaoImpl majTentatives\t\t\tseq");
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("majTentatives"));
      pst.setString(1, pseudo);
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          logger.info("Donnees tentatives ont ete maj");
        } else {
          logger.info("Aucun res");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("majTentatives", exc);
    }

  }

  /*
   * 
   * @Override public List<String> backup() { return this.dalBackendService.backup(); }
   */
}
