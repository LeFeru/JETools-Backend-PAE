/**
 * 
 */
package business.ucc.mocks;

import java.util.ArrayList;

import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.ucc.interfaces.PersonneContactUcc;
import exceptions.BizException;

/**
 * @author rachid
 *
 */
public class PersonneContactUccMock implements PersonneContactUcc {
  private boolean throwBizException;
  private PersonneContactDto personneContactDtoSample;
  private boolean emptyList;
  private BizFactory bizFactory;

  /**
   * 
   */
  public PersonneContactUccMock() {
    this.throwBizException = false;
    this.emptyList = false;
    this.bizFactory = new BizFactoryStub();
    this.personneContactDtoSample = this.bizFactory.getPersonneContactDto();
  }

  public void setEmptyList(boolean emptyList) {
    this.emptyList = emptyList;
  }

  public void setThrowBizException(boolean throwBizException) {
    this.throwBizException = throwBizException;
  }

  private void tryToThrowException(String message) {
    if (throwBizException)
      throw new BizException(message);
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
    if (personneContactDto.getIdPersonne() == personneContactDtoSample.getIdPersonne())
      return personneContactDtoSample;
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.PersonneContactUcc#listeContacts()
   */
  @Override
  public ArrayList<PersonneContactDto> listeContacts() {
    tryToThrowException("Echec listeContacts");
    if (emptyList) {
      return null;
    }
    ArrayList<PersonneContactDto> temp = new ArrayList<PersonneContactDto>();
    temp.add(personneContactDtoSample);
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
    if (idPersonne == this.personneContactDtoSample.getIdPersonne())
      return this.personneContactDtoSample;
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
    tryToThrowException("Echec modifierContact");
    if (personne.getIdEntreprise() == this.personneContactDtoSample.getIdPersonne())
      return this.personneContactDtoSample;
    return null;
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
    if (personneContactDto.getIdEntreprise() == this.personneContactDtoSample.getIdPersonne()) {
      personneContactDto.setActif(false);
      return personneContactDto.getIdEntreprise();
    }
    return -1;
  }

}
