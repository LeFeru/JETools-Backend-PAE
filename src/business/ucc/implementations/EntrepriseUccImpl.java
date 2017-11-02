package business.ucc.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import business.biz.EntrepriseBiz;
import business.dto.EntrepriseDto;
import business.dto.ParticipationDto;
import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.ucc.interfaces.EntrepriseUcc;
import dal.dao.interfaces.EntrepriseDao;
import dal.dao.interfaces.JourneeDao;
import dal.dao.interfaces.ParticipationDao;
import dal.dao.interfaces.UtilisateurDao;
import dal.services.DalServices;
import exceptions.BizException;

/**
 * Classe Entreprise UccImpl implémentant l'interface d'UtilisateurUcc.
 * 
 * @author Nathan.
 *
 */
public class EntrepriseUccImpl implements EntrepriseUcc {
  private final Logger logger;
  private BizFactory bizFactory;
  private EntrepriseDao entrepriseDao;
  private DalServices dalServices;
  private UtilisateurDao utilisateurDao;
  private ParticipationDao participationDao;
  private JourneeDao journeeDao;

  /**
   * Renvoie une reference vers une nouvelle instance de EntrepriseUccImpl.
   * 
   * @author Nathan.
   */
  public EntrepriseUccImpl(EntrepriseDao entrepriseDao, BizFactory bizFactory,
      DalServices dalServices, UtilisateurDao utilisateurDao, ParticipationDao participationDao,
      JourneeDao journeeDao) {
    super();
    this.bizFactory = bizFactory;
    this.entrepriseDao = entrepriseDao;
    this.dalServices = dalServices;
    this.utilisateurDao = utilisateurDao;
    this.participationDao = participationDao;
    this.journeeDao = journeeDao;
    this.logger = Logger.getLogger("reportsLogger");
  }

  private void tryToRollbackAndCloseAndThrowBizException(String methode, Exception exception) {
    logger.info("Exception lors de l'appel de la methode: " + methode);
    logger.info(exception.getMessage());
    if (!dalServices.hasAlreadyClosedConnection()) {
      if (dalServices.hasOpenTransaction()) {
        try {
          dalServices.rollbackTransaction();
        } catch (Exception exce) {
          logger.info("Exception lors de l'appel de la methode: throwBizException");
          logger.info("Une exception a été rencontrée lors de la tentative de rollback");
          logger.info(exception.getMessage());
        }
      }
      try {
        dalServices.closeConnection();
      } catch (Exception exce) {
        logger.info("Exception lors de l'appel de la methode: throwBizException");
        String mess1 = "Une exception a été rencontrée lors de la tentative de fermeture";
        logger.info(mess1 + " de la connection à la base de données");
        logger.info(exception.getMessage());
      }
    }
    throw new BizException(exception.getMessage());
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#creerEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto creerEntreprise(EntrepriseDto entreprise) {
    try {
      EntrepriseBiz entrepriseBiz = (EntrepriseBiz) bizFactory.getEntrepriseDto();
      logger.info("check EntrepriseDto");
      entrepriseBiz.checkEntreprise(entreprise);
      logger.info("check EntrepriseDto -> ok");
      dalServices.openConnection();
      if (entrepriseDao.findEntrepriseByNomEntreprise(entreprise.getNomEntreprise())
          .getIdEntreprise() <= 0) {
        UtilisateurDto retour =
            utilisateurDao.findUtilisateurByPseudo(entreprise.getCreateur().getPseudo());
        if (retour.getId() > 0) {
          entreprise.setCreateur(retour);
          dalServices.startTransaction();
          entrepriseDao.creerEntreprise(entreprise);
          dalServices.commitTransaction();
          dalServices.closeConnection();
          return entreprise;
        } else {
          dalServices.closeConnection();
          logger.error("utilsiateur inexistant");
          throw new BizException("utilisateur inexistant");
        }

      }
      dalServices.closeConnection();
      logger.error("Entreprise déjà existante");
      throw new BizException("Une entreprise de ce nom existe déjà");
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("creerEntreprise", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#listeEntreprises()
   */
  @Override
  public ArrayList<EntrepriseDto> listeEntreprises() {
    try {
      ArrayList<EntrepriseDto> temp = null;
      dalServices.openConnection();
      temp = entrepriseDao.listeEntreprises();
      dalServices.closeConnection();
      return temp;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("listeEntreprises", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#modifierEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto modifierEntreprise(EntrepriseDto entreprise) {
    try {
      logger.info("checkentreprise en cours");
      ((EntrepriseBiz) entreprise).checkEntreprise(entreprise);
      logger.info("check entreprise -> ok");
      dalServices.openConnection();
      EntrepriseDto temp =
          entrepriseDao.findEntrepriseByNomEntreprise(entreprise.getNomEntreprise());
      if (temp.getIdEntreprise() > 0 && temp.getIdEntreprise() != entreprise.getIdEntreprise()) {
        logger.info("nom d'entreprise déjà pris");
        dalServices.closeConnection();
        throw new BizException("Une entreprise de ce nom existe déjà");
      }
      dalServices.startTransaction();
      entrepriseDao.modifierEntreprise(entreprise);
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return entreprise;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("modifierEntreprise", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#chargerEntreprise(int)
   */
  @Override
  public EntrepriseDto chargerEntreprise(int idEntreprise) {
    try {
      EntrepriseDto temp = null;
      dalServices.openConnection();
      temp = entrepriseDao.findEntrepriseById(idEntreprise);
      if (temp.getIdEntreprise() > 0) {
        dalServices.closeConnection();
        return temp;
      } else {
        dalServices.closeConnection();
        throw new BizException("entreprise inexistante");
      }
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("chargerEntreprise", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#historiqueEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public List<ParticipationDto> historiqueEntreprise(EntrepriseDto entreprise) {
    try {
      List<ParticipationDto> res = null;
      dalServices.openConnection();
      if (entrepriseDao.findEntrepriseById(entreprise.getIdEntreprise()).getIdEntreprise() <= 0) {
        dalServices.closeConnection();
        throw new BizException("entreprise inexistante");
      }
      res = participationDao.findParticipationByIdEntreprise(entreprise.getIdEntreprise());
      for (ParticipationDto participation : res) {
        LocalDate dateTemp = journeeDao.findJourneeById(participation.getIdJournee()).getDateJe();
        participation.setDateJe(dateTemp);
      }
      dalServices.closeConnection();
      return res;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("historiqueEntreprise", exception);
    }
    return null;
  }
}
