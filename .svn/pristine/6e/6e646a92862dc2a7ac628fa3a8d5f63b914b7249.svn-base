package business.biz.tests;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import business.dto.JourneeDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.impl.JourneeImpl;
import exceptions.InvalidJourneeDtoException;

public class JourneeBiz {
  private JourneeDto sample;
  private BizFactory bizFactory;
  private JourneeImpl journeeImpl;

  @Before
  public void setUp() {
    this.bizFactory = new BizFactoryStub();
    this.sample = this.bizFactory.getJourneeDto();
    this.journeeImpl = (JourneeImpl) this.bizFactory.getJourneeDto();
  }

  @Test
  public void testCheckJournee1() {
    journeeImpl.checkJournee(sample);
  }

  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee2() {
    journeeImpl.checkJournee(null);
  }

  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee3() {
    journeeImpl.setIdCreateur(null);
    journeeImpl.checkJournee(journeeImpl);
  }

  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee4() {
    journeeImpl.setDateJe(null);
    journeeImpl.checkJournee(journeeImpl);
  }

  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee5() {
    journeeImpl.setDateJe(LocalDate.now().minusDays(2));
    journeeImpl.checkJournee(journeeImpl);
  }
  
  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee6(){
    journeeImpl.setCloturee(true);
    journeeImpl.checkJournee(journeeImpl);
  }
}
