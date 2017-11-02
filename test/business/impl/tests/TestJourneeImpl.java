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
import business.impl.JourneeImpl;
import business.impl.UtilisateurImpl;
import exceptions.InvalidJourneeDtoException;

/**
 * @author rachid
 *
 */
public class TestJourneeImpl {

  private BizFactory bizFactory;
  private JourneeImpl journee;
  private JourneeImpl sample;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.bizFactory = new BizFactoryStub();
    this.sample = (JourneeImpl) this.bizFactory.getJourneeDto();
    this.journee = (JourneeImpl) this.bizFactory.getJourneeDto();
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#hashCode()}.
   */
  @Test
  public void testHashCode() {
    assertEquals("Test hashCode a échoué", sample.hashCode(), journee.hashCode());
    journee.setIdJournee(100000);
    assertNotEquals("Test hashCode a échoué", sample.hashCode(), journee.hashCode());
    journee.setIdJournee(sample.getIdJournee());
  }

  /**
   * Test method for
   * {@link business.impl.JourneeImpl#JourneeImpl(int, java.time.LocalDate, business.dto.UtilisateurDto, boolean, int)}.
   */
  @Test
  public void testJourneeImplIntLocalDateUtilisateurDtoBooleanInt() {
    this.journee = new JourneeImpl(sample.getIdJournee(), sample.getDateJe(),
        sample.getIdCreateur(), sample.isCloturee(), sample.getNumVersion());
    assertEquals("Test du constructeur a échoué", sample, journee);
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#getNumVersion()}.
   */
  @Test
  public void testGetNumVersion() {
    assertEquals("Test getNumVersion a échoué", sample.getNumVersion(), journee.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#setNumVersion(int)}.
   */
  @Test
  public void testSetNumVersion() {
    journee.setNumVersion(3000);
    assertEquals("Test setNumVersion a échoué", 3000, journee.getNumVersion());
    journee.setNumVersion(sample.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#JourneeImpl()}.
   */
  @Test
  public void testJourneeImpl() {
    new JourneeImpl();
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#getIdJournee()}.
   */
  @Test
  public void testGetIdJournee() {
    assertEquals("Test getIdJournee a échoué", sample.getIdJournee(), journee.getIdJournee());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#setIdJournee(int)}.
   */
  @Test
  public void testSetIdJournee() {
    journee.setIdJournee(3000);
    assertEquals("Test setIdJournee a échoué", 3000, journee.getIdJournee());
    journee.setIdJournee(sample.getIdJournee());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#getDateJe()}.
   */
  @Test
  public void testGetDateJe() {
    assertEquals("Test getDateJe a échoué", sample.getDateJe(), journee.getDateJe());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#setDateJe(java.time.LocalDate)}.
   */
  @Test
  public void testSetDateJe() {
    LocalDate test = LocalDate.now();
    journee.setDateJe(test);
    assertEquals("Test setDateJe a échoué", test, journee.getDateJe());
    journee.setDateJe(sample.getDateJe());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#getIdCreateur()}.
   */
  @Test
  public void testGetIdCreateur() {
    assertEquals("Test getIdCreateur a échoué", sample.getIdCreateur(), journee.getIdCreateur());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#setIdCreateur(business.dto.UtilisateurDto)}.
   */
  @Test
  public void testSetIdCreateur() {
    UtilisateurImpl test = new UtilisateurImpl();
    journee.setIdCreateur(test);
    assertEquals("Test setIdCreateur a échoué", test, journee.getIdCreateur());
    journee.setIdCreateur(sample.getIdCreateur());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#isCloturee()}.
   */
  @Test
  public void testIsCloturee() {
    assertEquals("Test isCloturee a échoué", sample.isCloturee(), journee.isCloturee());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#setCloturee(boolean)}.
   */
  @Test
  public void testSetCloturee() {
    journee.setCloturee(!sample.isCloturee());
    assertEquals("Test setCommune a échoué", !sample.isCloturee(), journee.isCloturee());
    journee.setCloturee(sample.isCloturee());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#toString()}.
   */
  @Test
  public void testToString() {
    assertEquals("Test toString a échoué", sample.toString(), journee.toString());
  }

  /**
   * Test method for {@link business.impl.JourneeImpl#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    assertTrue("Test equalsObject a échoué", sample.equals(journee));
    assertFalse("Test equalsObject a échoué", sample.equals(new JourneeImpl()));
    assertFalse("Test equalsObject a échoué", sample.equals(null));
    assertTrue("Test equalsObject a échoué", sample.equals(sample));
    journee.setIdJournee(433445534);
    assertFalse("Test equalsObject a échoué", sample.equals(journee));
    journee.setIdJournee(sample.getIdJournee());
  }

  @Test
  public void testCheckJournee1() {
    this.journee.checkJournee(this.journee);
  }

  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee2() {
    this.journee.checkJournee(null);
  }

  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee3() {
    journee.setCloturee(true);
    this.journee.checkJournee(this.journee);
  }

  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee4() {
    journee.setCloturee(false);
    journee.setIdCreateur(null);
    this.journee.checkJournee(this.journee);
  }

  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee5() {
    journee.setIdCreateur(this.sample.getIdCreateur());
    journee.setDateJe(null);
    this.journee.checkJournee(this.journee);
  }

  @Test(expected = InvalidJourneeDtoException.class)
  public void testCheckJournee6() {
    journee.setDateJe(sample.getDateJe());
    LocalDate dateJe = LocalDate.of(2015, 3, 3);
    journee.setDateJe(dateJe);
    this.journee.checkJournee(this.journee);
  }
}
