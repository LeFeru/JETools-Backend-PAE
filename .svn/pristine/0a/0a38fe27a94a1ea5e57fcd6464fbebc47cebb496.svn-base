package dal.dao.stubs;

import java.util.ArrayList;
import java.util.List;

import business.dto.ParticipationDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import dal.dao.interfaces.ParticipationDao;
import exceptions.UncheckedSqlException;

/**
 * @author rachid.
 *
 */
public class ParticipationDaoStub implements ParticipationDao {

  private boolean throwSqlUncheckedException;
  private BizFactoryStub bizFactoryStub;
  private BizFactory bizFactory;

  /**
   * Constructeur ParticipationDaoStub.
   */
  public ParticipationDaoStub() {
    this.throwSqlUncheckedException = false;
    this.bizFactoryStub = new BizFactoryStub();
    this.bizFactory = new BizFactoryImpl();
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#insererParticipation(business.dto.ParticipationDto)
   */

  public void setThrowSqlUncheckedException(boolean throwSqlUncheckedException) {
    this.throwSqlUncheckedException = throwSqlUncheckedException;
  }

  private void tryToThrowSqlUncheckedException(String message) {
    if (throwSqlUncheckedException) {
      throw new UncheckedSqlException(message);
    }
  }

  @Override
  public ParticipationDto insererParticipation(ParticipationDto participationDto) {
    tryToThrowSqlUncheckedException("Echec insererParticipation");
    participationDto.setIdEntreprise(1);
    return participationDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#findParticipationByIdJourneeIdEntreprise(business.dto.
   * ParticipationDto)
   */
  @Override
  public ParticipationDto findParticipationByIdJourneeIdEntreprise(
      ParticipationDto participationDto) {
    tryToThrowSqlUncheckedException("Echec findParticipationByIdJourneeIdEntreprise");
    ParticipationDto participationDtoTemp = this.bizFactoryStub.getParticipationDto();
    participationDto.setIdParticipation(1);
    if (participationDtoTemp.getIdEntreprise() != participationDto.getIdEntreprise()
        || participationDtoTemp.getIdJournee() != participationDto.getIdJournee()) {
      participationDto.setIdParticipation(0);
      participationDto = bizFactory.getParticipationDto();
    }
    participationDtoTemp.setIdJournee(participationDto.getIdJournee());
    participationDtoTemp.setIdEntreprise(participationDto.getIdEntreprise());
    return participationDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#findParticipationByIdEntreprise(int)
   */
  @Override
  public List<ParticipationDto> findParticipationByIdEntreprise(int idEntreprise) {
    tryToThrowSqlUncheckedException("Echec findParticipationByIdEntreprise");
    ParticipationDto participationDto = this.bizFactoryStub.getParticipationDto();
    participationDto.setIdEntreprise(idEntreprise);
    ArrayList<ParticipationDto> liste = new ArrayList<ParticipationDto>();
    liste.add(participationDto);
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#annulerParticipation(business.dto.ParticipationDto)
   */
  @Override
  public void annulerParticipation(ParticipationDto participation) {
    tryToThrowSqlUncheckedException("Echec annulerParticipation");
    participation.setAnnulee(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.ParticipationDao#participationsNonAnnulees(int)
   */
  @Override
  public ArrayList<ParticipationDto> participationsNonAnnulees(int idJournee) {
    tryToThrowSqlUncheckedException("Echec participationsNonAnnulees");
    ParticipationDto participationDto = this.bizFactoryStub.getParticipationDto();
    participationDto.setIdJournee(idJournee);
    ArrayList<ParticipationDto> liste = new ArrayList<ParticipationDto>();
    liste.add(participationDto);
    return liste;
  }

  @Override
  public ParticipationDto modifierParticipation(ParticipationDto participation) {
    tryToThrowSqlUncheckedException("Echec modifierParticipation");
    return participation;
  }

  @Override
  public ParticipationDto findParticipationByIdParticipation(int idParticipation) {
    tryToThrowSqlUncheckedException("Echec findParticipationByIdJourneeIdEntreprise");
    ParticipationDto participationDtoTemp = this.bizFactoryStub.getParticipationDto();
    if (participationDtoTemp.getIdParticipation() != idParticipation) {
      participationDtoTemp.setIdParticipation(0);
      participationDtoTemp = bizFactory.getParticipationDto();
    }
    return participationDtoTemp;
  }

}
