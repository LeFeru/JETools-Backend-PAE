package business.ucc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import business.ucc.implementations.PersonneContactUccImpl;
import business.ucc.interfaces.PersonneContactUcc;
import dal.dao.stubs.EntrepriseDaoStub;
import dal.dao.stubs.PersonneContactDaoStub;
import dal.services.DalServicesStub;
import exceptions.BizException;

public class TestPersonneContactUcc {
  private PersonneContactUcc personneContactUcc;
  private BizFactory bizFactory;
  private PersonneContactDto personneContact;

  @Before
  public void setUp() throws Exception {
    personneContactUcc = new PersonneContactUccImpl(new PersonneContactDaoStub(),
        new BizFactoryImpl(), new DalServicesStub(), new EntrepriseDaoStub());
    bizFactory = new BizFactoryStub();
    personneContact = bizFactory.getPersonneContactDto();
  }

  @Test
  public void testCreerPersonneContact1() {
    personneContact.setEmail("nveauEmail@mail.com");
    personneContact.setTelephone("0404040404");
    assertEquals(1, personneContactUcc.creerPersonneContact(personneContact).getIdPersonne());
  }

  @Test(expected = BizException.class)
  public void testCreerPersonneContact2() {
    personneContact.setEmail("nveauEmail@mail.com");
    assertEquals(1, personneContactUcc.creerPersonneContact(personneContact).getIdPersonne());
  }

  @Test(expected = BizException.class)
  public void testCreerPersonneContact3() {
    personneContact.setTelephone("0404040404");
    assertEquals(1, personneContactUcc.creerPersonneContact(personneContact).getIdPersonne());
  }

  @Test
  public void testListeContacts() {
    assertNotEquals(null, personneContactUcc.listeContacts());
  }

  @Test
  public void testChargerContact1() {
    assertEquals(1,
        personneContactUcc.chargerContact(personneContact.getIdPersonne()).getIdPersonne());
  }

  @Test(expected = BizException.class)
  public void testChargerContact2() {
    personneContact.setIdPersonne(0);
    assertEquals(1,
        personneContactUcc.chargerContact(personneContact.getIdPersonne()).getIdPersonne());
  }

  @Test
  public void testModifierContact1() {
    assertEquals(1,
        personneContactUcc.modifierContact(personneContact).getIdPersonne());
  }

  @Test(expected = BizException.class)
  public void testModifierContact2() {
    personneContact.setIdPersonne(0);
    assertEquals(1,
        personneContactUcc.modifierContact(personneContact).getIdPersonne());
  }

  @Test
  public void testDesactiverContact1() {
    assertEquals(1, personneContactUcc.desactiverContact(personneContact));
  }

  @Test(expected = BizException.class)
  public void testDesactiverContact2() {
    personneContact.setIdPersonne(0);
    assertEquals(1, personneContactUcc.desactiverContact(personneContact));
  }

}
