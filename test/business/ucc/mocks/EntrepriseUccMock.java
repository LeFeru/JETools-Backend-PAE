/**
 * 
 */
package business.ucc.mocks;

import java.util.ArrayList;
import java.util.List;

import business.dto.EntrepriseDto;
import business.dto.ParticipationDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.ucc.interfaces.EntrepriseUcc;
import exceptions.BizException;

/**
 * @author rachid
 *
 */
public class EntrepriseUccMock implements EntrepriseUcc {
  private boolean throwBizException;
  private BizFactory bizFactoryStub;
  private EntrepriseDto entrepriseDtoSample;
  private boolean emptyList;

  /**
   * 
   */
  public EntrepriseUccMock() {
    this.throwBizException = false;
    this.emptyList = false;
    this.bizFactoryStub = new BizFactoryStub();
    this.entrepriseDtoSample = this.bizFactoryStub.getEntrepriseDto();
  }

  public void setEmptyList(boolean emptyList) {
    this.emptyList = emptyList;
  }


  public void setThrowBizException(boolean throwBizException) {
    this.throwBizException = throwBizException;
  }

  private void tryToThrowException(String message) {
    if (throwBizException){
      throw new BizException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#creerEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto creerEntreprise(EntrepriseDto entreprise) {
    tryToThrowException("Echec creerEntreprise");
    if (entreprise.getNomEntreprise().equals(entrepriseDtoSample.getNomEntreprise())) {
      return entrepriseDtoSample;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#listeEntreprises()
   */
  @Override
  public ArrayList<EntrepriseDto> listeEntreprises() {
    tryToThrowException("Echec listeEntreprises");
    if (emptyList) {
      return null;
    }
    ArrayList<EntrepriseDto> liste = new ArrayList<EntrepriseDto>();
    liste.add(entrepriseDtoSample);
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#modifierEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto modifierEntreprise(EntrepriseDto entreprise) {
    tryToThrowException("Echec modifierEntreprise");
    if (entreprise.getNomEntreprise().equals(entrepriseDtoSample.getNomEntreprise())) {
      return entrepriseDtoSample;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#chargerEntreprise(int)
   */
  @Override
  public EntrepriseDto chargerEntreprise(int idEntreprise) {
    tryToThrowException("Echec chargerEntreprise");
    if (idEntreprise == entrepriseDtoSample.getIdEntreprise()) {
      return entrepriseDtoSample;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#historiqueEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public List<ParticipationDto> historiqueEntreprise(EntrepriseDto entreprise) {
    tryToThrowException("Echec historiqueEntreprise");
    if (emptyList) {
      return null;
    }
    List<ParticipationDto> res = new ArrayList<ParticipationDto>();
    if (entreprise.getNomEntreprise().equals(entrepriseDtoSample.getNomEntreprise())) {
      res.add(this.bizFactoryStub.getParticipationDto());
    }
    return res;
  }

}
