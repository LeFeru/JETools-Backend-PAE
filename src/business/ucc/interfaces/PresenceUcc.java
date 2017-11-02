package business.ucc.interfaces;

import java.util.List;

import business.dto.EntrepriseDto;
import business.dto.JourneeDto;
import business.dto.PersonneContactDto;
import business.dto.PresenceDto;

/**
 * @author skubi.
 *
 */
public interface PresenceUcc {

  /**
   * Indique présent les personnes de contact de l'entreprise entrepriseDto indiquées dans la liste
   * personneDeContactDtos pour la journée journee.
   * 
   * @param entrepriseDto contenant le nom de l'entreprise ayant les personnes de contact indiquées
   *        comme présentes.
   * @param personneContactDtos liste des personnes de contact à inviter.
   * @param journee la journée où on tente d'indiquer la présence des personnes de contact.
   * @return liste des présences effectuées en base de données.
   */
  List<PresenceDto> indiquerPresence(EntrepriseDto entrepriseDto, JourneeDto journee,
      List<PersonneContactDto> personneContactDtos);

  /**
   * Cree un set de toutes les personnes de contact d'une entreprise donnee presentes dans la DB.
   * 
   * @param entrepriseDto id de l'entreprise selon lequel on cherche les personnes de contact.
   * @param journeeDto id de la journee selon lequel on cherche les personnes de contact avec leur
   *        presence.
   * @return un Set de PersonneContactDto.
   */
  List<PersonneContactDto> listerContactsParEntreprise(EntrepriseDto entrepriseDto,
      JourneeDto journeeDto);

}
