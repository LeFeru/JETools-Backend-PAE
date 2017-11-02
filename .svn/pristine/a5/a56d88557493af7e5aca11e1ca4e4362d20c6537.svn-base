package business.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import business.biz.EntrepriseBiz;
import business.dto.EntrepriseDto;
import business.dto.UtilisateurDto;
import exceptions.InvalidEntrepriseDtoException;

/**
 * @author Nathan.
 *
 */
public class EntrepriseImpl implements EntrepriseBiz, EntrepriseDto {
  private int idEntreprise;
  private String nomEntreprise;
  private UtilisateurDto createur;
  private LocalDateTime dateCreation;
  private LocalDate dateDerniereParticipationPayee;
  private String rue;
  private String numero;
  private String boite;
  private String codePostal;
  private String commune;
  private int numVersion;

  /**
   * @param idEntreprise.
   * @param nomEntreprise.
   * @param createur.
   * @param dateCreation.
   * @param rue.
   * @param numero.
   * @param boite.
   * @param codePostal.
   * @param commune.
   */
  public EntrepriseImpl(int idEntreprise, String nomEntreprise, UtilisateurDto createur,
      LocalDateTime dateCreation, LocalDate dateDerniereParticipationPayee, String rue,
      String numero, String boite, String codePostal, String commune, int numVersion) {
    super();
    this.idEntreprise = idEntreprise;
    this.nomEntreprise = nomEntreprise;
    this.createur = createur;
    this.dateCreation = dateCreation;
    this.rue = rue;
    this.numero = numero;
    this.boite = boite;
    this.codePostal = codePostal;
    this.commune = commune;
    this.dateDerniereParticipationPayee = dateDerniereParticipationPayee;
    this.numVersion = numVersion;
  }



  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getNumVersion()
   */
  @Override
  public int getNumVersion() {
    return numVersion;
  }



  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setNumVersion(int)
   */
  @Override
  public void setNumVersion(int numVersion) {
    this.numVersion = numVersion;
  }



