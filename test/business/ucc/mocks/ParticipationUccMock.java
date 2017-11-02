/**
 * 
 */
package business.ucc.mocks;

import java.util.ArrayList;
import java.util.List;

import business.dto.EntrepriseDto;
import business.dto.ParticipationDto;
import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.ucc.interfaces.ParticipationUcc;
import exceptions.BizException;
import exceptions.InvalidParticipationDtoException;



/**
 * @author rachid
 *
 */
public class ParticipationUccMock implements ParticipationUcc {
  private boolean throwBizException;
  private boolean throwInvalidParticipationDtoException;
  private BizFactory bizFactoryStub;
  private ParticipationDto participationDtoSample;
  private boolean emptyList;

  /**
   * 
   */
  public ParticipationUccMock() {
    this.throwBizException = false;
    this.throwInvalidParticipationDtoException = false;
    this.emptyList = false;
    this.participationDtoSample = this.bizFactoryStub.getParticipationDto();
  }

  public void setThrowInvalidParticipationDtoException(
      boolean throwInvalidParticipationDtoException) {
    this.throwInvalidParticipationDtoException = throwInvalidParticipationDtoException;
  }

  public void setThrowBizException(boolean throwBizException) {
    this.throwBizException = throwBizException;
  }

  private void tryToThrowException(String message) {
    if (throwInvalidParticipationDtoException) {
      throw new InvalidParticipationDtoException(message);
    }
    if (throwBizException)
      throw new BizException(message);
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#inviter(java.util.List)
   */
  @Override
  public List<PersonneContactDto> inviter(List<EntrepriseDto> entrepriseDtos) {
    tryToThrowException("Echec inviter");
    if (emptyList) {
      return null;
    }
    List<PersonneContactDto> res = new ArrayList<>();
    for (EntrepriseDto entrepriseDto : entrepriseDtos) {
      if (entrepriseDto.getNomEntreprise()
          .equals(this.bizFactoryStub.getPersonneContactDto().getNomEntreprise())) {
        res.add(this.bizFactoryStub.getPersonneContactDto());
        return res;
      }
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#listeSelectionEntreprises()
   */
  @Override
  public ArrayList<EntrepriseDto> listeSelectionEntreprises() {
    tryToThrowException("Echec listeSelectionEntreprises");
    if (emptyList) {
      return null;
    }
    ArrayList<EntrepriseDto> temp = new ArrayList<>();
    return temp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#listeEntrepriseNonSelectionnees()
   */
  @Override
  public ArrayList<EntrepriseDto> listeEntrepriseNonSelectionnees() {
    tryToThrowException("Echec listeEntrepriseNonSelectionnees");
    if (emptyList) {
      return null;
    }
    ArrayList<EntrepriseDto> temp = new ArrayList<EntrepriseDto>();
    return temp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#entrepriseInvitees(int)
   */
  @Override
  public ArrayList<EntrepriseDto> entrepriseInvitees(int idJournee) {
    tryToThrowException("Echec entrepriseInvitees");
    if (emptyList) {
      return null;
    }
    if (idJournee == this.bizFactoryStub.getJourneeDto().getIdJournee()) {
      ArrayList<EntrepriseDto> temp = new ArrayList<>();
      temp.add(this.bizFactoryStub.getEntrepriseDto());
      return temp;
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
    tryToThrowException("Echec annulerParticipation");
    if (participation.getIdParticipation() == this.participationDtoSample.getIdParticipation()) {
      participation.setAnnulee(true);
      return participation;
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
  public ArrayList<ParticipationDto> participationsNonAnnulees(ParticipationDto participation) {
    tryToThrowException("Echec participationsNonAnnulees");
    if (participation.getIdParticipation() == this.participationDtoSample.getIdParticipation()) {
      ArrayList<ParticipationDto> temp = new ArrayList<ParticipationDto>();
      temp.add(participationDtoSample);
      return temp;
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
    tryToThrowException("Echec modificationParticipation");
    if (participation.getIdParticipation() == this.participationDtoSample.getIdParticipation()) {
      return participationDtoSample;
    }
    return null;
  }

}
