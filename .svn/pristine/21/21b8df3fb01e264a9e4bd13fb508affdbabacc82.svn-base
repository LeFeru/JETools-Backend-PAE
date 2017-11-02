package business.ucc.implementations;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import business.dto.EntrepriseDto;
import business.dto.JourneeDto;
import business.dto.ParticipationDto;
import business.dto.PersonneContactDto;
import business.dto.PresenceDto;
import business.factories.BizFactory;
import business.ucc.interfaces.PresenceUcc;
import dal.dao.interfaces.EntrepriseDao;
import dal.dao.interfaces.JourneeDao;
import dal.dao.interfaces.ParticipationDao;
import dal.dao.interfaces.PersonneContactDao;
import dal.dao.interfaces.PresenceDao;
import dal.services.DalServices;
import exceptions.BizException;

/**
 * @author skubi.
 *
 */
public class PresenceUccImpl implements PresenceUcc {
  private final Logger logger;
  private PresenceDao presenceDao;
  private BizFactory bizFactory;
  private DalServices dalServices;
  private EntrepriseDao entrepriseDao;
  private JourneeDao journeeDao;
  private PersonneContactDao personneContactDao;
  private ParticipationDao participationDao;

  /**
   * Renvoie une référence vers une nouvelle instance de PresenceUccImpl.
   * 
   * @param participationDao.
   * @param bizFactory.
   * @param dalServices.
   * @param entrepriseDao.
   */
  public PresenceUccImpl(PresenceDao presenceDao, BizFactory bizFactory, DalServices dalServices,
      EntrepriseDao entrepriseDao, JourneeDao journeeDao, PersonneContactDao personneContactDao,
      ParticipationDao participationDao) {
    this.presenceDao = presenceDao;
    this.bizFactory = bizFactory;
    this.dalServices = dalServices;
    this.entrepriseDao = entrepriseDao;
    this.journeeDao = journeeDao;
    this.personneContactDao = personneContactDao;
    this.participationDao = participationDao;
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
        String mess1 = "Une exception a été rencontrée lors de la tentative de fermeture de la ";
        String mess2 = "connection à la base de données";
        logger.info(mess1 + mess2);
        logger.info(exception.getMessage());
      }
    }
    throw new BizException(exception.getMessage());
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.PresenceUcc#indiquerPresence(business.dto.EntrepriseDto,
   * java.util.List)
   */
  @Override
  public List<PresenceDto> indiquerPresence(EntrepriseDto entrepriseDto, JourneeDto journee,
      List<PersonneContactDto> personneContactDtos) {
    try {
      dalServices.openConnection();
      JourneeDto journeeDto = journeeDao.findJourneeById(journee.getIdJournee());
      if (journeeDto.getIdJournee() < 1 || journeeDto.isCloturee()) {
        dalServices.closeConnection();
        throw new BizException("Journée d'entreprise inexistante ou cloturée");
      }
      entrepriseDto = entrepriseDao.findEntrepriseByNomEntreprise(entrepriseDto.getNomEntreprise());
      if (entrepriseDto.getIdEntreprise() < 1) {
        dalServices.closeConnection();
        throw new BizException(
            "L'entreprise " + entrepriseDto.getNomEntreprise() + " n'existe pas");
      }
      ParticipationDto participationDto = bizFactory.getParticipationDto();
      participationDto.setIdEntreprise(entrepriseDto.getIdEntreprise());
      participationDto.setIdJournee(journeeDto.getIdJournee());
      participationDto =
          participationDao.findParticipationByIdJourneeIdEntreprise(participationDto);
      if (participationDto.getIdParticipation() < 1 || participationDto.isAnnulee()) {
        dalServices.closeConnection();
        String nomEntreprise = entrepriseDto.getNomEntreprise();
        throw new BizException(
            "L'entreprise " + nomEntreprise + " ne participe pas à la JE active");
      }
      if ("refusee".equals(participationDto.getEtat())) {
        dalServices.closeConnection();
        String nomEntreprise = entrepriseDto.getNomEntreprise();
        throw new BizException(
            "L'entreprise " + nomEntreprise + " a refusé de participer à la JE active");
      }
      PresenceDto presenceDto = bizFactory.getPresenceDto();
      presenceDto.setIdParticipation(participationDto.getIdParticipation());
      List<PresenceDto> res = new ArrayList<>();
      dalServices.startTransaction();
      for (PersonneContactDto personneContactDto : personneContactDtos) {
        personneContactDto =
            personneContactDao.findPersonneById(personneContactDto.getIdPersonne());
        if (personneContactDto.getIdEntreprise() != entrepriseDto.getIdEntreprise()) {
          logger.error("Personne de contact n'appartenant pas a l'entreprise");
          // dalServices.rollbackTransaction();
          // dalServices.closeConnection();
          throw new BizException("Personne de contact n'appartenant pas a l'entreprise");
        }
        presenceDto.setIdPersonne(personneContactDto.getIdPersonne());
        PresenceDto presenceDto2 = presenceDao.findPresenceByIdParticipationIdPersonne(presenceDto);
        if (presenceDto2.getIdParticipation() < 1) {
          presenceDao.insererPresence(presenceDto);
          res.add(presenceDto);
        } else {
          logger.info("Presence deja en memoire");
        }
      }
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return res;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("indiquerPresence", exception);
    }
    return null;
  }


  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.PresenceUcc#listerContactsParEntreprise(business.dto.EntrepriseDto,
   * business.dto.JourneeDto)
   */
  @Override
  public List<PersonneContactDto> listerContactsParEntreprise(EntrepriseDto entrepriseDto,
      JourneeDto journeeDto) {
    try {
      dalServices.openConnection();
      entrepriseDto = entrepriseDao.findEntrepriseByNomEntreprise(entrepriseDto.getNomEntreprise());
      if (entrepriseDto.getIdEntreprise() < 1) {
        dalServices.closeConnection();
        throw new BizException(
            "L'entreprise " + entrepriseDto.getNomEntreprise() + " n'existe pas");
      }
      journeeDto = journeeDao.findJourneeById(journeeDto.getIdJournee());
      if (journeeDto.getIdJournee() < 1) {
        dalServices.closeConnection();
        throw new BizException("La JE donnée n'existe pas");
      }
      ParticipationDto participationDto = bizFactory.getParticipationDto();
      participationDto.setIdEntreprise(entrepriseDto.getIdEntreprise());
      participationDto.setIdJournee(journeeDto.getIdJournee());
      participationDto =
          participationDao.findParticipationByIdJourneeIdEntreprise(participationDto);
      if (participationDto.getIdParticipation() < 1) {
        dalServices.closeConnection();
        String nomEntreprise = entrepriseDto.getNomEntreprise();
        throw new BizException(
            "L'entreprise " + nomEntreprise + " ne participe pas à la JE active");
      }
      List<PersonneContactDto> personnesContact;
      if (journeeDto.isCloturee()) {
        personnesContact =
            personneContactDao.findPersonneByIdEntrepriseNonDesactivees(entrepriseDto);
      } else {
        personnesContact = personneContactDao.findPersonneByIdEntreprise(entrepriseDto);
      }
      if (personnesContact.isEmpty()) {
        logger.info("Aucune personne de contact");
      }
      PresenceDto presenceDto = bizFactory.getPresenceDto();
      for (PersonneContactDto personneContactDto : personnesContact) {
        presenceDto.setIdParticipation(participationDto.getIdParticipation());
        presenceDto.setIdPersonne(personneContactDto.getIdPersonne());
        presenceDto = presenceDao.findPresenceByIdParticipationIdPersonne(presenceDto);
        if (presenceDto.getIdParticipation() > 0 && presenceDto.getIdPersonne() > 0) {
          personneContactDto.setPresent(true);
        }
      }
      dalServices.closeConnection();
      return personnesContact;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("listerContactsParEntreprise", exception);
    }
    return null;
  }
}
