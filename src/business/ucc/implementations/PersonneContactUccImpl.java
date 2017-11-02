package business.ucc.implementations;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import business.biz.PersonneContactBiz;
import business.dto.EntrepriseDto;
import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.ucc.interfaces.PersonneContactUcc;
import dal.dao.interfaces.EntrepriseDao;
import dal.dao.interfaces.PersonneContactDao;
import dal.services.DalServices;
import exceptions.BizException;

/**
 * Classe PersonneContactUccImpl implémentant l'interface de PersonneContactUcc.
 * 
 * @author skubi.
 *
 */
public class PersonneContactUccImpl implements PersonneContactUcc {
  private final Logger logger;
  private BizFactory bizFactory;
  private PersonneContactDao personneContactDao;
  private DalServices dalServices;
  private EntrepriseDao entrepriseDao;

  /**
   * Renvoie une reference vers une nouvelle instance de PersonneContactUccImpl.
   * 
   * @author skubi.
   */
  public PersonneContactUccImpl(PersonneContactDao personneContactDao, BizFactory bizFactory,
      DalServices dalServices, EntrepriseDao entrepriseDao) {
    super();
    this.bizFactory = bizFactory;
    this.personneContactDao = personneContactDao;
    this.dalServices = dalServices;
    this.entrepriseDao = entrepriseDao;
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
   * @see business.ucc.interfaces.PersonneContactUcc#creerPersonneContact(business.dto.
   * PersonneContactDto)
   */
  @Override
  public PersonneContactDto creerPersonneContact(PersonneContactDto personneContactDto) {
    try {
      logger.info("check PersonneContactDto");
      ((PersonneContactBiz) personneContactDto).checkPersonneContact(personneContactDto);
      logger.info("check PersonneContactDto -> ok");
      dalServices.openConnection();
      int idEntreprise = entrepriseDao
          .findEntrepriseByNomEntreprise(personneContactDto.getNomEntreprise()).getIdEntreprise();
      if (idEntreprise < 1) {
        dalServices.closeConnection();
        throw new BizException("Entreprise n'existe pas");
      }
      personneContactDto.setIdEntreprise(idEntreprise);
      PersonneContactDto tempEmail = bizFactory.getPersonneContactDto();
      PersonneContactDto tempTelephone = bizFactory.getPersonneContactDto();
      if ((personneContactDto.getEmail() != null || !personneContactDto.getEmail().isEmpty())) {
        tempEmail = personneContactDao.findPersonneContactByMail(personneContactDto.getEmail());
      }
      if ((personneContactDto.getTelephone() != null
          || !personneContactDto.getTelephone().isEmpty())) {
        tempTelephone =
            personneContactDao.findPersonneContactByTelephone(personneContactDto.getTelephone());
      }
      if (tempEmail.getIdPersonne() > 0) {
        dalServices.closeConnection();
        throw new BizException("Ce mail est deja utilisé");
      }
      if (tempTelephone.getIdPersonne() > 0) {
        dalServices.closeConnection();
        throw new BizException("Ce telephone est deja utilise");
      }
      dalServices.startTransaction();
      personneContactDto = personneContactDao.creerPersonneContact(personneContactDto);
      logger.info("Personne " + personneContactDto);
      logger.info("Personne de contact cree");
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return personneContactDto;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("creerPersonneContact", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.PersonneContactUcc#listeContacts()
   */
  @Override
  public ArrayList<PersonneContactDto> listeContacts() {
    try {
      ArrayList<PersonneContactDto> temp = null;
      dalServices.openConnection();
      temp = personneContactDao.listeContacts();
      for (PersonneContactDto personne : temp) {
        EntrepriseDto entreprise = entrepriseDao.findEntrepriseById(personne.getIdEntreprise());
        personne.setNomEntreprise(entreprise.getNomEntreprise());
      }
      dalServices.closeConnection();
      return temp;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("listeContacts", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.PersonneContactUcc#chargerContact(int)
   */
  @Override
  public PersonneContactDto chargerContact(int idPersonne) {
    try {
      PersonneContactDto temp = null;
      dalServices.openConnection();
      temp = personneContactDao.findPersonneById(idPersonne);
      System.out.println(temp.getIdPersonne());
      logger.info(temp.getIdPersonne());
      if (temp.getIdPersonne() < 1) {
        dalServices.closeConnection();
        throw new BizException("La personne de contact n'existe plus");
      }
      dalServices.closeConnection();
      return temp;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("chargerContact", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.PersonneContactUcc#modifierContact(business.dto.PersonneContactDto)
   */
  @Override
  public PersonneContactDto modifierContact(PersonneContactDto personne) {
    try {
      logger.info("check personne en cours");
      logger.info(personne.toString());
      ((PersonneContactBiz) personne).checkPersonneContact(personne);
      logger.info("check personne -> ok");
      dalServices.openConnection();
      // PersonneContactDto temp = personneContactDao.findPersonneById(personne.getIdPersonne());
      PersonneContactDto tempEmail = bizFactory.getPersonneContactDto();
      PersonneContactDto tempTelephone = bizFactory.getPersonneContactDto();
      if ((personne.getEmail() != null || !personne.getEmail().isEmpty())) {
        tempEmail = personneContactDao.findPersonneContactByMail(personne.getEmail());
      }
      if ((personne.getTelephone() != null || !personne.getTelephone().isEmpty())) {
        tempTelephone = personneContactDao.findPersonneContactByTelephone(personne.getTelephone());
      }
      if (tempEmail.getIdPersonne() > 0 && tempEmail.getIdPersonne() != personne.getIdPersonne()) {
        dalServices.closeConnection();
        throw new BizException("Ce mail est deja utilisé");
      }
      if (tempTelephone.getIdPersonne() > 0
          && tempTelephone.getIdPersonne() != personne.getIdPersonne()) {
        dalServices.closeConnection();
        throw new BizException("Ce téléphone est deja utilisé");
      }
      dalServices.startTransaction();
      personne = personneContactDao.modifierPersonneContact(personne);
      logger.info("Personne " + personne);
      logger.info("Personne de contact modifiee");
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return personne;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("modifierContact", exception);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.PersonneContactUcc#desactiverContact(int)
   */
  @Override
  public int desactiverContact(PersonneContactDto personneContactDto) {
    try {
      // int idPersonne = personneContactDto.getIdPersonne();
      dalServices.openConnection();
      dalServices.startTransaction();
      logger.info(personneContactDto);
      if (personneContactDao.desactiverPersonne(personneContactDto) < 0) {
        logger.info("personne déjà desactivée");
        // dalServices.rollbackTransaction();
        // dalServices.closeConnection();
        throw new BizException("Personne de contact deja inactive");
      }
      logger.info("personne désactivée");
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return personneContactDto.getIdPersonne();
    } catch (Exception exception) {
      // dalServices.rollbackTransaction();
      tryToRollbackAndCloseAndThrowBizException("desactiverContact", exception);
    }
    return 0;
  }
}
