package dal.dao.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import business.dto.JourneeDto;

/**
 * @author rachid.
 *
 */
public interface JourneeDao {

  /**
   * Crée une journée en base de données.
   * 
   * @param journeeDto contient l'ensemble des informations à insérer en DB pour la création d'une
   *        JE.
   * @return une JourneeDto contenant l'ensemble des champs donnés via le paramètre plus
   *         l'id_journee.
   */
  JourneeDto creerJournee(JourneeDto journeeDto);

  /**
   * Cherche une journée avec la date donnée en paramètre.
   * 
   * @param date LocalDate, date recherchée en base de données.
   * @return une JourneeDto avec son id si elle a été trouvée en base de données.
   */
  JourneeDto findJourneeByDate(LocalDate date);

  /**
   * Cherche les journées non cloturée.
   * 
   * @return une JourneeDto avec son id si elle a été trouvée en base de données.
   */
  JourneeDto findJourneeActive();

  /**
   * Cloture les journées encore actives.
   */
  void cloturerJournees();

  /**
   * Renvoie les journées encore actives.
   * 
   * @return ArrayList de journées actives.
   */
  ArrayList<JourneeDto> findJournees();

  /**
   * Cherche une journée avec l'id passé en paramètre.
   * 
   * @param idJournee id recherché en base de données.
   * @return une JourneeDto avec son id si elle a été trouvée en base de données.
   */
  JourneeDto findJourneeById(int idJournee);

  /**
   * Cloture une journée avec l'id passé en paramètre.
   * 
   * @param idJournee id de la journée à cloturer.
   * @return instance de JourneeDto avec l'id de la journée cloturée.
   */
  int cloturerJournee(int idJournee);

  JourneeDto findDerniereJournee();
}
