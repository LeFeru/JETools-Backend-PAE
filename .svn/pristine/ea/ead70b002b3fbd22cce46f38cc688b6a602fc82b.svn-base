package business.biz;

import business.dto.ParticipationDto;
import exceptions.InvalidJourneeDtoException;

public interface ParticipationBiz extends ParticipationDto {
  /**
   * Verifie si les conditions de création d'une journée sont respectées.
   * 
   * @author Nathan
   * @param participation designe l'instance de ParticipationDto à vérifier.
   * 
   */
  void checkParticipation(ParticipationDto participation);

  /**
   * Verifie si les changements d'état effectués sont valides.
   * 
   * @param temp une instance de ParticipationDto contenant les infos actuelles sur la
   *        participation.
   * @param participation une instance de ParticipationDto contenant les futures infos sur la
   *        participation.
   * @throws InvalidJourneeDtoException si les changements d'états ne sont pas respectés.
   */
  void checkEtats(ParticipationDto temp, ParticipationDto participation);

  /**
   * Verifie si l'évolution de la participation est valide.
   * 
   * @param temp une instance de ParticipationDto contenant les infos actuelles sur la
   *        participation.
   * @param participation une instance de ParticipationDto contenant les futures infos sur la
   *        participation.
   * @throws InvalidJourneeDtoException si l'evolution n'est pas valide.
   */
  void checkEvolutions(ParticipationDto temp, ParticipationDto participation);
}
