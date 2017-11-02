package business.ucc.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import business.biz.ParticipationBiz;
import business.dto.EntrepriseDto;
import business.dto.JourneeDto;
import business.dto.ParticipationDto;
import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.ucc.interfaces.ParticipationUcc;
import dal.dao.interfaces.EntrepriseDao;
import dal.dao.interfaces.JourneeDao;
import dal.dao.interfaces.ParticipationDao;
import dal.dao.interfaces.PersonneContactDao;
import dal.services.DalServices;
import exceptions.BizException;
import exceptions.InvalidParticipationDtoException;

public class ParticipationUccImpl implements ParticipationUcc {
  private final Logger logger;
  private ParticipationDao participationDao;
  private BizFactory bizFactory;
  private DalServices dalServices;
  private EntrepriseDao entrepriseDao;
  private JourneeDao journeeDao;
  private PersonneContactDao personneContactDao;

  /**
   * Renvoie une référence vers une nouvelle instance de ParticipationUccImpl.
   * 
   * @param participationDao.
   * @param bizFactory.
   * @param dalServices.
   * @param entrepriseDao.
   */
  public ParticipationUccImpl(ParticipationDao participationDao, BizFactory bizFactory,
      DalServices dalServices, EntrepriseDao entrepriseDao, JourneeDao journeeDao,
      PersonneContactDao personneContactDao) {
    this.participationDao = participationDao;
    this.bizFactory = bizFactory;
    this.dalServices = dalServices;
    this.entrepriseDao = entrepriseDao;
    this.journeeDao = journeeDao;
    this.personneContactDao = personneContactDao;
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
          logger.info(
              "Exception lors de l'appel de la methode: tryToRollbackAndCloseAndThrowBizException");
          logger.info("Une exception a été rencontrée lors de la tentative de rollback");
          logger.info(exception.getMessage());
        }
      }
      try {
        dalServices.closeConnection();
      } catch (Exception exce) {
        logger.info(
            "Exception lors de l'appel de la methode: tryToRollbackAndCloseAndThrowBizException");
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
   * @see business.ucc.interfaces.ParticipationUcc#inviter(java.util.List)
   */
  @Override
  public List<PersonneContactDto> inviter(List<EntrepriseDto> entrepriseDtos) {
    try {
      ParticipationDto participationDto = bizFactory.getParticipationDto();
      List<PersonneContactDto> res = null;
      dalServices.openConnection();
      JourneeDto journeeDto = journeeDao.findJourneeActive();
      if (journeeDto.getIdJournee() < 1) {
        dalServices.closeConnection();
        throw new BizException("Aucune journée d'entreprise non cloturée");
      }
      res = new ArrayList<>();
      dalServices.startTransaction();
      for (EntrepriseDto entrepriseDto : entrepriseDtos) {
        try {
          String nomEntreprise = entrepriseDto.getNomEntreprise();
          entrepriseDto = entrepriseDao.findEntrepriseByNomEntreprise(nomEntreprise);
          if (entrepriseDto.getIdEntreprise() < 1) {
            throw new BizException("Entreprise " + nomEntreprise + " inexistante");
          }
          logger.info("entrepriseDto " + entrepriseDto);
          participationDto.setIdEntreprise(entrepriseDto.getIdEntreprise());
          participationDto.setIdJournee(journeeDto.getIdJournee());
          participationDto =
              participationDao.findParticipationByIdJourneeIdEntreprise(participationDto);
          if (participationDto.getIdParticipation() > 0) {
            logger.info("L'entreprise " + entrepriseDto.getNomEntreprise()
                + " est déjà invitée pour cette JE" + " participation :"
                + participationDto.getIdParticipation());
          } else {
            participationDto = participationDao.insererParticipation(participationDto);
            logger.info(participationDto);
          }
          List<PersonneContactDto> listPersonnes =
              personneContactDao.findPersonneByIdEntreprise(entrepriseDto);
          if (listPersonnes.isEmpty()) {
            // dalServices.rollbackTransaction();
            // dalServices.closeConnection();
            String messErreur = "Une entreprise sans personne de contact ne peut être invitée.";
            throw new BizException(messErreur);
          }
          res.addAll(listPersonnes);
        } catch (InvalidParticipationDtoException invalidParticipationDtoException) {
          logger.error(invalidParticipationDtoException.getMessage());
        }
      }
      if (res.isEmpty()) {
        dalServices.rollbackTransaction();
        dalServices.closeConnection();
        String mess2 = "Aucune entreprise contenant des personnes de contact n'a été sélectionnée";
        throw new BizException(mess2);
      }
      dalServices.commitTransaction();
      dalServices.closeConnection();
      logger.info("participations créées");
      return res;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("inviter", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#listeSelectionEntreprises()
   */
  @Override
  public List<EntrepriseDto> listeSelectionEntreprises() {
    try {
      List<EntrepriseDto> temp = null;
      dalServices.openConnection();
      JourneeDto journeeDto = journeeDao.findJourneeActive();
      if (journeeDto.getIdJournee() < 1) {
        dalServices.closeConnection();
        throw new BizException("Aucune journee d'entreprise non cloturée");
      }
      List<EntrepriseDto> res = entrepriseDao.getSelectionEntreprises();
      temp = new ArrayList<>();
      ParticipationDto participationDto = bizFactory.getParticipationDto();
      participationDto.setIdJournee(journeeDto.getIdJournee());
      for (EntrepriseDto entreprise : res) {
        participationDto.setIdEntreprise(entreprise.getIdEntreprise());
        participationDto =
            participationDao.findParticipationByIdJourneeIdEntreprise(participationDto);
        if (participationDto.getIdParticipation() < 1) {
          temp.add(entreprise);
        }
      }
      dalServices.closeConnection();
      return temp;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("listeSelectionEntreprises", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#listeEntrepriseNonSelectionnees()
   */
  @Override
  public List<EntrepriseDto> listeEntrepriseNonSelectionnees() {
    try {
      List<EntrepriseDto> temp = this.listeSelectionEntreprises();
      dalServices.openConnection();
      logger.info(temp);
      JourneeDto journeeTemp = journeeDao.findJourneeActive();
      if (journeeTemp.getIdJournee() == -1) {
        logger.info("pas de journee active");
        dalServices.closeConnection();
        throw new BizException("pas de journée active");
      }
      List<EntrepriseDto> res;
      res = entrepriseDao.entreprisesNonSelectionnes(journeeTemp.getIdJournee());
      logger.info(res);
      for (EntrepriseDto entreprise : temp) {
        res.remove(entreprise);
      }
      dalServices.closeConnection();
      logger.info("res" + res);
      return res;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("listeEntrepriseNonSelectionnees", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#entrepriseInvitees(int)
   */
  @Override
  public List<EntrepriseDto> entrepriseInvitees(int idJournee) {
    try {
      List<EntrepriseDto> res = null;
      dalServices.openConnection();
      res = entrepriseDao.entreprisesInvitees(idJournee);
      dalServices.closeConnection();
      return res;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("entrepriseInvitees", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.ParticipationUcc#annulerParticipation(business.dto.ParticipationDto)
   */
  @Override
  public ParticipationDto annulerParticipation(ParticipationDto participation) {
    try {
      dalServices.openConnection();
      dalServices.startTransaction();
      JourneeDto temp = journeeDao.findJourneeActive();
      if (temp.getIdJournee() != participation.getIdJournee()) {
        logger.info("erreur : journée déjà finie");
        dalServices.closeConnection();
        throw new BizException("Cette journée est terminée");
      }
      int idPart = participation.getIdParticipation();
      ParticipationDto tempParticipation =
          participationDao.findParticipationByIdParticipation(idPart);
      if (tempParticipation.getEtat().equals("invitee")
          || tempParticipation.getEtat().equals("refusee")) {
        dalServices.closeConnection();
        throw new BizException("Vous ne pouvez pas encore annuler cette participation");
      }
      participationDao.annulerParticipation(participation);
      logger.info("participation annulée");
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return participation;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("annulerParticipation", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#participationsNonAnnulees(business.dto.
   * ParticipationDto)
   */
  @Override
  public List<ParticipationDto> participationsNonAnnulees(ParticipationDto participation) {
    List<ParticipationDto> liste = null;
    try {
      dalServices.openConnection();
      liste = participationDao.participationsNonAnnulees(participation.getIdJournee());
      for (ParticipationDto temp : liste) {
        String nom = entrepriseDao.findEntrepriseById(temp.getIdEntreprise()).getNomEntreprise();
        temp.setNomEntreprise(nom);
      }
      dalServices.closeConnection();
      return liste;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("participationsNonAnnulees", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.ParticipationUcc#modifierParticipation(business.dto.ParticipationDto)
   */
  @Override
  public ParticipationDto modifierParticipation(ParticipationDto participation) {
    try {
      ParticipationBiz participationBiz = (ParticipationBiz) bizFactory.getParticipationDto();
      logger.info("checkParticipation en cours");
      participationBiz.checkParticipation(participation);
      logger.info("check participation ok");
      dalServices.openConnection();
      JourneeDto tempJournee = journeeDao.findJourneeActive();
      if (tempJournee.getIdJournee() != participation.getIdJournee()) {
        logger.info("erreur : journée déjà finie");
        dalServices.closeConnection();
        throw new BizException("Cette journée est terminée");
      }
      EntrepriseDto tempEntreprise =
          entrepriseDao.findEntrepriseByNomEntreprise(participation.getNomEntreprise());
      if (tempEntreprise.getIdEntreprise() < 1) {
        logger.info("erreur: cette entreprise n'existe plus");
        dalServices.closeConnection();
        throw new BizException("cette entreprise n'existe plus");
      }
      ParticipationDto temp =
          participationDao.findParticipationByIdParticipation(participation.getIdParticipation());
      if (temp.isAnnulee()) {
        dalServices.closeConnection();
        throw new BizException("cette participation est annulée");
      }
      participationBiz.checkEtats(temp, participation);
      dalServices.startTransaction();
      participation = participationDao.modifierParticipation(participation);
      if (participation.getEvolution() != null && participation.getEvolution().equals("payee")) {
        EntrepriseDto entreprise =
            entrepriseDao.findEntrepriseByNomEntreprise(participation.getNomEntreprise());
        LocalDate datePaye = LocalDate.now();
        entreprise.setDateDerniereParticipationPayee(datePaye);
        entrepriseDao.modifierDatePaye(entreprise);
      }
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return participation;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("modifierParticipation", exception);
    }
    return null;
  }
}
