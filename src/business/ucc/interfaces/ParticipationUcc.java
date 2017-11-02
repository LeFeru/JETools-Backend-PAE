package business.ucc.interfaces;

import java.util.List;

import business.dto.EntrepriseDto;
import business.dto.ParticipationDto;
import business.dto.PersonneContactDto;

public interface ParticipationUcc {

  /**
   * Cherche l'entreprise et la journée passée en paramètre, vérifie si elles existent et, dans
   * l'affirmative, créé une participation pour cette entreprise à cette JE donnée.
   * 
   * @param entrepriseDtos Entreprise invitée à la JE passée en paramètre.
   * 
   * @return List de PersonneContactDto Une map contenant l'entreprise invitée et l'ensemble de ses
   *         personnes de contact.
   */
  List<PersonneContactDto> inviter(List<EntrepriseDto> entrepriseDtos);

  /**
   * Methode qui cree une liste avec les entreprises qui respectent les criteres de preselections et
   * qui ne sont pas encore invitées.
   * 
   * @return la liste avec les entreprises.
   */
  List<EntrepriseDto> listeSelectionEntreprises();

  /**
   * Cree une liste qui donne toutes les entreprises qui ne sont pas selectionnés pr cette JE.
   * 
   * @return une liste avec toutes les entreprises non selectionnées et qui n'ont pas encore été
   *         invitées.
   */
  List<EntrepriseDto> listeEntrepriseNonSelectionnees();

  /**
   * Liste de toutes les entreprises invitées à cette journee.
   * 
   * @param idJournee l'id de la Journee d'entreprises.
   * @return une liste d'entreprises contenant toutes les entreprises invitées.
   */
  List<EntrepriseDto> entrepriseInvitees(int idJournee);

  /**
   * Annule une participation dans la DB.
   * 
   * @param participation une instance de ParticipationDto qui contient les informations de la
   *        participation à annuler dans la DB.
   * @return la participation annulée,une BizException en cas d'echec.
   */
  ParticipationDto annulerParticipation(ParticipationDto participation);

  /**
   * Cree une liste qui donnes toutes les participations qui n'ont pas été annulés pour cette JE.
   * 
   * @param participation qui contient la date de la JE.
   * @return une liste avec toutes les participations non annulées.
   */
  List<ParticipationDto> participationsNonAnnulees(ParticipationDto participation);

  /**
   * Modifie la participation (son état, évolution) en base de données.
   * 
   * @param participation Participation a modifier en base de donnees.
   * @return la participation modifiée.
   */
  ParticipationDto modifierParticipation(ParticipationDto participation);
}
