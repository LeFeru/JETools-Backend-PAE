
package business.ucc.interfaces;

import business.dto.UtilisateurDto;

/**
 * Interface permettant de maîtriser les méthodes visibles d'un UtilisateurUccImpl, méthodes de type
 * UseCase.
 * 
 * @author rachid
 *
 */
public interface UtilisateurUcc {

  /**
   * Connecte un UtilisateurDto au système, vérifie les données dont le mot de passe.
   * 
   * @param utilisateurDto la référence vers l'UtilisateurDto à connecter.
   * @return une reférence vers un UtilisateurDto contenant toutes les données provenant de la base
   *         de données dont l'id qui permettra de déterminer si l'utilisateur a été authentifié ou
   *         non, si non, une instance d'UtilisateurDto avec un id négatif
   */
  UtilisateurDto connecterUtilisateur(UtilisateurDto utilisateurDto);

  /**
   * Vérifie et injecte les données d'un UtilisateurDto dans la base de données, crypte le mot de
   * passe par la même occasion.
   * 
   * @param utilisateurDto la référence de l'instance d'UtilisateurDto contenant toutes les
   *        informations à insérer
   * @return une référence vers une instance UtilisateurDto contenant en plus des informations
   *         données en paramètre, l'id renvoyé par la base de données, si non la référence donné en
   *         paramètre
   */
  UtilisateurDto inscrireUtilisateur(UtilisateurDto utilisateurDto);

  // void backup();
}
