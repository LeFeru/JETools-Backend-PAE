package business.biz;

import business.dto.UtilisateurDto;
import exceptions.BCryptDysfonctionnelException;
import exceptions.InvalidUtilisateurDtoException;

/**
 * Interface permettant de maîtriser la visibilité des fonctions Biz de la classe UtilisateurImpl.
 * 
 * @author rachid.
 *
 */
public interface UtilisateurBiz extends UtilisateurDto {

  /**
   * Renvoie un booléen permettant de savoir si le mot de passe fournit dans l'instance utilisateur1
   * correspond bien au mot de passe présent dans 'instance utilisateur 2 qui aura été créée sur
   * base des donnes présentent en base de données.
   * 
   * @author Roman.
   * @param utilisateur1 désigne l'instance d'UtilisateurDto contenant un mot de passe en clair.
   * @param utilisateur2 désigne l'instance d'UtilisateurDto contenant le mot de passe crypté.
   * @return true si les mots de passes concordent, sinon false.
   * 
   */
  boolean checkMdp(UtilisateurDto utilisateur1, UtilisateurDto utilisateur2);

  /**
   * Renvoie une instance d'UtilisateurDto contenant les données de l'instance UtilisateurDto passée
   * en paramètre et où le mot de passe a été crypté.
   * 
   * @author Roman.
   * @param utilisateur désigne l'instance d'Utilisateur contenant un mot de passe en clair.
   * @return Une instance d'UtilisateurDto contenant le mot de passe crypté.
   * @throws BCryptDysfonctionnelException si le mot de passe n'a pas été crypté correctement par
   *         BCrypt.
   */
  UtilisateurDto crypterMdp(UtilisateurDto utilisateur);

  /**
   * Vérifie si les conditions d'inscriptions d'un utilisateur sont respectées.
   * 
   * @author Roman.
   * @throws InvalidUtilisateurDtoException si un des attributs est null ou vide
   * 
   */
  void checkBeforeInscription();

  /**
   * Vérifie si les conditions de connexion d'un utilisateur sont respectées.
   * 
   * @author Roman.
   * @throws InvalidUtilisateurDtoException si un des attributs est null,vide ou ne respecte pas les
   *         limites de caractères.
   * 
   */
  void checkBeforeConnexion();

}
