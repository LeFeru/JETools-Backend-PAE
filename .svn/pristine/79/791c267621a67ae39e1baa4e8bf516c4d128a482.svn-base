package dal.dao.stubs;

import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import dal.dao.interfaces.UtilisateurDao;
import exceptions.UncheckedSqlException;

/**
 * @author rachid.
 *
 */
public class UtilisateurDaoStub implements UtilisateurDao {
  private boolean throwSqlUncheckedException;
  private boolean responsable;
  private BizFactoryStub bizFactoryStub;
  private BizFactory bizFactory;

  /**
   * Constructeur UtilisateurDaoStub.
   */
  public UtilisateurDaoStub() {
    this.throwSqlUncheckedException = false;
    this.responsable = false;
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

  public void setResponsable(boolean responsable) {
    this.responsable = responsable;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.UtilisateurDao#inscrireUtilisateur(business.dto.UtilisateurDto)
   */
  @Override
  public UtilisateurDto inscrireUtilisateur(UtilisateurDto utilisateurDto) {
    tryToThrowSqlUncheckedException("Echec inscrireUtilisateur");
    utilisateurDto.setId(1);
    return utilisateurDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.UtilisateurDao#findUtilisateurByPseudo(java.lang.String)
   */
  @Override
  public UtilisateurDto findUtilisateurByPseudo(String pseudo) {
    tryToThrowSqlUncheckedException("Echec findUtilisateurByPseudo");
    UtilisateurDto utilisateurDto = this.bizFactoryStub.getUtilisateurDto();
    if (!utilisateurDto.getPseudo().equals(pseudo)) {
      utilisateurDto.setId(0);
      utilisateurDto = bizFactory.getUtilisateurDto();
    }
    utilisateurDto.setPseudo(pseudo);
    return utilisateurDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.UtilisateurDao#findUtilisateurByEmail(java.lang.String)
   */
  @Override
  public UtilisateurDto findUtilisateurByEmail(String mail) {
    tryToThrowSqlUncheckedException("Echec findUtilisateurByEmail");
    UtilisateurDto utilisateurDto = this.bizFactoryStub.getUtilisateurDto();
    if (!utilisateurDto.getEmail().equals(mail)) {
      utilisateurDto.setId(0);
      utilisateurDto = bizFactory.getUtilisateurDto();
    }
    utilisateurDto.setEmail(mail);
    return utilisateurDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.UtilisateurDao#findUtilisateurById(int)
   */
  @Override
  public UtilisateurDto findUtilisateurById(int id) {
    tryToThrowSqlUncheckedException("Echec findUtilisateurById");
    UtilisateurDto utilisateurDto = this.bizFactoryStub.getUtilisateurDto();
    if (id != utilisateurDto.getId()) {
      utilisateurDto.setId(0);
      utilisateurDto = bizFactory.getUtilisateurDto();
    }
    utilisateurDto.setId(id);
    return utilisateurDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.UtilisateurDao#resetTentatives(java.lang.String)
   */
  @Override
  public void resetTentatives(String pseudo) {
    tryToThrowSqlUncheckedException("Echec resetTentatives");

  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.UtilisateurDao#majTentatives(java.lang.String)
   */
  @Override
  public void majTentatives(String pseudo) {
    tryToThrowSqlUncheckedException("Echec majTentatives");

  }

  /*
   * @Override public List<String> backup() { // TODO Auto-generated method stub return new
   * ArrayList<>(); }
   */

}
