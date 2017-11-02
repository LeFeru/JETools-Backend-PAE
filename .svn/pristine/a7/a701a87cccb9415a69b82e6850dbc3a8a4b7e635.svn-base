/**
 * 
 */
package business.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.impl.EntrepriseImpl;
import business.impl.UtilisateurImpl;
import exceptions.InvalidEntrepriseDtoException;

/**
 * @author rachid
 *
 */
public class TestEntrepriseImpl {

  private BizFactory bizFactory;
  private EntrepriseImpl sample;
  private EntrepriseImpl entreprise;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.bizFactory = new BizFactoryStub();
    this.sample = (EntrepriseImpl) this.bizFactory.getEntrepriseDto();
    this.entreprise = (EntrepriseImpl) this.bizFactory.getEntrepriseDto();
  }


  /**
   * Test method for
   * {@link business.impl.EntrepriseImpl#EntrepriseImpl(int, java.lang.String, business.dto.UtilisateurDto, java.time.LocalDateTime, java.time.LocalDate, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
   */
  @Test
  public void testEntrepriseImplIntStringUtilisateurDtoLocalDateTimeLocalDateStringStringStringStringStringInt() {
    this.entreprise = new EntrepriseImpl(sample.getIdEntreprise(), sample.getNomEntreprise(),
        sample.getCreateur(), sample.getDateCreation(), sample.getDateDerniereParticipationPayee(),
        sample.getRue(), sample.getNumero(), sample.getBoite(), sample.getCodePostal(),
        sample.getCommune(), sample.getNumVersion());
    assertEquals("Test du constructeur a échoué", sample, entreprise);
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getNumVersion()}.
   */
  @Test
  public void testGetNumVersion() {
    assertEquals("Test getNumVersion a échoué", sample.getNumVersion(), entreprise.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setNumVersion(int)}.
   */
  @Test
  public void testSetNumVersion() {
    entreprise.setNumVersion(1000);
    assertEquals("Test setNumVersion a échoué", 1000, entreprise.getNumVersion());
    entreprise.setNumVersion(sample.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#EntrepriseImpl()}.
   */
  @Test
  public void testEntrepriseImpl() {
    new EntrepriseImpl();
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getIdEntreprise()}.
   */
  @Test
  public void testGetIdEntreprise() {
    assertEquals("Test getIdEnterprise a échoué", sample.getIdEntreprise(),
        entreprise.getIdEntreprise());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setIdEntreprise(int)}.
   */
  @Test
  public void testSetIdEntreprise() {
    entreprise.setIdEntreprise(1000);
    assertEquals("Test setIdEntreprise a échoué", 1000, entreprise.getIdEntreprise());
    entreprise.setIdEntreprise(sample.getIdEntreprise());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getNomEntreprise()}.
   */
  @Test
  public void testGetNomEntreprise() {
    assertEquals("Test getNomEnterprise a échoué", sample.getNomEntreprise(),
        entreprise.getNomEntreprise());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setNomEntreprise(java.lang.String)}.
   */
  @Test
  public void testSetNomEntreprise() {
    entreprise.setNomEntreprise("Test");
    assertEquals("Test setNomEntreprise a échoué", "Test", entreprise.getNomEntreprise());
    entreprise.setNomEntreprise(sample.getNomEntreprise());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getDateCreation()}.
   */
  @Test
  public void testGetDateCreation() {
    assertEquals("Test getDateCreation a échoué", sample.getDateCreation(),
        entreprise.getDateCreation());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setDateCreation(java.time.LocalDateTime)}.
   */
  @Test
  public void testSetDateCreation() {
    LocalDateTime expected = LocalDateTime.now();
    entreprise.setDateCreation(expected);
    assertEquals("Test setDateCreation a échoué", expected, entreprise.getDateCreation());
    entreprise.setDateCreation(sample.getDateCreation());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getCreateur()}.
   */
  @Test
  public void testGetCreateur() {
    assertEquals("Test getCreateur a échoué", sample.getCreateur(), entreprise.getCreateur());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setCreateur(business.dto.UtilisateurDto)}.
   */
  @Test
  public void testSetCreateur() {
    UtilisateurDto expected = new UtilisateurImpl();
    entreprise.setCreateur(expected);
    assertEquals("Test setCreateur a échoué", expected, entreprise.getCreateur());
    entreprise.setCreateur(sample.getCreateur());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getDateDerniereParticipationPayee()}.
   */
  @Test
  public void testGetDateDerniereParticipationPayee() {
    assertEquals("Test getDateDerniereParticipationPayee a échoué",
        sample.getDateDerniereParticipationPayee(), entreprise.getDateDerniereParticipationPayee());
  }

  /**
   * Test method for
   * {@link business.impl.EntrepriseImpl#setDateDerniereParticipationPayee(java.time.LocalDate)}.
   */
  @Test
  public void testSetDateDerniereParticipationPayee() {
    LocalDate expected = LocalDate.now();
    entreprise.setDateDerniereParticipationPayee(expected);
    assertEquals("Test setDateDerniereParticipationPayee a échoué", expected,
        entreprise.getDateDerniereParticipationPayee());
    entreprise.setDateDerniereParticipationPayee(sample.getDateDerniereParticipationPayee());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getRue()}.
   */
  @Test
  public void testGetRue() {
    assertEquals("Test getRue a échoué", sample.getRue(), entreprise.getRue());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setRue(java.lang.String)}.
   */
  @Test
  public void testSetRue() {
    entreprise.setRue("Test");
    assertEquals("Test setRue a échoué", "Test", entreprise.getRue());
    entreprise.setRue(sample.getRue());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getNumero()}.
   */
  @Test
  public void testGetNumero() {
    assertEquals("Test getNumero a échoué", sample.getNumero(), entreprise.getNumero());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setNumero(java.lang.String)}.
   */
  @Test
  public void testSetNumero() {
    entreprise.setNumero("Test");
    assertEquals("Test setNumero a échoué", "Test", entreprise.getNumero());
    entreprise.setNumero(sample.getNumero());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getBoite()}.
   */
  @Test
  public void testGetBoite() {
    assertEquals("Test getBoite a échoué", sample.getBoite(), entreprise.getBoite());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setBoite(java.lang.String)}.
   */
  @Test
  public void testSetBoite() {
    entreprise.setBoite("Test");
    assertEquals("Test setBoite a échoué", "Test", entreprise.getBoite());
    entreprise.setBoite(sample.getBoite());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getCodePostal()}.
   */
  @Test
  public void testGetCodePostal() {
    assertEquals("Test getCodePostal a échoué", sample.getCodePostal(), entreprise.getCodePostal());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setCodePostal(java.lang.String)}.
   */
  @Test
  public void testSetCodePostal() {
    entreprise.setCodePostal("Test");
    assertEquals("Test setCodePostal a échoué", "Test", entreprise.getCodePostal());
    entreprise.setCodePostal(sample.getCodePostal());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#getCommune()}.
   */
  @Test
  public void testGetCommune() {
    assertEquals("Test getCommune a échoué", sample.getCommune(), entreprise.getCommune());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#setCommune(java.lang.String)}.
   */
  @Test
  public void testSetCommune() {
    entreprise.setCommune("Test");
    assertEquals("Test setCommune a échoué", "Test", entreprise.getCommune());
    entreprise.setCommune(sample.getCommune());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#toString()}.
   */
  @Test
  public void testToString() {
    assertEquals("Test toString a échoué", sample.toString(), entreprise.toString());
  }

  /**
   * Test method for {@link business.impl.EntrepriseImpl#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    assertTrue("Test equals a échoué", sample.equals(entreprise));
    assertTrue("Test equals a échoué",
        sample.equals((EntrepriseImpl) this.bizFactory.getEntrepriseDto()));
    assertTrue("Test equals a échoué", entreprise.equals(entreprise));
    assertFalse("Test equals a échoué", entreprise.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals("Test hashCode a échoué", sample.hashCode(), entreprise.hashCode());
    EntrepriseImpl test = (EntrepriseImpl) this.bizFactory.getEntrepriseDto();
    test.setIdEntreprise(2384387);
    assertNotEquals("Test hashCode a échoué", sample.hashCode(), test.hashCode());
  }

  @Test
  public void testCheckEntreprise1() {
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise2() {
    this.entreprise.checkEntreprise(null);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise3() {
    entreprise.setNomEntreprise(null);
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise4() {
    entreprise.setNomEntreprise(this.sample.getNomEntreprise());
    entreprise.setRue(null);
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise5() {
    entreprise.setRue(this.sample.getRue());
    entreprise.setNumero(null);
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise6() {
    entreprise.setNumero(this.sample.getNumero());
    entreprise.setCodePostal(null);
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise7() {
    entreprise.setCodePostal(this.sample.getCodePostal());
    entreprise.setCommune(null);
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise8() {
    entreprise.setCommune(this.sample.getCommune());
    entreprise.setNomEntreprise("");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise9() {
    entreprise.setNomEntreprise(this.sample.getNomEntreprise());
    entreprise.setRue("");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise10() {
    entreprise.setRue(this.sample.getRue());
    entreprise.setNumero("");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise11() {
    entreprise.setNumero(this.sample.getNumero());
    entreprise.setCodePostal("");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise12() {
    entreprise.setCodePostal(this.sample.getCodePostal());
    entreprise.setCommune("");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise13() {
    entreprise.setCommune(this.sample.getCommune());
    entreprise.setNomEntreprise(
        "ZeHm081VMu0mkbUykxAnPRCNor0eoHMJYaNUTSevssQMlj45UkULUpEyCpRB9LyoBPEGqGWnxuFhLS6STp1hJtcdjvHJFbAOJn28cym1oPIMIRqJa6Xuk3C8n0kVp76YAJU77QEUfdBxZJ0Qpyae32K");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise14() {
    entreprise.setNomEntreprise(this.sample.getNomEntreprise());
    entreprise.setRue(
        "ZeHm081VMu0mkbUykxAnPRCNor0eoHMJYaNUTSevssQMlj45UkULUpEyCpRB9LyoBPEGqGWnxuFhLS6STp1hJtcdjvHJFbAOJn28cym1oPIMIRqJa6Xuk3C8n0kVp76YAJU77QEUfdBxZJ0Qpyae32K");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise15() {
    entreprise.setRue(this.sample.getRue());
    entreprise.setBoite("%%%");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise16() {
    entreprise.setBoite(this.sample.getBoite());
    entreprise.setNumero("%%%");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise17() {
    entreprise.setNumero(this.sample.getNumero());
    entreprise.setCodePostal("%%%%");
    this.entreprise.checkEntreprise(this.entreprise);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise18() {
    entreprise.setCodePostal(this.sample.getCodePostal());
    entreprise.setCommune(
        "ZeHm081VMu0mkbUykxAnPRCNor0eoHMJYaNUTSevssQMlj45UkULUpEyCpRB9LyoBPEGqGWnxuFhLS6STp1hJtcdjvHJFbAOJn28cym1oPIMIRqJa6Xuk3C8n0kVp76YAJU77QEUfdBxZJ0Qpyae32K");
    this.entreprise.checkEntreprise(this.entreprise);
  }

}
