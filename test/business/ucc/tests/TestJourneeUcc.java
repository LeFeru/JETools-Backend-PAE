package business.ucc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import business.dto.JourneeDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import business.ucc.implementations.JourneeUccImpl;
import business.ucc.interfaces.JourneeUcc;
import dal.dao.stubs.JourneeDaoStub;
import dal.dao.stubs.UtilisateurDaoStub;
import dal.services.DalServicesStub;
import exceptions.BizException;

public class TestJourneeUcc {
  private JourneeUcc journeeUcc;
  private BizFactory bizFactory;
  private JourneeDto journee1;
  private JourneeDto sample;

  @Before
  public void setUp() throws Exception {
    journeeUcc = new JourneeUccImpl(new JourneeDaoStub(), new BizFactoryImpl(),
        new DalServicesStub(), new UtilisateurDaoStub());
    bizFactory = new BizFactoryStub();
    journee1 = bizFactory.getJourneeDto();
    sample = bizFactory.getJourneeDto();
  }

  @Test(expected = BizException.class)
  public void testCreerJournee1() {
    journee1.setIdJournee(2);
    LocalDate date = LocalDate.now();
    journee1.setDateJe(date);
    journeeUcc.creerJournee(journee1);
  }

  @Test(expected = BizException.class)
  public void testCreerJournee2() {
    journee1.setDateJe(sample.getDateJe().plusDays(1));
    journee1.getIdCreateur().setPseudo("LLALALA");
    journeeUcc.creerJournee(journee1);
  }

  @Test(expected = BizException.class)
  public void testCreerJournee3() {
    journee1.setDateJe(sample.getDateJe().plusDays(1));
    assertEquals(1, journeeUcc.creerJournee(journee1).getIdJournee());
  }

  @Test
  public void testGetJournees() {
    assertNotEquals(null, journeeUcc.getJournees());
  }

  @Test
  public void testGetJourneeCourante() {
    assertEquals(1, journeeUcc.getJourneeCourante().getIdJournee());
  }

  @Test
  public void testCloturerJournee1() {
    assertEquals(1, journeeUcc.cloturerJournee(journee1));
  }

  @Test(expected = BizException.class)
  public void testCloturerJournee2() {
    journee1.setIdJournee(0);
    assertEquals(1, journeeUcc.cloturerJournee(journee1));
  }
}
