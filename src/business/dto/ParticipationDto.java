package business.dto;

import java.time.LocalDate;

public interface ParticipationDto {

  /**
   * Retourne l'id de la participation.
   * 
   * @return the idParticipation.
   */
  int getIdParticipation();

  /**
   * Modifie l'id de la participation.
   * 
   * @param idParticipation the idParticipation to set.
   */
  void setIdParticipation(int idParticipation);

  /**
   * Retourne l'id de la journée.
   * 
   * @return the idJournee.
   */
  int getIdJournee();

  /**
   * Modifie l'id de la journée.
   * 
   * @param idJournee the idJournee to set.
   */
  void setIdJournee(int idJournee);

  /**
   * Retourne l'id de l'entreprise.
   * 
   * @return the idEntreprise.
   */
  int getIdEntreprise();

  /**
   * Modifie l'id de l'entreprise.
   * 
   * @param idEntreprise the idEntreprise to set.
   */
  void setIdEntreprise(int idEntreprise);

  /**
   * Retourne l'état de la participation.
   * 
   * @return the etat.
   */
  String getEtat();

  /**
   * Modifie l'état de la participation.
   * 
   * @param etat the etat to set.
   */
  void setEtat(String etat);

  /**
   * Retourne l'évolution de la participation.
   * 
   * @return the evolution.
   */
  String getEvolution();

  /**
   * Modifie l'évolution de la participation.
   * 
   * @param evolution the evolution to set.
   */
  void setEvolution(String evolution);

  /**
   * Rertourne vrai si la participation est annulée.
   * 
   * @return the annulee.
   */
  boolean isAnnulee();

  /**
   * Modifie l'état d'annulation de la participation.
   * 
   * @param annulee the annulee to set.
   */
  void setAnnulee(boolean annulee);

  /**
   * Rertourne le numéro de version.
   * 
   * @return the numVersion.
   */
  int getNumVersion();

  /**
   * Modifie le numéro de version.
   * 
   * @param numVersion the numVersion to set.
   */
  void setNumVersion(int numVersion);

  /**
   * Retourne la date de la JE.
   * 
   * @return the DateJe
   */
  LocalDate getDateJe();

  /**
   * Modifie la date de la JE.
   * 
   * @param date the date to set.
   */
  void setDateJe(LocalDate date);

  /**
   * Retourne le nom de l'entreprise.
   * 
   * @return the nomEntreprise
   */
  String getNomEntreprise();

  /**
   * Modifie le nom de l'entreprise.
   * 
   * @param nomEntreprise the nomEntreprise to set
   */
  void setNomEntreprise(String nomEntreprise);
}
