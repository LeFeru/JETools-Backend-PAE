package business.impl;

import business.biz.PresenceBiz;

/**
 * @author Skubi.
 *
 */
public class PresenceImpl implements PresenceBiz {
  private int idParticipation;
  private int idPersonne;
  private int numVersion;

  /**
   * Constructeur de PresenceImpl.
   * 
   * @param idParticipation.
   * @param idPersonne.
   */
  public PresenceImpl(int idParticipation, int idPersonne) {
    this.idParticipation = idParticipation;
    this.idPersonne = idPersonne;
    this.numVersion = 0;
  }

  /**
   * Constructeur de PresenceImpl.
   */
  public PresenceImpl() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PresenceDto#getIdParticipation()
   */
  @Override
  public int getIdParticipation() {
    return idParticipation;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PresenceDto#setIdParticipation(int)
   */
  @Override
  public void setIdParticipation(int idParticipation) {
    this.idParticipation = idParticipation;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PresenceDto#getIdPersonne()
   */
  @Override
  public int getIdPersonne() {
    return idPersonne;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PresenceDto#setIdPersonne(int)
   */
  @Override
  public void setIdPersonne(int idPersonne) {
    this.idPersonne = idPersonne;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PresenceDto#getNumVersion()
   */
  @Override
  public int getNumVersion() {
    return numVersion;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PresenceDto#setNumVersion(int)
   */
  @Override
  public void setNumVersion(int numVersion) {
    this.numVersion = numVersion;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "PresenceImpl [idParticipation=" + idParticipation + ", idPersonne=" + idPersonne
        + ", numVersion=" + numVersion + "]";
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idParticipation;
    result = prime * result + idPersonne;
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
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
    PresenceImpl other = (PresenceImpl) obj;
    if (idParticipation != other.idParticipation) {
      return false;
    }
    if (idPersonne != other.idPersonne) {
      return false;
    }
    return true;
  }


}
