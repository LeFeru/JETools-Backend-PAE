package business.ucc.stubs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import business.biz.EntrepriseBiz;
import business.dto.EntrepriseDto;
import business.dto.ParticipationDto;
import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.factories.BizFactoryStub;
import business.ucc.interfaces.EntrepriseUcc;
import dal.dao.interfaces.EntrepriseDao;
import dal.dao.interfaces.JourneeDao;
import dal.dao.interfaces.ParticipationDao;
import dal.dao.interfaces.UtilisateurDao;
import dal.dao.stubs.EntrepriseDaoStub;
import dal.dao.stubs.JourneeDaoStub;
import dal.dao.stubs.ParticipationDaoStub;
import dal.dao.stubs.UtilisateurDaoStub;
import exceptions.BizException;

/**
 * @author rachid.
 *
 */
public class EntrepriseUccStub implements EntrepriseUcc {
  private boolean throwBizException;
  private BizFactory bizFactoryStub;
  private UtilisateurDao utilisateurDaoStub;
  private EntrepriseDao entrepriseDaoStub;
  private ParticipationDao participationDaoStub;
  private JourneeDao journeeDaoStub;

  /**
   * Constructeur de EntrepriseUccStub.
   */
  public EntrepriseUccStub() {
    this.throwBizException = false;
    this.bizFactoryStub = new BizFactoryStub();
    this.utilisateurDaoStub = new UtilisateurDaoStub();
    this.entrepriseDaoStub = new EntrepriseDaoStub();
    this.participationDaoStub = new ParticipationDaoStub();
    this.journeeDaoStub = new JourneeDaoStub();
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
   * @see business.ucc.interfaces.EntrepriseUcc#creerEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto creerEntreprise(EntrepriseDto entreprise) {
    tryToThrowException("Echec creerEntreprise");
    EntrepriseBiz entrepriseBiz = (EntrepriseBiz) bizFactoryStub.getEntrepriseDto();
    entrepriseBiz.checkEntreprise(entreprise);
    if (entrepriseDaoStub.findEntrepriseByNomEntreprise(entreprise.getNomEntreprise())
        .getIdEntreprise() <= 0) {
      UtilisateurDto retour =
          utilisateurDaoStub.findUtilisateurByPseudo(entreprise.getCreateur().getPseudo());
      entreprise.setCreateur(retour);
      entrepriseDaoStub.creerEntreprise(entreprise);
      return entreprise;
    }
    return entreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#listeEntreprises()
   */
  @Override
  public ArrayList<EntrepriseDto> listeEntreprises() {
    tryToThrowException("Echec listeEntreprises");
    return entrepriseDaoStub.listeEntreprises();
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#modifierEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public EntrepriseDto modifierEntreprise(EntrepriseDto entreprise) {
    tryToThrowException("Echec modifierEntreprise");
    ((EntrepriseBiz) entreprise).checkEntreprise(entreprise);
    EntrepriseDto temp = entrepriseDaoStub.findEntrepriseById(entreprise.getIdEntreprise());
    if (temp.getNumVersion() != entreprise.getNumVersion()) {
      throw new BizException("rafraichissez et réessayez");
    }
    entrepriseDaoStub.modifierEntreprise(entreprise);
    return entreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#chargerEntreprise(int)
   */
  @Override
  public EntrepriseDto chargerEntreprise(int idEntreprise) {
    tryToThrowException("Echec chargerEntreprise");
    return entrepriseDaoStub.findEntrepriseById(idEntreprise);
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.EntrepriseUcc#historiqueEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public List<ParticipationDto> historiqueEntreprise(EntrepriseDto entreprise) {
    tryToThrowException("Echec historiqueEntreprise");
    List<ParticipationDto> res =
        participationDaoStub.findParticipationByIdEntreprise(entreprise.getIdEntreprise());
    for (ParticipationDto participation : res) {
      LocalDate dateTemp = journeeDaoStub.findJourneeById(participation.getIdJournee()).getDateJe();
      participation.setDateJe(dateTemp);
    }
    return res;
  }

}
