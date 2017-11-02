package dal.dao.interfaces;

import business.dto.UtilisateurDto;

/**
 * @author rachid.
 *
 */
public interface UtilisateurDao {

  /**
   * Crée un utilisateur en base de données.
   * 
   * @param utilisateurDto contient l'ensembles des informations à insérer en DB pour l'inscription
   *        d'un utilisateur.
   * @return un utilisateurDto contenant l'ensemble des champs données via le paramètre plus
   *         l'id_journée.
   */
  UtilisateurDto inscrireUtilisateur(UtilisateurDto utilisateurDto);

  /**
   * Trouve l'utilisateur dans la base de données en fonction de son pseudo.
   * 
   * @param pseudo String grâce auquel on trouve l'utilisateur.
   * @return un UtilisateurDto avec toutes les informations de l'utilisateur.
   */
  UtilisateurDto findUtilisateurByPseudo(String pseudo);

  /**
   * Trouve l'utilisateur dans la base de données en fonction de son mail.
   * 
   * @param mail String grâce auquel on trouve l'utilisateur.
   * @return un UtilisateurDto avec toutes les informations de l'utilisateur.
   */
  UtilisateurDto findUtilisateurByEmail(String mail);

  /**
   * Trouve l'utilisateur dans la base de données en fonction de son id.
   * 
   * @param id Int grace auquel on trouve l'utilisateur.
   * @return un UtilisateurDto avec toutes les informations de l'utilisateur.
   */
  UtilisateurDto findUtilisateurById(int id);

  void resetTentatives(String pseudo);

  void majTentatives(String pseudo);

  // List<String> backup();

}
