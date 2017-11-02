package dal.dao.interfaces;

import java.util.ArrayList;
import java.util.List;

import business.dto.ParticipationDto;

public interface ParticipationDao {
  /**
   * Insere un participant en base de données.
   * 
   * @param participationDto le participant à insérer en base de données.
   * @return ParticipantDto, le participant inséré en base de données avec son id mis à jour.
   */
  ParticipationDto insererParticipation(ParticipationDto participationDto);

  /**
   * Cherche une participation en base de données en fonction de l'idJournee et l'idEntreprise.
   * 
   * @param participationDto la participation contenant l'idJournee et l'idEntreprise recherché.
   * @return ParticipantDto, le participant trouvé en base de données.
   */
  ParticipationDto findParticipationByIdJourneeIdEntreprise(ParticipationDto participationDto);

  /**
   * Cherche une participation en base de données en fonction de l'idEntreprise.
   * 
   * @param idEntreprise l'id de l'entreprise à rechercher
   * @return ParticipantDto, le participant trouvé en base de données.
   */
  List<ParticipationDto> findParticipationByIdEntreprise(int idEntreprise);

  /**
   * Annule la participation en DB.
   * 
   * @param participation l'instance de ParticipationDto qui contient les informations necessaires à
   *        l'annulation de la participation.
   */
  void annulerParticipation(ParticipationDto participation);

  /**
   * Cree une liste de toutes les participations non annulees sur une JE passée en parametre.
   * 
   * @param idJournee l'id de la journée sur laquelle on veut recuperer les participations.
   * @return une liste de participations
   */
  ArrayList<ParticipationDto> participationsNonAnnulees(int idJournee);

  /**
   * modifie l'état ou l'évolution d'une participation en DB.
   * 
   * @param participation la participation possedant les elements modifiées.
   * @return la participation modifiée.
   */
  ParticipationDto modifierParticipation(ParticipationDto participation);

  /**
   * Cherche une participation en fonction de l'id de la participation.
   * 
   * @param idParticipation l'id sur lequel on recherche la participation.
   * @return une instance de ParticipationDto contenant les informations sur la participation.
   */
  ParticipationDto findParticipationByIdParticipation(int idParticipation);
}
