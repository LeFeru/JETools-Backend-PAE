package ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.owlike.genson.Genson;

import business.dto.EntrepriseDto;
import business.dto.JourneeDto;
import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.ucc.interfaces.PresenceUcc;
import exceptions.NotImplementedException;

public class PresenceIhm {
  private Dealer dealer;
  private PresenceUcc presenceUcc;
  private BizFactory bizFactory;
  private Genson genson;
  private EntrepriseDto entrepriseDto;
  private JourneeDto journeeDto;
  private Logger logger;

  /**
   * Constructeur de la classe ParticipationIhm.
   * 
   * @param dealer Dealer passé en paramètre pour initialiser les attriuts.
   */
  public PresenceIhm(Dealer dealer) {
    this.dealer = dealer;
    this.presenceUcc = this.dealer.getPresenceUcc();
    this.bizFactory = this.dealer.getBizFactory();
    this.genson = new Genson();
    this.entrepriseDto = bizFactory.getEntrepriseDto();
    this.journeeDto = bizFactory.getJourneeDto();
    this.logger = Logger.getLogger("reportsLogger");
  }

  /**
   * Redirige l'action en fonction de l'attribut action de l'HttpServletRequest.
   * 
   * @param req HttpServletRequest.
   * @return L'objet à parser et à renvoyer dans resp.
   */
  public Object operer(HttpServletRequest req) throws IOException {
    logger.info(req.toString());
    switch (req.getParameter("req")) {
      case "indiquerPresence":
        return indiquerPresence(req);
      case "listerContactsParEntreprise":
        return listerContactsParEntreprise(req);
      default:
        throw new NotImplementedException();
    }
  }

  private Object indiquerPresence(HttpServletRequest req) {
    entrepriseDto.setNomEntreprise(req.getParameter("nomEntreprise"));
    journeeDto.setIdJournee(Integer.parseInt(req.getParameter("idJournee")));
    String idPersonnes = req.getParameter("idPersonnes");
    List<PersonneContactDto> personneDtos = deserializePersonneContact(idPersonnes);
    // prendre le retour
    return presenceUcc.indiquerPresence(entrepriseDto, journeeDto, personneDtos);
  }

  private List<PersonneContactDto> deserializePersonneContact(String jsonPersonnes) {
    logger.info("jsonEntreprises " + jsonPersonnes);
    List<PersonneContactDto> personneContactDtos = new ArrayList<>();
    List<String> tmp = genson.deserialize(jsonPersonnes, ArrayList.class);
    for (String idPers : tmp) {
      PersonneContactDto personneContact = bizFactory.getPersonneContactDto();
      personneContact.setIdPersonne(Integer.parseInt(idPers));
      personneContactDtos.add(personneContact);
    }
    return personneContactDtos;
  }

  private Object listerContactsParEntreprise(HttpServletRequest req) {
    EntrepriseDto entrepriseDto = bizFactory.getEntrepriseDto();
    JourneeDto journeeDto = bizFactory.getJourneeDto();
    journeeDto.setIdJournee(Integer.parseInt(req.getParameter("idJournee")));
    // int idEntreprise = Integer.parseInt(req.getParameter("idEntreprise"));
    entrepriseDto.setNomEntreprise(req.getParameter("nomEntreprise"));
    List<PersonneContactDto> personnesContact =
        presenceUcc.listerContactsParEntreprise(entrepriseDto, journeeDto);
    return personnesContact;
  }
}
