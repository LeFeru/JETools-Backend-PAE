package business.ucc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import business.dto.EntrepriseDto;
import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import business.ucc.implementations.EntrepriseUccImpl;
import business.ucc.interfaces.EntrepriseUcc;
import dal.dao.stubs.EntrepriseDaoStub;
import dal.dao.stubs.JourneeDaoStub;
import dal.dao.stubs.ParticipationDaoStub;
import dal.dao.stubs.UtilisateurDaoStub;
import dal.services.DalServicesStub;
import exceptions.BizException;

public class TestEntrepriseUcc {
  private EntrepriseUcc entrepriseUcc;
  private BizFactory bizFactory;
  private EntrepriseDto entreprise1;

  @Before
  public void setUp() throws Exception {
    entrepriseUcc =
        new EntrepriseUccImpl(new EntrepriseDaoStub(), new BizFactoryImpl(), new DalServicesStub(),
            new UtilisateurDaoStub(), new ParticipationDaoStub(), new JourneeDaoStub());
    bizFactory = new BizFactoryStub();
    entreprise1 = bizFactory.getEntrepriseDto();
  }

  @Test
  public void testCreerEntreprise1() {
    entreprise1.setNomEntreprise("test1");
    entrepriseUcc.creerEntreprise(entreprise1);
    assertEquals(1, entreprise1.getIdEntreprise());
  }


  @Test(expected = BizException.class)
  public void testCreerEntreprise2() {
    entrepriseUcc.creerEntreprise(entreprise1);
    assertEquals(1, entreprise1.getIdEntreprise());
  }

  @Test(expected = BizException.class)
  public void testCreerEntreprise3() {
    entreprise1.setNomEntreprise("test1");
    UtilisateurDto utilisateur = bizFactory.getUtilisateurDto();
    utilisateur.setPseudo("pseudoFaux");
    entreprise1.setCreateur(utilisateur);
    entrepriseUcc.creerEntreprise(entreprise1);
    assertEquals(1, entreprise1.getIdEntreprise());
  }

  @Test
  public void testListeEntreprises() {
    assertNotEquals(null, entrepriseUcc.listeEntreprises());
  }

  @Test
  public void testModifierEntreprise1() {
    entreprise1.setNomEntreprise("changementNom");
    entrepriseUcc.modifierEntreprise(entreprise1);
    assertEquals(1, entreprise1.getIdEntreprise());
  }

  @Test(expected = BizException.class)
  public void testModifierEntreprise2() {
    entreprise1.setIdEntreprise(2);
    entrepriseUcc.modifierEntreprise(entreprise1);
    assertEquals(1, entreprise1.getIdEntreprise());
  }

  @Test
  public void testChargerEntreprise1() {
    entrepriseUcc.chargerEntreprise(entreprise1.getIdEntreprise());
    assertEquals(1, entreprise1.getIdEntreprise());
  }

  @Test(expected = BizException.class)
  public void testChargerEntreprise2() {
    entreprise1.setIdEntreprise(2);
    entrepriseUcc.chargerEntreprise(entreprise1.getIdEntreprise());
  }

  @Test
  public void testHistoriqueEntreprise1() {
    assertNotEquals(null, entrepriseUcc.historiqueEntreprise(entreprise1));
  }

  @Test(expected = BizException.class)
  public void testHistoriqueEntreprise2() {
    entreprise1.setIdEntreprise(2);
    assertNotEquals(null, entrepriseUcc.historiqueEntreprise(entreprise1));
  }
}
