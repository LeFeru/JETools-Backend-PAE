package dal.dao.interfaces;


import java.util.ArrayList;

import business.dto.EntrepriseDto;

/**
 * @author Nathan.
 *
 */
public interface EntrepriseDao {


  /**
   * Crée une entreprise en base de données.
   * 
   * @param entreprise contient l'ensemble des informations à insérer en DB pour la creation d'une
   *        entreprise.
   * @return une entrepriseDto contenant l'ensemble des champs donnés via le paramètre plus l'id
   *         entreprise
   */
  EntrepriseDto creerEntreprise(EntrepriseDto entreprise);

  /**
   * Trouve l'entreprise dans la base de données en fonction de son nom.
   * 
   * @param nomEntreprise String grace auquel on trouve l'utilisateur.
   * @return un EntrepriseDto avec une partie des informations de l'entreprise.
   */
  EntrepriseDto findEntrepriseByNomEntreprise(String nomEntreprise);

  /**
   * Renvoie les entreprises dans la DB.
   * 
   * @return ArrayList d'entreprises.
   */
  ArrayList<EntrepriseDto> listeEntreprises();

  /**
   * Renvoie l'entreprise possedant l'idEntreprise passé en paramètre.
   * 
   * @param idEntreprise l'id de l'entreprise qu'on veut recuperer.
   * @return une instance d'EntrepriseDto possedant tout les attributs de l'entreprise,une instance
   *         d'entrepriseDto avec un id negatif en cas d'echec
   */
  EntrepriseDto findEntrepriseById(int idEntreprise);

  /**
   * Modifie une entreprise presente dans la DB.
   * 
   * @param entreprise l'entreprise à modifier en DB.
   * @return l'entreprise modifiée si succes, une entrepriseDto avec un id negatif.
   */
  EntrepriseDto modifierEntreprise(EntrepriseDto entreprise);


  /**
   * Renvoie les entreprises respectant les criteres.
   * 
   * @return ArrayList d'entreprises.
   */
  ArrayList<EntrepriseDto> getSelectionEntreprises();

  /**
   * Renvoie les entreprises qui ne respectent pas les critères de selection.
   * 
   * @param idJournee permet de retrouver les entrperises non selectionnées sur cette JE.
   * @return une liste d'EntrepriseDto.
   */
  ArrayList<EntrepriseDto> entreprisesNonSelectionnes(int idJournee);

  /**
   * Renvoie toutes les entreprises invitées à la JE passée en parametre.
   * 
   * @param idJournee contient l'id de la journée sur laquelle on veut recuperer les entreprises
   *        invitées.
   * @return une liste d'entrepriseDto.
   */
  ArrayList<EntrepriseDto> entreprisesInvitees(int idJournee);

  /**
   * Methode qui modifie la date de l'entreprise passée en parametre après qu'elle est payée sa
   * participation.
   * 
   * @param entreprise une instance d'entrepriseDto qui contient les infos de l'entreprise qui a
   *        payé sa participation.
   * @return l'entreprise avec la date de payement.
   */
  EntrepriseDto modifierDatePaye(EntrepriseDto entreprise);
}


