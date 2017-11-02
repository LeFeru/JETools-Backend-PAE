package business.ucc.tests;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import business.dto.EntrepriseDto;
import business.dto.JourneeDto;
import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import business.ucc.implementations.PresenceUccImpl;
import business.ucc.interfaces.PresenceUcc;
import dal.dao.stubs.EntrepriseDaoStub;
import dal.dao.stubs.JourneeDaoStub;
import dal.dao.stubs.ParticipationDaoStub;
import dal.dao.stubs.PersonneContactDaoStub;
import dal.dao.stubs.PresenceDaoStub;
import dal.services.DalServicesStub;
import exceptions.BizException;

public class TestPresenceUcc {
  private PresenceUcc presenceUcc;
  private BizFactory bizFactory;
  private JourneeDto journee;
  private EntrepriseDto entreprise;

  @Before
  public void setUp() throws Exception {
    presenceUcc =
        new PresenceUccImpl(new PresenceDaoStub(), new BizFactoryImpl(), new DalServicesStub(),
            new EntrepriseDaoStub(), new JourneeDaoStub(), new PersonneContactDaoStub(),
            new ParticipationDaoStub());
    bizFactory = new BizFactoryStub();
    journee = bizFactory.getJourneeDto();
    entreprise = bizFactory.getEntrepriseDto();
  }

  @Test
  public void testIndiquerPresence1() {
    List<PersonneContactDto> list=new ArrayList<>();
    list.add(bizFactory.getPersonneContactDto());
    assertNotEquals(null, presenceUcc.indiquerPresence(entreprise, journee, list));
  }

  @Test(expected = BizException.class)
  public void testIndiquerPresence2() {
    entreprise.setNomEntreprise("fauxNom");
    List<PersonneContactDto> list = new ArrayList<>();
    list.add(bizFactory.getPersonneContactDto());
    assertNotEquals(null, presenceUcc.indiquerPresence(entreprise, journee, list));
  }

  @Test(expected = BizException.class)
  public void testIndiquerPresence3() {
    journee.setIdJournee(0);
    List<PersonneContactDto> list = new ArrayList<>();
    list.add(bizFactory.getPersonneContactDto());
    assertNotEquals(null, presenceUcc.indiquerPresence(entreprise, journee, list));
  }

  @Test(expected = BizException.class)
  public void testIndiquerPresence4() {
    List<PersonneContactDto> list = new ArrayList<>();
    PersonneContactDto personne = bizFactory.getPersonneContactDto();
    personne.setIdPersonne(0);
    list.add(personne);
    assertNotEquals(null, presenceUcc.indiquerPresence(entreprise, journee, list));
  }

  @Test
  public void testListerContactsParEntreprise1() {
    assertNotEquals(null, presenceUcc.listerContactsParEntreprise(entreprise, journee));
  }

  @Test(expected = BizException.class)
  public void testListerContactsParEntreprise2() {
    entreprise.setNomEntreprise("fauxnom");
    assertNotEquals(null, presenceUcc.listerContactsParEntreprise(entreprise, journee));
  }

  @Test(expected = BizException.class)
  public void testListerContactsParEntreprise3() {
    journee.setIdJournee(0);
    assertNotEquals(null, presenceUcc.listerContactsParEntreprise(entreprise, journee));
  }

}
