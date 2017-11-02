package business.biz.tests;

import org.junit.Before;
import org.junit.Test;

import business.dto.EntrepriseDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.impl.EntrepriseImpl;
import exceptions.InvalidEntrepriseDtoException;

public class EntrepriseBiz {

  private EntrepriseDto sample;
  private BizFactory bizFactory;
  private EntrepriseImpl entrepriseImpl;

  @Before
  public void setUp() {
    this.bizFactory = new BizFactoryStub();
    this.sample = this.bizFactory.getEntrepriseDto();
    this.entrepriseImpl = (EntrepriseImpl) this.bizFactory.getEntrepriseDto();
  }

  @Test
  public void testCheckEntreprise1() {
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  // test 2 inutile

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise3() {
    this.entrepriseImpl.checkEntreprise(null);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise4() {
    entrepriseImpl.setNomEntreprise(null);
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise5() {
    entrepriseImpl.setNomEntreprise(this.sample.getNomEntreprise());
    entrepriseImpl.setRue(null);
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise6() {
    entrepriseImpl.setRue(this.sample.getRue());
    entrepriseImpl.setNumero(null);
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise7() {
    entrepriseImpl.setNumero(this.sample.getNumero());
    entrepriseImpl.setCodePostal(null);
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise8() {
    entrepriseImpl.setCodePostal(this.sample.getCodePostal());
    entrepriseImpl.setCommune(null);
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }


  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise9() {
    entrepriseImpl.setBoite(this.sample.getRue());
    entrepriseImpl.setNomEntreprise(
        "azertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazerty");
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise10() {
    entrepriseImpl.setNomEntreprise(this.sample.getNomEntreprise());
    entrepriseImpl.setRue(
        "azertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazerty");
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise11() {
    entrepriseImpl.setRue(this.sample.getRue());
    entrepriseImpl.setNumero(" *%* ))");
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise12() {
    entrepriseImpl.setNumero(this.sample.getNumero());
    entrepriseImpl.setBoite("**");
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise13() {
    entrepriseImpl.setBoite(this.sample.getBoite());
    entrepriseImpl.setCodePostal("***");
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test(expected = InvalidEntrepriseDtoException.class)
  public void testCheckEntreprise14() {
    entrepriseImpl.setCodePostal(this.sample.getCodePostal());
    entrepriseImpl.setCommune(
        "azertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazertyazerty");
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }

  @Test
  public void testCheckEntreprise15() {
    entrepriseImpl.setCommune(this.sample.getCommune());
    entrepriseImpl.setBoite(null);
    this.entrepriseImpl.checkEntreprise(this.entrepriseImpl);
  }
}
