package business.dto;

import java.time.LocalDate;


public interface JourneeDto {
  int getNumVersion();

  void setNumVersion(int numVersion);

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
   * Retourne la date de la journée.
   * 
   * @return the dateJe.
   */
  LocalDate getDateJe();

  /**
   * Modifie la date de la journée.
   * 
   * @param dateJe the dateJe to set.
   */
  void setDateJe(LocalDate dateJe);

  /**
   * Retourne le créateur de la journée.
   * 
   * @return the idCreateur UtilisateurDto.
   */
  UtilisateurDto getIdCreateur();

  /**
   * Modifie le créateur de la journée.
   * 
   * @param idCreateur the idCreateur to set.
   */
  void setIdCreateur(UtilisateurDto idCreateur);

  /**
   * Retourne true si la journée est cloturée, faux sinon.
   * 
   * @return the cloturee.
   */
  boolean isCloturee();

  /**
   * Modifie l'état cloturée d'une journée (cloture une journée).
   * 
   * @param cloturee the cloturee to set.
   */
  void setCloturee(boolean cloturee);

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
