package business.ucc.stubs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import business.biz.ParticipationBiz;
import business.dto.EntrepriseDto;
import business.dto.JourneeDto;
import business.dto.ParticipationDto;
import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.ucc.interfaces.ParticipationUcc;
import dal.dao.interfaces.EntrepriseDao;
import dal.dao.interfaces.JourneeDao;
import dal.dao.interfaces.ParticipationDao;
import dal.dao.interfaces.PersonneContactDao;
import dal.dao.stubs.EntrepriseDaoStub;
import dal.dao.stubs.JourneeDaoStub;
import dal.dao.stubs.ParticipationDaoStub;
import dal.dao.stubs.PersonneContactDaoStub;
import exceptions.BizException;
import exceptions.InvalidParticipationDtoException;

/**
 * @author rachid.
 *
 */
public class ParticipationUccStub implements ParticipationUcc {
  private boolean throwBizException;
  private boolean throwInvalidParticipationDtoException;
  private BizFactory bizFactoryStub;
  private JourneeDao journeeDaoStub;
  private EntrepriseDao entrepriseDaoStub;
  private ParticipationDao participationDaoStub;
  private PersonneContactDao personneContactDaoStub;

  /**
   * Constructeur ParticipationUccStub.
   */
  public ParticipationUccStub() {
    this.throwBizException = false;
    this.bizFactoryStub = new BizFactoryStub();
    this.journeeDaoStub = new JourneeDaoStub();
    this.entrepriseDaoStub = new EntrepriseDaoStub();
    this.participationDaoStub = new ParticipationDaoStub();
    this.personneContactDaoStub = new PersonneContactDaoStub();
  }

  public void setThrowInvalidParticipationDtoException(
      boolean throwInvalidParticipationDtoException) {
    this.throwInvalidParticipationDtoException = throwInvalidParticipationDtoException;
  }

  public void setThrowBizException(boolean throwBizException) {
    this.throwBizException = throwBizException;
  }

  private void tryToThrowException(String message) {
    if (throwInvalidParticipationDtoException) {
      throw new InvalidParticipationDtoException(message);
    }
    if (throwBizException) {
      throw new BizException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#inviter(java.util.List)
   */
  @Override
  public List<PersonneContactDto> inviter(List<EntrepriseDto> entrepriseDtos) {
    tryToThrowException("Echec inviter");
    ParticipationDto participationDto = bizFactoryStub.getParticipationDto();
    JourneeDto journeeDto = journeeDaoStub.findJourneeActive();
    List<PersonneContactDto> res = new ArrayList<>();
    for (EntrepriseDto entrepriseDto : entrepriseDtos) {
      try {
        entrepriseDto =
            entrepriseDaoStub.findEntrepriseByNomEntreprise(entrepriseDto.getNomEntreprise());
        participationDto.setIdEntreprise(entrepriseDto.getIdEntreprise());
        participationDto.setIdJournee(journeeDto.getIdJournee());
        participationDto =
            participationDaoStub.findParticipationByIdJourneeIdEntreprise(participationDto);
        participationDto = participationDaoStub.insererParticipation(participationDto);
        List<PersonneContactDto> listPersonnes =
            personneContactDaoStub.findPersonneByIdEntreprise(entrepriseDto);
        participationDto.setEtat("invitee");
        res.addAll(listPersonnes);
      } catch (InvalidParticipationDtoException invalidParticipationDtoException) {
        continue;
      }
    }
    return res;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#listeSelectionEntreprises()
   */
  @Override
  public ArrayList<EntrepriseDto> listeSelectionEntreprises() {
    tryToThrowException("Echec listeSelectionEntreprises");
    JourneeDto journeeDto = journeeDaoStub.findJourneeActive();
    ArrayList<EntrepriseDto> res = entrepriseDaoStub.getSelectionEntreprises();
    ArrayList<EntrepriseDto> temp = new ArrayList<>();
    ParticipationDto participationDto = bizFactoryStub.getParticipationDto();
    participationDto.setIdJournee(journeeDto.getIdJournee());
    for (EntrepriseDto entreprise : res) {
      participationDto.setIdEntreprise(entreprise.getIdEntreprise());
      participationDto =
          participationDaoStub.findParticipationByIdJourneeIdEntreprise(participationDto);
      if (participationDto.getIdParticipation() < 1) {
        temp.add(entreprise);
      }
    }
    return temp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#listeEntrepriseNonSelectionnees()
   */
  @Override
  public ArrayList<EntrepriseDto> listeEntrepriseNonSelectionnees() {
    tryToThrowException("Echec listeEntrepriseNonSelectionnees");
    ArrayList<EntrepriseDto> temp = this.listeSelectionEntreprises();
    JourneeDto journeeTemp = journeeDaoStub.findJourneeActive();
    ArrayList<EntrepriseDto> res =
        entrepriseDaoStub.entreprisesNonSelectionnes(journeeTemp.getIdJournee());
    for (EntrepriseDto entreprise : temp) {
      res.remove(entreprise);
    }
    return res;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#entrepriseInvitees(int)
   */
  @Override
  public ArrayList<EntrepriseDto> entrepriseInvitees(int idJournee) {
    tryToThrowException("Echec entrepriseInvitees");
    return entrepriseDaoStub.entreprisesInvitees(idJournee);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.ParticipationUcc#annulerParticipation(business.dto.ParticipationDto)
   */
  @Override
  public ParticipationDto annulerParticipation(ParticipationDto participation) {
    tryToThrowException("Echec annulerParticipation");
    participationDaoStub.annulerParticipation(participation);
    return participation;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.ParticipationUcc#participationsNonAnnulees(business.dto.
   * ParticipationDto)
   */
  @Override
  public ArrayList<ParticipationDto> participationsNonAnnulees(ParticipationDto participation) {
    tryToThrowException("Echec participationsNonAnnulees");
    ArrayList<ParticipationDto> liste =
        participationDaoStub.participationsNonAnnulees(participation.getIdJournee());
    for (ParticipationDto temp : liste) {
      String nom = entrepriseDaoStub.findEntrepriseById(temp.getIdEntreprise()).getNomEntreprise();
      temp.setNomEntreprise(nom);
    }
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * business.ucc.interfaces.ParticipationUcc#modifierParticipation(business.dto.ParticipationDto)
   */
  @Override
  public ParticipationDto modifierParticipation(ParticipationDto participation) {
    tryToThrowException("Echec modificationParticipation");
    ParticipationBiz participationBiz = (ParticipationBiz) bizFactoryStub.getParticipationDto();
    participationBiz.checkParticipation(participation);
    participationDaoStub.modifierParticipation(participation);
    if (participation.getEvolution() == "payee") {
      EntrepriseDto entreprise =
          entrepriseDaoStub.findEntrepriseById(participation.getIdEntreprise());
      LocalDate datePaye = LocalDate.now();
      entreprise.setDateDerniereParticipationPayee(datePaye);
      entrepriseDaoStub.modifierDatePaye(entreprise);
    }
    return participation;
  }

}
