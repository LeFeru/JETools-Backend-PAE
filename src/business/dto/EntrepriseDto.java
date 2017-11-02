package business.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Interface permettant de maitriser la visibilité des fonctions Dto de la classe
 * UtilisateurDtoImpl.
 * 
 * @author Nathan.
 *
 */
public interface EntrepriseDto {

  /**
   * Retourne le numero de version.
   * 
   * @return the numVersion.
   */
  int getNumVersion();

  /**
   * Modifie le numero de version.
   * 
   * @param numVersion the numVersion to set.
   */
  void setNumVersion(int numVersion);

  /**
   * Retourne l'id de l'entreprise.
   * 
   * @return the idEntreprise.
   */
  int getIdEntreprise();

  /**
   * @param idEntreprise the idEntreprise to set.
   */
  void setIdEntreprise(int idEntreprise);

  /**
   * Retourne le nom de l'entreprise.
   * 
   * @return the nomEntreprise.
   */
  String getNomEntreprise();

  /**
   * Modifie le nom de l'entreprise.
   * 
   * @param nomEntreprise the nomEntreprise to set.
   */
  void setNomEntreprise(String nomEntreprise);

  /**
   * Retourne le createur de l'entreprise
   * 
   * @return the createur.
   */
  UtilisateurDto getCreateur();

  /**
   * Modifie l'utilisateurDto ayant créé l'entreprise.
   * 
   * @param createur the createur to set.
   */
  void setCreateur(UtilisateurDto createur);

  /**
   * Retourne la date de création de l'entreprise.
   * 
   * @return the dateCreation.
   */
  LocalDateTime getDateCreation();

  /**
   * Modifie la date de creation de l'entreprise.
   * 
   * @param dateCreation the dateCreation to set.
   */
  void setDateCreation(LocalDateTime dateCreation);

  /**
   * Retourne la date de la dernière participation à une JE ayant été payée par l'entreprise.
   * 
   * @return the dateDerniereParticipationPayee.
   */
  LocalDate getDateDerniereParticipationPayee();

  /**
   * Modifie la date de la dernière participation à une JE ayant été payée par l'entreprise.
   * 
   * @param dateParticipationPayee the dateDerniereParticipationPayee to set.
   */
  void setDateDerniereParticipationPayee(LocalDate dateParticipationPayee);

  /**
   * Retourne la rue de l'entreprise.
   * 
   * @return the rue.
   */
  String getRue();

  /**
   * Modifie la rue de l'entreprise.
   * 
   * @param rue the rue to set.
   */
  void setRue(String rue);

  /**
   * Retourne le numéro (adresse) de l'entreprise.
   * 
   * @return the numero.
   */
  String getNumero();

  /**
   * Modifie le numéro (adresse) de l'entreprise.
   * 
   * @param numero the numero to set.
   */
  void setNumero(String numero);

  /**
   * Retourne la boite (adresse) de l'entreprise.
   * 
   * @return the boite.
   */
  String getBoite();

  /**
   * Modifie la boite (adresse) de l'entreprise.
   * 
   * @param boite the boite to set
   */
  void setBoite(String boite);

  /**
   * Retourne le code postal (adresse) de l'entreprise.
   * 
   * @return the codePostal.
   */
  String getCodePostal();

  /**
   * Modifie le code postal (adresse) de l'entreprise.
   * 
   * @param codePostal the codePostal to set.
   */
  void setCodePostal(String codePostal);

  /**
   * Retourne la commune (adresse) de l'entreprise.
   * 
   * @return the commune.
   */
  String getCommune();

  /**
   * Modifie la commune (adresse) de l'entreprise.
   * 
   * @param commune the commune to set.
   */
  void setCommune(String commune);

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
String toString();

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
int hashCode();

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
boolean equals(Object obj);
}
