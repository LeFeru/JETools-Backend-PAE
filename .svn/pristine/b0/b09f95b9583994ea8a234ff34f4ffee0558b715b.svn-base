package dal.dao.interfaces;

import java.util.ArrayList;
import java.util.List;

import business.dto.EntrepriseDto;
import business.dto.PersonneContactDto;

public interface PersonneContactDao {
  /**
   * Crée une personne de contact en base de données.
   * 
   * @param personneContactDto contient l'ensemble des informations à insérer en DB pour la creation
   *        d'une personne de contact.
   * @return une personneContactDto contenant l'ensemble des champs donnés via le paramètre plus
   *         l'id personne
   */
  PersonneContactDto creerPersonneContact(PersonneContactDto personneContactDto);

  /**
   * Trouve la personne de contact dans la base de données en fonction de son mail.
   * 
   * @param email String grace auquel on trouve la personne de contact.
   * @return une personneContactDto avec une partie des infomrations de la personne.
   */
  PersonneContactDto findPersonneContactByMail(String email);

  /**
   * Crée une liste de toutes les personnes de ocntact presentes et actives dans la DB.
   * 
   * @return une ArrayList de personneContactDto.
   */
  ArrayList<PersonneContactDto> listeContacts();

  /**
   * Trouve la personne de contact dans la base de données en fonction de son numero de telephone.
   * 
   * @param telephone String grace auquel on trouve la pesonne de contact.
   * @return une personneContactDto avec une aprtie des informations de la personne.
   */
  PersonneContactDto findPersonneContactByTelephone(String telephone);

  /**
   * Modifie une personne de contact dans la DB.
   * 
   * @param personne la personne à modifier dans la DB.
   * @return la personne modifiée si succès,une entrepriseDto avec un id negatif en cas d'echec.
   */
  PersonneContactDto modifierPersonneContact(PersonneContactDto personne);

  /**
   * Trouve la personne de contact dans la DB en fonction de son id.
   * 
   * @param idPersonne Integer grace auquel on trouve la personne de contact.
   * @return une personneContactDto avec une partie des informations de la personne.
   */
  PersonneContactDto findPersonneById(int idPersonne);

  /**
   * Desactive la personne dans la DB.
   * 
   * @param personne PersonneContactDto contenant l'id grace à laquelle on trouve la personne de
   *        contact.
   * @return un integer negatif en cas d'echec,l'id de la personne desactivée en cas de succès.
   */
  int desactiverPersonne(PersonneContactDto personne);

  /**
   * Trouves toutes les personnes de contact actives appartenant à une entreprise.
   * 
   * @param entrepriseDto l'entreprise sur laquelle on va rechercher les personnes de contact.
   * @return un ensemble qui possede toutes les personnes de contact de l'entreprise.
   */
  List<PersonneContactDto> findPersonneByIdEntreprise(EntrepriseDto entrepriseDto);

  /**
   * Trouves toutes les personnes de contact appartenant à une entreprise.
   * 
   * @param entrepriseDto l'entreprise sur laquelle on va rechercher les personnes de contact.
   * @return un ensemble qui possede toutes les personnes de contact de l'entreprise.
   */
  List<PersonneContactDto> findPersonneByIdEntrepriseNonDesactivees(EntrepriseDto entrepriseDto);

}