  /**
   * Constructeur vide EntrepriseImpl.
   */
  public EntrepriseImpl() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getIdEntreprise()
   */
  @Override
  public int getIdEntreprise() {
    return idEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setIdEntreprise(int)
   */
  @Override
  public void setIdEntreprise(int idEntreprise) {
    this.idEntreprise = idEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getNomEntreprise()
   */
  @Override
  public String getNomEntreprise() {
    return nomEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setNomEntreprise(java.lang.String)
   */
  @Override
  public void setNomEntreprise(String nomEntreprise) {
    this.nomEntreprise = nomEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getDateCreation()
   */
  @Override
  public LocalDateTime getDateCreation() {
    return dateCreation;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setDateCreation(java.time.LocalDateTime)
   */
  @Override
  public void setDateCreation(LocalDateTime dateCreation) {
    this.dateCreation = dateCreation;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getCreateur()
   */
  @Override
  public UtilisateurDto getCreateur() {
    return createur;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setCreateur(business.dto.UtilisateurDto)
   */
  @Override
  public void setCreateur(UtilisateurDto createur) {
    this.createur = createur;
  }



  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getDateDerniereParticipationPayee()
   */
  @Override
  public LocalDate getDateDerniereParticipationPayee() {
    return dateDerniereParticipationPayee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setDateDerniereParticipationPayee(java.time.LocalDate)
   */
  @Override
  public void setDateDerniereParticipationPayee(LocalDate dateDerniereParticipationPayee) {
    this.dateDerniereParticipationPayee = dateDerniereParticipationPayee;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getRue()
   */
  @Override
  public String getRue() {
    return rue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setRue(java.lang.String)
   */
  @Override
  public void setRue(String rue) {
    this.rue = rue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getNumero()
   */
  @Override
  public String getNumero() {
    return numero;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setNumero(java.lang.String)
   */
  @Override
  public void setNumero(String numero) {
    this.numero = numero;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getBoite()
   */
  @Override
  public String getBoite() {
    return boite;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setBoite(java.lang.String)
   */
  @Override
  public void setBoite(String boite) {
    this.boite = boite;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getCodePostal()
   */
  @Override
  public String getCodePostal() {
    return codePostal;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setCodePostal(java.lang.String)
   */
  @Override
  public void setCodePostal(String codePostal) {
    this.codePostal = codePostal;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#getCommune()
   */
  @Override
  public String getCommune() {
    return commune;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.EntrepriseDto#setCommune(java.lang.String)
   */
  @Override
  public void setCommune(String commune) {
    this.commune = commune;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "EntrepriseImpl [idEntreprise=" + idEntreprise + ", nomEntreprise=" + nomEntreprise
        + ", createur=" + createur + ", dateCreation=" + dateCreation
        + ", dateDerniereParticipationPayee=" + dateDerniereParticipationPayee + ", rue=" + rue
        + ", numero=" + numero + ", boite=" + boite + ", codePostal=" + codePostal + ", commune="
        + commune + ", numVersion=" + numVersion + "]";
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
    result = prime * result + idEntreprise;
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
    EntrepriseImpl other = (EntrepriseImpl) obj;
    if (idEntreprise != other.idEntreprise) {
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.EntrepriseBiz#CheckEntreprise(business.dto.EntrepriseDto)
   */
  @Override
  public void checkEntreprise(EntrepriseDto entrepriseDto) {
    Pattern patternNomEntreprise = Pattern.compile("^.{1,150}$");
    Pattern patternRue = Pattern.compile("^.{1,150}$");
    Pattern patternBoite = Pattern.compile("^[0-9a-zA-Z]{1,10}$");
    Pattern patternNumero = Pattern.compile("^[0-9a-zA-Z]{1,10}$");
    Pattern patternCodePostal = Pattern.compile("^[-0-9a-zA-Z]{1,10}$");
    Pattern patternCommune = Pattern.compile("^.{1,75}$");
    if (entrepriseDto == null) {
      throw new InvalidEntrepriseDtoException(
          "L'entrepriseDto ne peut etre null pour l'uc 'Creer Entreprise'");
    } else if (entrepriseDto.getNomEntreprise() == null
        || entrepriseDto.getNomEntreprise().isEmpty()) {
      throw new InvalidEntrepriseDtoException("Le nom de l'entreprise ne peut etre null ou vide");
    } else if (entrepriseDto.getRue() == null || entrepriseDto.getRue().isEmpty()) {
      throw new InvalidEntrepriseDtoException("La rue de l'entreprise ne peut etre null ou vide");
    } else if (entrepriseDto.getNumero() == null || entrepriseDto.getNumero().isEmpty()) {
      throw new InvalidEntrepriseDtoException(
          "Le numero de l'entreprise ne peut etre null ou vide");
    } else if (entrepriseDto.getCodePostal() == null || entrepriseDto.getCodePostal().isEmpty()) {
      throw new InvalidEntrepriseDtoException("Le code postal de l'entreprise ne peut etre null");
    } else if (entrepriseDto.getCommune() == null || entrepriseDto.getCommune().isEmpty()) {
      throw new InvalidEntrepriseDtoException(
          "La commune de l'entreprise ne peut etre null ou vide");
    } else if (!patternNomEntreprise.matcher(entrepriseDto.getNomEntreprise()).matches()) {
      throw new InvalidEntrepriseDtoException("Mauvais format du nom de l'entreprise");
    } else if (!patternRue.matcher(entrepriseDto.getRue()).matches()) {
      throw new InvalidEntrepriseDtoException("mauvais format de la rue de l'entreprise");
    } else if (entrepriseDto.getBoite() != null && !entrepriseDto.getBoite().isEmpty()
        && !patternBoite.matcher(entrepriseDto.getBoite()).matches()) {
      throw new InvalidEntrepriseDtoException("mauvais format de la boite de l'entreprise");
    } else if (!patternNumero.matcher(entrepriseDto.getNumero()).matches()) {
      throw new InvalidEntrepriseDtoException("mauvais format du numero de l'entreprise");
    } else if (!patternCodePostal.matcher(entrepriseDto.getCodePostal()).matches()) {
      throw new InvalidEntrepriseDtoException("mauvais format du code postal de l'entreprise");
    } else if (!patternCommune.matcher(entrepriseDto.getCommune()).matches()) {
      throw new InvalidEntrepriseDtoException("mauvais format de la commune de l'entreprise");
    }

  }



}
