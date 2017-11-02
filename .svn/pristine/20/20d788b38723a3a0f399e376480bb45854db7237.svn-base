package business.ucc.stubs;

import java.util.ArrayList;
import java.util.List;

import business.dto.EntrepriseDto;
import business.dto.JourneeDto;
import business.dto.ParticipationDto;
import business.dto.PersonneContactDto;
import business.dto.PresenceDto;
import business.factories.BizFactoryStub;
import business.ucc.interfaces.PresenceUcc;
import dal.dao.interfaces.EntrepriseDao;
import dal.dao.interfaces.JourneeDao;
import dal.dao.interfaces.ParticipationDao;
import dal.dao.interfaces.PersonneContactDao;
import dal.dao.interfaces.PresenceDao;
import dal.dao.stubs.EntrepriseDaoStub;
import dal.dao.stubs.JourneeDaoStub;
import dal.dao.stubs.ParticipationDaoStub;
import dal.dao.stubs.PersonneContactDaoStub;
import dal.dao.stubs.PresenceDaoStub;
import exceptions.BizException;

/**
 * @author rachid.
 *
 */
public class PresenceUccStub implements PresenceUcc {

  private boolean throwBizException;
  private PresenceDao presenceDaoStub;
  private EntrepriseDao entrepriseDaoStub;
  private JourneeDao journeeDaoStub;
  private ParticipationDao participationDaoStub;
  private PersonneContactDao personneContactDaoStub;
  private BizFactoryStub bizFactoryStub;

  /**
   * Constructeur PresenceUccStub.
   */
  public PresenceUccStub() {
    this.throwBizException = false;
    this.presenceDaoStub = new PresenceDaoStub();
    this.entrepriseDaoStub = new EntrepriseDaoStub();
    this.journeeDaoStub = new JourneeDaoStub();
    this.participationDaoStub = new ParticipationDaoStub();
    this.personneContactDaoStub = new PersonneContactDaoStub();
    this.bizFactoryStub = new BizFactoryStub();
  }

  public void setThrowBizException(boolean throwBizException) {
    this.throwBizException = throwBizException;
  }

  private void tryToThrowException(String message) {
    if (throwBizException) {
      throw new BizException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.PresenceUcc#indiquerPresence(business.dto.EntrepriseDto,
   * java.util.List)
   */
  @Override
  public List<PresenceDto> indiquerPresence(EntrepriseDto entrepriseDto, JourneeDto journee,
      List<PersonneContactDto> personneContactDtos) {
    tryToThrowException("Echec indiquerPresence");
    JourneeDto journeeDto = journeeDaoStub.findJourneeActive();
    entrepriseDto =
        entrepriseDaoStub.findEntrepriseByNomEntreprise(entrepriseDto.getNomEntreprise());
    ParticipationDto participationDto = bizFactoryStub.getParticipationDto();
    participationDto.setIdEntreprise(entrepriseDto.getIdEntreprise());
    participationDto.setIdJournee(journeeDto.getIdJournee());
    participationDto =
        participationDaoStub.findParticipationByIdJourneeIdEntreprise(participationDto);
    PresenceDto presenceDto = bizFactoryStub.getPresenceDto();
    presenceDto.setIdParticipation(participationDto.getIdParticipation());
    List<PresenceDto> res = new ArrayList<>();
    for (PersonneContactDto personneContactDto : personneContactDtos) {
      personneContactDto =
          personneContactDaoStub.findPersonneById(personneContactDto.getIdPersonne());
      presenceDto.setIdPersonne(personneContactDto.getIdPersonne());
      PresenceDto presenceDto2 =
          presenceDaoStub.findPresenceByIdParticipationIdPersonne(presenceDto);
      if (presenceDto2.getIdParticipation() < 1) {
        presenceDaoStub.insererPresence(presenceDto);
        res.add(presenceDto);
      }
    }
    return res;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.PresenceUcc#listerContactsParEntreprise(business.dto.EntrepriseDto,
   * business.dto.PresenceDto)
   */
  @Override
  public List<PersonneContactDto> listerContactsParEntreprise(EntrepriseDto entrepriseDto,
      JourneeDto journeeDto) {
    tryToThrowException("Echec listerContactsParEntreprise");
    entrepriseDto =
        this.entrepriseDaoStub.findEntrepriseByNomEntreprise(entrepriseDto.getNomEntreprise());
    journeeDto = this.journeeDaoStub.findJourneeById(journeeDto.getIdJournee());
    ParticipationDto participationDto = bizFactoryStub.getParticipationDto();
    participationDto.setIdEntreprise(entrepriseDto.getIdEntreprise());
    participationDto.setIdJournee(journeeDto.getIdJournee());
    participationDto =
        participationDaoStub.findParticipationByIdJourneeIdEntreprise(participationDto);
    List<PersonneContactDto> personnesContact =
        personneContactDaoStub.findPersonneByIdEntreprise(entrepriseDto);
    PresenceDto presenceDto = bizFactoryStub.getPresenceDto();
    for (PersonneContactDto personneContactDto : personnesContact) {
      presenceDto.setIdParticipation(participationDto.getIdParticipation());
      presenceDto.setIdPersonne(personneContactDto.getIdPersonne());
      presenceDto = presenceDaoStub.findPresenceByIdParticipationIdPersonne(presenceDto);
      if (presenceDto.getIdParticipation() > 0 && presenceDto.getIdPersonne() > 0) {
        personneContactDto.setPresent(true);
      }
    }
    return personnesContact;
  }

}
