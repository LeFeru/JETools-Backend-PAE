package dal.dao.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import business.dto.ParticipationDto;
import business.factories.BizFactory;
import dal.dao.interfaces.ParticipationDao;
import dal.services.DalBackendServices;
import exceptions.OptimisticLockException;
import exceptions.UncheckedSqlException;
import ihm.AppConfig;

public class ParticipationDaoImpl implements ParticipationDao {
  private DalBackendServices dalBackendService;
  private BizFactory bizFactory;
  private final Logger logger;
  private AppConfig queriesConfig;

  /**
   * Consructeur ParticipationDaoImpl.
   * 
   * @param dalBacService dalBackendService.
   * @param bizFact bizFactory.
   */
  public ParticipationDaoImpl(DalBackendServices dalBacService, BizFactory bizFact,
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

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#insererParticipation(business.dto.ParticipationDto)
   */
  @Override
  public ParticipationDto insererParticipation(ParticipationDto participationDto) {
    int id = 0;
    try {
      PreparedStatement ps1 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("insererParticipation"));
      ps1.setInt(1, participationDto.getIdJournee());
      ps1.setInt(2, participationDto.getIdEntreprise());
      if (participationDto.getEvolution() == null) {
        ps1.setNull(3, 0);
      } else {
        ps1.setString(3, participationDto.getEvolution());
      }
      ps1.setBoolean(4, participationDto.isAnnulee());
      try (ResultSet rs = ps1.executeQuery()) {
        if (rs.next()) {
          id = rs.getInt(1);
          logger.info("participation créée");
          participationDto.setIdParticipation(id);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("insererParticipation", exc);
    }
    return participationDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#findParticipationByIdJourneeIdEntreprise(business.dto.
   * ParticipationDto)
   */
  @Override
  public ParticipationDto findParticipationByIdJourneeIdEntreprise(
      ParticipationDto participationDto) {
    int id = 0;
    try {
      String findPartByIdJourIdEntr =
          queriesConfig.getValueFor("findParticipationByIdJourneeIdEntreprise");
      PreparedStatement ps1 = dalBackendService.prepareStatement(findPartByIdJourIdEntr);
      ps1.setInt(1, participationDto.getIdJournee());
      ps1.setInt(2, participationDto.getIdEntreprise());
      try (ResultSet rs = ps1.executeQuery()) {
        if (rs.next()) {
          id = rs.getInt(1);
          participationDto.setEtat(rs.getString(4));
          participationDto.setEvolution(rs.getString(5));
          participationDto.setAnnulee(rs.getBoolean(6));
          logger.info("participation trouvée");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findParticipationByIdJourneeIdEntreprise", exc);
    }
    participationDto.setIdParticipation(id);
    return participationDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#findParticipationByIdEntreprise(int)
   */
  @Override
  public List<ParticipationDto> findParticipationByIdEntreprise(int idEntreprise) {
    List<ParticipationDto> res = new ArrayList<>();
    try {
      String findPartByIdJourIdEntr = queriesConfig.getValueFor("findParticipationByIdEntreprise");
      PreparedStatement ps1 = dalBackendService.prepareStatement(findPartByIdJourIdEntr);
      ps1.setInt(1, idEntreprise);
      logger.info(ps1);
      try (ResultSet rs = ps1.executeQuery()) {
        if (!rs.next()) {
          logger.info("aucune participation trouvée");
        } else {
          do {
            ParticipationDto temp = bizFactory.getParticipationDto();
            temp.setIdParticipation(rs.getInt(1));
            temp.setIdJournee(rs.getInt(2));
            temp.setIdEntreprise(rs.getInt(3));
            temp.setEtat(rs.getString(4));
            temp.setEvolution(rs.getString(5));
            temp.setAnnulee(rs.getBoolean(6));
            logger.info("participation trouvée");
            res.add(temp);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findParticipationByIdEntreprise", exc);
    }
    logger.info(res);
    return res;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#annulerParticipation(business.dto.ParticipationDto)
   */
  @Override
  public void annulerParticipation(ParticipationDto participation) {
    try {
      PreparedStatement ps =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("annulerParticipation"));
      ps.setInt(1, participation.getIdParticipation());
      ps.setInt(2, participation.getNumVersion());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next() && participation.getIdParticipation() == rs.getInt(1)) {
          logger.info("participation annulée");
        } else {
          logger.info("participation déjà annulée");
          throw new OptimisticLockException(
              "La participation a déjà été annulée");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("annulerParticipation", exc);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#participationsNonAnnulees(int)
   */
  @Override
  public ArrayList<ParticipationDto> participationsNonAnnulees(int idJournee) {
    ArrayList<ParticipationDto> liste = new ArrayList<>();
    try {
      PreparedStatement ps = dalBackendService
          .prepareStatement(queriesConfig.getValueFor("participationsNonAnnulees"));
      ps.setInt(1, idJournee);
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next()) {
          logger.info("pas de participations");
        } else {
          do {
            ParticipationDto temp = bizFactory.getParticipationDto();
            temp.setIdParticipation(rs.getInt(1));
            temp.setIdJournee(rs.getInt(2));
            temp.setIdEntreprise(rs.getInt(3));
            temp.setEtat(rs.getString(4));
            temp.setEvolution(rs.getString(5));
            temp.setAnnulee(rs.getBoolean(6));
            temp.setNumVersion(rs.getInt(7));
            logger.info("participation trouvée");
            liste.add(temp);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("participationsNonAnnulees", exc);
    }
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#modifierParticipation(business.dto.ParticipationDto)
   */
  @Override
  public ParticipationDto modifierParticipation(ParticipationDto participation) {
    try {
      PreparedStatement ps =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("modifierParticipation"));
      ps.setString(1, participation.getEtat());
      if (participation.getEvolution() != null) {
        ps.setString(2, participation.getEvolution());
      } else {
        ps.setNull(2, 12);
      }
      ps.setInt(3, participation.getIdParticipation());
      ps.setInt(4, participation.getNumVersion());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next() && participation.getIdParticipation() == rs.getInt(1)) {
          logger.info("participation modifiée");
        } else {
          logger.info("données périmées ou inexistantes");
          throw new OptimisticLockException("Données périmées ou inexistantes");
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("modifierParticipation", exc);
    }
    return participation;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#findParticipationByIdParticipation(int)
   */
  @Override
  public ParticipationDto findParticipationByIdParticipation(int idParticipation) {
    ParticipationDto participationTrouve = bizFactory.getParticipationDto();
    try {
      String nomQuery = "findParticipationByIdParticipation";
      String query = queriesConfig.getValueFor(nomQuery);
      PreparedStatement ps = dalBackendService.prepareStatement(query);
      ps.setInt(1, idParticipation);
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next()) {
          logger.info("pas de participation avec cet id");
        } else {
          participationTrouve.setIdParticipation(rs.getInt(1));
          participationTrouve.setIdJournee(rs.getInt(2));
          participationTrouve.setIdEntreprise(rs.getInt(3));
          participationTrouve.setEtat(rs.getString(4));
          if (rs.getString(5) != null) {
            participationTrouve.setEvolution(rs.getString(5));
          }
          participationTrouve.setAnnulee(rs.getBoolean(6));
          participationTrouve.setNumVersion(rs.getInt(7));
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findParticipationByIdParticipation", exc);
    }
    // TODO Auto-generated method stub
    return participationTrouve;
  }

}
