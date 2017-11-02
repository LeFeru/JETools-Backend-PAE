package business.ucc.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import business.ucc.implementations.UtilisateurUccImpl;
import business.ucc.interfaces.UtilisateurUcc;
import dal.dao.stubs.UtilisateurDaoStub;
import dal.services.DalServicesStub;
import exceptions.BizException;

public class TestUtilisateurUcc {
  private UtilisateurUcc utilisateurUcc;
  private BizFactory bizFactory;
  private UtilisateurDto utilisateur1;

  @Before
  public void setUp() throws Exception {
    utilisateurUcc = new UtilisateurUccImpl(new UtilisateurDaoStub(), new BizFactoryImpl(),
        new DalServicesStub());
    bizFactory = new BizFactoryStub();
    utilisateur1 = bizFactory.getUtilisateurDto();
  }


  @Test
  public void testConnecterUtilisateur1() {
    utilisateur1.setMdp("stubstub1");
    utilisateur1.setMdpConfirme("stubstub1");
    assertEquals(1, utilisateurUcc.connecterUtilisateur(utilisateur1).getId());
  }

  @Test(expected = BizException.class)
  public void testConnecterUtilisateur2() {
    utilisateur1.setMdp("stubstub");
    utilisateur1.setMdpConfirme("stubstub");
    assertEquals(1, utilisateurUcc.connecterUtilisateur(utilisateur1).getId());
  }

  @Test(expected = BizException.class)
  public void testConnecterUtilisateur3() {
    utilisateur1.setMdp("stubstub1");
    utilisateur1.setMdpConfirme("stubstub1");
    utilisateur1.setPseudo("mauvaisPseudo");
    assertEquals(1, utilisateurUcc.connecterUtilisateur(utilisateur1).getId());
  }

  @Test
  public void testInscrireUtilisateur1() {
    utilisateur1.setMdp("stubstub1");
    utilisateur1.setMdpConfirme("stubstub1");
    utilisateur1.setEmail("nouveauEmail@test.com");
    utilisateur1.setPseudo("nouveauPseudo");
    assertEquals(1, utilisateurUcc.inscrireUtilisateur(utilisateur1).getId());
  }

  @Test(expected = BizException.class)
  public void testInscrireUtilisateur2() {
    utilisateur1.setPseudo("nouveauPseudo");
    assertEquals(1, utilisateurUcc.inscrireUtilisateur(utilisateur1).getId());
  }

  @Test(expected = BizException.class)
  public void testInscrireUtilisateur3() {
    utilisateur1.setEmail("nouveauEmail@test.com");
    assertEquals(1, utilisateurUcc.inscrireUtilisateur(utilisateur1).getId());
  }

  /*
   * @Test public void testBackup() { utilisateurUcc.backup(); }
   */
}
