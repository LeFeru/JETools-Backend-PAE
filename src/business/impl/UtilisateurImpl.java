package business.impl;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import business.biz.UtilisateurBiz;
import business.dto.UtilisateurDto;
import exceptions.BCryptDysfonctionnelException;
import exceptions.InvalidUtilisateurDtoException;


/**
 * Classe UtilisateurImpl implémentant l'interface UtilisateurDto et UtilisateurBiz.
 * 
 * @author rachid.
 *
 */
public class UtilisateurImpl implements UtilisateurBiz {
  private int id;
  private String pseudo;
  private String nom;
  private String prenom;
  private String email;
  private LocalDateTime dateInscription;
  private String mdp;
  private String mdpConfirme;
  private boolean responsable;
  private int numVersion;
  private int nombreTentativesConnexions;
  private LocalDateTime dateDerniereTentative;

  /**
   * Constructeur complet de la classe UtilisateurImpl.
   * 
   * @param id valeur entière
   * @param pseudo référence vers un objet String contenant le pseudo
   * @param nom référence vers un objet String contenant le nom
   * @param prenom référence vers un objet String contenant le prenom
   * @param email référence vers un objet String contenant l'email
   * @param dateInscription référence vers un objet LocalDateTime contenant la dateInscription
   * @param mdp référence vers un objet String contenant le mdp
   * @param responsable valeur booléenne représentant le fait qu'un utilisateur soit un responsable
   *        ou non
   */
  public UtilisateurImpl(int id, String pseudo, String nom, String prenom, String email,
      LocalDateTime dateInscription, String mdp, String mdpConfirme, boolean responsable,
      int numVersion) {
    super();
    this.id = id;
    this.pseudo = pseudo;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.dateInscription = dateInscription;
    this.mdp = mdp;
    this.mdpConfirme = mdpConfirme;
    this.responsable = responsable;
    this.numVersion = numVersion;
  }

  /**
   * Retourne le mdpConfirme.
   * 
   * @return the mdpConfirme.
   */
  public String getMdpConfirme() {
    return mdpConfirme;
  }

  /**
   * Modifie le mdpConfirme.
   * 
   * @param mdpConfirme the mdpConfirme to set.
   */
  public void setMdpConfirme(String mdpConfirme) {
    this.mdpConfirme = mdpConfirme;
  }

