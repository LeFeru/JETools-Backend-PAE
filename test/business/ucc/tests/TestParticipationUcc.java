package business.ucc.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import business.dto.EntrepriseDto;
import business.dto.ParticipationDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import business.ucc.implementations.ParticipationUccImpl;
import business.ucc.interfaces.ParticipationUcc;
import dal.dao.stubs.EntrepriseDaoStub;
import dal.dao.stubs.JourneeDaoStub;
import dal.dao.stubs.ParticipationDaoStub;
import dal.dao.stubs.PersonneContactDaoStub;
import dal.services.DalServicesStub;
import exceptions.BizException;

public class TestParticipationUcc {
  private ParticipationUcc participationUcc;
  private BizFactory bizFactory;
  private ParticipationDto participationDto;

  @Before
  public void setUp() throws Exception {
    participationUcc = new ParticipationUccImpl(new ParticipationDaoStub(), new BizFactoryImpl(),
        new DalServicesStub(), new EntrepriseDaoStub(), new JourneeDaoStub(),
        new PersonneContactDaoStub());
    bizFactory = new BizFactoryStub();
    participationDto = bizFactory.getParticipationDto();
  }

  @Test(expected = BizException.class)
  public void testInviter1() {
    participationUcc.inviter(null);
  }

  @Test
  public void testInviter2() {
    EntrepriseDto entrepriseDto = bizFactory.getEntrepriseDto();
    List<EntrepriseDto> list = new ArrayList<>();
    list.add(entrepriseDto);
    assertNotEquals(null, participationUcc.inviter(list));
  }

  @Test(expected = BizException.class)
  public void testInviter3() {
    EntrepriseDto entrepriseDto = bizFactory.getEntrepriseDto();
    entrepriseDto.setNomEntreprise("fauxNom");
    List<EntrepriseDto> list = new ArrayList<>();
    list.add(entrepriseDto);
    assertNotEquals(null, participationUcc.inviter(list));
  }

  @Test
  public void testListeSelectionEntreprises() {
    assertNotEquals(null, participationUcc.listeSelectionEntreprises());
  }

  @Test
  public void testListeEntrepriseNonSelectionnees() {
    assertNotEquals(null, participationUcc.listeEntrepriseNonSelectionnees());
  }

  @Test
  public void testEntrepriseInvitees1() {
    int idJournee = bizFactory.getJourneeDto().getIdJournee();
    assertNotEquals(null, participationUcc.entrepriseInvitees(idJournee));
  }

  @Test
  public void testEntrepriseInvitees2() {
    if (!participationUcc.entrepriseInvitees(0).isEmpty()) {
      fail("Journée inexistante");
    }
  }

  @Test(expected = BizException.class)
  public void testAnnulerParticipation() {
    participationDto.setEtat("refusee");
    assertNotEquals(null, participationUcc.annulerParticipation(participationDto));
  }

  @Test
  public void testParticipationsNonAnnulees() {
    assertNotEquals(null, participationUcc.participationsNonAnnulees(participationDto));
  }

  @Test
  public void testModifierParticipation1() {
    assertNotEquals(null, participationUcc.modifierParticipation(participationDto));
  }

  @Test(expected = BizException.class)
  public void testModifierParticipation2() {
    participationDto.setNomEntreprise("fauxNom");
    assertNotEquals(null, participationUcc.modifierParticipation(participationDto));
  }

  @Test(expected = BizException.class)
  public void testModifierParticipation3() {
    participationDto.setIdParticipation(0);
    assertNotEquals(null, participationUcc.modifierParticipation(participationDto));
  }
}
