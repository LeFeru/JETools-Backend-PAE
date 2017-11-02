package dal.dao.stubs;

import java.util.ArrayList;

import business.dto.EntrepriseDto;
import business.factories.BizFactory;
import business.factories.BizFactoryImpl;
import business.factories.BizFactoryStub;
import dal.dao.interfaces.EntrepriseDao;
import exceptions.UncheckedSqlException;

/**
 * @author rachid.
 *
 */
public class EntrepriseDaoStub implements EntrepriseDao {
  private boolean throwSqlUncheckedException;
  private BizFactoryStub bizFactoryStub;
  private BizFactory bizFactory;

  /**
   * Constructeur EntrepriseDaoStub.
   */
  public EntrepriseDaoStub() {
    this.throwSqlUncheckedException = false;
    this.bizFactoryStub = new BizFactoryStub();
    this.bizFactory = new BizFactoryImpl();
  }

  private void setThrowSqlUncheckedException(boolean throwSqlUncheckedException) {
    this.throwSqlUncheckedException = throwSqlUncheckedException;;
  }

  private void tryToThrowSqlUncheckedException(String message) {
    if (throwSqlUncheckedException) {
      throw new UncheckedSqlException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#creerEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto creerEntreprise(EntrepriseDto entreprise) {
    tryToThrowSqlUncheckedException("Echec insertEntreprise");
    entreprise.setIdEntreprise(1);
    return entreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#findEntrepriseByNomEntreprise(java.lang.String)
   */
  @Override
  public EntrepriseDto findEntrepriseByNomEntreprise(String nomEntreprise) {
    tryToThrowSqlUncheckedException("Echec FindEntreprise");
    EntrepriseDto entrepriseDto = this.bizFactoryStub.getEntrepriseDto();
    if (!entrepriseDto.getNomEntreprise().equals(nomEntreprise)) {
      //entrepriseDto.setIdEntreprise(0);
      entrepriseDto = bizFactory.getEntrepriseDto();
    }
    entrepriseDto.setNomEntreprise(nomEntreprise);
    return entrepriseDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#listeEntreprises()
   */
  @Override
  public ArrayList<EntrepriseDto> listeEntreprises() {
    tryToThrowSqlUncheckedException("echec affichage entreprise");
    ArrayList<EntrepriseDto> liste = new ArrayList<EntrepriseDto>();
    for (int i = 1; i < 11; i++) {
      EntrepriseDto entrepriseDto = this.bizFactoryStub.getEntrepriseDto();
      entrepriseDto.setIdEntreprise(i);
      liste.add(entrepriseDto);
    }
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#findEntrepriseById(int)
   */
  @Override
  public EntrepriseDto findEntrepriseById(int idEntreprise) {
    tryToThrowSqlUncheckedException("Echec FindEntreprise");
    EntrepriseDto entrepriseDto = this.bizFactoryStub.getEntrepriseDto();
    if (entrepriseDto.getIdEntreprise() != idEntreprise) {
      entrepriseDto.setIdEntreprise(0);
      entrepriseDto = bizFactory.getEntrepriseDto();
    }
    return entrepriseDto;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#modifierEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto modifierEntreprise(EntrepriseDto entreprise) {
    tryToThrowSqlUncheckedException("Echec modifierEntreprise");
    if (entreprise.getIdEntreprise() == 0) {
      entreprise.setIdEntreprise(1);
    }
    return entreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#getSelectionEntreprises()
   */
  @Override
  public ArrayList<EntrepriseDto> getSelectionEntreprises() {
    tryToThrowSqlUncheckedException("Echec getSelectionEntreprises");
    ArrayList<EntrepriseDto> liste = new ArrayList<EntrepriseDto>();
    for (int i = 1; i < 11; i++) {
      EntrepriseDto entrepriseDto = this.bizFactoryStub.getEntrepriseDto();
      entrepriseDto.setIdEntreprise(i);
      liste.add(entrepriseDto);
    }
    return liste;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#entreprisesNonSelectionnes(int)
   */
  @Override
  public ArrayList<EntrepriseDto> entreprisesNonSelectionnes(int idJournee) {
    tryToThrowSqlUncheckedException("Echec entreprisesNonSelectionnes");
    return new ArrayList<EntrepriseDto>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see dal.dao.interfaces.EntrepriseDao#entreprisesInvitees(int)
   */
  @Override
  public ArrayList<EntrepriseDto> entreprisesInvitees(int idJournee) {
    tryToThrowSqlUncheckedException("Echec entreprisesInvitees");
    ArrayList<EntrepriseDto> liste = new ArrayList<EntrepriseDto>();
    if (bizFactoryStub.getJourneeDto().getIdJournee() == idJournee) {
      liste.add(this.bizFactoryStub.getEntrepriseDto());
    }
    return liste;
  }

  @Override
  public EntrepriseDto modifierDatePaye(EntrepriseDto entreprise) {
    tryToThrowSqlUncheckedException("Echec modifierDatePaye");
    return entreprise;
  }

}
