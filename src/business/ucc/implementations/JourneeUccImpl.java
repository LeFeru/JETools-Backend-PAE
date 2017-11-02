package business.ucc.implementations;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import business.biz.JourneeBiz;
import business.dto.JourneeDto;
import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.ucc.interfaces.JourneeUcc;
import dal.dao.interfaces.JourneeDao;
import dal.dao.interfaces.UtilisateurDao;
import dal.services.DalServices;
import exceptions.BizException;

/**
 * @author rachid.
 *
 */
public class JourneeUccImpl implements JourneeUcc {
  private final Logger logger;
  private JourneeDao journeeDao;
  private DalServices dalServices;
  private UtilisateurDao utilisateurDao;

  /**
   * Renvoie une référence vers une nouvelle instance de JourneeUccImpl.
   * 
   * @author Roman.
   * @throws IOException une IOException.
   * @throws SecurityException une SecurityException.
   */
  public JourneeUccImpl(JourneeDao journeeDao, BizFactory bizFactory, DalServices dalServices,
      UtilisateurDao utilisateurDao) throws SecurityException, IOException {
    super();
    this.journeeDao = journeeDao;
    this.dalServices = dalServices;
    this.utilisateurDao = utilisateurDao;
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

  /**
   * Vérifie et injecte les données d'une JourneeDto dans la base de données.
   * 
   * @param journeeDto la référence de l'instance de journéeDto contenant toutes les informations à
   *        inserer
   * @return une référence vers une instance EntrepriseDto contenant en plus des informations
   *         données en paramètres, l'id renvoyé par la base de données,si non la référence donné en
   *         paramètre
   */
  @Override
  public JourneeDto creerJournee(JourneeDto journeeDto) {
    try {
      ((JourneeBiz) journeeDto).checkJournee(journeeDto);
      dalServices.openConnection();
      JourneeDto temp = journeeDao.findDerniereJournee();
      if (temp.getIdJournee() == -1) {
        logger.info("pas encore de JE");
      } else if (journeeDto.getDateJe().compareTo(temp.getDateJe()) <= 0) {
        logger.info("date invalide");
        dalServices.closeConnection();
        String mess =
            "La date que vous avez rentrée précède ou est égale à celle de la dernière JE.";
        throw new BizException(mess);
      }
      UtilisateurDto retour =
          utilisateurDao.findUtilisateurByPseudo(journeeDto.getIdCreateur().getPseudo());
      if (retour.getId() <= 0) {
        logger.error("Utilisateur inexistant dans la création d'une JE");
        dalServices.closeConnection();
        throw new BizException("Utilisateur inexistant dans la création d'une JE");
      }
      if (temp.getIdJournee() != -1 && temp.isCloturee() == false) {
        dalServices.closeConnection();
        throw new BizException("La journée actuelle n'a pas été cloturée");
      }
      journeeDto.setIdCreateur(retour);
      dalServices.startTransaction();
      journeeDao.creerJournee(journeeDto);
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return journeeDto;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("creerJournee", exception);
    }
    return null;
  }

  /**
   * Fournit la liste de toutes les journées jusqu'à aujourd'hui.
   * 
   * @return une liste d'instance de journées
   */
  @Override
  public ArrayList<JourneeDto> getJournees() {
    try {
      ArrayList<JourneeDto> temp = null;
      dalServices.openConnection();
      temp = journeeDao.findJournees();
      if (temp.isEmpty()) {
        dalServices.closeConnection();
        throw new BizException("Aucune journee en base de donnees.");
      }
      dalServices.closeConnection();
      return temp;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("getJournees", exception);
    }
    return null;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.JourneeUcc#getJourneeCourante()
   */
  @Override
  public JourneeDto getJourneeCourante() {
    try {
      JourneeDto result = null;
      dalServices.openConnection();
      result = journeeDao.findJourneeActive();
      /*
       * if (result.getIdJournee() == -1) { logger.info("pas de journée courante");
       * dalServices.closeConnection(); throw new BizException("pas de journée courante"); }
       */
      dalServices.closeConnection();
      return result;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("getJourneeCourante", exception);
    }
    return null;
  }

  @Override
  public int cloturerJournee(JourneeDto journeeDto) {
    try {
      dalServices.openConnection();
      dalServices.startTransaction();
      JourneeDto temp = journeeDao.findJourneeActive();
      if (temp.getIdJournee() == -1) {
        logger.info("pas de journée active");
        dalServices.closeConnection();
        throw new BizException("Il n'y a pas de journée active");
      }
      int idJournee = journeeDto.getIdJournee();
      if (temp.getIdJournee() != idJournee) {
        logger.info("pas la journée actuelle");
        dalServices.closeConnection();
        throw new BizException("ce n'est pas la journée actuelle");
      }
      if (journeeDao.cloturerJournee(idJournee) < 0) {
        logger.info("journée déjà desactivée");
        dalServices.closeConnection();
        throw new BizException("Jounée déjà désactivée");
      }
      logger.info("journée desactivée");
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return idJournee;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("cloturerJournee", exception);
    }
    // TODO Auto-generated method stub
    return 0;
  }



}
