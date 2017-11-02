/**
 * 
 */
package business.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.impl.UtilisateurImpl;
import exceptions.InvalidUtilisateurDtoException;

/**
 * @author rachid
 *
 */
public class TestUtilisateurImpl {
  private BizFactory bizFactory;
  private UtilisateurImpl utilisateur;
  private UtilisateurImpl sample;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.bizFactory = new BizFactoryStub();
    this.sample = (UtilisateurImpl) this.bizFactory.getUtilisateurDto();
    this.utilisateur = (UtilisateurImpl) this.bizFactory.getUtilisateurDto();
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#hashCode()}.
   */
  @Test
  public void testHashCode() {
    assertEquals("Test hashCode a échoué", sample.hashCode(), utilisateur.hashCode());
    utilisateur.setId(100000);
    assertEquals("Test hashCode a échoué", sample.hashCode(), utilisateur.hashCode());
    utilisateur.setId(sample.getId());
    utilisateur.setPseudo("100000");
    assertNotEquals("Test hashCode a échoué", sample.hashCode(), utilisateur.hashCode());
    utilisateur.setPseudo(sample.getPseudo());
  }

  /**
   * Test method for
   * {@link business.impl.UtilisateurImpl#UtilisateurImpl(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.time.LocalDateTime, java.lang.String, java.lang.String, boolean, int)}.
   */
  @Test
  public void testUtilisateurImplIntStringStringStringStringLocalDateTimeStringStringBooleanInt() {
    new UtilisateurImpl(sample.getId(), sample.getPseudo(), sample.getNom(), sample.getPrenom(),
        sample.getEmail(), sample.getDateInscription(), sample.getMdp(), sample.getMdpConfirme(),
        sample.isResponsable(), sample.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getMdpConfirme()}.
   */
  @Test
  public void testGetMdpConfirme() {
    assertEquals("Test getMdpConfirme a échoué", sample.getMdpConfirme(),
        utilisateur.getMdpConfirme());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setMdpConfirme(java.lang.String)}.
   */
  @Test
  public void testSetMdpConfirme() {
    utilisateur.setMdpConfirme("test");
    assertEquals("Test setMdpConfirme a échoué", "test", utilisateur.getMdpConfirme());
    utilisateur.setMdpConfirme(sample.getMdpConfirme());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#UtilisateurImpl()}.
   */
  @Test
  public void testUtilisateurImpl() {
    new UtilisateurImpl();
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getId()}.
   */
  @Test
  public void testGetId() {
    assertEquals("Test getId a échoué", sample.getId(), utilisateur.getId());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setId(int)}.
   */
  @Test
  public void testSetId() {
    utilisateur.setId(200);
    assertEquals("Test setId a échoué", 200, utilisateur.getId());
    utilisateur.setId(sample.getId());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getNom()}.
   */
  @Test
  public void testGetNom() {
    assertEquals("Test getNom a échoué", sample.getNom(), utilisateur.getNom());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setNom(java.lang.String)}.
   */
  @Test
  public void testSetNom() {
    utilisateur.setNom("test");
    assertEquals("Test setNom a échoué", "test", utilisateur.getNom());
    utilisateur.setNom(sample.getNom());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getPrenom()}.
   */
  @Test
  public void testGetPrenom() {
    assertEquals("Test getPrenom a échoué", sample.getPrenom(), utilisateur.getPrenom());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setPrenom(java.lang.String)}.
   */
  @Test
  public void testSetPrenom() {
    utilisateur.setPrenom("test");
    assertEquals("Test setPrenom a échoué", "test", utilisateur.getPrenom());
    utilisateur.setPrenom(sample.getPrenom());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getEmail()}.
   */
  @Test
  public void testGetEmail() {
    assertEquals("Test getEmail a échoué", sample.getEmail(), utilisateur.getEmail());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setEmail(java.lang.String)}.
   */
  @Test
  public void testSetEmail() {
    utilisateur.setEmail("test");
    assertEquals("Test setEmail a échoué", "test", utilisateur.getEmail());
    utilisateur.setEmail(sample.getEmail());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getDateInscription()}.
   */
  @Test
  public void testGetDateInscription() {
    assertEquals("Test getDateInscription a échoué", sample.getDateInscription(),
        utilisateur.getDateInscription());
  }

  /**
   * Test method for
   * {@link business.impl.UtilisateurImpl#setDateInscription(java.time.LocalDateTime)}.
   */
  @Test
  public void testSetDateInscription() {
    LocalDateTime test = LocalDateTime.now();
    utilisateur.setDateInscription(test);;
    assertEquals("Test setDateInscription a échoué", test, utilisateur.getDateInscription());
    utilisateur.setDateInscription(sample.getDateInscription());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getMdp()}.
   */
  @Test
  public void testGetMdp() {
    assertEquals("Test getMdp a échoué", sample.getMdp(), utilisateur.getMdp());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setMdp(java.lang.String)}.
   */
  @Test
  public void testSetMdp() {
    utilisateur.setMdp("test");
    assertEquals("Test setMdp a échoué", "test", utilisateur.getMdp());
    utilisateur.setMdp(sample.getMdp());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#isResponsable()}.
   */
  @Test
  public void testIsResponsable() {
    assertEquals("Test isResponsable a échoué", sample.isResponsable(),
        utilisateur.isResponsable());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setResponsable(boolean)}.
   */
  @Test
  public void testSetResponsable() {
    utilisateur.setResponsable(!sample.isResponsable());
    assertEquals("Test setResponsable a échoué", !sample.isResponsable(),
        utilisateur.isResponsable());
    utilisateur.setResponsable(sample.isResponsable());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getPseudo()}.
   */
  @Test
  public void testGetPseudo() {
    assertEquals("Test getPseudo a échoué", sample.getPseudo(), utilisateur.getPseudo());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setPseudo(java.lang.String)}.
   */
  @Test
  public void testSetPseudo() {
    utilisateur.setPseudo("test");
    assertEquals("Test setPseudo a échoué", "test", utilisateur.getPseudo());
    utilisateur.setPseudo(sample.getPseudo());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getNumVersion()}.
   */
  @Test
  public void testGetNumVersion() {
    assertEquals("Test getNumVersion a échoué", sample.getNumVersion(),
        utilisateur.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setNumVersion(int)}.
   */
  @Test
  public void testSetNumVersion() {
    utilisateur.setNumVersion(123);
    assertEquals("Test setNumVersion a échoué", 123, utilisateur.getNumVersion());
    utilisateur.setNumVersion(sample.getNumVersion());
  }


  /**
   * Test method for {@link business.impl.UtilisateurImpl#crypterMdp(business.dto.UtilisateurDto)}.
   */
  @Test
  public void testCrypterMdp() {
    assertTrue("Test crypterMdp a échoué",
        BCrypt.checkpw(sample.getMdp(), utilisateur.crypterMdp(utilisateur).getMdp()));
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    assertTrue("Test equalsObject a échoué", sample.equals(utilisateur));
    assertFalse("Test equalsObject a échoué", sample.equals(new UtilisateurImpl()));
    assertFalse("Test equalsObject a échoué", sample.equals(null));
    assertTrue("Test equalsObject a échoué", sample.equals(sample));
    utilisateur.setId(433445534);
    assertTrue("Test equalsObject a échoué", sample.equals(utilisateur));
    utilisateur.setId(sample.getId());
    utilisateur.setPseudo("Test");
    assertFalse("Test equalsObject a échoué", sample.equals(utilisateur));
    utilisateur.setPseudo(sample.getPseudo());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#toString()}.
   */
  @Test
  public void testToString() {
    assertEquals("Test toString a échoué", sample.toString(), utilisateur.toString());
  }


  /**
   * Test method for {@link business.impl.UtilisateurImpl#getNombreTentativesConnexions()}.
   */
  @Test
  public void testGetNombreTentativesConnexions() {
    assertEquals("Test getNombreTentativesConnexions a échoué",
        sample.getNombreTentativesConnexions(), utilisateur.getNombreTentativesConnexions());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#setNombreTentativesConnexions(int)}.
   */
  @Test
  public void testSetNombreTentativesConnexions() {
    utilisateur.setNombreTentativesConnexions(5);
    assertEquals("Test setNombreTentativesConnexions a échoué", 5,
        utilisateur.getNombreTentativesConnexions());
    utilisateur.setNombreTentativesConnexions(sample.getNombreTentativesConnexions());
  }

  /**
   * Test method for {@link business.impl.UtilisateurImpl#getDateDerniereTentative()}.
   */
  @Test
  public void testGetDateDerniereTentative() {
    assertEquals("Test getDateDerniereTentative a échoué", sample.getDateDerniereTentative(),
        utilisateur.getDateDerniereTentative());
  }

  /**
   * Test method for
   * {@link business.impl.UtilisateurImpl#setDateDerniereTentative(java.time.LocalDateTime)}.
   */
  @Test
  public void testSetDateDerniereTentative() {
    LocalDateTime test = LocalDateTime.now();
    utilisateur.setDateDerniereTentative(test);;
    assertEquals("Test setDateDerniereTentative a échoué", test,
        utilisateur.getDateDerniereTentative());
    utilisateur.setDateDerniereTentative(sample.getDateDerniereTentative());
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeConnexion1() {
    utilisateur.setPseudo(null);
    this.utilisateur.checkBeforeConnexion();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeConnexion2() {
    utilisateur.setPseudo("");
    this.utilisateur.checkBeforeConnexion();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeConnexion3() {
    utilisateur.setPseudo(sample.getPseudo());
    utilisateur.setMdp(null);
    this.utilisateur.checkBeforeConnexion();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeConnexion4() {
    utilisateur.setPseudo(sample.getPseudo());
    utilisateur.setMdp("");
    this.utilisateur.checkBeforeConnexion();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription1() {
    utilisateur.setMdp(sample.getMdp());
    utilisateur.setNom(null);
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription2() {
    utilisateur.setNom("");
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription3() {
    utilisateur.setNom(sample.getNom());
    utilisateur.setPrenom(null);
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription4() {
    utilisateur.setPrenom("");
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription5() {
    utilisateur.setPrenom(sample.getPrenom());
    utilisateur.setEmail(null);
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription6() {
    utilisateur.setEmail("");
    this.utilisateur.checkBeforeInscription();
  }

  /*
   * @Test(expected = InvalidUtilisateurDtoException.class) public void checkBeforeInscription7() {
   * utilisateur.setEmail(sample.getEmail()); utilisateur.setMdpConfirme(null);
   * this.utilisateur.checkBeforeInscription(); }
   * 
   * @Test(expected = InvalidUtilisateurDtoException.class) public void checkBeforeInscription8() {
   * utilisateur.setMdpConfirme(""); this.utilisateur.checkBeforeInscription(); }
   */

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription9() {
    utilisateur.setMdpConfirme("aaaaaaaaaaaaaaa");
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription10() {
    utilisateur.setMdp(sample.getMdpConfirme());
    utilisateur.setNom("a");
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription11() {
    utilisateur.setNom(sample.getNom());
    utilisateur.setPrenom("a");
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription12() {
    utilisateur.setPrenom(sample.getPrenom());
    utilisateur.setPseudo("ue");
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription13() {
    utilisateur.setPseudo(sample.getPseudo());
    utilisateur.setMdp("ue");
    this.utilisateur.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void checkBeforeInscription14() {
    utilisateur.setMdp(sample.getMdp());
    utilisateur.setEmail("ue");
    this.utilisateur.checkBeforeInscription();
  }

}
