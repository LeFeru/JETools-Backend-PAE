package business.factories;

import business.dto.EntrepriseDto;
import business.dto.JourneeDto;
import business.dto.ParticipationDto;
import business.dto.PersonneContactDto;
import business.dto.PresenceDto;
import business.dto.UtilisateurDto;
import business.impl.EntrepriseImpl;
import business.impl.JourneeImpl;
import business.impl.ParticipationImpl;
import business.impl.PersonneContactImpl;
import business.impl.PresenceImpl;
import business.impl.UtilisateurImpl;

/**
 * Classe suivant le pattern design singleton permettant la distribution d'instance de type Dto.
 * 
 * @author rachid.
 *
 */
public class BizFactoryImpl implements BizFactory {

  /**
   * Renvoie une référence vers une nouvelle instance d'UtilisateurDto.
   * 
   * @author rachid.
   * @return une référence vers une nouvelle instance d'UtilisateurDto.
   */
  @Override
  public UtilisateurDto getUtilisateurDto() {
    return new UtilisateurImpl();
  }

  /**
   * Renvoie une référence vers une nouvelle instance d'EntrepriseDto.
   * 
   * @author Nathan.
   * @return une référence vers une nouvelle instance d'EntrepriseDto.
   */
  @Override
public EntrepriseDto getEntrepriseDto() {
    return new EntrepriseImpl();
  }

  /**
   * Renvoie une référence vers une nouvelle instance de JourneeDto.
   * 
   * @author Roman.
   * @return une référence vers une nouvelle instance de JourneeDto.
   */
  @Override
  public JourneeDto getJourneeDto() {
    return new JourneeImpl();
  }

  /**
   * Renvoie une référence vers une nouvelle instance de PersonneContactDto.
   * 
   * @author Roman.
   * @return une référence vers une nouvelle instance de PersonneContactDto.
   */
  @Override
  public PersonneContactDto getPersonneContactDto() {
    return new PersonneContactImpl();
  }

  /**
   * Renvoie une référence vers une nouvelle instance de ParticipationDto.
   * 
   * @author Roman.
   * @return une référence vers une nouvelle instance de ParticipationDto.
   */
  @Override
  public ParticipationDto getParticipationDto() {
    return new ParticipationImpl();
  }

  /**
   * Renvoie une référence vers une nouvelle instance de PresenceDto.
   * 
   * @author Roman.
   * @return une référence vers une nouvelle instance de PresenceDto.
   */
  @Override
  public PresenceDto getPresenceDto() {
    return new PresenceImpl();
  }

}
