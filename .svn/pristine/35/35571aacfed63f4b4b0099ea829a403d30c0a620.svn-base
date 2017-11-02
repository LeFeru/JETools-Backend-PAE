/**
 * 
 */
package business.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.impl.PresenceImpl;

/**
 * @author rachid
 *
 */
public class TestPresenceImpl {

  private BizFactory bizFactory;
  private PresenceImpl presence;
  private PresenceImpl sample;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.bizFactory = new BizFactoryStub();
    this.presence = (PresenceImpl) this.bizFactory.getPresenceDto();
    this.sample = (PresenceImpl) this.bizFactory.getPresenceDto();
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#PresenceImpl(int, int)}.
   */
  @Test
  public void testPresenceImplIntInt() {
    new PresenceImpl(1, 1);
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#PresenceImpl()}.
   */
  @Test
  public void testPresenceImpl() {
    new PresenceImpl();
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#getIdParticipation()}.
   */
  @Test
  public void testGetIdParticipation() {
    assertEquals("Test getIdParticipation a échoué", sample.getIdParticipation(),
        presence.getIdParticipation());
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#setIdParticipation(int)}.
   */
  @Test
  public void testSetIdParticipation() {
    presence.setIdParticipation(3000);
    assertEquals("Test setIdParticipation a échoué", 3000, presence.getIdParticipation());
    presence.setIdParticipation(sample.getIdParticipation());
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#getIdPersonne()}.
   */
  @Test
  public void testGetIdPersonne() {
    assertEquals("Test getIdPersonne a échoué", sample.getIdPersonne(), presence.getIdPersonne());
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#setIdPersonne(int)}.
   */
  @Test
  public void testSetIdPersonne() {
    presence.setIdPersonne(3000);
    assertEquals("Test setIdPersonne a échoué", 3000, presence.getIdPersonne());
    presence.setIdPersonne(sample.getIdPersonne());
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#getNumVersion()}.
   */
  @Test
  public void testGetNumVersion() {
    assertEquals("Test getNumVersion a échoué", sample.getNumVersion(), presence.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#setNumVersion(int)}.
   */
  @Test
  public void testSetNumVersion() {
    presence.setNumVersion(3000);
    assertEquals("Test setNumVersion a échoué", 3000, presence.getNumVersion());
    presence.setNumVersion(sample.getNumVersion());
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#toString()}.
   */
  @Test
  public void testToString() {
    assertEquals("Test toString a échoué", sample.toString(), presence.toString());
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    assertTrue("Test equalsObject a échoué", sample.equals(presence));
    assertFalse("Test equalsObject a échoué", sample.equals(new PresenceImpl()));
    assertFalse("Test equalsObject a échoué", sample.equals(null));
    assertTrue("Test equalsObject a échoué", sample.equals(sample));
    presence.setIdParticipation(34343434);
    assertFalse("Test equalsObject a échoué", sample.equals(presence));
    presence.setIdParticipation(sample.getIdParticipation());
    presence.setIdPersonne(83434343);
    assertFalse("Test equalsObject a échoué", sample.equals(presence));
    presence.setIdPersonne(sample.getIdPersonne());
  }

  /**
   * Test method for {@link business.impl.PresenceImpl#hashCode()}.
   */
  @Test
  public void testHashCode() {
    assertEquals("Test hashCode a échoué", sample.hashCode(), presence.hashCode());
    presence.setIdPersonne(100000);
    assertNotEquals("Test hashCode a échoué", sample.hashCode(), presence.hashCode());
    presence.setIdPersonne(sample.getIdPersonne());
    presence.setIdParticipation(1343400);
    assertNotEquals("Test hashCode a échoué", sample.hashCode(), presence.hashCode());
    presence.setIdParticipation(sample.getIdParticipation());
  }

}
