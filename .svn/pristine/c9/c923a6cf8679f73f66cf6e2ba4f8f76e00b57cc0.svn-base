/**
 * 
 */
package business.ucc.mocks;

import java.util.ArrayList;
import java.util.List;

import business.dto.EntrepriseDto;
import business.dto.JourneeDto;
import business.dto.PersonneContactDto;
import business.dto.PresenceDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.ucc.interfaces.PresenceUcc;
import exceptions.BizException;

/**
 * @author rachid
 *
 */
public class PresenceUccMock implements PresenceUcc {

  private boolean throwBizException;
  private boolean emptyList;
  private BizFactory bizFactory;
  private PresenceDto presenceDtoSample;

  /**
   * 
   */
  public PresenceUccMock() {
    this.throwBizException = false;
    this.emptyList = false;
    this.bizFactory = new BizFactoryStub();
    this.presenceDtoSample = this.bizFactory.getPresenceDto();
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
   * @see business.ucc.interfaces.PresenceUcc#indiquerPresence(business.dto.EntrepriseDto,
   * java.util.List)
   */
  @Override
  public List<PresenceDto> indiquerPresence(EntrepriseDto entrepriseDto, JourneeDto journee,
      List<PersonneContactDto> personneContactDtos) {
    tryToThrowException("Echec indiquerPresence");
    if (emptyList) {
      return null;
    }
    if (this.bizFactory.getEntrepriseDto().getIdEntreprise() == entrepriseDto.getIdEntreprise()) {
      List<PresenceDto> liste = new ArrayList<PresenceDto>();
      for (PersonneContactDto personneContactDto : personneContactDtos) {
        if (personneContactDto.getIdEntreprise() == this.bizFactory.getEntrepriseDto()
            .getIdEntreprise()
            && personneContactDto.getIdPersonne() == this.presenceDtoSample.getIdPersonne()) {
          liste.add(this.presenceDtoSample);
        }
      }
      if (liste.isEmpty()) {
        return null;
      }
      return liste;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.PresenceUcc#listerContactsParEntreprise(business.dto.EntrepriseDto,
   * business.dto.PresenceDto)
   */
  @Override
  public List<PersonneContactDto> listerContactsParEntreprise(EntrepriseDto entrepriseDto,
      JourneeDto journeeDto) {
    tryToThrowException("Echec listerContactsParEntreprise");
    if (emptyList) {
      return null;
    }
    if (entrepriseDto.getIdEntreprise() == this.bizFactory.getEntrepriseDto().getIdEntreprise()) {
      List<PersonneContactDto> liste = new ArrayList<PersonneContactDto>();
      liste.add(this.bizFactory.getPersonneContactDto());
      return liste;
    }
    return null;

  }

}
