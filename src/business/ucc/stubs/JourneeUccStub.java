package business.ucc.stubs;

import java.util.ArrayList;

import business.dto.JourneeDto;
import business.ucc.interfaces.JourneeUcc;
import dal.dao.interfaces.JourneeDao;
import dal.dao.stubs.JourneeDaoStub;
import exceptions.BizException;
import exceptions.InvalidJourneeDtoException;

/**
 * @author rachid.
 *
 */
public class JourneeUccStub implements JourneeUcc {
  private boolean throwInvalidJourneeDtoException;
  private boolean throwBizException;
  private JourneeDao journeeDaoStub;

  /**
   * Constructeur JourneeUccStub.
   */
  public JourneeUccStub() {
    this.throwBizException = false;
    this.throwInvalidJourneeDtoException = false;
    this.journeeDaoStub = new JourneeDaoStub();
  }

  public void setThrowInvalidJourneeDtoException(boolean throwInvalidJourneeDtoException) {
    this.throwInvalidJourneeDtoException = throwInvalidJourneeDtoException;
  }

  public void setThrowBizException(boolean throwBizException) {
    this.throwBizException = throwBizException;
  }

  private void tryToThrowException(String message) {
    if (throwInvalidJourneeDtoException) {
      throw new InvalidJourneeDtoException(message);
    }
    if (throwBizException) {
      throw new BizException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.JourneeUcc#creerJournee(business.dto.JourneeDto)
   */
  @Override
  public JourneeDto creerJournee(JourneeDto journeeDto) {
    tryToThrowException("Echec creerJournee");
    return this.journeeDaoStub.creerJournee(journeeDto);
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.JourneeUcc#getJourneeCourante()
   */
  @Override
  public JourneeDto getJourneeCourante() {
    tryToThrowException("Echec getJourneeCourante");
    return this.journeeDaoStub.findJourneeActive();
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.JourneeUcc#getJournees()
   */
  @Override
  public ArrayList<JourneeDto> getJournees() {
    tryToThrowException("Echec getJournees");
    return this.journeeDaoStub.findJournees();
  }

  @Override
  public int cloturerJournee(JourneeDto journeeDto) {
    // TODO Auto-generated method stub
    return 0;
  }

}
