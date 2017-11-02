package business.ucc.mocks;

import java.util.ArrayList;

import business.dto.JourneeDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.ucc.interfaces.JourneeUcc;
import exceptions.BizException;
import exceptions.InvalidJourneeDtoException;

/**
 * @author rachid.
 *
 */
public class JourneeUccMock implements JourneeUcc {
  private boolean throwBizException;
  private BizFactory bizFactoryStub;
  private JourneeDto journeeDtoSample;
  private boolean emptyList;
  private boolean throwInvalidJourneeDtoException;

  /**
   * Constructeur JourneeUccMock.
   */
  public JourneeUccMock() {
    this.throwBizException = false;
    this.throwInvalidJourneeDtoException = false;
    this.emptyList = false;
    this.bizFactoryStub = new BizFactoryStub();
    this.journeeDtoSample = this.bizFactoryStub.getJourneeDto();
  }

  public void setEmptyList(boolean emptyList) {
    this.emptyList = emptyList;
  }

  public void setThrowInvalidJourneeDtoException(boolean throwInvalidJourneeDtoException) {
    this.throwInvalidJourneeDtoException = throwInvalidJourneeDtoException;
  }

  public void setThrowBizException(boolean throwBizException) {
    this.throwBizException = throwBizException;
  }

  private void tryToThrowException(String message) {
    if (throwInvalidJourneeDtoException)
      throw new InvalidJourneeDtoException(message);
    if (throwBizException)
      throw new BizException(message);
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.JourneeUcc#creerJournee(business.dto.JourneeDto)
   */
  @Override
  public JourneeDto creerJournee(JourneeDto journeeDto) {
    tryToThrowException("Echec creerJournee");
    if (journeeDto.getDateJe().equals(this.journeeDtoSample.getDateJe())) {
      return journeeDtoSample;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.JourneeUcc#getJourneeCourante()
   */
  @Override
  public JourneeDto getJourneeCourante() {
    tryToThrowException("Echec getJourneeCourante");
    return this.journeeDtoSample;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.JourneeUcc#getJournees()
   */
  @Override
  public ArrayList<JourneeDto> getJournees() {
    tryToThrowException("Echec getJournees");
    if (emptyList) {
      return null;
    }
    ArrayList<JourneeDto> liste = new ArrayList<JourneeDto>();
    liste.add(this.journeeDtoSample);
    return liste;
  }

  @Override
  public int cloturerJournee(JourneeDto journeeDto) {
    tryToThrowException("Echec cloturerJournee");
    journeeDto.setCloturee(true);
    return journeeDto.getIdJournee();
  }

}
