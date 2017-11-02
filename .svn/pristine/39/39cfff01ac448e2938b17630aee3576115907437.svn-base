package business.factories;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
 * @author rachid.
 *
 */
public class BizFactoryStub implements BizFactory {

  /**
   * Constructeur BizFactoryStub.
   */
  public BizFactoryStub() {}

  /*
   * (non-Javadoc)
   * 
   * @see business.factories.BizFactory#getUtilisateurDto()
   */
  @Override
  public UtilisateurDto getUtilisateurDto() {
    // password == stubstub1
    String mdpCrypte = "$2a$12$W.vskbPpdouqMfh52xHSgeaMh4GRZYVziQTAr6hQbUMOAjI8DwVzy";
    return new UtilisateurImpl(1, "Stub", "Stub", "Stub", "Stub@Stub.com", LocalDateTime.now(),
        mdpCrypte, mdpCrypte, false, 1);
    //Mdp non crypté = stubstub1
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.factories.BizFactory#getEntrepriseDto()
   */
  @Override
  public EntrepriseDto getEntrepriseDto() {
    return new EntrepriseImpl(1, "Stub Company", getUtilisateurDto(), LocalDateTime.now(), null,
        "Boulevard Stub", "1", "1S", "1000", "Stub-Ville", 1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.factories.BizFactory#getJourneeDto()
   */
  @Override
  public JourneeDto getJourneeDto() {
    return new JourneeImpl(1, LocalDate.of(2018, 10, 10), getUtilisateurDto(), false, 1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.factories.BizFactory#getPersonneContactDto()
   */
  @Override
  public PersonneContactDto getPersonneContactDto() {
    return new PersonneContactImpl(1, getEntrepriseDto().getIdEntreprise(), 1, "Van Stub", "Stub",
        "VanStub.Stub@Stub.com", "0485952258", true, getEntrepriseDto().getNomEntreprise(), true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.factories.BizFactory#getParticipationDto()
   */
  @Override
  public ParticipationDto getParticipationDto() {
    return new ParticipationImpl(1, getJourneeDto().getIdJournee(),
        getEntrepriseDto().getIdEntreprise(), "invitee", null, false, getJourneeDto().getDateJe(),
        getEntrepriseDto().getNomEntreprise());
  }

  /*
   * (non-Javadoc)
   * 
   * @see business.factories.BizFactory#getPresenceDto()
   */
  @Override
  public PresenceDto getPresenceDto() {
    return new PresenceImpl(getParticipationDto().getIdParticipation(),
        getPersonneContactDto().getIdPersonne());
  }

}
