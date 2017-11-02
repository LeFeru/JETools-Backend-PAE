package business.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import business.biz.ParticipationBiz;
import business.dto.ParticipationDto;
import exceptions.InvalidParticipationDtoException;

public class ParticipationImpl implements ParticipationBiz, ParticipationDto {
  private int idParticipation;
  private int idJournee;
  private int idEntreprise;
  private String etat;
  private String evolution;
  private boolean annulee;
  private int numVersion;
  private LocalDate dateJe;
  private String nomEntreprise;

  public Set<String> etats = new HashSet<>(Arrays.asList("invitee", "confirmee", "refusee"));
  public Set<String> evolutions = new HashSet<>(Arrays.asList(null, "facturee", "payee"));

  /**
   * Constructeur de ParticipationImpl.
   * 
   * @param idParticipation.
   * @param idJournee.
   * @param idEntreprise.
   * @param etat.
   * @param evolution.
   * @param annulee.
   * @param nomEntreprise.
   * @param dateJe.
   */
  public ParticipationImpl(int idParticipation, int idJournee, int idEntreprise, String etat,
      String evolution, boolean annulee, LocalDate dateJe, String nomEntreprise) {
    super();
    this.idParticipation = idParticipation;
    this.idJournee = idJournee;
    this.idEntreprise = idEntreprise;
    checkEtat(etat);
    this.etat = etat;
    checkEvolution(evolution);
    this.evolution = evolution;
    this.annulee = annulee;
    this.dateJe = dateJe;
    this.nomEntreprise = nomEntreprise;
  }

  /**
   * Constructeur de ParticipationImpl.
   */
  public ParticipationImpl() {
    super();
  }


  private void checkEtat(String etat2) {
    if (!etats.contains(etat2)) {
      throw new InvalidParticipationDtoException("L'état n'est pas conforme");
    }
  }

  private void checkEvolution(String evolution) {
    if (!evolutions.contains(evolution)) {
      throw new InvalidParticipationDtoException("L'évolution n'est pas conforme");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#getIdParticipation()
   */
  @Override
  public int getIdParticipation() {
    return idParticipation;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#setIdParticipation(int)
   */
  @Override
  public void setIdParticipation(int idParticipation) {
    this.idParticipation = idParticipation;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#getIdJournee()
   */
  @Override
  public int getIdJournee() {
    return idJournee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#setIdJournee(int)
   */
  @Override
  public void setIdJournee(int idJournee) {
    this.idJournee = idJournee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#getIdEntreprise()
   */
  @Override
  public int getIdEntreprise() {
    return idEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#setIdEntreprise(int)
   */
  @Override
  public void setIdEntreprise(int idEntreprise) {
    this.idEntreprise = idEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#getEtat()
   */
  @Override
  public String getEtat() {
    return etat;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#setEtat(java.lang.String)
   */
  @Override
  public void setEtat(String etat) {
    checkEtat(etat);
    this.etat = etat;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#getEvolution()
   */
  @Override
  public String getEvolution() {
    return evolution;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#setEvolution(java.lang.String)
   */
  @Override
  public void setEvolution(String evolution) {
    checkEvolution(evolution);
    this.evolution = evolution;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#isAnnulee()
   */
  @Override
  public boolean isAnnulee() {
    return annulee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#setAnnulee(boolean)
   */
  @Override
  public void setAnnulee(boolean annulee) {
    this.annulee = annulee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#getNumVersion()
   */
  @Override
  public int getNumVersion() {
    return numVersion;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#setNumVersion(int)
   */
  @Override
  public void setNumVersion(int numVersion) {
    this.numVersion = numVersion;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#getDateJe()
   */
  @Override
  public LocalDate getDateJe() {
    return dateJe;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#setDateJe(java.time.LocalDate)
   */
  @Override
  public void setDateJe(LocalDate dateJe) {
    this.dateJe = dateJe;
  }



  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#getNomEntreprise()
   */
  public String getNomEntreprise() {
    return nomEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.ParticipationDto#setNomEntreprise(java.lang.String)
   */
  public void setNomEntreprise(String nomEntreprise) {
    this.nomEntreprise = nomEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ParticipationImpl [idParticipation=" + idParticipation + ", idJournee=" + idJournee
        + ", idEntreprise=" + idEntreprise + ", etat=" + etat + ", evolution=" + evolution
        + ", annulee=" + annulee + ", numVersion=" + numVersion + ", etats=" + etats
        + ", evolutions=" + evolutions + "]";
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.ParticipationBiz#chechParticipation(business.dto.ParticipationDto)
   */
  @Override
  public void checkParticipation(ParticipationDto participation) {
    if (participation == null) {
      throw new InvalidParticipationDtoException("la participation ne peut etre null");
    } else if (participation.getEtat() == null || participation.getEtat().isEmpty()) {
      throw new InvalidParticipationDtoException("L'état ne peut etre null");
    } else if (!etats.contains(participation.getEtat())) {
      throw new InvalidParticipationDtoException("Etat invalide");
    } else if (etats.contains(participation.getEtat()) && participation.getEvolution() != null) {
      if (!evolutions.contains(participation.getEvolution())) {
        throw new InvalidParticipationDtoException("Evolutoin invalide");
      }
    } else if (participation.getNomEntreprise() == null
        || participation.getNomEntreprise().isEmpty()) {
      throw new InvalidParticipationDtoException("nom entreprise invalide");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.ParticipationBiz#checkEtats(business.dto.ParticipationDto,
   * business.dto.ParticipationDto)
   */
  @Override
  public void checkEtats(ParticipationDto temp, ParticipationDto participation) {
    if (temp.isAnnulee()) {
      throw new InvalidParticipationDtoException("Cette participation est annulée");
    }
    if (temp.getEtat().equals("refusee")) {
      throw new InvalidParticipationDtoException("Cette participation ne peut changer d'état");
    }
    if (temp.getEtat().equals("confirmee") && !participation.getEtat().equals("confirmee")) {
      throw new InvalidParticipationDtoException("cette participation ne peut changer d'état");
    } else if (temp.getEvolution() != null) {
      checkEvolutions(temp, participation);
    }
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.ParticipationBiz#checkEvolutions(business.dto.ParticipationDto,
   * business.dto.ParticipationDto)
   */
  @Override
  public void checkEvolutions(ParticipationDto temp, ParticipationDto participation) {
    if (temp.getEvolution().equals("facturee") && !participation.getEvolution().equals("payee")) {
      throw new InvalidParticipationDtoException(
          "Cette participation ne peut pas passer à cet état");
    }
    if (temp.getEvolution().equals("payee") && !participation.getEvolution().equals("payee")) {
      throw new InvalidParticipationDtoException("cette participation ne peut pas changer d'état");
    }
    // TODO Auto-generated method stub

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
    ParticipationImpl other = (ParticipationImpl) obj;
    if (idParticipation != other.idParticipation) {
      return false;
    }
    return true;
  }



}
