package business.ucc.stubs;

import business.dto.UtilisateurDto;
import business.ucc.interfaces.UtilisateurUcc;
import dal.dao.interfaces.UtilisateurDao;
import dal.dao.stubs.UtilisateurDaoStub;
import exceptions.BizException;
import exceptions.ForbiddenAccessException;

/**
 * @author rachid.
 *
 */
public class UtilisateurUccStub implements UtilisateurUcc {
  private boolean throwForbiddenAccessException;
  private boolean throwBizException;
  private UtilisateurDao utilisateurDaoStub;

  /**
   * Constructeur UtilisateurUccStub.
   */
  public UtilisateurUccStub() {
    this.throwForbiddenAccessException = false;
    this.throwBizException = false;
    this.utilisateurDaoStub = new UtilisateurDaoStub();
  }

  public void setThrowForbiddenAccessException(boolean throwForbiddenAccessException) {
    this.throwForbiddenAccessException = throwForbiddenAccessException;
  }

  public void setThrowBizException(boolean throwBizException) {
    this.throwBizException = throwBizException;
  }

  private void tryToThrowException(String message) {
    if (throwForbiddenAccessException) {
      throw new ForbiddenAccessException(message);
    }
    if (throwBizException) {
      throw new BizException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.UtilisateurUcc#connecterUtilisateur(business.dto.UtilisateurDto)
   */
  @Override
  public UtilisateurDto connecterUtilisateur(UtilisateurDto utilisateurDto) {
    tryToThrowException("Echec connecterUtilisateur");
    return utilisateurDaoStub.findUtilisateurByPseudo(utilisateurDto.getPseudo());
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.UtilisateurUcc#inscrireUtilisateur(business.dto.UtilisateurDto)
   */
  @Override
  public UtilisateurDto inscrireUtilisateur(UtilisateurDto utilisateurDto) {
    tryToThrowException("Echec inscrireUtilisateur");
    return utilisateurDaoStub.inscrireUtilisateur(utilisateurDto);

  }
  /*
   * @Override public void backup() { // TODO Auto-generated method stub
   * 
   * }
   */
}
