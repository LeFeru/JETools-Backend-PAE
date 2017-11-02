package business.impl;

import java.util.regex.Pattern;

import business.biz.PersonneContactBiz;
import business.dto.PersonneContactDto;
import exceptions.InvalidPersonneContactDtoException;

public class PersonneContactImpl implements PersonneContactBiz {
  private int idPersonne;
  private int idEntreprise;
  private int numVersion;
  private String nom;
  private String prenom;
  private String email;
  private String telephone;
  private boolean actif;
  private String nomEntreprise;
  private boolean present;

  /**
   * Constructeur de PersonneContactImpl.
   * 
   * @param idPersonne.
   * @param idEntreprise.
   * @param numVersion.
   * @param nom.
   * @param prenom.
   * @param email.
   * @param telephone.
   * @param actif.
   */
  public PersonneContactImpl(int idPersonne, int idEntreprise, int numVersion, String nom,
      String prenom, String email, String telephone, boolean actif, String nomEntreprise,
      boolean present) {
    super();
    this.idPersonne = idPersonne;
    this.idEntreprise = idEntreprise;
    this.numVersion = numVersion;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.telephone = telephone;
    this.actif = actif;
    this.nomEntreprise = nomEntreprise;
    this.present = present;
  }

  /**
   * Constructeur vide de PersonneContactImpl.
   */
  public PersonneContactImpl() {
    super();
  }

  /**
   * Renvoie true si la personne de contact est annoncee comme presente.
   * 
   * @return the present.
   */
  public boolean isPresent() {
    return present;
  }

  /**
   * Modifie la presence de la personne de contact.
   *
   * @param present the present to set.
   */
  public void setPresent(boolean present) {
    this.present = present;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#getIdPersonne()
   */
  @Override
public int getIdPersonne() {
    return idPersonne;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#setIdPersonne(int)
   */
  @Override
public void setIdPersonne(int idPersonne) {
    this.idPersonne = idPersonne;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#getNom()
   */
  @Override
public String getNom() {
    return nom;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#setNom(java.lang.String)
   */
  @Override
public void setNom(String nom) {
    this.nom = nom;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#getPrenom()
   */
  @Override
public String getPrenom() {
    return prenom;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#setPrenom(java.lang.String)
   */
  @Override
public void setPrenom(String prenom) {
    this.prenom = prenom;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#getEmail()
   */
  @Override
public String getEmail() {
    return email;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#setEmail(java.lang.String)
   */
  @Override
public void setEmail(String email) {
    this.email = email;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#getTelephone()
   */
  @Override
public String getTelephone() {
    return telephone;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#setTelephone(java.lang.String)
   */
  @Override
public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#getNumVersion()
   */
  @Override
public int getNumVersion() {
    return numVersion;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#setNumVersion(int)
   */
  @Override
public void setNumVersion(int numVersion) {
    this.numVersion = numVersion;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#isActif()
   */
  @Override
public boolean isActif() {
    return actif;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#setActif(boolean)
   */
  @Override
public void setActif(boolean actif) {
    this.actif = actif;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#getIdEntreprise()
   */
  @Override
public int getIdEntreprise() {
    return idEntreprise;
  }


  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#setIdEntreprise(int)
   */
  @Override
public void setIdEntreprise(int idEntreprise) {
    this.idEntreprise = idEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#getNomEntreprise()
   */
  @Override
public String getNomEntreprise() {
    return nomEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.PersonneContactDto#setNomEntreprise(java.lang.String)
   */
  @Override
public void setNomEntreprise(String nomEntreprise) {
    this.nomEntreprise = nomEntreprise;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.PersonneContactBiz#checkPersonneContact(business.dto.PersonneContactDto)
   */
  @Override
  public void checkPersonneContact(PersonneContactDto personneContactDto) {
    Pattern patternNom = Pattern.compile("^[a-zA-Zéèêç-]{2,20}( )?[a-zA-Zéèêç-]{0,20}$");
    String mail1 = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@";
    String mail2 = "((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|";
    String mail3 = "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    Pattern patternEmail = Pattern.compile(mail1 + mail2 + mail3);
    Pattern patternTelephone = Pattern.compile("^(0|\\+[0-9]{2}|00[0-9]{2})[0-9]{6,10}$");
    if (personneContactDto == null) {
      throw new InvalidPersonneContactDtoException(
          "La personne de contact ne peut etre null pour l'uc creer Personne de contact");
    }
    if (personneContactDto.getIdEntreprise() < 0) {
      throw new InvalidPersonneContactDtoException("entreprise nulle");
    } else if (personneContactDto.getNom() == null || personneContactDto.getNom().isEmpty()) {
      throw new InvalidPersonneContactDtoException(
          "La personne de contact ne peut ne pas avoir de nom");
    } else if (personneContactDto.getPrenom() == null || personneContactDto.getPrenom().isEmpty()) {
      throw new InvalidPersonneContactDtoException(
          "La personne de contact ne peut avoir de prenom null");
    } else if ((personneContactDto.getTelephone() == null
        || personneContactDto.getTelephone().isEmpty())
        && (personneContactDto.getEmail() == null || personneContactDto.getEmail().isEmpty())) {
      throw new InvalidPersonneContactDtoException(
          "Il doit y avoir au moins le telephone ou le mail");
    } else if ((personneContactDto.getEmail() != null
        && !personneContactDto.getEmail().isEmpty())
        && !patternEmail.matcher(personneContactDto.getEmail()).matches()) {
      throw new InvalidPersonneContactDtoException("Le mail n'a pas le bon format");
    } else if ((personneContactDto.getTelephone() != null
        && !personneContactDto.getTelephone().isEmpty()
        && !patternTelephone.matcher(personneContactDto.getTelephone()).matches())) {
      throw new InvalidPersonneContactDtoException("Le telephone n'a pas le bon format");
    } else if (!patternNom.matcher(personneContactDto.getNom()).matches()) {
      throw new InvalidPersonneContactDtoException(
          "Mauvais format du nom de la personne de contact");
    } else if (!patternNom.matcher(personneContactDto.getPrenom()).matches()) {
      throw new InvalidPersonneContactDtoException(
          "Mauvais format du prenom de la personne de contact");
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "PersonneContactImpl [idPersonne=" + idPersonne + ", idEntreprise=" + idEntreprise
        + ", numVersion=" + numVersion + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
        + ", telephone=" + telephone + ", actif=" + actif + ", nomEntreprise=" + nomEntreprise
        + ", present=" + present + "]";
  }
}
