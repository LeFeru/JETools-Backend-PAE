/**
 * 
 */
package business.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.impl.ParticipationImpl;
import exceptions.InvalidParticipationDtoException;

/**
 * @author rachid
 *
 */
public class TestParticipationImpl {

  private BizFactory bizFactory;
  private ParticipationImpl participation;
  private ParticipationImpl sample;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.bizFactory = new BizFactoryStub();
    this.participation = (ParticipationImpl) this.bizFactory.getParticipationDto();
    this.sample = (ParticipationImpl) this.bizFactory.getParticipationDto();
  }

  /**
   * Test method for
   * {@link business.impl.ParticipationImpl#ParticipationImpl(int, int, int, java.lang.String, java.lang.String, boolean, java.time.LocalDate, java.lang.String)}.
   */
  @Test
  public void testParticipationImplIntIntIntStringStringBooleanLocalDateString() {
    this.participation = new ParticipationImpl(sample.getIdParticipation(), sample.getIdJournee(),
        sample.getIdEntreprise(), sample.getEtat(), sample.getEvolution(), sample.isAnnulee(),
        sample.getDateJe(), sample.getNomEntreprise());
    assertEquals("Test du constructeur a échoué", sample, participation);
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#ParticipationImpl()}.
   */
  @Test
  public void testParticipationImpl() {
    new ParticipationImpl();
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#getIdParticipation()}.
   */
  @Test
  public void testGetIdParticipation() {
    assertEquals("Test getIdParticipation a échoué", sample.getIdParticipation(),
        participation.getIdParticipation());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setIdParticipation(int)}.
   */
  @Test
  public void testSetIdParticipation() {
    participation.setIdParticipation(1000);
    assertEquals("Test setIdParticipation a échoué", 1000, participation.getIdParticipation());
    participation.setIdParticipation(sample.getIdParticipation());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#getIdJournee()}.
   */
  @Test
  public void testGetIdJournee() {
    assertEquals("Test getIdJournee a échoué", sample.getIdJournee(), participation.getIdJournee());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setIdJournee(int)}.
   */
  @Test
  public void testSetIdJournee() {
    participation.setIdJournee(1000);
    assertEquals("Test setIdJournee a échoué", 1000, participation.getIdJournee());
    participation.setIdJournee(sample.getIdJournee());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#getIdEntreprise()}.
   */
  @Test
  public void testGetIdEntreprise() {
    assertEquals("Test getIdEntreprise a échoué", sample.getIdEntreprise(),
        participation.getIdEntreprise());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setIdEntreprise(int)}.
   */
  @Test
  public void testSetIdEntreprise() {
    participation.setIdEntreprise(1000);
    assertEquals("Test setIdEntreprise a échoué", 1000, participation.getIdEntreprise());
    participation.setIdEntreprise(sample.getIdEntreprise());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#getEtat()}.
   */
  @Test
  public void testGetEtat() {
    assertEquals("Test getEtat a échoué", sample.getEtat(), participation.getEtat());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setEtat(java.lang.String)}.
   */
  @Test(expected = InvalidParticipationDtoException.class)
  public void testSetEtat1() {
    participation.setEtat("test");
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setEtat(java.lang.String)}.
   */
  @Test
  public void testSetEtat2() {
    participation.setEtat("invitee");
    assertEquals("Test setEtat a échoué", "invitee", participation.getEtat());
    participation.setEtat(sample.getEtat());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#getEvolution()}.
   */
  @Test
  public void testGetEvolution() {
    assertEquals("Test getEvolution a échoué", sample.getEvolution(), participation.getEvolution());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setEvolution(java.lang.String)}.
   */
  @Test(expected = InvalidParticipationDtoException.class)
  public void testSetEvolution1() {
    participation.setEvolution("test");
    assertEquals("Test setEvolution a échoué", "test", participation.getEvolution());
    participation.setEvolution(sample.getEvolution());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setEvolution(java.lang.String)}.
   */
  @Test
  public void testSetEvolution2() {
    participation.setEvolution(null);
    assertEquals("Test setEvolution a échoué", null, participation.getEvolution());
    participation.setEvolution(sample.getEvolution());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#isAnnulee()}.
   */
  @Test
  public void testIsAnnulee() {
    assertEquals("Test isAnnulee a échoué", sample.isAnnulee(), participation.isAnnulee());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setAnnulee(boolean)}.
   */
  @Test
  public void testSetAnnulee() {
    participation.setAnnulee(!sample.isAnnulee());
    assertEquals("Test setAnnulee a échoué", !sample.isAnnulee(), participation.isAnnulee());
    participation.setAnnulee(sample.isAnnulee());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#getNumVersion()}.
   */
  @Test
  public void testGetNumVersion() {
    assertEquals("Test getNumVersion a échoué", sample.getNumVersion(),
        participation.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setNumVersion(int)}.
   */
  @Test
  public void testSetNumVersion() {
    participation.setNumVersion(1000);
    assertEquals("Test setNumVersion a échoué", 1000, participation.getNumVersion());
    participation.setNumVersion(sample.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#getDateJe()}.
   */
  @Test
  public void testGetDateJe() {
    assertEquals("Test getDateJe a échoué", sample.getDateJe(), participation.getDateJe());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setDateJe(java.time.LocalDate)}.
   */
  @Test
  public void testSetDateJe() {
    LocalDate test = LocalDate.now();
    participation.setDateJe(test);
    assertEquals("Test setDateJe a échoué", test, participation.getDateJe());
    participation.setDateJe(sample.getDateJe());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#getNomEntreprise()}.
   */
  @Test
  public void testGetNomEntreprise() {
    assertEquals("Test getNomEntreprise a échoué", sample.getNomEntreprise(),
        participation.getNomEntreprise());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#setNomEntreprise(java.lang.String)}.
   */
  @Test
  public void testSetNomEntreprise() {
    participation.setNomEntreprise("test");
    assertEquals("Test setNomEntreprise a échoué", "test", participation.getNomEntreprise());
    participation.setNomEntreprise(sample.getNomEntreprise());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#toString()}.
   */
  @Test
  public void testToString() {
    assertEquals("Test toString a échoué", sample.toString(), participation.toString());
  }

  /**
   * Test method for {@link business.impl.ParticipationImpl#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    assertTrue("Test equalsObject a échoué", sample.equals(participation));
    assertFalse("Test equalsObject a échoué", sample.equals(new ParticipationImpl()));
    assertFalse("Test equalsObject a échoué", sample.equals(null));
    assertTrue("Test equalsObject a échoué", sample.equals(sample));
    participation.setIdParticipation(24343);
    assertFalse("Test equalsObject a échoué", sample.equals(participation));
    participation.setIdParticipation(sample.getIdParticipation());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#hashCode()}.
   */
  @Test
  public void testHashCode() {
    assertEquals("Test hashCode a échoué", sample.hashCode(), participation.hashCode());
    participation.setIdParticipation(100000);
    assertNotEquals("Test hashCode a échoué", sample.hashCode(), participation.hashCode());
    participation.setIdParticipation(sample.getIdParticipation());
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation1() {
    this.participation.checkParticipation(null);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation2() {
    participation.setEtat(null);
    this.participation.checkParticipation(this.participation);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation3() {
    participation.setEtat(sample.getEtat());
    participation.setEtat("");
    this.participation.checkParticipation(this.participation);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation4() {
    participation.setEtat("fauxEtat");
    this.participation.checkParticipation(this.participation);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation5() {
    participation.setEvolution("");
    this.participation.checkParticipation(this.participation);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation6() {
    participation.setEvolution("fausseEvolution");
    this.participation.checkParticipation(this.participation);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation7() {
    participation.setEvolution(sample.getEvolution());
    participation.setNomEntreprise(null);
    this.participation.checkParticipation(this.participation);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation8() {
    participation.setNomEntreprise("");
    this.participation.checkParticipation(this.participation);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEtats1() {
    participation.setNomEntreprise(sample.getNomEntreprise());
    ParticipationImpl participation2 = sample;
    participation.setAnnulee(true);
    this.participation.checkEtats(participation, participation2);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEtats2() {
    participation.setAnnulee(false);
    participation.setEtat("refusee");
    ParticipationImpl participation2 = sample;
    this.participation.checkEtats(participation, participation2);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEtats3() {
    participation.setEtat("confirmee");
    ParticipationImpl participation2 = sample;
    this.participation.checkEtats(participation, participation2);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEvolutions1() {
    participation.setEvolution("facturee");
    ParticipationImpl participation2 = sample;
    participation2.setEtat("confirmee");
    participation2.setEvolution("facturee");
    this.participation.checkEvolutions(participation, participation2);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEvolutions2() {
    participation.setEvolution("payee");
    ParticipationImpl participation2 = sample;
    participation2.setEtat("confirmee");
    participation2.setEvolution("facturee");
    this.participation.checkEvolutions(participation, participation2);
  }
}
