package business.ucc.stubs;

import java.util.ArrayList;

import business.biz.PersonneContactBiz;
import business.dto.EntrepriseDto;
import business.dto.PersonneContactDto;
import business.ucc.interfaces.PersonneContactUcc;
import dal.dao.interfaces.EntrepriseDao;
import dal.dao.interfaces.PersonneContactDao;
import dal.dao.stubs.EntrepriseDaoStub;
import dal.dao.stubs.PersonneContactDaoStub;
import exceptions.BizException;

/**
 * @author rachid.
 *
 */
public class PersonneContactUccStub implements PersonneContactUcc {
  private boolean throwBizException;
  private PersonneContactDao personneContactDaoStub;
  private EntrepriseDao entrepriseDaoStub;

  /**
   * Constructeur PersonneContactUccStub.
   */
  public PersonneContactUccStub() {
    this.throwBizException = false;
    this.personneContactDaoStub = new PersonneContactDaoStub();
    this.entrepriseDaoStub = new EntrepriseDaoStub();
  }

  public void setThrowBizException(boolean throwBizException) {
    this.throwBizException = throwBizException;
  }

  private void tryToThrowException(String message) {
    if (throwBizException) {
      throw new BizException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.PersonneContactUcc#creerPersonneContact(business.dto.
   * PersonneContactDto)
   */
  @Override
  public PersonneContactDto creerPersonneContact(PersonneContactDto personneContactDto) {
    tryToThrowException("Echec creerPersonneContact");
    ((PersonneContactBiz) personneContactDto).checkPersonneContact(personneContactDto);
    int idEntreprise = entrepriseDaoStub
        .findEntrepriseByNomEntreprise(personneContactDto.getNomEntreprise()).getIdEntreprise();
    personneContactDto.setIdEntreprise(idEntreprise);
    personneContactDto = personneContactDaoStub.creerPersonneContact(personneContactDto);
    return personneContactDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.PersonneContactUcc#listeContacts()
   */
  @Override
  public ArrayList<PersonneContactDto> listeContacts() {
    tryToThrowException("Echec listeContacts");
    ArrayList<PersonneContactDto> temp = personneContactDaoStub.listeContacts();
    for (PersonneContactDto personne : temp) {
      EntrepriseDto entreprise = entrepriseDaoStub.findEntrepriseById(personne.getIdEntreprise());
      personne.setNomEntreprise(entreprise.getNomEntreprise());
    }
    return temp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.PersonneContactUcc#chargerContact(int)
   */
  @Override
  public PersonneContactDto chargerContact(int idPersonne) {
    tryToThrowException("Echec chargerContact");
    return personneContactDaoStub.findPersonneById(idPersonne);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.PersonneContactUcc#modifierContact(business.dto.PersonneContactDto)
   */
  @Override
  public PersonneContactDto modifierContact(PersonneContactDto personne) {
    tryToThrowException("Echec modifierContact");
    ((PersonneContactBiz) personne).checkPersonneContact(personne);
    personne = personneContactDaoStub.modifierPersonneContact(personne);
    return personne;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.PersonneContactUcc#desactiverContact(business.dto.PersonneContactDto)
   */
  @Override
  public int desactiverContact(PersonneContactDto personneContactDto) {
    tryToThrowException("Echec desactiverContact");
    int idPersonne = personneContactDto.getIdPersonne();
    return idPersonne;
  }

}
