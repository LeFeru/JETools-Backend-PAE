package dal.dao.stubs;

import java.util.ArrayList;
import java.util.List;

import business.dto.EntrepriseDto;
import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import dal.dao.interfaces.PersonneContactDao;
import exceptions.OptimisticLockException;
import exceptions.UncheckedSqlException;

/**
 * @author rachid.
 *
 */
public class PersonneContactDaoStub implements PersonneContactDao {
  private boolean throwSqlUncheckedException;
  private BizFactoryStub bizFactoryStub;
  private BizFactory bizFactory;

  /**
   * Constructeur PersonneContactDaoStub.
   */
  public PersonneContactDaoStub() {
    this.throwSqlUncheckedException = false;
    this.bizFactoryStub = new BizFactoryStub();
    this.bizFactory = new BizFactoryImpl();
  }

  public void setThrowSqlUncheckedException(boolean throwSqlUncheckedException) {
    this.throwSqlUncheckedException = throwSqlUncheckedException;
  }

  private void tryToThrowSqlUncheckedException(String message) {
    if (throwSqlUncheckedException) {
      throw new UncheckedSqlException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * dal.dao.interfaces.PersonneContactDao#creerPersonneContact(business.dto.PersonneContactDto)
   */
  @Override
  public PersonneContactDto creerPersonneContact(PersonneContactDto personneContactDto) {
    tryToThrowSqlUncheckedException("Echec creerPersonneContact");
    personneContactDto.setIdPersonne(1);
    return personneContactDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#findPersonneContactByMail(java.lang.String)
   */
  @Override
  public PersonneContactDto findPersonneContactByMail(String email) {
    tryToThrowSqlUncheckedException("Echec findPersonneContactByMail");
    PersonneContactDto personneContactDto = this.bizFactoryStub.getPersonneContactDto();
    if (!personneContactDto.getEmail().equals(email)) {
      personneContactDto.setIdPersonne(0);
      personneContactDto = bizFactory.getPersonneContactDto();
    }
    personneContactDto.setEmail(email);
    return personneContactDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#listeContacts()
   */
  @Override
  public ArrayList<PersonneContactDto> listeContacts() {
    tryToThrowSqlUncheckedException("Echec listeContacts");
    ArrayList<PersonneContactDto> liste = new ArrayList<PersonneContactDto>();
    for (int i = 1; i < 11; i++) {
      PersonneContactDto personneContactDto = this.bizFactoryStub.getPersonneContactDto();
      personneContactDto.setIdPersonne(i);
      liste.add(personneContactDto);
    }
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#findPersonneContactByTelephone(java.lang.String)
   */
  @Override
  public PersonneContactDto findPersonneContactByTelephone(String telephone) {
    tryToThrowSqlUncheckedException("Echec findPersonneContactByTelephone");
    PersonneContactDto personneContactDto = this.bizFactoryStub.getPersonneContactDto();
    if (!personneContactDto.getTelephone().equals(telephone)) {
      personneContactDto.setIdPersonne(0);
      personneContactDto = bizFactory.getPersonneContactDto();
    }
    personneContactDto.setTelephone(telephone);
    return personneContactDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * dal.dao.interfaces.PersonneContactDao#modifierPersonneContact(business.dto.PersonneContactDto)
   */
  @Override
  public PersonneContactDto modifierPersonneContact(PersonneContactDto personne) {
    tryToThrowSqlUncheckedException("Echec modifierPersonneContact");
    return personne;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#findPersonneById(int)
   */
  @Override
  public PersonneContactDto findPersonneById(int idPersonne) {
    tryToThrowSqlUncheckedException("Echec findPersonneById");
    PersonneContactDto personneContactDto = this.bizFactoryStub.getPersonneContactDto();
    if (personneContactDto.getIdPersonne() != idPersonne) {
      personneContactDto.setIdPersonne(0);
      personneContactDto = bizFactory.getPersonneContactDto();
    }
    System.out.println(personneContactDto);
    return personneContactDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PersonneContactDao#desactiverPersonne(int)
   */
  @Override
  public int desactiverPersonne(PersonneContactDto personne) {
    tryToThrowSqlUncheckedException("Echec desactiverPersonne");
    if (personne.getIdPersonne() != bizFactoryStub.getPersonneContactDto().getIdPersonne()) {
      throw new OptimisticLockException("données périmées ou inexistantes");
    }
    return personne.getIdPersonne();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * dal.dao.interfaces.PersonneContactDao#findPersonneByIdEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public List<PersonneContactDto> findPersonneByIdEntreprise(EntrepriseDto entrepriseDto) {
    tryToThrowSqlUncheckedException("Echec findPersonneByIdEntreprises");
    List<PersonneContactDto> liste = new ArrayList<PersonneContactDto>();
    for (int i = 1; i < 11; i++) {
      PersonneContactDto personneContactDto = this.bizFactoryStub.getPersonneContactDto();
      personneContactDto.setIdPersonne(i);
      personneContactDto.setIdEntreprise(entrepriseDto.getIdEntreprise());
      personneContactDto.setNomEntreprise(entrepriseDto.getNomEntreprise());
      liste.add(personneContactDto);
    }
    return liste;
  }

  @Override
  public List<PersonneContactDto> findPersonneByIdEntrepriseNonDesactivees(
      EntrepriseDto entrepriseDto) {
    tryToThrowSqlUncheckedException("Echec findPersonneByIdEntrepriseNonDesactivees");
    List<PersonneContactDto> liste = new ArrayList<PersonneContactDto>();
    for (int i = 1; i < 11; i++) {
      PersonneContactDto personneContactDto = this.bizFactoryStub.getPersonneContactDto();
      personneContactDto.setIdPersonne(i);
      personneContactDto.setIdEntreprise(entrepriseDto.getIdEntreprise());
      personneContactDto.setNomEntreprise(entrepriseDto.getNomEntreprise());
      liste.add(personneContactDto);
    }
    return liste;
  }

}
