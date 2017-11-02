package business.ucc.interfaces;

import java.util.ArrayList;

import business.dto.JourneeDto;

/**
 * @author rachid.
 *
 */
public interface JourneeUcc {

  /**
   * Vérifie et injecte les données d'une JourneeDto dans la base de données.
   * 
   * @param journeeDto la référence de l'instance de journéeDto contenant toutes les informations à
   *        inserer
   * @return une référence vers une instance EntrepriseDto contenant en plus des informations
   *         données en paramètres, l'id renvoyé par la base de données,si non la référence donné en
   *         paramètre
   */
  JourneeDto creerJournee(JourneeDto journeeDto);


  /**
   * Recupere la journee active.
   * 
   * @return une instance de JourneeDto avec les données de la journée actuelle.
   */
  JourneeDto getJourneeCourante();

  /**
   * Fournit la liste de toutes les journées jusqu'à aujourd'hui.
   * 
   * @return une liste d'instance de journées
   */
  ArrayList<JourneeDto> getJournees();

  int cloturerJournee(JourneeDto journeeDto);
}
