package dal.dao.stubs;

import business.dto.PresenceDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import dal.dao.interfaces.PresenceDao;
import exceptions.UncheckedSqlException;

/**
 * @author rachid.
 *
 */
public class PresenceDaoStub implements PresenceDao {
  private boolean throwSqlUncheckedException;
  private BizFactoryStub bizFactoryStub;
  private BizFactory bizFactory;

  /**
   * Constructeur PresenceDaoStub.
   */
  public PresenceDaoStub() {
    this.throwSqlUncheckedException = false;
    this.bizFactoryStub = new BizFactoryStub();
    this.bizFactory = new BizFactoryImpl();
  }

  public void setThrowSqlUncheckedException(boolean throwSqlUncheckedException) {
    this.throwSqlUncheckedException = throwSqlUncheckedException;
  }

  private void tryToThrowSqlUncheckedException(String message) {
    if (throwSqlUncheckedException) {
      throw new UncheckedSqlException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PresenceDao#insererPresence(business.dto.PresenceDto)
   */
  @Override
  public PresenceDto insererPresence(PresenceDto presenceDto) {
    tryToThrowSqlUncheckedException("Echec insererPresence");
    return presenceDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.PresenceDao#findPresenceByIdParticipationIdPersonne(business.dto.
   * PresenceDto)
   */
  @Override
  public PresenceDto findPresenceByIdParticipationIdPersonne(PresenceDto presenceDto) {
    tryToThrowSqlUncheckedException("Echec findPresenceByIdParticipationIdPersonne");
    PresenceDto presenceDtoTemp = this.bizFactoryStub.getPresenceDto();
    if (presenceDtoTemp.getIdParticipation() != presenceDto.getIdParticipation()
        || presenceDtoTemp.getIdPersonne() != presenceDto.getIdPersonne()) {
      presenceDtoTemp = bizFactory.getPresenceDto();
    }
    presenceDtoTemp.setIdParticipation(presenceDto.getIdParticipation());
    presenceDtoTemp.setIdPersonne(presenceDto.getIdPersonne());
    return presenceDtoTemp;
  }

}
