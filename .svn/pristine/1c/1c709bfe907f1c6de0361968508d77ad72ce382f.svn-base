
package business.dto;

import java.time.LocalDateTime;

/**
 * Interface permettant de maîtriser la visibilité des fonctions Dto de la classe
 * UtilisateurDtoImpl.
 * 
 * @author rachid.
 *
 */
public interface UtilisateurDto {

  int getNumVersion();

  void setNumVersion(int numVersion);

  /**
   * Renvoie la valeur de l'id de l'UtilisateurDto.
   * 
   * @author rachid
   * @return l'id de l'UtilisateurDto
   */
  int getId();

  /**
   * Remplace la valeur de l'id de l'UtilisateurDto avec celui passé en paramètre.
   * 
   * @author rachid
   * @param id la valeur qui remplacera l'id actuel
   *
   */
  void setId(int id);

  /**
   * Renvoie la référence vers l'objet String contenant le nom de l'UtilisateurDto.
   * 
   * @author rachid
   * @return la référence vers le nom
   */
  String getNom();

  /**
   * Remplace la référence vers l'objet String contenant le nom de l'UtilisateurDto.
   * 
   * @author rachid
   * @param nom la référence qui remplacera le nom actuel
   *
   */
  void setNom(String nom);

  /**
   * Renvoie la référence vers l'objet String contenant le pseudo de l'UtilisateurDto.
   * 
   * @author rachid
   * @return la référence vers le pseudo
   */
  String getPseudo();

  /**
   * Remplace la référence vers l'objet String contenant le pseudo de l'UtilisateurDto.
   * 
   * @author rachid
   * @param pseudo la référence qui remplacera le pseudo actuel
   *
   */
  void setPseudo(String pseudo);

  /**
   * Renvoie la référence vers l'objet String contenant le prenom de l'UtilisateurDto.
   * 
   * @author rachid
   * @return la référence vers le prenom
   */
  String getPrenom();

  /**
   * Remplace la référence vers l'objet String contenant le prenom de l'UtilisateurDto.
   * 
   * @author rachid
   * @param prenom la référence qui remplacera le pseudo actuel
   *
   */
  void setPrenom(String prenom);

  /**
   * Renvoie la référence vers l'objet String contenant l'email de l'UtilisateurDto.
   * 
   * @author rachid
   * @return la référence vers l'email
   */
  String getEmail();

  /**
   * Remplace la référence vers l'objet String contenant l'email de l'UtilisateurDto.
   * 
   * @author rachid
   * @param email la référence qui remplacera l'email actuel
   *
   */
  void setEmail(String email);

  /**
   * Renvoie la référence vers l'objet LocalDateTime contenant la dateInscription de
   * l'UtilisateurDto.
   * 
   * @author rachid
   * @return la référence vers la dateInscription
   */
  LocalDateTime getDateInscription();

  /**
   * Remplace la référence vers l'objet LocalDateTime contenant la dateInscription de
   * l'UtilisateurDto.
   * 
   * @author rachid
   * @param dateInscription la référence qui remplacera la dateInscription actuelle
   *
   */
  void setDateInscription(LocalDateTime dateInscription);

  /**
   * Renvoie la référence vers l'objet String contenant le mdp de l'UtilisateurDto.
   * 
   * @author rachid
   * @return la référence vers le mdp
   */
  String getMdp();

  /**
   * Remplace la référence vers l'objet String contenant le mdp de l'UtilisateurDto.
   * 
   * @author rachid
   * @param mdp la référence qui remplacera le mdp actuel
   *
   */
  void setMdp(String mdp);

  /**
   * Renvoie un booléen permettant de savoir si l'UtilisateurDto est un responsable ou non.
   * 
   * @author rachid
   * @return la valeur de responsable
   */
  boolean isResponsable();

  /**
   * Remplace la valeur actuelle du booléen responsable de l'UtilisateurDto.
   * 
   * @author rachid
   * @param responsable la nouvelle valeur du booléen responsable
   *
   */
  void setResponsable(boolean responsable);

  /**
   * Renvoie une référence vers un objet String contenant une représentation textuelle des valeurs
   * de l'UtilisateurDto.
   * 
   * @author rachid
   * @return la réfrence vers l'objet String contenant la représentation textuelle
   */
  @Override
  String toString();

  /**
   * Renvoie un booléen suite à une comparaison entre l'UtilisateurDto et l'objet passé en
   * paramètre, la comparaison est réalisée sur base de l'attribut pseudo qui est unique si les
   * types sont les mêmes.
   * 
   * @author rachid
   * @param obj la référence de l'objet à comparer à l'UtilisateurDto
   * @return le résultat de la comparaison sous forme de valeur booléenne
   */
  @Override
  boolean equals(Object obj);

  /**
   * Renvoie l'identifiant unique en Java de l'objet UtilisateurDto sous forme d'entier, celui-ci
   * est basé sur l'identifiant unique du pseudo.
   * 
   * @author rachid
   * @return un entier représentant l'identifiant unique en Java
   */
  @Override
  int hashCode();

  /**
   * Renvoie la valeur contenant le nombre de tentatives de connexions.
   * 
   * @author rachid
   * @return la valeur du nombre de tentatives
   */
  int getNombreTentativesConnexions();

  /**
   * Remplace la valeur du nombre de tentatives de connexions par la valeur passée en paramètres.
   * 
   * @author rachid.
   * @param nbTentativesConnexions la nouvelle valeur du nombre de tentatives de connexions.
   *
   */
  void setNombreTentativesConnexions(int nbTentativesConnexions);

  /**
   * Renvoie la date de la dernière tentative de connexion.
   * 
   * @author rachid.
   * @return la date de la dernière tentative de connexion.
   */
  LocalDateTime getDateDerniereTentative();

  /**
   * Remplace la date de la dernière tentative de connexion.
   * 
   * @author rachid.
   * @param dateDerniereTentative la date de la dernière tentative de connexion.
   *
   */
  void setDateDerniereTentative(LocalDateTime dateDerniereTentative);

  /**
   * Retourne le mdpConfirme.
   * 
   * @return the mdpConfirme.
   */
  String getMdpConfirme();

  /**
   * Modifie le mdpConfirme.
   * 
   * @param mdpConfirme the mdpConfirme to set.
   */
  void setMdpConfirme(String mdpConfirme);
}
