package business.biz.tests;

import org.junit.Before;
import org.junit.Test;

import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.impl.PersonneContactImpl;
import exceptions.InvalidPersonneContactDtoException;

public class PersonneContactBiz {
  private PersonneContactDto sample;
  private BizFactory bizFactory;
  private PersonneContactImpl personneContactImpl;

  @Before
  public void setUp() {
    this.bizFactory = new BizFactoryStub();
    this.sample = this.bizFactory.getPersonneContactDto();
    this.personneContactImpl = (PersonneContactImpl) this.bizFactory.getPersonneContactDto();
  }

  @Test
  public void testCheckPersonneContact1() {
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }

  @Test
  public void testCheckPersonneContact2() {
    personneContactImpl.setEmail(null);
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }

  @Test
  public void testCheckPersonneContact3() {
    personneContactImpl.setTelephone(null);
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonneContact4() {
    personneContactImpl.checkPersonneContact(null);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonneContact5() {
    personneContactImpl.setNom(null);
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonneContact6() {
    personneContactImpl.setPrenom(null);
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonneContact7() {
    personneContactImpl.setEmail(null);
    personneContactImpl.setTelephone(null);
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonneContact8() {
    String nom = "";
    for (int i = 0; i < 76; i++) {
      nom += "ab";
    }
    personneContactImpl.setNom(nom);
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonneContact9() {
    String prenom = "";
    for (int i = 0; i < 76; i++) {
      prenom += "ab";
    }
    personneContactImpl.setPrenom(prenom);
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonneContact10() {
    personneContactImpl.setEmail("test");
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonneContact11() {
    personneContactImpl.setTelephone("0123");
    personneContactImpl.checkPersonneContact(personneContactImpl);
  }
}
