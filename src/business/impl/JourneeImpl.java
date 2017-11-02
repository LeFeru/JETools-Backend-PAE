package business.impl;

import java.time.LocalDate;

import business.biz.JourneeBiz;
import business.dto.JourneeDto;
import business.dto.UtilisateurDto;
import exceptions.InvalidJourneeDtoException;

/**
 * @author rachid.
 *
 */
public class JourneeImpl implements JourneeBiz, JourneeDto {
  private int idJournee;
  private LocalDate dateJe;
  private UtilisateurDto idCreateur;
  private boolean cloturee;
  private int numVersion;

  /**
   * Constructeur JourneeImpl.
   * 
   * @param idJournee.
   * @param dateJe.
   * @param idCreateur.
   * @param cloturee.
   */
  public JourneeImpl(int idJournee, LocalDate dateJe, UtilisateurDto idCreateur, boolean cloturee,
      int numVersion) {
    super();
    this.idJournee = idJournee;
    this.dateJe = dateJe;
    this.idCreateur = idCreateur;
    this.cloturee = cloturee;
    this.numVersion = numVersion;
  }

  @Override
  public int getNumVersion() {
    return this.numVersion;
  }

  @Override
  public void setNumVersion(int numVersion) {
    this.numVersion = numVersion;
  }

  /**
   * Constructeur vide JourneeImpl.
   */
  public JourneeImpl() {
    super();
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#getIdJournee()
   */
  @Override
  public int getIdJournee() {
    return idJournee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#setIdJournee(int)
   */
  @Override
  public void setIdJournee(int idJournee) {
    this.idJournee = idJournee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#getdateJe()
   */
  @Override
  public LocalDate getDateJe() {
    return dateJe;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#setdateJe(java.time.LocalDate)
   */
  @Override
  public void setDateJe(LocalDate dateJe) {
    this.dateJe = dateJe;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#getIdCreateur()
   */
  @Override
  public UtilisateurDto getIdCreateur() {
    return idCreateur;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#setIdCreateur(business.dto.UtilisateurDto)
   */
  @Override
  public void setIdCreateur(UtilisateurDto idCreateur) {
    this.idCreateur = idCreateur;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#isCloturee()
   */
  @Override
  public boolean isCloturee() {
    return cloturee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#setCloturee(boolean)
   */
  @Override
  public void setCloturee(boolean cloturee) {
    this.cloturee = cloturee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#toString()
   */
  @Override
  public String toString() {
    return "JourneeUccImpl [idJournee=" + idJournee + ", dateJe=" + dateJe + ", idCreateur="
        + idCreateur + ", cloturee=" + cloturee + "]";
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idJournee;
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  /*
   * (non-Javadoc)
   * 
   * @see business.impl.JourneeDto#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    JourneeImpl other = (JourneeImpl) obj;
    if (idJournee != other.idJournee) {
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.JourneeBiz#checkJournee(business.dto.JourneeDto)
   */
  @Override
  public void checkJournee(JourneeDto journeeDto) {
    if (journeeDto == null) {
      throw new InvalidJourneeDtoException("La journee ne peut etre null");
    } else if (journeeDto.isCloturee()) {
      throw new InvalidJourneeDtoException("La journeeDto ne peut etre cloturee");
    } else if (journeeDto.getIdCreateur() == null) {
      throw new InvalidJourneeDtoException("Le createur de la journee ne peut etre null");
    } else if (journeeDto.getDateJe() == null) {
      throw new InvalidJourneeDtoException("La date de la journee ne peut etre null");
    } else if (journeeDto.getDateJe().isBefore(LocalDate.now())) {
      throw new InvalidJourneeDtoException(
          "La date de la journee d'entreprise ne peut etre anterieure a aujourd'hui.");
    }

  }

}
