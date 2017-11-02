package dal.dao.interfaces;

import business.dto.PresenceDto;

/**
 * @author Skubi.
 *
 */

public interface PresenceDao {

  /**
   * Insere une presence en base de donnees.
   * 
   * @param presenceDto presence a ajouter en base de donnees.
   * @return la presence ajoutee en base de donnees.
   */
  PresenceDto insererPresence(PresenceDto presenceDto);

  /**
   * Cherche une presence en DB en fonction de l'id de la participation et de l'id de la personne de
   * contact.
   * 
   * @param presenceDto instance de ParticipationDto qui contient les infos necessaires à la
   *        recherche.
   * @return la presence presente en base de données.
   */
  PresenceDto findPresenceByIdParticipationIdPersonne(PresenceDto presenceDto);
}
