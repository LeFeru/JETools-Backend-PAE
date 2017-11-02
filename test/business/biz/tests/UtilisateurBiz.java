package business.biz.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.impl.UtilisateurImpl;
import exceptions.InvalidUtilisateurDtoException;

public class UtilisateurBiz {
  private UtilisateurDto sample;
  private BizFactory bizFactory;
  private UtilisateurImpl utilisateurImpl;
  private UtilisateurImpl utilisateurImpl2;

  @Before
  public void setUp() {
    this.bizFactory = new BizFactoryStub();
    this.sample = this.bizFactory.getUtilisateurDto();
    this.utilisateurImpl = (UtilisateurImpl) this.bizFactory.getUtilisateurDto();
    this.utilisateurImpl2 = (UtilisateurImpl) this.bizFactory.getUtilisateurDto();
  }

  @Test
  public void testCheckMdp1() {
    utilisateurImpl.setMdp("$2a$12$wQkut1PEVKV3SitFsnmn9ODsISc2RCqVI3Zyh.CEjFfKbEOu4u6I2");
    utilisateurImpl2.setMdp("$2a$12$wQkut1PEVKV3SitFsnmn9ODsISc2RCqVI3Zyh.CEjFfKbEOu4u6I2");
    utilisateurImpl.checkMdp(utilisateurImpl, utilisateurImpl2);
  }

  @Test
  public void testCheckMdp2() {
    utilisateurImpl.setMdp("$2a$12$wQkut1PEVKV3SitFsnmn9ODsISc2RCqVI3Zyh.CEjFfKbEOu4u6I2");
    utilisateurImpl2.setMdp("$2a$12$8X3EJi01HshlKpFCKcztB.jhwhANopC3mGw0MfS7Lwmy/tynGtRRW");
    assertEquals(false, utilisateurImpl.checkMdp(utilisateurImpl, utilisateurImpl2));
  }

  @Test
  public void testCheckBeforeConnexion1() {
    utilisateurImpl.checkBeforeConnexion();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeConnexion2() {
    utilisateurImpl.setMdp(null);
    utilisateurImpl.checkBeforeConnexion();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeConnexion3() {
    utilisateurImpl.setPseudo(null);
    utilisateurImpl.checkBeforeConnexion();
  }

  @Test
  public void testCheckBeforeInscription1() {
    utilisateurImpl.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeInscription2() {
    utilisateurImpl.setNom(null);
    utilisateurImpl.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeInscription3() {
    utilisateurImpl.setPrenom(null);
    utilisateurImpl.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeInscription4() {
    utilisateurImpl.setEmail(null);
    utilisateurImpl.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeInscription5() {
    utilisateurImpl.setNom("te*st");
    utilisateurImpl.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeInscription6() {
    utilisateurImpl.setPrenom("te*st");
    utilisateurImpl.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeInscription7() {
    utilisateurImpl.setMdp("test1");
    utilisateurImpl.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeInscription8() {
    utilisateurImpl.setEmail("testMail");
    utilisateurImpl.checkBeforeInscription();
  }

  @Test(expected = InvalidUtilisateurDtoException.class)
  public void testCheckBeforeInscription9() {
    utilisateurImpl.setPseudo("tes");
    utilisateurImpl.checkBeforeInscription();
  }
}
