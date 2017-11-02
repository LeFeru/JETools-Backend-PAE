package dal.dao.stubs;

import java.time.LocalDate;
import java.util.ArrayList;

import business.dto.JourneeDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import dal.dao.interfaces.JourneeDao;
import exceptions.UncheckedSqlException;

/**
 * @author rachid.
 *
 */
public class JourneeDaoStub implements JourneeDao {

  private boolean throwSqlUncheckedException;
  private BizFactoryStub bizFactoryStub;
  private BizFactory bizFactory;

  /**
   * Constructeur JourneeDaoStub.
   */
  public JourneeDaoStub() {
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
   * @see dal.dao.interfaces.JourneeDao#creerJournee(business.dto.JourneeDto)
   */
  @Override
  public JourneeDto creerJournee(JourneeDto journeeDto) {
    tryToThrowSqlUncheckedException("Echec creerJournee");
    journeeDto.setIdJournee(1);
    return journeeDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.JourneeDao#findJourneeByDate(java.time.LocalDate)
   */
  @Override
  public JourneeDto findJourneeByDate(LocalDate date) {
    tryToThrowSqlUncheckedException("Echec findJourneeByDate");
    JourneeDto journeeDto = this.bizFactoryStub.getJourneeDto();
    if (!journeeDto.getDateJe().isEqual(date)) {
      journeeDto.setIdJournee(0);
      journeeDto = bizFactory.getJourneeDto();
    }
    return journeeDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.JourneeDao#findJourneeActive()
   */
  @Override
  public JourneeDto findJourneeActive() {
    tryToThrowSqlUncheckedException("Echec findJourneeActive");
    return this.bizFactoryStub.getJourneeDto();
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.JourneeDao#cloturerJournees()
   */
  @Override
  public void cloturerJournees() {
    tryToThrowSqlUncheckedException("Echec cloturerJournees");
    JourneeDto journeeDto = this.bizFactoryStub.getJourneeDto();
    journeeDto.setCloturee(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.JourneeDao#findJournees()
   */
  @Override
  public ArrayList<JourneeDto> findJournees() {
    tryToThrowSqlUncheckedException("Echec findJournees");
    ArrayList<JourneeDto> liste = new ArrayList<JourneeDto>();
    for (int i = 1; i < 11; i++) {
      JourneeDto journeeDto = this.bizFactoryStub.getJourneeDto();
      journeeDto.setIdJournee(i);
      journeeDto.setCloturee(true);
      liste.add(journeeDto);
    }
    JourneeDto journeeDto = this.bizFactoryStub.getJourneeDto();
    journeeDto.setIdJournee(11);
    liste.add(journeeDto);
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.JourneeDao#findJourneeById(int)
   */
  @Override
  public JourneeDto findJourneeById(int idJournee) {
    tryToThrowSqlUncheckedException("Echec findJourneesById");
    JourneeDto journeeDto = this.bizFactoryStub.getJourneeDto();
    if (journeeDto.getIdJournee() != idJournee) {
      journeeDto.setIdJournee(0);
      journeeDto = bizFactory.getJourneeDto();
    }
    return journeeDto;
  }

  @Override
  public int cloturerJournee(int idJournee) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public JourneeDto findDerniereJournee() {
    // TODO Auto-generated method stub
    return bizFactoryStub.getJourneeDto();
  }

}
