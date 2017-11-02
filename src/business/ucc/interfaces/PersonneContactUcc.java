package business.ucc.interfaces;

import java.util.ArrayList;

import business.dto.PersonneContactDto;

public interface PersonneContactUcc {
  /**
   * Vérifie et injecte les données d'une personneContactDto dans la base de données.
   * 
   * @param personne la référence d 'linstance de personneContactDto contenant toutes les
   *        informations à inserer.
   * @return une référence vers une instance PersonneContactDto contenant en plus des informations
   *         données en paramètres, l'id renvoyé par la base de données, si non la référence donné
   *         en paramètre.
   */
  PersonneContactDto creerPersonneContact(PersonneContactDto personne);

  /**
   * Cree une liste de toutes les personnes de contact présentes dans la DB.
   * 
   * @return une ArrayList de PersonneContactDto.
   */
  ArrayList<PersonneContactDto> listeContacts();

  /**
   * Charge une instance de PersonneContactDto via l'id de la personne.
   * 
   * @param idPersonne Integer qui permet de chercher la personne dans la DB.
   * @return la personne de contact,une BizException en cas d'échec.
   */
  PersonneContactDto chargerContact(int idPersonne);

  /**
   * Modifie une personne de contact dans la DB.
   * 
   * @param personne l'instance de PersonneContactDto qui contient l'id de la personne à modifier
   *        ainsi que les attributs à modifier.
   * @return la personne modifiée si succes, une BizException en cas d'echec.
   */
  PersonneContactDto modifierContact(PersonneContactDto personne);

  /**
   * Desactive une personne de contact dans la DB.
   * 
   * @param personne l'instace de PersonneContactDto qui contient l'id de la personne à desactiver.
   * @return l'id de la personne desactivée,une bizException en cas d'échec.
   */
  int desactiverContact(PersonneContactDto personne);
}
