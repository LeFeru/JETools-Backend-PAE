package dal.dao.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import business.dto.EntrepriseDto;
import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import dal.dao.interfaces.PersonneContactDao;
import dal.services.DalBackendServices;
import exceptions.OptimisticLockException;
import exceptions.UncheckedSqlException;
import ihm.AppConfig;

public class PersonneContactDaoImpl implements PersonneContactDao {
  private DalBackendServices dalBackendService;
  private BizFactory bizFactory;
  private final Logger logger;
  private AppConfig queriesConfig;

  /**
   * Consructeur PersonneContactDaoImpl.
   * 
   * @param dalBservice dalBackendService.
   * @param bizFact bizzFactory.
   */
  public PersonneContactDaoImpl(DalBackendServices dalBservice, BizFactory bizFact,
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

  /*
   * (non-Javadoc)
   * 
   * @see
   * dal.dao.interfaces.PersonneContactDao#creerPersonneContact(business.dto.PersonneContactDto)
   */
  @Override
  public PersonneContactDto creerPersonneContact(PersonneContactDto personneContactDto) {
    int id = 0;
    try {
      PreparedStatement ps1 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("InsererPersonneContact"));
      ps1.setInt(1, personneContactDto.getIdEntreprise());
      ps1.setString(2, personneContactDto.getNom());
      ps1.setString(3, personneContactDto.getPrenom());
      if (!personneContactDto.getEmail().isEmpty()) {
        ps1.setString(4, personneContactDto.getEmail());
      } else {
        ps1.setNull(4, 12);
      }
      if (!personneContactDto.getTelephone().isEmpty()) {
        ps1.setString(5, personneContactDto.getTelephone());
      } else {
        ps1.setNull(5, 12);
      }
      try (ResultSet rs = ps1.executeQuery()) {
        if (rs.next()) {
          id = rs.getInt(1);
          logger.info("personne de contact crée");
          personneContactDto.setIdPersonne(id);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("creerPersonneContact", exc);
    }
    return personneContactDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#findPersonneContactByMail(java.lang.String)
   */
  @Override
  public PersonneContactDto findPersonneContactByMail(String email) {
    PersonneContactDto personneTrouve = bizFactory.getPersonneContactDto();
    try {
      PreparedStatement ps2 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("FindPersonneByMail"));
      ps2.setString(1, email);
      try (ResultSet rs = ps2.executeQuery()) {
        if (rs.next() && rs.getBoolean(7) == true) {
          personneTrouve.setIdPersonne(rs.getInt(1));
          personneTrouve.setIdEntreprise(rs.getInt(2));
          personneTrouve.setNom(rs.getString(3));
          personneTrouve.setPrenom(rs.getString(4));
          personneTrouve.setEmail(rs.getString(5));
          if (rs.getString(6) != null) {
            personneTrouve.setTelephone(rs.getString(6));
          }
          personneTrouve.setActif(rs.getBoolean(7));
          logger.info("personne trouvée");
        } else {
          logger.info("Aucun res");
          personneTrouve.setIdPersonne(-1);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findPersonneContactByMail", exc);
    }
    return personneTrouve;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#findPersonneContactByTelephone(java.lang.String)
   */
  @Override
  public PersonneContactDto findPersonneContactByTelephone(String telephone) {
    PersonneContactDto personneTrouve = bizFactory.getPersonneContactDto();
    try {
      PreparedStatement ps4 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("FindPersonneByTelephone"));
      ps4.setString(1, telephone);
      try (ResultSet rs = ps4.executeQuery()) {
        if (rs.next() && rs.getBoolean(7) == true) {
          personneTrouve.setIdPersonne(rs.getInt(1));
          personneTrouve.setIdEntreprise(rs.getInt(2));
          personneTrouve.setNom(rs.getString(3));
          personneTrouve.setPrenom(rs.getString(4));
          if (rs.getString(5) != null) {
            personneTrouve.setEmail(rs.getString(5));
          }
          personneTrouve.setTelephone(rs.getString(6));
          personneTrouve.setActif(rs.getBoolean(7));
          logger.info("personne trouvée");
        } else {
          logger.info("Aucun res");
          personneTrouve.setIdPersonne(-1);
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findPersonneContactByTelephone", exc);
    }
    return personneTrouve;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#listeContacts()
   */
  @Override
  public ArrayList<PersonneContactDto> listeContacts() {
    ArrayList<PersonneContactDto> liste = new ArrayList<>();
    try {
      PreparedStatement ps3 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("affichageContacts"));
      try (ResultSet rs = ps3.executeQuery()) {
        if (!rs.next()) {
          logger.info("pas encore de personnes de contact");
        } else {
          do {
            PersonneContactDto personne = bizFactory.getPersonneContactDto();
            personne.setIdPersonne(rs.getInt(1));
            personne.setIdEntreprise(rs.getInt(2));
            personne.setNom(rs.getString(3));
            personne.setPrenom(rs.getString(4));
            personne.setEmail(rs.getString(5));
            personne.setTelephone(rs.getString(6));
            personne.setNumVersion(rs.getInt(8));
            liste.add(personne);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("listeContacts", exc);
    }
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * dal.dao.interfaces.PersonneContactDao#modifierPersonneContact(business.dto.PersonneContactDto)
   */
  @Override
  public PersonneContactDto modifierPersonneContact(PersonneContactDto personne) {
    try {
      PreparedStatement ps4 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("modifierPersonneContact"));
      ps4.setString(1, personne.getNom());
      ps4.setString(2, personne.getPrenom());
      if (!personne.getEmail().isEmpty()) {
        ps4.setString(3, personne.getEmail());
      } else {
        ps4.setNull(3, 12);
      }
      if (!personne.getTelephone().isEmpty()) {
        ps4.setString(4, personne.getTelephone());
      } else {
        ps4.setNull(4, 12);
      }
      ps4.setInt(5, personne.getIdPersonne());
      ps4.setInt(6, personne.getNumVersion());
      ResultSet rs = ps4.executeQuery();
      if (rs.next() && personne.getIdPersonne() == rs.getInt(1)) {
        logger.info("personne modifiée");
      } else {
        logger.info("Données périmées ou inexistantes");
        throw new OptimisticLockException("Données périmées ou inexistantes");
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("modifierPersonneContact", exc);
    }
    return personne;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#findPersonneById(int)
   */
  @Override
  public PersonneContactDto findPersonneById(int idPersonne) {
    PersonneContactDto personneTrouve = bizFactory.getPersonneContactDto();
    try {
      PreparedStatement ps2 =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("findPersonneById"));
      ps2.setInt(1, idPersonne);
      try (ResultSet rs = ps2.executeQuery()) {
        if (rs.next()) {
          personneTrouve.setIdPersonne(rs.getInt(1));
          personneTrouve.setIdEntreprise(rs.getInt(2));
          personneTrouve.setNom(rs.getString(3));
          personneTrouve.setPrenom(rs.getString(4));
          if (rs.getString(5) != null) {
            personneTrouve.setEmail(rs.getString(5));
          }
          if (rs.getString(6) != null) {
            personneTrouve.setTelephone(rs.getString(6));
          }
          personneTrouve.setNumVersion(rs.getInt(8));
          logger.info("personne trouvée");
        } else {
          logger.info("aucun res");
          personneTrouve.setIdPersonne(-1);
        }
      }
    } catch (SQLException exc) {
      logger.info("echec findPersonneById");
      throwUncheckedSqlException("findPersonneById", exc);
    }
    return personneTrouve;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#DesactiverPersonne(business.dto.PersonneContactDto)
   */
  @Override
  public int desactiverPersonne(PersonneContactDto personne) {
    try {
      PreparedStatement pst =
          dalBackendService.prepareStatement(queriesConfig.getValueFor("desactiverPersonne"));
      pst.setInt(1, personne.getIdPersonne());
      pst.setInt(2, personne.getNumVersion());
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          logger.info("personne desactivée");
          return personne.getIdPersonne();
        }
        logger.info("personne déjà desactivée");
        throw new OptimisticLockException("Données périmées ou inexistantes");
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("desactiverPersonne", exc);
    }
    return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#findPersonneByEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public List<PersonneContactDto> findPersonneByIdEntreprise(EntrepriseDto entrepriseDto) {
    List<PersonneContactDto> listPersonnes = new ArrayList<>();
    try {
      PreparedStatement ps3 = dalBackendService
          .prepareStatement(queriesConfig.getValueFor("findPersonneByIdEntreprise"));
      ps3.setInt(1, entrepriseDto.getIdEntreprise());
      try (ResultSet rs = ps3.executeQuery()) {
        if (!rs.next()) {
          logger.error("aucune personne de contact trouvees");
        } else {
          do {
            PersonneContactDto personneContactDto = bizFactory.getPersonneContactDto();
            personneContactDto.setIdPersonne(rs.getInt(1));
            personneContactDto.setIdEntreprise(rs.getInt(2));
            personneContactDto.setNom(rs.getString(3));
            personneContactDto.setPrenom(rs.getString(4));
            if (rs.getString(5) != null) {
              personneContactDto.setEmail(rs.getString(5));
            }
            if (rs.getString(6) != null) {
              personneContactDto.setTelephone(rs.getString(6));
            }
            personneContactDto.setNomEntreprise(entrepriseDto.getNomEntreprise());
            listPersonnes.add(personneContactDto);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findPersonneByIdEntreprise", exc);
    }
    return listPersonnes;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * dal.dao.interfaces.PersonneContactDao#findPersonneByIdEntrepriseNonDesactivees(business.dto.
   * EntrepriseDto)
   */
  @Override
  public List<PersonneContactDto> findPersonneByIdEntrepriseNonDesactivees(
      EntrepriseDto entrepriseDto) {
    List<PersonneContactDto> listPersonnes = new ArrayList<>();
    try {
      String query = queriesConfig.getValueFor("findPersonneByIdEntrepriseNonDesactivees");
      PreparedStatement ps3 = dalBackendService.prepareStatement(query);
      ps3.setInt(1, entrepriseDto.getIdEntreprise());
      try (ResultSet rs = ps3.executeQuery()) {
        if (!rs.next()) {
          logger.error("aucune personne de contact trouvees");
        } else {
          do {
            PersonneContactDto personneContactDto = bizFactory.getPersonneContactDto();
            personneContactDto.setIdPersonne(rs.getInt(1));
            personneContactDto.setIdEntreprise(rs.getInt(2));
            personneContactDto.setNom(rs.getString(3));
            personneContactDto.setPrenom(rs.getString(4));
            if (rs.getString(5) != null) {
              personneContactDto.setEmail(rs.getString(5));
            }
            if (rs.getString(6) != null) {
              personneContactDto.setTelephone(rs.getString(6));
            }
            personneContactDto.setNomEntreprise(entrepriseDto.getNomEntreprise());
            listPersonnes.add(personneContactDto);
          } while (rs.next());
        }
      }
    } catch (SQLException exc) {
      throwUncheckedSqlException("findPersonneByIdEntrepriseNonDesactivees", exc);
    }
    return listPersonnes;
  }
}