  /**
   * Constructeur vide de la classe UtilisateurImpl.
   */
  public UtilisateurImpl() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#getId()
   */
  @Override
  public int getId() {
    return id;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#setId(int)
   */
  @Override
  public void setId(int id) {
    this.id = id;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#getNom()
   */
  @Override
  public String getNom() {
    return nom;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#setNom(java.lang.String)
   */
  @Override
  public void setNom(String nom) {
    this.nom = nom;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#getPrenom()
   */
  @Override
  public String getPrenom() {
    return prenom;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#setPrenom(java.lang.String)
   */
  @Override
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#getEmail()
   */
  @Override
  public String getEmail() {
    return email;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#setEmail(java.lang.String)
   */
  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#getDateInscription()
   */
  @Override
  public LocalDateTime getDateInscription() {
    return dateInscription;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#setDateInscription(java.time.LocalDateTime)
   */
  @Override
  public void setDateInscription(LocalDateTime dateInscription) {
    this.dateInscription = dateInscription;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#getMdp()
   */
  @Override
  public String getMdp() {
    return mdp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#setMdp(java.lang.String)
   */
  @Override
  public void setMdp(String mdp) {
    this.mdp = mdp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#isResponsable()
   */
  @Override
  public boolean isResponsable() {
    return responsable;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#setResponsable(boolean)
   */
  @Override
  public void setResponsable(boolean responsable) {
    this.responsable = responsable;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#getPseudo()
   */
  @Override
  public String getPseudo() {
    return pseudo;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.impl.UtilisateurDto#setPseudo(java.lang.String)
   */
  @Override
  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  @Override
  public int getNumVersion() {
    return this.numVersion;
  }

  @Override
  public void setNumVersion(int numVersion) {
    this.numVersion = numVersion;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.UtilisateurBiz#checkMdp(business.dto.UtilisateurDto,
   * business.dto.UtilisateurDto)
   */
  @Override
  public boolean checkMdp(UtilisateurDto u1, UtilisateurDto u2) {
    return BCrypt.checkpw(u1.getMdp(), u2.getMdp());
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.UtilisateurBiz#crypterMdp(business.dto.UtilisateurDto)
   */
  @Override
  public UtilisateurDto crypterMdp(UtilisateurDto utilisateur) {
    String mdp = null;
    if (!(mdp = BCrypt.hashpw(utilisateur.getMdp(), BCrypt.gensalt(12)))
        .equals(utilisateur.getMdp())) {
      utilisateur.setMdp(mdp);
    } else {
      throw new BCryptDysfonctionnelException();
    }
    return utilisateur;
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
    result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
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
    UtilisateurImpl other = (UtilisateurImpl) obj;
    if (pseudo == null) {
      if (other.pseudo != null) {
        return false;
      }
    } else if (!pseudo.equals(other.pseudo)) {
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "UtilisateurImpl [id=" + id + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
        + prenom + ", email=" + email + ", dateInscription=" + dateInscription + ", mdp=" + mdp
        + ", mdpConfirme=" + mdpConfirme + ", responsable=" + responsable + "]";
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.UtilisateurBiz#checkBeforeConnexion()
   */
  @Override
  public void checkBeforeConnexion() {
    if (this.getPseudo() == null || this.getPseudo().isEmpty()) {
      throw new InvalidUtilisateurDtoException(
          "Le pseudo contenu dans l'instance d'UtilisateurDto ne peut être null ou vide");
    } else if (this.getMdp() == null || this.getMdp().isEmpty()) {
      throw new InvalidUtilisateurDtoException(
          "Le mot de passe contenu dans l'instance d'UtilisateurDto ne peut être null ou vide");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.biz.UtilisateurBiz#checkBeforeInscription()
   */
  @Override
  public void checkBeforeInscription() {
    this.checkBeforeConnexion();
    String mail1 = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@";
    String mail2 = "((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|";
    String mail3 = "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    String mdp1 = "^((?=.*[0-9])(?=.*[A-Za-z&@\\\"'(§è!çà)\\\\-_^$¨";
    String mdp2 = "*[\\\\]ùµ%£´`,?;.:\\\\/+=<>])[^\t\n\r\" \"]{8,20})$";
    Pattern patternEmail = Pattern.compile(mail1 + mail2 + mail3);
    Pattern patternNom = Pattern.compile("^[a-zA-Zéèêç-]{2,20}( )?[a-zA-Zéèêç-]{0,20}$");
    Pattern patternPseudo = Pattern.compile("^[a-zA-Z-_.0-9]{4,15}$");
    Pattern patternMdp = Pattern.compile(mdp1 + mdp2);
    if (this.getNom() == null || this.getNom().isEmpty()) {
      throw new InvalidUtilisateurDtoException(
          "Le nom contenu dans l'instance d'UtilisateurDto ne peut être null ou vide");
    } else if (this.getPrenom() == null || this.getPrenom().isEmpty()) {
      throw new InvalidUtilisateurDtoException(
          "Le prénom contenu dans l'instance d'UtilisateurDto ne peut être null ou vide");
    } else if (this.getEmail() == null || this.getEmail().isEmpty()) {
      throw new InvalidUtilisateurDtoException(
          "L'email contenu dans l'instance d'UtilisateurDto ne peut être null ou vide");
    } else if (this.getMdpConfirme() != null && !this.getMdpConfirme().isEmpty()
        && !this.getMdp().equals(this.getMdpConfirme())) {
      throw new InvalidUtilisateurDtoException("Les deux mots de passes ne concordent pas");
    } else if (!patternNom.matcher(this.getNom()).matches()) {
      throw new InvalidUtilisateurDtoException(
          "Le nom contenu dans l'instance d'UtilisateurDto n'est pas du bon format");
    } else if (!patternNom.matcher(this.getPrenom()).matches()) {
      throw new InvalidUtilisateurDtoException(
          "Le prenom contenu dans l'instance d'UtilisateurDto n'est pas du bon format");
    } else if (!patternPseudo.matcher(this.getPseudo()).matches()) {
      throw new InvalidUtilisateurDtoException(
          "Le pseudo contenu dans l'instance d'UtilisateurDto n'est pas du bon format");
    } else if (!patternMdp.matcher(this.getMdp()).matches()) {
      throw new InvalidUtilisateurDtoException(
          "Le mot de passe contenu dans l'instance d'UtilisateurDto n'est pas du bon format");
    } else if (!patternEmail.matcher(this.getEmail()).matches()) {
      throw new InvalidUtilisateurDtoException(
          "Le mail contenu dans l'instance d'UtilisateurDto n'est pas du bon format");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.UtilisateurDto#getNombreTentativesConnexions()
   */
  @Override
  public int getNombreTentativesConnexions() {
    return this.nombreTentativesConnexions;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.UtilisateurDto#setNombreTentativesConnexions(int)
   */
  @Override
  public void setNombreTentativesConnexions(int nbTentativesConnexions) {
    this.nombreTentativesConnexions = nbTentativesConnexions;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.UtilisateurDto#getDateDerniereTentative()
   */
  @Override
  public LocalDateTime getDateDerniereTentative() {
    return this.dateDerniereTentative;
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.dto.UtilisateurDto#setDateDerniereTentative(java.time.LocalDateTime)
   */
  @Override
  public void setDateDerniereTentative(LocalDateTime dateDerniereTentative) {
    this.dateDerniereTentative = dateDerniereTentative;
  }
}
