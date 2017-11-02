/**

 * 
 */

package business.ucc.interfaces;

import java.util.ArrayList;
import java.util.List;

import business.dto.EntrepriseDto;
import business.dto.ParticipationDto;

/**
 * Interface permettant de maitriser les methodes visibles d'une EntrepriseUccImpl, methodes de type
 * UseCase.
 * 
 * @author Nathan.
 *
 */
public interface EntrepriseUcc {

  /**
   * Vérifie et injecte les données d'uneEntrepriseDto dans la base de données.
   * 
   * @param entreprise la référence d 'linstance d'EntrepriseDto contenant toutes les informations à
   *        inserer.
   * @return une référence vers une instance EntrepriseDto contenant en plus des informations
   *         données en paramètres, l'id renvoyé par la base de données, si non la référence donné
   *         en paramètre.
   */
  EntrepriseDto creerEntreprise(EntrepriseDto entreprise);

  /**
   * Cree une liste avec toutes les entreprises présentes dans la donnée.
   * 
   * @return Une liste d'entreprises présente dans la base de données.
   */
  ArrayList<EntrepriseDto> listeEntreprises();

  /**
   * Modifie une entreprise presente dans la DB.
   * 
   * @param entreprise l'entreprise à modifier en DB.
   * @return l'entreprise modifiée si succes, une BizException en cas d'echec.
   */
  EntrepriseDto modifierEntreprise(EntrepriseDto entreprise);

  /**
   * Recuperer l'entreprise dans la db via l'id.
   * 
   * @param idEntreprise l'id de l'entreprise à recuperer.
   * @return l'instance d'entreprise possedant cet ID,une bizException sinon.
   */
  EntrepriseDto chargerEntreprise(int idEntreprise);

  /**
   * Renvoyer une liste des participations d'une entreprise.
   * 
   * @param entreprise l'instance de l'entreprise sur laquelle on veut recuperer son historique de
   *        participations
   * 
   * @return une liste avec ses participations
   */
  List<ParticipationDto> historiqueEntreprise(EntrepriseDto entreprise);
}
