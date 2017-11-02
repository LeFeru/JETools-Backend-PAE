package business.biz.tests;

import org.junit.Before;
import org.junit.Test;

import business.dto.ParticipationDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.impl.ParticipationImpl;
import exceptions.InvalidParticipationDtoException;

public class ParticipationBiz {
  private ParticipationDto sample;
  private BizFactory bizFactory;
  private ParticipationImpl participationImpl;
  private ParticipationImpl participationImpl2;

  @Before
  public void setUp() {
    this.bizFactory = new BizFactoryStub();
    this.sample = this.bizFactory.getParticipationDto();
    this.participationImpl = (ParticipationImpl) this.bizFactory.getParticipationDto();
    this.participationImpl2 = (ParticipationImpl) this.bizFactory.getParticipationDto();
  }

  @Test
  public void testCheckParticipation1() {
    this.participationImpl.checkParticipation(this.participationImpl);
  }

  // test 2 inutile

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation3() {
    this.participationImpl.checkParticipation(null);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation6() {
    participationImpl.setNomEntreprise(sample.getNomEntreprise());
    participationImpl.setEtat(null);
    this.participationImpl.checkParticipation(this.participationImpl);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation7() {
    participationImpl.setNomEntreprise(sample.getNomEntreprise());
    participationImpl.setEtat("absolu");
    this.participationImpl.checkParticipation(this.participationImpl);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation8() {
    participationImpl.setNomEntreprise(sample.getNomEntreprise());
    participationImpl.setEtat("confirmee");
    participationImpl.setEvolution("absolu");
    this.participationImpl.checkParticipation(this.participationImpl);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckParticipation9() {
    participationImpl.setNomEntreprise(null);
    participationImpl.setEtat("confirmee");
    this.participationImpl.checkParticipation(this.participationImpl);
  }

  @Test
  public void testCheckEtats1() {
    participationImpl.setEtat("invitee");
    participationImpl2.setEtat("refusee");
    participationImpl.checkEtats(participationImpl, participationImpl2);
  }

  @Test
  public void testCheckEtats2() {
    participationImpl.setEtat("invitee");
    participationImpl2.setEtat("confirmee");
    participationImpl.checkEtats(participationImpl, participationImpl2);
  }

  @Test
  public void testCheckEtats3() {
    participationImpl.setEtat("confirmee");
    participationImpl2.setEtat("confirmee");
    participationImpl2.setEvolution("facturee");
    participationImpl.checkEtats(participationImpl, participationImpl2);
  }

  @Test
  public void testCheckEtats4() {
    participationImpl.setEtat("confirmee");
    participationImpl.setEvolution("facturee");
    participationImpl2.setEtat("confirmee");
    participationImpl2.setEvolution("payee");
    participationImpl.checkEtats(participationImpl, participationImpl2);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEtats5() {
    participationImpl.setEtat("invitee");
    participationImpl.setAnnulee(true);
    participationImpl2.setEtat("refusee");
    participationImpl.checkEtats(participationImpl, participationImpl2);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEtats6() {
    participationImpl2.setEtat("invitee");
    participationImpl.setEtat("refusee");
    participationImpl.checkEtats(participationImpl, participationImpl2);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEtats7() {
    participationImpl.setEtat("confirmee");
    participationImpl2.setEtat("refusee");
    participationImpl.checkEtats(participationImpl, participationImpl2);
  }

  @Test
  public void testCheckEvolutions1() {
    participationImpl.setEvolution("facturee");
    participationImpl2.setEvolution("payee");
    participationImpl.checkEvolutions(participationImpl, participationImpl2);
  }

  @Test
  public void testCheckEvolutions2() {
    participationImpl.setEvolution("payee");
    participationImpl2.setEvolution("payee");
    participationImpl.checkEvolutions(participationImpl, participationImpl2);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEvolutions3() {
    participationImpl.setEvolution("facturee");
    participationImpl2.setEvolution("facturee");
    participationImpl.checkEvolutions(participationImpl, participationImpl2);
  }

  @Test(expected = InvalidParticipationDtoException.class)
  public void testCheckEvolutions4() {
    participationImpl.setEvolution("payee");
    participationImpl2.setEvolution("facturee");
    participationImpl.checkEvolutions(participationImpl, participationImpl2);
  }
}
