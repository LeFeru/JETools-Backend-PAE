package business.ucc.implementations;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import business.biz.UtilisateurBiz;
import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.ucc.interfaces.UtilisateurUcc;
import dal.dao.interfaces.UtilisateurDao;
import dal.services.DalServices;
import exceptions.BCryptDysfonctionnelException;
import exceptions.BizException;
import exceptions.ForbiddenAccessException;

/**
 * Classe UtilisateurUccImpl implémentant l'interface UtilisateurUcc.
 * 
 * @author rachid
 *
 */
public class UtilisateurUccImpl implements UtilisateurUcc {
  private final Logger logger;
  private UtilisateurDao utilisateurDao;
  private BizFactory bizFactory;
  private DalServices dalServices;

  /**
   * Renvoie une référence vers une nouvelle instance d'UtilisateurUccImpl.
   * 
   * @author skubi.
   */
  public UtilisateurUccImpl(UtilisateurDao udao, BizFactory bizfac, DalServices dserv) {
    utilisateurDao = udao;
    bizFactory = bizfac;
    dalServices = dserv;
    this.logger = Logger.getLogger("reportsLogger");
  }

  private void tryToRollbackAndCloseAndThrowBizException(String methode, Exception exception) {
    logger.info("Exception lors de l'appel de la methode: " + methode);
    logger.info(exception.getMessage());
    if (!dalServices.hasAlreadyClosedConnection()) {
      if (dalServices.hasOpenTransaction()) {
        try {
          dalServices.rollbackTransaction();
        } catch (Exception exce) {
          logger.info("Exception lors de l'appel de la methode: throwBizException");
          logger.info("Une exception a été rencontrée lors de la tentative de rollback");
          logger.info(exception.getMessage());
        }
      }
      try {
        dalServices.closeConnection();
      } catch (Exception exce) {
        logger.info("Exception lors de l'appel de la methode: throwBizException");
        String mess1 = "Une exception a été rencontrée lors de la tentative de fermeture";
        logger.info(mess1 + " de la connection à la base de données");
        logger.info(exception.getMessage());
      }
    }
    throw new BizException(exception.getMessage());
  }

  /*
   * (non-Javadoc)
   * 
   * @author skubi
   *
   * @see business.ucc.interfaces.UtilisateurUcc#connecter(business.dto.UtilisateurDto)
   */
  @Override
  public UtilisateurDto connecterUtilisateur(UtilisateurDto utilisateurDto) {
    try {
      logger.info("UccImpl connecterUtilisateur\t\t\tseq");
      logger.info("Check utilisateurDto...");
      logger.info(utilisateurDto);
      ((UtilisateurBiz) utilisateurDto).checkBeforeConnexion();
      logger.info("Check utilisateurDto -> ok");
      UtilisateurBiz utilisateurBiz = (UtilisateurBiz) bizFactory.getUtilisateurDto();
      this.dalServices.openConnection();
      UtilisateurDto retour = utilisateurDao.findUtilisateurByPseudo(utilisateurDto.getPseudo());
      if (retour.getNombreTentativesConnexions() >= 3) {
        if (!retour.getDateDerniereTentative().isBefore(LocalDateTime.now().minusMinutes(15))) {
          this.dalServices.closeConnection();
          String mess1 = "Le nombre de tentatives échouées autorisé (3) a été atteint ! ";
          String mess2 = "Veuillez patienter 15 minutes svp.";
          throw new ForbiddenAccessException(mess1 + mess2);
        }
      }
      if (retour.getId() > 0 && utilisateurBiz.checkMdp(utilisateurDto, retour)) {
        logger.info("Bon mdp");
        logger.info(retour.toString());
        utilisateurDao.resetTentatives(retour.getPseudo());
        this.dalServices.closeConnection();
        return retour;
      }
      logger.info("Mauvais mdp");
      utilisateurDao.majTentatives(retour.getPseudo());
      this.dalServices.closeConnection();
      throw new BizException("combinaison login/mot de passe incorrecte.");
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("connecterUtilisateur", exception);
    }
    return null;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.ucc.interfaces.UtilisateurUcc#inscrire(business.dto.UtilisateurDto)
   */
  @Override
  public UtilisateurDto inscrireUtilisateur(UtilisateurDto utilisateurDto) {
    try {
      utilisateurDto.setDateInscription(LocalDateTime.now());
      utilisateurDto.setResponsable(false);
      logger.info("Check utilisateurDto...");
      ((UtilisateurBiz) utilisateurDto).checkBeforeInscription();
      logger.info("Check utilisateurDto -> ok");
      dalServices.openConnection();
      if (utilisateurDao.findUtilisateurByPseudo(utilisateurDto.getPseudo()).getId() > 0) {
        logger.error("Pseudo deja utilise");
        dalServices.closeConnection();
        throw new BizException("Pseudo déjà utilisé");
      }
      if (utilisateurDao.findUtilisateurByEmail(utilisateurDto.getEmail()).getId() > 0) {
        logger.error("Email deja utilise");
        dalServices.closeConnection();
        throw new BizException("Email déjà utilisé");
      }
      UtilisateurDto userAvecMdpCrypte = null;
      try {
        userAvecMdpCrypte = ((UtilisateurBiz) utilisateurDto).crypterMdp(utilisateurDto);
      } catch (BCryptDysfonctionnelException exception) {
        logger.info("Probleme cryptage mdp avec BCrypt");
        dalServices.closeConnection();
        throw new BizException("Problème interne, veuillez réessayer");
      }
      dalServices.startTransaction();
      utilisateurDto = utilisateurDao.inscrireUtilisateur(userAvecMdpCrypte);
      dalServices.commitTransaction();
      dalServices.closeConnection();
      return utilisateurDto;
    } catch (Exception exception) {
      tryToRollbackAndCloseAndThrowBizException("inscrireUtilisateur", exception);
    }
    return null;
  }

  /*
   * @Override public void backup() { this.dalServices.openConnection(); List<String> listes =
   * this.utilisateurDao.backup(); this.dalServices.closeConnection(); BufferedWriter bw; try {
   * String pathString = "backups/backup_" +
   * LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH:mm")) + ".sql"; Path path
   * = Paths.get(pathString); bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8); // bw =
   * new BufferedWriter(new FileWriter(new File("backups/backup_" // +
   * LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH:mm")) + ".sql"))); for
   * (String line : listes) { bw.write(line + "\n"); } bw.close(); } catch (Exception exception) {
   * tryToRollbackAndCloseAndThrowBizException("backup", exception); } }
   */
}
