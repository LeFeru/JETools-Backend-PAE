/**
 * 
 */
package business.impl.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.impl.PersonneContactImpl;
import exceptions.InvalidPersonneContactDtoException;

/**
 * @author rachid
 *
 */
public class TestPersonneContactImpl {

  private BizFactory bizFactory;
  private PersonneContactImpl personneContact;
  private PersonneContactImpl sample;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.bizFactory = new BizFactoryImpl();
    this.personneContact = (PersonneContactImpl) this.bizFactory.getPersonneContactDto();
    this.sample = (PersonneContactImpl) this.bizFactory.getPersonneContactDto();
  }

  /**
   * Test method for
   * {@link business.impl.PersonneContactImpl#PersonneContactImpl(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, boolean)}.
   */
  @Test
  public void testPersonneContactImplIntIntIntStringStringStringStringBooleanStringBoolean() {
    new PersonneContactImpl(sample.getIdPersonne(), sample.getIdEntreprise(),
        sample.getNumVersion(), sample.getNom(), sample.getPrenom(), sample.getEmail(),
        sample.getTelephone(), sample.isActif(), sample.getNomEntreprise(), sample.isPresent());

  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#PersonneContactImpl()}.
   */
  @Test
  public void testPersonneContactImpl() {
    new PersonneContactImpl();
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#isPresent()}.
   */
  @Test
  public void testIsPresent() {
    assertEquals("Test isPresent a échoué", sample.isPresent(), personneContact.isPresent());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setPresent(boolean)}.
   */
  @Test
  public void testSetPresent() {
    personneContact.setPresent(!sample.isPresent());
    assertEquals("Test setPresent a échoué", !sample.isPresent(), personneContact.isPresent());
    personneContact.setPresent(sample.isPresent());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#getIdPersonne()}.
   */
  @Test
  public void testGetIdPersonne() {
    assertEquals("Test getIdPersonne a échoué", sample.getIdPersonne(),
        personneContact.getIdPersonne());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setIdPersonne(int)}.
   */
  @Test
  public void testSetIdPersonne() {
    personneContact.setIdPersonne(333);
    assertEquals("Test setIdPersonne a échoué", 333, personneContact.getIdPersonne());
    personneContact.setIdPersonne(sample.getIdPersonne());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#getNom()}.
   */
  @Test
  public void testGetNom() {
    assertEquals("Test getNom a échoué", sample.getNom(), personneContact.getNom());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setNom(java.lang.String)}.
   */
  @Test
  public void testSetNom() {
    personneContact.setNom("test");
    assertEquals("Test setNom a échoué", "test", personneContact.getNom());
    personneContact.setNom(sample.getNom());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#getPrenom()}.
   */
  @Test
  public void testGetPrenom() {
    assertEquals("Test getPrenom a échoué", sample.getPrenom(), personneContact.getPrenom());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setPrenom(java.lang.String)}.
   */
  @Test
  public void testSetPrenom() {
    personneContact.setEmail("test");
    assertEquals("Test setEmail a échoué", "test", personneContact.getEmail());
    personneContact.setEmail(sample.getEmail());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#getEmail()}.
   */
  @Test
  public void testGetEmail() {
    assertEquals("Test getEmail a échoué", sample.getEmail(), personneContact.getEmail());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setEmail(java.lang.String)}.
   */
  @Test
  public void testSetEmail() {
    personneContact.setEmail("test");
    assertEquals("Test setEmail a échoué", "test", personneContact.getEmail());
    personneContact.setEmail(sample.getEmail());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#getTelephone()}.
   */
  @Test
  public void testGetTelephone() {
    assertEquals("Test getTelephone a échoué", sample.getTelephone(),
        personneContact.getTelephone());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setTelephone(java.lang.String)}.
   */
  @Test
  public void testSetTelephone() {
    personneContact.setTelephone("test");
    assertEquals("Test setTelephone a échoué", "test", personneContact.getTelephone());
    personneContact.setTelephone(sample.getTelephone());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#getNumVersion()}.
   */
  @Test
  public void testGetNumVersion() {
    assertEquals("Test getNumVersion a échoué", sample.getNumVersion(),
        personneContact.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setNumVersion(int)}.
   */
  @Test
  public void testSetNumVersion() {
    personneContact.setNumVersion(300);
    assertEquals("Test setNumVersion a échoué", 300, personneContact.getNumVersion());
    personneContact.setNumVersion(sample.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#isActif()}.
   */
  @Test
  public void testIsActif() {
    assertEquals("Test isActif a échoué", sample.isActif(), personneContact.isActif());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setActif(boolean)}.
   */
  @Test
  public void testSetActif() {
    personneContact.setActif(!sample.isActif());
    assertEquals("Test setActif a échoué", !sample.isActif(), personneContact.isActif());
    personneContact.setActif(sample.isActif());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#getIdEntreprise()}.
   */
  @Test
  public void testGetIdEntreprise() {
    assertEquals("Test getIdEntreprise a échoué", sample.getIdEntreprise(),
        personneContact.getIdEntreprise());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setIdEntreprise(int)}.
   */
  @Test
  public void testSetIdEntreprise() {
    personneContact.setIdEntreprise(300);
    assertEquals("Test setIdEntreprise a échoué", 300, personneContact.getIdEntreprise());
    personneContact.setIdEntreprise(sample.getIdEntreprise());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#getNomEntreprise()}.
   */
  @Test
  public void testGetNomEntreprise() {
    assertEquals("Test getNumVersion a échoué", sample.getNomEntreprise(),
        personneContact.getNomEntreprise());
  }

  /**
   * Test method for {@link business.impl.PersonneContactImpl#setNomEntreprise(java.lang.String)}.
   */
  @Test
  public void testSetNomEntreprise() {
    personneContact.setNomEntreprise("test");
    assertEquals("Test setNomEntreprise a échoué", "test", personneContact.getNomEntreprise());
    personneContact.setNomEntreprise(sample.getNomEntreprise());
  }


  /**
   * Test method for {@link business.impl.PersonneContactImpl#toString()}.
   */
  @Test
  public void testToString() {
    assertEquals("Test toString a échoué", sample.toString(), personneContact.toString());
  }

  /*
   * @Test public void testCheckPersonne1() { personneContact.setNom(sample.getNom());
   * this.personneContact.checkPersonneContact(this.personneContact); }
   */

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne2() {
    this.personneContact.checkPersonneContact(null);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne3() {
    personneContact.setIdEntreprise(-1);
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne4() {
    personneContact.setIdEntreprise(sample.getIdEntreprise());
    personneContact.setNom(null);
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne5() {
    personneContact.setNom("");
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne6() {
    personneContact.setNom(sample.getNom());
    personneContact.setPrenom(null);
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne7() {
    personneContact.setPrenom(sample.getPrenom());
    personneContact.setPrenom("");
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne8() {
    personneContact.setPrenom(sample.getPrenom());
    personneContact.setTelephone(null);
    personneContact.setEmail(null);
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne9() {
    personneContact.setTelephone("");
    personneContact.setEmail("");
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne81() {
    personneContact.setTelephone(sample.getTelephone());
    personneContact.setEmail(null);
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne91() {
    personneContact.setEmail("");
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne10() {
    personneContact.setTelephone(sample.getTelephone());
    personneContact.setEmail(sample.getEmail());
    personneContact.setEmail("%%");
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne11() {
    personneContact.setEmail(sample.getEmail());
    personneContact.setTelephone("333");
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne12() {
    personneContact.setTelephone(sample.getTelephone());
    personneContact.setNom(
        "ZeHm081VMu0mkbUykxAnPRCNor0eoHMJYaNUTSevssQMlj45UkULUpEyCpRB9LyoBPEGqGWnxuFhLS6STp1hJtcdjvHJFbAOJn28cym1oPIMIRqJa6Xuk3C8n0kVp76YAJU77QEUfdBxZJ0Qpyae32K");
    this.personneContact.checkPersonneContact(this.personneContact);
  }

  @Test(expected = InvalidPersonneContactDtoException.class)
  public void testCheckPersonne13() {
    personneContact.setNom(sample.getNom());
    personneContact.setPrenom(
        "ZeHm081VMu0mkbUykxAnPRCNor0eoHMJYaNUTSevssQMlj45UkULUpEyCpRB9LyoBPEGqGWnxuFhLS6STp1hJtcdjvHJFbAOJn28cym1oPIMIRqJa6Xuk3C8n0kVp76YAJU77QEUfdBxZJ0Qpyae32K");
    this.personneContact.checkPersonneContact(this.personneContact);
  }



}
