package dal.dao.implementations;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import business.dto.EntrepriseDto;
import business.factories.BizFactory;
import dal.dao.interfaces.EntrepriseDao;
import dal.services.DalBackendServices;
import exceptions.OptimisticLockException;
import exceptions.UncheckedSqlException;
import ihm.AppConfig;

/**
 * @author Nathan.
 *
 */
public class EntrepriseDaoImpl implements EntrepriseDao {
  private final Logger logger;
  private DalBackendServices dalBackendService;
  private BizFactory bizFactory;
  private AppConfig queriesConfig;

  /**
   * @param dalBackendService.
   * @param bizFactory.
   */
  public EntrepriseDaoImpl(DalBackendServices dalBackendService, BizFactory bizFactory,
      AppConfig queriesConfig) {
    super();
    this.dalBackendService = dalBackendService;
    this.bizFactory = bizFactory;
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
   * 
   * @see dal.dao.interfaces.EntrepriseDao#creerEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto creerEntreprise(EntrepriseDto entreprise) {
    int id = 0;
    try {
      PreparedStatement ps1 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("insererEntreprise"));
      ps1.setInt(1, entreprise.getCreateur().getId());
      ps1.setString(2, entreprise.getNomEntreprise());
      ps1.setString(3, entreprise.getRue());
      ps1.setString(4, entreprise.getNumero());
      if (!entreprise.getBoite().isEmpty()) {
        ps1.setString(5, entreprise.getBoite());
      } else {
        ps1.setNull(5, 12);
      }
      ps1.setString(6, entreprise.getCodePostal());
      ps1.setString(7, entreprise.getCommune());
      try (ResultSet rs = ps1.executeQuery()) {
        if (rs.next()) {
          id = rs.getInt(1);
          logger.info("entreprise crée");
          entreprise.setIdEntreprise(id);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("creerEntreprise", exc);
    }
    return entreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#findEntrepriseByNomEntreprise(java.lang.String)
   */
  @Override
  public EntrepriseDto findEntrepriseByNomEntreprise(String nomEntreprise) {
    EntrepriseDto entrepriseTrouve = bizFactory.getEntrepriseDto();
    try {
      PreparedStatement ps2 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findEntrepriseByNom"));
      ps2.setString(1, nomEntreprise);
      try (ResultSet rs = ps2.executeQuery()) {
        if (rs.next()) {
          entrepriseTrouve.setIdEntreprise(rs.getInt(1));
          entrepriseTrouve.setNomEntreprise(rs.getString(3));
          Timestamp tim = rs.getTimestamp(4);
          entrepriseTrouve.setDateCreation(tim.toLocalDateTime());
          entrepriseTrouve.setRue(rs.getString(6));
          entrepriseTrouve.setNumero(rs.getString(7));
          if (rs.getString(8) != null) {
            entrepriseTrouve.setBoite(rs.getString(8));
          }
          entrepriseTrouve.setCodePostal(rs.getString(9));
          entrepriseTrouve.setCommune(rs.getString(10));
          entrepriseTrouve.setNumVersion(rs.getInt(11));
          logger.info("entreprise trouvée");
        } else {
          logger.info("Aucun res");
          entrepriseTrouve.setIdEntreprise(-1);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findEntrepriseByNomEntreprise", exc);
    }
    return entrepriseTrouve;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#listeEntreprises()
   */
  @Override
  public ArrayList<EntrepriseDto> listeEntreprises() {
    ArrayList<EntrepriseDto> liste = new ArrayList<>();
    try {
      PreparedStatement ps3 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("affichageEntreprises"));
      try (ResultSet rs = ps3.executeQuery()) {
        if (!rs.next()) {
          logger.info("pas encore d'entreprises");
        } else {
          do {
            EntrepriseDto entreprise = bizFactory.getEntrepriseDto();
            entreprise.setIdEntreprise(rs.getInt(1));
            entreprise.setNomEntreprise(rs.getString(3));
            Timestamp time = rs.getTimestamp(4);
            entreprise.setDateCreation(time.toLocalDateTime());
            entreprise.setRue(rs.getString(6));
            entreprise.setNumero(rs.getString(7));
            if (rs.getString(8) != null) {
              entreprise.setBoite(rs.getString(8));
            }
            entreprise.setCodePostal(rs.getString(9));
            entreprise.setCommune(rs.getString(10));
            entreprise.setNumVersion(rs.getInt(11));
            liste.add(entreprise);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findEntrepriseByNomEntreprise", exc);
    }

    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#findEntrepriseById(int)
   */
  @Override
  public EntrepriseDto findEntrepriseById(int idEntreprise) {
    EntrepriseDto entrepriseTrouve = bizFactory.getEntrepriseDto();
    try {
      PreparedStatement ps2 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findEntrepriseById"));
      ps2.setInt(1, idEntreprise);
      try (ResultSet rs = ps2.executeQuery()) {
        if (rs.next()) {
          entrepriseTrouve.setIdEntreprise(rs.getInt(1));
          entrepriseTrouve.setNomEntreprise(rs.getString(3));
          Timestamp tim = rs.getTimestamp(4);
          entrepriseTrouve.setDateCreation(tim.toLocalDateTime());
          entrepriseTrouve.setRue(rs.getString(6));
          entrepriseTrouve.setNumero(rs.getString(7));
          if (rs.getString(8) != null) {
            entrepriseTrouve.setBoite(rs.getString(8));
          }
          entrepriseTrouve.setCodePostal(rs.getString(9));
          entrepriseTrouve.setCommune(rs.getString(10));
          entrepriseTrouve.setNumVersion(rs.getInt(11));
          logger.info("entreprise trouvée");
        } else {
          logger.info("Aucun res");
          entrepriseTrouve.setIdEntreprise(-1);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findEntrepriseById", exc);
    }
    return entrepriseTrouve;
  }



  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#modifierEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto modifierEntreprise(EntrepriseDto entreprise) {
    try {
      PreparedStatement ps5 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("modificationEntreprise"));
      ps5.setString(1, entreprise.getNomEntreprise());
      ps5.setString(2, entreprise.getRue());
      ps5.setString(3, entreprise.getNumero());
      if (!entreprise.getBoite().isEmpty()) {
        ps5.setString(4, entreprise.getBoite());
      } else {
        ps5.setNull(4, 12);
      }
      ps5.setString(5, entreprise.getCodePostal());
      ps5.setString(6, entreprise.getCommune());
      ps5.setInt(7, entreprise.getIdEntreprise());
      ps5.setInt(8, entreprise.getNumVersion());
      try (ResultSet rs = ps5.executeQuery()) {
        if (rs.next() && entreprise.getIdEntreprise() == rs.getInt(1)) {
          logger.info("entreprise modifiée");
        } else {
          logger.info("Données périmées ou inexistantes");
          throw new OptimisticLockException("données périmées ou inexistantes");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("modifierEntreprise", exc);
    }
    return entreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#getSelectionEntreprises()
   */
  @Override
  public ArrayList<EntrepriseDto> getSelectionEntreprises() {
    ArrayList<EntrepriseDto> liste = new ArrayList<>();
    try {
      PreparedStatement ps3 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("preselectionEntreprises"));
      try (ResultSet rs = ps3.executeQuery()) {
        if (!rs.next()) {
          logger.info("pas d'entreprises");
        } else {
          do {
            EntrepriseDto entreprise = bizFactory.getEntrepriseDto();
            entreprise.setIdEntreprise(rs.getInt(1));
            entreprise.setNomEntreprise(rs.getString(3));
            Timestamp time = rs.getTimestamp(4);
            entreprise.setDateCreation(time.toLocalDateTime());
            entreprise.setRue(rs.getString(6));
            entreprise.setNumero(rs.getString(7));
            if (rs.getString(8) != null) {
              entreprise.setBoite(rs.getString(8));
            }
            entreprise.setCodePostal(rs.getString(9));
            entreprise.setCommune(rs.getString(10));
            entreprise.setNumVersion(rs.getInt(11));
            liste.add(entreprise);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("getSelectionEntreprises", exc);
    }
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#entreprisesNonSelectionnes(int)
   */
  @Override
  public ArrayList<EntrepriseDto> entreprisesNonSelectionnes(int idJournee) {
    ArrayList<EntrepriseDto> liste = new ArrayList<>();
    try {
      PreparedStatement ps = dalBackendService
          .prepareStatement(queriesConfig.getValueFor("findEntrepriseNonSelectionnees"));
      ps.setInt(1, idJournee);
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next()) {
          logger.info("pas d'entreprises");
        } else {
          do {
            EntrepriseDto entreprise = bizFactory.getEntrepriseDto();
            entreprise.setIdEntreprise(rs.getInt(1));
            entreprise.setNomEntreprise(rs.getString(3));
            Timestamp time = rs.getTimestamp(4);
            entreprise.setDateCreation(time.toLocalDateTime());
            entreprise.setRue(rs.getString(6));
            entreprise.setNumero(rs.getString(7));
            if (rs.getString(8) != null) {
              entreprise.setBoite(rs.getString(8));
            }
            entreprise.setCodePostal(rs.getString(9));
            entreprise.setCommune(rs.getString(10));
            entreprise.setNumVersion(rs.getInt(11));
            liste.add(entreprise);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("entreprisesNonSelectionnes", exc);
    }
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#entreprisesInvitees(int)
   */
  @Override
  public ArrayList<EntrepriseDto> entreprisesInvitees(int idJournee) {
    ArrayList<EntrepriseDto> liste = new ArrayList<>();
    try {
      PreparedStatement ps =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findEntreprisesInvitees"));
      ps.setInt(1, idJournee);
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next()) {
          logger.info("pas d'entreprises");
        } else {
          do {
            EntrepriseDto entreprise = bizFactory.getEntrepriseDto();
            entreprise.setIdEntreprise(rs.getInt(1));
            entreprise.setNomEntreprise(rs.getString(3));
            Timestamp tim = rs.getTimestamp(4);
            entreprise.setDateCreation(tim.toLocalDateTime());
            entreprise.setRue(rs.getString(6));
            entreprise.setNumero(rs.getString(7));
            if (rs.getString(8) != null) {
              entreprise.setBoite(rs.getString(8));
            }
            entreprise.setCodePostal(rs.getString(9));
            entreprise.setCommune(rs.getString(10));
            entreprise.setNumVersion(rs.getInt(11));
            logger.info("entreprise trouvée");
            liste.add(entreprise);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("entreprisesInvitees", exc);
    }
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#modifierDatePaye(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto modifierDatePaye(EntrepriseDto entreprise) {
    try {
      PreparedStatement ps =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("modifierDatePaye"));
      Date datePaye = Date.valueOf(entreprise.getDateDerniereParticipationPayee());
      ps.setDate(1, datePaye);
      ps.setInt(2, entreprise.getIdEntreprise());
      ps.setInt(3, entreprise.getNumVersion());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next() && rs.getInt(1) == entreprise.getIdEntreprise()) {
          logger.info("date derniere participation payée");
        } else {
          logger.info("données perimées ou inexistantes");
          throw new OptimisticLockException("Données périmées ou inexistantes");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("modifierDatePaye", exc);
    }
    return entreprise;
  }

  private static String removeLastChar(String str) {
    return str.substring(0, str.length() - 1);
  }

  /**
   * Cree une liste avec le backup de la base de données.
   * 
   * @return la liste.
   */
  public List<String> backupEntreprises() {
    try {
      PreparedStatement ps =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("backupEntreprises"));
      ResultSet rs = ps.executeQuery();
      List<String> liste = new ArrayList<String>();
      while (rs.next()) {
        String line = "INSERT INTO " + queriesConfig.getValueFor("schema") + ".entreprises VALUES(";
        for (int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
          if (rs.getMetaData().getColumnTypeName(i).equalsIgnoreCase("varchar")
              || rs.getMetaData().getColumnTypeName(i).equalsIgnoreCase("date")
              || rs.getMetaData().getColumnTypeName(i).equalsIgnoreCase("timestamp")) {
            line += "'" + rs.getString(i) + "',";
          } else {
            line += rs.getString(i) + ",";
          }
        }
        line = removeLastChar(line);
        line += ");";
        liste.add(line);
      }
      return liste;
    } catch (SQLException sqlexception) {
      throwUncheckedSqlException("backupEntreprises", sqlexception);
    }
    return new ArrayList<String>();
  }
}
